package com.niemiec.games.battleship.command.order.game;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipGameInterface;

public class GiveUp implements BattleshipGameInterface {
	public static final int GIVE_UP = 0;
	public static final int CANCEL = 1;
	
	private int typeOfGame;
	private String opponentPlayerNick;
	private int resignation;

	public GiveUp(int typeOfGame, String opponentPlayerNick, int resignation) {
		this.typeOfGame = typeOfGame;
		this.opponentPlayerNick = opponentPlayerNick;
		this.resignation = resignation;
	}

	public String getOpponentPlayerNick() {
		return opponentPlayerNick;
	}

	public int getResignation() {
		return resignation;
	}

	public int getTypeOfGame() {
		return typeOfGame;
	}
}
