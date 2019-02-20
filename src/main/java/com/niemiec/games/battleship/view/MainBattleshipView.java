package com.niemiec.games.battleship.view;

import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.controllers.MainBattleshipController;
import com.niemiec.games.battleship.game.logic.BorderManagement;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainBattleshipView {
	public static final int REAL_PLAYER = 0;
	public static final int VIRTUAL_PLAYER = 1;
	private int typeOfGame;
	private String opponentPlayerNick;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private BorderManagement borderManagement;
	private MainBattleshipController controller;
	private FXMLLoader loader;
	private VBox vBox;
	private Stage stage;

	public MainBattleshipView(int typeOfGame, String opponentPlayerNick,
			DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest, BorderManagement borderManagement) {
		this.typeOfGame = typeOfGame;
		this.opponentPlayerNick = opponentPlayerNick;
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
		this.borderManagement = borderManagement;
	}

	public void show() {
		Platform.runLater(() -> {
			loadFXMLLoader();
			updateBattleshipWindowController();
			view();
			updateBorderManagement();
			addCloseOption();
		});
	}

	private void updateBorderManagement() {
		VBox myBorder = controller.getMyBorder();
		VBox opponentBorder = controller.getOpponentBorder();
		borderManagement.setBorders(myBorder, opponentBorder);
	}

	private void addCloseOption() {
		stage.setOnCloseRequest(e -> {
			controller.close();
			e.consume();
		});
	}

	private void view() {
		Scene scene = new Scene(vBox);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Battleship. Przeciwnik: " + opponentPlayerNick);
		stage.show();
	}

	private void updateBattleshipWindowController() {
		controller.setTypeOfGame(typeOfGame);
		controller.setOpponentPlayerNick(opponentPlayerNick);
		controller.setDispatcherOfOutgoingRequest(dispatcherOfOutgoingRequest);
	}

	private void loadFXMLLoader() {
		loader = new FXMLLoader(this.getClass().getResource("/fxml/battleship/BattleshipWindow.fxml"));
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

	public MainBattleshipController getController() {
		return controller;
	}

	public void disableTrue() {
		try {
			stage.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disableFalse() {
	}
}
