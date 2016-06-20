package map;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JLabel;

import map.element.Sprite;
import map.element.mobile.Fireball;
import map.element.mobile.Lorann;
import map.element.motionless.Energy;
import map.element.motionless.IDoActionOnHeroes;
import map.element.motionless.*;



public class MapPlay implements IOrderPerformed{
	private  MapFrame mapFrame;
		private MapWorld mapWorld;
		private int calcul_level = 1;
		private int score = 0;

		public MapPlay(final MapWorld mapWorld) {
			this.mapWorld = mapWorld;
			
			
			
			
			this.mapWorld.addMobile(new Lorann(Orientation.ND), this.mapWorld.getPointSpawnLorannX(), this.mapWorld.getPointSpawnLorannY());
			this.mapWorld.addElement(new Energy(), this.mapWorld.getPointEnergyX(), this.mapWorld.getPointEnergyY());
			this.mapWorld.addElement(new Gate_Close(), this.mapWorld.getPointGateCloseX(), this.mapWorld.getPointGateCloseY());
			this.mapWorld.addElement(new Treasure(), this.mapWorld.getPointTreasureX(), this.mapWorld.getPointTreasureY());
			
			
		
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
					
					/*case SPACE:
						
						
						this.mapWorld.addMobile(new Fireball(this.mapWorld.getLorann().getOrientation()), this.mapWorld.getLorann().getX(), this.mapWorld.getLorann().getY());
						
						break;*/
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
				/*	
				case PICKUP_FIREBALL:
					this.mapFrame.showMessage("You have found the treasure");
					this.score+=200;
					mapFrame.setTextChange();
					
					this.resolvePickUpFireball();
					break;
					*/
				case EXIT:
					this.mapFrame.showMessage("You go back now");
					this.resolveExit();
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
		/*
		private void resolvePickUpFireball() throws IOException {
			this.mapWorld.addElement(new Land(), this.mapWorld.getFireball().getX(), this.mapWorld.getFireball().getY());
			this.resolveWorldAnswer();
		}*/
		
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



