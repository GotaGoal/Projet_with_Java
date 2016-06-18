package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

public class Bone_H extends MotionlessElement {
	
	public Bone_H() {
		super(new Sprite("hh", "boneh.png"), Permeability.BLOCKING, 'H');
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.NOP;
	}

}
