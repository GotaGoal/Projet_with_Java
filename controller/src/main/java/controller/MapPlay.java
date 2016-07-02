package controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import javax.swing.JLabel;
import javax.swing.Timer;

import contract.IMapFrame;
import contract.IMapPlay;
import contract.IMapWorld;
import contract.IOrderPerformed;
import contract.UserOrder;
import map.MapWorld;
import map.Orientation;
import map.element.Permeability;
import map.element.Sprite;
import map.element.mobile.Fireball;
import map.element.mobile.Lorann;
import map.element.mobile.MonsterFour;
import map.element.motionless.Energy;
import map.element.motionless.IDoActionOnHeroes;
import view.MapFrame;
import view.MapView;
import map.element.motionless.*;
import map.element.mobile.*;



public class MapPlay extends Thread implements contract.IOrderPerformed,IMapPlay,Runnable{
	
	public  IMapFrame mapFrame;
		private IMapWorld mapWorld;
		private int calcul_level = 1;
		private int score = 0;
		int Random;
		private Timer timer;
		private Timer timerKill;
		public int sensOne = 0;
		public int sensTwo = 0;
		public Orientation orientation;
		int orienFireball;
		private Boolean boolFireball = false;
		public Boolean key;
		
		
		
		

		public MapPlay(MapWorld mapWorld) throws InterruptedException {
			this.mapWorld = mapWorld;
			
			this.key = true;
			
			
			this.mapWorld.addMobile(new Lorann(Orientation.ND), this.mapWorld.getPointSpawnLorannX(), this.mapWorld.getPointSpawnLorannY());
			this.mapWorld.addElement(new Energy(), this.mapWorld.getPointEnergyX(), this.mapWorld.getPointEnergyY());
			this.mapWorld.addElement(new Gate_Close(), this.mapWorld.getPointGateCloseX(), this.mapWorld.getPointGateCloseY());
			this.mapWorld.addElement(new Treasure(), this.mapWorld.getPointTreasureX(), this.mapWorld.getPointTreasureY());
			
			
			/*
			//this.mapWorld.addMobile(new Fireball(), 1, 1);
			if(this.mapWorld.getM1X() == -1)
			{
				System.out.println("caca");
			}
			else
			{
				this.mapWorld.addMobile(new MonsterOne(), this.mapWorld.getM1X(), this.mapWorld.getM1Y());
			}
			if(this.mapWorld.getM2X() == -1)
			{
				System.out.println("caca");
			}
			else
			{
				this.mapWorld.addMobile(new MonsterTwo(), this.mapWorld.getM2X(), this.mapWorld.getM2Y());
			}
			if(this.mapWorld.getM3X() == -1)
			{
				System.out.println("caca");
			}
			else
			{
				this.mapWorld.addMobile(new MonsterThree(), this.mapWorld.getM3X(), this.mapWorld.getM3Y());
			}
			if(this.mapWorld.getM4X() == -1)
			{
				System.out.println("caca");
			}
			else
			{
				this.mapWorld.addMobile(new MonsterFour(), this.mapWorld.getM4X(), this.mapWorld.getM4Y());
			}
			
			
		    this.timer = createTimer (this);
		    this.startTimer();
		    */
		    this.timerKill  = createTimerForKill(this);
		    this.startTimerForKill();
			
		
		}
		
