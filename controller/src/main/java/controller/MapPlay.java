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
import contract.ITestThread;
import contract.UserOrder;
import map.MapWorld;
import map.Orientation;
import map.element.Permeability;
import map.element.Sprite;
import map.element.mobile.Fireball;
import map.element.mobile.Lorann;
import map.element.motionless.Energy;
import map.element.motionless.IDoActionOnHeroes;
import view.MapFrame;
import view.MapView;
import map.element.motionless.*;
import map.element.mobile.*;



public class MapPlay extends Thread implements contract.IOrderPerformed,IMapPlay,Runnable,ITestThread{
	
	private Timer timerFireball;
	public  MapFrame mapFrame;
		private IMapWorld mapWorld;
		private int calcul_level = 1;
		private int score = 0;
		int Random;
		private Timer timer;
		private Timer timerVerif;
		public int sensOne = 0;
		public int sensTwo = 0;
		public Orientation orientation;
		int orienFireball;
		private Boolean boolFireball = false;
		public Boolean key;
		
		public MapPlay map;
		private Monster monsterOne;
		private Monster monsterTwo;
		private Monster monsterThree;
		private Monster monsterFour;
		private int exist1=0;
		private int exist2=0;
		private int exist3=0;
		private int exist4=0;
		private Monster monster;
		public MapPlay(MapWorld mapWorld) throws InterruptedException {
			this.mapWorld = mapWorld;
			
			this.key = true;
			
			this.initMonstre();
			this.mapWorld.addMobile(new Lorann(Orientation.ND), this.mapWorld.getPointSpawnLorannX(), this.mapWorld.getPointSpawnLorannY());
			this.mapWorld.addElement(new Energy(), this.mapWorld.getPointEnergyX(), this.mapWorld.getPointEnergyY());
			this.mapWorld.addElement(new Gate_Close(), this.mapWorld.getPointGateCloseX(), this.mapWorld.getPointGateCloseY());
			this.mapWorld.addElement(new Treasure(), this.mapWorld.getPointTreasureX(), this.mapWorld.getPointTreasureY());
			this.timerFireball = this.createTimerFireball(this);
			this.startTimerFireball();
		    this.timer = createTimer (this);
		    this.startTimer();
		    this.timerVerif = createTimerVerif(this);
		    this.startTimerVerif();

		}
		public void initMonstre()
		{
			if (this.mapWorld.getM1X() != -1)
			{
				this.monsterOne = new Monster(new Sprite("1","monster_1.png"),(MapWorld) this.mapWorld,1);	
				this.mapWorld.addMobile(this.monsterOne, this.mapWorld.getM1X(), this.mapWorld.getM1Y());
				this.exist1 = 1;
				
			}
			if(this.mapWorld.getM2X() != -1)
			{
				
				this.monsterTwo = new Monster(new Sprite("2","monster_2.png"),(MapWorld) this.mapWorld,2);
				this.mapWorld.addMobile(this.monsterTwo, this.mapWorld.getM2X(), this.mapWorld.getM2Y());
				this.exist2 = 2;
				
			}
			
			if(this.mapWorld.getM3X() != -1)
			{
				this.monsterThree = new Monster(new Sprite("3","monster_3.png"),(MapWorld) this.mapWorld,3);
				this.mapWorld.addMobile(this.monsterThree, this.mapWorld.getM3X(), this.mapWorld.getM3Y());
				this.exist3=3;
				
			}
			if (this.mapWorld.getM4X() != -1)
			{
				this.monsterFour = new Monster(new Sprite("2","monster_4.png"),(MapWorld) this.mapWorld,4);
				this.mapWorld.addMobile(this.monsterFour, this.mapWorld.getM4X(), this.mapWorld.getM4Y());
			this.exist4 = 4;
				
			}

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
		        	
		        	mapPlay.deplacementMonstre();
			    	mapPlay.mapFrame.repainture(1);
			    			
		        	}
		        
		        	
		        	
		        
		      };
		      
		    return new Timer(500, action);
		  }
		
		
		public void startTimerVerif(){
			this.timerVerif.start();
		}
		
		public Timer createTimerVerif (final MapPlay mapPlay){
		    ActionListener action3 = new ActionListener()
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
		        	if (boolFireball)
		        	{
		        		if(exist1 == 1 && mapWorld.getFireball().getY() == mapPlay.monsterOne.getY() && mapPlay.monsterOne.getX() == mapWorld.getFireball().getX() )
		        		{
		        			mapPlay.monsterOne.setIdMonster(0);
							mapPlay.deplacementMonstre();
							mapPlay.monsterOne.setSprite(new Sprite(" ","land.png"));
							mapPlay.monsterOne =null;
				        	mapPlay.mapFrame.repainture(1);
							exist1 =0;
							
							
		        		}
		        		if(exist2 == 2 && mapWorld.getFireball().getY() == mapPlay.monsterTwo.getY() && mapPlay.monsterTwo.getX() == mapWorld.getFireball().getX() )
		        		{
		        			mapPlay.monsterTwo.setIdMonster(0);
							mapPlay.deplacementMonstre();
							mapPlay.monsterTwo.setSprite(new Sprite(" ","land.png"));
							mapPlay.monsterTwo =null;
				        	mapPlay.mapFrame.repainture(1);
							exist2 =0;
						
		        		}
		        		if(exist3 == 3 && mapWorld.getFireball().getY() == mapPlay.monsterThree.getY() && mapPlay.monsterThree.getX() == mapWorld.getFireball().getX() )
		        		{
		        			mapPlay.monsterThree.setIdMonster(0);
							mapPlay.deplacementMonstre();
							mapPlay.monsterThree.setSprite(new Sprite(" ","land.png"));
							mapPlay.monsterThree =null;
				        	mapPlay.mapFrame.repainture(1);
							exist3 =0;
							
		        		}
		        		if(exist4 == 4 && mapWorld.getFireball().getY() == mapPlay.monsterFour.getY() && mapPlay.monsterFour.getX() == mapWorld.getFireball().getX() )
		        		{
		        			mapPlay.monsterFour.setIdMonster(0);
							mapPlay.deplacementMonstre();
							mapPlay.monsterFour.setSprite(new Sprite(" ","land.png"));
							mapPlay.monsterFour =null;
				        	mapPlay.mapFrame.repainture(1);
							exist4 =0;
							
		        		}
		        		if (mapWorld.getFireball().getY() == mapWorld.getLorann().getY() && mapWorld.getFireball().getX() == mapWorld.getLorann().getX()){
			    			mapWorld.getFireball().setX(0);
			    			mapWorld.getFireball().setY(0);
							mapWorld.getFireball().setSprite(new Sprite("!","bone.png"));
				        	mapPlay.mapFrame.repainture(1);
			    			boolFireball = false;
			    		}
		        		
		        		
		        		
		        	}
		        	if(mapPlay.exist1== 1 && mapPlay.monsterOne.getX() == mapPlay.mapWorld.getLorann().getX() && mapPlay.monsterOne.getY() == mapPlay.mapWorld.getLorann().getY())
		        	{
		        	
						mapPlay.monsterOne.setIdMonster(0);
						mapPlay.deplacementMonstre();
						mapPlay.monsterOne.setSprite(new Sprite(" ,land.png"));
						mapPlay.mapFrame.showMessage("Vous êtes mort au niveau "+mapPlay.calcul_level+". Fermeture du jeu");
						mapPlay.monsterOne =null;
			        	mapPlay.mapFrame.repainture(1);
						mapPlay.resolveDie();
		        	}
		        	
		        	if(mapPlay.exist2 == 2 && mapPlay.monsterTwo.getX() == mapPlay.mapWorld.getLorann().getX() && mapPlay.monsterTwo.getY() == mapPlay.mapWorld.getLorann().getY())
		        	{
		        	
		        		mapPlay.monsterTwo.setIdMonster(0);
						mapPlay.deplacementMonstre();
						mapPlay.monsterTwo.setSprite(new Sprite(" ,land.png"));
						mapPlay.mapFrame.showMessage("Vous êtes mort au niveau "+mapPlay.calcul_level+". Fermeture du jeu");
						mapPlay.monsterTwo =null;
			        	mapPlay.mapFrame.repainture(1);
						mapPlay.resolveDie();
		        	}
		        	
		        	if(mapPlay.exist3 == 3 && mapPlay.monsterThree.getX() == mapPlay.mapWorld.getLorann().getX() && mapPlay.monsterThree.getY() == mapPlay.mapWorld.getLorann().getY())
		        	{
		        	
		        		mapPlay.monsterThree.setIdMonster(0);
						mapPlay.deplacementMonstre();
						mapPlay.monsterThree.setSprite(new Sprite(" ,land.png"));
						mapPlay.mapFrame.showMessage("Vous êtes mort au niveau "+mapPlay.calcul_level+". Fermeture du jeu");
						mapPlay.monsterThree =null;
			        	mapPlay.mapFrame.repainture(1);
						mapPlay.resolveDie();
		        	}
		        	if(mapPlay.exist4 == 4 && mapPlay.monsterFour.getX() == mapPlay.mapWorld.getLorann().getX() && mapPlay.monsterFour.getY() == mapPlay.mapWorld.getLorann().getY())
		        	{
		        	
		        		mapPlay.monsterFour.setIdMonster(0);
						mapPlay.deplacementMonstre();
						mapPlay.monsterFour.setSprite(new Sprite(" ,land.png"));
						mapPlay.mapFrame.showMessage("Vous êtes mort au niveau "+mapPlay.calcul_level+". Fermeture du jeu");
						mapPlay.monsterFour =null;
			        	mapPlay.mapFrame.repainture(1);
						mapPlay.resolveDie();
						
		        	}

			    		
		        	}		        
		      };
		      
		    return new Timer(1, action3);
		  }
		
		
		
		
		public void startTimerFireball(){
			this.timerFireball.start();
		}
		
		public Timer createTimerFireball (final MapPlay mapPlay){
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
		        	}
		        }
		      };
		      
		    return new Timer(499, action2);
		  }
		@Override
		public void deplacementMonstre()
		{
			if (exist1 == 1)
        	{
				this.monsterOne.deplacement(this.monsterOne.getIdMonster());
	    		this.mapFrame.repainture(1);
        	}
			
        	if (exist2  == 2)
        	{
        		this.monsterTwo.deplacement(this.monsterTwo.getIdMonster());
    			this.mapFrame.repainture(1);
        	}
        	
        	if (exist3 ==3)
        	{
        		this.monsterThree.deplacement(this.monsterThree.getIdMonster());
    			this.mapFrame.repainture(1);
        	}
        	
        	if (exist4  == 4)
        	{
        		this.monsterFour.deplacement(this.monsterFour.getIdMonster());
    			this.mapFrame.repainture(1);
    			
        	}
			
		}
		@Override
		public IMapWorld getMapWorld()
		{
			return this.mapWorld;
			
		}
		@Override
		public MapFrame getMapFrame() {
			return this.mapFrame;
		}
		@Override
		public void setMapFrame(final MapFrame mapFrame) {
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
					
						
					default:
						
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
			this.initThread((MapWorld) this.getActuelMapWorld(), this.mapFrame, this);
			this.monsterOne = null;
			this.monsterTwo = null;
			this.monsterThree = null;
			this.monsterFour = null;
			this.exist1 = 0;
			this.exist2 =0;
			this.exist3 = 0;
			this.exist4 = 0;
			this.resolveWorldAnswer();
		}
		@Override
		public void resolveExit() throws IOException {
			this.calcul_level = this.calcul_level-1;
			
			this.setMapWorld(new MapWorld(this.calcul_level));
			this.initThread((MapWorld) this.getActuelMapWorld(), this.mapFrame, this);

			this.monsterOne = null;
			this.monsterTwo = null;
			this.monsterThree = null;
			this.monsterFour = null;
			this.exist1 = 0;
			this.exist2 =0;
			this.exist3 = 0;
			this.exist4 = 0;
			this.resolveWorldAnswer();
		}
		@Override
		public void resolvePickUpFireball() throws IOException {
			this.mapWorld.addElement(new Land(), this.mapWorld.getFireball().getX(), this.mapWorld.getFireball().getY());
			
		}
		@Override
		public void resolvePickUpEnergy() throws IOException {
			
			
			this.mapWorld.addElement(new Land(), this.mapWorld.getPointEnergyX(), this.mapWorld.getPointEnergyY());
			
			this.mapWorld.addElement(new Gate_Open_Level_Up() , this.mapWorld.getPointGateCloseX(), this.mapWorld.getPointGateCloseY());
		
			
			
		}
		@Override
		public void resolvePickUpTreasure() throws IOException {
			
			
			this.mapWorld.addElement(new Land(), this.mapWorld.getPointTreasureX(), this.mapWorld.getPointTreasureY());
	
		}
		
		public void resolveDie() throws IOException {

			System.exit(0);

		}
		@Override
		public void resolveWorldAnswer() throws IOException {
			
			
			this.getMapWorld().addElement(new Energy(), this.getActuelMapWorld().getPointEnergyX(), this.getActuelMapWorld().getPointEnergyY());
			this.getMapWorld().addElement(new Gate_Open(), this.getActuelMapWorld().getPointGateOpenX(), this.getActuelMapWorld().getPointGateOpenY());
			this.getMapWorld().addElement(new Gate_Close(), this.getActuelMapWorld().getPointGateCloseX(),this.getActuelMapWorld().getPointGateCloseY());
			this.getMapWorld().addElement(new Treasure(), this.getActuelMapWorld().getPointTreasureX(),this.getActuelMapWorld().getPointTreasureY());
			this.exist1 = 0;
			this.exist2 =0;
			this.exist3 = 0;
			this.exist4 = 0;
			
			this.initMonstre();
			this.getMapWorld().addMobile(new Lorann(Orientation.ND), this.getActuelMapWorld().getPointSpawnLorannX(), this.getActuelMapWorld().getPointSpawnLorannY());
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

		@Override
		public TestThread initThread(MapWorld mapWorld, MapFrame mapFrame, MapPlay mapPlay) {
			TestThread t = new TestThread(mapWorld,mapFrame,mapPlay);
			return t;
		}

		
		
}



