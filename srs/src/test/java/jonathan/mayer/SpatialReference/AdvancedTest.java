package jonathan.mayer.SpatialReference;

import jonathan.mayer.srs.GeoFunctions;
import jonathan.mayer.srs.GeoFunctions.Geom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class AdvancedTest {

	@Test
	public void ST_AsText() {
		assertEquals("POINT (40 50)", GeoFunctions.ST_AsText(GeoFunctions.ST_GeomFromText("POINT(40 50)")));
		assertNotEquals("POINT (40 51)", GeoFunctions.ST_AsText(GeoFunctions.ST_GeomFromText("POINT(40 50)")));
	}

	@Test
	public void ST_AsWKT() {
		assertEquals("POINT (40 50)", GeoFunctions.ST_AsWKT(GeoFunctions.ST_GeomFromText("POINT(40 50)")));
		assertNotEquals("POINT (40 51)", GeoFunctions.ST_AsWKT(GeoFunctions.ST_GeomFromText("POINT(40 50)")));
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

	@Test
	public void ST_Contains() {
		assertEquals(false, GeoFunctions.ST_Contains(GeoFunctions.ST_GeomFromText("POINT(0 0)"),
				GeoFunctions.ST_GeomFromText("POINT(1 2)")));
		assertEquals(true, GeoFunctions.ST_Contains(GeoFunctions.ST_GeomFromText("POINT(0 0)"),
				GeoFunctions.ST_GeomFromText("POINT(0 0)")));

	}

	@Test
	public void ST_ContainsProperly() {
		// GeoFunctions.ST_ContainsProperly(geom1, geom2)
		// assertEquals(false,GeoFunctions.ST_Contains(GeoFunctions.ST_GeomFromText("POINT(0
		// 0)"),GeoFunctions.ST_GeomFromText("POINT(1 2)")));
		// assertEquals(true,GeoFunctions.ST_Contains(GeoFunctions.ST_GeomFromText("POINT(0
		// 0)"),GeoFunctions.ST_GeomFromText("POINT(0 0)")));

	}

	@Test
	public void ST_Crosses() {
		// GeoFunctions.ST_Crosses(geom1, geom2)
		assertEquals(true, GeoFunctions.ST_Crosses(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));
		assertEquals(false, GeoFunctions.ST_Crosses(GeoFunctions.ST_GeomFromText("LINESTRING(176 149, 176 151)"),
				GeoFunctions.ST_GeomFromText("POLYGON((175 150, 20 40, 50 60, 125 100, 175 150))")));

	}

	@Test
	public void ST_Disjoint() {
		// GeoFunctions.ST_Disjoint(geom1, geom2)
		assertEquals(false, GeoFunctions.ST_Disjoint(GeoFunctions.ST_GeomFromText("POINT(0 0)"),
				GeoFunctions.ST_GeomFromText("LINESTRING ( 0 0, 0 2 )")));
		assertEquals(false, GeoFunctions.ST_Disjoint(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));

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
	public void ST_DWithin() {
		//// GeoFunctions.ST_DWithin(geom1, geom2, distance)
		// assertEquals(false,GeoFunctions.ST_Disjoint(GeoFunctions.ST_GeomFromText("POINT(0
		//// 0)"),GeoFunctions.ST_GeomFromText("LINESTRING ( 0 0, 0 2 )")));
		// assertEquals(false,GeoFunctions.ST_Disjoint(GeoFunctions.ST_GeomFromText("LINESTRING(1
		//// 3, 5 3)"),GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));

	}

	@Test
	public void ST_Envelope() {
		// GeoFunctions.ST_Envelope(geom)
		assertEquals("POINT (1 3)",
				GeoFunctions.ST_Envelope(GeoFunctions.ST_GeomFromText("POINT(1 3)")).g().toString());
		assertEquals("POLYGON ((0 0, 0 3, 1 3, 1 0, 0 0))",
				GeoFunctions.ST_Envelope(GeoFunctions.ST_GeomFromText("LINESTRING(0 0, 1 3)")).g().toString());
		// assertEquals("POLYGON ((0 0, 0 1, 1.00000011920929 1, 1.00000011920929 0, 0
		// 0))",GeoFunctions.ST_Envelope(GeoFunctions.ST_GeomFromText("POLYGON((0 0, 0
		// 1, 1.0000001 1, 1.0000001 0, 0 0))")).g().toString());
	}

	@Test
	public void ST_EnvelopesIntersect() {
		// GeoFunctions.ST_EnvelopesIntersect(geom1, geom2)
		assertEquals(true, GeoFunctions.ST_EnvelopesIntersect(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));
		// assertEquals("POLYGON ((0 0, 0 3, 1 3, 1 0, 0
		// 0))",GeoFunctions.ST_Envelope(GeoFunctions.ST_GeomFromText("LINESTRING(0 0, 1
		// 3)")).g().toString());
		// assertEquals("POLYGON ((0 0, 0 1, 1.00000011920929 1, 1.00000011920929 0, 0
		// 0))",GeoFunctions.ST_Envelope(GeoFunctions.ST_GeomFromText("POLYGON((0 0, 0
		// 1, 1.0000001 1, 1.0000001 0, 0 0))")).g().toString());
	}

	@Test
	public void ST_Equals() {
		// GeoFunctions.ST_Equals(geom1, geom2)
		assertEquals(false, GeoFunctions.ST_Equals(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));
		assertEquals(true, GeoFunctions.ST_Equals(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)")));
		// assertEquals("POLYGON ((0 0, 0 3, 1 3, 1 0, 0
		// 0))",GeoFunctions.ST_Envelope(GeoFunctions.ST_GeomFromText("LINESTRING(0 0, 1
		// 3)")).g().toString());
		// assertEquals("POLYGON ((0 0, 0 1, 1.00000011920929 1, 1.00000011920929 0, 0
		// 0))",GeoFunctions.ST_Envelope(GeoFunctions.ST_GeomFromText("POLYGON((0 0, 0
		// 1, 1.0000001 1, 1.0000001 0, 0 0))")).g().toString());
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
	public void ST_GeomFromText() {
		assertEquals("POINT (40 50)", GeoFunctions.ST_GeomFromText("POINT(40 50)").g().toString());
		assertEquals("POINT (40 50)", GeoFunctions.ST_GeomFromText("POINT(40 50)", 4326).g().toString());

		assertNotEquals("POINT (40 51)", GeoFunctions.ST_GeomFromText("POINT(40 50)").g().toString());
		assertNotEquals("POINT (40 51)", GeoFunctions.ST_GeomFromText("POINT(40 50)", 4326).g().toString());

	}

	@Test
	public void ST_Intersects() {
		// GeoFunctions.ST_Intersects(geom1, geom2)
		// assertEquals(3,GeoFunctions.ST_GeometryTypeCode(GeoFunctions.ST_GeomFromText("LINESTRING(1
		// 3, 5 3)")));
		assertEquals(true, GeoFunctions.ST_Intersects(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)")));
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
	public void ST_LineFromText() {
		// GeoFunctions.ST_LineFromText(s)

		assertEquals("LINESTRING (1 2, 3 4)", GeoFunctions.ST_LineFromText("LINESTRING(1 2, 3 4)").g().toString());
		assertEquals("LINESTRING (1 2, 3 4)",
				GeoFunctions.ST_LineFromText("LINESTRING(1 2, 3 4)", 4326).g().toString());

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
	
	@Test
	public void ST_MLineFromText() {

//		GeoFunctions.ST_MLineFromText(s)

		assertEquals("MULTILINESTRING ((1 2, 3 4), (4 5, 6 7))", GeoFunctions.ST_MLineFromText("MULTILINESTRING((1 2, 3 4), (4 5, 6 7))").g().toString());
		assertEquals("MULTILINESTRING ((1 2, 3 4), (4 5, 6 7))", GeoFunctions.ST_MLineFromText("MULTILINESTRING((1 2, 3 4), (4 5, 6 7))",4326).g().toString());


	}
	@Test
	public void ST_MPointFromText() {

//		GeoFunctions.ST_MPointFromText(s)

		assertEquals("MULTIPOINT ((1 2), (3 4))", GeoFunctions.ST_MPointFromText("MULTIPOINT(1 2, 3 4)").g().toString());
		assertEquals("MULTIPOINT ((1 2), (3 4))", GeoFunctions.ST_MPointFromText("MULTIPOINT(1 2, 3 4)",4326).g().toString());


	}
	
	@Test
	public void ST_MPolyFromText() {

//		GeoFunctions.ST_MPolyFromText(s)

		assertEquals("MULTIPOLYGON (((0 0, 20 0, 20 20, 0 20, 0 0), (5 5, 5 7, 7 7, 7 5, 5 5)))", GeoFunctions.ST_MPolyFromText("MULTIPOLYGON(((0 0 1,20 0 1,20 20 1,0 20 1,0 0 1),(5 5 3,5 7 3,7 7 3,7 5 3,5 5 3)))").g().toString());
		assertEquals("MULTIPOLYGON (((0 0, 20 0, 20 20, 0 20, 0 0), (5 5, 5 7, 7 7, 7 5, 5 5)))", GeoFunctions.ST_MPolyFromText("MULTIPOLYGON(((0 0 1,20 0 1,20 20 1,0 20 1,0 0 1),(5 5 3,5 7 3,7 7 3,7 5 3,5 5 3)))",4326).g().toString());


	}
	
	@Test
	public void ST_Overlaps() {

//		GeoFunctions.ST_Overlaps(geom1, geom2)
		assertEquals(true, GeoFunctions.ST_Overlaps(GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));

		assertEquals(false, GeoFunctions.ST_Overlaps(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
				GeoFunctions.ST_GeomFromText("LINESTRING(50 50, 100 100, 150 150)")));

	}
	
	
	@Test
	public void ST_Point() {

//		GeoFunctions.ST_Point(x, y)
		
		assertEquals("POINT (1 0)", GeoFunctions.ST_MakePoint(new BigDecimal(1), new BigDecimal(0)).g().toString());
		
	}

	


	@Test
	public void ST_PointFromText() {

		assertEquals("POINT (40 50)", GeoFunctions.ST_PointFromText("POINT(40 50)").g().toString());
		assertEquals("POINT (40 50)", GeoFunctions.ST_PointFromText("POINT(40 50)", 4326).g().toString());

		assertNotEquals("POINT (40 51)", GeoFunctions.ST_PointFromText("POINT(40 50)").g().toString());
		assertNotEquals("POINT (40 51)", GeoFunctions.ST_PointFromText("POINT(40 50)", 4326).g().toString());

	}
	
	@Test
	public void ST_PolyFromText() {

//		GeoFunctions.ST_PolyFromText(s)
		
		assertEquals("POLYGON ((10 130, 50 190, 110 190, 140 150, 150 80, 100 10, 20 40, 10 130), (70 40, 100 50, 120 80, 80 110, 50 90, 70 40))", GeoFunctions.ST_PolyFromText("POLYGON(( 10 130, 50 190, 110 190, 140 150, 150 80, 100 10, 20 40, 10 130 ),( 70 40, 100 50, 120 80, 80 110, 50 90, 70 40 ))").g().toString());
		assertEquals("POLYGON ((10 130, 50 190, 110 190, 140 150, 150 80, 100 10, 20 40, 10 130), (70 40, 100 50, 120 80, 80 110, 50 90, 70 40))", GeoFunctions.ST_PolyFromText("POLYGON(( 10 130, 50 190, 110 190, 140 150, 150 80, 100 10, 20 40, 10 130 ),( 70 40, 100 50, 120 80, 80 110, 50 90, 70 40 ))",4326).g().toString());
	}
	
	@Test
	public void ST_SetSRID() {

//		GeoFunctions.ST_SetSRID(geom, srid)
		
		assertEquals(4326,GeoFunctions.ST_SetSRID(GeoFunctions.ST_PointFromText("POINT(40 50)"), 4326).sr().GetSRID());
		
	}
	@Test
	public void ST_Touches() {

//		GeoFunctions.ST_Touches(geom1, geom2)
		
//		assertEquals(false, GeoFunctions.ST_Touches(GeoFunctions.ST_GeomFromText("LINESTRING(1 3, 5 3)"),
//				GeoFunctions.ST_GeomFromText("LINESTRING(1 1, 5 2, 2 5)")));
		assertEquals(true, GeoFunctions.ST_Touches(GeoFunctions.ST_GeomFromText("LINESTRING(0 0, 1 1, 0 2)"),
				GeoFunctions.ST_GeomFromText("POINT(0 2)")));
		
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
	
	@Test
	public void ST_Union() {

//		GeoFunctions.ST_Union(geoms)
		
		assertEquals("MULTIPOINT ((0 0), (1 0))", GeoFunctions
				.ST_Union(GeoFunctions.ST_PointFromText("POINT(0 0)"), GeoFunctions.ST_PointFromText("POINT(1 0)"))
				.g().toString());
		
	}
	
	@Test
	public void ST_Within() {

//		GeoFunctions.ST_Within(geom1, geom2)
		
//		assertEquals(true,GeoFunctions.ST_SetSRID(GeoFunctions.ST_PointFromText("POINT(40 50)"), 4326).sr().GetSRID());
		
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
