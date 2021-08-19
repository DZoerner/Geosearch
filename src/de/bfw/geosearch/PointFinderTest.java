package de.bfw.geosearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

class PointFinderTest {

	@Test
	void test() {
		PointFinder finder = new PointFinder();
				
		finder.add(new Point(233, 777));
		finder.add(new Point(23, 7777));
		finder.add(new Point(333, 777));

		Point p1 = new Point(2333, 7777);
		Point p2 = new Point(2333, 7888);
		Point p3 = new Point(2434, 7877);
		Point p4 = new Point(2534, 7877);
				
		finder.add(p1);
		finder.add(p2);
		finder.add(p3);
		finder.add(p4);
		
		finder.add(new Point(2834, 7977));
		
		Point p5 = new Point(2334, 9977);
		finder.add(p5);
		
		Set<Point> found = finder.findPoints(2333,  7777, 2540, 8000);
		
		assertEquals(found.size(), 4, "4 points should be found");

		assertTrue(found.contains(p1), "p1 not found");
		assertTrue(found.contains(p2), "p2 not found");
		assertTrue(found.contains(p3), "p3 not found");
		assertTrue(found.contains(p4), "p4 not found");
		
		
		found = finder.findPoints(2333,  7777, 2540, 7887);
		
		assertEquals(found.size(), 3, "3 points should be found");

		assertTrue(found.contains(p1), "p1 not found");
		assertTrue(found.contains(p3), "p3 not found");
		assertTrue(found.contains(p4), "p4 not found");

		
		finder.move(p5, 2400, 7850);
		
		found = finder.findPoints(2333,  7777, 2540, 8000);
		
		assertEquals(found.size(), 5, "5 points should be found");

		assertTrue(found.contains(p1), "p1 not found");
		assertTrue(found.contains(p2), "p2 not found");
		assertTrue(found.contains(p3), "p3 not found");
		assertTrue(found.contains(p4), "p4 not found");
		assertTrue(found.contains(p5), "p5 not found");		

		
		finder.move(p5, 1, 1);
		
		found = finder.findPoints(2333,  7777, 2540, 8000);
		
		assertEquals(found.size(), 4, "4 points should be found");

		assertTrue(found.contains(p1), "p1 not found");
		assertTrue(found.contains(p2), "p2 not found");
		assertTrue(found.contains(p3), "p3 not found");
		assertTrue(found.contains(p4), "p4 not found");
		
		found = finder.findPoints(0,  0, 10, 10);
		
		assertEquals(found.size(), 1, "1 points should be found");
		assertTrue(found.contains(p5), "p5 not found");
	}
}
