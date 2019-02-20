package com.niemiec.games.battleship.command.order.option;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipOptionInterface;

public class Exit implements BattleshipOptionInterface {
	private int typeOfGame;
	private String opponentPlayerNick;
	
	public Exit(int typeOfGame, String opponentPlayerNick) {
		this.typeOfGame = typeOfGame;
		this.opponentPlayerNick = opponentPlayerNick;
	}

	public String getOpponentPlayerNick() {
		return opponentPlayerNick;
	}

	public int getTypeOfGame() {
		return typeOfGame;
	}
}
