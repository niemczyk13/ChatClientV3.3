package com.niemiec.chat.dispatchers.messages.outgoing;

import com.niemiec.chat.command.type.messages.game.battleship.BattleshipGameInterface;
import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.dispatchers.DispatcherOfOutgoingBattleshipMessage;

public class DispatcherOfOutgoingGameMessages {
	private DispatcherOfOutgoingBattleshipMessage dispatcherOfOutgoingBattleshipMessage;
	
	public DispatcherOfOutgoingGameMessages(ChatData chatData) {
		dispatcherOfOutgoingBattleshipMessage = new DispatcherOfOutgoingBattleshipMessage(chatData);
	}

	public void setTheCommand(Object object) {
		if (object instanceof BattleshipGameInterface) {
			dispatcherOfOutgoingBattleshipMessage.setTheCommand(object);
		}
	}

}
