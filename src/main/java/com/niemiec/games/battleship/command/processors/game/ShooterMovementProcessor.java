package com.niemiec.games.battleship.command.processors.game;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.game.EndOfflineGame;
import com.niemiec.games.battleship.command.order.game.ShooterMovement;
import com.niemiec.games.battleship.data.BattleshipGameWithComputer;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.game.Battleship;
import com.niemiec.games.battleship.game.data.check.CheckData;
import com.niemiec.games.battleship.game.logic.BorderManagement;
import com.niemiec.games.battleship.game.logic.ShotShip;
import com.niemiec.games.battleship.game.objects.Board;
import com.niemiec.games.battleship.game.objects.Coordinates;
import com.niemiec.games.battleship.messages.BattleshipGame;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class ShooterMovementProcessor {
	private ChatData chatData;
	private BattleshipGamesManager battleshipGamesManager;
	private BattleshipGameWithComputer battleshipGameWithComputer;
	private BattleshipGame battleshipGame;
	private ShooterMovement shooterMovement;
	private Coordinates coordinates;

	public ShooterMovementProcessor(ChatData chatData) {
		this.chatData = chatData;
		this.battleshipGamesManager = chatData.getBattleshipGamesManager();
		this.battleshipGameWithComputer = chatData.getBattleshipGameWithComputer();
	}

	public void receiveTheObject(Object object) {
		updateBattleshipGame(object);
		updateBorders();
		updateBattleshipGameInManager();
		updateViewWindow();
	}

	private void updateViewWindow() {
		if (turnOfPlayIsYours()) {
			setThePlayingOnlineGameWindow();
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
		if (gameIsOnline()) {
			setTheCommandOnline();
		} else {
			setTheCommandOffline();
		}
	}

	private void setTheCommandOffline() {
		setThePendingOfflineGameWindow();
		offlineShot();
		drawInBordersInOfflineGame();
	}

	private void offlineShot() {
		updateCoordinates();
		if (thisIsWinnerShotInOfflineGame()) {
			endOfflineGame();
		} else {
			setThePlayingOfflineGameWindow();
		}
	}

	private void endOfflineGame() {
		chatData.getDispatcherOfOutgoingRequest().setTheCommand(new EndOfflineGame());
	}

	private boolean thisIsWinnerShotInOfflineGame() {
		ShotShip shotShip = battleshipGameWithComputer.getShotShip();
		return shotShip.shot(coordinates);
	}

	private void setTheCommandOnline() {
		updateBattleshipGameFromManager();
		updateCoordinates();
		setThePendingOnlineGameWindow();
		onlineShot();
	}

	private boolean gameIsOnline() {
		return shooterMovement.getTypeOfGame() == Battleship.ONLINE;
	}

	private void onlineShot() {
		if (playingFieldHasNotBeenUsed()) {
			setShotCoordinatesInBattleshipGame();
			sendBattleshipGame();
		} else {
			setThePlayingOnlineGameWindow();
		}
	}

	private void setThePlayingOnlineGameWindow() {
		battleshipGamesManager.getBorderManagement(battleshipGame).setBordersToStartShot();
	}
	
	private void setThePlayingOfflineGameWindow() {
		battleshipGameWithComputer.getBorderManagement().setBordersToStartShot();
	}

	private void setShotCoordinatesInBattleshipGame() {
		battleshipGame.setShotCoordinates(coordinates);
	}

	private void setThePendingOnlineGameWindow() {
		battleshipGamesManager.getBorderManagement(shooterMovement.getOpponentPlayerNick()).setBordersToEndGame();
	}
	
	private void setThePendingOfflineGameWindow() {
		battleshipGameWithComputer.getBorderManagement().setBordersToEndGame();
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
	
	private void drawInBordersInOfflineGame() {
		BorderManagement b = battleshipGameWithComputer.getBorderManagement();
		Platform.runLater(() -> {
			b.drawBoardsInBordersDuringTheGame(battleshipGameWithComputer.getRealPlayer());
		});
	}

	private boolean playerInBattleshipGameExist() {
		return battleshipGame.getPlayer() != null;
	}

	private void updateBattleshipGame(Object object) {
		battleshipGame = (BattleshipGame) object;
	}

}
