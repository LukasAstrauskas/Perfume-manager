package Service;

import DAO.IngredientDAO;
import Model.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;

public class IngredientService {

    private final IngredientDAO ingredientDAO;

    public IngredientService() {
        this.ingredientDAO = new IngredientDAO();
    }


    public int addNewMaterial(Ingredient ingredient) {
        return ingredientDAO.save(ingredient);
    }

    public LinkedList<Ingredient> gelAllMaterials() {
        LinkedList<Ingredient> all = ingredientDAO.getAll();

        return all;
    }

    public LinkedList<Ingredient> getSelectedList(String text) {
        LinkedList<Ingredient> all = ingredientDAO.getAll();
        LinkedList<Ingredient> elements = new LinkedList<>();
        if (!text.equals("")) {
            String regex = text.toLowerCase().concat(".*");
            for (Ingredient ingredient : all) {
                String name = ingredient.getIngredientName().toLowerCase();
                if (name.matches(regex)) {
                    elements.add(ingredient);
                }
            }
        }
        return elements;


    }

    public void editMaterial(Ingredient ingredient, int place) {
        ingredientDAO.update(ingredient, place);
    }

    public void deleteMaterial(int place) {
        ingredientDAO.delete(place);
    }
}
