package com.niemiec.games.battleship.view;

import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.controllers.WindowWithAnUnacceptableGameController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowWithAnUnacceptableGameView {
	private String opponentPlayerNick;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private WindowWithAnUnacceptableGameController controller;
	private FXMLLoader loader;
	private VBox vBox;
	private Stage stage;
	
	public WindowWithAnUnacceptableGameView(String opponentPlayerNick,	DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.opponentPlayerNick = opponentPlayerNick;
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}
	
	public void show() {
		Platform.runLater(() -> {
			loadFXMLLoader();
			updateWindowWiatAnUnacceptableGameController();
			view();
			addCloseOption();
		});
	}
	
	private void addCloseOption() {
		stage.setOnCloseRequest(e -> controller.understand());
	}
	
	private void view() {
		Scene scene = new Scene(vBox);
		stage = new Stage();
		stage.setScene(scene);
		controller.setTextLabel("Użytkownik " + opponentPlayerNick + " nie zaakceptował gry");
		stage.show();
	}
	
	private void updateWindowWiatAnUnacceptableGameController() {
		controller.setOpponentPlayerNick(opponentPlayerNick);
		controller.setDispatcherOfOutgoingRequest(dispatcherOfOutgoingRequest);
	}

	private void loadFXMLLoader() {
		loader = new FXMLLoader(this.getClass().getResource("/fxml/battleship/InformationAndAcceptanceWindow.fxml"));
		try {
			vBox = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller = loader.getController();
	}

	public void close() {
		stage.close();
	}
}
