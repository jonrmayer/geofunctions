package jonathan.mayer.SpatialReference;

import jonathan.mayer.srs.GeoFunctions;
import jonathan.mayer.srs.GeoFunctions.Geom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class convert {
	
	@Test
	public void ST_AsText() {
		assertEquals("POINT (40 50)", GeoFunctions.ST_AsText(GeoFunctions.ST_GeomFromText("POINT(40 50)")));
		assertNotEquals("POINT (40 51)", GeoFunctions.ST_AsText(GeoFunctions.ST_GeomFromText("POINT(40 50)")));
	}
	@Test
	public void ST_AsWKB() {
		
		
		assertEquals("POINT (40 50)", GeoFunctions.ST_GeomFromWKB(GeoFunctions.ST_AsWKB(GeoFunctions.ST_GeomFromText("POINT(40 50)"))).g().toString());
		
		
	}
	@Test
	public void ST_AsWKT() {
		assertEquals("POINT (40 50)", GeoFunctions.ST_AsWKT(GeoFunctions.ST_GeomFromText("POINT(40 50)")));
		assertNotEquals("POINT (40 51)", GeoFunctions.ST_AsWKT(GeoFunctions.ST_GeomFromText("POINT(40 50)")));
	}
	
	@Test
	public void ST_GeomFromWKB() {
		
		
		assertEquals("POINT (40 50)", GeoFunctions.ST_GeomFromWKB(GeoFunctions.ST_AsWKB(GeoFunctions.ST_GeomFromText("POINT(40 50)"))).g().toString());
		
	}
	 
	
	@Test
	public void ST_LineFromText() {
		// GeoFunctions.ST_LineFromText(s)

		assertEquals("LINESTRING (1 2, 3 4)", GeoFunctions.ST_LineFromText("LINESTRING(1 2, 3 4)").g().toString());
		assertEquals("LINESTRING (1 2, 3 4)",
				GeoFunctions.ST_LineFromText("LINESTRING(1 2, 3 4)", 4326).g().toString());

	}

	@Test
	public void ST_MLineFromText() {

//		GeoFunctions.ST_MLineFromText(s)

		assertEquals("MULTILINESTRING ((1 2, 3 4), (4 5, 6 7))", GeoFunctions.ST_MLineFromText("MULTILINESTRING((1 2, 3 4), (4 5, 6 7))").g().toString());
		assertEquals("MULTILINESTRING ((1 2, 3 4), (4 5, 6 7))", GeoFunctions.ST_MLineFromText("MULTILINESTRING((1 2, 3 4), (4 5, 6 7))",4326).g().toString());


	}
	@Test
	public void ST_MPointFromText() {

//		GeoFunctions.ST_MPointFromText(s)

		assertEquals("MULTIPOINT ((1 2), (3 4))", GeoFunctions.ST_MPointFromText("MULTIPOINT(1 2, 3 4)").g().toString());
		assertEquals("MULTIPOINT ((1 2), (3 4))", GeoFunctions.ST_MPointFromText("MULTIPOINT(1 2, 3 4)",4326).g().toString());


	}
	
	@Test
	public void ST_MPolyFromText() {

//		GeoFunctions.ST_MPolyFromText(s)

		assertEquals("MULTIPOLYGON (((0 0, 20 0, 20 20, 0 20, 0 0), (5 5, 5 7, 7 7, 7 5, 5 5)))", GeoFunctions.ST_MPolyFromText("MULTIPOLYGON(((0 0 1,20 0 1,20 20 1,0 20 1,0 0 1),(5 5 3,5 7 3,7 7 3,7 5 3,5 5 3)))").g().toString());
		assertEquals("MULTIPOLYGON (((0 0, 20 0, 20 20, 0 20, 0 0), (5 5, 5 7, 7 7, 7 5, 5 5)))", GeoFunctions.ST_MPolyFromText("MULTIPOLYGON(((0 0 1,20 0 1,20 20 1,0 20 1,0 0 1),(5 5 3,5 7 3,7 7 3,7 5 3,5 5 3)))",4326).g().toString());


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
	public void ST_Point() {

//		GeoFunctions.ST_Point(x, y)
		
		assertEquals("POINT (1 0)", GeoFunctions.ST_MakePoint(new BigDecimal(1), new BigDecimal(0)).g().toString());
		
	}

	


	@Test
	public void ST_PointFromText() {

		assertEquals("POINT (40 50)", GeoFunctions.ST_PointFromText("POINT(40 50)").g().toString());
		assertEquals("POINT (40 50)", GeoFunctions.ST_PointFromText("POINT(40 50)", 4326).g().toString());

		assertNotEquals("POINT (40 51)", GeoFunctions.ST_PointFromText("POINT(40 50)").g().toString());
		assertNotEquals("POINT (40 51)", GeoFunctions.ST_PointFromText("POINT(40 50)", 4326).g().toString());

	}
	
	@Test
	public void ST_PolyFromText() {

//		GeoFunctions.ST_PolyFromText(s)
		
		assertEquals("POLYGON ((10 130, 50 190, 110 190, 140 150, 150 80, 100 10, 20 40, 10 130), (70 40, 100 50, 120 80, 80 110, 50 90, 70 40))", GeoFunctions.ST_PolyFromText("POLYGON(( 10 130, 50 190, 110 190, 140 150, 150 80, 100 10, 20 40, 10 130 ),( 70 40, 100 50, 120 80, 80 110, 50 90, 70 40 ))").g().toString());
		assertEquals("POLYGON ((10 130, 50 190, 110 190, 140 150, 150 80, 100 10, 20 40, 10 130), (70 40, 100 50, 120 80, 80 110, 50 90, 70 40))", GeoFunctions.ST_PolyFromText("POLYGON(( 10 130, 50 190, 110 190, 140 150, 150 80, 100 10, 20 40, 10 130 ),( 70 40, 100 50, 120 80, 80 110, 50 90, 70 40 ))",4326).g().toString());
	}
	
	

}
