package com.niemiec.games.battleship.command.processors.options;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.option.CloseBattleshipWindow;
import com.niemiec.games.battleship.data.BattleshipGameWithComputer;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.game.Battleship;
import com.niemiec.games.battleship.view.management.BattleshipView;

public class CloseBattleshipWindowProcessor {
	private BattleshipGamesManager battleshipGamesManager;
	private BattleshipGameWithComputer battleshipGameWithComputer;
	private String opponentPlayerNick;
	private CloseBattleshipWindow closeBattleshipWindow;

	public CloseBattleshipWindowProcessor(ChatData chatData) {
		battleshipGamesManager = chatData.getBattleshipGamesManager();
		battleshipGameWithComputer = chatData.getBattleshipGameWithComputer();
	}

	public void setTheCommand(Object object) {
		updateCloseBattleshipWindow(object);
		if (gameIsOnline()) {
			closeBattleshipWindowForOnlineGame();
		} else {
			closeBattleshipWindowForOfflineGame();
		}
	}

	private void closeBattleshipWindowForOfflineGame() {
		blockTheOfflineGameWindow();
		viewConfirmationOfLeaveOfflineGameWindow();
	}

	private void viewConfirmationOfLeaveOfflineGameWindow() {
		battleshipGameWithComputer.getBattleshipOfflineView().getConfirmationOfLeaveGameWindowView().show();
	}

	private void blockTheOfflineGameWindow() {
		battleshipGameWithComputer.getBorderManagement().setBordersToEndGame();
	}

	private void closeBattleshipWindowForOnlineGame() {
		updateOpponentPlayerNick();
		blockTheOnlineGameWindow();
		viewConfirmationOfLeaveOnlineGameWindow();
	}

	private boolean gameIsOnline() {
		return closeBattleshipWindow.getTypeOfGame() == Battleship.ONLINE;
	}

	private void blockTheOnlineGameWindow() {
//		battleshipGamesManager.getBorderManagement(opponentPlayerNick).setBordersToEndGame();
		BattleshipView battleshipView = battleshipGamesManager.getBattleshipView(opponentPlayerNick);
		battleshipView.getBorderManagement().setBordersToEndGame();
	}

	private void viewConfirmationOfLeaveOnlineGameWindow() {
		BattleshipView battleshipView = battleshipGamesManager.getBattleshipView(opponentPlayerNick);
		battleshipView.getConfirmationOfLeaveGameWindowView().show();
	}

	private void updateOpponentPlayerNick() {
		opponentPlayerNick = closeBattleshipWindow.getOpponentPlayerNick();
	}

	private void updateCloseBattleshipWindow(Object object) {
		closeBattleshipWindow = (CloseBattleshipWindow) object;	
	}
	
}
