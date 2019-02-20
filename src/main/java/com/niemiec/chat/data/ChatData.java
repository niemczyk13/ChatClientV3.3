package com.niemiec.chat.data;

import com.niemiec.chat.command.processors.messages.data.MessageProcessorsData;
import com.niemiec.chat.command.processors.options.data.OptionChatProcessorData;
import com.niemiec.chat.connection.Connection;
import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.chat.messages.data.generalchat.GeneralChat;
import com.niemiec.chat.messages.data.privatechat.InterlocutorsManager;
import com.niemiec.chat.view.ChatView;
import com.niemiec.games.battleship.command.processors.BattleshipProcessorData;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.data.BattleshipGameWithComputer;

public class ChatData {
	private Connection connection;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private ChatView chatView;
	private MessageProcessorsData messageProcessorsData;
	private OptionChatProcessorData optionChatData;
	private InterlocutorsManager interlocutorsManager;
	private GeneralChat generalChat;
	private String nick;
	private String actualInterlocutor;
	private GamesData gamesData;

	public ChatData() {
		messageProcessorsData = new MessageProcessorsData(this);
		optionChatData = new OptionChatProcessorData(this);
		interlocutorsManager = new InterlocutorsManager();
		generalChat = new GeneralChat();
		gamesData = new GamesData(this);
	}

	public void setChatView(ChatView chatView) {
		this.chatView = chatView;
	}

	public ChatView getChatView() {
		return chatView;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void sendTheObject(Object object) {
		connection.sendTheObject(object);
	}

	public MessageProcessorsData getMessageProcessorsData() {
		return messageProcessorsData;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNick() {
		return nick;
	}
	
	public void addMessageToGeneralChat(String message) {
		generalChat.addMessage(message);
	}
	
	public OptionChatProcessorData getOptionChatData() {
		return optionChatData;
	}

	public void setActualInterlocutor(String actualInterlocutor) {
		this.actualInterlocutor = actualInterlocutor;
	}

	public String getActualInterlocutor() {
		return actualInterlocutor;
	}

	public InterlocutorsManager getInterlocutorsManager() {
		return interlocutorsManager;
	}
	
	public void interruptConnection() {
		connection.interrupt();
	}
	
	public BattleshipGamesManager getBattleshipGamesManager() {
		return gamesData.getBattleshipData().getBattleshipGamesManager();
	}
	
	public BattleshipProcessorData getBattleshipProcessorData() {
		return gamesData.getBattleshipData().getBattleshipProcessorData();
	}

	public void setDispatcherOfOutgoingRequest(DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}

	public DispatcherOfOutgoingRequest getDispatcherOfOutgoingRequest() {
		return dispatcherOfOutgoingRequest;
	}
	
	public BattleshipGameWithComputer getBattleshipGameWithComputer() {
		return gamesData.getBattleshipData().getBattleshipGameWithComputer();
	}
}