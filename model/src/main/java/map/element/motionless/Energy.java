package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;


public class Energy extends MotionlessElement {
	public Energy() {
		super(new Sprite("┌┐","crystal_ball.png"), Permeability.PENETRABLE, 'X');
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.PICKUP_ENERGY;
	}

	

}