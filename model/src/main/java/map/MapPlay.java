package map;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import javax.swing.JLabel;
import javax.swing.Timer;

import map.element.Sprite;
import map.element.mobile.Fireball;
import map.element.mobile.Lorann;
import map.element.mobile.MonsterFour;
import map.element.motionless.Energy;
import map.element.motionless.IDoActionOnHeroes;
import map.element.motionless.*;



public class MapPlay implements IOrderPerformed{
	private  MapFrame mapFrame;
		private MapWorld mapWorld;
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
		    this.timer  = this.createTimerForKill(this);
		    this.startTimerForKill();
			
		
		}
		public void startTimer(){
			this.timer.start();
		}
		private Timer createTimer (MapPlay mapPlay){
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
					this.Random = (int)( Math.random()*( 4 - 1 + 1 ) ) + 1;
					switch (Random) {
					case 1:
						mapPlay.getActuelMapWorld().getMonsterFour().moveUp();
						mapPlay.mapFrame.setBackground(Color.black);
						break;
					case 2:
						mapPlay.getActuelMapWorld().getMonsterFour().moveRight();
						mapPlay.mapFrame.setBackground(Color.black);
						break;
					case 3:
						mapPlay.getActuelMapWorld().getMonsterFour().moveDown();
						mapPlay.mapFrame.setBackground(Color.black);
						break;
					case 4:
						mapPlay.getActuelMapWorld().getMonsterFour().moveLeft();
						mapPlay.mapFrame.setBackground(Color.black);
						break;
					default:
						break;
					 }
					if(mapPlay.getActuelMapWorld().getMonsterFour().getX() == mapPlay.getActuelMapWorld().getLorann().getX()&&mapPlay.getActuelMapWorld().getMonsterFour().getY() == mapPlay.getActuelMapWorld().getLorann().getY() )
					{
						mapPlay.mapFrame.showMessage("Vous êtes mort fdp");
					}
				
			}
		      };
		    return new Timer(500, action);
		  }
		
		public void startTimerForKill(){
			this.timer.start();
		}
		private Timer createTimerForKill (MapPlay mapPlay){
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
		
		public MapWorld getMapWorld()
		{
			return this.mapWorld;
			
		}
		private MapFrame getMapFrame() {
			return this.mapFrame;
		}
		public void setMapFrame(final MapFrame mapFrame) {
			this.mapFrame = mapFrame;
		}
		private MapWorld getActuelMapWorld() {
			
			return this.getMapWorld();
		}
		
		public void orderPerform(final UserOrder userOrder) throws IOException {
				
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
		
		private void getWorldAnswer() throws IOException {
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
		
		private void resolveUp() throws IOException {
			this.calcul_level = this.calcul_level+1;
			
			this.setMapWorld(new MapWorld(this.calcul_level));
			
			
			
			this.resolveWorldAnswer();
		}
		private void resolveExit() throws IOException {
			this.calcul_level = this.calcul_level-1;
			
			this.setMapWorld(new MapWorld(this.calcul_level));
			
			
			
			this.resolveWorldAnswer();
		}
		
		private void resolvePickUpFireball() throws IOException {
			this.mapWorld.addElement(new Land(), this.mapWorld.getFireball().getX(), this.mapWorld.getFireball().getY());
			this.resolveWorldAnswer();
		}
		
		private void resolvePickUpEnergy() throws IOException {
			
			
			this.mapWorld.addElement(new Land(), this.mapWorld.getPointEnergyX(), this.mapWorld.getPointEnergyY());
			
			this.mapWorld.addElement(new Gate_Open_Level_Up() , this.mapWorld.getPointGateCloseX(), this.mapWorld.getPointGateCloseY());
			//this.mapWorld.removeObject(this.energy);
			
			
		}
		private void resolvePickUpTreasure() throws IOException {
			
			
			this.mapWorld.addElement(new Land(), this.mapWorld.getPointTreasureX(), this.mapWorld.getPointTreasureY());
			
			
			//this.mapWorld.removeObject(this.energy);
			
			
		}
		private void resolveWorldAnswer() throws IOException {
			
			this.getMapWorld().addMobile(new Lorann(Orientation.ND), this.getActuelMapWorld().getPointSpawnLorannX(), this.getActuelMapWorld().getPointSpawnLorannY());
			this.getMapWorld().addElement(new Energy(), this.getActuelMapWorld().getPointEnergyX(), this.getActuelMapWorld().getPointEnergyY());
			this.getMapWorld().addElement(new Gate_Open(), this.getActuelMapWorld().getPointGateOpenX(), this.getActuelMapWorld().getPointGateOpenY());
			this.getMapWorld().addElement(new Gate_Close(), this.getActuelMapWorld().getPointGateCloseX(),this.getActuelMapWorld().getPointGateCloseY());
			this.getMapWorld().addElement(new Treasure(), this.getActuelMapWorld().getPointTreasureX(),this.getActuelMapWorld().getPointTreasureY());
			
			this.getMapFrame().setMeeting(this.getMapWorld());
			System.out.println("je suis la ");
			this.getMapFrame();
			
		}
		
		private void setMapWorld(final MapWorld map) {
			this.mapWorld= map;
		}
		@Override
		public int getScore() {
			// TODO Auto-generated method stub
			return this.score;
		}
}



