package com.niemiec.games.battleship.command.order.game;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipGameInterface;

import javafx.event.ActionEvent;

public class ShipsAdder implements BattleshipGameInterface {
	public final int MANUALLY = 0;
	public final int AUTOMATICALLY = 1;
	private String opponentPlayerNick;
	private ActionEvent event;
	private int methodOfAdding;

	public ShipsAdder(String opponentPlayerNick, ActionEvent event) {
		this.opponentPlayerNick = opponentPlayerNick;
		this.event = event;
		this.methodOfAdding = MANUALLY;
	}
	
	public ShipsAdder(String opponentPlayerNick) {
		this.opponentPlayerNick = opponentPlayerNick;
		this.methodOfAdding = AUTOMATICALLY;
	}

	public String getOpponentPlayerNick() {
		return opponentPlayerNick;
	}

	public ActionEvent getEvent() {
		return event;
	}

	public int getMethodOfAdding() {
		return methodOfAdding;
	}
}
