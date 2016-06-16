package map;
import java.io.IOException;

public class MapPlay {
	

		private final MapWorld mapWorld;

		public MapPlay(final MapWorld mapWorld) {
			this.mapWorld = mapWorld;
		}

		public void play() throws IOException {
			for (;;) {
				final int key = System.in.read();

				switch (key) {
					case 53:
						this.mapWorld.getLorann().moveUp();
						break;
					case 51:
						this.mapWorld.getLorann().moveRight();
						break;
					case 50:
						this.mapWorld.getLorann().moveDown();
						break;
					case 49:
						this.mapWorld.getLorann().moveLeft();
						break;
					default:
						break;
				}
			}
		}

	}



