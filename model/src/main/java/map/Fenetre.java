package map;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;

public class Fenetre extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Fenetre()
	{
	
	this.setTitle("Lorann Yolo");
	this.setSize(400, 400);
	//fenetre.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel pan = new JPanel();
	pan.setBackground(Color.orange);
	this.setContentPane(pan);
	this.setVisible(true);
	}

}
