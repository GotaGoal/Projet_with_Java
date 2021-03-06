package contract;

import java.io.IOException;
import java.util.ArrayList;

import map.element.Element;
import map.element.mobile.Fireball;
import map.element.mobile.Lorann;
import map.element.mobile.Mobile;
import map.element.mobile.Monster;
import map.element.motionless.MotionlessElement;

import java.util.Observer;

import javax.swing.JComponent;


public interface IMapWorld {
	

		int getWidth();
		int getHeight();
		public Lorann getLorann();
		Element getElement(final int x, final int y);
		MotionlessElement getElements (final int x, final int y);
		void addElement(final MotionlessElement element, final int x, final int y);
		void addMobile(final Mobile mobile, final int x, final int y);
		void addMobile(final Lorann lorann, final int x, final int y);
		int getPointSpawnLorannX();
		int getPointSpawnLorannY();
		void loadMap(final int mapID) throws IOException;
		void enregistrer(final String filename, final int mapID) throws IOException;
		void loadFile(final String fileName) throws IOException;
		ArrayList<Mobile> getMobiles();
		void setLorann(final Lorann lorann);
		void setMobileHasChanged();
		Element[][] getElements();
		int getPointEnergyX();
		void setPointEnergyX(int pointEnergyX);
		
		void setPointEnergyY(int pointEnergyY);
		int getPointGateCloseX();
		void setPointGateCloseX(int pointGateCloseX);
		int getPointGateCloseY();
		void setPointGateCloseY(int pointGateCloseY);
		int getPointGateOpenX();
		void setPointGateOpenX(int pointGateOpenX);
		int getPointGateOpenY();
		void setPointGateOpenY(int pointGateOpenY);
		int getPointTreasureX();
		void setPointTreasureX(int pointTreasureX);
		int getPointTreasureY();
		void setPointTreasureY(int pointTreasureY);
		Fireball getFireball();
		void setFireball(Fireball fireball);
		public Monster getMonsterFour();
		void setMonsterFour(final Monster monsterFour);
		
		void addObserver(Observer e);
		int getPointEnergyY();
		void CallGetMonsterFour();
		ArrayList<Mobile> removeMob(Mobile mobile);
		
		int getIdMap();
		void setIdMap(int idMap);
		Monster getMonsterOne();
		void setMonsterOne(Monster monsterOne);
		void CallGetMonsterOne();
		Monster getMonsterTwo();
		void setMonsterTwo(Monster monsterTwo);
		
		void CallGetMonsterTwo();
		void addMobile(Monster monster, int x, int y);
		void CallGetMonsterThree();
		Monster getMonsterThree();
		void setMonsterThree(Monster monsterThree);
		void addMobile(Fireball fireball, int x, int y);
		void setM4Y(int m4y);
		int getM4Y();
		void setM4X(int m4x);
		int getM4X();
		void setM3Y(int m3y);
		int getM3Y();
		void setM3X(int m3x);
		int getM3X();
		void setM2Y(int m2y);
		int getM2Y();
		void setM2X(int m2x);
		int getM2X();
		void setM1Y(int m1y);
		int getM1Y();
		void setM1X(int m1x);
		int getM1X();
		
		Monster getMonsterTest();
		
	
		
		

	}


