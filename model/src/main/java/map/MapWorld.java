package map;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import contract.IMapWorld;
import map.element.Element;
import map.element.interactions.Interactions;
import map.element.mobile.Fireball;
import map.element.mobile.Lorann;
import map.element.mobile.Mobile;
import map.element.mobile.MonsterFour;
import map.element.motionless.MotionlessElements;
import view.MapPanel;
import map.element.motionless.MotionlessElement;

public class MapWorld extends Observable implements IMapWorld{
	
	
	public MotionlessElement		elements[][];
	public  ArrayList<Mobile>	mobiles;
	
	private MonsterFour monsterFour;
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
		//this.loadFile(1);
		switch(id)
		{
		case 1:
			this.loadMap(1);
			break;
		case 2:
			this.loadMap(2);
			break;
		case 3:
			this.loadMap(3);
			break;
		default:
			break;
		}
	}
	
	public MapWorld(final String charge) throws IOException {
		this();
		//this.loadFile(1);
		this.loadFile(charge);
		
	}
	
	@Override 
  public int getWidth() {
		return this.width;
	}
  @Override
	public int getHeight() {
		return this.height;
	}
	@Override
	public Lorann getLorann()
	{
		return this.lorann;
	}
   
	@Override
	public Element getElement(final int x, final int y) {
		if ((x < 0) || (y < 0) || (x >= this.getWidth()) || (y >= this.getHeight())) {
			return null;
			
		}
		return this.elements[x][y];
	}
	
	@Override
	public MotionlessElement getElements(final int x, final int y) {
		if ((x < 0) || (y < 0) || (x >= this.getWidth()) || (y >= this.getHeight())) {
			return null;
		}
		return this.elements[x][y];
	}
	
   
	@Override
	public void addElement(final MotionlessElement element, final int x, final int y) {
		this.elements[x][y] = element;
		if (element != null) {
			element.setMapWorld(this);
		}
	}
	@Override
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
	@Override
	public void addMobile(final Lorann lorann, final int x, final int y) {
		this.setLorann(lorann);
		this.addMobile((Mobile) lorann, x, y);
	}
	@Override
	public int getPointSpawnLorannX()
	{
		return this.pointLorannX;
	}
	@Override
	public int getPointSpawnLorannY()
	{
		return this.pointLorannY;
	}
	
	@Override
	public void loadMap(final int mapID) throws IOException {
		try {
			final DAOMap daomap = new DAOMap(DBConnection.getInstance().getConnection());
			this.width = daomap.getTailleMapX(mapID);
			this.height = daomap.getTailleMapY(mapID);
			
			this.pointLorannX = daomap.getLorannX(mapID);
			this.pointLorannY = daomap.getLorannY(mapID);
			
			this.setPointEnergyX(daomap.getEnergyX(mapID));
			this.setPointEnergyY(daomap.getEnergyY(mapID));
			
			this.setPointGateOpenX(daomap.getDoorOpenX(mapID));
			this.setPointGateOpenY(daomap.getDoorOpenY(mapID));
			
			this.setPointGateCloseX(daomap.getDoorCloseX(mapID));
			this.setPointGateCloseY(daomap.getDoorCloseY(mapID));
			
			this.setPointTreasureX(daomap.getTreasureX(mapID));
			this.setPointTreasureY(daomap.getTreasureY(mapID));
			
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
	@Override
	public void enregistrer(final String filename, final int mapID) throws IOException{
		try {
			final DAOMap daomap = new DAOMap(DBConnection.getInstance().getConnection());
			daomap.loadBDD(filename, mapID);
		
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void loadFile(final String fileName) throws IOException {
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

		}
		
		
	}
	@Override
	public ArrayList<Mobile> getMobiles() {
		
		return this.mobiles;
		
	}
	@Override
	public void setLorann(final Lorann lorann) {
		this.lorann = lorann;
		this.setChanged();
	}
	@Override
	public void setMobileHasChanged() {
		this.setChanged();
		this.notifyObservers();
	}
	@Override
	 
	public void notifyObservers() {
		super.notifyObservers();
	}
	@Override 
	public Element[][] getElements() {
		return this.elements;
	}
	@Override 
	public int getPointEnergyX() {
		return pointEnergyX;
	}
	@Override
	public void setPointEnergyX(int pointEnergyX) {
		this.pointEnergyX = pointEnergyX;
	}
	@Override 
	public int getPointEnergyY() {
		return pointEnergyY;
	}
	@Override
	public void setPointEnergyY(int pointEnergyY) {
		this.pointEnergyY = pointEnergyY;
	}
	@Override 
	public int getPointGateCloseX() {
		return pointGateCloseX;
	}
	@Override
	public void setPointGateCloseX(int pointGateCloseX) {
		this.pointGateCloseX = pointGateCloseX;
	}
	@Override
	public int getPointGateCloseY() {
		return pointGateCloseY;
	}
	@Override
	public void setPointGateCloseY(int pointGateCloseY) {
		this.pointGateCloseY = pointGateCloseY;
	}
	@Override
	public int getPointGateOpenX() {
		return pointGateOpenX;
	}
	@Override
	public void setPointGateOpenX(int pointGateOpenX) {
		this.pointGateOpenX = pointGateOpenX;
	}
	@Override
	public int getPointGateOpenY() {
		return pointGateOpenY;
	}
	@Override
	public void setPointGateOpenY(int pointGateOpenY) {
		this.pointGateOpenY = pointGateOpenY;
	}
	@Override 
	public int getPointTreasureX() {
		return pointTreasureX;
	}
	@Override 
	public void setPointTreasureX(int pointTreasureX) {
		this.pointTreasureX = pointTreasureX;
	}
	@Override 
	public int getPointTreasureY() {
		return pointTreasureY;
	}
	@Override 
	public void setPointTreasureY(int pointTreasureY) {
		this.pointTreasureY = pointTreasureY;
	}
	@Override
	public Fireball getFireball() {
		return fireball;
	}
	@Override
	public void setFireball(Fireball fireball) {
		this.fireball = fireball;
	}
	@Override
	public MonsterFour getMonsterFour()
	{
		return this.monsterFour;
	}
	@Override
	public void setMonsterFour(final MonsterFour monsterFour) {
		this.monsterFour = monsterFour;
		this.setChanged();
	}
	@Override
	public void addMobile(final MonsterFour monsterFour, final int x, final int y) {
		this.setMonsterFour(monsterFour);
		this.addMobile((Mobile) monsterFour, x, y);
	}
	@Override
	public void CallGetMonsterFour()
	{
		this.getMonsterFour();
	}
	@Override
	public ArrayList<Mobile> removeMob(Mobile object)
	{
		this.mobiles.remove(object);
		
		return mobiles;
	}
	
/*	
	public void setElements(final Element[][] elements) {
		this.elements = elements;
	}*/
}
