package MenuItems;

import Components.BrowserBody;
import Components.TabBar;
import TabManagement.Tab;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Saves HTML to file. Allows user to choose folder.
 */
public class SavePage extends ListItem {

    public SavePage(TabBar tabBar, BrowserBody body, String string) {
        super(tabBar, body, string);
    }

    @Override
    protected void setupEventListeners() {
        this.setOnMouseClicked(e -> {
            WebEngine engine = body.currentWebView.getEngine();
            String html = (String) engine.executeScript("document.documentElement.outerHTML");
            saveHtmlToFile(html, body.getScene().getWindow());
        });
    }

    private void saveHtmlToFile(String htmlContent, Window owner) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save HTML File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML Files", "*.html"));
        File file = fileChooser.showSaveDialog(owner);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(htmlContent);
                Alert alert = new Alert(Alert.AlertType.NONE, "File saved!", ButtonType.OK);
                alert.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
