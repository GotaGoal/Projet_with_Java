package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

class Stone extends MotionlessElement {

	public Stone() {
		super(new Sprite("■▀"), Permeability.BLOCKING);
	}

}
