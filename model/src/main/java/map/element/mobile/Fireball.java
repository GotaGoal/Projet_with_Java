package map.element.mobile;


import map.Orientation;
import map.element.Permeability;
import map.element.Sprite;
import map.element.interactions.Interactions;
import map.element.motionless.ActionOnHeroes;
import map.element.motionless.IDoActionOnHeroes;

public class Fireball extends Mobile implements IDoActionOnHeroes   {
	private Orientation orientation;
	
	
	public Fireball(Orientation orientation) {
		super(new Sprite("☺!","fireball.png"));
		this.setOrientation(orientation);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.PICKUP_FIREBALL;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	

	
	

}
