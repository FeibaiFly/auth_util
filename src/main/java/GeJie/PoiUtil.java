package GeJie;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * poi 导入导出工具类
 *
 * @author luojunjie
 * @date 2018-10-8 20:34
 */
public class PoiUtil {
    private final static String XLS = "xls";

    private final static String XLSX = "xlsx";

    private final static long FILE_MAX_SIZE = 100 * 1024 * 1024L;


    public static List<String[]> readExcel(File file, int... readNum) throws IOException {
        //检查文件
        if (null == file) {
            throw new FileNotFoundException("IMPORT_FILE_NOT_NULL");
        }
        if (file.length() > FILE_MAX_SIZE) {
            throw new FileNotFoundException("FILE_SIZE_MORE_THAN_100_MB");
        }
        //获得文件名
        String fileName = file.getName();
        //判断文件是否是excel文件
        if (!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)) {
            throw new IOException("FILE_NOT_EXCEL");
        }
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = new FileInputStream(file);
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(XLS)) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(XLSX)) {
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if (workbook != null) {
//            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            //获得当前sheet工作表(只读第一个)
            Sheet sheet = workbook.getSheetAt(0);

            if (sheet == null) {
                return null;
            }
            //获得当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();
            //获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();
            //循环所有行
            for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                //获得当前行
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                //获得当前行的开始列(第一列为空也可以获得)
                //int firstCellNum = row.getFirstCellNum();
                int firstCellNum = 0;
                //获得当前行不为空的列数
                //int lastCellNum = row.getPhysicalNumberOfCells();
                //获得列数
                int lastCellNum = 0;
                if (null != readNum && readNum.length > 0) {
                    lastCellNum = readNum[0];
                } else {
                    lastCellNum = sheet.getRow(rowNum).getLastCellNum();
                }

                String[] cells = new String[lastCellNum];
                //循环当前行
                int num = 0;
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                    //无论是否为空，都要返回值
                    Cell cell = row.getCell(cellNum, Row.RETURN_BLANK_AS_NULL);
                    String value = getCellValue(cell);
                    ++num;
                    cells[cellNum] = value.trim();
                }
                if (num > 0) {
                    list.add(cells);
                }
            }
//            }
        }
        return list;
    }


    /**
     * 获取值
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) throws IOException {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        // 把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            boolean flag = false;
            short format = cell.getCellStyle().getDataFormat();
            SimpleDateFormat sdf = null;
            // 日期
            if (format == 14 || format == 31 || format == 57 || format == 58 || (176 <= format && format <= 178)
                    || (182 <= format && format <= 196) || (210 <= format && format <= 213) || (208 == format)) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                flag = true;
            } else if (format == 20 || format == 32 || format == 183 || (200 <= format && format <= 209)) {
                // 时间
                flag = true;
                sdf = new SimpleDateFormat("HH:mm");
            } else {
                // 不是日期格式
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }
            if (flag) {
                double value = cell.getNumericCellValue();
                Date date = DateUtil.getJavaDate(value);
                return sdf.format(date);
            }

        }
        // 判断数据的类型
        switch (cell.getCellType()) {
            // 数字
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            // 字符串
            case Cell.CELL_TYPE_STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            // Boolean
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            // 公式
            case Cell.CELL_TYPE_FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            // 空值
            case Cell.CELL_TYPE_BLANK:
                cellValue = "";
                break;
            // 故障
            case Cell.CELL_TYPE_ERROR:
                throw new IOException("EXCEL_WRONG_FORMAT");
            default:
                throw new IOException("EXCEL_WRONG_FORMAT");
        }
        return cellValue;
    }
}
