package map.element.mobile;

import map.element.Sprite;
import map.element.motionless.ActionOnHeroes;
import map.element.motionless.IDoActionOnHeroes;

public class MonsterTwo extends Mobile implements IDoActionOnHeroes {
	
	
	
	public MonsterTwo() {
		super(new Sprite("  ","monster_2.png"));
	}

	
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.DIE;
	}
}
