package map.element.mobile;


import map.IOrientation;
import map.Orientation;
import map.element.Sprite;


public class Lorann extends Mobile implements IOrientation   {
	private Orientation orientation;
	
	public Lorann(Orientation orientation) {
		super(new Sprite("☺!","lorann.png"));
		this.orientation = orientation;
	}
	
	@Override
	public Orientation getOrientation() {
		// TODO Auto-generated method stub
		return this.orientation;
	}

	@Override
	public void setOrientation(Orientation orient) {
		this.orientation = orient;
		
		
	}
	

	
	
}
