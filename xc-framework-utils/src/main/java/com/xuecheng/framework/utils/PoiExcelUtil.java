package com.xuecheng.framework.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PoiExcel工具类
 *
 * @author：GJH
 * @createDate：2019/9/6
 */
@SuppressWarnings("deprecation")
public class PoiExcelUtil {
    /** 日志 */
    //   private static Logger logger = Logger.getLogger(PoiExcelUtil.class);

    /**
     * 获取excel中所有的合并单元格的值，封装成List<Map<String,Object>>
     *
     * @parame sheet excel的sheet页
     */
    public static List<Map<String, Object>> getAllMergedValueFromExcel(Sheet sheet) {
        // 声明一个list集合，保存所有的合并单元格的值
        List<Map<String, Object>> mergedValueList = new ArrayList<Map<String, Object>>();
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            // 获取合并单元格
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn(); // 第一列
            int lastColumn = ca.getLastColumn(); // 最后一列
            int firstRow = ca.getFirstRow(); // 第一行
            int lastRow = ca.getLastRow(); // 最后一行
            Row fRow = sheet.getRow(firstRow);
            Cell fCell = fRow.getCell(firstColumn);
            String mergedValue = getCellValue(fCell); // 合并单元个的值
            map.put("firstColumn", firstColumn);
            map.put("lastColumn", lastColumn);
            map.put("firstRow", firstRow);
            map.put("lastRow", lastRow);
            map.put("mergedValue", mergedValue);
            mergedValueList.add(map);
        }
        return mergedValueList;
    }

    /**
     * 获取excel文件的sheet页
     *
     * @param excelFile excel文件
     * @param sheetNo   sheet页的编号，从0 开始
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static Sheet getSheetFromExcelAt(File excelFile, int sheetNo) throws FileNotFoundException, IOException {
        Workbook book = null;
        try {
            // Excel 2007获取方法
            book = new XSSFWorkbook(new FileInputStream(excelFile));
        } catch (Exception ex) {
            // Excel 2003获取方法
            book = new HSSFWorkbook(new FileInputStream(excelFile));
        }
        // 读取表格的指定编号的sheet页
        Sheet sheet = book.getSheetAt(sheetNo);
        return sheet;
    }

    /**
     * 获取合并单元格的值
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static Map<String, Object> getMergedRegionValue(Sheet sheet, int row, int column, int firstColumn,
                                                           int lastColumn, int firstRow, int lastRow) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            firstColumn = ca.getFirstColumn();
            lastColumn = ca.getLastColumn();
            firstRow = ca.getFirstRow();
            lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow) {
                Map<String, Object> map = new HashMap<String, Object>();
                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    map.put("firstRow", firstRow);
                    map.put("lastRow", lastRow);
                    map.put("cellValue", getCellValue(fCell));
                    return map;
                }
            }
        }

        return null;
    }

    /**
     * 判断合并了行
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    @SuppressWarnings("unused")
    private static boolean isMergedRow(Sheet sheet, int row, int column) {
        // 获得一个 sheet 中合并单元格的数量
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            // 获得合并单元格CellRangeAddress（int， int， int， int），参数：起始行号，终止行号，
            // 起始列号，终止列号
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn(); // 起始列号
            int lastColumn = range.getLastColumn();// 结束列号
            int firstRow = range.getFirstRow(); // 起始行号
            int lastRow = range.getLastRow(); // 结束行号
            if (row >= firstRow && row <= lastRow) {
                if (column == firstColumn && column == lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     *
     * @param sheet
     * @param row    行下标
     * @param column 列下标
     * @return
     */
    private static boolean isMergedRegion(Sheet sheet, int row, int column) {
        // 获得一个 sheet 中合并单元格的数量
        int sheetMergeCount = sheet.getNumMergedRegions();
        // 循环遍历素有的合并单元格
        for (int i = 0; i < sheetMergeCount; i++) {
            // 获得合并单元格CellRangeAddress（int， int， int， int），参数：起始行号，终止行号，
            // 起始列号，终止列号
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取指定起始行列的excel的sheet页上所有的单元格的属性值(此处的终止行列的值必须大于起始值，而且仅行合并，若存在列合并，可以拆分为行合并)
     *
     * @param sheet    选中的excel的sheet页
     * @param beginRow 指定的起始行
     * @param endRow   指定的终止行
     * @param beginCol 指定的起始列
     * @param endCol   指定的终止列
     * @return
     */
    public static List<Map<String, Object>> getCellAttrHasMerged(Sheet sheet, int beginRow, int endRow, int beginCol,
                                                                 int endCol) {
        List<Map<String, Object>> rowList = new ArrayList<Map<String, Object>>();
        while (beginRow <= endRow) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("firstRow", beginRow);
            int currEndRow = beginRow;
            int temRowNum = 0;
            // 判断指定的区域的第一个单元格是否为合并单元格
            boolean isMerged = isMergedRegion(sheet, beginRow, beginCol);
            // 如果是合并单元格，则作如下处理
            if (isMerged) {
                // 循环每一个单元格，取出其中的数据
                for (int i = beginCol; i <= endCol; i++) {
                    // 取出单元格的值，并且得到本次行合并后最后的行的行号
                    Map<String, Object> cellAttrMap = getMergedRegionValue(sheet, beginRow, i, i, i, beginRow,
                            currEndRow);
                    temRowNum = Integer.parseInt(cellAttrMap.get("lastRow").toString());
                    map.put("cell" + i, cellAttrMap.get("cellValue"));
                    map.put("lastRow", temRowNum);
                }
                beginRow = temRowNum + 1;
            } else {
                // 获取指定行号的行对象
                Row row = sheet.getRow(beginRow);
                for (int i = beginCol; i <= endCol; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = getCellValue(cell);
                    map.put("cell" + i, cellValue.trim());
                    map.put("lastRow", beginRow);
                }
                beginRow += 1;
            }
            // 将一行数据添加到List集合中
            rowList.add(map);
        }
        return rowList;
    }

    /**
     * 获取指定起始行列的excel的sheet页上所有的单元格的属性值（选中的excel表格区域不包含合并单元格）
     *
     * @param sheet    选中的excel的sheet页
     * @param beginRow 指定的起始行
     * @param endRow   指定的终止行
     * @param beginCol 指定的起始列
     * @param endCol   指定的终止列
     * @return
     */
    public static List<Map<String, Object>> getCellAttrNoMerged(Sheet sheet, int beginRow, int endRow, int beginCol,
                                                                int endCol) {
        List<Map<String, Object>> rowList = new ArrayList<Map<String, Object>>();
        while (beginRow <= endRow) {
            Map<String, Object> map = new HashMap<String, Object>();
            // 获取指定行号的行对象
            Row row = sheet.getRow(beginRow);
            for (int i = beginCol; i <= endCol; i++) {
                Cell cell = row.getCell(i);
                String cellValue = getCellValue(cell);
                map.put("cell" + i, cellValue);
            }
            beginRow += 1;
            // 将一行数据添加到List集合中
            rowList.add(map);
        }
        return rowList;
    }

    /**
     * 判断sheet页中是否含有合并单元格
     *
     * @param sheet
     * @return
     */
    @SuppressWarnings("unused")
    private static boolean hasMerged(Sheet sheet) {
        return sheet.getNumMergedRegions() > 0 ? true : false;
    }

    /**
     * 合并单元格
     *
     * @param sheet
     * @param firstRow 开始行
     * @param lastRow  结束行
     * @param firstCol 开始列
     * @param lastCol  结束列
     */
    @SuppressWarnings("unused")
    private static void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {

        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return cell.getCellFormula();
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return "";
    }

    /**
     * @param wb       HSSFWorkbook
     * @param fileName 文件名称
     * @return 0：正常导出，-1：文档不存在，-2：导出失败，-3：输出流关闭异常
     */
    public static int writeWorkBool(HSSFWorkbook wb, String fileName) {
        int res = 0;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // logger.error(e.getMessage());
            System.out.println("导出文档失败，错误原因：" + e.getMessage());
            res = -1;
        } catch (IOException e) {
            e.printStackTrace();
            //  logger.error(e.getMessage());
            System.out.println("导出文档失败，错误原因：" + e.getMessage());
            res = -2;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    //      logger.error(e.getMessage());
                    res = -3;
                }
            }
        }
        return res;
    }

    /**
     * 创建HSSFSheet工作簿
     *
     * @param wb        HSSFWorkbook
     * @param sheetName String
     * @return HSSFSheet
     */
    public static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName) {
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(15);
        sheet.setGridsPrinted(false);
        sheet.setDisplayGridlines(false);
        return sheet;
    }

    /**
     * 创建HSSFRow
     *
     * @param sheet  HSSFSheet
     * @param rowNum 行数
     * @param height 高度
     * @return HSSFRow
     */
    public static HSSFRow createRow(HSSFSheet sheet, int rowNum, int height) {
        HSSFRow row = sheet.createRow(rowNum);
        row.setHeight((short) height);
        return row;
    }

    /**
     * 功能：创建CellStyle样式
     *
     * @param wb              HSSFWorkbook
     * @param backgroundColor 背景色
     * @param foregroundColor 前置色
     * @param halign          位置
     * @param font            字体
     * @return 样式
     */

    public static CellStyle createCellStyle(HSSFWorkbook wb, short backgroundColor, short foregroundColor,
                                            short halign, Font font) {
        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(halign);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cs.setFillBackgroundColor(backgroundColor);
        cs.setFillForegroundColor(foregroundColor);
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs.setFont(font);
        return cs;
    }

    /**
     * 功能：创建带边框的CellStyle样式
     *
     * @param wb              HSSFWorkbook
     * @param backgroundColor 背景色
     * @param foregroundColor 前置色
     * @param halign          位置
     * @param font            字体
     * @return 样式
     */
    public static CellStyle createBorderCellStyle(HSSFWorkbook wb, short backgroundColor, short foregroundColor,
                                                  short halign, Font font) {
        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(halign);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cs.setFillBackgroundColor(backgroundColor);
        cs.setFillForegroundColor(foregroundColor);
        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs.setFont(font);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        return cs;
    }

    /**
     * 功能：创建CELL
     *
     * @param row     HSSFRow
     * @param cellNum int
     * @param style   HSSFStyle
     * @return HSSFCell
     */
    public static HSSFCell createCell(HSSFRow row, int cellNum, CellStyle style) {
        HSSFCell cell = row.createCell(cellNum);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        cell.setCellStyle(style);
        return cell;
    }

    /**
     * 功能：合并单元格
     *
     * @param sheet       HSSFSheet
     * @param firstRow    int
     * @param lastRow     int
     * @param firstColumn int
     * @param lastColumn  int
     * @return int 合并区域号码
     */
    public static int mergeCell(HSSFSheet sheet, int firstRow, int lastRow, int firstColumn, int lastColumn) {
        return sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstColumn, lastColumn));
    }

    /**
     * 功能：创建字体
     *
     * @param wb         HSSFWorkbook
     * @param boldweight short
     * @param color      short
     * @param size       short
     * @return Font
     */
    public static Font createFont(HSSFWorkbook wb, short boldweight, short color, short size) {
        Font font = wb.createFont();
        font.setBoldweight(boldweight);
        font.setColor(color);
        font.setFontHeightInPoints(size);
        return font;
    }

    /**
     * 设置合并单元格的边框样式
     *
     * @param sheet HSSFSheet
     * @param ca    CellRangAddress
     * @param style CellStyle
     */
    public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress ca, CellStyle style) {
        for (int i = ca.getFirstRow(); i <= ca.getLastRow(); i++) {
            HSSFRow row = HSSFCellUtil.getRow(i, sheet);
            for (int j = ca.getFirstColumn(); j <= ca.getLastColumn(); j++) {
                HSSFCell cell = HSSFCellUtil.getCell(row, j);
                cell.setCellStyle(style);
            }
        }
    }
}
