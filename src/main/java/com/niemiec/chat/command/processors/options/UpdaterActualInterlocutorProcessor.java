package com.niemiec.chat.command.processors.options;

import com.niemiec.chat.command.order.options.generalchat.UpdaterActualInterlocutor;
import com.niemiec.chat.data.ChatData;
import com.niemiec.chat.messages.data.privatechat.InterlocutorsManager;
import com.niemiec.chat.view.GeneralChatView;

public class UpdaterActualInterlocutorProcessor {
	private ChatData chatData;
	private UpdaterActualInterlocutor updaterActualInterlocutor;
	private String selectedNick;
	private GeneralChatView generalChatView;
	private InterlocutorsManager interlocutorsManager;

	public UpdaterActualInterlocutorProcessor(ChatData chatData) {
		this.chatData = chatData;
		this.generalChatView = chatData.getChatView().getGeneralChatView();
		this.interlocutorsManager = chatData.getInterlocutorsManager();
	}

	public void setTheCommandUpdatePlayerNick(Object object) {
		updateUpdaterActualInterlocutor(object);
		if (selectedNickIsNotNull()) {
			updatePrivateChatView();
		}
	}

	private void updatePrivateChatView() {
		if (selectedNickIsYour()) {
			lockPrivateChat();
		} else {
			viewPrivateChatMessage();
		}
	}

	private void viewPrivateChatMessage() {
		updateInterlocutor();
		updatePrivateChat();
		unlockPrivateChat();
	}

	private boolean selectedNickIsNotNull() {
		return updaterActualInterlocutor.getActualInterlocutor() != null;
	}

	private void updatePrivateChat() {
		generalChatView.updatePrivateChatListView(interlocutorsManager.getMessages(selectedNick));
	}

	private void unlockPrivateChat() {
		generalChatView.unlockPrivateChat();
	}

	private void updateInterlocutor() {
		chatData.setActualInterlocutor(selectedNick);
		interlocutorsManager.addInterlocutor(selectedNick);
		interlocutorsManager.setMessageIsRead(selectedNick, true);
	}

	private void lockPrivateChat() {
		chatData.setActualInterlocutor("");
		generalChatView.lockPrivateChat();
	}

	private boolean selectedNickIsYour() {
		return selectedNick.equals(chatData.getNick());
	}

	private void updateUpdaterActualInterlocutor(Object object) {
		updaterActualInterlocutor = (UpdaterActualInterlocutor) object;
		selectedNick = updaterActualInterlocutor.getActualInterlocutor();
	}

}
