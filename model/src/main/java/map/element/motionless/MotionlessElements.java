package map.element.motionless;

public abstract class MotionlessElements {
	public static final MotionlessElement	BONE_V			= new Bone_V();
	public static final MotionlessElement	BONE_H		= new Bone_H();
	public static final MotionlessElement	LAND			= new Land();
	public static final MotionlessElement	BONE	= new Bone();
	public static final MotionlessElement	GATE_OPEN	= new Gate_Open();
	public static final MotionlessElement	GATE_CLOSE	= new Gate_Close();
	public static final MotionlessElement	GATE_OPEN_LEVEL_UP	= new Gate_Open_Level_Up();
	public static final MotionlessElement	TREASURE	= new Treasure();
	public static final MotionlessElement	ENERGY	= new Energy();
	
	
	
	
	private static MotionlessElement			motionlessElements[]	= { BONE_V, BONE_H, LAND, BONE, GATE_OPEN, GATE_CLOSE, GATE_OPEN_LEVEL_UP, TREASURE, ENERGY };

	public static MotionlessElement getFromFileSymbol(final char fileSymbol) {
		for (final MotionlessElement motionlessElement : motionlessElements) {
			if (motionlessElement.getFileSymbol() == fileSymbol) {
				return motionlessElement;
			}
		}
		return LAND;
	}
}