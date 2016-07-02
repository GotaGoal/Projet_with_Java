package map.element.mobile;
import map.element.Sprite;
import map.element.motionless.ActionOnHeroes;
import map.element.motionless.IDoActionOnHeroes;
public class MonsterThree extends Mobile implements IDoActionOnHeroes {
	
	
	
	public MonsterThree() {
		super(new Sprite("  ","monster_4.png"));
	}

	
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.DIE;
	}
}
