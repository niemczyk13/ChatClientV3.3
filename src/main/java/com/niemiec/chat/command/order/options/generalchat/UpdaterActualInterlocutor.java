package com.niemiec.chat.command.order.options.generalchat;

import com.niemiec.chat.command.type.options.generalchat.GeneralChatOption;

public class UpdaterActualInterlocutor implements GeneralChatOption {
	private String actualInterlocutor;

	public UpdaterActualInterlocutor(String actualInterlocutor) {
		this.actualInterlocutor = actualInterlocutor;
	}

	public String getActualInterlocutor() {
		return actualInterlocutor;
	}
}
