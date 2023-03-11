package DAO;

import DAO.ExcelHelper.ExcelAccess;
import Model.Ingredient;

import java.util.LinkedList;

public class IngredientDAO implements Dao<Ingredient> {

    private final ExcelAccess excelClass;

    public IngredientDAO() {
        this.excelClass = new ExcelAccess();
    }

    @Override
    public Ingredient get(int place) {
        return excelClass.getOneItem(place);
    }

    @Override
    public int save(Ingredient ingredient) {
        int place = findPlace(ingredient);
//        if (place != -1) {
            excelClass.writeNewData(ingredient, place);
//        } else {
//            excelClass.writeNewData(ingredient);
//        }
        return place-1;
    }


    @Override
    public LinkedList<Ingredient> getAll() {
        LinkedList<Ingredient> list = excelClass.getMaterialsList();
        return list;
    }

    @Override
    public void update(Ingredient ingredient, int place) {
        excelClass.updateData(ingredient, place);
    }

    @Override
    public void delete(int place) {
        excelClass.deleteData(place);
    }


    private int findPlace(Ingredient ingredient) {
        LinkedList<Ingredient> all = getAll();
        int com = -1;
        int place = -1;
        try {
            while (com < 0) {
                place++;
                com = all.get(place).getIngredientName().compareToIgnoreCase(ingredient.getIngredientName());
            }
        } catch (IndexOutOfBoundsException e) {
            return all.size()+1;
        }
        return place+1;
    }

}
