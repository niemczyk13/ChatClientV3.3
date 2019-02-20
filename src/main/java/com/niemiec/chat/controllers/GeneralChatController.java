package com.niemiec.chat.controllers;

import java.util.ArrayList;

import com.niemiec.chat.command.order.messages.text.GroupMessage;
import com.niemiec.chat.command.order.messages.text.PrivateMessage;
import com.niemiec.chat.command.order.options.generalchat.ExitGeneralChat;
import com.niemiec.chat.command.order.options.generalchat.UpdaterActualInterlocutor;
import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.command.order.game.PlayBattleshipOffline;
import com.niemiec.games.battleship.messages.BattleshipGame;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class GeneralChatController {
    @FXML
    private MenuBar manu;
    
	@FXML
	private HBox mainHBox;

	@FXML
	private ListView<String> generalChat;

	@FXML
	private TextField textAreaGeneralChat;

	@FXML
	private Button sendToGeneralChatButton;

	@FXML
	private Button suggestAGameButton;

	@FXML
	private ListView<String> listOfUsersChat;

	@FXML
	private ListView<String> privateChat;

	@FXML
	private TextField textAreaPrivateChat;

	@FXML
	private Button sendToPrivateChatButton;
	
    @FXML
    private MenuItem playBattleshipWithComputerButton;

	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private ObservableList<String> usersList;
	private ObservableList<String> privateList;

	@FXML
	void initialize() {
		usersList = FXCollections.observableArrayList();
		privateList = FXCollections.observableArrayList();
	}

	@FXML
	void getUserNick(MouseEvent event) {
		String userNickFromListView = listOfUsersChat.getSelectionModel().getSelectedItem();
		dispatcherOfOutgoingRequest.setTheCommand(new UpdaterActualInterlocutor(userNickFromListView));
	}

	@FXML
	void sendToGeneralChat() {
		dispatcherOfOutgoingRequest.setTheCommand(new GroupMessage(textAreaGeneralChat.getText()));
	}

	public TextField getTextAreaGeneralChat() {
		return textAreaGeneralChat;
	}

	@FXML
	void sendToGeneralChatAfterKeyPress(KeyEvent event) {
		if ("ENTER".equals(event.getCode().toString())) {
			sendToGeneralChat();
		}
	}

	@FXML
	void sendToPrivateChatAfterKeyPress(KeyEvent event) {
		if ("ENTER".equals(event.getCode().toString())) {
			sendToPrivateChat();
		}
	}

	@FXML
	void sendToPrivateChat() {
		dispatcherOfOutgoingRequest.setTheCommand(new PrivateMessage(textAreaPrivateChat.getText()));
	}

	@FXML
	void suggestAGame() {
		dispatcherOfOutgoingRequest.setTheCommand(new BattleshipGame());
	}

	public ArrayList<String> getUsersList(ObservableList<String> obs) {
		ArrayList<String> a = new ArrayList<>();
		for (int i = 0; i < obs.size(); i++) {
			a.add(obs.get(i));
		}

		return a;
	}

	public void updateUsersList(ArrayList<String> list) {
		Platform.runLater(() -> {
			listOfUsersChat.getItems().clear();
			this.usersList.addAll(list);
			listOfUsersChat.setItems(this.usersList);

		});
	}

	public void addMessageToGeneralChat(String message) {
		Platform.runLater(() -> {
			generalChat.getItems().add(message);
		});
	}

	public ObservableList<String> getUsersList() {
		return usersList;
	}

	public ListView<String> getListOfUsersChat() {
		return listOfUsersChat;
	}

	public void addMessageToPrivateChat(String message) {
		Platform.runLater(() -> {
			privateChat.getItems().add(message);
		});
	}

	public void updatePrivateChat(ArrayList<String> list) {
		if (!list.isEmpty()) {
			privateChat.getItems().clear();
			this.privateList.addAll(list);
			privateChat.setItems(this.privateList);
		}
	}

	public void lockPrivateChat() {
		privateChat.getItems().clear();
		sendToPrivateChatButton.setDisable(true);
		suggestAGameButton.setDisable(true);
	}

	public void unlockPrivateChat() {
		sendToPrivateChatButton.setDisable(false);
		suggestAGameButton.setDisable(false);
	}

	public void clearPrivateListView() {
		privateChat.getItems().clear();
	}

	@FXML
	public void exit() {
		dispatcherOfOutgoingRequest.setTheCommand(new ExitGeneralChat());
	}

	public void setDisDispatcherOfOutgoingMessages(DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}

	public TextField getTextAreaPrivateChat() {
		return textAreaPrivateChat;
	}
	
    @FXML
    void playBattleshipWithComputer() {
    	dispatcherOfOutgoingRequest.setTheCommand(new PlayBattleshipOffline());
    }
    
    public void setDisablePlayBattleshipWithComputerButton(boolean isDisable) {
    	playBattleshipWithComputerButton.setDisable(isDisable);
    }
	
	public void blockAllChat() {
		manu.setDisable(true);
		listOfUsersChat.setDisable(true);
		textAreaPrivateChat.setDisable(true);
		sendToPrivateChatButton.setDisable(true);
		suggestAGameButton.setDisable(true);
		generalChat.setDisable(true);
		sendToGeneralChatButton.setDisable(true);
		textAreaGeneralChat.setDisable(true);
	}
	
	public void unblockAllChat() {
		manu.setDisable(false);
		listOfUsersChat.setDisable(false);
		textAreaPrivateChat.setDisable(false);
		sendToPrivateChatButton.setDisable(false);
		suggestAGameButton.setDisable(false);
		generalChat.setDisable(false);
		sendToGeneralChatButton.setDisable(false);
		textAreaGeneralChat.setDisable(false);
	}
}
