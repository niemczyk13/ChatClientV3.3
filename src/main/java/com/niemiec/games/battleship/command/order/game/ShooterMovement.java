package com.niemiec.games.battleship.command.order.game;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipGameInterface;

import javafx.event.ActionEvent;

public class ShooterMovement implements BattleshipGameInterface {
	private int typeOfGame;
	private String opponentPlayerNick;
	private ActionEvent event;

	public ShooterMovement(int typeOfGame, String opponentPlayerNick, ActionEvent event) {
		this.typeOfGame = typeOfGame;
		this.opponentPlayerNick = opponentPlayerNick;
		this.event = event;
	}

	public int getTypeOfGame() {
		return typeOfGame;
	}

	public String getOpponentPlayerNick() {
		return opponentPlayerNick;
	}

	public ActionEvent getEvent() {
		return event;
	}
}
