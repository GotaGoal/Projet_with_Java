package map.element.mobile;

import java.awt.Point;

import aedt.showboard.IPawn;
import map.MapWorld;
import map.element.Element;
import map.element.Permeability;
import map.element.Sprite;

public abstract class Mobile extends Element implements IPawn{

	private final Point position;

	public Mobile(final Sprite sprite) {
		super(sprite, Permeability.PENETRABLE);
		this.position = new Point();
	}

	@Override
	public int getX() {
		return this.position.x;
	}

	public void setX(final int x) {
		if ((x >= 0) && (x < this.getMapWorld().getWidth())) {
			this.position.x = x;
			this.getMapWorld().setMobileHasChanged();
		}
	}

	@Override
	public int getY() {
		return this.position.y;
	}

	public void setY(final int y) {
		if ((y >= 0) && (y < this.getMapWorld().getHeight())) {
			this.position.y = y;
			this.getMapWorld().setMobileHasChanged();
		}
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	private boolean isMovePossible(final int x, final int y) {
		return (this.getMapWorld().getElements(x, y).getPermeability() != Permeability.BLOCKING);
	}

	public void moveUp() {
		if (this.isMovePossible(this.getX(), this.getY() - 1)) {
			this.setY(this.getY() - 1);
		}
	}

	public void moveLeft() {
		if (this.isMovePossible(this.getX() - 1, this.getY())) {
			this.setX(this.getX() - 1);
		}
	}

	public void moveDown() {
		if (this.isMovePossible(this.getX(), this.getY() + 1)) {
			this.setY(this.getY() + 1);
		}
	}

	public void moveRight() {
		if (this.isMovePossible(this.getX() + 1, this.getY())) {
			this.setX(this.getX() + 1);
		}
	}

	public void setMapWorld(final MapWorld mapWorld, final int x, final int y) {
		super.setMapWorld(mapWorld);
		this.setX(x);
		this.setY(y);
	}
}

