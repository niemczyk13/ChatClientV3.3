package com.niemiec.chat.command.processors.options;

import com.niemiec.chat.data.ChatData;
import com.niemiec.chat.view.ChatView;

public class ExitGeneralChatProcessor {
	private ChatView chatView;

	public ExitGeneralChatProcessor(ChatData chatData) {
		chatView = chatData.getChatView();
	}

	public void setTheCommand(Object object) {
		chatView.getGeneralChatView().blockAllChat();
		chatView.getExitInformationAndAcceptanceView().show();
	}

}
