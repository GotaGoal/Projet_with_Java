package map;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import map.element.Element;
import map.element.mobile.Lorann;
import map.element.mobile.Mobile;
import view.MapFrame;
import aedt.showboard.BoardFrame;
import contract.IMapFrame;
import contract.IMapPlay;
import contract.IOrderPerformed;
import controller.MapPlay;
public class Map extends Entity implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MapWorld mapWorld;
	private Lorann Lorann;
	private  int				WINDOW_WIDTH;
	private  int				WINDOW_HEIGHT;
	private  MapPlay mapPlay;
	private  MapFrame mapFrame;

	public Map() throws IOException {
		this.mapWorld = new MapWorld(1);
		
		this.mapPlay = new MapPlay(this.mapWorld);
		
		
		/*
		this.WINDOW_WIDTH = mapWorld.getWidth();
		this.WINDOW_HEIGHT = mapWorld.getHeight();
		this.Lorann = new Lorann();
		this.mapWorld.addMobile(this.Lorann, 2, 2);
		
		this.mapPlay = new MapPlay(this.mapWorld);
		
		//this.mapWorld.addMobile(Lorann, 2, 2);*/
		SwingUtilities.invokeLater(this);
		//this.mapPlay.play(0);
		
		
	}
	
	public void run() {
		//new Fenetre();
		/*
		final BoardFrame boardFrame = new BoardFrame("Lorann Yolo",
				WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_HEIGHT, this.getMapWorld().getElements(), this.getMapWorld().getMobiles(), this.getMapWorld().getLorann().getPosition());
		this.mapWorld.addObserver(boardFrame.getBoardPanel());*/
		this.mapFrame = new MapFrame("Welcome to LorannWorld",this.getMapWorld(), this.getMapPlay());
		this.mapPlay.setMapFrame(this.mapFrame);
		
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
	
	public void loadMessage(final String fileName, final int id) throws IOException {
		try {
			final DAOMap daomap = new DAOMap(DBConnection.getInstance().getConnection());
			daomap.loadBDD(fileName, id);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

}
