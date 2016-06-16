package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

class Town extends MotionlessElement {

	public Town() {
		super(new Sprite("╔╗"), Permeability.PENETRABLE);
	}

}
