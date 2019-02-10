package com.niemiec.chat.command.processors.messages.data;

import com.niemiec.chat.command.processors.messages.CheckNickMessageProcessor;
import com.niemiec.chat.command.processors.messages.ExitMessageProcessor;
import com.niemiec.chat.command.processors.messages.GroupChatMessageProcessor;
import com.niemiec.chat.command.processors.messages.PrivateMessageProcessor;
import com.niemiec.chat.command.processors.messages.UsersListMessageProcessor;
import com.niemiec.chat.data.ChatData;

public class MessageProcessorsData {
	private ChatData chatData;
	private CheckNickMessageProcessor checkNickMessageProcessor;
	private GroupChatMessageProcessor groupChatMessageProcessor;
	private UsersListMessageProcessor usersListMessageProcessor;
	private PrivateMessageProcessor privateMessageProcessor;
	private ExitMessageProcessor exitMessageProcessor;

	public MessageProcessorsData(ChatData chatData) {
		this.chatData = chatData;
	}

	public CheckNickMessageProcessor getCheckNickMessageProcessor() {
		if (checkNickMessageProcessor == null) {
			checkNickMessageProcessor = new CheckNickMessageProcessor(chatData);
		}
		return checkNickMessageProcessor;
	}

	public GroupChatMessageProcessor getGroupChatMessageProcessor() {
		if (groupChatMessageProcessor == null) {
			groupChatMessageProcessor = new GroupChatMessageProcessor(chatData);
		}
		return groupChatMessageProcessor;
	}

	public UsersListMessageProcessor getUsersListMessageProcessor() {
		if (usersListMessageProcessor == null) {
			usersListMessageProcessor = new UsersListMessageProcessor(chatData);
		}
		return usersListMessageProcessor;
	}

	public PrivateMessageProcessor getPrivateMessageProcessor() {
		if (privateMessageProcessor == null) {
			privateMessageProcessor = new PrivateMessageProcessor(chatData);
		}
		return privateMessageProcessor;
	}

	public ExitMessageProcessor getExitMessageProcessor() {
		if (exitMessageProcessor == null) {
			exitMessageProcessor = new ExitMessageProcessor(chatData);
		}
		return exitMessageProcessor;
	}
	
	

}
