package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

class Water extends MotionlessElement {

	public Water() {
		super(new Sprite("~~"), Permeability.BLOCKING);
	}

}
