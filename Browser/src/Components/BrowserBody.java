package Components;

import java.time.Instant;
import java.util.ArrayList;
import java.util.TreeMap;

import FavManagement.BookmarkItem;
import HistoryManagement.HistoryItem;
import MenuItems.SideMenu;
import application.BrowserScene;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

/**
 * Manages main body of the program, including WebViews management and sideMenu.
 * WebViews managed in this class. See @SideMenu for side menu management.
 * Menu is toggled through @BurgerButton class, adding and removal managed in this class, see menu().
 */
public class BrowserBody extends HBox {
	
	public WebView currentWebView;
	public VBox menu;
    private TabBar tabBar;
    private Toolbar toolBar;
    private ArrayList<HistoryItem> historyList;
    private ArrayList<BookmarkItem> favList;
    private BrowserScene scene;
    private Boolean incognito;
	
	public BrowserBody(BrowserScene scene, Boolean incognito) {
		this.scene = scene;
		this.incognito = incognito;
		createBrowserBody();
	}
	
	private void createBrowserBody() {
        currentWebView = new WebView();
        historyList = new ArrayList<>();
        
        this.getStyleClass().add("BrowserBody");
        this.getChildren().add(currentWebView);
        
        HBox.setHgrow(currentWebView, Priority.ALWAYS);
        
    }
	
	/**
	 * Records browsing history as user browses WebView.
	 * @param url
	 */
	private void recordHistory(String url) {
		
		if (!incognito) {
	        WebEngine engine = currentWebView.getEngine();
	        
	        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
	            if (newState == Worker.State.SUCCEEDED) {
	                String title = (String) engine.executeScript("document.title");
	                String favicon = (String) engine.executeScript("document.querySelector('link[rel=\"icon\"]') ? document.querySelector('link[rel=\"icon\"]').href : '';");
	
	                HistoryItem hist = new HistoryItem(Instant.now(), url, favicon, title);
	                System.out.println(hist);
	                boolean found = false;
	
	                for (HistoryItem i : historyList) {
	                    if (i.getLink().equals(hist.getLink())) {
	                        i.setTime(hist.getTime());
	                        found = true;
	                        break;
	                    }
	                }
	                if (!found) {
	                    historyList.add(hist);
	                }
	            }
	        });
		}
    }

	/**
	 * Removes then replaces WebView when tabs change. If removal of WebView causes it
	 * to become null, a default index page is loaded.
	 * @param newWebView
	 */
	public void replaceCurrent(WebView newWebView) {
		removeMenu();
	    if (currentWebView != null) {
	        this.getChildren().remove(currentWebView);
	    }

	    if (newWebView == null) {
	        newWebView = new WebView();
	        String url = getClass().getResource("../application/assets/index.html").toExternalForm();
	        newWebView.getEngine().load(url);
	        toolBar.getTextField().setText("Homepagecv");
	        HBox.setHgrow(newWebView, Priority.ALWAYS);
	    }

	    newWebView.getEngine().locationProperty().addListener((obs, oldUrl, newUrl) -> {
	        System.out.println("Navigated from: " + oldUrl + " to: " + newUrl);
	        toolBar.getFavButton().updateLabel();
	        toolBar.getTextField().setText(newUrl);
	        recordHistory(newUrl);
	    });
	    
	    newWebView.getEngine().getHistory().currentIndexProperty().addListener((obs, oldIndex, newIndex) -> {
	    	toolBar.getBackButton().updateLabel();
	        toolBar.getForwardButton().updateLabel();
        });

	    currentWebView = newWebView; 
	    this.getChildren().add(currentWebView);
	}
    
	/**
	 * Menu is created.
	 */
    public void menu() {
    	menu = new SideMenu(tabBar, this, scene, toolBar, incognito);
    	this.getChildren().add(menu);
    	HBox.setHgrow(menu, Priority.NEVER);
    	
    }
	
    public VBox getMenu() {
    	return menu;
    }
    
    
    public void makeMenu() {
    	menu();
    }
    
    public void removeMenu() {
    	this.getChildren().remove(menu);
    	menu = null;
    }
    
    public void getTabbar(TabBar tabBar) {
    	this.tabBar = tabBar;
    }
    
    public void getToolbar(Toolbar toolBar) {
    	this.toolBar = toolBar;
    }
    
    public ArrayList<HistoryItem> getHistoryList() {
    	return historyList;
    }
    
    public ArrayList<BookmarkItem> getFavList() {
    	return favList;
    }
    
    public void initialiseFavList() {
    	favList = new ArrayList<>();
    }
    
    public void clearHistoryList() {
    	historyList.clear();
    }
    
    public Toolbar getToolbar() {
    	return toolBar;
    }
    
}