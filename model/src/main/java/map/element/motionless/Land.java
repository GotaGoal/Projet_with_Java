package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

public class Land extends MotionlessElement {

	public Land() {
		super(new Sprite("░░","land.png"), Permeability.PENETRABLE, ' ');
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.NOP;
	}

}
