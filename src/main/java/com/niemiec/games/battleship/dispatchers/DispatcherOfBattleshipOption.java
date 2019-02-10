package com.niemiec.games.battleship.dispatchers;

import com.niemiec.chat.data.ChatData;
import com.niemiec.games.battleship.command.order.option.CloseBattleshipWindow;
import com.niemiec.games.battleship.command.order.option.Exit;
import com.niemiec.games.battleship.command.order.option.ExitAllBattleshipGames;
import com.niemiec.games.battleship.command.processors.BattleshipProcessorData;

public class DispatcherOfBattleshipOption {
	private BattleshipProcessorData battleshipProcessorData;

	public DispatcherOfBattleshipOption(ChatData chatData) {
		battleshipProcessorData = chatData.getBattleshipProcessorData();
	}

	public void setTheCommand(Object object) {
		if (object instanceof CloseBattleshipWindow) {
			battleshipProcessorData.getCloseBattleshipWindowProcessor().setTheCommand(object);
		} else if (object instanceof Exit) {
			battleshipProcessorData.getExitProcessor().setTheCommand(object);
		} else if (object instanceof ExitAllBattleshipGames) {
			battleshipProcessorData.getExitAllBattleshipGamesProcessor().setTheCommand(object);
		}
	}
}
