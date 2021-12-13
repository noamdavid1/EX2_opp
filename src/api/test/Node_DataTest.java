package api.test;

import api.GeoLocation;
import api.Geo_location;
import api.NodeData;
import api.Node_Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Node_DataTest {

    NodeData testNode = new Node_Data(0);

    @Test
    void getKey() { // success //
        NodeData node1 = new Node_Data(40);
        assertEquals(40,node1.getKey());
        NodeData node2 = new Node_Data(60);
        assertEquals(60,node2.getKey());
        NodeData node3 = new Node_Data(80);
        assertEquals(80,node3.getKey());
    }

    @Test
    void getLocation() { // success //
        GeoLocation geo1 = testNode.getLocation();
        assertEquals(geo1,testNode.getLocation());
        GeoLocation geo2 = new Geo_location(1,1);
        testNode.setLocation(geo2);
        assertEquals(geo2,testNode.getLocation());
        GeoLocation geo3 = new Geo_location(4,80);
        testNode.setLocation(geo3);
        assertEquals(geo3,testNode.getLocation());
        GeoLocation geo4 = new Geo_location(22,10);
        testNode.setLocation(geo4);
        assertEquals(geo4,testNode.getLocation());

    }

    @Test
    void setLocation() { // success //
        GeoLocation g1 = new Geo_location(1,2);
        testNode.setLocation(g1);
        assertEquals(testNode.getLocation(),g1);
        GeoLocation g2 = new Geo_location(10,8);
        testNode.setLocation(g2);
        assertEquals(testNode.getLocation(),g2);
        GeoLocation g3 = new Geo_location(2,2);
        testNode.setLocation(g3);
        assertEquals(testNode.getLocation(),g3);
    }

    @Test
    void getWeight() { // success //
        assertEquals(0,testNode.getWeight());
        testNode.setWeight(40.4);
        assertEquals(40.4,testNode.getWeight());
        testNode.setWeight(2.8);
        assertEquals(2.8,testNode.getWeight());
        testNode.setWeight(110);
        assertEquals(110,testNode.getWeight());
    }

    @Test
    void setWeight() { // success //
        testNode.setWeight(80.8);
        assertEquals(testNode.getWeight(),80.8);
        testNode.setWeight(11.11);
        assertEquals(testNode.getWeight(),11.11);
    }

    @Test
    void getInfo() { // success //
        assertEquals("",testNode.getInfo());
        testNode.setInfo("hello");
        assertEquals("hello",testNode.getInfo());
        testNode.setInfo("node");
        assertEquals("node",testNode.getInfo());
    }

    @Test
    void setInfo() { // success //
        testNode.setInfo("test");
        assertEquals("test",testNode.getInfo());
        testNode.setInfo("check");
        assertEquals("check",testNode.getInfo());
        testNode.setInfo("hello");
        assertEquals("hello",testNode.getInfo());
    }

    @Test
    void getTag() { // success //
        assertEquals(0,testNode.getTag());
        testNode.setTag(2);
        assertEquals(2,testNode.getTag());
        testNode.setTag(4);
        assertEquals(4,testNode.getTag());
        testNode.setTag(100);
        assertEquals(100,testNode.getTag());
    }

    @Test
    void setTag() { // success //
        testNode.setTag(43);
        assertEquals(43,testNode.getTag());
        testNode.setTag(20);
        assertEquals(20,testNode.getTag());
        testNode.setTag(8);
        assertEquals(8,testNode.getTag());
    }
}