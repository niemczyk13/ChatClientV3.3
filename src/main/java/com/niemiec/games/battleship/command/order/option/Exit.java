package com.niemiec.games.battleship.command.order.option;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipOptionInterface;

public class Exit implements BattleshipOptionInterface {
	private String opponentPlayerNick;
	
	public Exit(String opponentPlayerNick) {
		this.opponentPlayerNick = opponentPlayerNick;
	}

	public String getOpponentPlayerNick() {
		return opponentPlayerNick;
	}
}
