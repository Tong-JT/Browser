package ToolbarButtons;

import Components.BrowserBody;
import Components.TabBar;

/**
 * Refreshes WebView.
 * See @Toolbar
 */

public class RefreshButton extends IconButton {
    
    public RefreshButton(TabBar tabBar, BrowserBody body, String symbol) {
        super(tabBar, body, "\u2B6F");
    }
    
    @Override
    protected void style() {
    	button.getStyleClass().add("ToolBarButton");
    }
    

    @Override
    protected void setupEventListeners() {
        button.setOnMouseClicked(e -> {
        	body.currentWebView.getEngine().reload();
        });
    }
}