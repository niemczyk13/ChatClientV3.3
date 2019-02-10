package com.niemiec.chat.data;

import com.niemiec.games.battleship.data.BattleshipData;

public class GamesData {
	private BattleshipData battleshipData;

	public GamesData(ChatData chatData) {
		battleshipData = new BattleshipData(chatData);
	}

	public BattleshipData getBattleshipData() {
		return battleshipData;
	}
}
