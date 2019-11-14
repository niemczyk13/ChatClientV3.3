package com.niemiec.games.battleship.game.data.create.automatically;

import java.io.Serializable;
import java.util.Random;

import com.niemiec.games.battleship.game.data.check.CheckData;
import com.niemiec.games.battleship.game.objects.Coordinates;
import com.niemiec.games.battleship.game.objects.Ship;

@SuppressWarnings("serial")
public class CreatorAutomaticallyData implements Serializable  {
	protected Random random;
	private final int RANDOM_TWO_SIDE = 2;
	private final int FIRST_MAST = 1;
	private final int ZERO_MASTED_INSERTED = 0;
	private final int ONE_MAST_INSERTED = 1;
	protected final int DIRECTION_NOT_SELECTED = 0;
	protected final int DIRECTION_ALONG_Y = 1;
	protected final int DIRECTION_ALONG_X = 2;

	protected final int RIGHT = 0;
	protected final int DOWN = 0;
	protected final int LEFT = 1;
	protected final int TOP = 1;

	

	public CreatorAutomaticallyData() {
		random = new Random();
	}

	public Coordinates downloadCoordinatesWhenAddShip(Ship ship, int currentMast) {
		Coordinates coordinates;

		while (true) {
			switch (currentMast) {
			case ZERO_MASTED_INSERTED:
				coordinates = randomTheFirstMast();
				break;
			case ONE_MAST_INSERTED:
				coordinates = randomTheSecondMast(ship);
				break;
			default:
				coordinates = randomTheReamainingMasts(ship, currentMast);
				break;
			}

			if (CheckData.checkIfWithinThePlayingField(coordinates))
				return coordinates;
		}
	}

	protected Coordinates randomTheReamainingMasts(Ship ship, int currentMast) {
		Coordinates coordinates = new Coordinates();
		int randomSide = random.nextInt(RANDOM_TWO_SIDE);
		int firstMastX = ship.getCoordinates(FIRST_MAST).getX();
		int firstMastY = ship.getCoordinates(FIRST_MAST).getY();
		int shipDirection = ship.getDirection();

		if (theDirectionIsAlongY(shipDirection)) {
			coordinates.setY(firstMastY);
			coordinates.setX(setMast(randomSide, firstMastX, ship));
		} else if (theDirectionIsAlongX(shipDirection)) {
			coordinates.setX(firstMastX);
			coordinates.setY(setMast(randomSide, firstMastY, ship));
		}
		return coordinates;
	}

	private int setMast(int randomSide, int firstMastXorY, Ship ship) {
		if (randomLeftSide(randomSide) && thereIsPlaceOnTheLeftOrTop(firstMastXorY)) {
			return (firstMastXorY - 1);
		} else if (randomRightSide(randomSide) && thereIsPlaceOnTheRightOrDwon(firstMastXorY, ship)) {
			return (firstMastXorY + ship.getCurrentNumberOfMasts());
		}
		return -1;
	}

	private boolean thereIsPlaceOnTheRightOrDwon(int firstMastXorY, Ship ship) {
		return (firstMastXorY + ship.getCurrentNumberOfMasts()) <= 10;
	}

	private boolean randomRightSide(int randomSide) {
		return randomSide == RIGHT;
	}

	private boolean randomLeftSide(int randomSide) {
		return (randomSide == LEFT);
	}

	private boolean thereIsPlaceOnTheLeftOrTop(int firstMastXorY) {
		return (firstMastXorY - 1) > 0;
	}

	private boolean theDirectionIsAlongX(int shipDirection) {
		return shipDirection == DIRECTION_ALONG_X;
	}

	private boolean theDirectionIsAlongY(int shipDirection) {
		return shipDirection == DIRECTION_ALONG_Y;
	}

	private Coordinates randomTheSecondMast(Ship ship) {
		int direction = random.nextInt(2) + 1;
		return assignCoordinates(direction, ship);
	}


	private Coordinates assignCoordinates(int direction, Ship ship) {
		int firstMastX = ship.getCoordinates(FIRST_MAST).getX();
		int firstMastY = ship.getCoordinates(FIRST_MAST).getY();
		Coordinates coordinates = new Coordinates();
		int randomSide = random.nextInt(RANDOM_TWO_SIDE);

		if (direction == DIRECTION_ALONG_X) {
			coordinates = followWhenDirectionItIsAlongX(randomSide, firstMastX, firstMastY);
		} else if (direction == DIRECTION_ALONG_Y) {
			coordinates = followWhenDirectionItIsAlongY(randomSide, firstMastX, firstMastY);
		}

		return coordinates;
	}

	private Coordinates followWhenDirectionItIsAlongX(int side, int coordinateX, int coordinateY) {
		Coordinates coordinates = new Coordinates();
		coordinates.setX(coordinateX);
		if (side == LEFT && CheckData.checkIfWithinThePlayingField(new Coordinates(coordinateX, coordinateY - 1)))
			coordinates.setY(coordinateY - 1);
		else if (side == RIGHT && CheckData.checkIfWithinThePlayingField(new Coordinates(coordinateX, coordinateY + 1)))
			coordinates.setY(coordinateY + 1);

		return coordinates;
	}

	private Coordinates followWhenDirectionItIsAlongY(int side, int coordinateX, int coordinateY) {
		Coordinates coordinates = new Coordinates();
		coordinates.setY(coordinateY);
		if (side == LEFT && CheckData.checkIfWithinThePlayingField(new Coordinates(coordinateX - 1, coordinateY)))
			coordinates.setX(coordinateX - 1);
		else if (side == RIGHT && CheckData.checkIfWithinThePlayingField(new Coordinates(coordinateX + 1, coordinateY)))
			coordinates.setX(coordinateX + 1);

		return coordinates;
	}

	protected Coordinates randomTheFirstMast() {
		int x = random.nextInt(10) + 1;
		int y = random.nextInt(10) + 1;
		return new Coordinates(x, y);
	}
}
