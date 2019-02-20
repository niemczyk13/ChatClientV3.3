package com.niemiec.games.battleship.command.processors.game;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.data.BattleshipGameWithComputer;
import com.niemiec.games.battleship.view.management.BattleshipOfflineView;

public class ShipsAddedInOfflineGameProcessor {
	private ChatData chatData;
	private BattleshipOfflineView battleshipOfflineView;
	private BattleshipGameWithComputer battleshipGameWithComputer;

	public ShipsAddedInOfflineGameProcessor(ChatData chatData) {
		this.chatData = chatData;
	}

	public void setTheCommand(Object object) {
		updateBattleshipOfflineGameObjects();
		battleshipOfflineView.getBorderManagement().setBordersToStartShot();
	}

	private void updateBattleshipOfflineGameObjects() {
		battleshipGameWithComputer = chatData.getBattleshipGameWithComputer();
		battleshipOfflineView = battleshipGameWithComputer.getBattleshipOfflineView();
	}
}
