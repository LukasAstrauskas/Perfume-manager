package Service;

import DAO.ExcelConection.Excel;
import DAO.ExcelHelper.ExcelFilePathAccess;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Optional;

public class ExcelService {

    private final ExcelFilePathAccess excelPathAccess;

    public ExcelService() {
        excelPathAccess = new ExcelFilePathAccess();
    }


    public String getFilePath() {
        return excelPathAccess.readData();
    }

    public void setFilePath(String path) {
        excelPathAccess.writeData(path);
        Excel.setPath(path);
    }

    public boolean fileExists() {
        return excelPathAccess.checkIfFileExists();
    }

    public boolean checkExcelConnection(String path) {
        Optional<XSSFWorkbook> workbook = Optional.ofNullable(Excel.checkConnection(path));
        return workbook.isPresent();
    }

    public void createNewFile() {
        excelPathAccess.createFile();
    }
}
