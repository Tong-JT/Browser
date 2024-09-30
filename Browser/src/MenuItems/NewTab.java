package MenuItems;

import Components.BrowserBody;
import Components.TabBar;
import TabManagement.Tab;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * SideMenu button which allows user to add a new tab. Same function as AddButton.
 * See @SideMenu
 */
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