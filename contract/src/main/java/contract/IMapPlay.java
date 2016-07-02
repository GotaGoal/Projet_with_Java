package contract;

import java.io.IOException;

import javax.swing.Timer;

import controller.MapPlay;



public interface IMapPlay {
	
	
	void startTimer();
	Timer createTimer (MapPlay mapPlay);
	//void startTimerForKill();
	//Timer createTimerForKill(MapPlay mapPlay);
	IMapWorld getMapWorld();
	IMapFrame getMapFrame();
	void setMapFrame(final IMapFrame mapFrame);
	IMapWorld getActuelMapWorld();
	void orderPerform(final UserOrder userOrder) throws IOException;
	void getWorldAnswer() throws IOException;
	void resolveUp() throws IOException;
	void resolveExit() throws IOException;
	void resolvePickUpFireball() throws IOException;
	void resolvePickUpEnergy() throws IOException;
	void resolvePickUpTreasure() throws IOException;
	void resolveWorldAnswer() throws IOException;
	void setMapWorld(final IMapWorld map);
	
	
	
	
	
	

}
