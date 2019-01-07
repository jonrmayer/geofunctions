package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class measurement {

	@Test
	public void ST_ClosestPoint() {
		
		String pointwkt1 ="POINT(4 8)";
		Geom point1 = GeoFunctions.ST_GeomFromText(pointwkt1);
		
		String polywkt ="LINESTRING(1 2, 3 6, 5 7, 4 1)";
		Geom poly = GeoFunctions.ST_GeomFromText(polywkt);
		
		
		
		Geom result1  = GeoFunctions.ST_ClosestPoint(point1,poly);
		String resultstr1 = result1.toString();
		assertEquals("POINT (4 8)",resultstr1);
		
		 result1  = GeoFunctions.ST_ClosestPoint(poly,point1);
		 resultstr1 = result1.toString();
		assertEquals("POINT (4.6 6.8)",resultstr1);
		
//		String polywkt1 ="POLYGON((9 0, 9 11, 10 11, 10 0, 9 0))";
//		Geom poly1 = GeoFunctions.ST_GeomFromText(polywkt1);
//		String polywkt2 ="POLYGON((1 1, 1 7, 7 7, 7 1, 1 1))";
//		Geom poly2 = GeoFunctions.ST_GeomFromText(polywkt2);
//		
//		
//		
//		
//		
//		Geom result2  = GeoFunctions.ST_Accum(poly1,poly2,point2,point3);
//		
//		String resultstr2 = result2.g().toString();
//		assertEquals("GEOMETRYCOLLECTION (POLYGON ((9 0, 9 11, 10 11, 10 0, 9 0)), POLYGON ((1 1, 1 7, 7 7, 7 1, 1 1)), POINT (1 1), POINT (2 2))",resultstr2);

	}
	
	@Test
	public void ST_ClosestCoordinate() {
		
//		String pointwkt1 ="POINT(4 8)";
//		Geom point1 = GeoFunctions.ST_GeomFromText(pointwkt1);
//		
//		String polywkt ="LINESTRING(1 2, 3 6, 5 7, 4 1)";
//		Geom poly = GeoFunctions.ST_GeomFromText(polywkt);
//		
//		
//		
//		Geom result1  = GeoFunctions.ST_ClosestPoint(point1,poly);
//		String resultstr1 = result1.toString();
//		assertEquals("POINT (4 8)",resultstr1);
//		
//		 result1  = GeoFunctions.ST_ClosestPoint(poly,point1);
//		 resultstr1 = result1.toString();
//		assertEquals("POINT (4.6 6.8)",resultstr1);
		
//		String polywkt1 ="POLYGON((9 0, 9 11, 10 11, 10 0, 9 0))";
//		Geom poly1 = GeoFunctions.ST_GeomFromText(polywkt1);
//		String polywkt2 ="POLYGON((1 1, 1 7, 7 7, 7 1, 1 1))";
//		Geom poly2 = GeoFunctions.ST_GeomFromText(polywkt2);
//		
//		
//		
//		
//		
//		Geom result2  = GeoFunctions.ST_Accum(poly1,poly2,point2,point3);
//		
//		String resultstr2 = result2.g().toString();
//		assertEquals("GEOMETRYCOLLECTION (POLYGON ((9 0, 9 11, 10 11, 10 0, 9 0)), POLYGON ((1 1, 1 7, 7 7, 7 1, 1 1)), POINT (1 1), POINT (2 2))",resultstr2);

	}

}
