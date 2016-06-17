package map.element.motionless;

import map.element.Element;
import map.element.Permeability;
import map.element.Sprite;

public abstract class MotionlessElement extends Element {

	private char fileSymbol;
	public MotionlessElement(final Sprite sprite, final Permeability permeability, final char fileSymbol) {
		super(sprite, permeability);
		this.fileSymbol = fileSymbol;
	}
	public char getFileSymbol() {
		return this.fileSymbol;
	}
}