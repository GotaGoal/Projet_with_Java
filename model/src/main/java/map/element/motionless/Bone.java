package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

public class Bone extends MotionlessElement {

	

	public Bone() {
		super(new Sprite("┌┐","bone.png"), Permeability.BLOCKING, 'B');
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.NOP;
	}

}
