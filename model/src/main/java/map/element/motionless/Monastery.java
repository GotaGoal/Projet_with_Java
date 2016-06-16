package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

class Monastery extends MotionlessElement {

	public Monastery() {
		super(new Sprite("⌂⌂"), Permeability.PENETRABLE);
	}

}
