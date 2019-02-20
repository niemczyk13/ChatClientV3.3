package com.niemiec.games.battleship.view;

import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.controllers.EndGameInformationAndAcceptanceController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowOfTEndGameInformationAndAcceptance {
	private int typeOfGame;
	private String opponentPlayerNick;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private EndGameInformationAndAcceptanceController controller;
	private FXMLLoader loader;
	private VBox vBox;
	private Stage stage;
	
	public WindowOfTEndGameInformationAndAcceptance(int typeOfGame, String opponentPlayerNick, DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.typeOfGame = typeOfGame;
		this.opponentPlayerNick = opponentPlayerNick;
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}
	
	public void show(String text) {
		Platform.runLater(() -> {
			loadFXMLLoader();
			updateEndGameInformationAndAcceptanceController();
			view(text);
			addCloseOption();
		});
	}

	private void addCloseOption() {
		stage.setOnCloseRequest(e -> controller.understand());
	}
	
	private void view(String text) {
		Scene scene = new Scene(vBox);
		stage = new Stage();
		stage.setScene(scene);
		controller.setTextLabel(text);
		stage.show();
	}

	private void updateEndGameInformationAndAcceptanceController() {
		controller.setTypeOfGame(typeOfGame);
		controller.setOpponentPlayerNick(opponentPlayerNick);
		controller.setDispatcherOfOutgoingRequest(dispatcherOfOutgoingRequest);
	}

	private void loadFXMLLoader() {
		loader = new FXMLLoader(this.getClass().getResource("/fxml/battleship/EndGameInformationAndAcceptanceWindow.fxml"));
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

	public boolean isView() {
		return stage != null;
	}
}
