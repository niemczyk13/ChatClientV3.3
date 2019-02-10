package com.niemiec.games.battleship.command.order.game;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipGameInterface;

public class AcceptRejectionGame implements BattleshipGameInterface {
	private String opponentPlayerNick;
	
	public AcceptRejectionGame(String opponentPlayerNick) {
		this.opponentPlayerNick = opponentPlayerNick;
	}

	public String getOpponentPlayerNick() {
		return opponentPlayerNick;
	}
}
