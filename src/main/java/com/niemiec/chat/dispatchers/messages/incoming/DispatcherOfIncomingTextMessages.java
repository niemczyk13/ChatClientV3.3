package com.niemiec.chat.dispatchers.messages.incoming;

import com.niemiec.chat.command.order.messages.text.GroupMessage;
import com.niemiec.chat.command.order.messages.text.PrivateMessage;
import com.niemiec.chat.command.processors.messages.data.MessageProcessorsData;
import com.niemiec.chat.data.ChatData;

public class DispatcherOfIncomingTextMessages {
	private MessageProcessorsData messageProcessorsData;

	public DispatcherOfIncomingTextMessages(ChatData chatData) {
		this.messageProcessorsData = chatData.getMessageProcessorsData();
	}

	public void receiveTheObject(Object object) {
		if (object instanceof GroupMessage) {
			messageProcessorsData.getGroupChatMessageProcessor().receiveTheGroupMessage(object);
		} else if (object instanceof PrivateMessage) {
			messageProcessorsData.getPrivateMessageProcessor().receiveThePrivateMessage(object);
		}
	}
}
