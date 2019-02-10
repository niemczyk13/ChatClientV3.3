package com.niemiec.chat.dispatchers.general;

import com.niemiec.chat.command.type.messages.Message;
import com.niemiec.chat.data.ChatData;
import com.niemiec.chat.dispatchers.messages.incoming.DispatcherOfIncomingMessages;

public class DispatcherOfIncomingRequest {
	private DispatcherOfIncomingMessages dispatcherOfIncomingMessages;

	public DispatcherOfIncomingRequest(ChatData chatData) {
		dispatcherOfIncomingMessages = new DispatcherOfIncomingMessages(chatData);
	}

	public void receiveTheObject(Object object) {
		if (object instanceof Message) {
			dispatcherOfIncomingMessages.receiveTheObject(object);
		}
	}

}
