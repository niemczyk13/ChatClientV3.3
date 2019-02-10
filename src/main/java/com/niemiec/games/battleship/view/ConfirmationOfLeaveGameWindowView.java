package com.niemiec.games.battleship.view;

import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.controllers.ConfirmationOfLeaveGameWindowController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfirmationOfLeaveGameWindowView {
	private String opponentPlayerNick;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private ConfirmationOfLeaveGameWindowController controller;
	private FXMLLoader loader;
	private VBox vBox;
	private Stage stage;

	public ConfirmationOfLeaveGameWindowView(String opponentPlayerNick, DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.opponentPlayerNick = opponentPlayerNick;
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}

	public void show() {
		Platform.runLater(() -> {
			loadFXMLLoader();
			updateConfirmationOfLeaveGameWindowController();
			view();
			addCloseOption();
		});
	}
	
	private void addCloseOption() {
		stage.setOnCloseRequest(e -> controller.cancel());
	}
	
	private void view() {
		Scene scene = new Scene(vBox);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	private void updateConfirmationOfLeaveGameWindowController() {
		controller.setOpponentPlayerNick(opponentPlayerNick);
		controller.setDispatcherOfOutgoingRequest(dispatcherOfOutgoingRequest);
	}
	
	private void loadFXMLLoader() {
		loader = new FXMLLoader(this.getClass().getResource("/fxml/battleship/ConfirmationOfLeaveGameWindow.fxml"));
		try {
			vBox = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller = loader.getController();
	}
	
	public void close() {
		Platform.runLater(() -> {
			stage.close();
		});
	}
}
