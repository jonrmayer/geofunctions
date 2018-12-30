package jonathan.mayer.jts.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import jonathan.mayer.jts.geo.GeoFunctions;
import jonathan.mayer.jts.geo.GeoFunctions.Geom;

public class properties {

	@Test
	public void ST_Area() {
		Geom gb = GeoFunctions.ST_Buffer(GeoFunctions.ST_GeomFromText("POINT(50 50)"), Double.parseDouble("10"));
		assertEquals("312.14451522580526", Double.toString(GeoFunctions.ST_Area(gb)));
	}

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

	@Test
	public void ST_CoordDim() {

	}

	@Test
	public void ST_InteriorRingN() {
		// GeoFunctions.ST_Perimeter(geom1)
		String wkt = "POLYGON((0 0, 10 0, 10 6, 0 6, 0 0),(1 1, 2 1, 2 5, 1 5, 1 1), (8 5, 8 4, 9 4, 9 5, 8 5))";
		Geom g = GeoFunctions.ST_GeomFromText(wkt);

		Geom result = GeoFunctions.ST_InteriorRingN(g, 1);
		// Geom gt = GeoFunctions.ST_Transform(g, 2249);
		assertEquals("LINEARRING (1 1, 2 1, 2 5, 1 5, 1 1)", result.g().toString());

	}

	@Test
	public void ST_ExteriorRing() {
		// GeoFunctions.ST_Perimeter(geom1)
		String wkt = "POLYGON((0 0 1, 1 1 1, 1 2 1, 1 1 1, 0 0 1))";
		Geom g = GeoFunctions.ST_GeomFromText(wkt);
		// Geom gt = GeoFunctions.ST_Transform(g, 2249);
		assertEquals("LINEARRING (0 0, 1 1, 1 2, 1 1, 0 0)", GeoFunctions.ST_ExteriorRing(g).g().toString());

	}

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
	public void ST_Length() {
		// GeoFunctions.ST_Length(geom1)

		String wkt = "LINESTRING(743238 2967416,743238 2967450,743265 2967450,743265.625 2967416,743238 2967416)";
		Geom g = GeoFunctions.ST_GeomFromText(wkt, 2249);
		// Geom gt = GeoFunctions.ST_Transform(g, 2249);
		assertEquals("122.63074400009504", Double.toString(GeoFunctions.ST_Length(g)));

	}

	@Test
	public void ST_Perimeter() {
		// GeoFunctions.ST_Perimeter(geom1)
		String wkt = "POLYGON((743238 2967416,743238 2967450,743265 2967450,743265.625 2967416,743238 2967416))";
		Geom g = GeoFunctions.ST_GeomFromText(wkt, 2249);
		// Geom gt = GeoFunctions.ST_Transform(g, 2249);
		assertEquals("122.63074400009504", Double.toString(GeoFunctions.ST_Perimeter(g)));

	}

	@Test
	public void ST_MinimumDiameter() {
		// GeoFunctions.ST_Perimeter(geom1)
		String wkt = "POLYGON((743238 2967416,743238 2967450,743265 2967450,743265.625 2967416,743238 2967416))";
		Geom g = GeoFunctions.ST_GeomFromText(wkt, 2249);
		// Geom gt = GeoFunctions.ST_Transform(g, 2249);
		assertEquals("LINESTRING (743265.6156683647 2967416.5076409625, 743238 2967416)",
				GeoFunctions.ST_MinimumDiameter(g).g().toString());

	}

	@Test
	public void ST_MinimumRectangle() {
		// GeoFunctions.ST_Perimeter(geom1)
		String wkt = "POLYGON((743238 2967416,743238 2967450,743265 2967450,743265.625 2967416,743238 2967416))";
		Geom g = GeoFunctions.ST_GeomFromText(wkt, 2249);
		// Geom gt = GeoFunctions.ST_Transform(g, 2249);
		assertEquals(
				"POLYGON ((743264.9910239311 2967450.4960363717, 743237.3753555663 2967449.9883954134, 743238.0094760772 2967415.4922395404, 743265.6251444418 2967415.9998804983, 743264.9910239311 2967450.4960363717))",
				GeoFunctions.ST_MinimumRectangle(g).g().toString());

	}

