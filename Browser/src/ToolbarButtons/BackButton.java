package ToolbarButtons;

import Components.BrowserBody;
import Components.TabBar;
import javafx.scene.web.WebEngine;

/**
 * Allows the user to go backwards in the history. Is disabled if no history exists
 * ahead. State detection is managed in BrowserBody.
 * See @BrowserBody
 * 
 */
public class BackButton extends IconButton {
    
    int currentIndex;
    int historySize;

    public BackButton(TabBar tabBar, BrowserBody body, String symbol) {
        super(tabBar, body, "\u2BC7");
        updateLabel();
    }

    @Override
    protected void style() {
        button.getStyleClass().add("ToolBarButton");
    }

    @Override
    protected void setupEventListeners() {
    	button.setOnMouseClicked(e -> {
            try {
            	body.currentWebView.getEngine().getHistory().go(-1);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("No more history.");
            }
        });
    }
    
    /**
     * Updates whether the button is disabled or not. If the index number is equal to 0,
     *  the button is disabled, as there are no history items to 
     * view. 
     * See @BrowserBody
     */
    public void updateLabel() {
    	currentIndex = body.currentWebView.getEngine().getHistory().getCurrentIndex();
    	button.setDisable(currentIndex == 0);
    }

}
