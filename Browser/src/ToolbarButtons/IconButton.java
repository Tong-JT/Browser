package ToolbarButtons;

import Components.BrowserBody;
import Components.TabBar;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Abstract class that represents a custom button on the toolbar. Each IconButton stores
 * a symbol and an event manager.
 * See @Toolbar
 */
public abstract class IconButton extends HBox {
    
    protected HBox button;
    protected TabBar tabBar;
    protected BrowserBody body;
    protected String symbol;
    protected Label label;

    public IconButton(TabBar tabBar, BrowserBody body, String symbol) {
        this.tabBar = tabBar;
        this.body = body;
        this.symbol = symbol;
        this.button = new HBox();
        this.label = new Label(symbol);
        createButton();
        getChildren().add(button);
    }

    protected void createButton() {
        label.setStyle("-fx-font-size: 20px;");
        button.getChildren().add(label);
        setupEventListeners();
        style();
    }
    
    protected abstract void style();

    protected abstract void setupEventListeners();
}