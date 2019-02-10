package com.niemiec.chat.command.processors.messages;

import java.util.regex.Pattern;

import com.niemiec.chat.command.order.messages.condition.CheckNickMessage;
import com.niemiec.chat.command.order.messages.condition.ReadyToWorkMessage;
import com.niemiec.chat.data.ChatData;

public class CheckNickMessageProcessor {
	private ChatData chatData;
	private CheckNickMessage checkNickMessage;

	public CheckNickMessageProcessor(ChatData chatData) {
		this.chatData = chatData;
	}

	public void setTheCommandCheckNick(Object object) {
		updateCheckNickMessage(object);
		
		if (checkThatNickIsGood()) {
			sendCheckNickMessage();
		} else {
			viewInformationThatTheNickIsBad();
		}
	}

	private boolean checkThatNickIsGood() {
		if (nickIsNotNull()) {
			return correctnessOfCharactersIsGood();
		}
		return false;
	}

	private void viewInformationThatTheNickIsBad() {
		chatData.getChatView().getGetNickWindow().viewInformation("Błędny nick");
	}

	private void sendCheckNickMessage() {
		chatData.sendTheObject(checkNickMessage);
	}

	private boolean nickIsNotNull() {
		return checkNickMessage.getNick() != null;
	}

	private boolean correctnessOfCharactersIsGood() {
		return Pattern.matches("[a-zA-Z]{1}[a-zA-Z0-9]{2,14}", checkNickMessage.getNick());
	}

	public void receiveTheCheckNickMessage(Object object) {
		updateCheckNickMessage(object);
		if (!checkNickMessage.isNickNotExist()) {
			chatData.getChatView().getGetNickWindow().viewInformation("Wybrany przez Ciebie nick jest już zajęty");
		} else {
			chatData.getChatView().getGetNickWindow().close();
			chatData.setNick(checkNickMessage.getNick());
			chatData.getChatView().getGeneralChatView().show();
			chatData.sendTheObject(new ReadyToWorkMessage(chatData.getNick()));
		}
	}

	private void updateCheckNickMessage(Object object) {
		checkNickMessage = (CheckNickMessage) object;
	}

}
