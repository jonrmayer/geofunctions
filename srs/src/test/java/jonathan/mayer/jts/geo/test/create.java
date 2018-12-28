package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class create {

	
	
	@Test
	public void ST_BoundingCircle() {

		// GeoFunctions.ST_BoundingCircle(geom)
		
		String result = "POLYGON ((1 0, 0.9903926402016152 -0.0975451610080641, 0.9619397662556434 -0.1913417161825449, 0.9157348061512727 -0.2777851165098011, 0.8535533905932737 -0.3535533905932737, 0.7777851165098011 -0.4157348061512726, 0.6913417161825449 -0.4619397662556434, 0.5975451610080642 -0.4903926402016152, 0.5 -0.5, 0.4024548389919359 -0.4903926402016152, 0.3086582838174551 -0.4619397662556434, 0.222214883490199 -0.4157348061512727, 0.1464466094067263 -0.3535533905932738, 0.0842651938487273 -0.2777851165098011, 0.0380602337443566 -0.1913417161825447, 0.0096073597983847 -0.0975451610080639, 0 0.0000000000000004, 0.0096073597983849 0.0975451610080646, 0.0380602337443569 0.1913417161825454, 0.0842651938487278 0.2777851165098017, 0.1464466094067268 0.3535533905932743, 0.2222148834901996 0.4157348061512731, 0.3086582838174561 0.4619397662556438, 0.402454838991937 0.4903926402016154, 0.5000000000000012 0.5, 0.5975451610080654 0.4903926402016149, 0.6913417161825463 0.4619397662556428, 0.7777851165098024 0.4157348061512717, 0.853553390593275 0.3535533905932726, 0.9157348061512736 0.2777851165097996, 0.9619397662556441 0.1913417161825431, 0.9903926402016157 0.0975451610080622, 1 0))";
		
		assertEquals(result, GeoFunctions.ST_BoundingCircle(GeoFunctions.ST_GeomFromText("LINESTRING (0 0, 1 0)")).g().toString());


	}
	
	@Test
	public void ST_BoundingCircleCenter() {

		// GeoFunctions.ST_BoundingCircle(geom)
		
		String result = "POINT (0.5 -0.0000000000000001)";
		Geom bc = GeoFunctions.ST_BoundingCircle(GeoFunctions.ST_GeomFromText("LINESTRING (0 0, 1 0)"));
		assertEquals(result, GeoFunctions.ST_BoundingCircleCenter(bc).g().toString());


	}
	
	@Test
	public void ST_MakeEnvelope() {
//		 GeoFunctions.ST_MakeEnvelope(xmin, ymin, xmax, ymax, srid)
		double xmin = Double.parseDouble("5");
		double ymin = Double.parseDouble("5");
		double xmax = Double.parseDouble("10");
		double ymax = Double.parseDouble("10");
		assertEquals("POLYGON ((5 5, 10 5, 10 10, 5 10, 5 5))", GeoFunctions.ST_MakeEnvelope(xmin, ymin, xmax, ymax, 4326).g().toString());
		 
		
	}
	
	
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
