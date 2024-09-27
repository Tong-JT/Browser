package FavManagement;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import Components.BrowserBody;
import Components.TabBar;
import Components.Toolbar;
import ToolbarButtons.IconButton;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;

public class FavButton extends IconButton {

	private boolean selected = false;
	private Toolbar toolbar;
	private ImageView favicon;

	public FavButton(TabBar tabBar, BrowserBody body, String symbol, Toolbar toolbar) {
		super(tabBar, body, "\u2606");
		this.toolbar = toolbar;
	}

	@Override
	protected void style() {
		button.getStyleClass().add("ToolBarButton");
	}

	protected void setupEventListeners() {
	    button.setOnMouseClicked(e -> {
	        WebEngine engine = body.currentWebView.getEngine();
	        String currentLink = engine.getLocation();
	        String title = (String) engine.executeScript("document.title");
	        loadFavicon(currentLink);

	        if (body.getFavList() == null) {
	            body.initialiseFavList();
	        }

	        BookmarkItem newBookmark = new BookmarkItem(currentLink, title, favicon);

	        boolean isFavorite = false;

	        for (BookmarkItem bookmark : body.getFavList()) {
	            if (bookmark.getLink().equals(currentLink)) {
	                isFavorite = true;
	                body.getFavList().remove(bookmark);
	                toolbar.removeFav(bookmark);
	                break;
	            }
	        }

	        if (!isFavorite) {
	            addFav(newBookmark);
	        }

	        updateLabel();
	    });
	}


	public void updateLabel() {
		WebEngine engine = body.currentWebView.getEngine();
		String currentLink = engine.getLocation();

		if (body.getFavList() != null) {
		    boolean found = false;
		    for (BookmarkItem b : body.getFavList()) {
		        if (b.getLink().equals(currentLink)) {
		            found = true;
		            break;
		        }
		    }
		    symbol = found ? "\u2605" : "\u2606";
		}
		Label label = (Label) button.getChildren().get(0);
		label.setText(symbol);
	}
	
	private void addFav(BookmarkItem newItem) {
		body.getFavList().add(newItem);
		toolbar.addFav(newItem);
	}

	private void loadFavicon(String url) {
	    try {
	        String faviconUrl = String.format("https://www.google.com/s2/favicons?domain_url=%s", URLEncoder.encode(url, "UTF-8"));
	        Image faviconImage = new Image(faviconUrl, true);
	        favicon = new ImageView();
	        favicon.setImage(faviconImage);
	    } catch (UnsupportedEncodingException ex) {
	        favicon.setImage(null);
	    }
	}
}