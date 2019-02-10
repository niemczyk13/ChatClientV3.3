package com.niemiec.games.battleship.data;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.processors.BattleshipProcessorData;

public class BattleshipData {
	private BattleshipGamesManager battleshipGamesManager;
	private BattleshipProcessorData battleshipProcessorData;
	
	public BattleshipData(ChatData chatData) {
		battleshipGamesManager = new BattleshipGamesManager();
		battleshipProcessorData = new BattleshipProcessorData(chatData);
	}

	public BattleshipGamesManager getBattleshipGamesManager() {
		return battleshipGamesManager;
	}

	public BattleshipProcessorData getBattleshipProcessorData() {
		return battleshipProcessorData;
	}
}
