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
		
		public void orderPerform(final UserOrder userOrder) throws IOException {
			
				switch (userOrder) {
					case UP:
						this.mapWorld.getLorann().moveUp();
						//this.mapWorld.getLorann().setImage("land.png");
						//this.mapWorld.getLorann().setSprite(new Sprite("☺!","bone.png"));
						
						System.out.println("maxime est un fils de pute");
						break;
					case RIGHT:
						this.mapWorld.getLorann().moveRight();
						System.out.println("gros poupouch est moche");
						break;
					case DOWN:
						this.mapWorld.getLorann().moveDown();
						System.out.println("docteur coq a une jonquille");
						break;
					case LEFT:
						this.mapWorld.getLorann().moveLeft();
						System.out.println("goal marque un but");
						break;
					case NOP:
						
					default:
						break;
				
			}
		}
	}



