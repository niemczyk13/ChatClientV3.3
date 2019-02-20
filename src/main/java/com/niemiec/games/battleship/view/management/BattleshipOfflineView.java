package com.niemiec.games.battleship.view.management;

import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.game.Battleship;
import com.niemiec.games.battleship.game.logic.BorderManagement;
import com.niemiec.games.battleship.view.ConfirmationOfLeaveGameWindowView;
import com.niemiec.games.battleship.view.MainBattleshipView;
import com.niemiec.games.battleship.view.WindowOfTEndGameInformationAndAcceptance;

public class BattleshipOfflineView {
	private String opponentPlayerNick;
	private BorderManagement borderManagement;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private MainBattleshipView mainBattleshipView;
	private WindowOfTEndGameInformationAndAcceptance windowOfTEndGameInformationAndAcceptance;
	private ConfirmationOfLeaveGameWindowView confirmationOfLeaveGameWindowView;

	public BattleshipOfflineView(DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
		opponentPlayerNick = "Komputer";
		borderManagement = new BorderManagement();
		mainBattleshipView = new MainBattleshipView(Battleship.OFFLINE, opponentPlayerNick, dispatcherOfOutgoingRequest,
				borderManagement);
	}

	public MainBattleshipView getMainBattleshipView() {
		return mainBattleshipView;
	}

	public BorderManagement getBorderManagement() {
		return borderManagement;
	}
	
	public WindowOfTEndGameInformationAndAcceptance getWindowOfTEndGameInformationAndAcceptance() {
		if (windowOfTEndGameInformationAndAcceptance == null) {
			windowOfTEndGameInformationAndAcceptance = new WindowOfTEndGameInformationAndAcceptance(Battleship.OFFLINE, opponentPlayerNick, dispatcherOfOutgoingRequest);
		}
		return windowOfTEndGameInformationAndAcceptance;
	}

	public ConfirmationOfLeaveGameWindowView getConfirmationOfLeaveGameWindowView() {
		if (confirmationOfLeaveGameWindowView == null) {
			confirmationOfLeaveGameWindowView = new ConfirmationOfLeaveGameWindowView(Battleship.OFFLINE, opponentPlayerNick, dispatcherOfOutgoingRequest);
		}
		return confirmationOfLeaveGameWindowView;
	}
}
