package Controller.JavaFxWindows;

import Model.Formula.InitialComponent;
import Model.Formula.InitialFormula;
import Model.Ingredient;
import Service.Formula.FormulaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.awt.*;
import java.util.LinkedList;

public enum MixtureWindow {
    LAYOUT;

    private VBox mixtureWindow;
    private final LinkedList<InitialComponent> ingredientsList = new LinkedList<>();
    private final ObservableList<InitialComponent> observableList = FXCollections.observableList(ingredientsList);
    private final TableView<InitialComponent> tableView = new TableView<>(observableList);

    private InitialComponent selectedItem;
    private FormulaService service = new FormulaService();

    private HBox controlPanel;


    private Button createMixtureButton;
    private Button removeIngredientButton;


    MixtureWindow() {
        createTableView();
        createControls();
        createMixtureWindow();
    }

    private void createControls() {
        createMixtureButton = new Button("Create");

        createMixtureButton.setOnAction(event -> {
//            ObservableList<InitialComponent> observableList2 = FXCollections.observableList(new LinkedList<>());
//            observableList2.add(new InitialComponent("Pine",));
            if (!observableList.isEmpty()) {
                service.setFinalFormula(observableList);
                observableList.remove(0, observableList.size());
            } else {
                service.setMessage("Add elements to mix.");
            }
        });

        removeIngredientButton = new Button("Remove");
        removeIngredientButton.setOnAction(deleteEvent -> {
            remove();
        });

        controlPanel = new HBox(createMixtureButton, removeIngredientButton);
        controlPanel.setBackground(new Background(new BackgroundFill(
                Color.OLIVE,
                CornerRadii.EMPTY,
                new Insets(0)
        )));
        controlPanel.setPadding(new Insets(5));
        controlPanel.setSpacing(10);
    }

    private void createMixtureWindow() {
        mixtureWindow = new VBox();
        mixtureWindow.setAlignment(Pos.TOP_CENTER);
        mixtureWindow.setSpacing(0);
        mixtureWindow.setPrefWidth(150);
        mixtureWindow.setPrefHeight(380);
        mixtureWindow.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2),
                new Insets(0, 10, 10, 10))));
        mixtureWindow.getChildren().addAll(tableView, controlPanel);
    }

    private void createTableView() {
        TableColumn<InitialComponent, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setMinWidth(180);

        TableColumn<InitialComponent, Double> densityCol = new TableColumn<>("Density");
        densityCol.setCellValueFactory(new PropertyValueFactory<>("density"));
        densityCol.setVisible(false);

        TableColumn<InitialComponent, Integer> dropCountCol = new TableColumn<>("Drops");
        dropCountCol.setCellValueFactory(new PropertyValueFactory<>("dropCount"));
        dropCountCol.setMaxWidth(60);
        dropCountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        dropCountCol.setOnEditCommit(event -> {
            InitialComponent component = event.getRowValue();
            int drops = event.getNewValue();
            component.setDropCount(drops);
        });

        TableColumn<InitialComponent, Double> dilutionCol = new TableColumn<>("Dilution");
        dilutionCol.setCellValueFactory(new PropertyValueFactory<>("dilution"));
        dilutionCol.setMaxWidth(60);
        dilutionCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        dilutionCol.setOnEditCommit(editEvent -> {
            double dilution = editEvent.getNewValue();
            editEvent.getRowValue().setDilution(dilution);
        });

        tableView.getColumns().setAll(nameCol, densityCol, dropCountCol, dilutionCol);
        tableView.setEditable(true);
        tableView.setPlaceholder(new Text("Add elements To Mix"));

        tableView.setOnMouseClicked(clickEvent -> {
            selectedItem = tableView.getSelectionModel().getSelectedItem();
        });

    }

    public Node getLayout() {
        return mixtureWindow;
    }

    public void addIngredientToList(Ingredient ingredient) {
        InitialComponent component = new InitialComponent(ingredient.getIngredientName(), ingredient.getDensity());
        observableList.add(component);
    }

    private void remove() {
        try {
            observableList.remove(selectedItem);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage() + " Removed: ");
        }


    }


}
