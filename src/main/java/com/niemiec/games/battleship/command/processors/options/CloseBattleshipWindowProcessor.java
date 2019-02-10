package com.niemiec.games.battleship.command.processors.options;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.option.CloseBattleshipWindow;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.view.BattleshipView;

public class CloseBattleshipWindowProcessor {
	private BattleshipGamesManager battleshipGamesManager;
	private String opponentPlayerNick;
	private CloseBattleshipWindow closeBattleshipWindow;

	public CloseBattleshipWindowProcessor(ChatData chatData) {
		battleshipGamesManager = chatData.getBattleshipGamesManager();
	}

	public void setTheCommand(Object object) {
		updateCloseBattleshipWindow(object);
		updateOpponentPlayerNick();
		blockTheGameWindow();
		viewConfirmationOfLeaveGameWindow();
	}

	private void blockTheGameWindow() {
		battleshipGamesManager.getBorderManagement(opponentPlayerNick).setBordersToEndGame();
	}

	private void viewConfirmationOfLeaveGameWindow() {
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
