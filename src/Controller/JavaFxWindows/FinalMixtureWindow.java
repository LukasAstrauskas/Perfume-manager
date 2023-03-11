package Controller.JavaFxWindows;

import Model.Formula.FinalComponent;
import Service.Formula.FormulaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.converter.DoubleStringConverter;

import java.util.ArrayList;
import java.util.List;

public enum FinalMixtureWindow {

    INSTANCE;

    private final FormulaService formulaService = new FormulaService();

    //    Elements for Top Window controls
    private final HBox topBoxForControls = new HBox();
    private final Button listToFrontButton = new Button("Main List");
    private Label messageLabel =  new Label();
    //  Elements for TableView box
    private final ObservableList<FinalComponent> observableList = FXCollections.observableArrayList(new ArrayList<>());
    private TableView<FinalComponent> tableView;
    //    Elements for Mixture controls
    private final VBox mixControlsBox = new VBox();
    private final TextField solventField = new TextField();
    private final TextField amountField = new TextField();
    private TextField concentrationField;


    //    Elements for main Box
    private final VBox mainBox = new VBox();

    FinalMixtureWindow() {
        createTopWindowControls();
        createTableView();
        createMixtureControls();
        addActions();
        createMainVBox();
    }


    private void createTopWindowControls() {
        topBoxForControls.setAlignment(Pos.CENTER_LEFT);
        topBoxForControls.setPadding(new Insets(5));
        topBoxForControls.setBackground(new Background(new BackgroundFill(
                Color.FORESTGREEN,
                CornerRadii.EMPTY,
                new Insets(0)
        )));
        topBoxForControls.getChildren().addAll(listToFrontButton, messageLabel);
        topBoxForControls.setSpacing(10);
        topBoxForControls.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2),
                new Insets(0))
        ));

    }

    private void createTableView() {
        tableView = new TableView<>(observableList);
        tableView.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2),
                new Insets(0))
        ));
        tableView.setPrefHeight(1000);


        TableColumn<FinalComponent, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(220);

        TableColumn<FinalComponent, Double> densityCol = new TableColumn<>("Density, kg/L");
        densityCol.setCellValueFactory(new PropertyValueFactory<>("density"));

        TableColumn<FinalComponent, Double> massCol = new TableColumn<>("Mass, g");
        massCol.setCellValueFactory(new PropertyValueFactory<>("mass"));


        TableColumn<FinalComponent, Double> volumeCol = new TableColumn<>("Volume, mL");
        volumeCol.setCellValueFactory(new PropertyValueFactory<>("volume"));


        TableColumn<FinalComponent, Double> concentrationCol = new TableColumn<>("Concentration, %");
        concentrationCol.setCellValueFactory(new PropertyValueFactory<>("concentration"));
        concentrationCol.setPrefWidth(120);
        concentrationCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        //     Edit event
//      TODO my new DoubleStringConverter()
        concentrationCol.setOnEditCommit(editEvent -> {
            Double oldValue = editEvent.getOldValue();
            System.out.println("OLd concentration: " + oldValue);
            double concentration;
            try {
                concentration = editEvent.getNewValue();
            } catch (NumberFormatException e) {
                concentration = 100;
            }
            FinalComponent component = editEvent.getRowValue();
            component.setConcentration(concentration);
            keyTypedEvent();
        });


        tableView.getColumns().addAll(nameCol, densityCol, massCol, volumeCol, concentrationCol);
        tableView.setEditable(true);
    }

    private void createMixtureControls() {
//        Create box1 add  elements
        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER_LEFT);

        Label solventName = new Label("Ethanol:");
        solventName.setPrefWidth(100);

        solventField.setText("0");
        solventField.setEditable(false);
        solventField.setPrefWidth(50);

        Label solventUnitsLabel = new Label("mL");

        hBox1.getChildren().addAll(solventName, solventField, solventUnitsLabel);

//        Create box2 add  elements
        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.CENTER_LEFT);

        Label amountLabel = new Label("Mixture Amount:");

        amountLabel.setPrefWidth(100);
        amountField.setText("1000");
        amountField.setPrefWidth(50);

        Label units = new Label("mL");

        hBox2.getChildren().addAll(amountLabel, amountField, units);

//        Create box3 add  elements
        HBox hBox3 = new HBox();
        hBox3.setAlignment(Pos.CENTER_LEFT);

        Label concentrationLabel = new Label("Concentration:");
        concentrationLabel.setPrefWidth(100);

        concentrationField = new TextField();
        concentrationField.setText("100");
        concentrationField.setPrefWidth(50);

        Label percentsLabel = new Label("%");

        hBox3.getChildren().addAll(concentrationLabel, concentrationField, percentsLabel);

//        Add boxes to control box
        mixControlsBox.getChildren().addAll(hBox1, hBox2, hBox3);
//        ToggleGroup group = new ToggleGroup();
//        RadioButton volumeButton = new RadioButton("Vol. ");
//        volumeButton.setToggleGroup(group);
//        volumeButton.setSelected(true);
//        RadioButton massButton = new RadioButton("Mass");
//        massButton.setToggleGroup(group);
//        volumeButton.setOnAction(event -> {
//            units.setText(" mL ");
//        });
//        massButton.setOnAction(event -> {
//            units.setText(" kg ");
//        });
//        Label solventLabel = new Label("%   Solvent: ");
//        ChoiceBox<String> choiceBox = new ChoiceBox<>();
//        choiceBox.getItems().addAll("Ethanol", "Ether");
//        choiceBox.setValue("Ethanol");
//        solventName.setText("Solvent: " + choiceBox.getValue() + ", amount in mixture: ");
//        choiceBox.setOnAction(event -> {
//            solventName.setText("Solvent: " + choiceBox.getValue() + ", amount in mixture: ");
//        });
    }

    private void addActions() {
        listToFrontButton.setOnAction(event -> SceneClass.INSTANCE.setListToFront());

        amountField.setOnKeyTyped(event -> {
            keyTypedEvent();
        });

        concentrationField.setOnKeyTyped(event -> {
            keyTypedEvent();
        });
    }

    private void createMainVBox() {
        mainBox.setPadding(new Insets(10, 10, 10, 10));
        mainBox.setSpacing(10);
        mainBox.getChildren().addAll(topBoxForControls, tableView, mixControlsBox);
        mainBox.setBackground(new Background(new BackgroundFill(
                Color.YELLOWGREEN,
                CornerRadii.EMPTY,
                new Insets(0)
        )));
    }

    private void keyTypedEvent() {
        try {
            int amount = Integer.parseInt(amountField.getText());
            int concentration = Integer.parseInt(concentrationField.getText());
            formulaService.showFinalMixture(amount, concentration);
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }

    public VBox getVBox() {
        return mainBox;
    }

    public void addComponentsToObsList(List<FinalComponent> componentList) {
        observableList.remove(0, observableList.size());
        observableList.setAll(componentList);
    }

    public void setSolventField(String volume) {
        solventField.setText(volume);
    }

    public void setDefaultAmountAndConcentrationValues() {
        amountField.setText("1000");
        concentrationField.setText("100");
    }

    public void setMessageText(String message) {
     messageLabel.setText(message);
    }
}
