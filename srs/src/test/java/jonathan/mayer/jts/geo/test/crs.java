package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class crs {


	
	@Test
	public void ST_SetSRID() {

//		GeoFunctions.ST_SetSRID(geom, srid)
		
		assertEquals(4326,GeoFunctions.ST_SetSRID(GeoFunctions.ST_PointFromText("POINT(40 50)"), 4326).sr().GetSRID());
		
	}

	
	@Test
	public void ST_Transform() {

//		GeoFunctions.ST_Transform(geom, srid)
		Geom g =GeoFunctions.ST_GeomFromText("POINT(50 50)",4326);
		assertEquals("POINT (3963652.4834313598 1425830.5980279834)",GeoFunctions.ST_Transform(g,27700).g().toString());
	
		// 2. Variant
		
		String projection27700 = "+proj=tmerc +lat_0=49 +lon_0=-2 +k=0.9996012717 "
			    + "+x_0=400000 +y_0=-100000 +ellps=airy +datum=OSGB36 +units=m +no_defs";
		assertEquals("POINT (3963652.4834313598 1425830.5980279834)",GeoFunctions.ST_Transform(g,projection27700).g().toString());
	}
	
	
}
