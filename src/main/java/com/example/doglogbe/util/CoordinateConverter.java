package com.example.doglogbe.util;

import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;

public class CoordinateConverter {

    public static double[] convert5174ToWGS84(double x, double y) {
        CRSFactory crsFactory = new CRSFactory();

        // EPSG:5174 - UTM-K
        CoordinateReferenceSystem utmk = crsFactory.createFromParameters("UTMK",
                "+proj=tmerc +lat_0=38 +lon_0=127.5 +k=1 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs");

        // EPSG:4326 - WGS84 위경도
        CoordinateReferenceSystem wgs84 = crsFactory.createFromName("EPSG:4326");

        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform transform = ctFactory.createTransform(utmk, wgs84);

        ProjCoordinate srcCoord = new ProjCoordinate(x, y);
        ProjCoordinate dstCoord = new ProjCoordinate();

        transform.transform(srcCoord, dstCoord);

        return new double[]{dstCoord.y, dstCoord.x}; // [위도, 경도]
    }
}
