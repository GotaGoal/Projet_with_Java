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
import map.element.mobile.Fireball;
import map.element.mobile.Lorann;
import map.element.mobile.Mobile;
import map.element.motionless.MotionlessElements;
import map.element.motionless.MotionlessElement;

public class MapWorld extends Observable {
	
	
	public MotionlessElement		elements[][];
	public final ArrayList<Mobile>	mobiles;
	
	
	private Lorann lorann;
	private Fireball fireball;
	
	private int	width;
	private int	height;
	
	private int pointLorannX;
	private int pointLorannY;
	private int pointEnergyX;
	private int pointEnergyY;
	private int pointGateCloseX;
	private int pointGateCloseY;
	private int pointGateOpenX;
	private int pointGateOpenY;
	private int pointTreasureX;
	private int pointTreasureY;
	
	

	public MapWorld() throws IOException {
		
		this.mobiles = new ArrayList<Mobile>();
		
		
		
	}
	public MapWorld(int id) throws IOException {
		this();
<<<<<<< HEAD
		this.loadFile(1);
=======
		switch(id)
		{
		case 1:
			this.loadFile("mapWorld.txt");
			break;
		case 2:
			this.loadFile("map1.txt");
			break;
		case 3:
			this.loadFile("map2.txt");
			break;
		default:
			break;
		}
>>>>>>> origin/benjamin
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
	public int getPointSpawnLorannX()
	{
		return this.pointLorannX;
	}
	public int getPointSpawnLorannY()
	{
		return this.pointLorannY;
	}
	

<<<<<<< HEAD
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
=======
	private void loadFile(final String fileName) throws IOException {
		final BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line;
		int numLine = 0;
		line = buffer.readLine();
		this.width = Integer.parseInt(line);
		line = buffer.readLine();
		this.height = Integer.parseInt(line);
		line = buffer.readLine();
		this.pointLorannX = Integer.parseInt(line);
		line = buffer.readLine();
		this.pointLorannY = Integer.parseInt(line);
		line = buffer.readLine();
		this.setPointEnergyX(Integer.parseInt(line));
		line = buffer.readLine();
		this.setPointEnergyY(Integer.parseInt(line));
		line = buffer.readLine();
		this.setPointGateOpenX((Integer.parseInt(line)));
		line = buffer.readLine();
		this.setPointGateOpenY((Integer.parseInt(line)));		
		line = buffer.readLine();
		this.setPointGateCloseX((Integer.parseInt(line)));
		line = buffer.readLine();
		this.setPointGateCloseY((Integer.parseInt(line)));
		line = buffer.readLine();
		this.setPointTreasureX((Integer.parseInt(line)));
		line = buffer.readLine();
		this.setPointTreasureY((Integer.parseInt(line)));
		
		
		
		this.elements = new MotionlessElement[this.getWidth()][this.getHeight()];
		while ((line = buffer.readLine()) != null) {
			for (int x = 0; x < line.toCharArray().length; x++) {
				this.addElement(MotionlessElements.getFromFileSymbol(line.toCharArray()[x]), x, numLine);
				//System.out.print(line.toCharArray()[x]);
			}
			numLine++;
			//System.out.print(line);
>>>>>>> origin/benjamin
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
	public int getPointEnergyX() {
		return pointEnergyX;
	}
	public void setPointEnergyX(int pointEnergyX) {
		this.pointEnergyX = pointEnergyX;
	}
	public int getPointEnergyY() {
		return pointEnergyY;
	}
	public void setPointEnergyY(int pointEnergyY) {
		this.pointEnergyY = pointEnergyY;
	}
	public int getPointGateCloseX() {
		return pointGateCloseX;
	}
	public void setPointGateCloseX(int pointGateCloseX) {
		this.pointGateCloseX = pointGateCloseX;
	}
	public int getPointGateCloseY() {
		return pointGateCloseY;
	}
	public void setPointGateCloseY(int pointGateCloseY) {
		this.pointGateCloseY = pointGateCloseY;
	}
	public int getPointGateOpenX() {
		return pointGateOpenX;
	}
	public void setPointGateOpenX(int pointGateOpenX) {
		this.pointGateOpenX = pointGateOpenX;
	}
	public int getPointGateOpenY() {
		return pointGateOpenY;
	}
	public void setPointGateOpenY(int pointGateOpenY) {
		this.pointGateOpenY = pointGateOpenY;
	}
	public int getPointTreasureX() {
		return pointTreasureX;
	}
	public void setPointTreasureX(int pointTreasureX) {
		this.pointTreasureX = pointTreasureX;
	}
	public int getPointTreasureY() {
		return pointTreasureY;
	}
	public void setPointTreasureY(int pointTreasureY) {
		this.pointTreasureY = pointTreasureY;
	}
	public Fireball getFireball() {
		return fireball;
	}
	public void setFireball(Fireball fireball) {
		this.fireball = fireball;
	}
	
	
	
	
/*
	public void setElements(final Element[][] elements) {
		this.elements = elements;
	}*/
}
