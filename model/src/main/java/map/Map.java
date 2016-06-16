package map;

import java.io.IOException;

import map.element.Element;
import map.element.motionless.MotionlessElements;



public class Map {
	
	private final MapWorld mapWorld;

	public Map() throws IOException {
		this.mapWorld = new MapWorld("mapWorld.txt");
		this.mapWorld.addElement(MotionlessElements.FOREST, 10, 10);
		this.mapWorld.addElement(MotionlessElements.STONE, 11, 10);
		this.mapWorld.addElement(MotionlessElements.WATER, 12, 10);
		this.mapWorld.addElement(MotionlessElements.CAMP, 13, 10);
	}

	public void show() {
		final String arrayToPrint[] = new String[this.mapWorld.getHeight()];

		for (int y = 0; y < this.mapWorld.getHeight(); y++) {
			arrayToPrint[y] = "";
			for (int x = 0; x < this.mapWorld.getWidth(); x++) {
				final Element element = this.mapWorld.getElements(x, y);
				if (element != null) {
					arrayToPrint[y] += this.mapWorld.getElements(x, y).getSprite();
				} else {
					arrayToPrint[y] += "  ";
				}
			}
		}

		for (final String stringToPrint : arrayToPrint) {
			System.out.println(stringToPrint);
		}
	}

}
