package jonathan.mayer.SpatialReference;

import jonathan.mayer.srs.GeoFunctions;
import jonathan.mayer.srs.GeoFunctions.Geom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class buffer {

	
	@Test
	public void ST_Buffer() {
		// assertEquals("GEOMETRYCOLLECTION EMPTY",
		// GeoFunctions.ST_Buffer(GeoFunctions.ST_GeomFromText("POINT(40
		// 50)")).g().toString());
		// assertEquals("MULTILINESTRING ((10 130, 50 190, 110 190, 140 150, 150 80, 100
		// 10, 20 40, 10 130), (70 40, 100 50, 120 80, 80 110, 50 90, 70 40))",
		// GeoFunctions.ST_Boundary(GeoFunctions.ST_GeomFromText("POLYGON(( 10 130, 50
		// 190, 110 190, 140 150, 150 80, 100 10, 20 40, 10 130 ),( 70 40, 100 50, 120
		// 80, 80 110, 50 90, 70 40 ))")).g().toString());
		// assertNotEquals("POINT (40 51)",
		// GeoFunctions.ST_AsWKT(GeoFunctions.ST_GeomFromText("POINT(40 50)")));
	}

	}
