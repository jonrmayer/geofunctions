package jonathan.mayer.jts.geo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.locationtech.jts.io.gml2.GMLReader;
import org.locationtech.jts.io.gml2.GMLWriter;
import org.locationtech.jts.io.kml.KMLWriter;

import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.locationtech.jts.operation.overlay.snap.GeometrySnapper;
import org.locationtech.jts.operation.union.UnaryUnionOp;
import org.locationtech.jts.simplify.DouglasPeuckerSimplifier;
import org.locationtech.jts.simplify.TopologyPreservingSimplifier;
import org.locationtech.jts.algorithm.MinimumBoundingCircle;
import org.locationtech.jts.algorithm.MinimumDiameter;
import org.locationtech.jts.densify.Densifier;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;

public class GeoFunctions {
	private static final int NO_SRID = 0;
	private static final SpatialReference SPATIAL_REFERENCE = SpatialReference.create(4326);

	private GeoFunctions() {
	}

	private static UnsupportedOperationException todo() {
		return new UnsupportedOperationException();
	}

	public static Double ST_Area(Geom geom1) {
		return GeometryEngine.getarea(geom1.g());
	}

	public static String ST_AsText(Geom g) {
		return ST_AsWKT(g);
	}

	public static String ST_AsWKT(Geom g) {
		return GeometryEngine.geometryToWkt(g.g());
	}

	public static String ST_AsGeoJSON(Geom g, boolean EncodeCRS) {

		String result = GeometryEngine.geometryToGeoJSON(g.g(), EncodeCRS);
		
		return result;
	}
	
	public static String ST_AsKML(Geom geom) {

		String result = GeometryEngine.geometryToKML(geom.g());
		
		return result;
	}
	
	public static String ST_AsGML(Geom geom) {

		String result = GeometryEngine.geometryToGML(geom.g());
		
		return result;
	}

	public static byte[] ST_AsWKB(Geom g) {
		return GeometryEngine.geometryToWkb(g.g());
	}

	public static Geom ST_GeomFromWKB(byte[] b) {
		final Geometry g = GeometryEngine.geometryFromWkb(b, GeometryType.GEOMETRY);
		return bind(g, NO_SRID);
	}

	public static Geom ST_GeomFromText(String s) {
		return ST_GeomFromText(s, NO_SRID);
	}

	public static Geom ST_GeomFromGeoJSON(String s) {
		Geometry result = GeometryEngine.geometryFromGeojson(s);
		return new SimpleGeom(result);
	}

	public static Geom ST_GeomFromText(String s, int srid) {
		final Geometry g = GeometryEngine.geometryFromWkt(s, GeometryType.GEOMETRY);
		return bind(g, srid);
	}

	public static Geom ST_LineFromText(String s) {
		return ST_GeomFromText(s, NO_SRID);
	}

	public static Geom ST_LineFromText(String wkt, int srid) {
		final Geometry g = GeometryEngine.geometryFromWkt(wkt, GeometryType.LINESTRING);
		return bind(g, srid);
	}

	public static Double ST_Length(Geom geom1) {
		return GeometryEngine.length(geom1.g());
	}

	public static Double ST_Perimeter(Geom geom1) {
		return GeometryEngine.perimeter(geom1.g());
	}

	public static Geom ST_MPointFromText(String s) {
		return ST_GeomFromText(s, NO_SRID);
	}

	public static Geom ST_MPointFromText(String wkt, int srid) {
		final Geometry g = GeometryEngine.geometryFromWkt(wkt, GeometryType.MULTIPOINT);
		return bind(g, srid);
	}

	public static Geom ST_PointFromText(String s) {
		return ST_GeomFromText(s, NO_SRID);
	}

	public static Geom ST_PointFromText(String wkt, int srid) {
		final Geometry g = GeometryEngine.geometryFromWkt(wkt, GeometryType.POINT);
		return bind(g, srid);
	}

	public static Geom ST_PolyFromText(String s) {
		return ST_GeomFromText(s, NO_SRID);
	}

	public static Geom ST_Reverse(Geom geom) {

		Geometry result = GeometryEngine.reverse(geom.g());

		return geom.wrap(result);
	}

	public static Geom ST_PolyFromText(String wkt, int srid) {
		final Geometry g = GeometryEngine.geometryFromWkt(wkt, GeometryType.POLYGON);
		return bind(g, srid);
	}

	public static Geom ST_MLineFromText(String s) {
		return ST_GeomFromText(s, NO_SRID);
	}

	public static Geom ST_MLineFromText(String wkt, int srid) {
		final Geometry g = GeometryEngine.geometryFromWkt(wkt, GeometryType.MULTILINESTRING);
		return bind(g, srid);
	}

	public static Geom ST_MPolyFromText(String s) {
		return ST_GeomFromText(s, NO_SRID);
	}

	public static Geom ST_MPolyFromText(String wkt, int srid) {
		final Geometry g = GeometryEngine.geometryFromWkt(wkt, GeometryType.MULTIPOLYGON);
		return bind(g, srid);
	}

	public static Geom ST_Normalize(Geom geom) {
		final Geometry g = GeometryEngine.normalize(geom.g());
		return geom.wrap(g);
	}

	public static int ST_NumGeometries(Geom geom) {
		return GeometryEngine.numgeometries(geom.g());
	}

	public static int ST_NPoints(Geom geom) {
		return GeometryEngine.numpoints(geom.g());
	}

	public static int ST_NumPoints(Geom geom) {
		return GeometryEngine.numpoints(geom.g());
	}

	public static int ST_NRings(Geom geom) {
		return GeometryEngine.nrings(geom.g());
	}

	public static int ST_NumInteriorRings(Geom geom) {
		return GeometryEngine.nInteriorRings(geom.g());
	}