	@Test
	public void ST_GeometryType() {
		// GeoFunctions.ST_GeometryType(geom)
		assertEquals("LINESTRING", GeoFunctions.ST_GeometryType(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)")));

	}

	@Test
	public void ST_GeometryTypeCode() {
		// GeoFunctions.ST_GeometryTypeCode(geom)
		assertEquals(2, GeoFunctions.ST_GeometryTypeCode(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)")));

	}
	
	@Test
	public void ST_GeometryN() {
		String wkt = "MULTIPOINT((0 0), (1 6), (2 2), (1 2))";
		Geom g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		
		Geom result = GeoFunctions.ST_GeometryN(g, 1);
		String strresult = result.g().toString();
		assertEquals("POINT (1 6)", strresult);
	}
	

	@Test
	public void ST_Is3D() {
		// GeoFunctions.ST_Is3D(geom)
		//
		assertEquals(true, GeoFunctions.ST_Is3D(GeoFunctions.ST_GeomFromText("POINT(0 0 0)")));
		assertEquals(false, GeoFunctions.ST_Is3D(GeoFunctions.ST_GeomFromText("POINT(0 0)")));

	}

	@Test
	public void ST_IsClosed() {
		String wkt = "MULTILINESTRING((0 2, 3 2, 3 6, 0 6, 0 2),(5 0, 7 0, 7 1, 5 1, 5 0))";
		Geom g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		boolean result = GeoFunctions.ST_IsClosed(g);
		assertEquals(true, result);

		wkt = "MULTILINESTRING((0 2, 3 2, 3 6, 0 6, 0 1),(5 0, 7 0, 7 1, 5 1, 5 0))";
		g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		result = GeoFunctions.ST_IsClosed(g);
		assertEquals(false, result);
	}

	@Test
	public void ST_IsRing() {
		String wkt = "LINESTRING(2 1, 1 3, 6 6, 2 1)";
		Geom g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		boolean result = GeoFunctions.ST_IsRing(g);
		assertEquals(true, result);

		wkt = "LINESTRING(2 1, 1 3, 6 6)";
		g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		result = GeoFunctions.ST_IsRing(g);
		assertEquals(false, result);

	}

	@Test
	public void ST_IsRectangle() {
		String wkt = "LINESTRING(2 1, 1 3, 6 6, 2 1)";
		Geom g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		boolean result = GeoFunctions.ST_IsRectangle(g);
		assertEquals(false, result);

		wkt = "POLYGON((0 0, 10 0, 10 5, 0 5, 0 0))";
		g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		result = GeoFunctions.ST_IsRectangle(g);
		assertEquals(true, result);

	}

	@Test
	public void ST_IsEmpty() {
		String wkt = "LINESTRING(2 1, 1 3, 6 6, 2 1)";
		Geom g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		boolean result = GeoFunctions.ST_IsEmpty(g);
		assertEquals(false, result);

	}

	@Test
	public void ST_IsSimple() {
		// GeoFunctions.ST_Is3D(geom)
		//
		// assertEquals(true,
		// GeoFunctions.ST_IsSimple(GeoFunctions.ST_GeomFromText("POLYGON((1 2, 3 4, 5
		// 6, 1 2))")));
		// assertEquals(false,
		// GeoFunctions.ST_IsSimple(GeoFunctions.ST_GeomFromText("LINESTRING(1 1,2 2,2
		// 3.5,1 3,1 2,2 1)")));

	}

	@Test
	public void ST_IsValid() {
		// GeoFunctions.ST_Is3D(geom)
		//
		assertEquals(true, GeoFunctions.ST_IsValid(GeoFunctions.ST_GeomFromText("LINESTRING(0 0, 1 1)")));
		assertEquals(false,
				GeoFunctions.ST_IsValid(GeoFunctions.ST_GeomFromText("POLYGON((0 0, 1 1, 1 2, 1 1, 0 0))")));

	}

	@Test
	public void ST_IsValidDetail() {
		String wkt = "LINESTRING(0 0, 1 1)";
		Geom g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		Object[] result = GeoFunctions.ST_IsValidDetail(g);
		assertEquals(true, result[0]);
		assertEquals("Valid Geometry", result[1]);

		wkt = "POLYGON((0 0 1, 10 0 1, 10 5 1, 6 -2 1, 0 0 1))";
		g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		result = GeoFunctions.ST_IsValidDetail(g);
		
		assertEquals(false, result[0]);
		assertEquals("Self-intersection", result[1]);
		assertEquals("POINT (7.142857142857143 0)", result[2].toString());

	}

	@Test
	public void ST_IsValidReason() {
		String wkt = "LINESTRING(0 0, 1 1)";
		Geom g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		String result = GeoFunctions.ST_IsValidReason(g);
		assertEquals("Valid Geometry", result);


		wkt = "POLYGON((0 0 1, 10 0 1, 10 5 1, 6 -2 1, 0 0 1))";
		g = GeoFunctions.ST_GeomFromText(wkt, 4326);
		result = GeoFunctions.ST_IsValidReason(g);
		assertEquals("Self-intersection at or near point (7.142857142857143, 0.0, NaN)", result);

	}

	@Test
	public void ST_NPoints() {
		// GeoFunctions.ST_Is3D(geom)
		Geom g = GeoFunctions.ST_GeomFromText("POINT(0 0 0)");
		assertEquals(1, GeoFunctions.ST_NPoints(g));

	}

	@Test
	public void ST_NumPoints() {
		// GeoFunctions.ST_Is3D(geom)
		Geom g = GeoFunctions.ST_GeomFromText("POINT(0 0 0)");
		assertEquals(1, GeoFunctions.ST_NumPoints(g));

	}

	@Test
	public void ST_NumGeometries() {
		// GeoFunctions.ST_Is3D(geom)
		Geom g = GeoFunctions.ST_GeomFromText("MULTIPOINT((0 0),(1 1))");
		assertEquals(2, GeoFunctions.ST_NumGeometries(g));

	}

	@Test
	public void ST_NumInteriorRings() {
		// GeoFunctions.ST_Is3D(geom)
		String wkt = "POLYGON((1 2, 3 4, 5 6, 1 2))";

		Geom g = GeoFunctions.ST_GeomFromText(wkt);
		assertEquals(0, GeoFunctions.ST_NumInteriorRings(g));
		wkt = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)),((20 35, 45 20, 30 5, 10 10, 10 30, 20 35),(30 20, 20 25, 20 15, 30 20)))";

