package com.niemiec.games.battleship.data;

import java.util.ArrayList;

import com.niemiec.games.battleship.controllers.MainBattleshipController;
import com.niemiec.games.battleship.game.logic.AddShips;
import com.niemiec.games.battleship.game.logic.BorderManagement;
import com.niemiec.games.battleship.messages.BattleshipGame;
import com.niemiec.games.battleship.view.BattleshipView;

public class BattleshipGamesManager {
	private ArrayList<String> opponentPlayers;
	private ArrayList<BattleshipGame> battleshipGames;
	private ArrayList<BattleshipView> battleshipViews;
	private ArrayList<BorderManagement> borderManagements;
	private ArrayList<AddShips> addShips;
	
	public BattleshipGamesManager() {
		this.opponentPlayers = new ArrayList<>();
		this.battleshipGames = new ArrayList<>();
		this.battleshipViews = new ArrayList<>();
		this.borderManagements = new ArrayList<>();
		this.addShips = new ArrayList<>();
	}
	

	public void addBattleshipGame(BattleshipGame battleshipGame, BattleshipView battleshipView) {
		opponentPlayers.add(battleshipGame.getOpponentPlayerNick());
		battleshipGames.add(battleshipGame);
		battleshipViews.add(battleshipView);
		
		BorderManagement borderManagement = battleshipView.getBorderManagement();
		borderManagements.add(borderManagement);
		
		AddShips aS = new AddShips();
		aS.addBorderManagement(borderManagement);
		addShips.add(aS);
		
	}
	
	public BattleshipGame getBattleshipGame(String opponentPlayerNick) {
		for (int i = 0; i < opponentPlayers.size(); i++) {
			if (opponentPlayers.get(i).equals(opponentPlayerNick)) {
				return battleshipGames.get(i);
			}
		}
		return null;
	}
	
	public BattleshipGame getBattleshipGame(BattleshipGame battleshipGame) {
		return getBattleshipGame(battleshipGame.getOpponentPlayerNick());
		
	}

	public BattleshipView getBattleshipView(String opponentPlayerNick) {
		for (int i = 0; i < opponentPlayers.size(); i++) {
			if (opponentPlayers.get(i).equals(opponentPlayerNick)) {
				return battleshipViews.get(i);
			}
		}
		return null;
	}
	
	public BattleshipView getBattleshipView(BattleshipGame battleshipGame) {
		return getBattleshipView(battleshipGame.getOpponentPlayerNick());
		
	}
	
	public MainBattleshipController getMainScreenController(String opponentPlayerNick) {
		for (int i = 0; i < opponentPlayers.size(); i++) {
			if (opponentPlayers.get(i).equals(opponentPlayerNick)) {
				return battleshipViews.get(i).getMainBattleshipView().getController();
			}
		}
		return null;
	}
	
	public MainBattleshipController getMainScreenController(BattleshipGame battleshipGame) {
		return getMainScreenController(battleshipGame.getOpponentPlayerNick());
		
	}
	
	public BorderManagement getBorderManagement(String opponentPlayerNick) {
		for (int i = 0; i < opponentPlayers.size(); i++) {
			if (opponentPlayers.get(i).equals(opponentPlayerNick)) {
				return borderManagements.get(i);
			}
		}
		return null;
	}
	
	public BorderManagement getBorderManagement(BattleshipGame battleshipGame) {
		for (int i = 0; i < opponentPlayers.size(); i++) {
			if (battleshipGame.getGameIndex() == battleshipGames.get(i).getGameIndex()) {
				return borderManagements.get(i);
			}
		}
		return null;
	}
	
	public String getOpponentPlayerNick(BattleshipGame battleshipGame) {
		for (int i = 0; i < battleshipGames.size(); i++) {
			if (battleshipGame.getGameIndex() == battleshipGames.get(i).getGameIndex()) {
				return opponentPlayers.get(i);
			}
		}
		return null;
	}
	
	public void deleteBattleshipGame(String opponentPlayerNick) {
		for (int i = 0; i < opponentPlayers.size(); i++) {
			if (opponentPlayers.get(i).equals(opponentPlayerNick)) {
				battleshipGames.remove(i);
				battleshipViews.remove(i);
				opponentPlayers.remove(i);
				borderManagements.remove(i);
				addShips.remove(i);
				return;
			}
		}
	}
	
	public void deleteBattleshipGame(BattleshipGame battleshipGame) {
		deleteBattleshipGame(battleshipGame.getOpponentPlayerNick());
	}

	public void updateBattleshipGame(BattleshipGame battleshipGame) {
		for (int i = 0; i < opponentPlayers.size(); i++) {

			if (opponentPlayers.get(i).equals(battleshipGame.getOpponentPlayerNick())) {
				battleshipGames.set(i, battleshipGame);
				return;
			}
		}
	}

	public AddShips getAddShip(String opponentPlayerNick) {
		for (int i = 0; i < opponentPlayers.size(); i++) {
			if (opponentPlayers.get(i).equals(opponentPlayerNick)) {
				return addShips.get(i);
			}
		}
		return null;
	}
	
	public AddShips getAddShip(BattleshipGame battleshipGame) {
		return getAddShip(battleshipGame.getOpponentPlayerNick());
	}


	public void showStatistic() {
		System.out.println("*************BATTLESHIP GAMES MANAGER STATISTIC***************");
		for (int i = 0; i < opponentPlayers.size(); i++) {
			BattleshipGame b = battleshipGames.get(i);
			System.out.println("INDEX: " + b.getGameIndex() + ", opponent: " + b.getOpponentPlayerNick());
			System.out.println("Nick z ArrayList opponentPlayers: " + opponentPlayers.get(i));
			System.out.println("# # #                                                # # #");
		}
		System.out.println("**************************************************************");
	}


	public boolean checkIfExist(String opponentPlayerNick) {
		for (int i = 0; i < opponentPlayers.size(); i++) {
			if (opponentPlayers.get(i).equals(opponentPlayerNick))
				return true;
		}
		return false;
	}
	
	public String getFirstOpponentPlayer() {
		return opponentPlayers.get(0);
	}
	
	public int getNumberOfBattleshipGames() {
		return opponentPlayers.size();
	}


	public boolean checkIfExist(BattleshipGame battleshipGame) {
		return checkIfExist(battleshipGame.getOpponentPlayerNick());
	}
}
