package com.niemiec.chat.command.processors.messages;

import com.niemiec.chat.command.order.messages.text.GroupMessage;
import com.niemiec.chat.data.ChatData;

public class GroupChatMessageProcessor {
	private ChatData chatData;
	private GroupMessage groupMessage;
	private String senderNick;
	private String message;

	public GroupChatMessageProcessor(ChatData chatData) {
		this.chatData = chatData;
	}

	public void setTheCommand(Object object) {
		updateGroupMessage(object);
		updateSenderNickInGroupMessage();
		sendTheGroupMessage();
		clearTextAreaInGeneralChat();
	}

	public void receiveTheGroupMessage(Object object) {
		updateGroupMessage(object);
		updateLocalVariableFromGroupMessage();
		addMessageToGeneralChat();
		viewTheReceiveMessage();
	}

	private void viewTheReceiveMessage() {
		chatData.getChatView().getGeneralChatView().addMessageToGeneralChat(message);
	}

	private void addMessageToGeneralChat() {
		chatData.addMessageToGeneralChat(message);
	}

	private void updateLocalVariableFromGroupMessage() {
		senderNick = groupMessage.getSenderNick();
		message = createMessageForShow();
	}

	private void clearTextAreaInGeneralChat() {
		chatData.getChatView().getGeneralChatView().clearTextAreaGeneralChat();
	}

	private void sendTheGroupMessage() {
		chatData.sendTheObject(groupMessage);
	}

	private void updateSenderNickInGroupMessage() {
		groupMessage.setSenderNick(chatData.getNick());
	}

	private void updateGroupMessage(Object object) {
		groupMessage = (GroupMessage) object;
	}

	private String createMessageForShow() {
		String userNick = chatData.getNick();
		String front = ((userNick.equals(senderNick)) ? "TY" : senderNick);
		String message = groupMessage.getMessage();
		return new String(front + "> " + message);
	}
}
