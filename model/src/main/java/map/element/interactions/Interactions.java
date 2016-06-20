package map.element.interactions;

import java.awt.Point;

import aedt.showboard.IPawn;
import map.MapWorld;
import map.element.Element;
import map.element.Permeability;
import map.element.Sprite;
import map.element.motionless.ActionOnHeroes;
import map.element.motionless.IDoActionOnHeroes;

public class Interactions extends Element implements IPawn {
	private char fileSymbol;
	private Point position;
	
	public Interactions(final Sprite sprite, final Permeability permeability, final char fileSymbol) {
		super(sprite, permeability);
		this.fileSymbol = fileSymbol;
		this.position = new Point();
	}
	
	
	public char getFileSymbol() {
		return this.fileSymbol;
	}

	public void setMapWorld(final MapWorld mapWorld, final int x, final int y) {
		super.setMapWorld(mapWorld);
		this.setX(x);
		this.setY(y);
	}


	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}


	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.position.x;
	}


	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.position.y;
	}
	private void setX(final int x) {
		if ((x >= 0) && (x < this.getMapWorld().getWidth())) {
			this.position.x = x;
			this.getMapWorld().setMobileHasChanged();
		}
	}
	private void setY(final int y) {
		if ((y >= 0) && (y < this.getMapWorld().getHeight())) {
			this.position.y = y;
			this.getMapWorld().setMobileHasChanged();
		}
	}

}
