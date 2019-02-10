package com.niemiec.chat.command.processors.messages;

import com.niemiec.chat.command.order.messages.text.PrivateMessage;
import com.niemiec.chat.data.ChatData;
import com.niemiec.chat.messages.data.privatechat.InterlocutorsManager;

public class PrivateMessageProcessor {
	private ChatData chatData;
	private InterlocutorsManager interlocutorsManager;
	private PrivateMessage privateMessage;
	private String senderNick;
	private String recipientNick;
	private String message;

	public PrivateMessageProcessor(ChatData chatData) {
		this.chatData = chatData;
		this.interlocutorsManager = chatData.getInterlocutorsManager();
	}

	public void setTheCommand(Object object) {
		updatePrivateMessage(object);
		addDataToPrivateMessage();
		addDataToLocalVariablesWhenSend();
		addMessageToPrivateChat();
		addMessageToInterlocutorWhenSend();
		sendThePrivateMessage();
		clearTextAreaInPrivateChat();
	}

	public void receiveThePrivateMessage(Object object) {
		updatePrivateMessage(object);
		addInterlocutorIfNotExist();
		addDataToLocalVariablesWhenReceive();
		addMessageToInterlocutorWhenReceive();
		viewTheReceiveMessage();
	}

	private void viewTheReceiveMessage() {
		if (senderNickIsActualInterlocutor()) {
			setMessageIsRead(true);
			addMessageToPrivateChat();
		} else {
			// TODO podświetlanie pola
			setMessageIsRead(false);
		}
	}

	private void updatePrivateMessage(Object object) {
		privateMessage = (PrivateMessage) object;
	}

	private void addDataToPrivateMessage() {
		privateMessage.setSenderNick(chatData.getNick());
		privateMessage.setRecipientNick(chatData.getActualInterlocutor());
	}

	private void addDataToLocalVariablesWhenSend() {
		senderNick = chatData.getNick();
		recipientNick = chatData.getActualInterlocutor();
		message = createMessageForShow();
	}

	private void addMessageToPrivateChat() {
		chatData.getChatView().getGeneralChatView().addMessageToPrivateChat(message);
	}

	private void addMessageToInterlocutorWhenSend() {
		interlocutorsManager.addMessage(recipientNick, message);
	}

	private void sendThePrivateMessage() {
		chatData.sendTheObject(privateMessage);
	}

	private void clearTextAreaInPrivateChat() {
		chatData.getChatView().getGeneralChatView().clearTextAreaPrivateChat();
	}

	private void setMessageIsRead(boolean isRead) {
		interlocutorsManager.setMessageIsRead(senderNick, isRead);
	}

	private boolean senderNickIsActualInterlocutor() {
		return senderNick.equals(chatData.getActualInterlocutor());
	}

	private void addMessageToInterlocutorWhenReceive() {
		interlocutorsManager.addMessage(senderNick, message);
	}

	private void addDataToLocalVariablesWhenReceive() {
		senderNick = privateMessage.getSenderNick();
		recipientNick = privateMessage.getRecipientNick();
		message = createMessageForShow();
	}

	private void addInterlocutorIfNotExist() {
		interlocutorsManager.addInterlocutor(privateMessage.getSenderNick());
	}

	private String createMessageForShow() {
		String front = ((senderNick.equals(chatData.getNick())) ? "TY" : senderNick);
		return new String(front + "> " + privateMessage.getMessage());
	}
}
