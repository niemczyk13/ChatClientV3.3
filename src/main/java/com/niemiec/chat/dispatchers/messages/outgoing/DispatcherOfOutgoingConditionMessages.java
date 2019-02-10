package com.niemiec.chat.dispatchers.messages.outgoing;

import com.niemiec.chat.command.order.messages.condition.CheckNickMessage;
import com.niemiec.chat.command.order.messages.condition.ExitMessage;
import com.niemiec.chat.command.processors.messages.data.MessageProcessorsData;
import com.niemiec.chat.data.ChatData;

public class DispatcherOfOutgoingConditionMessages {
	private MessageProcessorsData messageProcessorsData;
	
	public DispatcherOfOutgoingConditionMessages(ChatData chatData) {
		this.messageProcessorsData = chatData.getMessageProcessorsData();
	}

	public void setTheCommand(Object object) {
		if (object instanceof CheckNickMessage) {
			messageProcessorsData.getCheckNickMessageProcessor().setTheCommandCheckNick(object);
		} else if (object instanceof ExitMessage) {
			messageProcessorsData.getExitMessageProcessor().setTheCommandExit(object);
		}
	}
}
