package Components;

import TabManagement.AddTabButton;
import TabManagement.Tab;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * Manages tabs, users may add new tabs with add button. Tab management is shared with Tab class.
 * See @AddButton, @Tab
 * The user may click a tab, which becomes an active tab, highlighted with distinct
 * styling. 
 * WebViews managed by BrowserBody, which remove and replace WebView to active tab.
 * See @BrowserBody
 * 
 */
public class TabBar extends HBox {

    private HBox tabManager;
    private BrowserBody body;
    private Tab activeTab;

    public TabBar(BrowserBody body) {
        this.body = body;
        createTabBar();
    }

    private void createTabBar() {
        tabManager = new HBox();
        HBox addButton = new AddTabButton(this, body, "+");
        HBox tabBarRoot = new HBox(tabManager, addButton);
        this.getChildren().add(tabBarRoot);
        this.getStyleClass().add("TabBar");
        tabManager.setSpacing(5);
        tabBarRoot.setSpacing(5);
    }

    public void addTab(Tab tab) {
        tabManager.getChildren().add(tab);
        setActiveTab(tab);
    }

    public void removeTab(Tab tab) {
        if (tab.equals(activeTab)) {
            int newIndex = tabManager.getChildren().indexOf(tab) - 1;
            if (newIndex < 0 && tabManager.getChildren().size() > 1) {
                newIndex = 1;
            }
            else if (newIndex >= 0) {
                activeTab = null;
                setActiveTab((Tab) tabManager.getChildren().get(newIndex));
            }
            else {
                activeTab = null;
                setActiveTab(null);
            }
        }
        tabManager.getChildren().remove(tab);
        tab.remove();
    }


    /**
     * Active tab is the tab whose WebView is played on screen. Clicking a tab will make it active, 
     * if an active tab is deleted, the previous tab will become the active tab.
     * @param tab
     */
    public void setActiveTab(Tab tab) {
        if (tab != null && tab.getRemove()) {
            return;
        }
        if (activeTab != null) {
            activeTab.setActive(false);
        }
        activeTab = tab;
        if (activeTab != null) {
            activeTab.setActive(true);
            body.removeMenu();
            body.replaceCurrent(activeTab.getBrowser());
        } else {
            body.replaceCurrent(null);
        }
        
        if (activeTab!=null) {
	        activeTab.getBrowser().getEngine().locationProperty().addListener((obs, oldUrl, newUrl) -> {
	            body.getToolbar().getTextField().setText(newUrl);
	        });
	
	        String currentUrl = activeTab.getBrowser().getEngine().getLocation();
	        body.getToolbar().getTextField().setText(currentUrl);
        }
    }
    
    public Tab getActiveTab() {
        return activeTab;
    }

}