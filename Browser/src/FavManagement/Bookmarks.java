package FavManagement;

import java.util.ArrayList;

import Components.BrowserBody;
import Components.TabBar;
import Components.Toolbar;
import HistoryManagement.HistoryItem;
import MenuItems.ListItem;
import TabManagement.Tab;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * SideMenu button that visually displays bookmarked items in a table, and allows users to delete bookmarks
 * Bookmarks deleted within this TableView will also disappear from the BookmarkBar
 * See @BookmarkBar
 */
public class Bookmarks extends ListItem {

	private ArrayList<BookmarkItem> favList;
	private Stage source;
	private Toolbar toolBar;

	public Bookmarks(TabBar tabBar, BrowserBody body, String string, Toolbar toolBar) {
		super(tabBar, body, string);
		this.toolBar = toolBar;
	}

	@Override
	protected void setupEventListeners() {
		this.setOnMouseClicked(e -> {
			favList = body.getFavList();
			createSourcePopup();
		});
	}

	/**
	 * Creates TableView with column headers title and link. Allows users to doubleclick
	 * to open link, and to delete individual links from bookmarks list.
	 */
	private void createSourcePopup() {
		source = new Stage();
		source.setTitle("Bookmarks");
		source.getIcons().add(new Image(getClass().getResourceAsStream("../application/assets/bookmark.png")));

		VBox popupContent = new VBox();

		TableView<BookmarkItem> tableView = new TableView<>();
		tableView.setEditable(false);
		tableView.setPrefSize(600, 400);

		TableColumn<BookmarkItem, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));

		TableColumn<BookmarkItem, String> linkColumn = new TableColumn<>("Link");
		linkColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLink()));

		tableView.getColumns().add(titleColumn);
		tableView.getColumns().add(linkColumn);

		if (favList != null) {
			favList = body.getFavList();
			tableView.getItems().addAll(favList);
		}

		tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                BookmarkItem selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    String link = selectedItem.getLink();
                    toolBar.getTextField().setText(link);
                    toolBar.getTextField().submitText(); 
                }
            }
        });
		
		HBox bottomBar = new HBox();
        Button deleteButton = new Button("Delete bookmark");
        
        deleteButton.setOnMouseClicked(event -> {
            BookmarkItem selectedItem = tableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
            	body.getFavList().remove(selectedItem);
                toolBar.removeFav(selectedItem); 
                tableView.getItems().clear();
                tableView.getItems().addAll(body.getFavList());
            }
        });
        
        bottomBar.getChildren().add(deleteButton);
        bottomBar.setStyle("-fx-padding: 5;");
 
        popupContent.getChildren().addAll(tableView, bottomBar);
        
		source.setScene(new javafx.scene.Scene(popupContent));
		source.show();
	}

}