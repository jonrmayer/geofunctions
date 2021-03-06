package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class predicates {

	
	

	@Test
	public void ST_Contains() {
		assertEquals(false, GeoFunctions.ST_Contains(GeoFunctions.ST_GeomFromText("POINT(0 0)"),
				GeoFunctions.ST_GeomFromText("POINT(1 2)")));
		assertEquals(true, GeoFunctions.ST_Contains(GeoFunctions.ST_GeomFromText("POINT(0 0)"),
				GeoFunctions.ST_GeomFromText("POINT(0 0)")));

	}
	@Test
	public void ST_Covers() {
//		GeoFunctions.ST_Covers(geom1, geom2)
		
		Geom geom1 = GeoFunctions.ST_GeomFromText("POINT(1 2)");
		Geom geom2 = GeoFunctions.ST_Buffer(geom1,Double.parseDouble("10"));
		Boolean result = GeoFunctions.ST_Covers(geom1, geom1);
		assertEquals(true, result);
		result = GeoFunctions.ST_Covers(geom1, geom2);
		assertEquals(false, result);
		
		
	}

	@Test
	public void ST_ContainsProperly() {
		
		// GeoFunctions.ST_ContainsProperly(geom1, geom2)
		// assertEquals(false,GeoFunctions.ST_Contains(GeoFunctions.ST_GeomFromText("POINT(0
		// 0)"),GeoFunctions.ST_GeomFromText("POINT(1 2)")));
		// assertEquals(true,GeoFunctions.ST_Contains(GeoFunctions.ST_GeomFromText("POINT(0
		// 0)"),GeoFunctions.ST_GeomFromText("POINT(0 0)")));

	}

	@Test
	public void ST_Crosses() {
		// GeoFunctions.ST_Crosses(geom1, geom2)
		assertEquals(true, GeoFunctions.ST_Crosses(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));
		assertEquals(false, GeoFunctions.ST_Crosses(GeoFunctions.ST_GeomFromText("LINESTRING(176 149, 176 151)"),
				GeoFunctions.ST_GeomFromText("POLYGON((175 150, 20 40, 50 60, 125 100, 175 150))")));

	}

	@Test
	public void ST_Disjoint() {
		// GeoFunctions.ST_Disjoint(geom1, geom2)
		assertEquals(false, GeoFunctions.ST_Disjoint(GeoFunctions.ST_GeomFromText("POINT(0 0)"),
				GeoFunctions.ST_GeomFromText("LINESTRING ( 0 0, 0 2 )")));
		assertEquals(false, GeoFunctions.ST_Disjoint(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));

	}
	
	@Test
	public void ST_Difference() {
		// GeoFunctions.ST_Disjoint(geom1, geom2)
		assertEquals("POLYGON ((7 2, 7 1, 1 1, 1 6, 3 6, 3 2, 7 2))", 
				GeoFunctions.ST_Difference(
						GeoFunctions.ST_GeomFromText("POLYGON((1 1, 7 1, 7 6, 1 6, 1 1))"),
				GeoFunctions.ST_GeomFromText("POLYGON((3 2, 8 2, 8 8, 3 8, 3 2))")
				).g().toString());
	

	}
//	POLYGON((1 1, 7 1, 7 6, 1 6, 1 1))	POLYGON((3 2, 8 2, 8 8, 3 8, 3 2))
	

	@Test
	public void ST_DWithin() {
		//// GeoFunctions.ST_DWithin(geom1, geom2, distance)
		// assertEquals(false,GeoFunctions.ST_Disjoint(GeoFunctions.ST_GeomFromText("POINT(0
		//// 0)"),GeoFunctions.ST_GeomFromText("LINESTRING ( 0 0, 0 2 )")));
		// assertEquals(false,GeoFunctions.ST_Disjoint(GeoFunctions.ST_GeomFromText("LINESTRING(1
		//// 3, 5 3)"),GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));

	}

	

	@Test
	public void ST_EnvelopesIntersect() {
		// GeoFunctions.ST_EnvelopesIntersect(geom1, geom2)
		assertEquals(true, GeoFunctions.ST_EnvelopesIntersect(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));
	
	}

	@Test
	public void ST_Equals() {
		// GeoFunctions.ST_Equals(geom1, geom2)
		assertEquals(false, GeoFunctions.ST_Equals(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));
		assertEquals(true, GeoFunctions.ST_Equals(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)")));
		
	}

	
	@Test
	public void ST_Intersects() {
		// GeoFunctions.ST_Intersects(geom1, geom2)
		
		assertEquals(true, GeoFunctions.ST_Intersects(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)")));
	
	}
	
	@Test
	public void ST_Relate() {
//		SELECT ST_Relate('LINESTRING(1 2, 3 4)',
//                'LINESTRING(5 6, 7 3)');
//		
		
		Geom l1 = GeoFunctions.ST_GeomFromText("LINESTRING(1 2, 3 4)");
		Geom l2 = GeoFunctions.ST_GeomFromText("LINESTRING(5 6, 7 3)");
		String result = GeoFunctions.ST_Relate(l1, l2);
		assertEquals("FF1FF0102", result);
		
//		SELECT ST_Relate('POLYGON((1 1, 4 1, 4 5, 1 5, 1 1))',
//                'POLYGON((3 2, 6 2, 6 6, 3 6, 3 2))',
//                '212101212');
		
		Geom p1 = GeoFunctions.ST_GeomFromText("POLYGON((1 1, 4 1, 4 5, 1 5, 1 1))");
		Geom p2 = GeoFunctions.ST_GeomFromText("POLYGON((3 2, 6 2, 6 6, 3 6, 3 2))");
		Boolean resultb = GeoFunctions.ST_Relate(p1, p2,"212101212");
		assertEquals(true, resultb);
	}

	
	@Test
	public void ST_Overlaps() {

//		GeoFunctions.ST_Overlaps(geom1, geom2)
		assertEquals(true, GeoFunctions.ST_Overlaps(GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));

		assertEquals(false, GeoFunctions.ST_Overlaps(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(50 50, 100 100, 150 150)")));

	}
	
	@Test
	public void ST_SymDifference() {
		// GeoFunctions.ST_Disjoint(geom1, geom2)
		assertEquals("MULTIPOLYGON (((7 2, 7 1, 1 1, 1 6, 3 6, 3 2, 7 2)), ((7 2, 7 6, 3 6, 3 8, 8 8, 8 2, 7 2)))", 
				GeoFunctions.ST_SymDifference(
						GeoFunctions.ST_GeomFromText("POLYGON((1 1, 7 1, 7 6, 1 6, 1 1))"),
				GeoFunctions.ST_GeomFromText("POLYGON((3 2, 8 2, 8 8, 3 8, 3 2))")
				).g().toString());
	

	}
	
	@Test
	public void ST_Touches() {

//		GeoFunctions.ST_Touches(geom1, geom2)
		

		assertEquals(true, GeoFunctions.ST_Touches(GeoFunctions.ST_GeomFromText("LINESTRING(0 0, 1 1, 0 2)"),
				GeoFunctions.ST_GeomFromText("POINT(0 2)")));
		
	}

	@Test
	public void ST_Within() {

//		GeoFunctions.ST_Within(geom1, geom2)
		
		Geom smallc = GeoFunctions.ST_Buffer(GeoFunctions.ST_GeomFromText("POINT(50 50)"),Double.parseDouble("20"));
		Geom bigc = GeoFunctions.ST_Buffer(GeoFunctions.ST_GeomFromText("POINT(50 50)"),Double.parseDouble("40"));
		assertEquals(true, GeoFunctions.ST_Within(smallc,smallc));
		assertEquals(true, GeoFunctions.ST_Within(smallc,bigc));
		assertEquals(false, GeoFunctions.ST_Within(bigc,smallc));
		assertEquals(true, GeoFunctions.ST_Within(GeoFunctions.ST_Union(smallc,bigc),bigc));
		assertEquals(true, GeoFunctions.ST_Within(bigc,GeoFunctions.ST_Union(smallc,bigc)));
		
	}
	
}
