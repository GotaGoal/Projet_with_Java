package map;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import map.element.Element;
import map.element.mobile.Lorann;
import map.element.mobile.Mobile;
import aedt.showboard.BoardFrame;
public class Map extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MapWorld mapWorld;
	private Lorann Lorann;
	private  int				WINDOW_WIDTH;
	private  int				WINDOW_HEIGHT;
	private MapPlay mapPlay;
	

	public Map() throws IOException {
		this.mapWorld = new MapWorld("map3.txt");
		this.WINDOW_WIDTH = mapWorld.getWidth();
		this.WINDOW_HEIGHT = mapWorld.getHeight();
		this.Lorann = new Lorann();
		this.mapWorld.addMobile(this.Lorann, 2, 2);
		
		this.mapPlay = new MapPlay(this.mapWorld);
		
		//this.mapWorld.addMobile(Lorann, 2, 2);
		SwingUtilities.invokeLater(this);
		this.mapPlay.play();
		
		
	}
	/*
	public void play() throws IOException {
		
			this.show(0, 0 , WINDOW_WIDTH, WINDOW_HEIGHT);
			final int key = System.in.read();
			switch (key) {
				case 10:
					this.getLorann().moveUp();
					break;
				case 1:
					this.getLorann().moveRight();
					break;
				case 9:
					this.getLorann().moveDown();
					break;
				case 49:
					this.getLorann().moveLeft();
					break;
				default:
					break;
			}
		
	}
*//*
	public void show(final int cornerX, final int cornerY,final int width, final int height) {
		final String arrayToPrint[] = new String[this.mapWorld.getHeight()];

		for (int y = 0; y < this.mapWorld.getHeight(); y++) {
			arrayToPrint[y] = "";
			for (int x = 0; x < this.mapWorld.getWidth(); x++) {
				final Element element = this.mapWorld.getElements(x, y);
				
				
				if (element != null) {
					arrayToPrint[y] += this.mapWorld.getElements(x, y).getSprite();
					
					
				}
				
				else {
					arrayToPrint[y] += "  ";
				}
			}
		}
		for (final Mobile mobile : this.mapWorld.mobiles) {
			if ((mobile.getX() >= cornerX) && (mobile.getX() < (width + cornerX)) && (mobile.getY() >= cornerY) && (mobile.getY() < (width + cornerY))) {
				arrayToPrint[mobile.getY() - cornerY] = arrayToPrint[mobile.getY() - cornerY].substring(0, (mobile.getX() - cornerX) * 2) + mobile.getSprite()
						+ arrayToPrint[mobile.getY() - cornerY].substring(((mobile.getX() - cornerX) + 1) * 2);
			}
		}

		

		for (final String stringToPrint : arrayToPrint) {
			System.out.println(stringToPrint);
		}
	}
	*/
	@Override
	public void run() {
		//new Fenetre();
		
		final BoardFrame boardFrame = new BoardFrame("Lorann Yolo",
				WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_HEIGHT, this.getMapWorld().getElements(), this.getMapWorld().getMobiles(), this.getMapWorld().getLorann().getPosition());
		this.mapWorld.addObserver(boardFrame.getBoardPanel());
	}
	public void play() throws IOException {
		this.getMapPlay().play();
	}

	public MapWorld getMapWorld() {
		return this.mapWorld;
	}

	public MapPlay getMapPlay() {
		return this.mapPlay;
	}

}