	// Creation

	public static Geom ST_MakePoint(BigDecimal x, BigDecimal y) {
		return ST_Point(x, y);
	}

	public static Geom ST_MakePoint(BigDecimal x, BigDecimal y, BigDecimal z) {
		return ST_Point(x, y, z);
	}

	public static Geom ST_Point(BigDecimal x, BigDecimal y) {

		Geometry result = GeometryEngine.makePoint(x.doubleValue(), y.doubleValue());

		return new SimpleGeom(result);
	}

	public static Geom ST_MinimumRectangle(Geom geom) {

		Geometry result = GeometryEngine.minimumRectangle(geom.g());

		return new SimpleGeom(result);
	}

	public static Geom ST_MinimumDiameter(Geom geom) {

		Geometry result = GeometryEngine.minimumDiameter(geom.g());

		return new SimpleGeom(result);
	}

	public static Geom ST_Point(BigDecimal x, BigDecimal y, BigDecimal z) {

		Geometry result = GeometryEngine.makePoint(x.doubleValue(), y.doubleValue(), z.doubleValue());
		return new SimpleGeom(result);
	}

	public static Geom ST_MakeEnvelope(double xmin, double ymin, double xmax, double ymax, int srid) {

		Geometry result = GeometryEngine.makeEnvelope(xmin, ymin, xmax, ymax, srid);
		return new SimpleGeom(result);
	}

	public static Geom ST_MakeLine(Geom... geoms) {

		Collection<Geometry> colgeoms = new ArrayList<Geometry>();
		for (Geom geom : geoms) {
			colgeoms.add(geom.g());
		}
		Geometry result = GeometryEngine.makeLine(colgeoms);
		return new SimpleGeom(result);
	}

	// Geometry properties (2D and 3D) ==========================================

	/** Returns whether {@code geom} has at least one z-coordinate. */
	public static boolean ST_Is3D(Geom geom) {
		return GeometryEngine.hasZ(geom.g());
	}

	public static boolean ST_IsValid(Geom geom) {
		return GeometryEngine.isvalid(geom.g());
	}

	public static boolean ST_IsSimple(Geom geom) {
		return GeometryEngine.issimple(geom.g());
	}

	/** Returns the x-value of the first coordinate of {@code geom}. */
	public static Double ST_X(Geom geom) {
		return geom.g() instanceof Point ? ((Point) geom.g()).getX() : null;
	}

	public static Double ST_XMax(Geom geom) {
		Double result = GeometryEngine.getxmax(geom.g());
		return result;
	}

	public static Double ST_XMin(Geom geom) {
		Double result = GeometryEngine.getxmin(geom.g());
		return result;
	}

	public static Double ST_YMax(Geom geom) {
		Double result = GeometryEngine.getymax(geom.g());
		return result;
	}

	public static Double ST_YMin(Geom geom) {
		Double result = GeometryEngine.getymin(geom.g());
		return result;
	}

	/** Returns the y-value of the first coordinate of {@code geom}. */
	public static Double ST_Y(Geom geom) {
		return geom.g() instanceof Point ? ((Point) geom.g()).getY() : null;
	}

	/** Returns the z-value of the first coordinate of {@code geom}. */
	public static Double ST_Z(Geom geom) {
		Double result = null;
		if (ST_Is3D(geom)) {
			result = geom.g().getCoordinate().z;
		}
		return result;

	}

	public static Geom ST_Boundary(Geom geom) {

		Geometry result = GeometryEngine.getBoundary(geom.g());
		return geom.wrap(result);
	}

	public static Geom ST_BoundingCircle(Geom geom) {

		Geometry result = GeometryEngine.boundingcircle(geom.g());
		return geom.wrap(result);
	}

	public static Geom ST_BoundingCircleCenter(Geom geom) {

		Geometry result = GeometryEngine.boundingcirclecenter(geom.g());
		return geom.wrap(result);
	}

	/** Returns the boundary of {@code geom}. */
	public static double ST_Distance(Geom geom1, Geom geom2) {
		return GeometryEngine.distance(geom1.g(), geom2.g(), geom1.sr());
	}

	/** Returns the type of {@code geom}. */
	public static String ST_GeometryType(Geom geom) {
		return GeometryEngine.typename(geom.g());
	}

	/** Returns the OGC SFS type code of {@code geom}. */
	public static int ST_GeometryTypeCode(Geom geom) {
		return GeometryEngine.typecode(geom.g());
	}

	/**
	 * Returns the minimum bounding box of {@code geom} (which may be a
	 * GEOMETRYCOLLECTION).
	 */
	public static Geom ST_Envelope(Geom geom) {

		return geom.wrap(GeometryEngine.envelope(geom.g()));
	}

	public static Geom ST_Centroid(Geom geom) {
		Geometry result = GeometryEngine.centroid(geom.g());
		return geom.wrap(result);
	}

	/** Returns whether {@code geom1} contains {@code geom2}. */
	public static boolean ST_Contains(Geom geom1, Geom geom2) {
		return GeometryEngine.contains(geom1.g(), geom2.g());
	}

	public static boolean ST_ContainsProperly(Geom geom1, Geom geom2) {
		return GeometryEngine.contains(geom1.g(), geom2.g()) && !GeometryEngine.crosses(geom1.g(), geom2.g());
	}

	public static int ST_CoordDim(Geom geom1) {
		return GeometryEngine.coorddim(geom1.g());
	}

	public static boolean ST_Crosses(Geom geom1, Geom geom2) {
		return GeometryEngine.crosses(geom1.g(), geom2.g());
	}

	public static boolean ST_Covers(Geom geom1, Geom geom2) {
		return GeometryEngine.covers(geom1.g(), geom2.g());

	}

