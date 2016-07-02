package map.element.mobile;

import map.element.Sprite;
import map.element.motionless.ActionOnHeroes;
import map.element.motionless.IDoActionOnHeroes;

public class MonsterOne extends Mobile implements IDoActionOnHeroes {
	
	
	
	public MonsterOne() {
		super(new Sprite("  ","monster_1.png"));
	}

	
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.DIE;
	}
}
