package com.niemiec.games.battleship.command.processors.game;

import com.niemiec.chat.data.ChatData;
import com.niemiec.chat.view.ChatView;
import com.niemiec.games.battleship.data.BattleshipGameWithComputer;
import com.niemiec.games.battleship.view.management.BattleshipOfflineView;

public class PlayBattleshipOfflineProcessor {
	private BattleshipOfflineView battleshipOfflineView;
	private BattleshipGameWithComputer battleshipGameWithComputer;
	private ChatView chatView;

	public PlayBattleshipOfflineProcessor(ChatData chatData) {
		this.battleshipGameWithComputer = chatData.getBattleshipGameWithComputer();
		this.chatView = chatData.getChatView();
	}

	public void setTheCommand(Object object) {
		battleshipGameWithComputer.startNewGame();
		disableGeneralChatBattleshipMenu();
		updateBattleshipOfflineView();
		viewMainBattleshipView();
	}

	private void updateBattleshipOfflineView() {
		battleshipOfflineView = battleshipGameWithComputer.getBattleshipOfflineView();
	}

	private void viewMainBattleshipView() {
		battleshipOfflineView.getMainBattleshipView().show();
		battleshipOfflineView.getBorderManagement().startNewGameWithVirtualPlayer();
	}

	private void disableGeneralChatBattleshipMenu() {
		chatView.getGeneralChatView().setDisablePlayBattleshipWithComputerButton(true);
	}

}
