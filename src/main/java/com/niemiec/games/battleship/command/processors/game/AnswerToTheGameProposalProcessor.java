package com.niemiec.games.battleship.command.processors.game;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.game.AnswerToTheGameProposal;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.messages.BattleshipGame;

public class AnswerToTheGameProposalProcessor {
	private ChatData chatData;
	private BattleshipGamesManager battleshipGamesManager;
	private AnswerToTheGameProposal answerToTheGameProposal;
	private BattleshipGame battleshipGame;

	public AnswerToTheGameProposalProcessor(ChatData chatData) {
		this.chatData = chatData;
		this.battleshipGamesManager = chatData.getBattleshipGamesManager();
	}

	public void setTheCommand(Object object) {
		updateAnswerToTheGameProposal(object);
		getBattleshipGame();
		closeGameAcceptanceWindow();
		setTheAcceptanceResponseInBattleshipGame();
		sendTheObject();
	}

	private void sendTheObject() {
		chatData.sendTheObject(battleshipGame);
	}

	private void setTheAcceptanceResponseInBattleshipGame() {
		if (answerToTheGameProposal.isAccept()) {
			battleshipGame.setGameStatus(BattleshipGame.ACCEPTING_THE_GAME);
		} else {
			battleshipGame.setGameStatus(BattleshipGame.REJECTION_GAME_PROPOSAL);
			deleteBattleshipGame();
		}
	}

private void deleteBattleshipGame() {
		battleshipGamesManager.deleteBattleshipGame(battleshipGame);
	}

	private void closeGameAcceptanceWindow() {
		battleshipGamesManager.getBattleshipView(battleshipGame).getGameAcceptanceWindowView().close();
	}

	private void getBattleshipGame() {
		battleshipGame = battleshipGamesManager.getBattleshipGame(answerToTheGameProposal.getOpponentPlayerNick());
	}

	private void updateAnswerToTheGameProposal(Object object) {
		answerToTheGameProposal = (AnswerToTheGameProposal) object;
	}

}
