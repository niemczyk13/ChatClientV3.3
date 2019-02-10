package com.niemiec.games.battleship.controllers;

import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.command.order.game.AcceptRejectionGame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WindowWithAnUnacceptableGameController {

    @FXML
    private Label label;

    @FXML
    private Button okButton;
    
    private String opponentPlayerNick;
    private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;

    @FXML
    public void understand() {
    	dispatcherOfOutgoingRequest.setTheCommand(new AcceptRejectionGame(opponentPlayerNick));
    }

	public void setOpponentPlayerNick(String opponentPlayerNick) {
		this.opponentPlayerNick = opponentPlayerNick;
	}

	public void setTextLabel(String message) {
		label.setText(message);
	}

	public void setDispatcherOfOutgoingRequest(DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}
}
