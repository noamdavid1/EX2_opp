package api.test;

import api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private DirectedWeightedGraph graph=new Graph();
    private DirectedWeightedGraph graph2=new Graph();
    private NodeData node1 =new Node_Data(2);
    private NodeData node2 =new Node_Data(33);


    @Test //success //
    void getNode() {
        assertEquals(2,node1.getKey());
        assertEquals(33,node2.getKey());
    }

    @Test //success //
    void getEdge() {
        DirectedWeightedGraph ga = new Graph();
        ga.addNode(new Node_Data(0));
        ga.addNode(new Node_Data(1));
        ga.addNode(new Node_Data(2));
        ga.addNode(new Node_Data(3));
        ga.addNode(new Node_Data(4));
        assertEquals(5, ga.getMC());
        assertEquals(5, ga.nodeSize());

        ga.connect(0,1,5);
        ga.connect(1,2,4);
        ga.connect(2,3,9);
        ga.connect(2,1,2);
        ga.connect(2,0,3);
        ga.connect(3,4,7);
        assertEquals(11, ga.getMC());
        assertEquals(6, ga.edgeSize());

        assertEquals(3, ga.getEdge(2,0).getWeight());
        assertNotNull(ga.getEdge(0,1));
        assertTrue(ga.getEdge(2,3) != null);
        assertEquals(11, ga.getMC());
        assertEquals(6, ga.edgeSize());
    }

    @Test //success //
    void addNode() {
        graph = new Graph();
        NodeData n = new Node_Data(5);
        NodeData n1 = new Node_Data((Node_Data) n);
        graph.addNode(n);
        assertEquals(1, graph.nodeSize()); /**Should be SAME! */
        graph.addNode(n1);
        assertEquals(1, graph.nodeSize()); /**Should be SAME! */
        for (int i = 0; i < 10; i++)
            graph.addNode(new Node_Data(i));
        assertEquals(10, graph.nodeSize());
    }

    @Test //success //
    void connect() {
        NodeData a = new Node_Data(1);
        NodeData a1 = new Node_Data(2);
        NodeData a2 = new Node_Data(3);
        NodeData a3 = new Node_Data(4);
        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);
        graph.addNode(a3);
        graph.connect(1,2,5);
        graph.connect(1,0,5);
        graph.connect(1,1,5);
        Assertions.assertEquals(1,graph.edgeSize());
        graph.connect(1,3,3);
        Assertions.assertEquals(2,graph.edgeSize());
        graph.removeEdge(1,3);
        Assertions.assertEquals(1,graph.edgeSize());
    }

    @Test // success //
    void nodeIter() {
        NodeData a = new Node_Data(1);
        NodeData a1 = new Node_Data(2);
        NodeData a2 = new Node_Data(3);
        NodeData a3 = new Node_Data(4);
        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);
        graph.addNode(a3);
        int count = 0;
        Iterator it = this.graph.nodeIter();
        while (it.hasNext()){
            count++;
            it.next();
        }
        assertEquals(4,count);
    }

    @Test // success //
    void edgeIter() {
        NodeData a = new Node_Data(1);
        NodeData a1 = new Node_Data(2);
        NodeData a2 = new Node_Data(3);
        NodeData a3 = new Node_Data(4);
        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);
        graph.addNode(a3);
        graph.connect(1,2,2);
        graph.connect(2,3,4);
        graph.connect(3,4,6);
        int count = 0;
        Iterator it = this.graph.edgeIter();
        while (it.hasNext()){
            count++;
            it.next();
        }
        assertEquals(3,count);
    }

    @Test
    void testEdgeIter() {
        DirectedWeightedGraph g1 = new Graph();
        Iterator<EdgeData> itr = g1.edgeIter(0);
        g1.removeEdge(0,1);
        try {
            EdgeData edge = itr.next();
            fail();
        } catch (NoSuchElementException e){
            System.out.println(e.toString());
        }

    }

    @Test //success //
    void removeNode() {
        DirectedWeightedGraph graph1 = new Graph();
        graph1.addNode(new Node_Data(0));
        graph1.addNode(new Node_Data(1));
        graph1.addNode(new Node_Data(2));
        assertEquals(3, graph1.getMC());
        assertEquals(3, graph1.nodeSize());
        graph1.removeNode(1);
        assertEquals(2, graph1.nodeSize());
    }

    @Test //success //
    void removeEdge() {
        DirectedWeightedGraph graph = new Graph();
        graph.addNode(new Node_Data(0));
        graph.addNode(new Node_Data(1));
        graph.addNode(new Node_Data(2));
        assertEquals(3, graph.getMC());
        assertEquals(3, graph.nodeSize());
        graph.connect(1,2,3);
        graph.connect(0,2,2);
        graph.connect(1,0,4);
        assertEquals(6, graph.getMC());
        assertEquals(3,graph.edgeSize());
    }

    @Test //success //
    void nodeSize() {
        DirectedWeightedGraph graph = new Graph();
        graph.addNode(new Node_Data(0));
        graph.addNode(new Node_Data(1));
        graph.addNode(new Node_Data(2));
        assertEquals(3, graph.nodeSize());
        graph.removeNode(2);
        assertEquals(2, graph.nodeSize());

    }

    @Test // success //
    void edgeSize() {
        DirectedWeightedGraph graph = new Graph();
        NodeData a = new Node_Data(1);
        NodeData a1 = new Node_Data(2);
        NodeData a2 = new Node_Data(3);
        NodeData a3 = new Node_Data(4);
        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);
        graph.addNode(a3);
        graph.connect(1, 2, 6);
        graph.connect(3, 4, 7);
        graph.connect(2, 4, 8);
        assertEquals(3,graph.edgeSize());
        graph.removeEdge(1,2);
        assertEquals(2,graph.edgeSize());
    }

    @Test // success //
    void getMC() {
        DirectedWeightedGraph graph = new Graph();
        NodeData a = new Node_Data(1);
        NodeData a1 = new Node_Data(2);
        NodeData a2 = new Node_Data(3);
        NodeData a3 = new Node_Data(4);
        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);
        graph.addNode(a3);
        graph.connect(1, 2, 6);
        graph.connect(3, 4, 7);
        graph.connect(2, 4, 8);
        assertEquals(7,graph.getMC());
    }
}