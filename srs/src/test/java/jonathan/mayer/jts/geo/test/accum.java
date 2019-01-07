package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class accum {

	@Test
	public void ST_Accum() {
		
		
		
		String pointwkt1 ="POINT(0 0)";
		Geom point1 = GeoFunctions.ST_GeomFromText(pointwkt1);
		
		String pointwkt2 ="POINT(1 1)";
		Geom point2 = GeoFunctions.ST_GeomFromText(pointwkt2);
		
		String pointwkt3 ="POINT(2 2)";
		Geom point3 = GeoFunctions.ST_GeomFromText(pointwkt3);
		
		Geom result1  = GeoFunctions.ST_Accum(point1,point2,point3);
		String resultstr1 = result1.g().toString();
		assertEquals("MULTIPOINT ((0 0), (1 1), (2 2))",resultstr1);
		
//	SELECT	ST_Accum(ST_GeomFromText('POINT(0 0)'),ST_GeomFromText('POINT(1 1)')) g
//		MULTIPOINT ((0 0), (1 1), (2 2))
		
		String polywkt1 ="POLYGON((9 0, 9 11, 10 11, 10 0, 9 0))";
		Geom poly1 = GeoFunctions.ST_GeomFromText(polywkt1);
		String polywkt2 ="POLYGON((1 1, 1 7, 7 7, 7 1, 1 1))";
		Geom poly2 = GeoFunctions.ST_GeomFromText(polywkt2);
		
		
		
		
		
		Geom result2  = GeoFunctions.ST_Accum(poly1,poly2,point2,point3);
		
		String resultstr2 = result2.g().toString();
		assertEquals("GEOMETRYCOLLECTION (POLYGON ((9 0, 9 11, 10 11, 10 0, 9 0)), POLYGON ((1 1, 1 7, 7 7, 7 1, 1 1)), POINT (1 1), POINT (2 2))",resultstr2);

	}
	
	@Test
	public void ST_Collect() {
		
		String pointwkt1 ="POINT(0 0)";
		Geom point1 = GeoFunctions.ST_GeomFromText(pointwkt1);
		
		String pointwkt2 ="POINT(1 1)";
		Geom point2 = GeoFunctions.ST_GeomFromText(pointwkt2);
		
		String pointwkt3 ="POINT(2 2)";
		Geom point3 = GeoFunctions.ST_GeomFromText(pointwkt3);
		
		Geom result1  = GeoFunctions.ST_Collect(point1,point2,point3);
		String resultstr1 = result1.g().toString();
		assertEquals("MULTIPOINT ((0 0), (1 1), (2 2))",resultstr1);
		
		String polywkt1 ="POLYGON((9 0, 9 11, 10 11, 10 0, 9 0))";
		Geom poly1 = GeoFunctions.ST_GeomFromText(polywkt1);
		String polywkt2 ="POLYGON((1 1, 1 7, 7 7, 7 1, 1 1))";
		Geom poly2 = GeoFunctions.ST_GeomFromText(polywkt2);
		
		
		
		
		
		Geom result2  = GeoFunctions.ST_Collect(poly1,poly2,point2,point3);
		
		String resultstr2 = result2.g().toString();
		assertEquals("GEOMETRYCOLLECTION (POLYGON ((9 0, 9 11, 10 11, 10 0, 9 0)), POLYGON ((1 1, 1 7, 7 7, 7 1, 1 1)), POINT (1 1), POINT (2 2))",resultstr2);

	}

}
