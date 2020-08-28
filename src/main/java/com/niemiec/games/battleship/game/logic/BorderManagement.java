package com.niemiec.games.battleship.game.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.niemiec.games.battleship.game.objects.Board;
import com.niemiec.games.battleship.game.objects.Coordinates;
import com.niemiec.games.battleship.game.objects.Player;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BorderManagement {

	private VBox myBorder;
	private VBox opponentBorder;

//	public BorderManagement(VBox myBorder, VBox opponentBorder) {
//		this.myBorder = myBorder;
//		this.opponentBorder = opponentBorder;
//	}

	public void setBorders(VBox myBorder, VBox opponentBorder) {
		this.myBorder = myBorder;
		this.opponentBorder = opponentBorder;
	}

	public void startNewGameWithVirtualPlayer() {
		Platform.runLater(() -> {
			cleanBorders();
			myBorder.setDisable(false);
			opponentBorder.setDisable(true);
		});
	}
	
	public void continudeAddingShips() {
		Platform.runLater(() -> {
			myBorder.setDisable(false);
			opponentBorder.setDisable(true);
		});
	}

	private void cleanBorders() {
		drawInBorderButton(myBorder.getChildren(), null);
		drawInBorderButton(opponentBorder.getChildren(), null);
	}

	public void drawOpponentBoardInMyBorder(Player player) {
		drawInBorderButton(myBorder.getChildren(), player.getOpponentBoard());
	}

	public void drawOpponentBoardInOpponentBorder(Player player) {
		drawInBorderButton(opponentBorder.getChildren(), player.getOpponentBoard());
	}

	public void drawBoardInMyBorder(Player player) {
		drawInBorderButton(myBorder.getChildren(), player.getBoard());
	}

	public void drawBoardsInBordersDuringTheGame(Player player) {
		drawBoardInMyBorder(player);
		drawOpponentBoardInOpponentBorder(player);
	}

	public void drawBoardInOpponentBorder(Player player) {
		drawInBorderButton(opponentBorder.getChildren(), player.getBoard());
	}

	private void drawInBorderButton(ObservableList<Node> obervableList, Board board) {
		for (int y = 0; y < 10; y++) {
			HBox hbox = (HBox) obervableList.get(y);
			drawInBorderXAxis(y, hbox, board);
		}
	}

	private void drawInBorderXAxis(int y, HBox hbox, Board board) {
		for (int x = 0; x < 10; x++) {
			Coordinates coordinates = new Coordinates(x + 1, y + 1);
			Button button = (Button) hbox.getChildren().get(x);
			drawInButton(button, board, coordinates);
		}
	}

	private void drawInButton(Button button, Board board, Coordinates coordinates) {
		if (board == null) {
			button.setText("");
			button.setGraphic(null);
		} else {
			int box = board.getBox(coordinates);
			if (box != 0) {
			FileInputStream in = null;
			try {
				if (box == 1) {
					in = new FileInputStream("src/main/resources/img/box_not_hit.png");
				} else if (box == 2 || box == 4) {
					in = new FileInputStream("src/main/resources/img/box_ship.png");
				} else if (box == 3 || box == 5) {
					in = new FileInputStream("src/main/resources/img/box_hit.png");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Image img = new Image(in);
			ImageView view = new ImageView(img);
			button.setGraphic(view);
			button.getGraphic().maxHeight(button.getMaxHeight());
			button.getGraphic().maxWidth(button.getMaxWidth());
			}
		}
	}

	public void setBordersToStartShot() {
		Platform.runLater(() -> {
			myBorder.setDisable(true);
			opponentBorder.setDisable(false);
		});
	}

	public void realPlayerAddedShipsAutomatically() {
		myBorder.setDisable(true);
	}
	
	public void disableOpponentBorder() {
		opponentBorder.setDisable(true);
	}

	public void setBordersToEndGame() {
		Platform.runLater(() -> {
			myBorder.setDisable(true);
			opponentBorder.setDisable(true);
		});
	}
	
	public void setBordersToStartAdd() {
		Platform.runLater(() -> {
			myBorder.setDisable(false);
			opponentBorder.setDisable(true);
		});
	}
}
