package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

public class Gate_Open_Level_Up extends MotionlessElement {

	public Gate_Open_Level_Up() {
		super(new Sprite("┌┐","gate_open_level_up.png"), Permeability.PENETRABLE, 'K');
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.UP;
	}

}