	public static boolean ST_Intersects(Geom geom1, Geom geom2) {
		return GeometryEngine.intersects(geom1.g(), geom2.g());
	}

	public static boolean ST_EnvelopesIntersect(Geom geom1, Geom geom2) {
		return GeometryEngine.envelopeintersects(geom1.g(), geom2.g());

	}

	public static boolean ST_Disjoint(Geom geom1, Geom geom2) {
		return GeometryEngine.disjoint(geom1.g(), geom2.g());
	}

	public static boolean ST_Equals(Geom geom1, Geom geom2) {
		return GeometryEngine.equals(geom1.g(), geom2.g());
	}

	public static Geom ST_ExteriorRing(Geom geom) {

		Geometry result = GeometryEngine.exteriorring(geom.g());
		return new SimpleGeom(result);
	}

	public static boolean ST_Overlaps(Geom geom1, Geom geom2) {
		return GeometryEngine.overlaps(geom1.g(), geom2.g());
	}

	public static boolean ST_Touches(Geom geom1, Geom geom2) {
		return GeometryEngine.touches(geom1.g(), geom2.g());
	}

	/** Returns whether {@code geom1} is within {@code geom2}. */
	public static boolean ST_Within(Geom geom1, Geom geom2) {
		return GeometryEngine.within(geom1.g(), geom2.g());
	}

	public static boolean ST_DWithin(Geom geom1, Geom geom2, double distance) {
		return GeometryEngine.dwithin(geom1.g(), geom2.g(), distance);
	}

	/** Computes a buffer around {@code geom}. */
	public static Geom ST_Buffer(Geom geom, Double distance) {
		Geometry g = GeometryEngine.buffer(geom.g(), distance);
		return geom.wrap(g);
	}

	public static Geom ST_Densify(Geom geom, Double tolerance) {
		Geometry g = GeometryEngine.densify(geom.g(), tolerance);
		return geom.wrap(g);
	}

	/** Computes a buffer around {@code geom} with . */
	public static Geom ST_Buffer(Geom geom, double distance, int quadSegs) {
		Geometry g = GeometryEngine.buffer(geom.g(), distance, quadSegs);
		return geom.wrap(g);
	}

	/** Computes a buffer around {@code geom} with . */
	public static Geom ST_Buffer(Geom geom, double distance, String style) {
		Geometry g = GeometryEngine.buffer(geom.g(), distance);
		return geom.wrap(g);
	}

	public static Geom ST_Union(Geom... geoms) {
		SpatialReference sr = geoms[0].sr();
		Collection<Geometry> colgeoms = new ArrayList<Geometry>();
		for (Geom geom : geoms) {
			colgeoms.add(geom.g());
		}
		Geometry g = GeometryEngine.union(colgeoms);
		return bind(g, sr);

	}

	public static Geom ST_SetSRID(Geom geom, int srid) {
		SpatialReference sr = SpatialReference.create(srid);

		Geometry g = GeometryEngine.setsrid(geom.g(), srid);
		return bind(g, sr);

	}

	public static Geom ST_StartPoint(Geom geom) {

		Geometry g = GeometryEngine.startpoint(geom.g());
		return new SimpleGeom(g);

	}

	public static Geom ST_Snap(Geom geom1, Geom geom2, double distance) {

		Geometry g = GeometryEngine.snap(geom1.g(), geom2.g(), distance);
		return new SimpleGeom(g);

	}

	public static Geom ST_SimplifyPreserveTopology(Geom geom, double distance) {

		Geometry g = GeometryEngine.simplifypreservetopology(geom.g(), distance);
		return geom.wrap(g);

	}

	public static Geom ST_Simplify(Geom geom, double distance) {

		Geometry g = GeometryEngine.simplify(geom.g(), distance);
		return geom.wrap(g);

	}

	public static Geom ST_SymDifference(Geom geom1, Geom geom2) {

		Geometry g = GeometryEngine.symmdifference(geom1.g(), geom1.g());
		return new SimpleGeom(g);

	}

	public static Geom ST_Intersection(Geom geom1, Geom geom2) {

		Geometry g = GeometryEngine.intersection(geom1.g(), geom1.g());
		return new SimpleGeom(g);

	}

	public static Geom ST_ConvexHull(Geom geom) {

		Geometry g = GeometryEngine.convexhull(geom.g());
		return new SimpleGeom(g);

	}

	public static Geom ST_Difference(Geom geom1, Geom geom2) {

		Geometry g = GeometryEngine.difference(geom1.g(), geom1.g());
		return new SimpleGeom(g);

	}

	public static Geom ST_EndPoint(Geom geom) {

		Geometry g = GeometryEngine.endpoint(geom.g());
		return new SimpleGeom(g);

	}

	public static Geom ST_Transform(Geom geom, int srid) {
		SpatialReference sr = SpatialReference.create(srid);
		Geometry g = GeometryEngine.transform(geom.g(), srid);
		return bind(g, sr);

	}

	public static Geom ST_Transform(Geom geom, String proj) {
		// SpatialReference sr = SpatialReference.create(srid);
		Geometry g = GeometryEngine.transform(geom.g(), proj);
		return new SimpleGeom(g);

	}

	protected static Geom bind(Geometry geometry, int srid) {
		if (geometry == null) {
			return null;
		}
		if (srid == NO_SRID) {
			return new SimpleGeom(geometry);
		}
		return bind(geometry, SpatialReference.create(srid));
	}

	private static MapGeom bind(Geometry geometry, SpatialReference sr) {
		return new MapGeom(new MapGeometry(geometry, sr));
	}

	static class GeometryEngine {

		private static final WKTReader wkr = new WKTReader();
		private static final WKTWriter wkw = new WKTWriter();