		@Override
		public  Boolean getKey()
		{
			return key;
		}
		@Override
		public  void setKey(final Boolean ky)
		{
			key = ky;
		}
		public void testThread() throws InterruptedException
		{
			while(true)
			{
			System.out.println("coucou");
			sleep(500);
			
			
			}
			
		}
		@Override
		public void startTimer(){
			this.timer.start();
		}
		@Override
		public Timer createTimer (final MapPlay mapPlay){
		    ActionListener action = new ActionListener()
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
		        	
		        	if (boolFireball){
		        		int RandomFire = (int)( Math.random()*( 5 - 1 + 1 ) ) + 1;
		        		if (RandomFire == 1){
							mapWorld.getFireball().setSprite(new Sprite("!","fireball.png"));	
				    	}
				    	if (RandomFire == 2){
							mapWorld.getFireball().setSprite(new Sprite("!","fireball_2.png"));
				    		
				    	}
				    	if (RandomFire == 3){
							mapWorld.getFireball().setSprite(new Sprite("!","fireball_3.png"));
				    		
				    	}
				    	if (RandomFire == 4){
							mapWorld.getFireball().setSprite(new Sprite("!","fireball_4.png"));
				    		
				    	}
				    	if (RandomFire == 5){
							mapWorld.getFireball().setSprite(new Sprite("!","fireball_5.png"));
				    		
				    	}
				    	
			    		
			    		if (mapWorld.getFireball().getY() == mapWorld.getLorann().getY() && mapWorld.getFireball().getX() == mapWorld.getLorann().getX()){
			    			

			    			mapWorld.getFireball().setX(1);
			    			mapWorld.getFireball().setY(1);
							mapWorld.getFireball().setSprite(new Sprite("!","land.png"));
			    			boolFireball = false;
			    		}
			    		
			    		
			    		
					    int oldYFireball = mapWorld.getFireball().getY();
					    int oldXFireball = mapWorld.getFireball().getX();
			    		switch (orientation) {
						case NORTH:
				    		mapWorld.getFireball().moveDown();
				    		if (mapWorld.getFireball().getY() == oldYFireball){
				    			orientation = Orientation.SOUTH;
				    		}
							break;
						case SOUTH:
				    		mapWorld.getFireball().moveUp();
				    		if (mapWorld.getFireball().getY() == oldYFireball){
				    			orientation = Orientation.NORTH;
				    		}
							break;
						case EAST:
				    		mapWorld.getFireball().moveLeft();
				    		if (mapWorld.getFireball().getX() == oldXFireball){
				    			orientation = Orientation.WEST;
				    		}
							break;
						case WEST:
				    		mapWorld.getFireball().moveRight();
				    		if (mapWorld.getFireball().getX() == oldXFireball){
				    			orientation = Orientation.EAST;
				    		}
							break;
						default:
							break;
			    		}
			    		mapPlay.mapFrame.repainture(1);
			    		
			    		if (mapPlay.mapWorld.getM1X() == -1)
			    		{
			    			if (mapWorld.getFireball().getY() == mapWorld.getMonsterFour().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterFour().getX()){
				    			
				    			
				    			mapWorld.getMonsterFour().setX(1);
				    			mapWorld.getMonsterFour().setY(1);
								mapWorld.getMonsterFour().setSprite(new Sprite("!","land.png"));
				    		}
							if (mapWorld.getFireball().getY() == mapWorld.getMonsterTwo().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterTwo().getX()){
				    			
				    			
				    			mapWorld.getMonsterTwo().setX(1);
				    			mapWorld.getMonsterTwo().setY(1);
								mapWorld.getMonsterTwo().setSprite(new Sprite("!","land.png"));
				    		}
							if (mapWorld.getFireball().getY() == mapWorld.getMonsterThree().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterThree().getX()){
				    			
				    			
				    			mapWorld.getMonsterThree().setX(1);
				    			mapWorld.getMonsterThree().setY(1);
								mapWorld.getMonsterThree().setSprite(new Sprite("!","land.png"));
				    		}
							mapPlay.mapFrame.repainture(1);
						
				        	
			    		}
			    		if (mapPlay.mapWorld.getM2X() == -1)
			    		{
			    			if (mapWorld.getFireball().getY() == mapWorld.getMonsterFour().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterFour().getX()){
				    			
				    			
				    			mapWorld.getMonsterFour().setX(1);
				    			mapWorld.getMonsterFour().setY(1);
								mapWorld.getMonsterFour().setSprite(new Sprite("!","land.png"));
				    		}
							
							if (mapWorld.getFireball().getY() == mapWorld.getMonsterThree().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterThree().getX()){
				    			
				    			
				    			mapWorld.getMonsterThree().setX(1);
				    			mapWorld.getMonsterThree().setY(1);
								mapWorld.getMonsterThree().setSprite(new Sprite("!","land.png"));
				    		}
							if (mapWorld.getFireball().getY() == mapWorld.getMonsterOne().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterOne().getX()){
				    			
				    			
				    			mapWorld.getMonsterOne().setX(1);
				    			mapWorld.getMonsterOne().setY(1);
								mapWorld.getMonsterOne().setSprite(new Sprite("!","land.png"));
				    		}
							mapPlay.mapFrame.repainture(1);
						
				        	
			    		}
			    		if (mapPlay.mapWorld.getM3X() == -1)
			    		{
			    			if (mapWorld.getFireball().getY() == mapWorld.getMonsterFour().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterFour().getX()){
				    			
				    			
				    			mapWorld.getMonsterFour().setX(1);
				    			mapWorld.getMonsterFour().setY(1);
								mapWorld.getMonsterFour().setSprite(new Sprite("!","land.png"));
				    		}
							if (mapWorld.getFireball().getY() == mapWorld.getMonsterOne().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterOne().getX()){
				    			
				    			
				    			mapWorld.getMonsterOne().setX(1);
				    			mapWorld.getMonsterOne().setY(1);
								mapWorld.getMonsterOne().setSprite(new Sprite("!","land.png"));
				    		}
							if (mapWorld.getFireball().getY() == mapWorld.getMonsterTwo().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterTwo().getX()){
				    			
				    			
				    			mapWorld.getMonsterTwo().setX(1);
				    			mapWorld.getMonsterTwo().setY(1);
								mapWorld.getMonsterTwo().setSprite(new Sprite("!","land.png"));
				    		}mapPlay.mapFrame.repainture(1);
			    		}
			    		if (mapPlay.mapWorld.getM4X() == -1)
			    		{
			    			if (mapWorld.getFireball().getY() == mapWorld.getMonsterThree().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterThree().getX()){
				    			
				    			
				    			mapWorld.getMonsterThree().setX(1);
				    			mapWorld.getMonsterThree().setY(1);
								mapWorld.getMonsterThree().setSprite(new Sprite("!","land.png"));
				    		}
			    			if (mapWorld.getFireball().getY() == mapWorld.getMonsterOne().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterOne().getX()){
				    			
				    			
				    			mapWorld.getMonsterOne().setX(1);
				    			mapWorld.getMonsterOne().setY(1);
								mapWorld.getMonsterOne().setSprite(new Sprite("!","land.png"));
				    		}
							if (mapWorld.getFireball().getY() == mapWorld.getMonsterTwo().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterTwo().getX()){
				    			
				    			
				    			mapWorld.getMonsterTwo().setX(1);
				    			mapWorld.getMonsterTwo().setY(1);
								mapWorld.getMonsterTwo().setSprite(new Sprite("!","land.png"));
				    		}mapPlay.mapFrame.repainture(1);
			    		}
			    		if(mapPlay.mapWorld.getM1X() > -1 && mapPlay.mapWorld.getM2X() > -1 &&mapPlay.mapWorld.getM3X() > -1 && mapPlay.mapWorld.getM4X() > -1){
			    		if (mapWorld.getFireball().getY() == mapWorld.getMonsterThree().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterThree().getX()){
			    			
			    			
			    			mapWorld.getMonsterThree().setX(1);
			    			mapWorld.getMonsterThree().setY(1);
							mapWorld.getMonsterThree().setSprite(new Sprite("!","land.png"));
			    		}
						if (mapWorld.getFireball().getY() == mapWorld.getMonsterOne().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterOne().getX()){
			    			
			    			
			    			mapWorld.getMonsterOne().setX(1);
			    			mapWorld.getMonsterOne().setY(1);
							mapWorld.getMonsterOne().setSprite(new Sprite("!","land.png"));
			    		}
						if (mapWorld.getFireball().getY() == mapWorld.getMonsterTwo().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterTwo().getX()){
			    			
			    			
			    			mapWorld.getMonsterTwo().setX(1);
			    			mapWorld.getMonsterTwo().setY(1);
							mapWorld.getMonsterTwo().setSprite(new Sprite("!","land.png"));
			    		}
						if (mapWorld.getFireball().getY() == mapWorld.getMonsterFour().getY() && mapWorld.getFireball().getX() == mapWorld.getMonsterFour().getX()){
			    			
			    			
			    			mapWorld.getMonsterFour().setX(1);
			    			mapWorld.getMonsterFour().setY(1);
							mapWorld.getMonsterFour().setSprite(new Sprite("!","land.png"));
			    		}
					    mapPlay.mapFrame.repainture(1);
					   
							
						
					
			        	}
		        	}
		        	
		        	if(mapPlay.mapWorld.getM1X() == -1)
		        	{
		        	int Random = (int)( Math.random()*( 5 - 1 + 1 ) ) + 1;
					
					int xLorann = mapWorld.getLorann().getX();
				    int yLorann = mapWorld.getLorann().getY();
				    int xMonsterFour = mapWorld.getMonsterFour().getX();
				    int yMonsterFour = mapWorld.getMonsterFour().getY();
				    //int saveXOne = mapWorld.getMonsterOne().getX();
				    int saveYTwo = mapWorld.getMonsterTwo().getY();
				   switch(Random)
				   {
				   case 1:
					   mapWorld.getMonsterThree().moveDown();
					   mapPlay.mapFrame.repainture(1);
					   break;
				   case 2:
					   mapWorld.getMonsterThree().moveUp();
					   mapPlay.mapFrame.repainture(1);
					   break;
				   case 3:
					   mapWorld.getMonsterThree().moveLeft();
					   mapPlay.mapFrame.repainture(1);
					   break;   
				   case 4:
					   mapWorld.getMonsterThree().moveRight();
					   mapPlay.mapFrame.repainture(1);
					   break;
					  default:
						  break;
					   
				   }
				 
				    
				    switch (mapPlay.sensTwo)
				    {
				    case 0:
				    	
				    	 mapWorld.getMonsterTwo().moveUp();
				    	 mapPlay.mapFrame.repainture(1);
				    	 if (saveYTwo ==mapWorld.getMonsterTwo().getY() )
				    	 {
				    		 mapPlay.sensTwo = 1;
				    	 }
				    	 break;
				    case 1 :
				    	
				    	 mapWorld.getMonsterTwo().moveDown();
				    	 mapPlay.mapFrame.repainture(1);
				    	 if (saveYTwo ==mapWorld.getMonsterTwo().getY() )
				    	 {
				    		 mapPlay.sensTwo = 0;
				    	 }
				    	 break;
				    default:
				    	break;
				    	
				    }
				    
				    mapPlay.mapFrame.repainture(1);
				   
						if (xMonsterFour > xLorann){
				    		mapWorld.getMonsterFour().moveLeft();

				    	}
				    	if (yMonsterFour > yLorann){
				    		mapWorld.getMonsterFour().moveUp();
				   
				    	}
				    	if (xMonsterFour < xLorann){
				    		mapWorld.getMonsterFour().moveRight();
				    	
				    		}
				    		
				    	if (yMonsterFour < yLorann){
				    		mapWorld.getMonsterFour().moveDown();
				    	
				    	}
					if(mapPlay.getActuelMapWorld().getMonsterFour().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterFour().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("t'es mort");
						mapPlay.mapFrame.repainture(1);
			    	
					
					}
					if(mapPlay.getActuelMapWorld().getMonsterTwo().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterTwo().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("t'es mort");
						mapPlay.mapFrame.repainture(1);
			    	
					
					}
					if(mapPlay.getActuelMapWorld().getMonsterThree().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterThree().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("t'es mort");
						mapPlay.mapFrame.repainture(1);
			    	
					
					}
		        	}
		        	
		        
		        if(mapPlay.mapWorld.getM2X() == -1)
	        	{
	        	int Random = (int)( Math.random()*( 5 - 1 + 1 ) ) + 1;
				
				int xLorann = mapWorld.getLorann().getX();
			    int yLorann = mapWorld.getLorann().getY();
			    int xMonsterFour = mapWorld.getMonsterFour().getX();
			    int yMonsterFour = mapWorld.getMonsterFour().getY();
			    int saveXOne = mapWorld.getMonsterOne().getX();
			    
			   switch(Random)
			   {
			   case 1:
				   mapWorld.getMonsterThree().moveDown();
				   mapPlay.mapFrame.repainture(1);
				   break;
			   case 2:
				   mapWorld.getMonsterThree().moveUp();
				   mapPlay.mapFrame.repainture(1);
				   break;
			   case 3:
				   mapWorld.getMonsterThree().moveLeft();
				   mapPlay.mapFrame.repainture(1);
				   break;   
			   case 4:
				   mapWorld.getMonsterThree().moveRight();
				   mapPlay.mapFrame.repainture(1);
				   break;
				  default:
					  break;
				   
			   }
			    
			    
			    switch (mapPlay.sensOne)
			    {
			    case 0:
			    	
			    	 mapWorld.getMonsterOne().moveLeft();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveXOne ==mapWorld.getMonsterOne().getX() )
			    	 {
			    		 mapPlay.sensOne = 1;
			    	 }
			    	 break;
			    case 1 :
			    	
			    	 mapWorld.getMonsterOne().moveRight();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveXOne ==mapWorld.getMonsterOne().getX() )
			    	 {
			    		 mapPlay.sensOne = 0;
			    	 }
			    	 break;
			    default:
			    	break;
			    	
			    }

			    
			   
			   
					if (xMonsterFour > xLorann){
			    		mapWorld.getMonsterFour().moveLeft();

			    	}
			    	if (yMonsterFour > yLorann){
			    		mapWorld.getMonsterFour().moveUp();
			   
			    	}
			    	if (xMonsterFour < xLorann){
			    		mapWorld.getMonsterFour().moveRight();
			    	
			    		}
			    		
