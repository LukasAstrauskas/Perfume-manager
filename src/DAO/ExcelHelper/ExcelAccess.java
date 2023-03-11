package DAO.ExcelHelper;

import DAO.ExcelConection.Excel;
import Model.Ingredient;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class ExcelAccess {

    private final String path = Excel.getPath();
    private final XSSFWorkbook workBook = Excel.getWorkbook();
    private final XSSFSheet sheet;

    public ExcelAccess() {
        this.sheet = workBook.getSheetAt(0);
    }

    public void writeNewData(Ingredient ingredient, int place) {
        if (place <= getMaterialsList().size()) {
            shiftRowsDown(place);
        }
        fillRow(ingredient, place);
        saveFile();
    }

    public void deleteData(int place) {
        XSSFRow row = sheet.getRow(place);
        sheet.removeRow(row);
        shiftRowsUp(place);
        saveFile();
    }

    public void updateData(Ingredient ingredient, int place) {
        fillRow(ingredient, place);
        saveFile();
    }

    public Ingredient getOneItem(int place) {
        XSSFRow row = sheet.getRow(place);
        return Converter.toObject(row);
    }

    public LinkedList<Ingredient> getMaterialsList() {
        LinkedList<Ingredient> list = new LinkedList<>();
        if (this.workBook != null) {
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);
            int i = 1;
            while (i < lastRowNum + 1) {
                Ingredient ingredient = Converter.toObject(sheet.getRow(i));
                list.add(ingredient);
                i++;
            }
            return list;
        } else {
            return new LinkedList<>();
        }
    }

    private void fillRow(Ingredient material, int place) {
        XSSFRow row = sheet.getRow(place);
        if (row == null) {
            row = sheet.createRow(place);
        }
        Converter.toRow(material, row);
    }

    private void shiftRowsDown(int place) {
        int lastRowNum = sheet.getLastRowNum();
        sheet.shiftRows(place, lastRowNum, 1);
    }

    private void shiftRowsUp(int place) {
        int lastRowNum = sheet.getLastRowNum();
        if (place + 1 <= lastRowNum) {
            sheet.shiftRows(place + 1, lastRowNum, -1);
        }
    }

    private void saveFile() {
        try {
            FileOutputStream out = new FileOutputStream(path);
            workBook.write(out);
            out.close();
//            workBook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
