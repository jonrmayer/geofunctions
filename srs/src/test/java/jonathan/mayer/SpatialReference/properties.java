package jonathan.mayer.SpatialReference;

import jonathan.mayer.srs.GeoFunctions;
import jonathan.mayer.srs.GeoFunctions.Geom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class properties {

	
	@Test
	public void ST_Boundary() {
		assertEquals("GEOMETRYCOLLECTION EMPTY",
				GeoFunctions.ST_Boundary(GeoFunctions.ST_GeomFromText("POINT(40 50)")).g().toString());
		assertEquals(
				"MULTILINESTRING ((10 130, 50 190, 110 190, 140 150, 150 80, 100 10, 20 40, 10 130), (70 40, 100 50, 120 80, 80 110, 50 90, 70 40))",
				GeoFunctions.ST_Boundary(GeoFunctions.ST_GeomFromText(
						"POLYGON(( 10 130, 50 190, 110 190, 140 150, 150 80, 100 10, 20 40, 10 130 ),( 70 40, 100 50, 120 80, 80 110, 50 90, 70 40 ))"))
						.g().toString());
		// assertNotEquals("POINT (40 51)",
		// GeoFunctions.ST_AsWKT(GeoFunctions.ST_GeomFromText("POINT(40 50)")));
	}

	

	@SuppressWarnings("deprecation")
	@Test
	public void ST_Distance() {
		// GeoFunctions.ST_Distance(geom1, geom2)
		assertEquals("0.0015056772638228177",
				Double.toString(GeoFunctions.ST_Distance(GeoFunctions.ST_GeomFromText("POINT(-72.1235 42.3521)"),
						GeoFunctions.ST_GeomFromText("LINESTRING(-72.1260 42.45, -72.123 42.1546)"))));
		// assertEquals(false,GeoFunctions.ST_Distance(GeoFunctions.ST_GeomFromText("LINESTRING(1
		// 3, 5 3)"),GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));

	}

	

	
	@Test
	public void ST_GeometryType() {
		// GeoFunctions.ST_GeometryType(geom)
		// assertEquals("LINESTRING",GeoFunctions.ST_GeometryType(GeoFunctions.ST_GeomFromText("LINESTRING(1
		// 3, 5 3)")));
		// assertEquals(true,GeoFunctions.ST_Equals(GeoFunctions.ST_GeomFromText("LINESTRING(1
		// 3, 5 3)"),GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)")));
		// assertEquals("POLYGON ((0 0, 0 3, 1 3, 1 0, 0
		// 0))",GeoFunctions.ST_Envelope(GeoFunctions.ST_GeomFromText("LINESTRING(0 0, 1
		// 3)")).g().toString());
		// assertEquals("POLYGON ((0 0, 0 1, 1.00000011920929 1, 1.00000011920929 0, 0
		// 0))",GeoFunctions.ST_Envelope(GeoFunctions.ST_GeomFromText("POLYGON((0 0, 0
		// 1, 1.0000001 1, 1.0000001 0, 0 0))")).g().toString());
	}

	@Test
	public void ST_GeometryTypeCode() {
		// GeoFunctions.ST_GeometryTypeCode(geom)
		// assertEquals(3,GeoFunctions.ST_GeometryTypeCode(GeoFunctions.ST_GeomFromText("LINESTRING(1
		// 3, 5 3)")));
		// assertEquals(true,GeoFunctions.ST_Equals(GeoFunctions.ST_GeomFromText("LINESTRING(1
		// 3, 5 3)"),GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)")));
		// assertEquals("POLYGON ((0 0, 0 3, 1 3, 1 0, 0
		// 0))",GeoFunctions.ST_Envelope(GeoFunctions.ST_GeomFromText("LINESTRING(0 0, 1
		// 3)")).g().toString());
		// assertEquals("POLYGON ((0 0, 0 1, 1.00000011920929 1, 1.00000011920929 0, 0
		// 0))",GeoFunctions.ST_Envelope(GeoFunctions.ST_GeomFromText("POLYGON((0 0, 0
		// 1, 1.0000001 1, 1.0000001 0, 0 0))")).g().toString());
	}

	
	@Test
	public void ST_Is3D() {
		// GeoFunctions.ST_Is3D(geom)
		//
		assertEquals(true, GeoFunctions.ST_Is3D(GeoFunctions.ST_GeomFromText("POINT(0 0 0)")));
		assertEquals(false, GeoFunctions.ST_Is3D(GeoFunctions.ST_GeomFromText("POINT(0 0)")));

	}

	
	@Test
	public void ST_X() {

//		GeoFunctions.ST_X(geom)
		Geom g = GeoFunctions.ST_PointFromText("POINT(40 50)");
//		assertEquals(Double.toString("40")),GeoFunctions.ST_X(g).toString();
		
	}
	@Test
	public void ST_Y() {

//		GeoFunctions.ST_Within(geom1, geom2)
		
		Geom g = GeoFunctions.ST_PointFromText("POINT(40 50)");
//		assertEquals(Double.parseDouble("50"),GeoFunctions.ST_Y(g).doubleValue());
		
	}
	@Test
	public void ST_Z() {

//		GeoFunctions.ST_Z(geom)
		
		Geom g = GeoFunctions.ST_PointFromText("POINT(40 50 0)");
//		assertEquals(Double.parseDouble("0"),GeoFunctions.ST_Z(g).doubleValue());
		
	}

}
