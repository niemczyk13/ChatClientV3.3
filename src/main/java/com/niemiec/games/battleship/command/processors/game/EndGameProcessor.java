package com.niemiec.games.battleship.command.processors.game;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.game.logic.BorderManagement;
import com.niemiec.games.battleship.messages.BattleshipGame;
import com.niemiec.games.battleship.view.BattleshipView;

import javafx.application.Platform;

public class EndGameProcessor {
	private BattleshipGamesManager battleshipGamesManager;
	private BattleshipGame battleshipGame;

	public EndGameProcessor(ChatData chatData) {
		battleshipGamesManager = chatData.getBattleshipGamesManager();
	}

	public void receiveTheObject(Object object) {
		updateBattleshipGame(object);
		updateBorders();
		updateBattleshipGameInManager();
		setBordersToTheEnd();
		speficyResignationOrEndOfTheGame();
	}
	
	private void speficyResignationOrEndOfTheGame() {
		if (giveUp()) {
			opponentGiveUp();
		} else {
			theGameIsOver();
		}
	}

	private void theGameIsOver() {
		BattleshipView battleshipView = battleshipGamesManager.getBattleshipView(battleshipGame);
		String text = "Bitwę wygrał gracz " + battleshipGame.getWinnerNick() + ".";
		battleshipView.getWindowOfTEndGameInformationAndAcceptance().show(text);
	}

	private void opponentGiveUp() {
		BattleshipView battleshipView = battleshipGamesManager.getBattleshipView(battleshipGame);
		String text = "Gracz " + battleshipGame.getOpponentPlayerNick() + " opuścił grę.";
		battleshipView.getWindowOfTEndGameInformationAndAcceptance().show(text);
	}

	private boolean giveUp() {
		return battleshipGame.getGameStatus() == BattleshipGame.GIVE_UP;
	}

	private void setBordersToTheEnd() {
		battleshipGamesManager.getBorderManagement(battleshipGame).setBordersToEndGame();
	}

	private void updateBattleshipGameInManager() {
		battleshipGamesManager.updateBattleshipGame(battleshipGame);
	}

	private void updateBorders() {
		if (playerInBattleshipGameExist()) {
			drawInBorders();
		}
	}
	
	private boolean playerInBattleshipGameExist() {
		return battleshipGame.getPlayer() != null;
	}

	private void drawInBorders() {
		BorderManagement b = battleshipGamesManager.getBorderManagement(battleshipGame);
		Platform.runLater(() -> {
			b.drawBoardsInBordersDuringTheGame(battleshipGame.getPlayer());
		});
	}

	private void updateBattleshipGame(Object object) {
		battleshipGame = (BattleshipGame) object;
	}

}
