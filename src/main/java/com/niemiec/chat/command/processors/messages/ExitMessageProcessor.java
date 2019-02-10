package com.niemiec.chat.command.processors.messages;

import com.niemiec.chat.command.order.messages.condition.ExitMessage;
import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.option.ExitAllBattleshipGames;

public class ExitMessageProcessor {
	private ChatData chatData;
	private ExitMessage exitMessage;

	public ExitMessageProcessor(ChatData chatData) {
		this.chatData = chatData;
	}

	public void setTheCommandExit(Object object) {
		updateExitMessage(object);
		exitMessage.setNick(chatData.getNick());
		chatData.getChatView().getGeneralChatView().close();
		closeAllGames();
		chatData.sendTheObject(exitMessage);
		chatData.interruptConnection();
		System.exit(0);
	}

	private void closeAllGames() {
		chatData.getDispatcherOfOutgoingRequest().setTheCommand(new ExitAllBattleshipGames());
	}

	private void updateExitMessage(Object object) {
		exitMessage = (ExitMessage) object;
	}

}
