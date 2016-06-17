package map;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;



public class MapFrame extends JFrame implements KeyListener,IOrderPerformed {
	private static final long				serialVersionUID	= 1500600853286674118L;
	private final MapPanel	mapPanel;
	//private NettleBoardPanel				meetingPanel;
	private final IOrderPerformed		mapPlay;
	//private final NettleCardView		nettleCardView;

	public MapFrame(final String title, final MapWorld mapWorld,final IOrderPerformed mapPlay) {
		this.setTitle(title);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mapPlay = mapPlay;
		this.mapPanel = new MapPanel(new Dimension(mapWorld.getWidth(), mapWorld.getHeight()), mapWorld.getElements(), mapWorld.getMobiles(),
				mapWorld.getLorann().getPosition(), 8);
		this.setResizable(false);
		mapWorld.addObserver(this.mapPanel);
		this.addKeyListener(this);
		//this.nettleCardView = new NettleCardView();
		//this.getContentPane().setLayout(this.nettleCardView);
		this.getContentPane().add(this.mapPanel, "MAP");
		this.setVisible(true);
	}

	private IOrderPerformed getNettlePlay() {
		return this.mapPlay;
	}

	

	public void refresh(final Point center) {
		this.mapPanel.setCenter(center);
	}

	

	@Override
	public void keyPressed(final KeyEvent keyEvent) {
		try {
			this.getNettlePlay().orderPerform(MapView.keyCodeToUserOrder(keyEvent.getKeyCode()));
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

	@Override
	public void orderPerform(UserOrder userOrder) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
