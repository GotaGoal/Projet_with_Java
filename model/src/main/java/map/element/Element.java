package map.element;

import java.awt.Image;

import aedt.showboard.ISquare;
import map.MapWorld;

public abstract class Element implements ISquare{
	private Sprite				sprite;
	private Permeability	permeability;
	private MapWorld		mapWorld;

	public Element(final Sprite sprite, final Permeability permeability) {
		this.setSprite(sprite);
		this.setPermeability(permeability);
	}

	public Sprite getSprite() {
		return this.sprite;
	}

	public void setSprite(final Sprite sprite) {
		this.sprite = sprite;
	}

	public Permeability getPermeability() {
		return this.permeability;
	}

	private void setPermeability(final Permeability permeability) {
		this.permeability = permeability;
	}

	public MapWorld getMapWorld() {
		return this.mapWorld;
	}

	public void setMapWorld(final MapWorld mapWorld) {
		this.mapWorld = mapWorld;
	}
	@Override
	public Image getImage() {
		return this.getSprite().getImage();
	}
	
	 
	
}