			    	if (yMonsterFour < yLorann){
			    		mapWorld.getMonsterFour().moveDown();
			    	
			    	}
			    	if(mapPlay.getActuelMapWorld().getMonsterFour().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterFour().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("t'es mort");
						mapPlay.mapFrame.repainture(1);
			    	
					
					}
					if(mapPlay.getActuelMapWorld().getMonsterOne().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterOne().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("t'es mort");
						mapPlay.mapFrame.repainture(1);
			    	
					
					}
					if(mapPlay.getActuelMapWorld().getMonsterThree().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterThree().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("t'es mort");
						mapPlay.mapFrame.repainture(1);
			    	
					
					}
				
			
		}
		        
		        
		        if(mapPlay.mapWorld.getM3X() == -1)
	        	{
	        	int Random = (int)( Math.random()*( 5 - 1 + 1 ) ) + 1;
				
				int xLorann = mapWorld.getLorann().getX();
			    int yLorann = mapWorld.getLorann().getY();
			    int xMonsterFour = mapWorld.getMonsterFour().getX();
			    int yMonsterFour = mapWorld.getMonsterFour().getY();
			    int saveXOne = mapWorld.getMonsterOne().getX();
			    int saveYTwo = mapWorld.getMonsterTwo().getY();
			   
			    
			    
			    switch (mapPlay.sensOne)
			    {
			    case 0:
			    	
			    	 mapWorld.getMonsterOne().moveLeft();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveXOne ==mapWorld.getMonsterOne().getX() )
			    	 {
			    		 mapPlay.sensOne = 1;
			    	 }
			    	 break;
			    case 1 :
			    	
			    	 mapWorld.getMonsterOne().moveRight();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveXOne ==mapWorld.getMonsterOne().getX() )
			    	 {
			    		 mapPlay.sensOne = 0;
			    	 }
			    	 break;
			    default:
			    	break;
			    	
			    }
			   
			    switch (mapPlay.sensTwo)
			    {
			    case 0:
			    	
			    	 mapWorld.getMonsterTwo().moveUp();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveYTwo ==mapWorld.getMonsterTwo().getY() )
			    	 {
			    		 mapPlay.sensTwo = 1;
			    	 }
			    	 break;
			    case 1 :
			    	
			    	 mapWorld.getMonsterTwo().moveDown();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveYTwo ==mapWorld.getMonsterTwo().getY() )
			    	 {
			    		 mapPlay.sensTwo = 0;
			    	 }
			    	 break;
			    default:
			    	break;
			    	
			    }
			    
			    mapPlay.mapFrame.repainture(1);
			   
					if (xMonsterFour > xLorann){
			    		mapWorld.getMonsterFour().moveLeft();

			    	}
			    	if (yMonsterFour > yLorann){
			    		mapWorld.getMonsterFour().moveUp();
			   
			    	}
			    	if (xMonsterFour < xLorann){
			    		mapWorld.getMonsterFour().moveRight();
			    	
			    		}
			    		
			    	if (yMonsterFour < yLorann){
			    		mapWorld.getMonsterFour().moveDown();
			    	
			    	}
			    	if(mapPlay.getActuelMapWorld().getMonsterFour().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterFour().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("t'es mort");
						mapPlay.mapFrame.repainture(1);
			    	
					
					}
					if(mapPlay.getActuelMapWorld().getMonsterOne().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterOne().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("t'es mort");
						mapPlay.mapFrame.repainture(1);
			    	
					
					}
					if(mapPlay.getActuelMapWorld().getMonsterTwo().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterTwo().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("t'es mort");
						mapPlay.mapFrame.repainture(1);
			    	
					
					}
				
			
		}
		      
		        if(mapPlay.mapWorld.getM4X() == -1)
	        	{
	        	int Random = (int)( Math.random()*( 5 - 1 + 1 ) ) + 1;
				
				
			    int saveXOne = mapWorld.getMonsterOne().getX();
			    int saveYTwo = mapWorld.getMonsterTwo().getY();
			   switch(Random)
			   {
			   case 1:
				   mapWorld.getMonsterThree().moveDown();
				   mapPlay.mapFrame.repainture(1);
				   break;
			   case 2:
				   mapWorld.getMonsterThree().moveUp();
				   mapPlay.mapFrame.repainture(1);
				   break;
			   case 3:
				   mapWorld.getMonsterThree().moveLeft();
				   mapPlay.mapFrame.repainture(1);
				   break;   
			   case 4:
				   mapWorld.getMonsterThree().moveRight();
				   mapPlay.mapFrame.repainture(1);
				   break;
				  default:
					  break;
				   
			   }
			    
			    
			    switch (mapPlay.sensOne)
			    {
			    case 0:
			    	
			    	 mapWorld.getMonsterOne().moveLeft();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveXOne ==mapWorld.getMonsterOne().getX() )
			    	 {
			    		 mapPlay.sensOne = 1;
			    	 }
			    	 break;
			    case 1 :
			    	
			    	 mapWorld.getMonsterOne().moveRight();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveXOne ==mapWorld.getMonsterOne().getX() )
			    	 {
			    		 mapPlay.sensOne = 0;
			    	 }
			    	 break;
			    default:
			    	break;
			    	
			    }
			    
			    
			    switch (mapPlay.sensTwo)
			    {
			    case 0:
			    	
			    	 mapWorld.getMonsterTwo().moveUp();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveYTwo ==mapWorld.getMonsterTwo().getY() )
			    	 {
			    		 mapPlay.sensTwo = 1;
			    	 }
			    	 break;
			    case 1 :
			    	
			    	 mapWorld.getMonsterTwo().moveDown();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveYTwo ==mapWorld.getMonsterTwo().getY() )
			    	 {
			    		 mapPlay.sensTwo = 0;
			    	 }
			    	 break;
			    default:
			    	break;
			    	
			    }
			    if(mapPlay.getActuelMapWorld().getMonsterThree().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterThree().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
				{
					mapPlay.mapFrame.showMessage("t'es mort");
					mapPlay.mapFrame.repainture(1);
		    	
				
				}
				if(mapPlay.getActuelMapWorld().getMonsterOne().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterOne().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
				{
					mapPlay.mapFrame.showMessage("t'es mort");
					mapPlay.mapFrame.repainture(1);
		    	
				
				}
				if(mapPlay.getActuelMapWorld().getMonsterTwo().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterTwo().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
				{
					mapPlay.mapFrame.showMessage("t'es mort");
					mapPlay.mapFrame.repainture(1);
		    	
				
				}
			   
					
				
			
		}
		        if(mapPlay.mapWorld.getM1X() > -1 && mapPlay.mapWorld.getM2X() > -1 &&mapPlay.mapWorld.getM3X() > -1 && mapPlay.mapWorld.getM4X() > -1 )
	        	{
	        	int Random = (int)( Math.random()*( 5 - 1 + 1 ) ) + 1;
				
				int xLorann = mapWorld.getLorann().getX();
			    int yLorann = mapWorld.getLorann().getY();
			    int xMonsterFour = mapWorld.getMonsterFour().getX();
			    int yMonsterFour = mapWorld.getMonsterFour().getY();
			    int saveXOne = mapWorld.getMonsterOne().getX();
			    int saveYTwo = mapWorld.getMonsterTwo().getY();
			   switch(Random)
			   {
			   case 1:
				   mapWorld.getMonsterThree().moveDown();
				   mapPlay.mapFrame.repainture(1);
				   break;
			   case 2:
				   mapWorld.getMonsterThree().moveUp();
				   mapPlay.mapFrame.repainture(1);
				   break;
			   case 3:
				   mapWorld.getMonsterThree().moveLeft();
				   mapPlay.mapFrame.repainture(1);
				   break;   
			   case 4:
				   mapWorld.getMonsterThree().moveRight();
				   mapPlay.mapFrame.repainture(1);
				   break;
				  default:
					  break;
				   
			   }
			    
			    
			    switch (mapPlay.sensOne)
			    {
			    case 0:
			    	
			    	 mapWorld.getMonsterOne().moveLeft();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveXOne ==mapWorld.getMonsterOne().getX() )
			    	 {
			    		 mapPlay.sensOne = 1;
			    	 }
			    	 break;
			    case 1 :
			    	
			    	 mapWorld.getMonsterOne().moveRight();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveXOne ==mapWorld.getMonsterOne().getX() )
			    	 {
			    		 mapPlay.sensOne = 0;
			    	 }
			    	 break;
			    default:
			    	break;
			    	
			    }
			    
			    
			    switch (mapPlay.sensTwo)
			    {
			    case 0:
			    	
			    	 mapWorld.getMonsterTwo().moveUp();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveYTwo ==mapWorld.getMonsterTwo().getY() )
			    	 {
			    		 mapPlay.sensTwo = 1;
			    	 }
			    	 break;
			    case 1 :
			    	
			    	 mapWorld.getMonsterTwo().moveDown();
			    	 mapPlay.mapFrame.repainture(1);
			    	 if (saveYTwo ==mapWorld.getMonsterTwo().getY() )
			    	 {
			    		 mapPlay.sensTwo = 0;
			    	 }
			    	 break;
			    default:
			    	break;
			    	
			    }
			    if (xMonsterFour > xLorann){
		    		mapWorld.getMonsterFour().moveLeft();

		    	}
		    	if (yMonsterFour > yLorann){
		    		mapWorld.getMonsterFour().moveUp();
		   
		    	}
		    	if (xMonsterFour < xLorann){
		    		mapWorld.getMonsterFour().moveRight();
		    	
		    		}
		    		
		    	if (yMonsterFour < yLorann){
		    		mapWorld.getMonsterFour().moveDown();
		    	
		    	}
		    	if(mapPlay.getActuelMapWorld().getMonsterThree().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterThree().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
				{
					mapPlay.mapFrame.showMessage("t'es mort");
					mapPlay.mapFrame.repainture(1);
		    	
				
				}
				if(mapPlay.getActuelMapWorld().getMonsterOne().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterOne().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
				{
					mapPlay.mapFrame.showMessage("t'es mort");
					mapPlay.mapFrame.repainture(1);
		    	
				
				}
				if(mapPlay.getActuelMapWorld().getMonsterTwo().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterTwo().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
				{
					mapPlay.mapFrame.showMessage("t'es mort");
					mapPlay.mapFrame.repainture(1);
		    	
				
				}
				if(mapPlay.getActuelMapWorld().getMonsterFour().getX() == mapPlay.getActuelMapWorld().getLorann().getX() && mapPlay.getActuelMapWorld().getMonsterFour().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
				{
					mapPlay.mapFrame.showMessage("t'es mort");
					mapPlay.mapFrame.repainture(1);
		    	
				
				}
	        	}
		        mapPlay.mapFrame.repainture(1);
		        
		        
		        }
		        
	        	
	        	
		        
		      };
		      
		    return new Timer(500, action);
		  }
		@Override
		public void startTimerForKill(){
			this.timerKill.start();
		}
		@Override
		
		public Timer createTimerForKill (final MapPlay mapPlay){
		    ActionListener test = new ActionListener()
		      {
		        public void actionPerformed (ActionEvent event)
		        {
		        	try {
						this.performance();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        private void performance() throws IOException {
		        	mapPlay.mapWorld.getLorann().setSprite(new Sprite("☺!","loranngauche.png"));
		        	mapPlay.mapWorld.setLorann(mapWorld.getLorann());
		        	
				
		        }
		        
		      };
		      
		    return new Timer(500, test);
		  }
		@Override
		public IMapWorld getMapWorld()
		{
			return this.mapWorld;
			
		}
		@Override
		public IMapFrame getMapFrame() {
			return this.mapFrame;
		}
		@Override
		public void setMapFrame(final IMapFrame mapFrame) {
			this.mapFrame = mapFrame;
		}
		@Override
		public IMapWorld getActuelMapWorld() {
			
			return this.getMapWorld();
		}
		@Override
		public synchronized void  orderPerform(contract.UserOrder userOrder) throws IOException {
			this.setKey(false);
				
				switch (userOrder) {
					case UP:
						this.getActuelMapWorld().getLorann().moveUp();
						this.getActuelMapWorld().getLorann().setOrientation(Orientation.NORTH);
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_u.png"));
						this.setKey(true);
						break;
						
					case RIGHT:
						this.getActuelMapWorld().getLorann().moveRight();
						this.getActuelMapWorld().getLorann().setOrientation(Orientation.EAST);
						System.out.println(String.valueOf(this.getMapWorld().getLorann().getOrientation()));
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_r.png"));
						this.setKey(true);
						break;
						
					case DOWN:
						this.getActuelMapWorld().getLorann().moveDown();
						this.getActuelMapWorld().getLorann().setOrientation(Orientation.SOUTH);
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_b.png"));
						this.setKey(true);
						break;
						
					case LEFT:
						this.getActuelMapWorld().getLorann().moveLeft();
						this.getActuelMapWorld().getLorann().setOrientation(Orientation.WEST);
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","loranngauche.png"));
						this.setKey(true);
						break;
						
					
						
					
					case E:
						if (boolFireball == false){
							orientation = this.getActuelMapWorld().getLorann().getOrientation();
							switch (orientation) {
								case NORTH:
									this.mapWorld.addMobile(new Fireball(), mapWorld.getLorann().getX(), mapWorld.getLorann().getY()+1);
									break;
								case SOUTH:
									this.mapWorld.addMobile(new Fireball(), mapWorld.getLorann().getX(), mapWorld.getLorann().getY()-1);
									break;
								case EAST:
									this.mapWorld.addMobile(new Fireball(), mapWorld.getLorann().getX()-1, mapWorld.getLorann().getY());
									break;
								case WEST:
									this.mapWorld.addMobile(new Fireball(), mapWorld.getLorann().getX()+1, mapWorld.getLorann().getY());
									break;
							default:
								break;
							}
							
							boolFireball = true;
							}
						break;
					case NOP:
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","loranngauche.png"));
						System.out.println("on est al");
						break;
						
					default:
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","loranngauche.png"));
						break;
						
						
				
			}
				this.getWorldAnswer();
				
				
				
				
				
			
		}
		
		@Override
		public  void run() {
			this.mapWorld.getLorann().setSprite(new Sprite("☺!","loranngauche.png"));
			
			
			
		}
		@Override
		public void getWorldAnswer() throws IOException {
			final IDoActionOnHeroes element = this.getActuelMapWorld().getElements(this.getActuelMapWorld().getLorann().getX(),
					this.getActuelMapWorld().getLorann().getY());

			switch (element.getActionOnHeroes()) {
				case UP:
					
					this.mapFrame.showMessage("You enter in a New Level.");
					
					this.resolveUp();
					break;
				case PICKUP_ENERGY:
					this.mapFrame.showMessage("You have found the energy");
					this.score+=100;
					mapFrame.setTextChange();
					
					this.resolvePickUpEnergy();
					break;
				case PICKUP_TREASURE:
					this.mapFrame.showMessage("You have found the treasure");
					this.score+=200;
					mapFrame.setTextChange();
					
					this.resolvePickUpTreasure();
					break;
				
				/*case PICKUP_FIREBALL:
					this.mapFrame.showMessage("You have found the treasure");
					this.score+=200;
					mapFrame.setTextChange();
					
					this.resolvePickUpFireball();
					break;*/
					
				case EXIT:
					this.mapFrame.showMessage("You go back now");
					this.resolveExit();
					break;
				case DIE:
					this.mapFrame.showMessage("You are die");
					break;
				
				
				default:
					break;
			}
		}
		@Override
		public void resolveUp() throws IOException {
			this.calcul_level = this.calcul_level+1;
			
			this.setMapWorld(new MapWorld(this.calcul_level));
			
			
			
			this.resolveWorldAnswer();
		}
		@Override
		public void resolveExit() throws IOException {
			this.calcul_level = this.calcul_level-1;
			
			this.setMapWorld(new MapWorld(this.calcul_level));
			
			
			
			this.resolveWorldAnswer();
		}
		@Override
		public void resolvePickUpFireball() throws IOException {
			this.mapWorld.addElement(new Land(), this.mapWorld.getFireball().getX(), this.mapWorld.getFireball().getY());
			this.resolveWorldAnswer();
		}
		@Override
		public void resolvePickUpEnergy() throws IOException {
			
			
			this.mapWorld.addElement(new Land(), this.mapWorld.getPointEnergyX(), this.mapWorld.getPointEnergyY());
			
			this.mapWorld.addElement(new Gate_Open_Level_Up() , this.mapWorld.getPointGateCloseX(), this.mapWorld.getPointGateCloseY());
			//this.mapWorld.removeObject(this.energy);
			
			
		}
		@Override
		public void resolvePickUpTreasure() throws IOException {
			
			
			this.mapWorld.addElement(new Land(), this.mapWorld.getPointTreasureX(), this.mapWorld.getPointTreasureY());
			
			
			//this.mapWorld.removeObject(this.energy);
			
			
		}
		@Override
		public void resolveWorldAnswer() throws IOException {
			
			this.getMapWorld().addMobile(new Lorann(Orientation.ND), this.getActuelMapWorld().getPointSpawnLorannX(), this.getActuelMapWorld().getPointSpawnLorannY());
			this.getMapWorld().addElement(new Energy(), this.getActuelMapWorld().getPointEnergyX(), this.getActuelMapWorld().getPointEnergyY());
			this.getMapWorld().addElement(new Gate_Open(), this.getActuelMapWorld().getPointGateOpenX(), this.getActuelMapWorld().getPointGateOpenY());
			this.getMapWorld().addElement(new Gate_Close(), this.getActuelMapWorld().getPointGateCloseX(),this.getActuelMapWorld().getPointGateCloseY());
			this.getMapWorld().addElement(new Treasure(), this.getActuelMapWorld().getPointTreasureX(),this.getActuelMapWorld().getPointTreasureY());
			if(this.getMapWorld().getM1X() == -1)
			{
				System.out.println("caca");
			}
			else
			{
				this.getMapWorld().addMobile(new MonsterOne(), this.mapWorld.getM1X(), this.mapWorld.getM1Y());
			}
			if(this.getMapWorld().getM2X() == -1)
			{
				System.out.println("caca");
			}
			else
			{
				this.getMapWorld().addMobile(new MonsterTwo(), this.mapWorld.getM2X(), this.mapWorld.getM2Y());
			}
			if(this.getMapWorld().getM3X() == -1)
			{
				System.out.println("caca");
			}
			else
			{
				this.getMapWorld().addMobile(new MonsterThree(), this.mapWorld.getM3X(), this.mapWorld.getM3Y());
			}
			if(this.getMapWorld().getM4X() == -1)
			{
				System.out.println("caca");
			}
			else
			{
				this.getMapWorld().addMobile(new MonsterFour(), this.mapWorld.getM4X(), this.mapWorld.getM4Y());
			}
			this.getMapFrame().setMeeting(this.getMapWorld());
			
			System.out.println("je suis la ");
			this.getMapFrame();
			this.mapFrame.repainture(1);
			
			
		}

		@Override
		public void setMapWorld(IMapWorld map) {
			this.mapWorld = map;
			
		}

		@Override
		public int getScore() {
			// TODO Auto-generated method stub
			return this.score;
		}
		public int getCalcul()
		{
			System.out.println(String.valueOf(this.calcul_level));
			return this.calcul_level;
		}

		
		
}



