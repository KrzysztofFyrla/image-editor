package pl.image.editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Krzysztof
 * @project ImageEditor
 */
public class Main extends Application {

    public static final String BORDER_PANE_MAIN_FXML = "/fxml/WindowEditor.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource(BORDER_PANE_MAIN_FXML));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setTitle("Image Editor by Krzysztof Fyrla");
        primaryStage.getIcons().add(new Image("/image/Logo.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
