package ToolbarButtons;

import Components.BrowserBody;
import Components.TabBar;
import javafx.scene.layout.VBox;

/**
 * Toggles the presence of a side menu on the BrowserBody. The side menu contains 
 * additional methods for the WebView.
 * See @SideMenu, see @BrowserBody
 */
public class BurgerButton extends IconButton {
	
    public BurgerButton(TabBar tabBar, BrowserBody body, String symbol) {
        super(tabBar, body, "\u2261");
    }
    
    @Override
    protected void style() {
    	button.getStyleClass().add("ToolBarButton");
    }
    

    @Override
    protected void setupEventListeners() {
        button.setOnMouseClicked(e -> {
        	toggleMenu();
        });
    }
    
    protected void toggleMenu() {
    	VBox menu = body.getMenu();
    	if (menu==null) {
    		body.makeMenu();
    	}
    	else {
    		body.removeMenu();
    	}
    }
    

}