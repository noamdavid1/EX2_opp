package api.test;

import api.EdgeData;
import api.Edge_data;
import api.NodeData;
import api.Node_Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Edge_dataTest {

    NodeData source1 = new Node_Data(8);
    NodeData destination1 = new Node_Data(4);
    NodeData source2 = new Node_Data(20);
    NodeData destination2 = new Node_Data(44);
    EdgeData testEdge1 = new Edge_data(source1.getKey(),destination1.getKey(),110);
    EdgeData testEdge2 = new Edge_data (source2.getKey(),destination2.getKey(),80.8);


    @Test
    void getSrc() { // success //
        assertEquals(testEdge1.getSrc(),source1.getKey());
        assertEquals(testEdge2.getSrc(),source2.getKey());
    }

    @Test
    void getDest() { // success //
        assertEquals(testEdge1.getDest(),destination1.getKey());
        assertEquals(testEdge2.getDest(),destination2.getKey());
    }

    @Test
    void getWeight() { // success //
        assertEquals(testEdge1.getWeight(),110);
        assertEquals(testEdge2.getWeight(),80.8);
    }

    @Test
    void getInfo() { // success //
        assertEquals(testEdge1.getInfo(),"");
        assertEquals(testEdge2.getInfo(),"");
    }

    @Test
    void setInfo() { // success //
        testEdge1.setInfo("test");
        assertEquals(testEdge1.getInfo(),"test");
        testEdge1.setInfo("hello");
        assertEquals(testEdge1.getInfo(),"hello");
        testEdge2.setInfo("check");
        assertEquals(testEdge2.getInfo(),"check");
        testEdge2.setInfo("node_edge");
        assertEquals(testEdge2.getInfo(),"node_edge");

    }

    @Test
    void getTag() { // success //
        assertEquals(testEdge1.getTag(),0);
        assertEquals(testEdge2.getTag(),0);
    }

    @Test
    void setTag() { // success //
        testEdge1.setTag(22);
        assertEquals(testEdge1.getTag(),22);
        testEdge1.setTag(100);
        assertEquals(testEdge1.getTag(),100);
        testEdge2.setTag(82);
        assertEquals(testEdge2.getTag(),82);
        testEdge2.setTag(10);
        assertEquals(testEdge2.getTag(),10);
    }
}