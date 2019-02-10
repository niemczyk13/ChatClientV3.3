package com.niemiec.chat.main;

import com.niemiec.chat.connection.Connection;
import com.niemiec.chat.data.ChatData;
import com.niemiec.chat.dispatchers.general.DispatcherOfIncomingRequest;
import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.chat.view.ChatView;

public class Chat {
	private Connection connection;
	private ChatData chatData;
	private ChatView chatView;
	private DispatcherOfIncomingRequest dispatcherOfIncomingRequest;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	public Chat() {
		chatData = new ChatData();
		createDispatcherClasses();
		createConnection();
		
		chatView = new ChatView(dispatcherOfOutgoingRequest);
		chatData.setChatView(chatView);
		chatData.setConnection(connection);
		chatData.setDispatcherOfOutgoingRequest(dispatcherOfOutgoingRequest);
	}
	
	private void createConnection() {
		connection = new Connection();
		connection.setDispatcherOfIncomingMessages(dispatcherOfIncomingRequest);
		connection.makeTheConnection("localhost", 6666);
	}

	private void createDispatcherClasses() {
		dispatcherOfIncomingRequest = new DispatcherOfIncomingRequest(chatData);
		dispatcherOfOutgoingRequest = new DispatcherOfOutgoingRequest(chatData);
	}

	public void runChat() {
		chatView.getGetNickWindow().show();
	}
}
