package com.niemiec.chat.view;

import java.io.IOException;
import java.util.ArrayList;

import com.niemiec.chat.controllers.GeneralChatController;
import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GeneralChatView {
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private GeneralChatController generalChatController;
	private FXMLLoader loader;
	private HBox hbox;
	private Stage stage;

	public GeneralChatView(DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}

	public void show() {
		Platform.runLater(() -> {
			loadFXMLLoader();
			updateGetNickController();
			view();
			addCloseOption();
		});
	}

	private void loadFXMLLoader() {
		loader = new FXMLLoader(this.getClass().getResource("/fxml/ChatWindow.fxml"));
		try {
			hbox = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		generalChatController = loader.getController();
	}

	private void updateGetNickController() {
		generalChatController.setDisDispatcherOfOutgoingMessages(dispatcherOfOutgoingRequest);
	}

	private void view() {
		Scene scene = new Scene(hbox);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Chat");
		stage.sizeToScene();
		stage.show();
	}

	private void addCloseOption() {
		stage.setOnCloseRequest(e -> generalChatController.exit());
	}

	public void close() {
		Platform.runLater(() -> {
			stage.close();
		});
	}

	public void addMessageToGeneralChat(String message) {
		generalChatController.addMessageToGeneralChat(message);
	}

	public void clearTextAreaGeneralChat() {
		generalChatController.getTextAreaGeneralChat().clear();
	}

	public void updateUsersList(ArrayList<String> usersArrayList) {
		Platform.runLater(() -> {
			generalChatController.updateUsersList(usersArrayList);
		});

	}

	public void addMessageToPrivateChat(String message) {
		Platform.runLater(() -> {
			generalChatController.addMessageToPrivateChat(message);
		});
	}

	public void lockPrivateChat() {
		Platform.runLater(() -> {
			generalChatController.lockPrivateChat();
		});
	}

	public void unlockPrivateChat() {
		Platform.runLater(() -> {
			generalChatController.unlockPrivateChat();
		});
	}

	public void updatePrivateChatListView(ArrayList<String> messages) {
		Platform.runLater(() -> {
			generalChatController.updatePrivateChat(messages);
		});
	}

	public void clearTextAreaPrivateChat() {
		generalChatController.getTextAreaPrivateChat().clear();
	}

	public void blockAllChat() {
		generalChatController.blockAllChat();
	}

	public void unblockAllChat() {
		generalChatController.unblockAllChat();
	}
}