package com.niemiec.games.battleship.view.management;

import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.game.Battleship;
import com.niemiec.games.battleship.game.logic.BorderManagement;
import com.niemiec.games.battleship.view.ConfirmationOfLeaveGameWindowView;
import com.niemiec.games.battleship.view.GameAcceptanceWindowView;
import com.niemiec.games.battleship.view.MainBattleshipView;
import com.niemiec.games.battleship.view.WindowOfTEndGameInformationAndAcceptance;
import com.niemiec.games.battleship.view.WindowOfWaitingForConfirmationOfTheGameView;
import com.niemiec.games.battleship.view.WindowWithAnUnacceptableGameView;

public class BattleshipView {
	private String opponentPlayerNick;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private BorderManagement borderManagement;
	
	private MainBattleshipView mainBattleshipView;
	private WindowOfWaitingForConfirmationOfTheGameView windowOfWaitingForConfirmationOfTheGameView;
	private GameAcceptanceWindowView gameAcceptanceWindowView;
	private WindowWithAnUnacceptableGameView windowWithAnUnacceptableGameView;
	private ConfirmationOfLeaveGameWindowView confirmationOfLeaveGameWindowView;
	private WindowOfTEndGameInformationAndAcceptance windowOfTEndGameInformationAndAcceptance;

	public BattleshipView(String opponentPlayerNick, DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.opponentPlayerNick = opponentPlayerNick;
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
		this.borderManagement = new BorderManagement();
	}
	
	public MainBattleshipView getMainBattleshipView() {
		if (mainBattleshipView == null) {
			mainBattleshipView = new MainBattleshipView(Battleship.ONLINE, opponentPlayerNick, dispatcherOfOutgoingRequest, borderManagement);
		}
		return mainBattleshipView;
	}
	
	public WindowOfWaitingForConfirmationOfTheGameView getWindowOfWaitingForConfirmationOfTheGameView() {
		if (windowOfWaitingForConfirmationOfTheGameView == null) {
			windowOfWaitingForConfirmationOfTheGameView = new WindowOfWaitingForConfirmationOfTheGameView(opponentPlayerNick);
		}
		return windowOfWaitingForConfirmationOfTheGameView;
	}

	public GameAcceptanceWindowView getGameAcceptanceWindowView() {
		if (gameAcceptanceWindowView == null) {
			gameAcceptanceWindowView = new GameAcceptanceWindowView(opponentPlayerNick, dispatcherOfOutgoingRequest);
		}
		return gameAcceptanceWindowView;
	}


	public WindowWithAnUnacceptableGameView getWindowWithAnUnacceptableGameView() {
		if (windowWithAnUnacceptableGameView == null) {
			windowWithAnUnacceptableGameView = new WindowWithAnUnacceptableGameView(opponentPlayerNick, dispatcherOfOutgoingRequest);
		}
		return windowWithAnUnacceptableGameView;
	}
	
	public ConfirmationOfLeaveGameWindowView getConfirmationOfLeaveGameWindowView() {
		if (confirmationOfLeaveGameWindowView == null) {
			confirmationOfLeaveGameWindowView = new ConfirmationOfLeaveGameWindowView(Battleship.ONLINE, opponentPlayerNick, dispatcherOfOutgoingRequest);
		}
		return confirmationOfLeaveGameWindowView;
	}

	public BorderManagement getBorderManagement() {
		return borderManagement;
	}

	public WindowOfTEndGameInformationAndAcceptance getWindowOfTEndGameInformationAndAcceptance() {
		if (windowOfTEndGameInformationAndAcceptance == null) {
			windowOfTEndGameInformationAndAcceptance = new WindowOfTEndGameInformationAndAcceptance(Battleship.ONLINE, opponentPlayerNick, dispatcherOfOutgoingRequest);
		}
		return windowOfTEndGameInformationAndAcceptance;
	}
}