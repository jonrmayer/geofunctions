package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class trig {

	@Test
	public void ST_Azimuth() {
		
		String pointwkt1 ="POINT(0 1)";
		Geom point1 = GeoFunctions.ST_GeomFromText(pointwkt1);
		
		String pointwkt2 ="POINT(10 1)";
		Geom point2 = GeoFunctions.ST_GeomFromText(pointwkt2);
		
		String pointwkt3 ="POINT(2 2)";
		Geom point3 = GeoFunctions.ST_GeomFromText(pointwkt3);
		
		Double result1  = GeoFunctions.ST_Azimuth(point1,point2);
		String resultstr1 = result1.toString();
		assertEquals("90.0",resultstr1);
		
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
