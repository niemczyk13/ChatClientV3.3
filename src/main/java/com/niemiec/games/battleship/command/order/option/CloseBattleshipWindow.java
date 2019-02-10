package com.niemiec.games.battleship.command.order.option;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipOptionInterface;

public class CloseBattleshipWindow implements BattleshipOptionInterface {
	private String opponentPlayerNick;

	public CloseBattleshipWindow(String opponentPlayerNick) {
		this.opponentPlayerNick = opponentPlayerNick;
	}

	public String getOpponentPlayerNick() {
		return opponentPlayerNick;
	}
}
