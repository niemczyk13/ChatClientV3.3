package com.niemiec.chat.dispatchers.options;

import com.niemiec.chat.command.order.options.generalchat.CancelExit;
import com.niemiec.chat.command.order.options.generalchat.ExitGeneralChat;
import com.niemiec.chat.command.order.options.generalchat.UpdaterActualInterlocutor;
import com.niemiec.chat.command.processors.options.data.OptionChatProcessorData;
import com.niemiec.chat.data.ChatData;

public class DispatcherOfGeneralChatOptions {
	private OptionChatProcessorData optionChatData;

	public DispatcherOfGeneralChatOptions(ChatData chatData) {
		optionChatData = chatData.getOptionChatData();
	}

	public void setTheCommand(Object object) {
		if (object instanceof UpdaterActualInterlocutor) {
			optionChatData.getUpdaterOpponentPlayerNickProcessor().setTheCommandUpdatePlayerNick(object);
		} else if (object instanceof ExitGeneralChat) {
			optionChatData.getExitGeneralChatProcessor().setTheCommand(object);
		} else if (object instanceof CancelExit) {
			optionChatData.getCancelExitProcessor().setTheCommand(object);
		}
	}

}
