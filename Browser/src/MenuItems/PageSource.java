package MenuItems;

import Components.BrowserBody;

import Components.TabBar;
import TabManagement.Tab;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Opens a window displaying the HTML of the current WebView. Contains a button that
 * allows users to save HTML if they wish
 */
public class PageSource extends ListItem {
	
	private Stage source;

    public PageSource(TabBar tabBar, BrowserBody body, String string) {
        super(tabBar, body, string);
    }

    @Override
    protected void setupEventListeners() {
        this.setOnMouseClicked(e -> {
            WebEngine engine = body.currentWebView.getEngine();
            String pageSource = (String) engine.executeScript("document.documentElement.outerHTML");
            createSourcePopup(pageSource);
           
        });
    }
    
    private void createSourcePopup(String pageSource) {
        source = new Stage();
        source.setTitle("Source code");

        VBox popupContent = new VBox();

        TextArea textArea = new TextArea(pageSource);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setPrefSize(600, 400);

        HBox bottomBar = new HBox();
        Button downloadButton = new Button("Download source code");
        downloadButton.setOnAction(e -> saveSourceToFile(pageSource));
        bottomBar.getChildren().add(downloadButton);
        bottomBar.setStyle("-fx-padding: 5;");

        popupContent.getChildren().addAll(textArea, bottomBar);
        source.setScene(new javafx.scene.Scene(popupContent));
        source.show();
    }

    private void saveSourceToFile(String sourceCode) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Source Code");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML Files", "*.html"));
        File file = fileChooser.showSaveDialog(source);
        
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(sourceCode);
                Alert alert = new Alert(Alert.AlertType.NONE, "File saved!", ButtonType.OK);
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
