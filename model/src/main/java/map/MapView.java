package map;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public abstract class MapView {
	public static UserOrder keyCodeToUserOrder(final int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_UP:
				return UserOrder.UP;
			case KeyEvent.VK_RIGHT:
				return UserOrder.RIGHT;
			case KeyEvent.VK_DOWN:
				return UserOrder.DOWN;
			case KeyEvent.VK_LEFT:
				return UserOrder.LEFT;
			default:
				return UserOrder.NOP;
		}
	}

	public static void displayMessage(final String message) {
		JOptionPane.showMessageDialog(null, message);
	}

}
