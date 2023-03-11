package Controller.JavaFxWindows;

import DAO.ExcelConection.Excel;
import Service.ExcelService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;



public enum FileChooserW {
    INST;
    private final HBox hBox = new HBox();
    private final ExcelService service = new ExcelService();
    private final Button confirmButton = new Button("Add Path");
    private final Label messageLabel = new Label();
    private final TextField fileLocationField = new TextField();

    FileChooserW() {
        createVisuals();
        addActions();
        hBox.getChildren().addAll(fileLocationField, confirmButton, messageLabel);
    }

    private void addActions() {
        confirmButton.setOnAction(event -> {
            String path = fileLocationField.getText();
            boolean connected = service.checkExcelConnection(path);
            if (connected) {
                service.setFilePath(path);
            } else {
                setMessage("Excel file doesn't found.");
                addFileLocation();
            }
        });
    }

    private void createVisuals() {
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
        messageLabel.setText(Excel.getConnectionMessage());
        fileLocationField.setPromptText("Add absolute File path.");
        fileLocationField.setMinWidth(400);
        addFileLocation();
    }


    public void setMessage(String message) {
        this.messageLabel.setText(message);
    }

    public void addFileLocation() {
//        if (Optional.ofNullable(Excel.getWorkbook()).isPresent()) {
        fileLocationField.setText(Excel.getPath());
//        }
    }


    public Node getLayout() {
        return hBox;
    }
}
