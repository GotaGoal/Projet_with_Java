package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

public class Gate_Open extends MotionlessElement {

	public Gate_Open(Sprite sprite, Permeability permeability, char fileSymbol) {
		super(new Sprite("┌┐","bone.png"), Permeability.PENETRABLE, 'O');
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.EXIT;
	}

}
