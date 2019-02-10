package com.niemiec.games.battleship.view;

import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.controllers.GameAcceptanceWindowController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameAcceptanceWindowView {
	private String opponentPlayerNick;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private GameAcceptanceWindowController controller;
	private FXMLLoader loader;
	private VBox vBox;
	private Stage stage;
	

	public GameAcceptanceWindowView(String opponentPlayerNick, DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.opponentPlayerNick = opponentPlayerNick;
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}

	public void show() {
		Platform.runLater(() -> {
			loadFXMLLoader();
			updateGameAcceptanceWindowsController();
			view("Propozycja gry od użytkownika " + opponentPlayerNick);
			addCloseOption();
		});
	}
	
	private void addCloseOption() {
		stage.setOnCloseRequest(e -> controller.rejectionGameProposal());
	}

	private void view(String info) {
		Scene scene = new Scene(vBox);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Akceptacja gry");
		controller.setTextLabel(info);
		stage.show();
	}

	private void updateGameAcceptanceWindowsController() {
		controller.setOpponentPlayerNick(opponentPlayerNick);
		controller.setDispatcherOfOutgoingRequest(dispatcherOfOutgoingRequest);
	}

	private void loadFXMLLoader() {
		loader = new FXMLLoader(this.getClass().getResource("/fxml/battleship/AcceptanceWindow.fxml"));
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
