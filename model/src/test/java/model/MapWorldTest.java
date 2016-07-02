package model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import map.MapWorld;
import map.Orientation;
import map.element.mobile.Lorann;
import map.element.motionless.Bone;
import map.element.motionless.MotionlessElement;


public class MapWorldTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() throws IOException {
		//fail("Not yet implemented");
		MapWorld mapWorld = new MapWorld(1);
		int id = mapWorld.getIdMap();
		assertTrue(1 ==id );
		
	}
	@Test
	public void verification_Lorann() throws IOException
	{
		MapWorld mapWorld = new MapWorld(1);
		mapWorld.addMobile(new Lorann(Orientation.ND), 1, 1);
		int x = mapWorld.getLorann().getX();
		int y = mapWorld.getLorann().getY();
		assertTrue(x ==1);
		assertTrue(y==1);
	}
	@Test
	public void verification_Element() throws IOException
	{
		MapWorld mapW = new MapWorld(1);
		mapW.addElement(new Bone(), 2, 2);
		MotionlessElement bone = new Bone();
		
		assertTrue(mapW.getElements(2, 2).getFileSymbol() ==bone.getFileSymbol() );
	}
	
	
	

}
