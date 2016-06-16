package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

class Camp extends MotionlessElement {

	public Camp() {
		super(new Sprite("┌┐"), Permeability.PENETRABLE);
	}

}
