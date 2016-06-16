package map.element.motionless;

public abstract class MotionlessElements {
	public static final MotionlessElement	CAMP			= new Camp();
	public static final MotionlessElement	FOREST		= new Forest();
	public static final MotionlessElement	LAND			= new Land();
	public static final MotionlessElement	MONASTERY	= new Monastery();
	public static final MotionlessElement	STONE			= new Stone();
	public static final MotionlessElement	TOWN			= new Town();
	public static final MotionlessElement	WATER			= new Water();
	
	private static MotionlessElement			motionlessElements[]	= { CAMP, FOREST, LAND, MONASTERY, STONE, TOWN, WATER };

	public static MotionlessElement getFromFileSymbol(final char fileSymbol) {
		for (final MotionlessElement motionlessElement : motionlessElements) {
			if (motionlessElement.getFileSymbol() == fileSymbol) {
				return motionlessElement;
			}
		}
		return LAND;
	}
}