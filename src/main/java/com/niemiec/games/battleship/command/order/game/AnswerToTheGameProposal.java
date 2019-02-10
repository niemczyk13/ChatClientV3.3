package com.niemiec.games.battleship.command.order.game;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipGameInterface;

public class AnswerToTheGameProposal implements BattleshipGameInterface {
	public static boolean ACCEPT = true;
	public static boolean NO_ACCEPT = false;
	private String opponentPlayerNick;
	private boolean accept;
	
	public AnswerToTheGameProposal(String opponentPlayerNick, boolean accept) {
		this.opponentPlayerNick = opponentPlayerNick;
		this.accept = accept;
	}

	public String getOpponentPlayerNick() {
		return opponentPlayerNick;
	}

	public boolean isAccept() {
		return accept;
	}
}
