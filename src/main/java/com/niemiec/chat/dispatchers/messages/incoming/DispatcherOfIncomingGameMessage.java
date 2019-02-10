package com.niemiec.chat.dispatchers.messages.incoming;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipGameInterface;
import com.niemiec.chat.data.ChatData;

public class DispatcherOfIncomingGameMessage {
	private DispatcherOfIncomingBattleshipMessage dispatcherOfIncomingBattleshipMessage;

	public DispatcherOfIncomingGameMessage(ChatData chatData) {
		dispatcherOfIncomingBattleshipMessage = new DispatcherOfIncomingBattleshipMessage(chatData);
	}

	public void receiveTheObject(Object object) {
		if (object instanceof BattleshipGameInterface) {
			dispatcherOfIncomingBattleshipMessage.receiveTheObject(object);
		}
	}

}
