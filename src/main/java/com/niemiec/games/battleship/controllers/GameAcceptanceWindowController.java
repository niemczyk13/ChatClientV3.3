package com.niemiec.games.battleship.controllers;

import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.command.order.game.AnswerToTheGameProposal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameAcceptanceWindowController {
	@FXML
	private Label label;

	@FXML
	private Button okButton;

	@FXML
	private Button rejectionButton;

	private String opponentPlayerNick;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;

	@FXML
	void accept() {
		dispatcherOfOutgoingRequest.setTheCommand(new AnswerToTheGameProposal(opponentPlayerNick, AnswerToTheGameProposal.ACCEPT));
	}

	@FXML
	public void rejectionGameProposal() {
		dispatcherOfOutgoingRequest.setTheCommand(new AnswerToTheGameProposal(opponentPlayerNick, AnswerToTheGameProposal.NO_ACCEPT));
	}

	public void setTextLabel(String text) {
		label.setText(text);
	}

	public void setOpponentPlayerNick(String opponentPlayerNick) {
		this.opponentPlayerNick = opponentPlayerNick;
	}

	public void setDispatcherOfOutgoingRequest(DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}
}