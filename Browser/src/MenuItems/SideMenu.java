package MenuItems;

import Components.BrowserBody;
import Components.TabBar;
import Components.Toolbar;
import FavManagement.Bookmarks;
import HistoryManagement.History;
import application.BrowserScene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SideMenu extends VBox {
	
	VBox menu;
	TabBar tabBar;
	BrowserBody body;
	BrowserScene scene;
	Toolbar toolBar;
	Boolean incognito;
	
	public SideMenu(TabBar tabBar, BrowserBody body, BrowserScene scene, Toolbar toolBar, Boolean incognito) {
		this.tabBar = tabBar;
		this.body = body;
		this.scene = scene;
		this.toolBar = toolBar;
		this.incognito = incognito;
		makeMenu();
		addMenuItems();
	}
	
	private void makeMenu() {
		menu = new VBox();
		menu.getStyleClass().add("BurgerMenu");
        menu.prefHeightProperty().bind(this.heightProperty());
        menu.setPrefWidth(200);
        this.getChildren().add(menu);
	}
	
	private void addMenuItems() {
		ListItem newTab = new NewTab(tabBar, body, "New tab");
		ListItem newWindow = new NewWindow(tabBar, body, "New window", scene);
		ListItem incognitoMode = new IncognitoMode(tabBar, body, "Incognito mode", scene);
        ListItem bookmarks = new Bookmarks(tabBar, body, "Bookmarks", toolBar);
        ListItem history = new History(tabBar, body, "History", toolBar);
        ListItem print = new Print(tabBar, body, "Print");
        ListItem pageSource = new PageSource(tabBar, body, "View page source");
        ListItem savePage = new SavePage(tabBar, body, "Save page");
        ListItem zoom = new Zoom(tabBar, body, "Zoom");
        ListItem fontSize = new FontSize(tabBar, body, "Font");
        menu.getChildren().addAll(newTab, newWindow, incognitoMode, bookmarks, history, print, pageSource, savePage, zoom, fontSize);
        if (!incognito) {
            ListItem colours = new Theme(tabBar, body, "Change theme", scene);
            menu.getChildren().add(colours);
        }
	}
}