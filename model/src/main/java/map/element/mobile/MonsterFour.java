package map.element.mobile;
import map.element.Sprite;
import map.element.motionless.ActionOnHeroes;
import map.element.motionless.IDoActionOnHeroes;
public class MonsterFour extends Mobile implements IDoActionOnHeroes {
	
	
	
	public MonsterFour() {
		super(new Sprite("  ","monster_4.png"));
	}

	@Override
	public ActionOnHeroes getActionOnHeroes() {
		// TODO Auto-generated method stub
		return ActionOnHeroes.DIE;
	}
}
