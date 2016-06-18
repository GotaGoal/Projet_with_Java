package map.element.motionless;

import map.element.Permeability;
import map.element.Sprite;
import map.element.interactions.Interactions;

public class Energy extends MotionlessElement {
	public Energy() {
		super(new Sprite("┌┐","crystal_ball.png"), Permeability.PENETRABLE, 'D');
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.PICKUP;
	}

}