		g = GeoFunctions.ST_GeomFromText(wkt);
		assertEquals(1, GeoFunctions.ST_NumInteriorRings(g));

	}

	@Test
	public void ST_NumInteriorRing() {
		// GeoFunctions.ST_Is3D(geom)
		String wkt = "POLYGON((1 2, 3 4, 5 6, 1 2))";

		Geom g = GeoFunctions.ST_GeomFromText(wkt);
		assertEquals(0, GeoFunctions.ST_NumInteriorRing(g));
		wkt = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)),((20 35, 45 20, 30 5, 10 10, 10 30, 20 35),(30 20, 20 25, 20 15, 30 20)))";

		g = GeoFunctions.ST_GeomFromText(wkt);
		assertEquals(1, GeoFunctions.ST_NumInteriorRing(g));

	}

	@Test
	public void ST_NRings() {
		// GeoFunctions.ST_Is3D(geom)
		String wkt = "POLYGON((1 2, 3 4, 5 6, 1 2))";

		Geom g = GeoFunctions.ST_GeomFromText(wkt);
		assertEquals(1, GeoFunctions.ST_NRings(g));
		wkt = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)),((20 35, 45 20, 30 5, 10 10, 10 30, 20 35),(30 20, 20 25, 20 15, 30 20)))";

		g = GeoFunctions.ST_GeomFromText(wkt);
		assertEquals(3, GeoFunctions.ST_NRings(g));

	}

	@Test
	public void ST_StartPoint() {

		// GeoFunctions.ST_StartPoint(geom)

		Geom g = GeoFunctions.ST_LineFromText("LINESTRING(-72.1260 42.45, -72.123 42.1546)");

		assertEquals("POINT (-72.126 42.45)", GeoFunctions.ST_StartPoint(g).g().toString());

	}

	@Test
	public void ST_EndPoint() {

		// GeoFunctions.ST_StartPoint(geom)

		Geom g = GeoFunctions.ST_LineFromText("LINESTRING(-72.1260 42.45, -72.123 42.1546)");

		assertEquals("POINT (-72.123 42.1546)", GeoFunctions.ST_EndPoint(g).g().toString());

	}

	@Test
	public void ST_X() {

		// GeoFunctions.ST_X(geom)
		Geom g = GeoFunctions.ST_PointFromText("POINT(40 50)");
		assertEquals("40.0", Double.toString(GeoFunctions.ST_X(g)));

	}

	@Test
	public void ST_XMax() {

		// GeoFunctions.ST_X(geom)
		Geom g = GeoFunctions.ST_PointFromText("LINESTRING(1 3 4, 5 6 7)");
		assertEquals("5.0", Double.toString(GeoFunctions.ST_XMax(g)));

	}

	@Test
	public void ST_XMin() {

		// GeoFunctions.ST_X(geom)
		Geom g = GeoFunctions.ST_PointFromText("LINESTRING(1 3 4, 5 6 7)");
		assertEquals("1.0", Double.toString(GeoFunctions.ST_XMin(g)));

	}

	@Test
	public void ST_YMax() {

		// GeoFunctions.ST_X(geom)
		Geom g = GeoFunctions.ST_PointFromText("LINESTRING(1 3 4, 5 6 7)");
		assertEquals("6.0", Double.toString(GeoFunctions.ST_YMax(g)));

	}

	@Test
	public void ST_YMin() {

		// GeoFunctions.ST_X(geom)
		Geom g = GeoFunctions.ST_PointFromText("LINESTRING(1 3 4, 5 6 7)");
		assertEquals("3.0", Double.toString(GeoFunctions.ST_YMin(g)));

	}

	@Test
	public void ST_Y() {

		// GeoFunctions.ST_Within(geom1, geom2)

		Geom g = GeoFunctions.ST_PointFromText("POINT(40 50)");
		assertEquals("50.0", Double.toString(GeoFunctions.ST_Y(g)));

	}

	@Test
	public void ST_Z() {

		// GeoFunctions.ST_Z(geom)

		Geom g = GeoFunctions.ST_PointFromText("POINT(40 50 0)");
		assertEquals("0.0", Double.toString(GeoFunctions.ST_Z(g)));

	}

}
