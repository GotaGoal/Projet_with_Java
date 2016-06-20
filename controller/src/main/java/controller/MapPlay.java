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
import map.MapWorld;
import map.Orientation;

import map.element.Sprite;
import map.element.mobile.Fireball;
import map.element.mobile.Lorann;
import map.element.mobile.MonsterFour;
import map.element.motionless.Energy;
import map.element.motionless.IDoActionOnHeroes;
import view.MapFrame;
import map.element.motionless.*;
import map.element.mobile.*;



public class MapPlay implements contract.IOrderPerformed,IMapPlay{
	 public  IMapFrame mapFrame;
		private IMapWorld mapWorld;
		private int calcul_level = 1;
		private int score = 0;
		int Random;
		private Timer timer;
		private Timer timerKill;
		

		public MapPlay(final MapWorld mapWorld) {
			this.mapWorld = mapWorld;
			
			
			
			
			this.mapWorld.addMobile(new Lorann(Orientation.ND), this.mapWorld.getPointSpawnLorannX(), this.mapWorld.getPointSpawnLorannY());
			this.mapWorld.addElement(new Energy(), this.mapWorld.getPointEnergyX(), this.mapWorld.getPointEnergyY());
			this.mapWorld.addElement(new Gate_Close(), this.mapWorld.getPointGateCloseX(), this.mapWorld.getPointGateCloseY());
			this.mapWorld.addElement(new Treasure(), this.mapWorld.getPointTreasureX(), this.mapWorld.getPointTreasureY());
			this.mapWorld.addMobile(new MonsterFour(), 5, 4);
		    this.timer = createTimer (this);
		    this.startTimer();
		    this.timerKill  = this.createTimerForKill(this);
		    this.startTimerForKill();
			
		
		}
		
		public void startTimer(){
			this.timer.start();
		}
		
		public Timer createTimer (final MapPlay mapPlay){
		    ActionListener action = new ActionListener()
		      {
		    	
				private int Random;
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
					System.out.println("gros poupouch");
					int xLorann = mapWorld.getLorann().getX();
				    int yLorann = mapWorld.getLorann().getY();
				    int xMonsterFour = mapWorld.getMonsterFour().getX();
				    int yMonsterFour = mapWorld.getMonsterFour().getY();
				   
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
					if(mapPlay.getActuelMapWorld().getMonsterFour().getX() == mapPlay.getActuelMapWorld().getLorann().getX()&&mapPlay.getActuelMapWorld().getMonsterFour().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("Vous êtes mort fdp");
					}
				
			}
		      };
		    return new Timer(500, action);
		  }
		@Override
		public void startTimerForKill(){
			this.timer.start();
		}
		@Override
		public Timer createTimerForKill (final MapPlay mapPlay){
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
					
					if(mapPlay.getActuelMapWorld().getMonsterFour().getX() == mapPlay.getActuelMapWorld().getLorann().getX()&&mapPlay.getActuelMapWorld().getMonsterFour().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("Vous êtes mort fdp");
					}
				
			}
		      };
		    return new Timer(1, action);
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
		public void orderPerform(final contract.UserOrder userOrder) throws IOException {
				
				switch (userOrder) {
					case UP:
						this.getActuelMapWorld().getLorann().moveUp();
						this.getActuelMapWorld().getLorann().setOrientation(Orientation.NORTH);
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_u.png"));
						
						break;
					case RIGHT:
						this.getActuelMapWorld().getLorann().moveRight();
						this.getActuelMapWorld().getLorann().setOrientation(Orientation.EAST);
						System.out.println(String.valueOf(this.getMapWorld().getLorann().getOrientation()));
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_r.png"));
						
						break;
					case DOWN:
						this.getActuelMapWorld().getLorann().moveDown();
						this.getActuelMapWorld().getLorann().setOrientation(Orientation.SOUTH);
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_b.png"));
						
						break;
					case LEFT:
						this.getActuelMapWorld().getLorann().moveLeft();
						this.getActuelMapWorld().getLorann().setOrientation(Orientation.WEST);
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_bl.png"));
					
					case SPACE:
						
						if (this.mapWorld.getLorann().getOrientation() == Orientation.EAST)
						{
							this.mapWorld.addMobile(new Fireball(Orientation.WEST), this.mapWorld.getLorann().getX()-1, this.mapWorld.getLorann().getY());
							this.getMapFrame().setMeeting(this.getMapWorld());
							System.out.println("je suis la ");
							this.getMapFrame();
							this.mapWorld.getFireball().moveLeft();
							this.getMapFrame().setMeeting(this.getMapWorld());
							System.out.println("je suis la ");
							this.getMapFrame();
							this.mapWorld.getFireball().moveLeft();
						}
						
						
						break;
					case NOP:
						
					default:
						break;
				
			}
				this.getWorldAnswer();
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
				
				case PICKUP_FIREBALL:
					this.mapFrame.showMessage("You have found the treasure");
					this.score+=200;
					mapFrame.setTextChange();
					
					this.resolvePickUpFireball();
					break;
					
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
			
			this.getMapFrame().setMeeting(this.getMapWorld());
			System.out.println("je suis la ");
			this.getMapFrame();
			
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

		
		
}



