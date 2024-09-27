package ToolbarButtons;

import Components.BrowserBody;
import Components.TabBar;
import javafx.scene.web.WebEngine;

/**
 * Allows the user to go forward in the history. Is disabled if no history exists
 * ahead. State detection is managed in BrowserBody.
 * See @BrowserBody
 * 
 */
public class ForwardButton extends IconButton {
	
	private int currentIndex = 0;
    
    public ForwardButton(TabBar tabBar, BrowserBody body, String symbol) {
        super(tabBar, body, "\u2BC8");
        updateLabel();
    }
    
    @Override
    protected void style() {
    	this.getStyleClass().add("ToolBarButton");
    }
    
    @Override
    protected void setupEventListeners() {
    	button.setOnMouseClicked(e -> {
            try {
                body.currentWebView.getEngine().getHistory().go(1);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("No more history.");
            }
        });
    }
    
    /**
     * Updates whether the button is disabled or not. If the index number is equal to the
     * number of history items, the button is disabled, as there are no history items to 
     * view. 
     * See @BrowserBody
     */
    public void updateLabel() {
        currentIndex = body.currentWebView.getEngine().getHistory().getCurrentIndex();
        int historySize = body.currentWebView.getEngine().getHistory().getEntries().size();
        
        button.setDisable(currentIndex >= historySize - 1);
    }

}
