package com.niemiec.games.battleship.command.processors.game;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.game.ShooterMovement;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.game.data.check.CheckData;
import com.niemiec.games.battleship.game.logic.BorderManagement;
import com.niemiec.games.battleship.game.objects.Board;
import com.niemiec.games.battleship.game.objects.Coordinates;
import com.niemiec.games.battleship.messages.BattleshipGame;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class ShooterMovementProcessor {
	private ChatData chatData;
	private BattleshipGamesManager battleshipGamesManager;
	private BattleshipGame battleshipGame;
	private ShooterMovement shooterMovement;
	private Coordinates coordinates;

	public ShooterMovementProcessor(ChatData chatData) {
		this.chatData = chatData;
		this.battleshipGamesManager = chatData.getBattleshipGamesManager();
	}

	public void receiveTheObject(Object object) {
		updateBattleshipGame(object);
		updateBorders();
		updateBattleshipGameInManager();
		updateViewWindow();
	}

	private void updateViewWindow() {
		if (turnOfPlayIsYours()) {
			setThePlayingGameWindow();
		}
	}

	private boolean turnOfPlayIsYours() {
		String nickWhoseTourn = battleshipGame.getNickWhoseTourn();
		return nickWhoseTourn.equals(chatData.getNick());
	}

	private void updateBattleshipGameInManager() {
		battleshipGamesManager.updateBattleshipGame(battleshipGame);
	}

	public void setTheCommand(Object object) {
		updateShooterMovement(object);
		updateBattleshipGameFromManager();
		updateCoordinates();
		setThePendingGameWindow();
		shot();
	}

	private void shot() {
		if (playingFieldHasNotBeenUsed()) {
			setShotCoordinatesInBattleshipGame();
			sendBattleshipGame();
		} else {
			setThePlayingGameWindow();
		}
	}

	private void setThePlayingGameWindow() {
		battleshipGamesManager.getBorderManagement(battleshipGame).setBordersToStartShot();
	}

	private void setShotCoordinatesInBattleshipGame() {
		battleshipGame.setShotCoordinates(coordinates);
	}

	private void setThePendingGameWindow() {
		battleshipGamesManager.getBorderManagement(shooterMovement.getOpponentPlayerNick()).setBordersToEndGame();
	}

	private void sendBattleshipGame() {
		chatData.sendTheObject(battleshipGame);
	}

	private boolean playingFieldHasNotBeenUsed() {
		return boxIsEmpty();
	}

	private boolean boxIsEmpty() {
		int box = battleshipGame.getBoxFromOpponentBoard(coordinates);
		return box == Board.BOX_EMPTY;
	}

	private void updateBattleshipGameFromManager() {
		battleshipGame = battleshipGamesManager.getBattleshipGame(shooterMovement.getOpponentPlayerNick());
	}

	private void updateCoordinates() {
		coordinates = CheckData.getCoordinatesFromButton((Button) shooterMovement.getEvent().getSource());
	}

	private void updateShooterMovement(Object object) {
		shooterMovement = (ShooterMovement) object;
	}

	private void updateBorders() {
		if (playerInBattleshipGameExist()) {
			drawInBorders();
		}
	}

	private void drawInBorders() {
		BorderManagement b = battleshipGamesManager.getBorderManagement(battleshipGame);
		Platform.runLater(() -> {
			b.drawBoardsInBordersDuringTheGame(battleshipGame.getPlayer());
		});
	}

	private boolean playerInBattleshipGameExist() {
		return battleshipGame.getPlayer() != null;
	}

	private void updateBattleshipGame(Object object) {
		battleshipGame = (BattleshipGame) object;
	}

}
