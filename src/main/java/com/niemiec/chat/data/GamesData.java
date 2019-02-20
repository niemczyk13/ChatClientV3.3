package com.niemiec.chat.data;

import com.niemiec.games.battleship.data.BattleshipData;

public class GamesData {
	public static final int ONLINE = 0;
	public static final int OFFLINE = 1;
	
	private BattleshipData battleshipData;

	public GamesData(ChatData chatData) {
		battleshipData = new BattleshipData(chatData);
	}

	public BattleshipData getBattleshipData() {
		return battleshipData;
	}
}
