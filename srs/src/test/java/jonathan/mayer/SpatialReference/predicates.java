package jonathan.mayer.SpatialReference;

import jonathan.mayer.srs.GeoFunctions;
import jonathan.mayer.srs.GeoFunctions.Geom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class predicates {

	
	

	@Test
	public void ST_Contains() {
		assertEquals(false, GeoFunctions.ST_Contains(GeoFunctions.ST_GeomFromText("POINT(0 0)"),
				GeoFunctions.ST_GeomFromText("POINT(1 2)")));
		assertEquals(true, GeoFunctions.ST_Contains(GeoFunctions.ST_GeomFromText("POINT(0 0)"),
				GeoFunctions.ST_GeomFromText("POINT(0 0)")));

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
	public void ST_Overlaps() {

//		GeoFunctions.ST_Overlaps(geom1, geom2)
		assertEquals(true, GeoFunctions.ST_Overlaps(GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));

		assertEquals(false, GeoFunctions.ST_Overlaps(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(50 50, 100 100, 150 150)")));

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
