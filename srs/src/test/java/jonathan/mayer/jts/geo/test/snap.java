package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class snap {

	
	@Test
	public void ST_Snap() {
		
		Geom geom1 = GeoFunctions.ST_GeomFromText("MULTIPOLYGON(((26 125, 26 200, 126 200, 126 125, 26 125 ),( 51 150, 101 150, 76 175, 51 150 )), (( 151 100, 151 200, 176 175, 151 100 )))");
		Geom geom2 = GeoFunctions.ST_GeomFromText("LINESTRING (5 107, 54 84, 101 100)");
		double dist = GeoFunctions.ST_Distance(geom1,geom2)*1.01;
		Geom result = GeoFunctions.ST_Snap(geom1, geom1,dist);
		assertEquals("MULTIPOLYGON (((26 125, 51 150, 26 200, 76 175, 151 200, 126 200, 101 150, 126 125, 26 125), (51 150, 101 150, 76 175, 51 150)), ((151 100, 126 125, 126 200, 151 200, 176 175, 151 100)))", result.g().toString());
		
	}

	}
