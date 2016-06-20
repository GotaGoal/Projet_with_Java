package contract;

import java.io.IOException;
import java.util.ArrayList;

import map.element.Element;
import map.element.mobile.Fireball;
import map.element.mobile.Lorann;
import map.element.mobile.Mobile;
import map.element.mobile.MonsterFour;
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
		public MonsterFour getMonsterFour();
		void setMonsterFour(final MonsterFour monsterFour);
		void addMobile(final MonsterFour monsterFour, final int x, final int y);
		void addObserver(Observer e);
		int getPointEnergyY();
		void CallGetMonsterFour();
		//Mobile getMonsterFour();
		//JComponent getMonsterFour();
		
	
		
		

	}


