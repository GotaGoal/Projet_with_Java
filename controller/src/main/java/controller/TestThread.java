package controller;


import java.io.IOException;

import contract.IOrderPerformed;
import contract.UserOrder;
import map.MapWorld;
import map.element.Sprite;
import view.MapFrame;
import view.MapView;

public class TestThread extends Thread  {
	private MapWorld map;
	private MapFrame frame;
	private MapPlay play;
	public TestThread(MapWorld iMapWorld,MapFrame map,MapPlay play)
	{
		this.play = play;
		this.map = iMapWorld;
		this.frame = map;
		
		System.out.println("coucou");
		this.start();
	}
	
	public synchronized void run(){
	   while(true)
	   {
		   int Random = (int)( Math.random()*( 5 - 1 + 1 ) ) + 1;
		   System.out.println("coucou");
		   try {
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   if (play.getKey() == true)
		   {
			   switch (Random)
			   {
			   case 1:
				   this.map.getLorann().setSprite(new Sprite("ll","loranngauche.png"));
				   this.frame.repaint();
				   break;
			   case 2:
				   this.map.getLorann().setSprite(new Sprite("ll","lorann_u.png"));
				   this.frame.repaint();
				   break;
			   case 3:
				   this.map.getLorann().setSprite(new Sprite("ll","lorann_r.png"));
				   this.frame.repaint();
				   break;
			   case 4:
				   this.map.getLorann().setSprite(new Sprite("ll","lorann_b.png"));
				   this.frame.repaint();
				   break;
			   }
			   
		   }
		   else
		   {
			   System.out.println("on est bien");
		   }
		   
		   
		   
	   }
	  }

	

	
}
