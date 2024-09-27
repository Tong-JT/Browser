package HistoryManagement;

import java.util.ArrayList;

import Components.BrowserBody;
import Components.TabBar;
import Components.Toolbar;
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

public class History extends ListItem {
	
	private ArrayList<HistoryItem> historyList;
	private Stage source;
	private Toolbar toolBar;
	
    
    public History(TabBar tabBar, BrowserBody body, String string, Toolbar toolBar) {
    	super(tabBar,body,string);
    	this.toolBar = toolBar;
    }

    @Override
    protected void setupEventListeners() {
    	this.setOnMouseClicked(e -> {
    		historyList = body.getHistoryList();
    		System.out.println(historyList);
    		createSourcePopup();
        });
    }
    
    private void createSourcePopup() {
        source = new Stage();
        source.setTitle("History");
        source.getIcons().add(new Image(getClass().getResourceAsStream("../application/assets/history.png")));

        VBox popupContent = new VBox();
        
        TableView<HistoryItem> tableView = new TableView<>();
        tableView.setEditable(false);
        tableView.setPrefSize(600, 400);

        TableColumn<HistoryItem, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormattedTime()));

        TableColumn<HistoryItem, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));

        TableColumn<HistoryItem, String> linkColumn = new TableColumn<>("Link");
        linkColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLink()));

        tableView.getColumns().add(timeColumn);
        tableView.getColumns().add(titleColumn);
        tableView.getColumns().add(linkColumn);

        tableView.getItems().clear();
        historyList.sort(new HistoryComparator());
        tableView.getItems().addAll(historyList);
        
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                HistoryItem selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    String link = selectedItem.getLink();
                    toolBar.getTextField().setText(link);
                    toolBar.getTextField().submitText(); 
                }
            }
        });

        HBox bottomBar = new HBox();
        Button clearHistory = new Button("Delete history");
        
        clearHistory.setOnMouseClicked(event -> {
            body.clearHistoryList();
            tableView.getItems().clear();
            historyList = body.getHistoryList();
            tableView.getItems().addAll(historyList);
        });
        
        bottomBar.getChildren().add(clearHistory);
        bottomBar.setStyle("-fx-padding: 5;");
        
        popupContent.getChildren().addAll(tableView, bottomBar);
        source.setScene(new javafx.scene.Scene(popupContent));
        source.show();
    }
}