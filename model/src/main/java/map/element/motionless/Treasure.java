package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;

public class Treasure extends MotionlessElement {
	public Treasure() {
		super(new Sprite("┌┐","treasure.png"), Permeability.PENETRABLE, 'Y');
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.PICKUP_TREASURE;
	}
}