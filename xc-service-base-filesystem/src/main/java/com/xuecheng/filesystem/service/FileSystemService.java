package com.xuecheng.filesystem.service;

import com.alibaba.fastjson.JSON;
import com.xuecheng.filesystem.dao.FileSystemRepository;
import com.xuecheng.framework.domain.filesystem.FileSystem;
import com.xuecheng.framework.domain.filesystem.response.FileSystemCode;
import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.utils.StringUtil;
import org.apache.poi.ss.formula.functions.T;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 文件管理Service
 *
 * @author：GJH
 * @createDate：2019/10/12
 * @company：洪荒宇宙加力蹲大学
 */
@Service
public class FileSystemService {

    @Value("${xuecheng.fastdfs.tracker_servers}")
    String tracker_servers;
    @Value("${xuecheng.fastdfs.connect_timeout_in_seconds}")
    int connect_timeout_in_seconds;
    @Value("${xuecheng.fastdfs.network_timeout_in_seconds}")
    int network_timeout_in_seconds;
    @Value("${xuecheng.fastdfs.charset}")
    String charset;

    @Autowired
    FileSystemRepository fileSystemRepository;

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @param filetag       文件标签
     * @param businesskey   业务key
     * @param metadata      元信息,json格式
     * @return
     */
    public UploadFileResult uoload(MultipartFile multipartFile, String filetag, String businesskey, String metadata) {
        if (multipartFile == null) {
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_FILEISNULL);
        }
        //1,将文件上传到FastDFS文件管理系统,得到文件ID
        String fileId = upload2fastDFS(multipartFile);
        if (StringUtil.isEmpty(fileId)) {
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_SERVERFAIL);
        }
        //2.将文件id和其他信息存入mongoDB
        FileSystem fileSystem = new FileSystem();
        fileSystem.setFileId(fileId);
        fileSystem.setFilePath(fileId);
        fileSystem.setFiletag(filetag);
        fileSystem.setBusinesskey(businesskey);
        fileSystem.setFileName(multipartFile.getOriginalFilename());
        fileSystem.setFileType(multipartFile.getContentType());
        if (StringUtil.isNotEmpty(metadata)) {
            try {
                Map metadataMap = JSON.parseObject(metadata, Map.class);
                fileSystem.setMetadata(metadataMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        FileSystem save = fileSystemRepository.save(fileSystem);
        return new UploadFileResult(CommonCode.SUCCESS, save);
    }


    /**
     * 上传文件到DFS
     *
     * @param multipartFile 文件
     * @return 文件id
     */
    public String upload2fastDFS(MultipartFile multipartFile) {
        //1.初始化fastDFS环境
        initFastDFSConfig();
        //2 创建trackerClient
        TrackerClient trackerClient = new TrackerClient();
        try {
            //2.1得到 TrackerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //2.2得到StorageServer
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            //2.3得到storageClient
            StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
            //2.4 得到文件的字节
            byte[] fileBytes = multipartFile.getBytes();
            //2.5 得到文件的原始名称
            String originalFilename = multipartFile.getOriginalFilename();
            //2.6 得到文件扩展名
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //2.7  上传文件到dfs
            String file1Id = storageClient.upload_file1(fileBytes, extName, null);
            return file1Id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化fastDFS环境
     */
    private void initFastDFSConfig() {
        //初始化tracker服务地址（多个tracker中间以半角逗号分隔）
        try {
            ClientGlobal.initByTrackers(tracker_servers);
            ClientGlobal.setG_charset(charset);
            ClientGlobal.setG_network_timeout(network_timeout_in_seconds);
            ClientGlobal.setG_connect_timeout(connect_timeout_in_seconds);
        } catch (Exception e) {
            e.printStackTrace();
            //抛出异常
            ExceptionCast.cast(FileSystemCode.FS_INITFDFSERROR);
        }
    }
}
