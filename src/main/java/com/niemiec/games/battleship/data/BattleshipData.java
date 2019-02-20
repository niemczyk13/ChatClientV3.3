package com.niemiec.games.battleship.data;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.processors.BattleshipProcessorData;

public class BattleshipData {
	private BattleshipGamesManager battleshipGamesManager;
	private BattleshipProcessorData battleshipProcessorData;
	private BattleshipGameWithComputer battleshipGameWithComputer;
	private ChatData chatData;
	
	public BattleshipData(ChatData chatData) {
		this.chatData = chatData;
		battleshipGamesManager = new BattleshipGamesManager();
	}

	public BattleshipGamesManager getBattleshipGamesManager() {
		return battleshipGamesManager;
	}

	public BattleshipProcessorData getBattleshipProcessorData() {
		if (battleshipProcessorData == null) {
			battleshipProcessorData = new BattleshipProcessorData(chatData);
		}
		return battleshipProcessorData;
	}

	public BattleshipGameWithComputer getBattleshipGameWithComputer() {
		if (battleshipGameWithComputer == null) {
			battleshipGameWithComputer = new BattleshipGameWithComputer(chatData);
		}
		return battleshipGameWithComputer;
	}
}
