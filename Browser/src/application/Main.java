package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class. Entry point into application. Instantiates browsers (Boolean incognito).
 * See @BrowserScene
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        createBrowserWindow(primaryStage, this, false);
    }

    private void createBrowserWindow(Stage stage, Main main, Boolean incognito) {
        BrowserScene browserScene = new BrowserScene(main, incognito);
        Scene scene = browserScene.createScene();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("./assets/bowser.png")));
        stage.setTitle("Bowser ");
        stage.sizeToScene();
        stage.show();
    }

    public void createNewBrowserWindow() {
        Stage newStage = new Stage();
        createBrowserWindow(newStage, this, false);
    }

    public void createIncognitoBrowser() {
        Stage newStage = new Stage();
        createBrowserWindow(newStage, this, true);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
