package ToolbarButtons;

import Components.BrowserBody;
import Components.TabBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TextBar extends HBox {

	private HBox textFieldContainer;
	private TextField textField;
	private TabBar tabBar;
	private BrowserBody body;
	private HBox launch;
	private String fieldText;

	public TextBar(TabBar tabBar, BrowserBody body) {
		this.tabBar = tabBar;
		this.body = body;
		this.textFieldContainer = new HBox();
		this.textField = new TextField();
		textField.getStyleClass().add("TextField");
		textFieldContainer.getStyleClass().add("TextFieldContainer");
		launch = new LaunchButton(tabBar, body, "", this);
		textFieldContainer.getChildren().addAll(textField, launch);
		textFieldContainer.setSpacing(5);
		getChildren().add(textFieldContainer);
		HBox.setHgrow(textField, Priority.ALWAYS);
		HBox.setHgrow(textFieldContainer, Priority.ALWAYS);
		createTextField();
	}

	private void createTextField() {
		setupEventListeners();
	}

	protected void setupEventListeners() {
		textField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				submitText();
			}
		});
	}
	
	public void submitText() {
		fieldText = textField.getText();
		if (!fieldText.isEmpty()) {
			if (fieldText.contains(".co.") || fieldText.contains(".com")) {
				if (!fieldText.startsWith("http://") && !fieldText.startsWith("https://")) {
					fieldText = "http://" + fieldText;
				}
			}
			else {
				fieldText = "https://www.google.com/search?q=" + fieldText;
			}
			body.currentWebView.getEngine().load(fieldText);
		}
	}
	
	public void setText(String string) {
		textField.setText(string);
	}

}