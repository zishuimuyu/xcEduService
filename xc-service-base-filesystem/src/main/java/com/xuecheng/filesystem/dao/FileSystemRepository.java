package com.xuecheng.filesystem.dao;

import com.xuecheng.framework.domain.filesystem.FileSystem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 文件管理系统Dao
 *
 * @author：GJH
 * @createDate：2019/10/12
 * @company：洪荒宇宙加力蹲大学
 */
public interface FileSystemRepository extends MongoRepository<FileSystem,String> {
}
