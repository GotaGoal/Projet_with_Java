package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;



public class MapFrame extends JFrame implements KeyListener {
	private static final long				serialVersionUID	= 1500600853286674118L;
	private final MapPanel	mapPanel;
	//private NettleBoardPanel				meetingPanel;
	private final IOrderPerformed		mapPlay;
	private final MapCardView		mapCardView;
	private int ZOOM = 13;
	private final Point position;
	

	public MapFrame(final String title, final MapWorld mapWorld,final IOrderPerformed mapPlay) {
		this.position = new Point();
	
		this.position.setLocation(10, (float)5.5);
		
		this.setTitle(title);
		this.setSize(1100, 900);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mapPlay = mapPlay;
		this.mapPanel = new MapPanel(new Dimension(mapWorld.getWidth(), mapWorld.getHeight()), mapWorld.getElements(), mapWorld.getMobiles(),
				this.position.getLocation(), ZOOM);
		this.setResizable(false);
		mapWorld.addObserver(this.mapPanel);
		this.addKeyListener(this);
		this.mapCardView = new MapCardView();
		this.getContentPane().setLayout(this.mapCardView);
		this.getContentPane().add(this.mapPanel, "MAP");
		this.setVisible(true);
	}

	private IOrderPerformed getMapPlay() {
		return this.mapPlay;
	}
	private void setX(final int x)
	{
		this.position.x = x;
	}
	

	public void refresh(final Point center) {
		this.mapPanel.setCenter(center);
	}

	

	@Override
	public void keyPressed(final KeyEvent keyEvent) {
		try {
			this.getMapPlay().orderPerform(MapView.keyCodeToUserOrder(keyEvent.getKeyCode()));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(final KeyEvent arg0) {
	}

	@Override
	public void keyTyped(final KeyEvent arg0) {
	}

	
}
