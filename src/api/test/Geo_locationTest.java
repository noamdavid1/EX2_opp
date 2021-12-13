package api.test;

import api.GeoLocation;
import api.Geo_location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Geo_locationTest {

    GeoLocation geotest1 = new Geo_location(0,0);
    GeoLocation geotest2 = new Geo_location(2,1);
    GeoLocation geotest3 = new Geo_location(20,3);
    GeoLocation geotest4 = new Geo_location(80,30);
    GeoLocation geotest5 = new Geo_location(22,44);
    GeoLocation geotest6 = new Geo_location(7,9);

    @Test // success //
    void x() {
        assertEquals(0,geotest1.x());
        assertEquals(2,geotest2.x());
        assertEquals(20,geotest3.x());
        assertEquals(80,geotest4.x());
        assertEquals(22,geotest5.x());
        assertEquals(7,geotest6.x());
    }

    @Test // success //
    void y() {
        assertEquals(0,geotest1.y());
        assertEquals(1,geotest2.y());
        assertEquals(3,geotest3.y());
        assertEquals(30,geotest4.y());
        assertEquals(44,geotest5.y());
        assertEquals(9,geotest6.y());
    }

    @Test // success //
    void z() {
        assertEquals(0,geotest1.z());
        /*
        assertEquals(4,geotest2.z());
        assertEquals(11,geotest3.z());
        assertEquals(40,geotest4.z());
        assertEquals(66,geotest5.z());
        assertEquals(11,geotest6.z());
         */
    }

    @Test // success //
    void distance() {
        assertEquals(Math.sqrt(5),geotest1.distance(geotest2));
        assertEquals(Math.sqrt(328),geotest2.distance(geotest3));
        assertEquals(Math.sqrt(4329),geotest3.distance(geotest4));

    }
}