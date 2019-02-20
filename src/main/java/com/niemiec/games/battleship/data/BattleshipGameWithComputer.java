package com.niemiec.games.battleship.data;

import com.niemiec.chat.data.ChatData;
import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;
import com.niemiec.games.battleship.game.logic.AddShips;
import com.niemiec.games.battleship.game.logic.BorderManagement;
import com.niemiec.games.battleship.game.logic.ShotShip;
import com.niemiec.games.battleship.game.objects.Player;
import com.niemiec.games.battleship.game.objects.PlayerImpl;
import com.niemiec.games.battleship.view.management.BattleshipOfflineView;

public class BattleshipGameWithComputer {
	private String nick;
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private Player[] players;
	private BattleshipOfflineView battleshipOfflineView;
	private AddShips addShips;
	private ShotShip shotShip;

	public BattleshipGameWithComputer(ChatData chatData) {
		this.nick = chatData.getNick();
		this.dispatcherOfOutgoingRequest = chatData.getDispatcherOfOutgoingRequest();
	}

	public void startNewGame() {
		battleshipOfflineView = new BattleshipOfflineView(dispatcherOfOutgoingRequest);
		createPlayers();
		createAddShips();
		createShotShip();
	}

	private void createShotShip() {
		shotShip = new ShotShip();
		shotShip.setInitialData(players[Player.REAL_PLAYER], players[Player.VIRTUAL_PLAYER]);
	}

	private void createAddShips() {
		addShips = new AddShips();
		addShips.addPlayers(players[Player.REAL_PLAYER], players[Player.VIRTUAL_PLAYER]);
	}

	private void createPlayers() {
		players = new Player[2];
		players[Player.REAL_PLAYER] = new PlayerImpl(Player.REAL_PLAYER, nick);
		players[Player.VIRTUAL_PLAYER] = new PlayerImpl(Player.VIRTUAL_PLAYER, "Komputer");
	}

	public AddShips getAddShips() {
		return addShips;
	}

	public BattleshipOfflineView getBattleshipOfflineView() {
		return battleshipOfflineView;
	}

	public ShotShip getShotShip() {
		return shotShip;
	}

	public BorderManagement getBorderManagement() {
		return battleshipOfflineView.getBorderManagement();
	}

	public Player getRealPlayer() {
		return players[Player.REAL_PLAYER];
	}

	public Player getVirtualPlayer() {
		return players[Player.VIRTUAL_PLAYER];
	}
}