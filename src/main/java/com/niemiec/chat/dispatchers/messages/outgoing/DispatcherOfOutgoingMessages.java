package com.niemiec.chat.dispatchers.messages.outgoing;

import com.niemiec.chat.command.type.messages.condition.ConditionMessage;
import com.niemiec.chat.command.type.messages.game.GameMessage;
import com.niemiec.chat.command.type.messages.text.TextMessage;
import com.niemiec.chat.data.ChatData;

public class DispatcherOfOutgoingMessages {
	private DispatcherOfOutgoingTextMessages dispatcherOfOutgoingTextMessages;
	private DispatcherOfOutgoingGameMessages dispatcherOfOutgoingGameMessages;
	private DispatcherOfOutgoingConditionMessages dispatcherOfOutgoingConditionMessages;

	public DispatcherOfOutgoingMessages(ChatData chatData) {
		dispatcherOfOutgoingTextMessages = new DispatcherOfOutgoingTextMessages(chatData);
		dispatcherOfOutgoingGameMessages = new DispatcherOfOutgoingGameMessages(chatData);
		dispatcherOfOutgoingConditionMessages = new DispatcherOfOutgoingConditionMessages(chatData);
	}

	public void setTheCommand(Object object) {
		if (object instanceof TextMessage) {
			dispatcherOfOutgoingTextMessages.setTheCommand(object);
		} else if (object instanceof GameMessage) {
			dispatcherOfOutgoingGameMessages.setTheCommand(object);
		} else if (object instanceof ConditionMessage) {
			dispatcherOfOutgoingConditionMessages.setTheCommand(object);
		}
	}
}
