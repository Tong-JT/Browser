package MenuItems;

import Components.BrowserBody;
import Components.TabBar;
import TabManagement.Tab;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;

/**
 * Allows user to adjust the font size of the WebView. Uses native WebView font size 
 * methods
 */
public class FontSize extends ListItem {
	
	private HBox minusButton;
	private HBox plusButton;
	private HBox resetButton;
	private double zoomPercent = 0.2;
	private double zoomStart = 1;
	private double currentZoom;
    
    public FontSize(TabBar tabBar, BrowserBody body, String string) {
    	super(tabBar,body,string);
    	
    	this.currentZoom = zoomStart;
    }

    @Override
    public void createButton() {
        label.setStyle("-fx-font-size: 12px;");
        minusButton = createMinusButton();
        plusButton = createPlusButton();
        resetButton = createResetButton();
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        button.getChildren().addAll(label, spacer, minusButton, plusButton, resetButton);
        button.setSpacing(5);
        style();

    }
    
    private HBox createMinusButton() {
	    HBox minusButton = new HBox(new Label("-"));
	    minusButton.getStyleClass().add("ZoomButton");

	    minusButton.setOnMouseClicked(event -> {
	    	currentZoom -= zoomPercent;
	    	applyZoom();
	    });

	    return minusButton;
	}
    
    private HBox createPlusButton() {
	    HBox plusButton = new HBox(new Label("+"));
	    plusButton.getStyleClass().add("ZoomButton");

	    plusButton.setOnMouseClicked(event -> {
	    	currentZoom += zoomPercent;
	    	applyZoom();
	    });

	    return plusButton;
	}
    
    private HBox createResetButton() {
	    HBox resetButton = new HBox(new Label("\u274F"));
	    resetButton.getStyleClass().add("ZoomButton");

	    resetButton.setOnMouseClicked(event -> {
	    	currentZoom = zoomStart;
            applyZoom();
	    });

	    return resetButton;
	}
    
    private void applyZoom() {
    	body.currentWebView.setFontScale(currentZoom);
    }
}