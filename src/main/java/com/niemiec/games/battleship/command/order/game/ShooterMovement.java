package com.niemiec.games.battleship.command.order.game;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipGameInterface;

import javafx.event.ActionEvent;

public class ShooterMovement implements BattleshipGameInterface {
	private String opponentPlayerNick;
	private ActionEvent event;

	public ShooterMovement(String opponentPlayerNick, ActionEvent event) {
		this.opponentPlayerNick = opponentPlayerNick;
		this.event = event;
	}

	public String getOpponentPlayerNick() {
		return opponentPlayerNick;
	}

	public ActionEvent getEvent() {
		return event;
	}
}
