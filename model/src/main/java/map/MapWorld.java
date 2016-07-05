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
import map.element.mobile.Fireball;
import map.element.mobile.Lorann;
import map.element.mobile.Mobile;
import map.element.mobile.Monster;
import map.element.motionless.MotionlessElements;
import map.element.motionless.MotionlessElement;

public class MapWorld extends Observable implements IMapWorld{
	
	
	public MotionlessElement		elements[][];
	public  ArrayList<Mobile>	mobiles;
	
	
	private Monster monsterOne;
	private Monster monsterTwo;
	private Monster monsterThree;
	private Monster monsterFour;
	private Lorann lorann;
	private Fireball fireball;
	private Monster monster;
	
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
	private int idMap;
	private int m1X;
	private int m1Y;
	private int m2X;
	private int m2Y;
	private int m3X;
	private int m3Y;
	private int m4X;
	private int m4Y;
	private DAOMap dao;
	
	
	

	public MapWorld() throws IOException {
		
		this.mobiles = new ArrayList<Mobile>();
		
		
		
	}
	public MapWorld(int id) throws IOException {
		this();
		//this.loadFile(1);
		
			this.setIdMap(id);
			this.loadMap(this.idMap);
			
			
	}
	
	public MapWorld(final String charge) throws IOException {
		this();
		//this.loadFile(1);
		this.loadFile(charge);
		
	}
	
	public MapWorld(final String str,final int idMap ) throws IOException
 	{
 		this();
 		this.enregistrer(str,idMap);


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
	
	@Override
	public void addMobile(final Fireball fireball, final int x, final int y){
		this.setFireball(fireball);
		this.addMobile((Mobile)fireball, x, y);
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
			this.dao = new DAOMap(DBConnection.getInstance().getConnection());
			this.m1X = dao.getMonstreX(mapID,1);
			System.out.print(m1X);
			this.m1Y = dao.getMonstreY(mapID, 1);
			System.out.println("");
			System.out.print(m1Y);
			this.m2X = dao.getMonstreX(mapID, 2);
			System.out.println("");
			System.out.print(m2X);
			this.m2Y = dao.getMonstreY(mapID, 2);
			System.out.println("");
			System.out.print(m2Y);
			this.m3X = dao.getMonstreX(mapID, 3);
			System.out.println("");
			System.out.print(m3X);
			this.m3Y = dao.getMonstreY(mapID, 3);
			System.out.println("");
			System.out.print(m3Y);
			this.m4X = dao.getMonstreX(mapID, 4);
			System.out.println("");
			System.out.print(m4X);
			this.m4Y = dao.getMonstreY(mapID, 4);
			System.out.println("");
			System.out.print(m4Y);
			
			this.width = dao.getTailleMapX(mapID);
			this.height = dao.getTailleMapY(mapID);
			
			this.pointLorannX = dao.getLorannX(mapID);
			this.pointLorannY = dao.getLorannY(mapID);
			
			this.setPointEnergyX(dao.getEnergyX(mapID));
			this.setPointEnergyY(dao.getEnergyY(mapID));
			
			this.setPointGateOpenX(dao.getDoorOpenX(mapID));
			this.setPointGateOpenY(dao.getDoorOpenY(mapID));
			
			this.setPointGateCloseX(dao.getDoorCloseX(mapID));
			this.setPointGateCloseY(dao.getDoorCloseY(mapID));
			
			this.setPointTreasureX(dao.getTreasureX(mapID));
			this.setPointTreasureY(dao.getTreasureY(mapID));
			
			this.elements = new MotionlessElement[this.getWidth()][this.getHeight()];
			for (int x = 0; x < this.width; x++) {
				for (int y = 0 ; y < this.height; y++) {
					switch (dao.getElementBDD(mapID, x, y)){
					
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
		this.setChanged();
	}
	@Override
	public Monster getMonsterFour()
	{
		return this.monsterFour;
	}
	@Override
	public void setMonsterFour(final Monster monsterFour) {
		this.monsterFour = monsterFour;
		this.setChanged();
	}
	
	@Override
	public void addMobile(final Monster monster, final int x, final int y) {
		this.setMonsterFour(monster);
		this.addMobile((Mobile) monster, x, y);
	}
	@Override
	public void CallGetMonsterFour()
	{
		this.getMonsterFour();
	}
	
	@Override
	public void CallGetMonsterOne()
	{
		this.getMonsterOne();
	}
	
	
	@Override
	public void CallGetMonsterTwo()
	{
		this.getMonsterTwo();
	}
	
	@Override
	public void CallGetMonsterThree()
	{
		this.getMonsterThree();
	}
	@Override
	public ArrayList<Mobile> removeMob(Mobile object)
	{
		this.mobiles.remove(object);
		
		return mobiles;
	}
	@Override
	public int getIdMap() {
		return idMap;
	}
	@Override
	public void setIdMap(int idMap) {
		this.idMap = idMap;
	}
	@Override
	public Monster getMonsterOne() {
		return monsterOne;
	}
	@Override
	public void setMonsterOne(Monster monsterOne) {
		this.monsterOne = monsterOne;
	}
	@Override
	public Monster getMonsterTwo() {
		return monsterTwo;
	}
	@Override
	public void setMonsterTwo(Monster monsterTwo) {
		this.monsterTwo = monsterTwo;
	}
	@Override 
	public Monster getMonsterThree() {
		return monsterThree;
	}
	@Override
	public void setMonsterThree(Monster monsterThree) {
		this.monsterThree = monsterThree;
	}
	@Override
	public int getM1X() {
		return m1X;
	}
	@Override
	public void setM1X(int m1x) {
		m1X = m1x;
	}
	@Override
	public int getM1Y() {
		return m1Y;
	}
	@Override
	public void setM1Y(int m1y) {
		m1Y = m1y;
	}
	@Override
	public int getM2X() {
		return m2X;
	}
	@Override
	public void setM2X(int m2x) {
		m2X = m2x;
	}
	@Override
	public int getM2Y() {
		return m2Y;
	}
	@Override
	public void setM2Y(int m2y) {
		m2Y = m2y;
	}
	/*
	public void test() throws IOException
	{
		this.lorann.setX(dao.getMonstreX(1, 1));
	}*/
/*	
	public void setElements(final Element[][] elements) {
		this.elements = elements;
	}*/
	@Override
	public int getM3X() {
		return m3X;
	}
	@Override
	public void setM3X(int m3x) {
		m3X = m3x;
	}
	@Override
	public int getM3Y() {
		return m3Y;
	}
	@Override
	public void setM3Y(int m3y) {
		m3Y = m3y;
	}
	@Override
	public int getM4X() {
		return m4X;
	}
	@Override
	public void setM4X(int m4x) {
		this.m4X = m4x;
	}
	@Override
	public int getM4Y() {
		return m4Y;
	}
	@Override
	public void setM4Y(int m4y) {
		m4Y = m4y;
	}
	public Monster getMonster() {
		return monster;
	}
	public void setMonster(Monster monster) {
		this.monster = monster;
	}
	
	
	@Override
	public Monster getMonsterTest()
	{
		return this.monster;
	}
	
}