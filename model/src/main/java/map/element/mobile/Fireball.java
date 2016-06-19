package map.element.mobile;


import map.Orientation;
import map.element.Sprite;
import map.element.motionless.ActionOnHeroes;
import map.element.motionless.IDoActionOnHeroes;

public class Fireball extends Mobile implements IDoActionOnHeroes   {
	
	
	public Fireball(Orientation orientation) {
		super(new Sprite("☺!","fireball.png"));
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.PICKUP_FIREBALL;
	}

	
	

}
