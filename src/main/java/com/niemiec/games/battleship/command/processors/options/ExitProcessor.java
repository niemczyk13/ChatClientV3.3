package com.niemiec.games.battleship.command.processors.options;

import com.niemiec.chat.data.ChatData;
import com.niemiec.chat.view.ChatView;
import com.niemiec.games.battleship.command.order.option.Exit;
import com.niemiec.games.battleship.data.BattleshipGameWithComputer;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.game.Battleship;
import com.niemiec.games.battleship.view.management.BattleshipView;

public class ExitProcessor {
	private BattleshipGamesManager battleshipGamesManager;
	private BattleshipGameWithComputer battleshipGameWithComputer;
	private ChatView chatView;
	private String opponentPlayerNick;
	private int typeOfGame;

	public ExitProcessor(ChatData chatData) {
		battleshipGamesManager = chatData.getBattleshipGamesManager();
		battleshipGameWithComputer = chatData.getBattleshipGameWithComputer();
		chatView = chatData.getChatView();
	}

	public void setTheCommand(Object object) {
		updateExitVariables(object);
		if (gameIsOnline()) {
			closeOnlineBattleshipGame();
		} else {
			closeOfflineBattleshipGame();
		}
	}

	private void closeOfflineBattleshipGame() {
		battleshipGameWithComputer.getBattleshipOfflineView().getMainBattleshipView().close();
		if (windowOfTheEndGameInformationAndAcceptanceIsView()) {
			battleshipGameWithComputer.getBattleshipOfflineView().getWindowOfTEndGameInformationAndAcceptance().close();
		}
		chatView.getGeneralChatView().setDisablePlayBattleshipWithComputerButton(false);
	}

	private boolean windowOfTheEndGameInformationAndAcceptanceIsView() {
		return battleshipGameWithComputer.getBattleshipOfflineView().getWindowOfTEndGameInformationAndAcceptance().isView();
	}

	private void closeOnlineBattleshipGame() {
		closeEndGameInformationAcdAcceptanceWindowIfOnline();
		closeBattleshipWindowIfOnline();
		deleteBattleshipGameIfOnline();
	}

	private boolean gameIsOnline() {
		return typeOfGame == Battleship.ONLINE;
	}

	private void closeEndGameInformationAcdAcceptanceWindowIfOnline() {
		BattleshipView battleshipView = battleshipGamesManager.getBattleshipView(opponentPlayerNick);
		battleshipView.getWindowOfTEndGameInformationAndAcceptance().close();
	}

	private void closeBattleshipWindowIfOnline() {
		BattleshipView battleshipView = battleshipGamesManager.getBattleshipView(opponentPlayerNick);
		battleshipView.getMainBattleshipView().close();
	}

	private void deleteBattleshipGameIfOnline() {
		battleshipGamesManager.deleteBattleshipGame(opponentPlayerNick);
	}

	private void updateExitVariables(Object object) {
		Exit exit = (Exit) object;
		opponentPlayerNick = exit.getOpponentPlayerNick();
		typeOfGame = exit.getTypeOfGame();
	}

}
