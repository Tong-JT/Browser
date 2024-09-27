package TabManagement;

import Components.BrowserBody;
import Components.TabBar;
import ToolbarButtons.IconButton;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AddTabButton extends IconButton {
    
    public AddTabButton(TabBar tabBar, BrowserBody body, String symbol) {
        super(tabBar, body, "+");
    }

    @Override
    protected void style() {
    	this.getStyleClass().add("AddButton");
    }
    
    @Override
    protected void setupEventListeners() {
        button.setOnMouseClicked(e -> {
            new Tab(tabBar, body);
        });
    }
}