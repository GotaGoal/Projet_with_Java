package main;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.text.html.parser.Entity;

import contract.*;
import controller.MapPlay;
import controller.TestThread;
import map.MapWorld;
import view.MapFrame;

/**
 * The Class Main.
 *
 * @author Jean-Aymeric Diet
 */
public abstract class Main implements Runnable {
	private static MapWorld mapWorld;
	//private final MapWorld menu;
	//private Animation menuPlay;
	
	private  static MapPlay mapPlay;
	private  static MapFrame mapFrame;
	//private MapFrame menuFrame;

	/**
	 * The main method.
	 *
	 * @param args
	 *          the arguments
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(final String[] args) throws IOException, InterruptedException {
		new MapInit();
		
	}
	
	
}

class MapInit extends Thread implements Runnable
{
	private final MapWorld mapWorld;
	//private final MapWorld menu;
	//private Animation menuPlay;
	
	private  MapPlay mapPlay;
	private  MapFrame mapFrame;
	
	//private MapFrame menuFrame;

	/**
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public MapInit() throws IOException, InterruptedException {
		this.mapWorld = new MapWorld(1);
		
		this.mapPlay = new MapPlay(this.mapWorld);
		
		//animation here
		//this.menu = new MapWorld("map0.txt");
		//this.menuPlay = new Animation(this.menu);
	
		
		
		
		SwingUtilities.invokeLater(this);

		
		
	}
	@Override
	public void run() {
		
		/* animation here
		try {
			this.menuFrame = new MapFrame("Coucou",this.menu,this.menuPlay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.menuPlay.setMapFrame(this.menuFrame);
		*/
		this.mapFrame = new MapFrame("Welcome to LorannWorld",this.getMapWorld(), this.getMapPlay());
		this.mapPlay.setMapFrame(this.mapFrame);
		TestThread t = new TestThread(mapWorld,mapFrame,mapPlay);
		
		
	}
	/*
	public void play() throws IOException {
		this.getMapPlay().play();
	}*/

	public MapWorld getMapWorld() {
		return this.mapWorld;
	}

	public MapPlay getMapPlay() {
		return this.mapPlay;
	}
	
	
}
