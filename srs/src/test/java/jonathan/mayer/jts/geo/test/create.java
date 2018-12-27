package jonathan.mayer.SpatialReference;

import jonathan.mayer.srs.GeoFunctions;
import jonathan.mayer.srs.GeoFunctions.Geom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class create {


	
	@Test
	public void ST_MakeLine() {
		// GeoFunctions.ST_MakeLine(geoms)
		assertEquals("LINESTRING (0 0, 1 0)", GeoFunctions
				.ST_MakeLine(GeoFunctions.ST_PointFromText("POINT(0 0)"), GeoFunctions.ST_PointFromText("POINT(1 0)"))
				.g().toString());
		assertEquals("LINESTRING (0 0, 1 0, 50 50)",
				GeoFunctions.ST_MakeLine(GeoFunctions.ST_PointFromText("POINT(0 0)"),
						GeoFunctions.ST_PointFromText("POINT(1 0)"), GeoFunctions.ST_PointFromText("POINT(50 50)")).g()
						.toString());

	}

	@Test
	public void ST_MakePoint() {

		// GeoFunctions.ST_MakePoint(x, y, z)
		// GeoFunctions.ST_MakePoint(x, y)

		assertEquals("POINT (1 0)", GeoFunctions.ST_MakePoint(new BigDecimal(1), new BigDecimal(0)).g().toString());
//		assertEquals("POINT (1 0 0)", GeoFunctions.ST_AsWKT(GeoFunctions.ST_MakePoint(new BigDecimal(1), new BigDecimal(0), new BigDecimal(0))));
		//

	}
	
	
	
	
	
	
}
