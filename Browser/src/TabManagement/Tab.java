package TabManagement;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import Components.BrowserBody;
import Components.TabBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Tab extends HBox {

	private HBox tabContainer;
	private WebView browser;
	private TabBar tabBar;
	private BrowserBody body;
	private Label tabTitle;
	private ImageView favicon;
	private boolean isActive;
	private boolean remove = false;

	public Tab(TabBar tabBar, BrowserBody body) {
		this.tabBar = tabBar;
		this.body = body;
		createTab();
		getChildren().add(tabContainer);
	}

	private void createTab() {
		
		tabContainer = new HBox();
		favicon = new ImageView();
        favicon.setFitWidth(18);
        favicon.setFitHeight(18);
		tabTitle = new Label("Loading...");
        HBox xButton = createXButton();
        xButton.setMinWidth(20);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        tabContainer.getChildren().addAll(favicon, tabTitle, spacer, xButton);
        tabContainer.getStyleClass().add("TabContainer");
        
        tabContainer.setOnMouseClicked(event -> {
            tabBar.setActiveTab(this);
            HBox.setHgrow(browser, Priority.ALWAYS);
        });
        
        tabContainer.setPrefWidth(100);
        tabContainer.setSpacing(3);
		browser = createBrowser();
		tabBar.addTab(this);
		
		this.getStyleClass().add("Tab");
	}

	private WebView createBrowser() {
		WebView newBrowser = new WebView();
		WebEngine webEngine = newBrowser.getEngine();
		webEngine.load("http://www.google.com");
		
		webEngine.titleProperty().addListener((observable, oldValue, newValue) -> {
			String newURL = webEngine.getLocation();
            tabTitle.setText(newValue); 
            loadFavicon(newURL);
        });

	    HBox.setHgrow(newBrowser, Priority.ALWAYS);
		return newBrowser;
	}
	
	private void loadFavicon(String url) {
	    try {
	        String faviconUrl = String.format("https://www.google.com/s2/favicons?domain_url=%s", URLEncoder.encode(url, "UTF-8"));
	        Image faviconImage = new Image(faviconUrl, true);
	        favicon.setImage(faviconImage);
	    } catch (UnsupportedEncodingException ex) {
	        favicon.setImage(null);
	    }
	}
	
	public void setActive(boolean active) {
        isActive = active;
        if (active) {
            tabContainer.getStyleClass().add("active-tab");
        } else {
            tabContainer.getStyleClass().remove("active-tab");
        }
    }

	private HBox createXButton() {
	    HBox xButton = new HBox(new Label("\u2715"));
	    xButton.getStyleClass().add("XButton");

	    xButton.setOnMouseClicked(event -> {
	        if (!getRemove()) {
	            tabBar.removeTab(this);
	        }
	    });

	    return xButton;
	}
	
	public WebView getBrowser() {
		return browser;
	}
	
	public void remove() {
		this.remove = true;
	}
	
	public boolean getRemove() {
		return remove;
	}

}