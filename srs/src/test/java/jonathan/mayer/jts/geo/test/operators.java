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
	
	

}
