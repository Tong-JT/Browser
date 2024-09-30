package MenuItems;

import Components.BrowserBody;
import Components.TabBar;
import application.BrowserScene;
import application.Main;

/**
 * Button that instantiates a new BrowserScene, with the incognito Boolean marked true,
 * which alters history storage and theme selection. If incognito is true, the 
 * browser does not record browsing history and the theme cannot be altered.
 * See @BrowserScene, See @Main
 */
public class IncognitoMode extends ListItem {
	
	BrowserScene scene;
    
    public IncognitoMode(TabBar tabBar, BrowserBody body, String string, BrowserScene scene) {
    	super(tabBar,body,string);
    	this.scene = scene;
    }

    @Override
    protected void setupEventListeners() {
    	this.setOnMouseClicked(e -> {
    		Main main = scene.getMain();
    		main.createIncognitoBrowser();
        });
    }
}