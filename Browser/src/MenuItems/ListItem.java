package MenuItems;

import Components.BrowserBody;
import Components.TabBar;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class ListItem extends HBox {
    
    protected HBox button;
    protected BrowserBody body;
    protected Label label;
    protected TabBar tabBar;


    public ListItem(TabBar tabBar, BrowserBody body, String string) {
    	this.tabBar = tabBar;
        this.body = body;
        this.button = new HBox();
        this.label = new Label(string);
        createButton();
        getChildren().add(button);
    }

    public void createButton() {
        label.setStyle("-fx-font-size: 12px;");
        button.getChildren().add(label);
        setupEventListeners();
        style();

    }
    
    public void style() {
    	button.getStyleClass().add("ListItem");
    	HBox.setHgrow(button, Priority.ALWAYS);
    }

    protected void setupEventListeners() {
    	
    }
}