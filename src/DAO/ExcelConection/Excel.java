package DAO.ExcelConection;

import DAO.ExcelHelper.ExcelFilePathAccess;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Excel {

    private static XSSFWorkbook workbook;
    private static final ExcelFilePathAccess access = new ExcelFilePathAccess();
    private static String path;


    private Excel() {
        System.out.println("Excel class constructor created.");
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String filePath) {
        Excel.path = filePath;
    }

    public static XSSFWorkbook getWorkbook() {
        FileInputStream inputStream;
        if (workbook == null) {
            try {
                inputStream = new FileInputStream(path);
                workbook = new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                System.out.println("No file path got. Excel class");
                return null;
            }
        }
        return workbook;
    }

    public static XSSFWorkbook checkConnection(String filePath)  {
        FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(filePath);
                workbook = new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                return null;
            }
        return workbook;
    }


    public static String getConnectionMessage() {
        File file = new File(path);
        if (file.exists()) {
            String message = "File found.";
            try {
                FileInputStream input = new FileInputStream(file);
                workbook = new XSSFWorkbook(input);
                input.close();
            } catch (IOException e) {
                return message + " Error happened.";
            } catch (NotOfficeXmlFileException e) {
                return message + " It's not *.xlsx file.";
            }
            return message + " It's .xlsx file.";
        } else {
            return "File Not Found.";
        }
    }
}
