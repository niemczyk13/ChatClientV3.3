package com.niemiec.chat.dispatchers.messages.incoming;

import com.niemiec.chat.command.type.messages.condition.ConditionMessage;
import com.niemiec.chat.command.type.messages.game.GameMessage;
import com.niemiec.chat.command.type.messages.text.TextMessage;
import com.niemiec.chat.data.ChatData;

public class DispatcherOfIncomingMessages {
	private DispatcherOfIncomingTextMessages dispatcherOfIncomingTextMessages;
	private DispatcherOfIncomingConditionMessages dispatcherOfIncomingConditionMessages;
	private DispatcherOfIncomingGameMessage dispatcherOfIncomingGameMessage;

	public DispatcherOfIncomingMessages(ChatData chatData) {
		dispatcherOfIncomingTextMessages = new DispatcherOfIncomingTextMessages(chatData);
		dispatcherOfIncomingConditionMessages = new DispatcherOfIncomingConditionMessages(chatData);
		dispatcherOfIncomingGameMessage = new DispatcherOfIncomingGameMessage(chatData);
	}

	public void receiveTheObject(Object object) {
		if (object instanceof TextMessage) {
			dispatcherOfIncomingTextMessages.receiveTheObject(object);
		} else if (object instanceof GameMessage) {
			dispatcherOfIncomingGameMessage.receiveTheObject(object);
		} else if (object instanceof ConditionMessage) {
			dispatcherOfIncomingConditionMessages.receiveTheObject(object);
		}
	}
}
