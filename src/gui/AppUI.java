package gui;

import core.App;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppUI extends Application {
    private final GridPane paneRoot;
    private final StackPane paneSexo;

    private final Label labelTitulo;
    private final Label labelNome;
    private final Label labelSexo;

    private final TextField fieldNome;

    {
        labelTitulo = configurarLabelTitulo();
        labelNome = configurarLabelNome();
        labelSexo = configurarLabelSexo();
        fieldNome = configurarFieldNome();
        paneSexo = configurarPaneSexo();
        paneRoot = configurarPaneRoot();
        start(configurarStage());
    }

    @Override
    public void start(Stage stage) {
    }

    private TextField configurarFieldNome() {
        final TextField field = new TextField();
        field.getStyleClass().add("field-nome");
        field.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getText().equals("")) return change;
            else if ("0123456789,.;/<>:?~^]}[{´`ªº=+-_)(*&$#@!\"\'\\|§".contains(change.getText())) return null;
            else return change;
        }));
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            final String sexo = new App().detectar(newValue);
            labelSexo.setText(sexo);

            final String id = sexo != null ? sexo.toLowerCase() : null;
            paneRoot.setId(id);
            labelTitulo.setId(id);
            labelNome.setId(id);
            fieldNome.setId(id);
        });
        return field;
    }

    private Label configurarLabelNome() {
        final Label label = new Label("Nome:");
        label.getStyleClass().add("label-nome");
        return label;
    }

    private Label configurarLabelSexo() {
        final Label label = new Label();
        label.getStyleClass().add("label-sexo");
        return label;
    }

    private Label configurarLabelTitulo() {
        final Label label = new Label("Detector de Sexo");
        label.getStyleClass().add("label-titulo");
        return label;
    }

    private GridPane configurarPaneRoot() {
        final GridPane pane = new GridPane();
        pane.getStyleClass().add("pane-root");
        pane.add(labelTitulo, 0, 0, 2, 1);
        pane.add(labelNome, 0, 1, 1, 1);
        pane.add(fieldNome, 1, 1, 1, 1);
        pane.add(paneSexo, 0, 2, 2, 1);
        return pane;
    }

    private StackPane configurarPaneSexo() {
        final StackPane pane = new StackPane(labelSexo);
        pane.getStyleClass().add("pane-sexo");
        return pane;
    }

    private Scene configurarScene() {
        final Scene scene = new Scene(paneRoot);
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/gui/css/AppUI.css");
        return scene;
    }

    private Stage configurarStage() {
        final Stage stage = new Stage();
        stage.setTitle(labelTitulo.getText());
        stage.setScene(configurarScene());
        stage.setMinWidth(1088);
        stage.setMinHeight(538);
        stage.setMaximized(true);
        stage.show();
        return stage;
    }
}