package com.niemiec.chat.dispatchers.messages.outgoing;

import com.niemiec.chat.command.order.messages.text.GroupMessage;
import com.niemiec.chat.command.order.messages.text.PrivateMessage;
import com.niemiec.chat.command.processors.messages.data.MessageProcessorsData;
import com.niemiec.chat.data.ChatData;

public class DispatcherOfOutgoingTextMessages {
	private MessageProcessorsData messageProcessorsData;

	public DispatcherOfOutgoingTextMessages(ChatData chatData) {
		this.messageProcessorsData = chatData.getMessageProcessorsData();
	}

	public void setTheCommand(Object object) {
		if (object instanceof GroupMessage) {
			messageProcessorsData.getGroupChatMessageProcessor().setTheCommand(object);
		} else if (object instanceof PrivateMessage) {
			messageProcessorsData.getPrivateMessageProcessor().setTheCommand(object);
		}
	}

}
