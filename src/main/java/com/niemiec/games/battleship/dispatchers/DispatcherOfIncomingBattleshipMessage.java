package com.niemiec.games.battleship.dispatchers;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.processors.BattleshipProcessorData;
import com.niemiec.games.battleship.messages.BattleshipGame;

public class DispatcherOfIncomingBattleshipMessage {
	private BattleshipProcessorData battleshipProcessorData;
	private BattleshipGame battleshipGame;

	public DispatcherOfIncomingBattleshipMessage(ChatData chatData) {
		battleshipProcessorData = chatData.getBattleshipProcessorData();
	}

	public void receiveTheObject(Object object) {
		updateBattleshipGame(object);
		switch (battleshipGame.getGameStatus()) {
		case BattleshipGame.GAME_PROPOSAL:
			battleshipProcessorData.getGameProposalProcessor().receiveTheObject(object);
			break;
		case BattleshipGame.REJECTION_GAME_PROPOSAL:
			battleshipProcessorData.getRejectionGameProposalProcessor().receiveTheObject(object);
			break;
		case BattleshipGame.ADD_SHIPS:
			battleshipProcessorData.getShipsAdderProcessor().receiveTheObject(object);
			break;
		case BattleshipGame.PLAY_THE_GAME:
			battleshipProcessorData.getShooterMovementProcessor().receiveTheObject(object);
			break;
		case BattleshipGame.GIVE_UP:
			battleshipProcessorData.getEndGameProcessor().receiveTheObject(object);
			break;
		case BattleshipGame.END_GAME:
			battleshipProcessorData.getEndGameProcessor().receiveTheObject(object);
			break;
		}
	}

	private void updateBattleshipGame(Object object) {
		battleshipGame = (BattleshipGame) object;
	}

}
