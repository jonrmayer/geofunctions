package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class operators {

	
	@Test
	public void ST_Union() {

//		GeoFunctions.ST_Union(geoms)
		
		assertEquals("MULTIPOINT ((0 0), (1 0))", GeoFunctions
				.ST_Union(GeoFunctions.ST_PointFromText("POINT(0 0)"), GeoFunctions.ST_PointFromText("POINT(1 0)"))
				.g().toString());
		
	}
	
//	@Test
//	public void ST_SymDifference() {
//
//		String wkt1 = "LINESTRING(50 100, 50 200)";
//		String wkt2 = "LINESTRING(50 50, 50 150)";
//		Geom g1 = GeoFunctions.ST_GeomFromText(wkt1);
//		Geom g2 = GeoFunctions.ST_GeomFromText(wkt2);
//		Geom result = GeoFunctions.ST_SymDifference(g1, g2);
//		assertEquals("MULTILINESTRING ((50 150, 50 200),(50 50, 50 100))", result.g().toString());
//
//	}

	@Test
	public void ST_Intersection() {

		String wkt1 = "POINT(0 0)";
		String wkt2 = "LINESTRING ( 0 0, 0 2 )";
		Geom g1 = GeoFunctions.ST_GeomFromText(wkt1);
		Geom g2 = GeoFunctions.ST_GeomFromText(wkt2);
		Geom result = GeoFunctions.ST_Intersection(g1, g2);
		assertEquals("POINT (0 0)", result.g().toString());
	}
	
	@Test
	public void ST_ConvexHull() {

		String wkt = "MULTILINESTRING((100 190,10 8),(150 10, 20 30))";
		Geom g = GeoFunctions.ST_GeomFromText(wkt);
		Geom result = GeoFunctions.ST_ConvexHull(g);
		assertEquals("POLYGON ((10 8, 20 30, 100 190, 150 10, 10 8))", result.g().toString());

	}

//	@Test
//	public void ST_Difference() {
//
//		String wkt1 = "LINESTRING(50 100, 50 200)";
//		String wkt2 = "LINESTRING(50 50, 50 150)";
//		Geom g1 = GeoFunctions.ST_GeomFromText(wkt1,4326);
//		Geom g2 = GeoFunctions.ST_GeomFromText(wkt2,4326);
//		Geom result = GeoFunctions.ST_Difference(g1, g2);
//		assertEquals("LINESTRING (50 150, 50 200)", result.g().toString());
//	}
	
	

}
