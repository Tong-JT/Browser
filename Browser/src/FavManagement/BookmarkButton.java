package FavManagement;

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

/**
 * The visual container representing the saved BookmarkItem. The container is a 
 * label containing title text and favicon, storing a link, which is accessible when clicked
 * Is added with addBookmark() in BookmarkBar
 * See @BookmarkBar
 */
public class BookmarkButton extends HBox {

	private TabBar tabBar;
	private BrowserBody body;
	private Label tabTitle;
	private BookmarkItem bookmark;

	public BookmarkButton(TabBar tabBar, BrowserBody body, BookmarkItem bookmark) {
		this.tabBar = tabBar;
		this.body = body;
		this.bookmark = bookmark;
		createTab();
	}

	private void createTab() {
		
		tabTitle = new Label(bookmark.getTitle());

        this.getChildren().addAll(bookmark.getFavicon(), tabTitle);
        this.getStyleClass().add("BookmarkButton");
        this.setSpacing(4);
        
        this.setOnMouseClicked(event -> {
        	body.currentWebView.getEngine().load(bookmark.getLink());
        });
        
        this.setMaxWidth(100);
	}

	public BookmarkItem getBookmark() {
		return bookmark;
	}
}