package com.niemiec.games.battleship.command.processors.game;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.game.objects.Player;
import com.niemiec.games.battleship.game.objects.PlayerImpl;
import com.niemiec.games.battleship.messages.BattleshipGame;
import com.niemiec.games.battleship.view.BattleshipView;

public class GameProposalProcessor {
	private ChatData chatData;
	private BattleshipGamesManager battleshipGamesManager;
	private BattleshipGame battleshipGame;
	private BattleshipView battleshipView;

	public GameProposalProcessor(ChatData chatData) {
		this.chatData = chatData;
		battleshipGamesManager = chatData.getBattleshipGamesManager();
	}

	public void setTheCommand(Object object) {
		updateBattlesipGame(object);
		setPlayersInBattleshipGame();
		createBattleshipView();
		addBattleshipGameAndBattleshipViewInBattleshipGamesManager();
		sendTheBattleshipGame();
		showWaitingWindow();
	}

	public void receiveTheObject(Object object) {
		updateBattlesipGame(object);
		if (!gameIsExist()) {
			displayTheGameAcceptanceWindow();
		} else {
			sendRejectionBattleshipProspal();
		}
	}

	private void showWaitingWindow() {
		battleshipView.getWindowOfWaitingForConfirmationOfTheGameView().show();
	}

	private void sendTheBattleshipGame() {
		chatData.sendTheObject(battleshipGame);
	}

	private void createBattleshipView() {
		battleshipView = new BattleshipView(battleshipGame.getOpponentPlayerNick(), chatData.getDispatcherOfOutgoingRequest());
	}

	private void addBattleshipGameAndBattleshipViewInBattleshipGamesManager() {
		battleshipGamesManager.addBattleshipGame(battleshipGame, battleshipView);
	}

	private void setPlayersInBattleshipGame() {
		battleshipGame.setPlayer(new PlayerImpl(Player.SECOND_PLAYER, chatData.getNick()));
		battleshipGame.setOpponentPlayerNick(chatData.getActualInterlocutor());
	}

	private void updateBattlesipGame(Object object) {
		battleshipGame = (BattleshipGame) object;
	}

	private void displayTheGameAcceptanceWindow() {
		deleteBattleshipGameIfExsistInformationController();
		createBattleshipView();
		addBattleshipGameAndBattleshipViewInBattleshipGamesManager();
		showGameAcceptanceWindow();
	}

	private void sendRejectionBattleshipProspal() {
		battleshipGame.setGameStatus(BattleshipGame.REJECTION_GAME_PROPOSAL);
		chatData.sendTheObject(battleshipGame);
	}

	private void showGameAcceptanceWindow() {
		battleshipView = battleshipGamesManager.getBattleshipView(battleshipGame);
		battleshipView.getGameAcceptanceWindowView().show();
	}

	private void deleteBattleshipGameIfExsistInformationController() {
		if (gameIsExist()) {
			closeGameAcceptanceWindow();
			deleteBattleshipGame();
		}
	}

	private void deleteBattleshipGame() {
		battleshipGamesManager.deleteBattleshipGame(battleshipGame);
	}

	private void closeGameAcceptanceWindow() {
		battleshipView = battleshipGamesManager.getBattleshipView(battleshipGame);
		battleshipView.getGameAcceptanceWindowView().close();
	}

	private boolean gameIsExist() {
		return battleshipGamesManager.checkIfExist(battleshipGame);
	}
}
