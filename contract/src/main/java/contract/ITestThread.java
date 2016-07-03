package contract;

import controller.MapPlay;
import controller.TestThread;
import map.MapWorld;
import view.MapFrame;

public interface ITestThread {
	
	public TestThread initThread(MapWorld mapWorld, MapFrame mapFrame, MapPlay mapPlay);
	
}
