package MenuItems;

import Components.BrowserBody;
import Components.TabBar;
import TabManagement.Tab;
import application.BrowserScene;
import application.Main;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NewWindow extends ListItem {
	
	BrowserScene scene;
    
    public NewWindow(TabBar tabBar, BrowserBody body, String string, BrowserScene scene) {
    	super(tabBar,body,string);
    	this.scene = scene;
    }

    @Override
    protected void setupEventListeners() {
    	this.setOnMouseClicked(e -> {
    		Main main = scene.getMain();
    		main.createNewBrowserWindow();
        });
    }
}