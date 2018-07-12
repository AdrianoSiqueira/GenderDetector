package code;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppUI extends Application {
    private static final String TITLE = "Gender Detector v" + Data.VERSION;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label labelTitle = new Label("Gender Detector");
        Label labelName = new Label("Name:");
        Label labelGender = new Label();

        TextField fieldName = new TextField();

        StackPane paneGender = new StackPane();

        GridPane paneComponent = new GridPane();

        BorderPane paneBackground = new BorderPane();


        // ----- Configuration of components
        labelTitle.getStyleClass().add("label-title");
        labelGender.getStyleClass().add("label-gender");

        Data.genderProperty().addListener((observable, oldValue, newValue) -> {
            labelGender.setText(newValue);
            fieldName.setId(newValue.toLowerCase());
            paneBackground.setId(newValue.toLowerCase());

            if (newValue.equalsIgnoreCase("feminine") || newValue.equalsIgnoreCase("masculine")) {
                labelTitle.setId("dark");
                labelName.setId("dark");
            } else {
                labelTitle.setId("");
                labelName.setId("");
            }
        });

        fieldName.textProperty().addListener((observable, oldValue, newValue) -> new App().detect(newValue));
        // ----- Configuration of components


        // ----- Configuration: paneGender
        paneGender.getStyleClass().add("pane-gender");
        paneGender.getChildren().add(labelGender);
        // ----- Configuration: paneGender


        // ----- Configuration: paneComponent
        GridPane.setConstraints(labelTitle, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(labelName, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(fieldName, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(paneGender, 0, 4, 2, 1, HPos.CENTER, VPos.CENTER);

        paneComponent.getStyleClass().add("pane-component");
        paneComponent.getChildren().addAll(labelTitle, labelName, fieldName, paneGender);
        // ----- Configuration: paneComponent


        // ----- Configuration: paneBackground
        paneBackground.getStyleClass().add("pane-background");
        paneBackground.setCenter(paneComponent);
        // ----- Configuration: paneBackground


        Scene scene = new Scene(paneBackground);
        scene.getRoot().getStylesheets().clear();
        scene.getRoot().getStylesheets().addAll("/css/Label.css", "/css/Pane.css", "/css/TextField.css");

        stage.setTitle(AppUI.TITLE);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}