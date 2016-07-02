package view;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import map.MapWorld;
import map.element.Element;


public class MapFrameTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGoodDimension() throws IOException {
		MapWorld mapWorld = new MapWorld(1);
		int height = mapWorld.getHeight();
		assertTrue (height == 11);
		int width = mapWorld.getWidth();
		assertTrue (width == 20);
		
	}
	@Test
	public void testCreationFrame() throws IOException
	{
		MapWorld mapWorld = new MapWorld(1);
		Element[][] elements = mapWorld.getElements();
		assertTrue(elements !=null);
		
	}

}
