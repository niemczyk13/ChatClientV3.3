package com.niemiec.chat.dispatchers.messages.outgoing;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipOptionInterface;
import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.game.AcceptRejectionGame;
import com.niemiec.games.battleship.command.order.game.AnswerToTheGameProposal;
import com.niemiec.games.battleship.command.order.game.GiveUp;
import com.niemiec.games.battleship.command.order.game.ShipsAdder;
import com.niemiec.games.battleship.command.order.game.ShooterMovement;
import com.niemiec.games.battleship.command.processors.BattleshipProcessorData;
import com.niemiec.games.battleship.dispatchers.DispatcherOfBattleshipOption;
import com.niemiec.games.battleship.messages.BattleshipGame;

public class DispatcherOfOutgoingBattleshipMessage {
	private BattleshipProcessorData battleshipProcessorData;
	private DispatcherOfBattleshipOption dispatcherOfBattleshipOption;

	public DispatcherOfOutgoingBattleshipMessage(ChatData chatData) {
		battleshipProcessorData = chatData.getBattleshipProcessorData();
		dispatcherOfBattleshipOption = new DispatcherOfBattleshipOption(chatData);
	}

	public void setTheCommand(Object object) {
		if (object instanceof BattleshipGame) {
			battleshipProcessorData.getGameProposalProcessor().setTheCommand(object);
		} else if (object instanceof AnswerToTheGameProposal) {
			battleshipProcessorData.getAnswerToTheGameProposalProcessor().setTheCommand(object);
		} else if (object instanceof AcceptRejectionGame) {
			battleshipProcessorData.getAcceptRejectionGameProcessor().setTheCommand(object);
		} else if (object instanceof ShipsAdder) {
			battleshipProcessorData.getShipsAdderProcessor().setTheCommand(object);
		} else if (object instanceof ShooterMovement) {
			battleshipProcessorData.getShooterMovementProcessor().setTheCommand(object);
		} else if (object instanceof BattleshipOptionInterface) {
			dispatcherOfBattleshipOption.setTheCommand(object);
		} else if (object instanceof GiveUp) {
			battleshipProcessorData.getGiveUpProcessor().setTheCommand(object);
		}
	}
}
