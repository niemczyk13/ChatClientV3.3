package com.niemiec.games.battleship.command.processors;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.processors.game.AcceptRejectionGameProcessor;
import com.niemiec.games.battleship.command.processors.game.AnswerToTheGameProposalProcessor;
import com.niemiec.games.battleship.command.processors.game.EndGameProcessor;
import com.niemiec.games.battleship.command.processors.game.EndOfflineGameProcessor;
import com.niemiec.games.battleship.command.processors.game.ExitAllBattleshipGamesProcessor;
import com.niemiec.games.battleship.command.processors.game.GameProposalProcessor;
import com.niemiec.games.battleship.command.processors.game.GiveUpProcessor;
import com.niemiec.games.battleship.command.processors.game.PlayBattleshipOfflineProcessor;
import com.niemiec.games.battleship.command.processors.game.RejectionGameProposalProcessor;
import com.niemiec.games.battleship.command.processors.game.ShipsAddedInOfflineGameProcessor;
import com.niemiec.games.battleship.command.processors.game.ShipsAdderProcessor;
import com.niemiec.games.battleship.command.processors.game.ShooterMovementProcessor;
import com.niemiec.games.battleship.command.processors.options.CloseBattleshipWindowProcessor;
import com.niemiec.games.battleship.command.processors.options.ExitProcessor;

public class BattleshipProcessorData {
	private ChatData chatData;
	private GameProposalProcessor gameProspalProcessor;
	private AnswerToTheGameProposalProcessor answerToTheGameProposalProcessor;
	private RejectionGameProposalProcessor rejectionGameProposalProcessor;
	private AcceptRejectionGameProcessor acceptRejectionGameProcessor;
	private ShipsAdderProcessor shipsAdderProcessor;
	private ShooterMovementProcessor shooterMovementProcessor;
	private EndGameProcessor endGameProcessor;
	private GiveUpProcessor giveUpProcessor;
	private CloseBattleshipWindowProcessor closeBattleshipWindowProcessor;
	private ExitProcessor exitProcessor;
	private ExitAllBattleshipGamesProcessor exitAllBattleshipGamesProcessor;
	private PlayBattleshipOfflineProcessor playBattleshipOfflineProcessor;
	private ShipsAddedInOfflineGameProcessor shipsAddedInOfflineGameProcessor;
	private EndOfflineGameProcessor endOfflineGameProcessor;

	public BattleshipProcessorData(ChatData chatData) {
		this.chatData = chatData;
	}

	public GameProposalProcessor getGameProposalProcessor() {
		if (gameProspalProcessor == null) {
			gameProspalProcessor = new GameProposalProcessor(chatData);
		}
		return gameProspalProcessor;
	}

	public AnswerToTheGameProposalProcessor getAnswerToTheGameProposalProcessor() {
		if (answerToTheGameProposalProcessor == null) {
			answerToTheGameProposalProcessor = new AnswerToTheGameProposalProcessor(chatData);
		}
		return answerToTheGameProposalProcessor;
	}

	public RejectionGameProposalProcessor getRejectionGameProposalProcessor() {
		if (rejectionGameProposalProcessor == null) {
			rejectionGameProposalProcessor = new RejectionGameProposalProcessor(chatData);
		}
		return rejectionGameProposalProcessor;
	}

	public AcceptRejectionGameProcessor getAcceptRejectionGameProcessor() {
		if (acceptRejectionGameProcessor == null) {
			acceptRejectionGameProcessor = new AcceptRejectionGameProcessor(chatData);
		}
		return acceptRejectionGameProcessor;
	}

	public ShipsAdderProcessor getShipsAdderProcessor() {
		if (shipsAdderProcessor == null) {
			shipsAdderProcessor = new ShipsAdderProcessor(chatData);
		}
		return shipsAdderProcessor;
	}
	
	public ShooterMovementProcessor getShooterMovementProcessor() {
		if (shooterMovementProcessor == null) {
			shooterMovementProcessor = new ShooterMovementProcessor(chatData);
		}
		return shooterMovementProcessor;
	}

	public EndGameProcessor getEndGameProcessor() {
		if (endGameProcessor == null) {
			endGameProcessor = new EndGameProcessor(chatData);
		}
		return endGameProcessor;
	}

	public GiveUpProcessor getGiveUpProcessor() {
		if (giveUpProcessor == null) {
			giveUpProcessor = new GiveUpProcessor(chatData);
		}
		return giveUpProcessor;
	}

	public CloseBattleshipWindowProcessor getCloseBattleshipWindowProcessor() {
		if (closeBattleshipWindowProcessor == null) {
			closeBattleshipWindowProcessor = new CloseBattleshipWindowProcessor(chatData);
		}
		return closeBattleshipWindowProcessor;
	}

	public ExitProcessor getExitProcessor() {
		if (exitProcessor == null) {
			exitProcessor = new ExitProcessor(chatData);
		}
		return exitProcessor;
	}

	public ExitAllBattleshipGamesProcessor getExitAllBattleshipGamesProcessor() {
		if (exitAllBattleshipGamesProcessor == null) {
			exitAllBattleshipGamesProcessor = new ExitAllBattleshipGamesProcessor(chatData);
		}
		return exitAllBattleshipGamesProcessor;
	}

	public PlayBattleshipOfflineProcessor getPlayBattleshipOfflineProcessor() {
		if (playBattleshipOfflineProcessor == null) {
			playBattleshipOfflineProcessor = new PlayBattleshipOfflineProcessor(chatData);
		}
		return playBattleshipOfflineProcessor;
	}

	public ShipsAddedInOfflineGameProcessor getShipsAddedInOfflineGameProcessor() {
		if (shipsAddedInOfflineGameProcessor == null) {
			shipsAddedInOfflineGameProcessor = new ShipsAddedInOfflineGameProcessor(chatData);
		}
		return shipsAddedInOfflineGameProcessor;
	}

	public EndOfflineGameProcessor getEndOfflineGameProcessor() {
		if (endOfflineGameProcessor == null) {
			endOfflineGameProcessor = new EndOfflineGameProcessor(chatData);
		}
		return endOfflineGameProcessor;
	}
}