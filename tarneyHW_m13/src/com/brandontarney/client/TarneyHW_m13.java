package com.brandontarney.client;

import java.util.Date;

import com.brandontarney.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TarneyHW_m13 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	private static final String[] HIKES = { "HELLROARING PLATEAU", "THE BEATEN PATH", "GARDINER LAKE" };
	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final MyServiceAsync myService = GWT.create(MyService.class);


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");

		final TextBox nameField = new TextBox();
		nameField.setText("Enter Name");

		final Label hikeLabel = new Label();
		hikeLabel.setText("Hike");
		final ListBox hikeListBox = new ListBox(false);
		for (int i = 0; i < HIKES.length; i++) {
			hikeListBox.addItem(HIKES[i]);
		}

		final Label dateLabel = new Label();
		dateLabel.setText("Date");
		final DateBox dateBox = new DateBox();

		final Label durationText = new Label();
		durationText.setText("Duration");
		final TextBox durationField = new TextBox();
		durationField.setText("Enter Duration (2-7)");

		final Label partySizeText = new Label();
		partySizeText.setText("Party Size");
		final TextBox partySizeField = new TextBox();
		partySizeField.setText("Enter Party Size (1-10)");

		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("nameFieldContainer").add(hikeLabel);
		RootPanel.get("nameFieldContainer").add(hikeListBox);
		RootPanel.get("nameFieldContainer").add(dateLabel);
		RootPanel.get("nameFieldContainer").add(dateBox);
		RootPanel.get("nameFieldContainer").add(durationText);
		RootPanel.get("nameFieldContainer").add(durationField);
		RootPanel.get("nameFieldContainer").add(partySizeText);
		RootPanel.get("nameFieldContainer").add(partySizeField);
		RootPanel.get("nameFieldContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();


		// Create the popup dialog box

		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Hike Cost");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		//final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		//dialogVPanel.add(new HTML("<b>Sending hike details to the server:</b>"));
		//dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);



		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler {

			public void onClick(ClickEvent event) {
				sendHikeDataToServer();
			}

			//Send data to the server and wait for a response
			
			private void sendHikeDataToServer() {
				// send the input to the server.

				errorLabel.setText("");
				sendButton.setEnabled(false);
				
				String hikeStr = hikeListBox.getSelectedItemText();

				String dateStr = dateBox.getValue().toString();
				
				String durationStr = durationField.getText();
				String partySizeStr = partySizeField.getText();

				serverResponseLabel.setText("");
				
				myService.greetServer(hikeStr, dateStr, durationStr, partySizeStr, 
						new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});

			}
		}

		
		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
	}
}
