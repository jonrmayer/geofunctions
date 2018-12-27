package jonathan.mayer.srs;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import jonathan.mayer.srs.GeoFunctions.Geom;
import jonathan.mayer.srs.GeoFunctions.GeometryType;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
//		CoordinateTransformFactory mCtFactory = new CoordinateTransformFactory();
		Geom g = GeoFunctions.ST_GeomFromText("POINT(50 50)");
		Geom gb = GeoFunctions.ST_Buffer(g, Double.parseDouble("10"));
		byte[] wkb = GeoFunctions.ST_AsWKB(g);
//		GetTypeCode BY name 
		Geom ggg = GeoFunctions.ST_GeomFromWKB(wkb);
		System.out.println(GeoFunctions.ST_CoordDim(GeoFunctions.ST_GeomFromText("LINESTRING(40 50, 0 10)")));
//		gt.name()
//		GeometryType gt = GeometryType.LINESTRING;//GeometryType.convertStringToGType("POLYGON");
////		int iGType = gt.getGTypeAsInt();
////		String strGType = gt.getGTypeAsString();
////		strGType = GeometryType.convertGTypeToString(GeometryType.GEOMETRY);
//		System.out.println(gt.name() + " " + gt.ordinal() + " " + GeometryType.valueOf("LINESTRING"));
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
	
	public enum GeometryType2 {
		UNKNOWN(0), 
		GEOMETRY(1),
		POINT(2),
		LINESTRING(3),
		POLYGON(4),
		MULTIPOINT(5),
		MULTILINESTRING(6),
		MULTIPOLYGON(7);
		
		private static final Map<Integer,GeometryType2> lookup 
	      = new HashMap<Integer,GeometryType2>();

		static {
		      for(GeometryType2 s : EnumSet.allOf(GeometryType2.class))
		           lookup.put(s.getCode(), s);
		 }
		
		private int code;

		 private GeometryType2(int code) {
		      this.code = code;
		 }

		 public int getCode() { return code; }

		 public static GeometryType2 get(int code) { 
		      return lookup.get(code); 
		 }
	}

}	
