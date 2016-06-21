package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import contract.IMapFrame;
import contract.IMapWorld;
import contract.IOrderPerformed;
import map.Animation;
import map.MapWorld;


public class MapFrame extends JFrame implements KeyListener,IMapFrame,MouseListener,ActionListener {
	private static final long				serialVersionUID	= 1500600853286674118L;
	
	public  MapPanel	mapPanel;
	
	private IOrderPerformed		mapPlay;
	private MapCardView		mapCardView;
	private int ZOOM = 13;
	private Point position;
	private JLabel label;
	private Animation menu;

	private JButton button = new JButton("Clique Moi");
	

	
	
	
	

	public MapFrame(final String title, final IMapWorld mapWorld,final IOrderPerformed mapPlay) {
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
		//mapWorld.addObserver(this.mapPanel);
		this.addKeyListener(this);
		this.mapCardView = new MapCardView();
		
		this.getContentPane().setLayout(this.mapCardView);
		this.getContentPane().add(this.mapPanel, "MAP");
		this.label = new JLabel("Votre score actuel est de: "+String.valueOf(this.mapPlay.getScore()));
		this.label.setForeground(Color.white);
		this.mapPanel.add(label);
		
		
		
		
		this.setVisible(true);
	}
	public MapFrame(final String title, final MapWorld mapWorld,final Animation mapPlay) throws InterruptedException {
		this.position = new Point();
		
	
		this.position.setLocation(10, (float)5.5);
		
		
		
		
		this.setTitle(title);
		this.setSize(1100, 900);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.menu = mapPlay;
		this.mapPanel = new MapPanel(new Dimension(mapWorld.getWidth(), mapWorld.getHeight()), mapWorld.getElements(), mapWorld.getMobiles(),
				this.position.getLocation(), ZOOM);
		this.setResizable(false);
		//mapWorld.addObserver(this.mapPanel);
		this.addKeyListener(this);
		this.mapCardView = new MapCardView();
		
		
		this.getContentPane().setLayout(this.mapCardView);
	  
		this.getContentPane().add(this.mapPanel, "MAP");
		/*
		
		button.setBackground(Color.black);
		 getContentPane().setLayout(new BorderLayout());
		 getContentPane().add(button,BorderLayout.NORTH);
		 getContentPane().add(mapPanel,BorderLayout.CENTER);*/
		 
		 //this.mapPanel.button.setLocation(300, 300);
		//this.label = new JLabel("Votre score actuel est de: "+String.valueOf(this.menu.getScore()));
		//this.label.setForeground(Color.white);
		//this.mapPanel.add(label);
		this.button.setForeground(Color.RED);
		 //this.button.setBackground(Color.GREEN);
		 this.mapPanel.add(button);
		 button.addActionListener(this);
		
		 
		 
		
		
		
		 
		// mapPanel.button.setLocation(300, 100);
		
		
		
		
		this.setVisible(true);
		
	}
	
	@Override
	public void setTextChange()
	{
		this.label.setText("Votre score actuel est de: "+String.valueOf(this.mapPlay.getScore()));
	}
	@Override
	public void showMessage(final String message)
	{
		this.setBackground(Color.black);
		JOptionPane.showMessageDialog(null, message);
	}
	@Override
	public void setMeeting(final IMapWorld mapWorld) {
		
		this.getContentPane().removeAll();
		if (this.mapPanel != null) {
	
			this.mapCardView.removeLayoutComponent(this.mapPanel);
		}
		System.out.println("coucou ca va "+String.valueOf(this.mapPlay.getScore()));
		
		
		System.out.println(String.valueOf(this.mapPlay.getScore()));
		
		this.mapPanel = new MapPanel(new Dimension(mapWorld.getWidth(), mapWorld.getHeight()), mapWorld.getElements(),
				mapWorld.getMobiles(), this.position.getLocation(),ZOOM);
		mapWorld.addObserver(this.mapPanel);
		//this.addKeyListener(this);
		
		this.getContentPane().add(this.mapPanel, "MAP");
		this.label = new JLabel("Votre score actuel est de: "+String.valueOf(this.mapPlay.getScore()));
		this.label.setForeground(Color.white);
		this.mapPanel.add(label);
		this.revalidate();
		
		
		
	}

	public IOrderPerformed getMapPlay() {
		return this.mapPlay;
	}
	public void setX(final int x)
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
			this.setBackground(Color.black);
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
	public void setBack()
	{
		this.setBackground(Color.black);
	}
	@Override
	public void repainture(final int i)
	{
		this.repaint(i);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		/*try {
			this.menu.Tracer();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		/*try {
			this.menu.Tracer();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		/*try {
			this.menu.Tracer();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == button)
		 {
			 
						this.menu.startTimer();
					
		 }
		
		
	}
	
}
