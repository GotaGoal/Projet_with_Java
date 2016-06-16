package map.element.motionless;

import map.element.Element;
import map.element.Permeability;
import map.element.Sprite;

abstract class MotionlessElement extends Element {

	private char fileSymbol;
	public MotionlessElement(final Sprite sprite, final Permeability permeability) {
		super(sprite, permeability);
	}
	public char getFileSymbol() {
		return this.fileSymbol;
	}
}