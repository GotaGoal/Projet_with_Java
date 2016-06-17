package map;
import java.io.IOException;


public class MapPlay implements IOrderPerformed{
	

		private final MapWorld mapWorld;

		public MapPlay(final MapWorld mapWorld) {
			this.mapWorld = mapWorld;
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



