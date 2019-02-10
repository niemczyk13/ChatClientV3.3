package com.niemiec.games.battleship.command.processors.options;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.option.Exit;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.view.BattleshipView;

public class ExitProcessor {
	private BattleshipGamesManager battleshipGamesManager;
	private String opponentPlayerNick;

	public ExitProcessor(ChatData chatData) {
		battleshipGamesManager = chatData.getBattleshipGamesManager();
	}

	public void setTheCommand(Object object) {
		updateOpponentPlayerNick(object);
		closeEndGameInformationAcdAcceptanceWindow();
		closeBattleshipWindow();
		deleteBattleshipGame();
	}

	private void closeEndGameInformationAcdAcceptanceWindow() {
		BattleshipView battleshipView = battleshipGamesManager.getBattleshipView(opponentPlayerNick);
		battleshipView.getWindowOfTEndGameInformationAndAcceptance().close();
	}

	private void closeBattleshipWindow() {
		BattleshipView battleshipView = battleshipGamesManager.getBattleshipView(opponentPlayerNick);
		battleshipView.getMainBattleshipView().close();
	}
	
	private void deleteBattleshipGame() {
		battleshipGamesManager.deleteBattleshipGame(opponentPlayerNick);
	}

	private void updateOpponentPlayerNick(Object object) {
		Exit exit = (Exit) object;
		opponentPlayerNick = exit.getOpponentPlayerNick();
	}

}
