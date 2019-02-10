package com.niemiec.games.battleship.command.processors.game;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.game.GiveUp;
import com.niemiec.games.battleship.data.BattleshipGamesManager;

public class ExitAllBattleshipGamesProcessor {
	private ChatData chatData;
	private BattleshipGamesManager battleshipGamesManager;
	private String opponentPlayerNick;

	public ExitAllBattleshipGamesProcessor(ChatData chatData) {
		this.chatData = chatData;
		battleshipGamesManager = chatData.getBattleshipGamesManager();
	}

	public void setTheCommand(Object object) {
		closeBattleshipGames();
	}

	private void closeBattleshipGames() {
		for (int i = battleshipGamesManager.getNumberOfBattleshipGames(); i > 0 ; i-- ) {
			getNextOpponentPlayerNick();
			sendGiveUp();
		}
	}

	private void sendGiveUp() {
		chatData.getDispatcherOfOutgoingRequest().setTheCommand(new GiveUp(opponentPlayerNick, GiveUp.GIVE_UP));
	}

	private void getNextOpponentPlayerNick() {
		opponentPlayerNick = battleshipGamesManager.getFirstOpponentPlayer();
	}
}
