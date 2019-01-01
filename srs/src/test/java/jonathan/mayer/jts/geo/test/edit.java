package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class edit {


	
	@Test
	public void ST_Densify() {

//		GeoFunctions.ST_Densify(geom, tolerance)
		Geom g = GeoFunctions.ST_GeomFromText("LINESTRING (140 200, 170 150)");
		Geom r = GeoFunctions.ST_Densify(g,Double.parseDouble("10"));
		assertEquals("LINESTRING (140 200, 145 191.66666666666666, 150 183.33333333333334, 155 175, 160 166.66666666666669, 165 158.33333333333334, 170 150)",
				r.g().toString());
		
	}
	
	@Test
	public void ST_Normalize() {

//		GeoFunctions.ST_Densify(geom, tolerance)
		
		String wkt ="POLYGON ((170 180, 310 180, 308 190, 310 206, 340 320, 135 333, 140 260, 170 180))";
		wkt.toString();
		Geom g = GeoFunctions.ST_GeomFromText(wkt.toString());
		Geom r = GeoFunctions.ST_Normalize(g);
		assertEquals("POLYGON ((135 333, 340 320, 310 206, 308 190, 310 180, 170 180, 140 260, 135 333))",
				r.g().toString());
		
	}
	
	@Test
	public void ST_CollectionExtract() {

//		GeoFunctions.ST_Densify(geom, tolerance)
		
		String wkt ="GEOMETRYCOLLECTION( MULTIPOINT((4 4), (1 1), (1 0), (0 3)),LINESTRING(2 6, 6 2),POLYGON((1 2, 4 2, 4 6, 1 6, 1 2)))";
		
		Geom g = GeoFunctions.ST_GeomFromText(wkt.toString());
		Geom r = GeoFunctions.ST_CollectionExtract(g,2);
		assertEquals("MULTILINESTRING ((2 6, 6 2))",
				r.g().toString());
		
	}
	
	@Test
	public void ST_Reverse() {

//		GeoFunctions.ST_Densify(geom, tolerance)
		Geom g = GeoFunctions.ST_GeomFromText("LINESTRING (105 353, 150 180, 300 280)");
		Geom r = GeoFunctions.ST_Reverse(g);
		assertEquals("LINESTRING (300 280, 150 180, 105 353)",
				r.g().toString());
		
	}
	
	
	
}
