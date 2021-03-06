package controller;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Timer;

import org.junit.Before;
import org.junit.Test;

import map.MapWorld;
import map.Orientation;
import map.element.mobile.Lorann;

public class MapPlayTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOrientationLorann() throws IOException {
		
		MapWorld mapWorld = new MapWorld(1);
		mapWorld.addMobile(new Lorann(Orientation.EAST), 1, 1);
		mapWorld.getLorann().setOrientation(Orientation.NORTH);
		assertTrue(mapWorld.getLorann().getOrientation() == Orientation.NORTH);
		
		
		
	}
	
	@Test
	public void testDeplacement() throws IOException, InterruptedException
	{
		MapWorld mapWorld = new MapWorld(1);
		MapPlay mapPlay = new MapPlay(mapWorld);
		mapWorld.addMobile(new Lorann(Orientation.EAST), 1, 1);
		mapWorld.getLorann().moveRight();
		assertTrue(mapWorld.getLorann().getX() == 2);
	}
	
	@Test
	public void testResolveUp() throws IOException
	{
		MapWorld mapWorld = new MapWorld(1);
		MapWorld mapWorld2 = new MapWorld(2);
		assertTrue(mapWorld != mapWorld2);
		
	}
	@Test
	public void testAddLorann() throws IOException
	{
		MapWorld mapWorld = new MapWorld(1);
		Lorann lorann = new Lorann(Orientation.EAST);
		Lorann lorann2 = new Lorann(Orientation.EAST);
		mapWorld.addMobile(lorann, 3, 3);
		mapWorld.addMobile(lorann2, 4, 4);
		assertTrue(lorann.getX() != lorann2.getX()&& lorann.getY() != lorann2.getY());
	
	}
	/*
	@Test
	public void testResolve() throws IOException
	{
		MapWorld mapWorld = new MapWorld(1);
		MapPlay mapPlay = new MapPlay(mapWorld);
		int x = 2;
		mapPlay.resolveUp();
		assertTrue(x == mapPlay.getCalcul());
	}
*/
}
