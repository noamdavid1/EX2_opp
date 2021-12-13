package api.test;

import api.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraph_AlgorithmsTest {

     DirectedWeightedGraphAlgorithms graph_algorithms = new Graph_Algorithm();
    // DirectedWeightedGraphAlgorithms graph1 = DirectedWeightedGraph_Algorithms;
     DirectedWeightedGraph  graph = new Graph();

    @Test // success //
    void init() {
        graph_algorithms.init(graph);
        assertEquals(graph,graph_algorithms.getGraph());
    }

    @Test // success //
    void getGraph() {
        graph_algorithms.init(graph);
        DirectedWeightedGraph g = graph_algorithms.getGraph();
        assertEquals(g, graph);    }

    @Test // success //
    void copy() {
        graph_algorithms.init(graph);
        DirectedWeightedGraph test = graph_algorithms.copy();
        assertNotSame(graph,test); // check whether two graphs object do not refer to the same object.
    }

    @Test // success //
    void isConnected() {
        //graph with two nodes and two edges
        DirectedWeightedGraph g = new Graph();
        NodeData node = new Node_Data(1);
        g.addNode(node);
        node = new Node_Data(2);
        g.addNode(node);
        g.connect(1, 2, 2.25);
        g.connect(2, 1, 2);
        graph_algorithms.init(g);
        //assertTrue(graph_algorithms.isConnected());

        g.removeEdge(2, 1);
        assertFalse(graph_algorithms.isConnected());
        g = new Graph();
        graph_algorithms.init(g);
        assertTrue(graph_algorithms.isConnected());
    }

    @Test // SUCCESS //
    void shortestPathDist() {
        DirectedWeightedGraphAlgorithms algo =new Graph_Algorithm();
        DirectedWeightedGraph graph = new Graph();
        algo.init(graph);
        NodeData a = new Node_Data(0);
        NodeData a1 = new Node_Data(1);
        NodeData a2 = new Node_Data(2);
        NodeData a3 = new Node_Data(3);
        NodeData a4 = new Node_Data(4);
        NodeData a5 = new Node_Data(5);
        graph.addNode(a);
        graph.addNode(a1);
        graph.addNode(a2);
        graph.addNode(a3);
        graph.addNode(a4);
        graph.addNode(a5);
        graph.connect(0,1,1);
        graph.connect(1,0,1);
        graph.connect(1,2,1);
        graph.connect(2,1,1);
        graph.connect(2,3,2);
        graph.connect(3,2,2);
        graph.connect(3,4,2);
        graph.connect(4,3,2);
        graph.connect(0,4,4);
        graph.connect(4,0,4);
        graph.connect(5,1,6);
        graph.connect(1,5,6);
        graph.connect(5,2,6);
        graph.connect(2,5,6);

        assertEquals(3, algo.shortestPathDist(1,3));
        assertEquals(4, algo.shortestPathDist(4,2));
        assertEquals(8, algo.shortestPathDist(5,3));
        assertEquals(2, algo.shortestPathDist(4,3));
    }

    @Test // success //
    void shortestPath() {
        DirectedWeightedGraph g = new Graph();

        g.addNode(new Node_Data(0));
        g.addNode(new Node_Data(1));
        g.addNode(new Node_Data(2));
        g.addNode(new Node_Data(4));
        g.addNode(new Node_Data(5));
        g.addNode(new Node_Data(6));

        g.connect(0,1,2);
        g.connect(0,5,3);
        g.connect(1,2,1);
        g.connect(2,0,5);
        g.connect(2,6,2);
        g.connect(4,5,8);
        g.connect(5,6,1);
        g.connect(6,0,1);
        g.connect(6,4,3);

        graph_algorithms.init(g);
        assertEquals(3, graph_algorithms.shortestPath(1,6).size());
        assertEquals(2, graph_algorithms.shortestPath(0,5).size());
        assertEquals(3, graph_algorithms.shortestPath(0,2).size());
        assertEquals(null, graph_algorithms.shortestPath(1,11));
        assertEquals(null, graph_algorithms.shortestPath(12,12));
        assertEquals(4, graph_algorithms.shortestPath(6,2).size());

    }

    @Test // success //
    void center() {
        NodeData v0 = new Node_Data(0);
        GeoLocation G0 = new Geo_location(1,2);
        v0.setLocation(G0);
        NodeData v1 = new Node_Data( 1);
        GeoLocation G1 = new Geo_location(4,5);
        v1.setLocation(G1);
        NodeData v2 = new Node_Data( 2);
        GeoLocation G2 = new Geo_location(7,8);
        v2.setLocation(G2);
        NodeData v3 = new Node_Data( 3);
        GeoLocation G3 = new Geo_location(1,2);
        v3.setLocation(G3);
        NodeData v4 = new Node_Data( 4);
        GeoLocation G4 = new Geo_location(4,5);
        v4.setLocation(G4);
        DirectedWeightedGraph graph = new Graph();
        graph.addNode(v0);
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);
        graph.addNode(v4);
        graph.connect(0, 1, 1);
        graph.connect(1, 2, 2);
        graph.connect(2, 3, 10);
        graph.connect(2, 4, 4);
        graph.connect(3, 4, 4);
        graph.connect(4, 3, 2);
        graph.connect(4, 0, 5);
        graph.connect(4, 2, 5);
        graph.connect(3, 2, 1);
        DirectedWeightedGraphAlgorithms graphAlgo=new Graph_Algorithm();
        graphAlgo.init(graph);
        assertEquals(4,graphAlgo.center().getKey());
        /*
        DirectedWeightedGraphAlgorithms graphAlgorithmsG1=new DirectedWeightedGraph_Algorithms();
        DirectedWeightedGraphAlgorithms graphAlgorithmsG2=new DirectedWeightedGraph_Algorithms();
        DirectedWeightedGraphAlgorithms graphAlgorithmsG3=new DirectedWeightedGraph_Algorithms();

        assertEquals(8,graphAlgorithmsG1.center().getKey());
        assertEquals(0,graphAlgorithmsG2.center().getKey());
        assertEquals(40,graphAlgorithmsG3.center().getKey());
         */
    }

    @Test
    void tsp() {
        DirectedWeightedGraph g1 = new Graph();
        NodeData a = new Node_Data(1);
        NodeData b = new Node_Data(2);
        NodeData c = new Node_Data(3);
        NodeData d = new Node_Data(4);
        g1.addNode(a);
        g1.addNode(b);
        g1.addNode(c);
        g1.addNode(d);
        g1.connect(a.getKey(), b.getKey(), 3);
        g1.connect(a.getKey(), c.getKey(), 2);
        g1.connect(c.getKey(), b.getKey(), 2);
        g1.connect(c.getKey(), a.getKey(), 3);
        g1.connect(b.getKey(), d.getKey(), 4);
        g1.connect(d.getKey(), c.getKey(), 9);
        g1.connect(d.getKey(), b.getKey(), 4);
        g1.connect(b.getKey(), c.getKey(), 2);
        List<NodeData> list = new ArrayList<>();
        list.add(d);
        list.add(b);
        list.add(c);
        list.add(a);
        DirectedWeightedGraphAlgorithms testgraph = new Graph_Algorithm();
        testgraph.init(g1);
        list = testgraph.tsp(list);
        List<NodeData> list2 = List.of(new NodeData[]{a, c, b, d});
        assertEquals(list,list2);

    }

    @Test // success //
    void save() {
        String file_name = "newGraph.bin";
        try {
            graph_algorithms.save(file_name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void load() {
        DirectedWeightedGraphAlgorithms testgraph1=new Graph_Algorithm();
        DirectedWeightedGraphAlgorithms testgraph2=new Graph_Algorithm();
        DirectedWeightedGraphAlgorithms testgraph3=new Graph_Algorithm();

        assertEquals(true,testgraph1.load("data/G1.json"));
        assertEquals(true,testgraph2.load("data/G2.json"));
        assertEquals(true,testgraph3.load("data/G3.json"));
        assertEquals(false,testgraph3.load("wrong_json_location"));
    }
}