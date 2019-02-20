package com.niemiec.games.battleship.command.processors.game;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.game.GiveUp;
import com.niemiec.games.battleship.command.order.option.Exit;
import com.niemiec.games.battleship.data.BattleshipGameWithComputer;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.game.Battleship;
import com.niemiec.games.battleship.messages.BattleshipGame;
import com.niemiec.games.battleship.view.management.BattleshipView;

public class GiveUpProcessor {
	private ChatData chatData;
	private BattleshipGamesManager battleshipGamesManager;
	private BattleshipGameWithComputer battleshipGameWithComputer;
	private BattleshipGame battleshipGame;
	private GiveUp giveUp;
	private String opponentPlayerNick;

	public GiveUpProcessor(ChatData chatData) {
		this.chatData = chatData;
		battleshipGamesManager = chatData.getBattleshipGamesManager();
		battleshipGameWithComputer = chatData.getBattleshipGameWithComputer();
	}

	public void setTheCommand(Object object) {
		updateGiveUp(object);
		updateOpoonentPlayerNick();
		if (gameIsOnline()) {
			setTheCommandOnlineGame();
		} else {
			setTheCommandOfflineGame();
		}
	}

	private void setTheCommandOfflineGame() {
		if (giveUpTheGame()) {
			closeConfirmationOfLeaveGameWindowView();
			sendExitCommand();
		} else {
			closeConfirmationOfLeaveGameWindowView();
			unblockMainBattleshipWindow();
		}
	}

	private void unblockMainBattleshipWindow() {
		if (shipsAdded()) {
			unblockShotBorders();
		} else {
			unblockAddBorders();
		}
	}

	private void unblockAddBorders() {
		battleshipGameWithComputer.getBorderManagement().setBordersToStartAdd();
	}

	private void unblockShotBorders() {
		battleshipGameWithComputer.getBorderManagement().setBordersToStartShot();
	}

	private boolean shipsAdded() {
		return battleshipGameWithComputer.getAddShips().areShipsAdded();
	}

	private void sendExitCommand() {
		chatData.getDispatcherOfOutgoingRequest().setTheCommand(new Exit(Battleship.OFFLINE, opponentPlayerNick));
	}

	private void closeConfirmationOfLeaveGameWindowView() {
		battleshipGameWithComputer.getBattleshipOfflineView().getConfirmationOfLeaveGameWindowView().close();
	}

	private void setTheCommandOnlineGame() {
		updateBattleshipGameFromManager();
		if (giveUpTheGame()) {
			startTheResignationProcedure();
			closeBattleship();
		} else {
			closeConfirmationOfLeaveGameWindow();
			updateViewWindow();
		}
	}

	private boolean gameIsOnline() {
		return giveUp.getTypeOfGame() == Battleship.ONLINE;
	}

	private void updateGiveUp(Object object) {
		giveUp = (GiveUp) object;
	}

	private void updateBattleshipGameFromManager() {
		battleshipGame = battleshipGamesManager.getBattleshipGame(opponentPlayerNick);
	}

	private void updateOpoonentPlayerNick() {
		opponentPlayerNick = giveUp.getOpponentPlayerNick();
	}

	private boolean giveUpTheGame() {
		return giveUp.getResignation() == GiveUp.GIVE_UP;
	}

	private void startTheResignationProcedure() {
		if (!battleshipHasBeenCompleted()) {
			updateVariablesInBattleshipGame();
			sendBattleshipGame();
		}
	}

	private boolean battleshipHasBeenCompleted() {
		return battleshipGame.getGameStatus() == BattleshipGame.END_GAME;
	}

	private void updateVariablesInBattleshipGame() {
		battleshipGame.setGameStatus(BattleshipGame.GIVE_UP);
		battleshipGame.setWinnerNick(opponentPlayerNick);
	}

	private void sendBattleshipGame() {
		chatData.sendTheObject(battleshipGame);
	}

	private void closeBattleship() {
		closeConfirmationOfLeaveGameWindow();
		closeBattleshipWindow();
		deleteBattleshipGame();
	}

	private void closeConfirmationOfLeaveGameWindow() {
		battleshipGamesManager.getBattleshipView(opponentPlayerNick).getConfirmationOfLeaveGameWindowView().close();
	}

	private void closeBattleshipWindow() {
		BattleshipView battleshipView = battleshipGamesManager.getBattleshipView(opponentPlayerNick);
		battleshipView.getMainBattleshipView().close();
	}

	private void deleteBattleshipGame() {
		battleshipGamesManager.deleteBattleshipGame(opponentPlayerNick);
	}

	private void updateViewWindow() {
		if (turnOfPlayIsYours()) {
			setThePlayingGameWindow();
		} else if (youAreAtStageOfAddingShips()) {
			setTheAddedShipsGameWindow();
		}
	}

	private void setTheAddedShipsGameWindow() {
		battleshipGamesManager.getBorderManagement(battleshipGame).continudeAddingShips();
	}

	private boolean youAreAtStageOfAddingShips() {
		return battleshipGame.getGameStatus() == BattleshipGame.ADD_SHIPS;
	}

	private boolean turnOfPlayIsYours() {
		return battleshipGame.getNickWhoseTourn() != null
				&& battleshipGame.getNickWhoseTourn().equals(chatData.getNick());
	}

	private void setThePlayingGameWindow() {
		battleshipGamesManager.getBorderManagement(battleshipGame).setBordersToStartShot();
	}
}
