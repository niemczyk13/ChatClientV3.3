package com.niemiec.games.battleship.controllers;

import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.command.order.game.GiveUp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ConfirmationOfLeaveGameWindowController {
	private String opponentPlayerNick;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	
    @FXML
	public
    void cancel() {
    	dispatcherOfOutgoingRequest.setTheCommand(new GiveUp(opponentPlayerNick, GiveUp.CANCEL));
    }

    @FXML
    void exit(ActionEvent event) {
    	dispatcherOfOutgoingRequest.setTheCommand(new GiveUp(opponentPlayerNick, GiveUp.GIVE_UP));
    }

	public void setOpponentPlayerNick(String opponentPlayerNick) {
		this.opponentPlayerNick = opponentPlayerNick;
	}

	public void setDispatcherOfOutgoingRequest(DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}
}
