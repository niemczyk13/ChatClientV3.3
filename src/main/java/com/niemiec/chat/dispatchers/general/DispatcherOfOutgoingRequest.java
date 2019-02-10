package com.niemiec.chat.dispatchers.general;

import com.niemiec.chat.command.type.messages.Message;
import com.niemiec.chat.command.type.options.Option;
import com.niemiec.chat.data.ChatData;
import com.niemiec.chat.dispatchers.messages.outgoing.DispatcherOfOutgoingMessages;
import com.niemiec.chat.dispatchers.options.DispatcherOfOptions;

public class DispatcherOfOutgoingRequest {
	private DispatcherOfOutgoingMessages dispatcherOfOutgoingMessages;
	private DispatcherOfOptions dispatcherOfOptions;

	public DispatcherOfOutgoingRequest(ChatData chatData) {
		dispatcherOfOutgoingMessages = new DispatcherOfOutgoingMessages(chatData);
		dispatcherOfOptions = new DispatcherOfOptions(chatData);
	}

	public void setTheCommand(Object object) {
		if (object instanceof Message) {
			dispatcherOfOutgoingMessages.setTheCommand(object);
		} else if (object instanceof Option) {
			dispatcherOfOptions.setTheCommand(object);
		}
	}

}
