package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class generalize {

	@Test
	public void ST_SimplifyPreserveTopology() {

		String wkt = "LINESTRING (250 250, 280 290, 300 230, 340 300, 360 260, 440 310, 470 360, 604 286)";
		Geom g = GeoFunctions.ST_GeomFromText(wkt);
		Geom result = GeoFunctions.ST_SimplifyPreserveTopology(g, Double.parseDouble("40"));
		assertEquals("LINESTRING (250 250, 280 290, 300 230, 470 360, 604 286)", result.g().toString());

	}

	@Test
	public void ST_Simplify() {

		String wkt = "LINESTRING (250 250, 280 290, 300 230, 340 300, 360 260, 440 310, 470 360, 604 286)";
		Geom g = GeoFunctions.ST_GeomFromText(wkt);
		Geom result = GeoFunctions.ST_Simplify(g, Double.parseDouble("40"));
		assertEquals("LINESTRING (250 250, 280 290, 300 230, 470 360, 604 286)", result.g().toString());
	}

}
