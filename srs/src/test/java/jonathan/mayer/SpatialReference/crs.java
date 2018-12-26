package jonathan.mayer.SpatialReference;

import jonathan.mayer.srs.GeoFunctions;
import jonathan.mayer.srs.GeoFunctions.Geom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class crs {


	
	@Test
	public void ST_SetSRID() {

//		GeoFunctions.ST_SetSRID(geom, srid)
		
		assertEquals(4326,GeoFunctions.ST_SetSRID(GeoFunctions.ST_PointFromText("POINT(40 50)"), 4326).sr().GetSRID());
		
	}

	
//	@Test
//	public void ST_Transform() {
//
////		GeoFunctions.ST_Transform(geom, srid)
//		
//		assertEquals("",GeoFunctions.ST_Transform(
//				GeoFunctions.ST_PointFromText("POINT(40 50)"), 4326),27700))));
//		
//	}
	
	
}
