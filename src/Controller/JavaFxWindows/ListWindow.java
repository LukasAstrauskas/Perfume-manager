package Controller.JavaFxWindows;

import Model.Ingredient;
import Service.IngredientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Optional;


public enum ListWindow {

    WINDOW;

    private int selectedIndex = -1;
    private final TextField searchField = new TextField();
    private final Button showAll = new Button("Show All");
    private final Button editButton = new Button("Edit");
    private final Button deleteButton = new Button("Delete");
    private final Button toMixButton = new Button("Add To Mix");
    private final Button formulaWindowButton = new Button("Formula");
    private final VBox vBox = new VBox();
    private final HBox hBoxForControls = new HBox();
    private final Label messageLabel = new Label();
    private final ObservableList<Ingredient> observableList = FXCollections.observableList(getIngredientsList());
    private final TableView<Ingredient> tableView = new TableView<>(observableList);


    ListWindow() {
        createControls();
        createTableView();
        createVBox();
        addActions();
    }

    private void addActions() {
        showAll.setOnAction(event -> {
            observableList.setAll(getIngredientsList());
            searchField.clear();
        });

        deleteButton.setOnAction(event -> {
            Optional<IngredientService> optional = Optional.of(new IngredientService());
            optional.ifPresentOrElse((service) -> {
                        try {
                            observableList.remove(selectedIndex);
                            service.deleteMaterial(selectedIndex + 1);
                            selectedIndex = -1;
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            messageLabel.setText("Nothing to delete.");
                        }
                    },
                    this::noConnectionMessage);

        });

        editButton.setOnAction(event -> {
            try {
                Ingredient ingredient = observableList.get(selectedIndex);
                Register.WINDOW.setToEditFillFields(ingredient, selectedIndex);
                messageLabel.setText("Elem: " + ingredient.getIngredientName() + " selected.");
                selectedIndex = -1;
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                messageLabel.setText("Select ingredient..");
            }
        });

        toMixButton.setOnAction(event -> {
            try {
                Ingredient item = observableList.get(selectedIndex);
                if (item.getDensity() == 0) {
                    messageLabel.setText("Set element's density.");
                } else {
                    MixtureWindow.LAYOUT.addIngredientToList(item);
                }
                selectedIndex = -1;
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                messageLabel.setText("No element selected.");
            }
        });

        formulaWindowButton.setOnAction(event -> SceneClass.INSTANCE.setFinalMixtureWindowToFront());

        tableView.setOnMouseClicked(event -> {
            selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println(selectedIndex + " " + observableList.get(selectedIndex).getIngredientName());
        });


        searchField.setOnKeyTyped(event -> {
            Optional<IngredientService> optional = Optional.of(new IngredientService());
            optional.ifPresentOrElse((service) -> {
                        String text = searchField.getText();
                        LinkedList<Ingredient> selectedList = service.getSelectedList(text);
                        observableList.setAll(selectedList);
                        if (selectedList.isEmpty()) {
                            messageLabel.setText("No such elements exits.");
                        }
                    },
                    this::noConnectionMessage);

        });
    }

    private void noConnectionMessage() {
        messageLabel.setText("No Excel connection.");
    }

    private void createVBox() {
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(hBoxForControls, tableView);
        vBox.setBackground(new Background(new BackgroundFill(
                Color.YELLOWGREEN,
                CornerRadii.EMPTY,
                new Insets(0)
        )));
    }

    private void createControls() {
        messageLabel.setTextFill(Color.BLACK);
        searchField.setPromptText("Search...");
        searchField.setPrefWidth(250);

        hBoxForControls.setAlignment(Pos.CENTER_LEFT);
        hBoxForControls.setPadding(new Insets(5));
        hBoxForControls.setBackground(new Background(new BackgroundFill(
                Color.FORESTGREEN,
                CornerRadii.EMPTY,
                new Insets(0)
        )));
        hBoxForControls.getChildren().addAll(searchField, showAll, editButton,
                deleteButton, toMixButton, formulaWindowButton, messageLabel);
        hBoxForControls.setSpacing(10);
        hBoxForControls.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2),
                new Insets(0))
        ));
    }

    private void createTableView() {
        tableView.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2),
                new Insets(0))
        ));
        tableView.setPrefHeight(1000);


        TableColumn<Ingredient, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));
        nameCol.setPrefWidth(220);
        TableColumn<Ingredient, String> botanicalNameCol = new TableColumn<>("Botanical Name");
        botanicalNameCol.setCellValueFactory(new PropertyValueFactory<>("botanicalName"));
        botanicalNameCol.setPrefWidth(220);
        TableColumn<Ingredient, Double> densityCol = new TableColumn<>("Density");
        densityCol.setCellValueFactory(new PropertyValueFactory<>("density"));
        TableColumn<Ingredient, Double> pricePerGramCol = new TableColumn<>("Price");
        pricePerGramCol.setCellValueFactory(new PropertyValueFactory<>("pricePerGram"));
        TableColumn<Ingredient, String> supplierCol = new TableColumn<>("Supplier");
        supplierCol.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        TableColumn<Ingredient, String> placeOfOriginCol = new TableColumn<>("Origin");
        placeOfOriginCol.setCellValueFactory(new PropertyValueFactory<>("placeOfOrigin"));
        TableColumn<Ingredient, String> batchCol = new TableColumn<>("Batch");
        batchCol.setCellValueFactory(new PropertyValueFactory<>("batch"));


        tableView.getColumns().addAll(nameCol, botanicalNameCol, densityCol, pricePerGramCol,
                supplierCol, placeOfOriginCol, batchCol);
    }

    private LinkedList<Ingredient> getIngredientsList() {
        Optional<IngredientService> optional = Optional.ofNullable(new IngredientService());
        IngredientService service;
        try {
            service = new IngredientService();
            LinkedList<Ingredient> ingredients = service.gelAllMaterials();
            if (ingredients.isEmpty()) {
                messageLabel.setText("List Empty. Check file path.");
            } else {
                messageLabel.setText("");
            }
            return ingredients;
        } catch (NullPointerException e) {
            return new LinkedList<>();
        }


    }

    public void editIngredient(Ingredient edited, Ingredient old) {
        int index = observableList.indexOf(old);
        observableList.set(index, edited);
    }

    public void addIngredient(int place, Ingredient ingredient) {
        observableList.add(place, ingredient);
    }

    public Node getLayout() {
        return vBox;
    }
}
