package MenuItems;

import Components.BrowserBody;
import Components.TabBar;
import TabManagement.Tab;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class NewTab extends ListItem {
    
    public NewTab(TabBar tabBar, BrowserBody body, String string) {
    	super(tabBar,body,string);
    }

    @Override
    protected void setupEventListeners() {
    	this.setOnMouseClicked(e -> {
    		new Tab(tabBar, body);
    		body.removeMenu();
        });
    }
}