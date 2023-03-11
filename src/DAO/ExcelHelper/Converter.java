package DAO.ExcelHelper;

import Model.Ingredient;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class Converter {

    private static XSSFCellStyle style = getXssfCellStyle();

    public static Ingredient toObject(Row row) {
        String ingredientName = row.getCell(0).getStringCellValue();
        String botanicalName = row.getCell(1).getStringCellValue();
        double density = row.getCell(2).getNumericCellValue();
        double pricePerGram = row.getCell(3).getNumericCellValue();
        String supplier = row.getCell(4).getStringCellValue();
        String placeOfOrigin = row.getCell(5).getStringCellValue();
        String batch = row.getCell(6).getStringCellValue();

        Ingredient ingredient = new Ingredient(ingredientName, botanicalName,
                density, pricePerGram, supplier, placeOfOrigin, batch);
        return ingredient;
    }


    public static void toRow(Ingredient ingredient, XSSFRow row) {
        row.createCell(0).setCellValue(ingredient.getIngredientName());
        row.createCell(1).setCellValue(ingredient.getBotanicalName());
        row.createCell(2).setCellValue(ingredient.getDensity());
        row.createCell(3).setCellValue(ingredient.getPricePerGram());
        row.createCell(4).setCellValue(ingredient.getSupplier());
        row.createCell(5).setCellValue(ingredient.getPlaceOfOrigin());
        row.createCell(6).setCellValue(ingredient.getBatch());
//        short lastCellNum = row.getLastCellNum();
//        int cellNum = 0;
//
//        while (cellNum<lastCellNum+1){
//            row.getCell(cellNum).setCellStyle(style);
//            cellNum++;
//        }
    }

    private static XSSFCellStyle getXssfCellStyle() {
        XSSFCellStyle style = new XSSFCellStyle(new StylesTable());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        return style;
    }
}
