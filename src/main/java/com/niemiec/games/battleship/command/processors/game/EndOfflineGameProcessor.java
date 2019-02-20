package com.niemiec.games.battleship.command.processors.game;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.data.BattleshipGameWithComputer;
import com.niemiec.games.battleship.view.management.BattleshipOfflineView;

public class EndOfflineGameProcessor {
	private String winner;
	private BattleshipGameWithComputer battleshipGameWithComputer;

	public EndOfflineGameProcessor(ChatData chatData) {
		battleshipGameWithComputer = chatData.getBattleshipGameWithComputer();
	}

	public void setTheCommand(Object object) {
		setTheEndOfflineGameWindow();
		showEndGameInformationAndAcceptance();
	}

	private void showEndGameInformationAndAcceptance() {
		winner = battleshipGameWithComputer.getShotShip().getWinnerName();
		battleshipGameWithComputer.getBattleshipOfflineView().getWindowOfTEndGameInformationAndAcceptance().show("Wygrywa gracz " + winner);
	}

	private void setTheEndOfflineGameWindow() {
		battleshipGameWithComputer.getBattleshipOfflineView().getBorderManagement().setBordersToEndGame();
	}
}
