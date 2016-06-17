package map;
import java.io.IOException;

import map.element.mobile.Lorann;

import java.awt.event.KeyEvent;


public class MapPlay implements IOrderPerformed{
	private final MapFrame mapFrame;


		private final MapWorld mapWorld;

		public MapPlay(final MapWorld mapWorld) {
			this.mapWorld = mapWorld;
			this.mapWorld.addMobile(new Lorann(), 2, 2);
		}
		
		public void setMapFrame(final MapFrame mapFrame) {
			this.mapFrame = mapFrame;
		}
		
		public void orderPerform(final UserOrder userOrder) throws IOException {
			for (;;) {
				switch (userOrder) {
					case UP:
						this.mapWorld.getLorann().moveUp();
						break;
					case RIGHT:
						this.mapWorld.getLorann().moveRight();
						break;
					case DOWN:
						this.mapWorld.getLorann().moveDown();
						break;
					case LEFT:
						this.mapWorld.getLorann().moveLeft();
						break;
					default:
						break;
				}
			}
		}
	}



