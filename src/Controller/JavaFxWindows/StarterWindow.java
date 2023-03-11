package Controller.JavaFxWindows;

import DAO.ExcelConection.Excel;
import Service.ExcelService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class StarterWindow {

    private static StarterWindow INSTANCE;

    private StarterWindow() {
    }

    private static final HBox hBox = new HBox();
    private static final ExcelService service = new ExcelService();
    private static final Button confirmButton = new Button("Add Path");
    private static final Label messageLabel = new Label();
    private static final TextField fileLocationField = new TextField();


    public static StarterWindow getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new StarterWindow();
            createHBox();
        }
        return INSTANCE;
    }

    private static void createHBox() {
        createVisuals();
        addActions();
        hBox.getChildren().addAll(fileLocationField, confirmButton, messageLabel);
    }

    private static void createVisuals() {
        hBox.setBackground(new Background(new BackgroundFill(
                Color.OLIVEDRAB,
                CornerRadii.EMPTY,
                new Insets(0, 10, 0, 10)
        )));
        hBox.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(1),
                new Insets(0, 10, 0, 10)
        )));
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(20);

        messageLabel.setTextFill(Color.BLACK);
//        messageLabel.setText(Excel.getConnectionMessage());
        fileLocationField.setPromptText("Add absolute File path.");
        fileLocationField.setMinWidth(400);
    }

    private static void addActions() {
        confirmButton.setOnAction(event -> {
            String path = fileLocationField.getText();
            boolean connected = service.checkExcelConnection(path);
            if (connected) {
                service.setFilePath(path);
                SceneClass.INSTANCE.setFullScene();

            } else {
                setMessage("Excel file doesn't found.");
            }
        });
    }

    public static void setMessage(String message) {
        messageLabel.setText(message);
    }

    public HBox getHBox() {
        return hBox;
    }
}
