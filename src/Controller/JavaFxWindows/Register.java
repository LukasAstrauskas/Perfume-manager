package Controller.JavaFxWindows;

import Controller.NumberHelper.NumberParseHelper;
import Model.Ingredient;
import Service.IngredientService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Optional;

public enum Register {
    WINDOW;

    //TODO
    // crete service on button press.
    private IngredientService service;

    private final NumberParseHelper helper = new NumberParseHelper();
    private final GridPane grid = new GridPane();

    //            Labels for textFields
    private final Label ingredientNameLabel = new Label("Ingredient name");
    private final Label botanicalNameLabel = new Label("Botanical name");
    private final Label densityLabel = new Label("Density");
    private final Label pricePerGramLabel = new Label("Price per Gram");
    private final Label supplierLabel = new Label("Supplier");
    private final Label placeOfOriginLabel = new Label("Origin");
    private final Label batchLabel = new Label("Batch");

    //           Creating Text and Number Fields
    private final TextField ingredientNameField = new TextField();
    private final TextField botanicalNameField = new TextField();
    private final TextField densityField = new TextField();
    private final TextField pricePerGramField = new TextField();
    private final TextField supplierField = new TextField();
    private final TextField placeOfOriginField = new TextField();
    private final TextField batchField = new TextField();

    private final List<TextField> textFields = List.of(ingredientNameField, botanicalNameField, densityField,
            pricePerGramField, supplierField, placeOfOriginField, batchField);


    private final Button addButton = new Button("Add");
    private final Button editButton = new Button("Edit");
    private final Button cancelButton = new Button("Cancel");

    private final HBox buttonSwitcher = new HBox();
    private int elementIndexInList;
    private Ingredient oldIngredient;

    Register() {
        setTextFill();
        createGridLayout();
        addActions();
        createControls();
    }


    private void setTextFill() {
        Color black = Color.BLACK;
        ingredientNameLabel.setTextFill(black);
        botanicalNameLabel.setTextFill(black);
        densityLabel.setTextFill(black);
        pricePerGramLabel.setTextFill(black);
        supplierLabel.setTextFill(black);
        placeOfOriginLabel.setTextFill(black);
        batchLabel.setTextFill(black);
    }

    private void createControls() {
        buttonSwitcher.getChildren().add(addButton);
        double width = 60;
        addButton.setMinWidth(width);
        editButton.setMinWidth(width);
        cancelButton.setMinWidth(width);
        HBox hBox = new HBox();
        hBox.setSpacing(40);
        hBox.getChildren().addAll(buttonSwitcher, cancelButton);
        grid.add(hBox, 1, 7);
    }

    private void createGridLayout() {
        grid.setBackground(new Background(new BackgroundFill(
                Color.SEAGREEN,
                CornerRadii.EMPTY,
                new Insets(10)
        )));
        grid.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(3),
                new Insets(10)
        )));

        grid.setPadding(new Insets(10, 20, 10, 20));

        grid.setAlignment(Pos.TOP_LEFT);
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(ingredientNameLabel, 0, 0);
        grid.add(botanicalNameLabel, 0, 1);
        grid.add(densityLabel, 0, 2);
        grid.add(pricePerGramLabel, 0, 3);
        grid.add(supplierLabel, 0, 4);
        grid.add(placeOfOriginLabel, 0, 5);
        grid.add(batchLabel, 0, 6);

        grid.add(ingredientNameField, 1, 0);
        grid.add(botanicalNameField, 1, 1);
        grid.add(densityField, 1, 2);
        grid.add(pricePerGramField, 1, 3);
        grid.add(supplierField, 1, 4);
        grid.add(placeOfOriginField, 1, 5);
        grid.add(batchField, 1, 6);
    }

    private void addActions() {
        densityField.setOnKeyTyped(event -> {
            String text = densityField.getText();
            while (inputChecker(text)) {
                int length = text.length();
                densityField.deleteText(length - 1, length);
                text = densityField.getText();
            }
        });

        pricePerGramField.setOnKeyTyped(event -> {
            String text = pricePerGramField.getText();
            while (inputChecker(text)) {
                int length = text.length();
                pricePerGramField.deleteText(length - 1, length);
                text = pricePerGramField.getText();
            }
        });


        addButton.setOnAction(event -> {
            Optional<IngredientService> optional = Optional.of(new IngredientService());
            optional.ifPresentOrElse((service) -> {
                        String name = ingredientNameField.getText();
                        if (!name.isEmpty()) {
                            Ingredient ingredient = getIngredientFromFields();
                            int place = service.addNewMaterial(ingredient);
                            ListWindow.WINDOW.addIngredient(place, ingredient);
                        } else {
                            ingredientNameField.setPromptText("Not to be empty!");
                        }
                    },
                    () -> {
                        noConnectionMessage();
                    });


        });

        editButton.setOnAction(event -> {
            Optional<IngredientService> optionalService = Optional.of(new IngredientService());
            optionalService.ifPresentOrElse((service) -> {
                        Ingredient edited = getIngredientFromFields();
                        service.editMaterial(edited, elementIndexInList + 1);
                        ListWindow.WINDOW.editIngredient(edited, oldIngredient);
                        buttonSwitcher.getChildren().remove(editButton);
                        buttonSwitcher.getChildren().add(addButton);
                    },
                    () -> {
                        noConnectionMessage();
                    });

        });

        cancelButton.setOnAction(event -> {
            for (TextField textField : textFields) {
                textField.clear();
            }
            buttonSwitcher.getChildren().removeAll(addButton, editButton);
            buttonSwitcher.getChildren().add(addButton);
        });
    }

    private void noConnectionMessage() {
        System.out.println("No connection to excel.");
        ingredientNameField.setPromptText("No connection to excel.");
    }

    private Optional<IngredientService> getIngredientService() {
        service = new IngredientService();
        return Optional.of(service);
    }

    private boolean inputChecker(String text) {
        boolean match1 = text.matches("[0-9]");
        boolean match2 = text.matches("[0-9][,.][0-9]*");
        boolean match3 = text.matches("[0-9][,.]");
        boolean match4 = text.matches("");
        boolean finalB = match1 || match2 || match3 || match4;
        return !finalB;
    }

    public Node getLayout() {
        return grid;
    }

    private Ingredient getIngredientFromFields() {
        double price = helper.parseNumber(pricePerGramField.getText());
        double density = helper.parseNumber(densityField.getText());
        Ingredient ingredient = new Ingredient(
                ingredientNameField.getText(),
                botanicalNameField.getText(),
                density,
                price,
                supplierField.getText(),
                placeOfOriginField.getText(),
                batchField.getText());
        for (TextField text : textFields) {
            text.setText("");
        }
        return ingredient;
    }

    public void setToEditFillFields(Ingredient toEdit, int index) {
        this.oldIngredient = toEdit;
        ingredientNameField.setText(toEdit.getIngredientName());
        botanicalNameField.setText(toEdit.getBotanicalName());
        densityField.setText(String.valueOf(toEdit.getDensity()));
        pricePerGramField.setText(String.valueOf(toEdit.getPricePerGram()));
        supplierField.setText(toEdit.getSupplier());
        placeOfOriginField.setText(toEdit.getPlaceOfOrigin());
        batchField.setText(toEdit.getBatch());

        this.elementIndexInList = index;
        buttonSwitcher.getChildren().removeAll(addButton, editButton);
        buttonSwitcher.getChildren().add(editButton);
    }
}
