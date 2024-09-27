package MenuItems;

import Components.BrowserBody;
import Components.TabBar;
import application.BrowserScene;
import application.Main;

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