package map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import contract.IMapFrame;
import controller.MapPlay;
import map.element.mobile.Lorann;
import map.element.mobile.MonsterFour;
import map.element.motionless.Energy;
import map.element.motionless.Gate_Close;
import map.element.motionless.Treasure;
import view.MapFrame;

public class Animation  {
	private MapWorld mapWorld;
	private MapFrame menuFrame;
	private Timer timer;
	private Lorann lorann;
	
	public Animation(MapWorld mapWorld) throws InterruptedException
	{
		this.mapWorld = mapWorld;
		
		
		this.lorann = new Lorann(Orientation.ND);
		
		this.mapWorld.addMobile(this.lorann, 1, 9);
		this.timer = createTimer (this);
		
	    
		
		
	    
	}
	
	public void Tracer() throws InterruptedException
	{
		
			this.startTimer();
		
		
	}
	public MapFrame getMapFrame() {
		return this.menuFrame;
	}
	
	public void setMapFrame(final MapFrame mapFrame) {
		this.menuFrame = mapFrame;
	}
	
	public void startTimer(){
		this.timer.start();
	}
	
	public Timer createTimer (final Animation animation){
	    ActionListener action2 = new ActionListener()
	      {
	        public void actionPerformed (ActionEvent event)
	        {
	        	try {
					this.timePerform();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        private void timePerform() throws IOException {
	        	
				animation.mapWorld.getLorann().moveUp();
			
		}
	      };
	      
	    return new Timer(500, action2);
	  }

}
