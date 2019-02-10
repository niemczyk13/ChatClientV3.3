package com.niemiec.chat.connection;

import java.io.IOException;
import java.net.Socket;

import com.niemiec.chat.dispatchers.general.DispatcherOfIncomingRequest;

public class Connection extends Thread {
	private DispatcherOfIncomingRequest dispatcherOfIncomingRequest;
	private Socket socket;
	private boolean isConnected;
	private InputOutputStream inputOutputStream;

	public Connection() {
		this.isConnected = false;
	}

	public void run() {
		Object object = null;
		while (true) {
			object = inputOutputStream.receiveTheObject();
			dispatcherOfIncomingRequest.receiveTheObject(object);
		}
	}

	public void makeTheConnection(String host, int port) {
		if (!isConnected) {
			createConnection(host, port);
			createInputOutputStream();
			start();
		}
	}

	private void createConnection(String host, int port) {
		socket = null;
		try {
			socket = new Socket(host, port);
			isConnected = true;
		} catch (Exception e) {
			System.out.println("Błąd tworzenia połączenia: " + e);
		}
	}

	private void createInputOutputStream() {
		inputOutputStream = new InputOutputStream(socket);
	}

	@SuppressWarnings("deprecation")
	public void interrupt() {
		this.stop();
		inputOutputStream.closeInputOutputStream();
		super.interrupt();
		try {
			socket.close();
			isConnected = false;
		} catch (IOException e) {
		}
	}

	public void sendTheObject(Object object) {
		inputOutputStream.sendTheObject(object);
	}

	public void setDispatcherOfIncomingMessages(DispatcherOfIncomingRequest dispatcherOfIncomingRequest) {
		this.dispatcherOfIncomingRequest = dispatcherOfIncomingRequest;
	}
}
