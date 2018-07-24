package com.sound.batteries.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 
 * @author liulin
 * @Description: 将excel中的数据读取出来，以List<string[]>的形式返回， excel中的每行数据以数组的形式存在List中
 * @date 2017年5月26日 下午4:06:45
 * @FunctionList: TODO
 */
public class ExcelFileReadUtil {

    private static ExcelFileReadUtil excelFileReadUtil = null;

    /**
     * 
     * @Description: 获取实例
     * @return
     */
    public static ExcelFileReadUtil getInstance() {
        if (excelFileReadUtil == null) {
            excelFileReadUtil = new ExcelFileReadUtil();
        }
        return excelFileReadUtil;
    }

    /**
     * 
     * @Description: 前端页面上传excel类型的附件时，传入UploadedFile，返回excel中的数据
     * @param uploadedFile
     * @return
     * @throws Exception 
     * @throws BusinessException
     */
    public List<String[]> getExcelData(File uploadedFile) throws Exception {
    	
        // 获得文件名
        String fileName = uploadedFile.getName();
        // 判断是否是excel文件，如果不是则抛出异常
        isExcelFile(fileName);
        InputStream is = null;
        try {
            // 获取excel文件的io流
            is = new FileInputStream(uploadedFile);
        } catch (FileNotFoundException fnfExcep) {
            throw new FileNotFoundException(fileName);
        }

        // 读取工作簿中的数据
        List<String[]> returnList = readExcel(fileName, is);
        is.close();
        return returnList;
    }

    /**
     * 
     * @Description: 根据路径获取到excel,读取并返回excel中的数据
     * @param uploadedFile
     * @return
     * @throws Exception 
     * @throws BusinessException
     */
    public List<String[]> getExcelData(String fullFilePath) throws Exception {
        // 判断是否是excel文件，如果不是则抛出异常
        isExcelFile(fullFilePath);
        InputStream is = null;
        try {
            // 获取excel文件的io流
            is = new FileInputStream(fullFilePath);
        } catch (FileNotFoundException fnfExcep) {
            throw new FileNotFoundException(fullFilePath);
        }
        // 读取工作簿中的数据
        List<String[]> returnList = readExcel(fullFilePath, is);
        is.close();
        return returnList;
    }

    /**
     * 
     * @Description: 是否是excel文件
     * @param fileName
     * @throws BusinessException
     */
    private void isExcelFile(String fileName) throws Exception {
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx") && !fileName.toLowerCase().endsWith("csv")) {
            throw new Exception("文件格式只能是xls,xlsx以及csv");
        }
    }

    /**
     * 
     * @Description: 获取工作簿
     * @param filePathOrName
     * @return
     * @throws IOException
     * @throws BusinessException
     */
    private Workbook getWorkbook(String filePathOrName, InputStream is) throws IOException {
        Workbook workbook = null;
        // 根据文件后缀名不同(XLS和XLSX)获得不同的Workbook实现类对象
        if (filePathOrName.endsWith("xls")) {
            // 2003
            workbook = new HSSFWorkbook(is);
        } else if (filePathOrName.endsWith("xlsx")) {
            // 2007
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    /**
     * 
     * @Description: 读取工作簿中的数据
     * @param fileName
     * @param is
     * @return
     * @throws IOException
     * @throws BusinessException
     */
    public List<String[]> readFileExcel(String fileName, InputStream is) throws IOException
    {
        return readExcel(fileName,is);
    }
    /**
     * 
     * @Description: 读取工作簿中的数据
     * @param workbook
     * @return
     * @throws IOException
     * @throws BusinessException
     */
    private List<String[]> readExcel(String filePathOrName, InputStream is) throws IOException {
        Workbook workbook = getWorkbook(filePathOrName, is);
        // 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                // 获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet == null) {
                    continue;
                }
                // 获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                // 获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                // 循环除了第一行的所有行
                for (int rowNum = firstRowNum + 2; rowNum <= lastRowNum; rowNum++) {
                    // 获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    // 获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    // 获得当前行的列数
                    int lastCellNum = row.getLastCellNum();
                    String[] cells = new String[sheet.getRow(0).getLastCellNum()];
                    // 循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
                break;
            }
        }
        return list;
    }

    /**
     * 
     * @Description: 获取单元格的值
     * @param cell
     * @return
     */
    @SuppressWarnings("deprecation")
    private String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        // 判断数据的类型
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_NUMERIC: // 数字
            // cellValue = String.valueOf(cell.getNumericCellValue());
            if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                SimpleDateFormat sdf = null;
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                    sdf = new SimpleDateFormat("HH:mm");
                } else {// 日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }
                Date date = cell.getDateCellValue();
                cellValue = sdf.format(date);
            } else if (cell.getCellStyle().getDataFormat() == 58) {
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                double value = cell.getNumericCellValue();
                Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                cellValue = sdf.format(date);
            } else {
                DecimalFormat df = new DecimalFormat("#.#########");
                cellValue = df.format(cell.getNumericCellValue());
            }
            break;
        case Cell.CELL_TYPE_STRING: // 字符串
            cellValue = String.valueOf(cell.getStringCellValue());
            break;
        case Cell.CELL_TYPE_BOOLEAN: // Boolean
            cellValue = String.valueOf(cell.getBooleanCellValue());
            break;
        case Cell.CELL_TYPE_FORMULA: // 公式
            cellValue = String.valueOf(cell.getCellFormula());
            break;
        case Cell.CELL_TYPE_BLANK: // 空值
            cellValue = "";
            break;
        case Cell.CELL_TYPE_ERROR: // 故障
            cellValue = "非法字符";
            break;
        default:
            cellValue = "未知类型";
            break;
        }
        return cellValue;
    }
    
    public static void main(String[] args){
        ExcelFileReadUtil excelFileReadUtil = ExcelFileReadUtil.getInstance();
        try {
           List<String[]> ls = excelFileReadUtil.getExcelData("D:\\aa.xlsx");
           System.out.println(ls.size());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
