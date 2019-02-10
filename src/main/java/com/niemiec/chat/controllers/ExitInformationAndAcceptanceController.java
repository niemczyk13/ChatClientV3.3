package com.niemiec.chat.controllers;

import com.niemiec.chat.command.order.messages.condition.ExitMessage;
import com.niemiec.chat.command.order.options.generalchat.CancelExit;
import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;

import javafx.fxml.FXML;

public class ExitInformationAndAcceptanceController {

	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;

    @FXML
    public void cancel() {
    	dispatcherOfOutgoingRequest.setTheCommand(new CancelExit());
    }

    @FXML
    void exit() {
    	dispatcherOfOutgoingRequest.setTheCommand(new ExitMessage());
    }

	public void setDisDispatcherOfOutgoingMessages(DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}
}
