package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ingredient {

    private StringProperty ingredientName;
    private StringProperty botanicalName;
    private DoubleProperty density;
    private DoubleProperty pricePerGram;
    private StringProperty supplier;
    private StringProperty placeOfOrigin;
    private StringProperty batch;

    public Ingredient() {
    }

    public Ingredient(String ingredientName, String botanicalName, Double density,
                      Double pricePerGram, String supplier, String placeOfOrigin, String batch) {
        setIngredientName(ingredientName);
        setBotanicalName(botanicalName);
        setDensity(density);
        setPricePerGram(pricePerGram);
        setSupplier(supplier);
        setPlaceOfOrigin(placeOfOrigin);
        setBatch(batch);
    }

    public String getIngredientName() {
        return ingredientNameProperty().get();
    }

    public StringProperty ingredientNameProperty() {
        if (ingredientName == null) {
            ingredientName = new SimpleStringProperty(this, "ingredientName");
        }
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientNameProperty().set(ingredientName);
    }

    public String getBotanicalName() {
        return botanicalNameProperty().get();
    }

    public StringProperty botanicalNameProperty() {
        if (botanicalName == null) {
            botanicalName = new SimpleStringProperty(this, "botanicalName");
        }
        return botanicalName;
    }

    public void setBotanicalName(String botanicalName) {
        this.botanicalNameProperty().set(botanicalName);
    }

    public double getDensity() {
        return densityProperty().get();
    }

    public DoubleProperty densityProperty() {
        if (density == null) {
            density = new SimpleDoubleProperty(this, "density");
        }
        return density;
    }

    public void setDensity(double density) {
        this.densityProperty().set(density);
    }

    public double getPricePerGram() {
        return pricePerGramProperty().get();
    }

    public DoubleProperty pricePerGramProperty() {
        if (pricePerGram == null) {
            pricePerGram = new SimpleDoubleProperty(this, "pricePerGram");
        }
        return pricePerGram;
    }

    public void setPricePerGram(double pricePerGram) {
        this.pricePerGramProperty().set(pricePerGram);
    }

    public String getSupplier() {
        return supplierProperty().get();
    }

    public StringProperty supplierProperty() {
        if (supplier == null) {
            supplier = new SimpleStringProperty(this, "supplier");
        }
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplierProperty().set(supplier);
    }

    public String getPlaceOfOrigin() {
        return placeOfOriginProperty().get();
    }

    public StringProperty placeOfOriginProperty() {
        if (placeOfOrigin == null) {
            placeOfOrigin = new SimpleStringProperty(this, "placeOfOrigin");
        }
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOriginProperty().set(placeOfOrigin);
    }

    public String getBatch() {
        return batchProperty().get();
    }

    public StringProperty batchProperty() {
        if (batch == null) {
            batch = new SimpleStringProperty(this, "batch");
        }
        return batch;
    }

    public void setBatch(String batch) {
        this.batchProperty().set(batch);
    }

    @Override
    public String toString() {
        return ingredientName + " " + botanicalName + " " + density + " " + pricePerGram +
                " " + supplier + " " + placeOfOrigin + " " + batch;
    }
}
