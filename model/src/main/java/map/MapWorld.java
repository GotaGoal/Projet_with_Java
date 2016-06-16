package map;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


import map.element.Element;
import map.element.mobile.Mobile;
import map.element.motionless.MotionlessElements;

public class MapWorld {
	public Element		elements[][];
	public final ArrayList<Mobile>	mobiles;
	private int	width;
	private int	height;

	public MapWorld(final String fileName) throws IOException {
		
		this.mobiles = new ArrayList<Mobile>();
		this.loadFile(fileName);
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public Element getElements(final int x, final int y) {
		if ((x < 0) || (y < 0) || (x >= this.getWidth()) || (y >= this.getHeight())) {
			return null;
		}
		return this.elements[x][y];
	}

	public void addElement(final Element element, final int x, final int y) {
		this.elements[x][y] = element;
		if (element != null) {
			element.setMapWorld(this);
		}
	}
	public void addMobile(final Mobile mobile, final int x, final int y) {
		this.mobiles.add(mobile);
		mobile.setMapWorld(this, x, y);
	}

	private void loadFile(final String fileName) throws IOException {
		final BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line;
		int numLine = 0;
		line = buffer.readLine();
		this.width = Integer.parseInt(line);
		//this.width=20;
		line = buffer.readLine();
		this.height = Integer.parseInt(line);
		//this.height = 11;
		this.elements = new Element[this.getWidth()][this.getHeight()];
		while ((line = buffer.readLine()) != null) {
			for (int x = 0; x < line.toCharArray().length; x++) {
				this.addElement(MotionlessElements.getFromFileSymbol(line.toCharArray()[x]), x, numLine);
				//System.out.print(line.toCharArray()[x]);
			}
			numLine++;
			//System.out.print(line);
		}
		buffer.close();
	}
}
