package MenuItems;

import Components.BrowserBody;
import Components.TabBar;
import TabManagement.Tab;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;

public class Print extends ListItem {
    
    public Print(TabBar tabBar, BrowserBody body, String string) {
    	super(tabBar,body,string);
    }

    @Override
    protected void setupEventListeners() {
        this.setOnMouseClicked(e -> {
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null) {
                boolean proceed = job.showPrintDialog(body.getScene().getWindow());
                if (proceed) {
                    WebEngine engine = body.currentWebView.getEngine();
                    engine.print(job);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Printing initiated!", ButtonType.OK);
                    alert.showAndWait();
                    job.endJob();
                }
            }
        });
    }
}