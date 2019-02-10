package com.niemiec.chat.command.processors.options;

import com.niemiec.chat.data.ChatData;
import com.niemiec.chat.view.ChatView;

public class CancelExitProcessor {
	private ChatView chatView;

	public CancelExitProcessor(ChatData chatData) {
		chatView = chatData.getChatView();
	}

	public void setTheCommand(Object object) {
		chatView.getExitInformationAndAcceptanceView().close();
		chatView.getGeneralChatView().unblockAllChat();
	}

}
