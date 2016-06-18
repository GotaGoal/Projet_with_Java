package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

public class Gate_Open extends MotionlessElement {

	public Gate_Open() {
		super(new Sprite("┌┐","gate_open.png"), Permeability.PENETRABLE, 'D');
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.UP;
	}

}
