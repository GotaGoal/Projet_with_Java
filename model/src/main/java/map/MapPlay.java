package map;
import java.io.IOException;

import java.awt.event.KeyEvent;


public class MapPlay{
	

		private final MapWorld mapWorld;

		public MapPlay(final MapWorld mapWorld) {
			this.mapWorld = mapWorld;
		}
		
		public void play(final int keyCode) throws IOException {
			for (;;) {
				switch (keyCode) {
					case KeyEvent.VK_UP:
						this.mapWorld.getLorann().moveUp();
						break;
					case KeyEvent.VK_RIGHT:
						this.mapWorld.getLorann().moveRight();
						break;
					case KeyEvent.VK_DOWN:
						this.mapWorld.getLorann().moveDown();
						break;
					case KeyEvent.VK_LEFT:
						this.mapWorld.getLorann().moveLeft();
						break;
					default:
						break;
				}
			}
		}

	}



