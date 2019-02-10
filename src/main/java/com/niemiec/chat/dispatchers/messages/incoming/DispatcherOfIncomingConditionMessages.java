package com.niemiec.chat.dispatchers.messages.incoming;

import com.niemiec.chat.command.order.messages.condition.CheckNickMessage;
import com.niemiec.chat.command.order.messages.condition.UsersListMessage;
import com.niemiec.chat.command.processors.messages.data.MessageProcessorsData;
import com.niemiec.chat.data.ChatData;

public class DispatcherOfIncomingConditionMessages {
	private MessageProcessorsData messageProcessorsData;
	
	public DispatcherOfIncomingConditionMessages(ChatData chatData) {
		this.messageProcessorsData = chatData.getMessageProcessorsData();
	}

	public void receiveTheObject(Object object) {
		if (object instanceof CheckNickMessage ) {
			messageProcessorsData.getCheckNickMessageProcessor().receiveTheCheckNickMessage(object);
		} else if (object instanceof UsersListMessage) {

			messageProcessorsData.getUsersListMessageProcessor().receiveTheUsersListMessage(object);
		}
	}

}
