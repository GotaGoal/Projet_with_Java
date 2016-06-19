package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

public class Gate_Close extends MotionlessElement {

	public Gate_Close() {
		super(new Sprite("┌┐","gate_closed.png"), Permeability.BLOCKING, 'C');
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.NOP;
	}

}