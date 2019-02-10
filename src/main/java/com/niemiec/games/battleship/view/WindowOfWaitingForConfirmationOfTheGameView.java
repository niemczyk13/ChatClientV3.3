package com.niemiec.games.battleship.view;

import com.niemiec.games.battleship.controllers.WaitingWindowController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowOfWaitingForConfirmationOfTheGameView {
	private String opponentPlayerNick;
	private WaitingWindowController controller;
	private FXMLLoader loader;
	private VBox vBox;
	private Stage stage;

	public WindowOfWaitingForConfirmationOfTheGameView(String opponentPlayerNick) {
		this.opponentPlayerNick = opponentPlayerNick;
	}

	public void show() {
		Platform.runLater(() -> {
			loadFXMLLoader();
			view("Oczekiwanie na potwierdzenie gry przez gracza " + opponentPlayerNick);
		});
	}

	private void loadFXMLLoader() {
		loader = new FXMLLoader(this.getClass().getResource("/fxml/battleship/WaitingForAcceptanceWindow.fxml"));
		try {
			vBox = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller = loader.getController();
	}

	private void view(String message) {
		Scene scene = new Scene(vBox);
		stage = new Stage();
		stage.setScene(scene);
		controller.setTextLabel(message);
		stage.show();
	}

	public void close() {
		Platform.runLater(() -> {
			stage.close();
		});
	}

	public boolean isShowing() {
		if (stage == null)
			return false;
		return true;
	}
}
