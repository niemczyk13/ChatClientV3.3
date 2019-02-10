package com.niemiec.games.battleship.command.processors.game;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.game.ShipsAdder;
import com.niemiec.games.battleship.data.BattleshipGamesManager;
import com.niemiec.games.battleship.game.logic.AddShips;
import com.niemiec.games.battleship.game.objects.Player;
import com.niemiec.games.battleship.messages.BattleshipGame;
import com.niemiec.games.battleship.view.BattleshipView;

public class ShipsAdderProcessor {
	private ChatData chatData;
	private BattleshipGamesManager battleshipGamesManager;
	private BattleshipGame battleshipGame;
	private BattleshipView battleshipView;
	private ShipsAdder shipsAdder;
	private String opponentPlayerNick;

	public ShipsAdderProcessor(ChatData chatData) {
		this.chatData = chatData;
		this.battleshipGamesManager = chatData.getBattleshipGamesManager();
	}

	public void receiveTheObject(Object object) {
		updateBattleshipGame(object);
		updateBattleshipGameInManager();
		getBattleshipView();
		closeWaitingWindowIsExist();
		updateAddShips();
		showBattleshipWindow();
	}

	public void setTheCommand(Object object) {
		updateShipsAdder(object);
		addShips();
	}

	private void addShips() {
		if (addShipsIsCompleted()) {
			updateBattleshipGame();
			setTheWaitingDisplayProperties();
			sendBattleshipGame();
		}
	}

	private void sendBattleshipGame() {
		chatData.sendTheObject(battleshipGame);
	}

	private void setTheWaitingDisplayProperties() {
		battleshipGamesManager.getBorderManagement(opponentPlayerNick).setBordersToEndGame();
	}

	private void updateBattleshipGame() {
		battleshipGame = battleshipGamesManager.getBattleshipGame(opponentPlayerNick);
		battleshipGame.setGameStatus(BattleshipGame.SHIPS_ADDED);
		updatePlayerInBatteshipGame();
	}

	private void updatePlayerInBatteshipGame() {
		Player player = battleshipGamesManager.getAddShip(opponentPlayerNick).getPlayer(Player.SECOND_PLAYER);
		battleshipGame.setPlayer(player);
	}

	private boolean addShipsIsCompleted() {
		AddShips addShips = battleshipGamesManager.getAddShip(opponentPlayerNick);
		if (methodOfAddingIsManually()) {
			return addShips.addShipsManually(Player.SECOND_PLAYER, shipsAdder.getEvent());
		} else {
			return addShips.addShipsAutomatically(Player.SECOND_PLAYER);
		}
	}

	private boolean methodOfAddingIsManually() {
		return shipsAdder.getMethodOfAdding() == shipsAdder.MANUALLY;
	}

	private void updateShipsAdder(Object object) {
		shipsAdder = (ShipsAdder) object;
		opponentPlayerNick = shipsAdder.getOpponentPlayerNick();
	}

	private void showBattleshipWindow() {
		battleshipView.getMainBattleshipView().show();
		setTheInitialDisplayProperties();
	}

	private void setTheInitialDisplayProperties() {
		battleshipGamesManager.getBorderManagement(battleshipGame).startNewGameWithVirtualPlayer();
	}

	private void updateAddShips() {
		AddShips addShips = battleshipGamesManager.getAddShip(battleshipGame);
		addShips.addOneRealPlayer(battleshipGame.getPlayer());
	}

	private void closeWaitingWindowIsExist() {
		if (WindowOfWaitingForConfirmationIsShowing()) {
			closeWindowOfWaitingForConfirmation();
		}
	}

	private void closeWindowOfWaitingForConfirmation() {
		battleshipView.getWindowOfWaitingForConfirmationOfTheGameView().close();
	}

	private boolean WindowOfWaitingForConfirmationIsShowing() {
		return battleshipView.getWindowOfWaitingForConfirmationOfTheGameView().isShowing();
	}

	private void getBattleshipView() {
		battleshipView = battleshipGamesManager.getBattleshipView(battleshipGame);
	}

	private void updateBattleshipGameInManager() {
		battleshipGamesManager.updateBattleshipGame(battleshipGame);
	}

	private void updateBattleshipGame(Object object) {
		battleshipGame = (BattleshipGame) object;
	}
}
