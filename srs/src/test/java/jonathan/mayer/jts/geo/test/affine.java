package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class affine {

	@Test
	public void ST_Scale() {

		String wkt = "LINESTRING(1 2, 4 5)";
		Geom geom = GeoFunctions.ST_GeomFromText(wkt);
		double xscale = Double.parseDouble("0.5");
		double yscale = Double.parseDouble("0.75");
		Geom result = GeoFunctions.ST_Scale(geom, xscale, yscale);

		String resultstr = "LINESTRING (0.5 1.5, 2 3.75)";
		assertEquals(resultstr, result.g().toString());

		wkt = "LINESTRING(1 2, 4 5)";
		geom = GeoFunctions.ST_GeomFromText(wkt);
		xscale = Double.parseDouble("0");
		yscale = Double.parseDouble("-1");
		result = GeoFunctions.ST_Scale(geom, xscale, yscale);

		resultstr = "LINESTRING (0 -2, 0 -5)";
		assertEquals(resultstr, result.g().toString());

	}

	@Test
	public void ST_Rotate() {

		String wkt = "LINESTRING(1 3, 1 1, 2 1)";
		Geom geom = GeoFunctions.ST_GeomFromText(wkt);
		double angle = Math.PI;
		double xscale = Double.parseDouble("0.5");
		double yscale = Double.parseDouble("0.75");
		Geom result = GeoFunctions.ST_Rotate(geom, angle);

		String resultstr = "LINESTRING (2 1, 2 3, 1 3)";
//		assertEquals(resultstr, result.g().toString());

		 wkt = "LINESTRING(1 2, 4 5)";
		 geom = GeoFunctions.ST_GeomFromText(wkt);
		 angle = Math.PI;
		 xscale = Double.parseDouble("0.5");
		 yscale = Double.parseDouble("0.75");
		 result = GeoFunctions.ST_Rotate(geom,angle);
		
		 resultstr = "LINESTRING (0 -2, 0 -5)";
		 assertEquals(resultstr,result.g().toString());

	}
	
	
	@Test
	public void ST_Translate() {

		String wkt = "POINT(-71.01 42.37)";
		Geom geom = GeoFunctions.ST_GeomFromText(wkt);
		double xscale = Double.parseDouble("1");
		double yscale = Double.parseDouble("0");
		Geom result = GeoFunctions.ST_Translate(geom, xscale, yscale);

		String resultstr = "POINT (-70.01 42.37)";
		assertEquals(resultstr, result.g().toString());

		wkt = "LINESTRING(-71.01 42.37,-71.11 42.38)";
		geom = GeoFunctions.ST_GeomFromText(wkt);
		xscale = Double.parseDouble("1");
		yscale = Double.parseDouble("0.5");
		result = GeoFunctions.ST_Translate(geom, xscale, yscale);
		
		resultstr = "LINESTRING (-70.01 42.87, -70.11 42.88)";
		assertEquals(resultstr, result.g().toString());

	}

}