		private static final GeoJsonReader gsr = new GeoJsonReader();
		private static final GeoJsonWriter gsw = new GeoJsonWriter();
		
		private static final GMLReader gmlr = new GMLReader();
		private static final GMLWriter gmlw = new GMLWriter();
		
		// private static final KMLReader kmlr = new KMLReader();
		private static final KMLWriter kmlw = new KMLWriter();

		private static final WKBReader wkbr = new WKBReader();
		private static final WKBWriter wkbw = new WKBWriter();

		private static final GeometryFactory geometryFactory = new GeometryFactory();

		private static UnsupportedOperationException err() {
			return new UnsupportedOperationException();
		}

		public static String geometryToWkt(Geometry geometry) {
			if (geometry == null) {
				return null;
			}
			String wkt = wkw.write(geometry);
			return wkt;
		}

		public static String geometryToKML(Geometry geom) {

			if (geom == null) {
				return null;
			}
			
			String kml = kmlw.write(geom);
			return kml;
		}
		
		public static String geometryToGML(Geometry geom) {

			if (geom == null) {
				return null;
			}
			
			String gml = gmlw.write(geom);
			return gml;
		}

		public static String geometryToGeoJSON(Geometry geom, boolean encodeCRS) {

			if (geom == null) {
				return null;
			}
			gsw.setEncodeCRS(encodeCRS);
			String geojson = gsw.write(geom);
			return geojson;
		}

		public static Geometry simplify(Geometry geometry, double distance) {
			Geometry result = null;
			if (geometry == null) {
				return result;
			}

			result = DouglasPeuckerSimplifier.simplify(geometry, distance);
			return result;
		}

		public static Geometry simplifypreservetopology(Geometry geometry, double distance) {
			Geometry result = null;
			if (geometry == null) {
				return result;
			}

			result = TopologyPreservingSimplifier.simplify(geometry, distance);
			return result;
		}

		public static Geometry snap(Geometry geom1, Geometry geom2, double distance) {
			Geometry result = null;
			if (geom1 == null || geom2 == null) {
				return result;
			}
			Geometry[] temp = GeometrySnapper.snap(geom1, geom2, distance);
			result = temp[0];
			return result;
		}

		public static byte[] geometryToWkb(Geometry geometry) {
			if (geometry == null) {
				return null;
			}

			byte[] wkb = wkbw.write(geometry);
			return wkb;
		}

