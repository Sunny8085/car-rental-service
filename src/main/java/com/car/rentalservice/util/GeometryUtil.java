package com.car.rentalservice.util;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTReader;

public class GeometryUtil {
	
	private static final GeometryFactory geometryFactory = new GeometryFactory ();
	
	public static Geometry stringTOGeometry(String geo) {
		try {
            WKTReader reader = new WKTReader(geometryFactory);
            return reader.read(geo);
        } catch (Exception e) {
            throw new RuntimeException("Invalid Car Geometry format");
        }
	}
	
}
