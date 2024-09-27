package FavManagement;

import Components.BrowserBody;
import Components.TabBar;
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
 * Holds the bookmarks ( @BookmarkItems ). Adds and removes bookmarks from within the class.
 * Exists within Toolbar class.
 * See @Toolbar
 * See @Bookmarks
 */
public class BookmarkBar extends HBox {
	
	private HBox bookmarkManager;
	private TabBar tabBar;
	private BrowserBody body;
	
	public BookmarkBar(BrowserBody body, TabBar tabBar) {
		this.tabBar = tabBar;
		this.body = body;
		createToolbar();
	}
	
	private void createToolbar() {
		bookmarkManager = new HBox();
		this.getChildren().add(bookmarkManager);
		bookmarkManager.setSpacing(3);
		this.getStyleClass().add("BookmarkBar");
	}
	
	public void addBookmark(BookmarkItem newItem) {
		BookmarkButton tab = new BookmarkButton(tabBar, body, newItem);
		bookmarkManager.getChildren().add(tab);
	}
	
	public void removeBookmark(BookmarkItem newItem) {
	    for (int i = 0; i < bookmarkManager.getChildren().size(); i++) {
	        BookmarkButton button = (BookmarkButton) bookmarkManager.getChildren().get(i);
	        if (button.getBookmark().equals(newItem)) {
	            bookmarkManager.getChildren().remove(i);
	            break;
	        }
	    }
	}
	
	
}