		public static Geometry geometryFromWkb(byte[] wkb, GeometryType geometrytype) {
			if (wkb == null) {
				return null;
			}
			Geometry geom = null;
			try {
				geom = wkbr.read(wkb);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			switch (geometrytype) {
			case GEOMETRY:

				break;
			case POINT:
				if (!geom.getGeometryType().equalsIgnoreCase("point")) {
					throw err();
				}
				break;
			case LINESTRING:
				if (!geom.getGeometryType().equalsIgnoreCase("linestring")) {
					throw err();
				}
				break;
			case POLYGON:
				if (!geom.getGeometryType().equalsIgnoreCase("polygon")) {
					throw err();
				}
				break;
			case MULTIPOINT:
				if (!geom.getGeometryType().equalsIgnoreCase("MULTIPOINT")) {
					throw err();
				}
				break;

			case MULTILINESTRING:
				if (!geom.getGeometryType().equalsIgnoreCase("MULTILINESTRING")) {
					throw err();
				}
				break;

			case MULTIPOLYGON:
				if (!geom.getGeometryType().equalsIgnoreCase("MULTIPOLYGON")) {
					throw err();
				}
				break;

			default:
				throw err();

			}
			return geom;

		}

		public static Geometry geometryFromGeojson(String geojson) {
			Geometry result = null;
			try {
				result = gsr.read(geojson);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

		public static Geometry geometryFromWkt(String wkt, GeometryType geometrytype) {
			if (wkt == null) {
				return null;
			}
			Geometry geom = null;
			try {
				geom = wkr.read(wkt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			switch (geometrytype) {
			case GEOMETRY:

				break;
			case POINT:
				if (!geom.getGeometryType().equalsIgnoreCase("point")) {
					throw err();
				}
				break;
			case LINESTRING:
				if (!geom.getGeometryType().equalsIgnoreCase("linestring")) {
					throw err();
				}
				break;
			case POLYGON:
				if (!geom.getGeometryType().equalsIgnoreCase("polygon")) {
					throw err();
				}
				break;
			case MULTIPOINT:
				if (!geom.getGeometryType().equalsIgnoreCase("MULTIPOINT")) {
					throw err();
				}
				break;

			case MULTILINESTRING:
				if (!geom.getGeometryType().equalsIgnoreCase("MULTILINESTRING")) {
					throw err();
				}
				break;

			case MULTIPOLYGON:
				if (!geom.getGeometryType().equalsIgnoreCase("MULTIPOLYGON")) {
					throw err();
				}
				break;

			default:
				throw err();

			}
			return geom;

		}

		public static Double distance(Geometry g1, Geometry g2, SpatialReference spatialReference) {
			if (g1 == null || g2 == null) {
				return null;
			}
			return g1.distance(g2);

		}

		public static boolean hasZ(Geometry geom) {

			boolean result = false;
			if (geom == null) {
				return result;
			}
			Double z = geom.getCoordinates()[0].z;
			if (!z.isNaN()) {
				result = true;
			}

			return result;

		}

		public static boolean isvalid(Geometry geom) {
			boolean result = false;
			if (geom == null) {
				return result;
			}

			result = geom.isValid();
			return result;

		}

		public static double length(Geometry geom) {

			Double result = 0.0d;
			if (geom == null) {
				return result;
			}

			if (geom instanceof LineString || geom instanceof MultiLineString) {
				result = geom.getLength();
			}

			return result;

		}

		public static double perimeter(Geometry geom) {

			double result = 0.0d;
			if (geom == null) {
				return result;
			}

			if (geom instanceof Polygon || geom instanceof MultiPolygon) {

				for (int i = 0; i < geom.getNumGeometries(); i++) {

					Polygon p = (Polygon) geom.getGeometryN(i);

					result += p.getExteriorRing().getLength();

				}

			}

			return result;

		}

		public static boolean issimple(Geometry geom) {
			boolean result = false;
			if (geom == null) {
				return result;
			}

			result = geom.isSimple();
			return result;

		}

		public static Geometry buffer(Geometry geom, Double distance) {
			if (geom == null) {
				return null;
			}
			return geom.buffer(distance);
		}

		public static Geometry buffer(Geometry geom, Double distance, int quadrantSegments) {
			if (geom == null) {
				return null;
			}
			return geom.buffer(distance, quadrantSegments);
		}

		public static Geometry centroid(Geometry geom) {
			if (geom == null) {
				return null;
			}
			return geom.getCentroid();
		}

		public static Geometry transform(Geometry geom, int srid) {
			if (geom == null) {
				return null;
			}

			Geometry result = GeometryTransform.transform(geom, srid);
			return result;
		}

		public static Geometry transform(Geometry geom, String to_proj) {
			if (geom == null) {
				return null;
			}

			Geometry result = GeometryTransform.transform(geom, to_proj);
			return result;
		}

		public static Geometry setsrid(Geometry geom, int srid) {
			if (geom == null) {
				return null;
			}

			Geometry result = geom.copy();
			result.setSRID(srid);

			return result;
		}

		public static Geometry startpoint(Geometry geom) {
			if (geom == null) {
				return null;
			}
			Geometry result = null;
			if (geom instanceof LineString) {

				LineString ls = (LineString) geom;
				result = ls.getStartPoint();
			}

			return result;
		}

		public static Geometry endpoint(Geometry geom) {
			if (geom == null) {
				return null;
			}
			Geometry result = null;
			if (geom instanceof LineString) {

				LineString ls = (LineString) geom;
				result = ls.getEndPoint();
			}

			return result;
		}

		public static Geometry union(Collection<Geometry> geoms) {
			if (geoms == null) {
				return null;
			}

			return UnaryUnionOp.union(geoms);

		}

		// public static Geometry buffer(Geom geom, Double distance,int
		// quadrantSegments,String style) {
		// if (geom == null) {
		// return null;
		// }
		// return geom.g().buffer(distance,endCapStyle);
		// }
		public static Boolean touches(Geometry geom1, Geometry geom2) {
			if (geom1 == null || geom2 == null) {
				return null;
			}
			return geom1.touches(geom2);
		}

		public static Boolean within(Geometry geom1, Geometry geom2) {
			if (geom1 == null || geom2 == null) {
				return null;
			}
			return geom1.within(geom2);
		}

		public static Boolean dwithin(Geometry geom1, Geometry geom2, Double distance) {
			if (geom1 == null || geom2 == null) {
				return null;
			}
			return geom1.isWithinDistance(geom2, distance);
		}

		public static Boolean overlaps(Geometry geom1, Geometry geom2) {
			if (geom1 == null || geom2 == null) {
				return null;
			}
			return geom1.overlaps(geom2);
		}

		public static Boolean equals(Geometry geom1, Geometry geom2) {
			if (geom1 == null || geom2 == null) {
				return null;
			}
			return geom1.equals(geom2);
		}

		public static Boolean intersects(Geometry geom1, Geometry geom2) {
			if (geom1 == null || geom2 == null) {
				return null;
			}
			return geom1.intersects(geom2);
		}

		public static Boolean envelopeintersects(Geometry geom1, Geometry geom2) {
			if (geom1 == null || geom2 == null) {
				return null;
			}
			return geom1.getEnvelopeInternal().intersects(geom2.getEnvelopeInternal());
		}

		public static Geometry exteriorring(Geometry geom) {
			Geometry result = null;

			if (geom == null) {
				return result;
			}
			if (geom instanceof Polygon) {

				Polygon p = (Polygon) geom;
				result = p.getExteriorRing();
			}
			return result;
		}

		public static Boolean crosses(Geometry geom1, Geometry geom2) {
			if (geom1 == null || geom2 == null) {
				return null;
			}
			return geom1.crosses(geom2);
		}

		public static Boolean disjoint(Geometry geom1, Geometry geom2) {
			if (geom1 == null || geom2 == null) {
				return null;
			}
			return geom1.disjoint(geom2);
		}

		public static Geometry densify(Geometry geom, double tolerance) {
			Geometry result = null;

			if (geom == null) {
				return result;
			}
			result = Densifier.densify(geom, tolerance);
			return result;
		}

		public static Boolean covers(Geometry geom1, Geometry geom2) {
			if (geom1 == null || geom2 == null) {
				return null;
			}
			return geom1.covers(geom2);
		}

		public static int coorddim(Geometry geom) {
			return geom.getDimension();
		}

		public static Boolean contains(Geometry geom1, Geometry geom2) {
			boolean result = false;
			if (geom1 == null) {
				return null;
			}
			if (geom2 == null) {
				return false;
			}
			result = geom1.contains(geom2);
			return result;
		}

		public static Geometry convexhull(Geometry geom) {
			Geometry result = null;

			if (geom == null) {
				return result;
			}
			return geom.convexHull();
		}

		public static Geometry difference(Geometry geom1, Geometry geom2) {
			Geometry result = null;

			if (geom1 == null || geom2 == null) {
				return result;
			}
			return geom1.difference(geom2);
		}

		public static Geometry intersection(Geometry geom1, Geometry geom2) {
			Geometry result = null;

			if (geom1 == null || geom2 == null) {
				return result;
			}
			return geom1.intersection(geom2);
		}

		public static Geometry symmdifference(Geometry geom1, Geometry geom2) {
			Geometry result = null;

			if (geom1 == null || geom2 == null) {
				return result;
			}

			result = geom1.symDifference(geom2);
			return result;
		}

		public static Geometry envelope(Geometry geom) {
			if (geom == null) {
				return null;
			}
			return geom.getEnvelope();
		}

		public static Geometry getBoundary(Geometry geom) {
			if (geom == null) {
				return null;
			}
			return geom.getBoundary();
		}

		public static Geometry makePoint(double x, double y) {

			final Geometry g = createPoint(x, y);
			return g;
		}

		public static Geometry makePoint(double x, double y, double z) {

			final Geometry g = createPoint(x, y, z);
			return g;
		}

		public static Geometry minimumRectangle(Geometry geom) {
			if (geom == null) {
				return null;
			}
			final Geometry g = new MinimumDiameter(geom).getMinimumRectangle();

			return g;
		}

		public static Geometry minimumDiameter(Geometry geom) {
			if (geom == null) {
				return null;
			}
			final Geometry g = new MinimumDiameter(geom).getDiameter();
			return g;
		}

		public static Geometry normalize(Geometry geom) {

			Geometry result = null;

			if (geom == null) {
				return result;
			}

			Geometry g = geom.copy();
			g.normalize();

			return g;
		}

		public static int numpoints(Geometry geom) {
			if (geom == null) {
				return 0;
			}

			return geom.getNumPoints();
		}

		public static Geometry removeholes(Geometry geom) {

			Geometry result = null;
			if (geom == null) {
				return result;
			}
			if (geom instanceof Polygon || geom instanceof MultiPolygon) {
				if (geom instanceof Polygon) {

					if (geom.getGeometryN(0) != null) {

					}

				} else if (geom instanceof MultiPolygon) {

				}

			}

			return result;
		}

		public static Geometry reverse(Geometry geom) {

			Geometry result = null;

			if (geom == null) {
				return result;
			}
			result = geom.reverse();

			return result;
		}

		public static int nInteriorRings(Geometry geom) {
			if (geom == null) {
				return 0;
			}
			int total = 0;

			if (geom instanceof Polygon) {

				if (geom.getGeometryN(0) != null) {

					total += ((Polygon) geom).getNumInteriorRing();
				}
			} else if (geom instanceof MultiPolygon) {

				int num = numgeometries(geom);

				for (int i = 0; i < num; i++) {
					Geometry p = geom.getGeometryN(i);
					if (p.getGeometryN(0) != null) {

						total += ((Polygon) p).getNumInteriorRing();
					}
				}

			} else {
				return 0;
			}

			return total;
		}

		public static int nrings(Geometry geom) {
			if (geom == null) {
				return 0;
			}
			int total = 0;

			if (geom instanceof Polygon) {

				if (geom.getGeometryN(0) != null) {
					total += 1;
					total += ((Polygon) geom).getNumInteriorRing();
				}
			} else if (geom instanceof MultiPolygon) {

				int num = numgeometries(geom);

				for (int i = 0; i < num; i++) {
					Geometry p = geom.getGeometryN(i);
					if (p.getGeometryN(0) != null) {
						total += 1;
						total += ((Polygon) p).getNumInteriorRing();
					}
				}

			} else {
				return 0;
			}

			return total;
		}

		public static int numgeometries(Geometry geom) {
			if (geom == null) {
				return 0;
			}

			return geom.getNumGeometries();
		}

		public static Point createPoint(double x, double y) {
			return createPoint(x, y, Coordinate.NULL_ORDINATE);
		}

		private static Point createPoint(double x, double y, double z) {
			return geometryFactory.createPoint(new Coordinate(x, y, z));
		}

		public static Geometry[] accum(Geom[] geoms) {

			return null;

		}

		public static Geometry[] collect(Geom[] geoms) {

			return null;

		}

		public static Double getarea(Geometry geom) {

			if (geom == null) {
				return null;
			}

			Double result = geom.getArea();
			return result;
		}

		public static Double getxmax(Geometry geom) {
			if (geom == null) {
				return null;
			}

			Double result = geom.getEnvelopeInternal().getMaxX();

			return result;

		}

		public static Double getxmin(Geometry geom) {
			if (geom == null) {
				return null;
			}

			Double result = geom.getEnvelopeInternal().getMinX();

			return result;

		}

		public static Double getymax(Geometry geom) {
			if (geom == null) {
				return null;
			}

			Double result = geom.getEnvelopeInternal().getMaxY();

			return result;

		}

		public static Double getymin(Geometry geom) {
			if (geom == null) {
				return null;
			}

			Double result = geom.getEnvelopeInternal().getMinY();

			return result;

		}

		// public static Double getzmax(Geometry geom) {
		// if (geom == null) {
		// return null;
		// }
		//
		// Double result =geom.getEnvelopeInternal().
		//
		//
		// return result;
		//
		// }
		//
		// public static Double getzmin(Geometry geom) {
		// if (geom == null) {
		// return null;
		// }
		//
		// Double result =geom.getEnvelopeInternal().getMinZ();
		//
		//
		// return result;
		//
		// }

		public static Geometry makeLine(Collection<Geometry> geomcoll) {

			Geometry[] geoms = geomcoll.toArray(new Geometry[geomcoll.size()]);
			int totalpoints = 0;
			for (Geometry geom : geomcoll) {
				totalpoints += pointCount(geom);
			}
			if (totalpoints < 2) {
				return null;
			}
			LineString g = null;
			Geometry p1 = null;
			if (geoms.length == 1) {
				p1 = geoms[0];
				if (!p1.getGeometryType().equalsIgnoreCase("MULTIPOINT")) {
					return null;
				} else {
					p1 = p1.getGeometryN(0);
				}
			} else {
				p1 = geoms[0];
			}

			List<Coordinate> coordinateList = new LinkedList<Coordinate>();

			for (Geometry geom : geoms) {
				totalpoints += pointCount(geom);
				addCoordinates(geom, coordinateList);
			}
			g = p1.getFactory().createLineString(coordinateList.toArray(new Coordinate[geoms.length]));

			return g;
		}

		public static Geometry makeEnvelope(double xmin, double ymin, double xmax, double ymax, int srid) {

			Coordinate[] coordArray = new Coordinate[] { new Coordinate(xmin, ymin), new Coordinate(xmax, ymin),
					new Coordinate(xmax, ymax), new Coordinate(xmin, ymax), new Coordinate(xmin, ymin) };

			Geometry geom = geometryFactory.createPolygon(coordArray);
			geom.setSRID(srid);
			return geom;
		}

		public static Geometry boundingcircle(Geometry geom) {
			if (geom == null) {
				return null;
			}
			return new MinimumBoundingCircle(geom).getCircle();
		}

		public static Geometry boundingcirclecenter(Geometry geom) {
			if (geom == null) {
				return null;
			}

			return geom.getFactory().createPoint(new MinimumBoundingCircle(geom).getCentre());
		}

		private static void addCoordinates(Geometry p1, List<Coordinate> list) {
			if (p1 instanceof Point) {
				list.add(p1.getCoordinate());
			} else if (p1 instanceof MultiPoint) {
				list.addAll(Arrays.asList(p1.getCoordinates()));
			} else {
				err();
			}
		}

		private static int pointCount(Geometry p) {

			int result = 0;
			if (p instanceof Point) {
				result = 1;
			} else if (p instanceof MultiPoint) {
				result = p.getNumPoints();
			} else {
				err();
			}

			return result;
		}

		public static int typecode(Geometry geom) {

			String gname = geom.getGeometryType().toUpperCase();
			return GeometryType.valueOf(gname).code;

		}

		public static String typename(Geometry geom) {

			String gname = geom.getGeometryType().toUpperCase();

			return GeometryType.valueOf(gname).name();

		}
	}

	// GeometryType Enum
	static class GeometryTransform {
		private static final CoordinateTransformFactory ctf = new CoordinateTransformFactory();
		private static final CRSFactory crsFactory = new CRSFactory();

		public GeometryTransform() {
		}

		public static Geometry transform(Geometry geom, int srid) {

			Geometry result = null;
			int geomsrid = geom.getSRID();

			CoordinateReferenceSystem fromcrs = crs(geomsrid);
			CoordinateReferenceSystem tocrs = crs(srid);
			CoordinateTransform trans = ctf.createTransform(fromcrs, tocrs);
			result = transformGeometry(trans, geom);

			return result;

		}

		public static Geometry transform(Geometry geom, String to_proj) {

			Geometry result = null;
			int geomsrid = geom.getSRID();

			CoordinateReferenceSystem fromcrs = crs(geomsrid);
			CoordinateReferenceSystem tocrs = crs(to_proj);
			CoordinateTransform trans = ctf.createTransform(fromcrs, tocrs);
			result = transformGeometry(trans, geom);

			return result;

		}

		protected static CoordinateReferenceSystem crs(int srid) {

			CoordinateReferenceSystem result = crsFactory.createFromName(String.format("epsg:%s", srid));

			return result;

		}

		protected static CoordinateReferenceSystem crs(String proj) {

			CoordinateReferenceSystem result = crsFactory.createFromParameters(null, proj);

			return result;

		}

		protected static Coordinate[] convert(ProjCoordinate[] projCoords) {
			Coordinate[] jtsCoords = new Coordinate[projCoords.length];
			for (int i = 0; i < projCoords.length; ++i) {
				jtsCoords[i] = new Coordinate(projCoords[i].x, projCoords[i].y);
			}
			return jtsCoords;
		}

		protected static ProjCoordinate[] convert(Coordinate[] jtsCoords) {
			ProjCoordinate[] projCoords = new ProjCoordinate[jtsCoords.length];
			for (int i = 0; i < jtsCoords.length; ++i) {
				projCoords[i] = new ProjCoordinate(jtsCoords[i].x, jtsCoords[i].y);
			}
			return projCoords;
		}

		protected static Coordinate[] transformCoordinates(CoordinateTransform ct, Coordinate[] in) {
			return convert(transformCoordinates(ct, convert(in)));
		}

		protected static ProjCoordinate[] transformCoordinates(CoordinateTransform ct, ProjCoordinate[] in) {
			ProjCoordinate[] out = new ProjCoordinate[in.length];
			for (int i = 0; i < in.length; ++i) {
				out[i] = ct.transform(in[i], new ProjCoordinate());
			}
			return out;
		}

		protected static Polygon transformPolygon(CoordinateTransform ct, Polygon polygon) {
			return polygon.getFactory().createPolygon(transformCoordinates(ct, polygon.getCoordinates()));
		}

		protected static Geometry transformPoint(CoordinateTransform ct, Point point) {
			return point.getFactory().createPoint(transformCoordinates(ct, point.getCoordinates())[0]);
		}

		protected static Geometry transformLinearRing(CoordinateTransform ct, LinearRing linearRing) {
			return linearRing.getFactory().createLinearRing(transformCoordinates(ct, linearRing.getCoordinates()));
		}

		protected static Geometry transformLineString(CoordinateTransform ct, LineString lineString) {
			return lineString.getFactory().createLineString(transformCoordinates(ct, lineString.getCoordinates()));
		}

		protected static Geometry transformMultiPolygon(CoordinateTransform ct, MultiPolygon multiPolygon) {
			Polygon[] polygon = new Polygon[multiPolygon.getNumGeometries()];
			for (int i = 0; i < polygon.length; ++i) {
				polygon[i] = multiPolygon.getFactory()
						.createPolygon(transformCoordinates(ct, multiPolygon.getGeometryN(i).getCoordinates()));
			}
			return multiPolygon.getFactory().createMultiPolygon(polygon);
		}

		protected static Geometry transformMultiPoint(CoordinateTransform ct, MultiPoint multiPoint) {
			return multiPoint.getFactory().createMultiPoint(transformCoordinates(ct, multiPoint.getCoordinates()));
		}

		protected static Geometry transformMultiLineString(CoordinateTransform ct, MultiLineString multiLineString) {
			LineString[] lineString = new LineString[multiLineString.getNumGeometries()];
			for (int i = 0; i < lineString.length; ++i) {
				lineString[i] = multiLineString.getFactory()
						.createLineString(transformCoordinates(ct, multiLineString.getGeometryN(i).getCoordinates()));
			}
			return multiLineString.getFactory().createMultiLineString(lineString);
		}

		protected static Geometry transformGeometryCollection(CoordinateTransform ct,
				GeometryCollection geometryCollection) {
			Geometry[] geometry = new Geometry[geometryCollection.getNumGeometries()];
			for (int i = 0; i < geometry.length; ++i) {
				geometry[i] = transformGeometry(ct, geometryCollection.getGeometryN(i));
			}
			return geometryCollection.getFactory().createGeometryCollection(geometry);
		}

		protected static Geometry transformGeometry(CoordinateTransform ct, Geometry geom) {
			if (geom instanceof Polygon) {
				return transformPolygon(ct, (Polygon) geom);
			} else if (geom instanceof Point) {
				return transformPoint(ct, (Point) geom);
			} else if (geom instanceof LinearRing) {
				return transformLinearRing(ct, (LinearRing) geom);
			} else if (geom instanceof LineString) {
				return transformLineString(ct, (LineString) geom);
			} else if (geom instanceof MultiPolygon) {
				return transformMultiPolygon(ct, (MultiPolygon) geom);
			} else if (geom instanceof MultiPoint) {
				return transformMultiPoint(ct, (MultiPoint) geom);
			} else if (geom instanceof MultiLineString) {
				return transformMultiLineString(ct, (MultiLineString) geom);
			} else if (geom instanceof GeometryCollection) {
				return transformGeometryCollection(ct, (GeometryCollection) geom);
			}
			return null;
		}
	}

	public static class SpatialReference {
		private static final CRSFactory crsFactory = new CRSFactory();
		public static CoordinateReferenceSystem crs;
		public static int wkid;

		public SpatialReference() {
		}

		public static SpatialReference create(int srid) {
			if (srid <= 0)
				throw new IllegalArgumentException("Invalid or unsupported wkid: " + wkid);

			SpatialReference sr = new SpatialReference();
			wkid = srid;
			crs = crsFactory.createFromName(String.format("epsg:%s", srid));

			return sr;
		}

		public int GetSRID() {
			return wkid;
		}
	}

	public enum GeometryType {
		// UNKNOWN(0),
		GEOMETRY(0), POINT(1), LINESTRING(2), POLYGON(3), MULTIPOINT(4), MULTILINESTRING(5), MULTIPOLYGON(
				6), GEOMETRYCOLLECTION(7);

		private static final Map<Integer, GeometryType> lookup = new HashMap<Integer, GeometryType>();

		static {
			for (GeometryType s : EnumSet.allOf(GeometryType.class))
				lookup.put(s.getCode(), s);
		}

		private int code;

		private GeometryType(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		public static GeometryType get(int code) {
			return lookup.get(code);
		}
	}

	// // GeometryType Enum - END
	public static interface Geom {
		Geometry g();

		SpatialReference sr();

		Geom wrap(Geometry g);
	}

	// interface Geom - END
	static class MapGeometry {
		Geometry m_geometry = null;
		SpatialReference sr = null;

		public MapGeometry(Geometry g, SpatialReference _sr) {
			m_geometry = g;

			sr = _sr;
			m_geometry.setSRID(getSRID());
		}

		public Geometry getGeometry() {
			return m_geometry;
		}

		public void setGeometry(Geometry geometry) {
			this.m_geometry = geometry;
		}

		public void setSpatialReference(SpatialReference _sr) {
			this.sr = _sr;
		}

		public SpatialReference getSpatialReference() {
			return sr;
		}

		public int getSRID() {
			return this.sr.GetSRID();
		}
	}

	static class MapGeom implements Geom {
		final MapGeometry mg;

		MapGeom(MapGeometry mg) {
			this.mg = Objects.requireNonNull(mg);
		}

		@Override
		public String toString() {
			return mg.toString();
		}

		public Geometry g() {
			return mg.getGeometry();
		}

		public Geom wrap(Geometry g) {
			return bind(g, this.mg.getSpatialReference());
		}

		public SpatialReference sr() {

			return mg.getSpatialReference();
		}

	}

	static class SimpleGeom implements Geom {
		final Geometry g;

		SimpleGeom(Geometry g) {
			this.g = Objects.requireNonNull(g);
		}

		@Override
		public String toString() {
			return g.toString();
		}

		public Geometry g() {
			return g;
		}

		public Geom wrap(Geometry g) {
			return new SimpleGeom(g);
		}

		public SpatialReference sr() {
			// TODO Auto-generated method stub
			return SPATIAL_REFERENCE;
		}

	}
}
