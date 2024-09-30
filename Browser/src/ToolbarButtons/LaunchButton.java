package ToolbarButtons;

import Components.BrowserBody;
import Components.TabBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Submits the text within the textfield
 * See @Textbar
 */
public class LaunchButton extends IconButton {

	TextBar textfield;

	public LaunchButton(TabBar tabBar, BrowserBody body, String symbol, TextBar textfield) {
		super(tabBar, body, "\u27A6");
		this.textfield = textfield;
	}

	@Override
	protected void style() {
		button.getStyleClass().add("ToolBarButton");
	}

	@Override
	protected void setupEventListeners() {
		button.setOnMouseClicked(e -> {
			textfield.submitText();
		});
	}
	
	
}