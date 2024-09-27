package Components;

import FavManagement.BookmarkBar;
import FavManagement.BookmarkItem;
import FavManagement.FavButton;
import ToolbarButtons.BackButton;
import ToolbarButtons.BurgerButton;
import ToolbarButtons.ForwardButton;
import ToolbarButtons.RefreshButton;
import ToolbarButtons.TextBar;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Builds the toolbar, containing navigation buttons, text bar and bookmarks bar
 * Bookmarks bar becomes visible when bookmarks are added.
 * See @BookmarkBar, @TextBar
 */
public class Toolbar extends VBox {
	
	private HBox toolbarRoot;
	private TabBar tabBar;
	private BrowserBody body;
	private FavButton fav;
	private TextBar textField;
	private BookmarkBar favBar;
	private BackButton back;
	private ForwardButton forward;
	
	public Toolbar(BrowserBody body, TabBar tabBar) {
		this.tabBar = tabBar;
		this.body = body;
		createToolbar();
	}
	
	private void createToolbar() {
		toolbarRoot = new HBox();
		back = new BackButton(tabBar, body, "");
		forward = new ForwardButton(tabBar, body, "");
		HBox refresh = new RefreshButton(tabBar, body, "");
		fav = new FavButton(tabBar, body, "", this);
		HBox burger = new BurgerButton(tabBar, body, "");
		textField = new TextBar(tabBar, body);
	    
		toolbarRoot.getChildren().addAll(back, forward, refresh, textField, fav, burger);
		toolbarRoot.getStyleClass().add("Toolbar");
		toolbarRoot.setSpacing(5);
		
		this.getChildren().addAll(toolbarRoot);

		HBox.setHgrow(textField, Priority.ALWAYS);
		
		favBar = new BookmarkBar(body, tabBar);
		this.getChildren().add(favBar);
		
	}
	
	public FavButton getFavButton() {
		return fav;
	}
	
	public BackButton getBackButton() {
		return back;
	}
	
	public ForwardButton getForwardButton() {
		return forward;
	}
	
	public TextBar getTextField() {
		return textField;
	}
	
	public void addFav(BookmarkItem newItem) {
		favBar.addBookmark(newItem);
	}
	
	public void removeFav(BookmarkItem newItem) {
		favBar.removeBookmark(newItem);
	}
}