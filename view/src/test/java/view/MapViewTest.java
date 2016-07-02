package view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import contract.UserOrder;

public class MapViewTest {

	private static final int VK_UP = 38;
	private static final int VK_DOWN = 40;
	private static final int VK_RIGHT = 39;
	private static final int VK_LEFT = 37;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAppuieTouche() {
		
		UserOrder user = MapView.keyCodeToUserOrder(VK_UP);
		assertTrue(user == UserOrder.UP);
		UserOrder user1 = MapView.keyCodeToUserOrder(VK_DOWN);
		assertTrue(user1 == UserOrder.DOWN);
		UserOrder user2 = MapView.keyCodeToUserOrder(VK_RIGHT);
		assertTrue(user2 == UserOrder.RIGHT);
		UserOrder user3 = MapView.keyCodeToUserOrder(VK_LEFT);
		assertTrue(user3 == UserOrder.LEFT);
		
		
		
		
		
	}

}
