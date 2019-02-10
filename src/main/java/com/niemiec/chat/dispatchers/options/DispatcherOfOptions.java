package com.niemiec.chat.dispatchers.options;

import com.niemiec.chat.command.type.options.generalchat.GeneralChatOption;
import com.niemiec.chat.data.ChatData;

public class DispatcherOfOptions {
	private DispatcherOfGeneralChatOptions dispatcherOfGeneralChatOptions;

	public DispatcherOfOptions(ChatData chatData) {
		dispatcherOfGeneralChatOptions = new DispatcherOfGeneralChatOptions(chatData);
	}

	public void setTheCommand(Object object) {
		if (object instanceof GeneralChatOption) {
			dispatcherOfGeneralChatOptions.setTheCommand(object);
		}
	}

}
