package Controller.JavaFxWindows;

import DAO.ExcelConection.Excel;
import Service.ExcelService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

public enum SceneClass {

    INSTANCE;

    private final ExcelService excelService = new ExcelService();

    private Stage stage;
    private Scene scene;

    private Node hBoxForRegister;
    private Node mixtureWindow;
    private Node hBoxForList;
    private VBox finalFormulaBox;
    private Node hBoxForFile;

    private VBox leftBox;
    private VBox centerBox;
    private StackPane stackPane;
    private BorderPane borderPane;

    public void setScene(Stage stage) {
        this.stage = stage;
        boolean fileExists = excelService.fileExists();
        if (fileExists) {
            String filePath = excelService.getFilePath();
            boolean foundExcelFile = excelService.checkExcelConnection(filePath);
            if (foundExcelFile) {
                Excel.setPath(filePath);
                setFullScene();
            } else {
                setSmallScene();
            }
        } else {
            excelService.createNewFile();
            setSmallScene();
        }
    }

    private void setSmallScene() {
        StarterWindow starterW = StarterWindow.getINSTANCE();
        HBox starterHBox = starterW.getHBox();
        scene = new Scene(starterHBox);
        stage.setTitle("Lab Manager");
        stage.setScene(scene);
        stage.show();

    }

    public void setFullScene() {

        if (stage.isShowing()) {
            stage.close();
        }

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        double width = screen.getWidth();
        double height = screen.getHeight();
        stage.setWidth(width);
        stage.setHeight(height);

        createMainNodes();
        createLeftBox();
        createCenterBox();
        createBorderPane();
        scene = new Scene(borderPane);
        stage.setTitle("Lab Manager");
        stage.setScene(scene);
        stage.show();
    }

    private void createCenterBox() {
        stackPane = getStackPane();
        centerBox = new VBox(stackPane, hBoxForFile);
    }

    private void createLeftBox() {
        leftBox = new VBox(hBoxForRegister, mixtureWindow);
    }

    private void createMainNodes() {
        hBoxForRegister = Register.WINDOW.getLayout();
        mixtureWindow = MixtureWindow.LAYOUT.getLayout();
        hBoxForList = ListWindow.WINDOW.getLayout();
        finalFormulaBox = FinalMixtureWindow.INSTANCE.getVBox();
        hBoxForFile = FileChooserW.INST.getLayout();
    }

    private void createBorderPane() {
        borderPane = new BorderPane();
        borderPane.setLeft(leftBox);
        borderPane.setCenter(centerBox);
        borderPane.setBackground(new Background(new BackgroundFill(
                Color.DARKSEAGREEN,
                CornerRadii.EMPTY,
                new Insets(0)
        )));
    }

    private StackPane getStackPane() {
        List<Node> list = List.of(finalFormulaBox, hBoxForList );
        ObservableList<Node> observableList = FXCollections.observableList(list);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(observableList);

        stackPane.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(1),
                new Insets(0, 10, 0, 10))));
        return stackPane;
    }


    public void setFinalMixtureWindowToFront() {
        finalFormulaBox.toFront();
    }
    public void setListToFront() {
        hBoxForList.toFront();
    }

}
