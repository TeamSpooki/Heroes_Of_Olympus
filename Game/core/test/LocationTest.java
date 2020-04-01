import static org.junit.Assert.*;

import org.junit.Test;

import com.mygdx.game.Location;

public class LocationTest {

	@Test
	public void aboveLocation() {
		Location above = new Location(64,-192);
		assertEquals(new Integer((int)above.aboveLocation().getY()), new Integer(-256));
	}
	
	@Test
	public void belowLocation() {
		Location below = new Location(256,256);
		assertEquals(new Integer((int)below.belowLocation().getY()), new Integer(320));
	}
	
	@Test
	public void leftLocation() {
		Location left = new Location(-128, -128);
		assertEquals(new Integer((int)left.leftLocation().getX()), new Integer(-192));
	}
	
	@Test 
	public void rightLocation() {
		Location right = new Location(64, 64);
		assertEquals(new Integer((int)right.rightLocation().getX()), new Integer(128));
	}
	
	@Test
	public void checkEquals() {
		Location herculesLoc = new Location(128,128);
		Location  helenLoc = new Location(128,128);
		assertEquals(herculesLoc.equals(helenLoc), helenLoc.equals(herculesLoc));
		System.out.print(herculesLoc);
		System.out.print(helenLoc);
	}
}
