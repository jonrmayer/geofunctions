package jonathan.mayer.srs;

import jonathan.mayer.srs.GeoFunctions.Geom;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
//		CoordinateTransformFactory mCtFactory = new CoordinateTransformFactory();
		Geom geo1 =GeoFunctions.ST_PolyFromText("POLYGON((743238 2967416,743238 2967450,743265 2967450,743265.625 2967416,743238 2967416))",2249);
//		Geom geo2 =GeoFunctions.ST_SetSRID(geo1, 2249);
		Geom geo3 =GeoFunctions.ST_Boundary(GeoFunctions.ST_GeomFromText("POINT(40 50)"));
//		Double d = ST_Distance(ST_GeomFromText("POINT(10 10)",4326),ST_GeomFromText("POINT(10 10)",4326))
////		Geom geo1 = GeoFunctions.ST_PolyFromText(
////				"POLYGON((743238 2967416,743238 2967450,743265 2967450,743265.625 2967416,743238 2967416))", 2249);
////		GeometryTransform.transform(geo1.g(), 27700);
		System.out.println(geo3.g().toString());
		// Geometry geometry = GeometryTransform.transform(geo1, 27700);
		// System.out.println(geometry.toString());
		// Geom geo2 = GeoFunctions.ST_PointFromText("POINT(1 1)", 4326);
		// Geom geo3 = GeoFunctions.ST_MPointFromText("MULTIPOINT(1 2 3)", 4326);
		// Geom result = GeoFunctions.ST_MakeLine(geo1,geo3);
		// Geom aaa =GeoFunctions.ST_GeomFromText("LINESTRING(100 150,50 60, 70 80, 160
		// 170)", 4326);
		// GeoFunctions.ST_Is3D(aaa);
		// System.out.println(GeoFunctions.ST_Union(aaa,GeoFunctions.ST_Buffer(result,
		// Double.parseDouble("10"))).g().toString());
	}

}	
