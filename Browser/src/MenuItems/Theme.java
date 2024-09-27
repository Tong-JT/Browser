package MenuItems;

import Components.BrowserBody;
import Components.TabBar;
import application.BrowserScene;

public class Theme extends ListItem {
	
	BrowserScene scene;
    
    public Theme (TabBar tabBar, BrowserBody body, String string, BrowserScene scene) {
    	super(tabBar,body,string);
    	this.scene = scene;
    }

    @Override
    protected void setupEventListeners() {
        this.setOnMouseClicked(e -> {
            scene.toggleTheme();
        });
    }
}