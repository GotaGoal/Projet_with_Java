package map;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;


import map.element.Element;
import map.element.interactions.Interactions;
import map.element.mobile.Lorann;
import map.element.mobile.Mobile;
import map.element.motionless.MotionlessElements;
import map.element.motionless.MotionlessElement;

public class MapWorld extends Observable {
	
	
	public MotionlessElement		elements[][];
	public final ArrayList<Mobile>	mobiles;
	public final ArrayList<Interactions> interactions;
	private Lorann lorann;
	
	private int	width;
	private int	height;

	public MapWorld() throws IOException {
		
		this.mobiles = new ArrayList<Mobile>();
		this.interactions = new ArrayList<Interactions>();
		
		
	}
	public MapWorld(final String fileName) throws IOException {
		this();
		this.loadFile(1);
	}
	

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
	public Lorann getLorann()
	{
		return this.lorann;
	}
	
	public Element getElement(final int x, final int y) {
		if ((x < 0) || (y < 0) || (x >= this.getWidth()) || (y >= this.getHeight())) {
			return null;
		}
		return this.elements[x][y];
	}
	
	public MotionlessElement getElements(final int x, final int y) {
		if ((x < 0) || (y < 0) || (x >= this.getWidth()) || (y >= this.getHeight())) {
			return null;
		}
		return this.elements[x][y];
	}
	

	public void addElement(final MotionlessElement element, final int x, final int y) {
		this.elements[x][y] = element;
		if (element != null) {
			element.setMapWorld(this);
		}
	}
	public void addMobile(final Mobile mobile, final int x, final int y) {
		this.mobiles.add(mobile);
		mobile.setMapWorld(this, x, y);
		this.setChanged();
		this.notifyObservers();
	}
	/*
	public void addMobile(final Interactions interaction, final int x, final int y) {
		this.mobiles.add(interaction);
		interaction.setMapWorld(this, x, y);
		this.setChanged();
		this.notifyObservers();
	}*/
	public void addMobile(final Lorann lorann, final int x, final int y) {
		this.setLorann(lorann);
		this.addMobile((Mobile) lorann, x, y);
	}

	private void loadFile(final int mapID) throws IOException {
		try {
			final DAOMap daomap = new DAOMap(DBConnection.getInstance().getConnection());
			this.width = daomap.getTailleMapX(mapID);
			this.height = daomap.getTailleMapY(mapID);
			this.elements = new MotionlessElement[this.getWidth()][this.getHeight()];
			for (int x = 0; x < this.width; x++) {
				for (int y = 0 ; y < this.height; y++) {
					switch (daomap.getElementBDD(mapID, x, y)){
					
					case 1:
						this.addElement(MotionlessElements.getFromFileSymbol('V'), x, y);
						break;
					case 2:
						this.addElement(MotionlessElements.getFromFileSymbol('H'), x, y);
						break;
					case 4:
						this.addElement(MotionlessElements.getFromFileSymbol('B'), x, y);
						break;
					case 5:
						this.addElement(MotionlessElements.getFromFileSymbol('D'), x, y);
						break;
					default :
						this.addElement(MotionlessElements.getFromFileSymbol(' '), x, y);
						break;
					}
					
				}
			} 
		
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public ArrayList<Mobile> getMobiles() {
		
		return this.mobiles;
		
	}
	
	public void setLorann(final Lorann lorann) {
		this.lorann = lorann;
		this.setChanged();
	}

	public void setMobileHasChanged() {
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}

	public Element[][] getElements() {
		return this.elements;
	}
	
	
	
	
/*
	public void setElements(final Element[][] elements) {
		this.elements = elements;
	}*/
}
