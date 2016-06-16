package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

class Land extends MotionlessElement {

	public Land() {
		super(new Sprite("░░"), Permeability.PENETRABLE, ' ');
	}

}
