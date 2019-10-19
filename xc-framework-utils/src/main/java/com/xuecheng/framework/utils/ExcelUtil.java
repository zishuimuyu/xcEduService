package com.xuecheng.framework.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Excel工具类
 * @author：GJH
 * @createDate：2019/7/3
 */
public class ExcelUtil {
    /**
     * 2007 版本以上 最大支持1048576行
     */
    public final static String EXCEl_FILE_2007 = "2007";
    /**
     * 2003 版本 最大支持65536行
     */
    public final static String EXCEL_FILE_2003 = "2003";


    /**
     * 导出带有头部标题行的Excel
     * 时间格式默认：yyyy-MM-dd hh:mm:ss
     *
     * @param sheetName    表格标题
     * @param excelHeaders 头部标题集合,如果不设置表头,参数为NUll
     * @param data         数据集合
     * @param out          输出流
     * @param version      2003 或者 2007，不传时默认生成2003版本
     * @return Workbook
     */
    public static Workbook exportExcel(String sheetName, String[] excelHeaders, List<List<String>> data, OutputStream out, String version) {
        if (StringUtils.isBlank(version) || EXCEL_FILE_2003.equals(version.trim())) {
            return exportExcel2003(sheetName, excelHeaders, data, null);
        } else {
            return exportExcel2007(sheetName, excelHeaders, data, null);
        }
    }

    /**
     * 导出Excel文件
     *
     * @param sheetName sheet名称
     * @param title     表头
     * @param data      表格内容
     * @param version   excel版本,versionweinull时,默认生成excel2003
     * @return Workbook
     * @throws IOException
     */
    public static Workbook exportExcel(String sheetName, String[] title, List<List<String>> data, String version) throws IOException {
        // 创建 一个excel文档对象,并声明单元格对象
        Workbook workbook = null;
        if (StringUtils.isEmpty(version) || EXCEL_FILE_2003.equals(version.trim())) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }
        // 创建一个工作薄对象
        Sheet sheet = null;
        if (StringUtils.isEmpty(sheetName)) {
            sheet = workbook.createSheet("sheet1");
        } else {
            sheet = workbook.createSheet(sheetName);
        }
        // 设置第二列的宽度为
        sheet.setColumnWidth(1, 10000);
        // 创建一个行对象,从0行开始
        Row row = sheet.createRow(1);
        // 设置行高23像素
        row.setHeightInPoints(20);
        int index = 0;
        if (title != null && title.length > 0) {
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
            row = sheet.createRow(index);
            // 自动调整列宽
//            sheet.autoSizeColumn(index);
            index++;
            //创建标题
            for (int i = 0; i < title.length; i++) {
                Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
                cell.setCellValue(title[i]);
                CellStyle style = getStyle(workbook);
                // 应用样式对象
                cell.setCellStyle(style);
                // 自动调整列宽
                sheet.autoSizeColumn(i);
            }
        }

