package model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import map.MapWorld;
import map.element.Permeability;
import map.element.motionless.Bone;
import map.element.motionless.MotionlessElement;

public class ElementTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPermeability() throws IOException {
		
		MotionlessElement Bone = new Bone();
		assertTrue(Bone.getPermeability()!=Permeability.PENETRABLE);
		
		
		
	}

}
