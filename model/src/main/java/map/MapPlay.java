package map;
import java.io.IOException;


import map.element.Sprite;
import map.element.mobile.Lorann;

import java.awt.event.KeyEvent;


public class MapPlay implements IOrderPerformed{
	private  MapFrame mapFrame;
	

		private final MapWorld mapWorld;

		public MapPlay(final MapWorld mapWorld) {
			this.mapWorld = mapWorld;
			this.mapWorld.addMobile(new Lorann(), 2, 2);
		
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
		public MapWorld getActuelMapWorld() {
			
			return this.getMapWorld();
		}
		
		public void orderPerform(final UserOrder userOrder) throws IOException {
			
				switch (userOrder) {
					case UP:
						this.getActuelMapWorld().getLorann().moveUp();
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_u.png"));
						
						break;
					case RIGHT:
						this.getActuelMapWorld().getLorann().moveRight();
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_r.png"));
						
						break;
					case DOWN:
						this.getActuelMapWorld().getLorann().moveDown();
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_b.png"));
						
						break;
					case LEFT:
						this.getActuelMapWorld().getLorann().moveLeft();
						this.mapWorld.getLorann().setSprite(new Sprite("☺!","lorann_bl.png"));
						
						break;
					case NOP:
						
					default:
						break;
				
			}
		}
	}