        //创建内容
        for (int i = 0; i < data.size(); i++) {
            row = sheet.createRow(index);
            // 自动调整列宽
            sheet.autoSizeColumn(index);
            for (int j = 0; j < data.get(i).size(); j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j, Cell.CELL_TYPE_STRING).setCellValue(data.get(i).get(j));
            }
            index++;
        }
        return workbook;
    }


    /**
     * 此方法生成2003版本的excel,文件名后缀：xls
     *
     * @param sheetName    sheet名称
     * @param excelHeaders 表头
     * @param data         要写入Excel表格的数据
     * @param workBook     HSSFWorkbook
     * @return Workbook
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Workbook exportExcel2003(String sheetName, String[] excelHeaders, List<List<String>> data, HSSFWorkbook workBook) {
        //第一步创建一个HSSFWorkbook,对应一个Excel
        if (workBook == null) {
            workBook = new HSSFWorkbook();
        }
        HSSFSheet sheet;
        //在HSSFWorkbook中添加一个sheet,对应excel中的sheet
        if (StringUtils.isEmpty(sheetName)) {
            sheet = workBook.createSheet("sheet1");
        } else {
            sheet = workBook.createSheet(sheetName);
        }
        //设置表格宽度
        sheet.setDefaultColumnWidth(20);
        //声明列对象
        HSSFRow row = null;
        //声明单元格对象
        HSSFCell cell = null;
        int index = 0;
        // 设置单元格样式,居中
        HSSFCellStyle style = workBook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workBook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        style.setFont(font);

        if (excelHeaders != null && excelHeaders.length > 0) {
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
            row = sheet.createRow(index);
            // 自动调整列宽
            sheet.autoSizeColumn(index);
            index++;
            //创建标题
            for (int i = 0; i < excelHeaders.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(excelHeaders[i]);
                cell.setCellStyle(style);
            }
        }

        //创建内容
        for (int i = 0; i < data.size(); i++) {
            row = sheet.createRow(index);
            // 自动调整列宽
            sheet.autoSizeColumn(index);
            for (int j = 0; j < data.get(i).size(); j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(data.get(i).get(j));
            }
            index++;
        }
        return workBook;
    }

    /**
     * 此方法生成2007版本的excel,文件名后缀：xlsx
     *
     * @param sheetName    sheet名称
     * @param excelHeaders 表头
     * @param data         要写入Excel表格的数据
     * @param workBook     HSSFWorkbook
     * @return Workbook
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Workbook exportExcel2007(String sheetName, String[] excelHeaders, List<List<String>> data, XSSFWorkbook workBook) {
        //第一步创建一个XSSFWorkbook,对应一个Excel
        if (workBook == null) {
            // 声明一个工作薄
            workBook = new XSSFWorkbook();
        }
        XSSFSheet sheet;
        //在XSSFWorkbook中添加一个sheet,对应excel中的sheet
        if (StringUtils.isEmpty(sheetName)) {
            // 生成一个表格
            sheet = workBook.createSheet("sheet1");
        } else {
            sheet = workBook.createSheet(sheetName);
        }
        //设置表格宽度
        sheet.setDefaultColumnWidth(20);
        //声明列对象
        XSSFRow row = null;
        //声明单元格对象
        XSSFCell cell = null;
        int index = 0;
        // 设置单元格样式,居中
        XSSFCellStyle style = workBook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        XSSFFont font = workBook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        style.setFont(font);

        if (excelHeaders != null && excelHeaders.length > 0) {
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
            row = sheet.createRow(index);
            index++;
            //创建标题
            for (int i = 0; i < excelHeaders.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(excelHeaders[i]);
                cell.setCellStyle(style);
            }
        }

        //创建内容
        for (int i = 0; i < data.size(); i++) {
            row = sheet.createRow(index);
            // 自动调整列宽
            sheet.autoSizeColumn(index);
            for (int j = 0; j < data.get(i).size(); j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(data.get(i).get(j));
            }
            index++;
        }
        return workBook;
    }

    /**
     * 从excel中读取数据
     *
     * @param file          excel文件
     * @param startRowIndex 开始读取的行的索引
     * @param cellNumber    sheet中的最大列数或读取的列数
     * @return List<Object[]>
     */
    public static List<Object[]> importFromExcel(File file, Integer startRowIndex, int cellNumber) {
        try {
            FileInputStream is = new FileInputStream(file);
            List<Object[]> result = null;
            if (file.getName().endsWith(".xls")) {
                HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
                for (int i = 0; i < hssfWorkbook.getNumberOfSheets(); i++) {
                    HSSFSheet sheetAt = hssfWorkbook.getSheetAt(i);
                    result = ExcelUtil.importFromExcel2003(sheetAt, startRowIndex, cellNumber);
                }
            }
            if (file.getName().endsWith(".xlsx")) {
                XSSFWorkbook xSSFWorkbook = new XSSFWorkbook(is);
                for (int i = 0; i < xSSFWorkbook.getNumberOfSheets(); i++) {
                    XSSFSheet sheetAt = xSSFWorkbook.getSheetAt(i);
                    result = ExcelUtil.importFromExcel2007(sheetAt, startRowIndex, cellNumber);
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 此方法从Excel2003版本的文件读取数据,文件名后缀：xls
     *
     * @param sheet         需要读取的sheet
     * @param startRowIndex 开始读取的行的索引,第一行的索引是0,第二行是1,以此类推
     * @return List<Object[]>
     */
    public static List<Object[]> importFromExcel2003(HSSFSheet sheet, Integer startRowIndex, int cellNumber) {
        if (sheet == null) {
            return null;
        }
        HSSFRow row;
        List<Object[]> data = new ArrayList<>();
        if (startRowIndex == null) {
            startRowIndex = 0;
        }
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowIndex = startRowIndex; rowIndex < rowCount; rowIndex++) {
            row = sheet.getRow(rowIndex);
            Object[] rowCellArr = new Object[cellNumber];
            int rowCellsCount = row.getPhysicalNumberOfCells();
            for (int cellIndex = 0; cellIndex < rowCellsCount; cellIndex++) {
                row.getCell(cellIndex).setCellType(Cell.CELL_TYPE_STRING);
                rowCellArr[cellIndex] = row.getCell(cellIndex).getStringCellValue();
            }
            data.add(rowCellArr);
        }
        return data;
    }

    /**
     * 此方法从Excel2003版本的文件读取数据,文件名后缀：xls
     *
     * @param sheet         需要读取的sheet
     * @param startRowIndex 开始读取的行的索引,第一行的索引是0,第二行是1,以此类推
     * @return List<Object[]>
     */
    public static List<Object[]> importFromExcel2007(XSSFSheet sheet, Integer startRowIndex, int cellNumber) {
        if (sheet == null) {
            return null;
        }
        //声明列对象
        XSSFRow row = null;
        //声明单元格对象
        List<Object[]> data = new ArrayList<>();
        if (startRowIndex == null) {
            startRowIndex = 0;
        }
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowIndex = startRowIndex; rowIndex < rowCount; rowIndex++) {
            row = sheet.getRow(rowIndex);
            Object[] rowCellArr = new Object[cellNumber];
            int rowCellsCount = row.getPhysicalNumberOfCells();
            for (int cellIndex = 0; cellIndex < rowCellsCount; cellIndex++) {
                row.getCell(cellIndex).setCellType(Cell.CELL_TYPE_STRING);
                rowCellArr[cellIndex] = row.getCell(cellIndex).getStringCellValue();
            }
            data.add(rowCellArr);
        }
        return data;
    }

    /**
     * 读取Excel文件里的数据
     *
     * @param file          excel文件
     * @param startRowIndex 开始读取的行的索引,第一行的索引是0,第二行是1,以此类推
     * @param cellNumber    每行数据的个数(最大个数)
     * @return List<Object[]>
     * @throws IOException
     */
    public static List<Object[]> readExcel(File file, Integer startRowIndex, int cellNumber) throws IOException {
        if (file == null) {
            return null;
        }
        // 创建 一个excel文档对象
        Workbook wb = null;
        if (file.getName().endsWith(".xlsx")) {
            wb = new XSSFWorkbook(new FileInputStream(file));
        } else if (file.getName().endsWith(".xls")) {
            wb = new HSSFWorkbook(new FileInputStream(file));
        }
        // 读取每一个sheet页表格内容
        Sheet sheet = null;
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            sheet = wb.getSheetAt(i);
        }
        Object value = null;
        Row row = null;
        Cell cell = null;
        List<Object[]> rowlist = new LinkedList<Object[]>();
        //遍历行
        for (int i = startRowIndex; i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            // 遍历列
            Object[] cellArr = new Object[cellNumber];
            for (int j = 0; j <= cellNumber; j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                // 格式化 number String
                DecimalFormat df = new DecimalFormat("0");
                // 格式化数字
                DecimalFormat nf = new DecimalFormat("0.00");
                // 格式化日期字符串
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                switch (cell.getCellType()) {
                    // 字符串——String type
                    case Cell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                    // 数字——Number type
                    case Cell.CELL_TYPE_NUMERIC:
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());
                        } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                            value = nf.format(cell.getNumericCellValue());
                        } else {
                            value = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                        }
                        break;
                    // boolean——Boolean type
                    case Cell.CELL_TYPE_BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    // 空白——Blank type
                    case Cell.CELL_TYPE_BLANK:
                        value = "";
                        break;
                    default:// default type
                        value = cell.toString();
                }
                if (value == null || "".equals(value)) {
                    continue;
                }
                cellArr[j] = value;
            }
            rowlist.add(cellArr);
        }
        return rowlist;
    }

    /**
     * 设置样式
     *
     * @param workbook
     * @return CellStyle
     */
    private static CellStyle getStyle(Workbook workbook) {
        // 创建样式对象
        CellStyle style = workbook.createCellStyle();
        // 设置对齐方式
        // 水平居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        // 垂直居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 设置边框
        // 顶部边框粗线
        style.setBorderTop(HSSFCellStyle.BORDER_THICK);
        // 设置为红色
        style.setTopBorderColor(HSSFColor.RED.index);
        // 底部边框双线
        style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
        // 左边边框
        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        // 右边边框
        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        // 设置单元格内容是否自动换行
        style.setWrapText(true);
        // 格式化日期
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        // 设置单元格字体
        // 创建字体对象
        Font font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 14);
        // 设置字体颜色
        font.setColor(HSSFColor.RED.index);
        // 设置为宋体字
        font.setFontName("宋体");
        // 设置粗体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 将字体加入到样式对象
        style.setFont(font);

        return style;
    }

    /**
     * 填写Excel图片区
     *
     * @param picPath  图片路径
     * @param startRow 开始行
     * @param startCol 开始列
     * @param endCol   结束列
     */
    public static void fillTableContentPic(Workbook wb, String version, String picPath, int startRow, int startCol, int endCol) {
        //处理图片文件，以便产生ByteArray
        if (StringUtils.isEmpty(picPath)){
            return;
        }
        ByteArrayOutputStream handlePicture  = handlePicture(picPath);
        Sheet sheet=null;
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            sheet = wb.getSheetAt(i);
        }
        HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 100, 50, (short) startCol, startRow, (short) endCol, startRow + 25);
        //插入图片
        patriarch.createPicture(anchor, wb.addPicture(handlePicture.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
        //导出后删除图片文件
        File picFile = new File(picPath);
        picFile.delete();
    }

    private static ByteArrayOutputStream handlePicture(String pathOfPicture) {
        if (StringUtils.isEmpty(pathOfPicture)){
            return null;
        }
        BufferedImage bufferImg = null;
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        try {
            bufferImg = ImageIO.read(new File(pathOfPicture));
            ImageIO.write(bufferImg, "jpeg", byteArrayOut);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return byteArrayOut;
    }

}
