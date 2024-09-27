package application;

import Components.BrowserBody;
import Components.TabBar;
import Components.Toolbar;
import TabManagement.Tab;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Browser object. Contains TabBar, Toolbar, and BrowserBody, the main build of the browser.
 * See @TabBar, @Toolbar, @BrowserBody
 * Builds the scene.
 */

public class BrowserScene {
    private TabBar tabBar;
    private Toolbar toolbar;
    private BrowserBody body;
    private int num = 0;
    private Scene scene;
    private Main main;
    private Boolean incognito;

    public BrowserScene(Main main, Boolean incognito) {
    	this.main = main;
    	this.incognito = incognito;
        initializeComponents();
    }

    /**
     * Initialise all components, passing singletons throughout program.
     * Creates first tab.
     */
    private void initializeComponents() {
        body = new BrowserBody(this, incognito);
        tabBar = new TabBar(body);
        toolbar = new Toolbar(body, tabBar);

        body.getTabbar(tabBar);
        body.getToolbar(toolbar);
        
        Tab firstTab = new Tab(tabBar, body);
    }

    /**
     * Creates the scene and applies initial stylesheets for colour and padding.
     * @return Scene 
     */
    public Scene createScene() {
        VBox root = new VBox();
        root.getChildren().addAll(tabBar, toolbar, body);

        scene = new Scene(root, 900, 600);
        if (incognito) {
        	scene.getStylesheets().add(getClass().getResource("/application/assets/incog-styles.css").toExternalForm());
        }else {
        	scene.getStylesheets().add(getClass().getResource("/application/assets/def-styles.css").toExternalForm());
        }
        return scene;
    }

    public BrowserBody getBody() {
        return body;
    }

    public TabBar getTabBar() {
        return tabBar;
    }
    
    public Scene getScene() {
    	return scene;
    }
    
    public Main getMain() {
    	return main;
    }
    
    /**
     * Apply different stylesheets to change theme colour
     */
    public void toggleTheme() {
        num++;

        switch (num % 4) {
            case 0:
                body.getScene().getStylesheets().clear();
                body.getScene().getStylesheets().add(getClass().getResource("/application/assets/def-styles.css").toExternalForm());
                break;
            case 1:
                body.getScene().getStylesheets().clear();
                body.getScene().getStylesheets().add(getClass().getResource("/application/assets/pink-styles.css").toExternalForm());
                break;
            case 2:
                body.getScene().getStylesheets().clear();
                body.getScene().getStylesheets().add(getClass().getResource("/application/assets/purple-styles.css").toExternalForm());
                break;
            case 3:
                body.getScene().getStylesheets().clear();
                body.getScene().getStylesheets().add(getClass().getResource("/application/assets/blue-styles.css").toExternalForm());
                break;
            default:
                break;
        }
    }
    
    
}
