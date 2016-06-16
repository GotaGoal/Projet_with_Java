package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

class Forest extends MotionlessElement {

	public Forest() {
		super(new Sprite("♠♣"), Permeability.BLOCKING);
	}

}
