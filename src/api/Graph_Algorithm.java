package api;

import java.util.*;
//import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;

public class Graph_Algorithm implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    public static double infinity = Double.POSITIVE_INFINITY;

    public Graph_Algorithm() {
        graph = new Graph();
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return new Graph((Graph) this.graph);
    }

    @Override
    public boolean isConnected() {
        boolean bool=false;
        ArrayList<NodeData>ans= new ArrayList<NodeData>();
        Iterator<NodeData> iter = graph.nodeIter();
        while (iter.hasNext()){
            NodeData n = iter.next();
            Iterator it =graph.edgeIter(n.getKey());
            boolean con =connecting(n,it,ans);
            if (con ==false) {
                bool=con;
                return bool;
            }else{
                bool=true;
            }
        }
        return bool;
    }


    public boolean connecting(NodeData n1, Iterator it, ArrayList<NodeData> arr) {
        if (arr.size() == graph.nodeSize()) {
            return true;
        }
        while (it.hasNext()) {
            EdgeData eg = (EdgeData) it.next();
            int nd = eg.getDest();
            NodeData n2 = graph.getNode(nd);
            if (arr.contains(n2)) {
                continue;
            }
            arr.add(n2);
            Iterator a = this.graph.edgeIter(nd);
            connecting(n2, a, arr);
            if (arr.size() == graph.nodeSize()) {
                return true;
            }
        }
        return false;
    }


    @Override
    public double shortestPathDist(int src, int dest) {
        if(this.graph.getNode(src)==null||this.graph.getNode(dest)==null||src == dest){
            return -1;
        }
        Iterator<NodeData> iterator = this.graph.nodeIter();
        while (iterator.hasNext()) {
            NodeData node = iterator.next();
            node.setWeight(infinity);
            node.setTag(0);
            node.setInfo("-1");
        }
        Node_Data _src_= (Node_Data) this.graph.getNode(src);
        _src_.setWeight(0);

        PriorityQueue<NodeData> q = new PriorityQueue<NodeData>(new Comparator<NodeData>() {
            public int compare(NodeData n1, NodeData n2) {
                if(n1.getWeight()<n2.getWeight()) {
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        q.add(_src_);
        this.dijk_q(q);

        NodeData _dest_ =  this.graph.getNode(dest);
        if(_dest_.getWeight()==infinity){
            return -1;
        }return _dest_.getWeight();
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if(this.graph.getNode(src)==null||this.graph.getNode(dest)==null||src == dest){
            return null;
        }
        Iterator<NodeData> iterator = this.graph.nodeIter();
        while (iterator.hasNext()) {
            NodeData node = iterator.next();
            node.setWeight(infinity);
            node.setTag(0);
            node.setInfo("-1");
        }
        NodeData _src_ =  this.graph.getNode(src);
        _src_.setWeight(0);

        PriorityQueue<NodeData> q = new PriorityQueue<NodeData>(new Comparator<NodeData>() {
            public int compare(NodeData n1, NodeData n2) {
                if(n1.getWeight()<n2.getWeight()) {
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        q.add(_src_);
        this.dijk_q(q);

        NodeData _dest_ =  this.graph.getNode(dest);
        if(_dest_.getWeight()==infinity){
            return null;
        }
        List<NodeData> shortpath = new ArrayList<NodeData>();
        shortpath.add(_dest_);
        String prev = _dest_.getInfo();
        while(!prev.equals("-1")){
            NodeData node = this.graph.getNode(Integer.valueOf(prev)) ;
            shortpath.add(node);
            prev = node.getInfo();
        }
        Collections.reverse(shortpath);
        return shortpath;
    }

    private void dijk_q(PriorityQueue queue){
        while(!queue.isEmpty()){
            NodeData ver = (NodeData) queue.poll();
            Iterator<EdgeData> iter = this.graph.edgeIter(ver.getKey());
            while (iter.hasNext()){
                EdgeData edge = iter.next();
                NodeData ver2 = this.graph.getNode(edge.getDest());
                double w=ver.getWeight()+edge.getWeight();
                if(w<ver2.getWeight()&&ver.getTag()!=1){
                    ver2.setWeight(w);
                    ver2.setInfo(String.valueOf(ver.getKey()));
                    queue.add(ver2);
                }
            }
            ver.setTag(1);
        }
    }


    @Override
    public NodeData center() {
        if (this.isConnected() == false) {
            return null;
        }
        NodeData[] arr = new NodeData[this.graph.nodeSize()];
        Iterator<NodeData> iter = this.graph.nodeIter();
        int index=0;
        double sum = 0;

       int max=Integer.MAX_VALUE;
        NodeData center = null;
        while (iter.hasNext() && index < arr.length) {
            NodeData node = iter.next();
            arr[index] = node;
            index++;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j) j++;
                sum += shortestPathDist(i, j);
            }
            if (sum < max) {
                center = arr[i];
                max = (int)sum;
                sum = 0;
            }sum=0;
        }
        return center;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData> tempC = new ArrayList<>();
        List<NodeData> path = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            tempC.add(cities.get(i)); //copying the list to a new one
        }
        int j = 0;
        NodeData node_path = tempC.get(j);
        while (!tempC.isEmpty()) {
            path.add(node_path); //adding the nodes to the path.
            j = minWeight(tempC, node_path); //sending to a function that returns the next node in the path.
            tempC.remove(node_path); //remove the node that we already have been at.
            node_path = tempC.get(j);
        }
        return path; //the list that represents the path.
    }

    private int minWeight(List<NodeData> cities, NodeData node) {
        double w = Double.MAX_VALUE;
        EdgeData e;
        int index = 0;
        for (NodeData n : cities) {
            if (n.equals(node)) continue;
            e = this.graph.getEdge(node.getKey(), n.getKey());
            if (e == null) continue;
            if (e.getWeight() < w) {
                w = e.getWeight();
                index = n.getKey();
            }
        }return index;
    }


    @Override
    public boolean save(String file) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
            FileOutputStream out= new FileOutputStream(file);
            JsonWriter write = new JsonWriter(new OutputStreamWriter(out));

            List<JsonObject> nodes=new ArrayList<>();
            List<JsonObject> edges=new ArrayList<>();
            Iterator<NodeData> iter = this.graph.nodeIter();
            while(iter.hasNext()){
                NodeData node = iter.next();

                JsonObject ver=new JsonObject();
                ver.addProperty("pos",node.getLocation().toString());
                ver.addProperty("id",node.getKey());
                nodes.add(ver);

                Iterator<EdgeData> iterE = this.graph.edgeIter(node.getKey());
                while (iterE.hasNext()){
                    EdgeData edge = iterE.next();
                    JsonObject edgeJSON=new JsonObject();
                    edgeJSON.addProperty("src",edge.getSrc());
                    edgeJSON.addProperty("w",edge.getWeight());
                    edgeJSON.addProperty("dest",edge.getDest());
                    edges.add(edgeJSON);
                }
            }

            JsonObject _graph =new JsonObject ();
            _graph.add("Edges", gson.toJsonTree(edges.toArray()));
            _graph.add("Nodes",  gson.toJsonTree(nodes.toArray()));

            gson.toJson(_graph,write);
            write.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public boolean load(String file) {
        try {
            FileInputStream input = new FileInputStream(file);
            JsonReader read = new JsonReader(new InputStreamReader(input));
            JsonObject elements = JsonParser.parseReader(read).getAsJsonObject();

            Graph _g = new Graph();

            for(JsonElement i: elements.getAsJsonArray("Nodes")) {
                int json = i.getAsJsonObject().get("id").getAsInt();
                NodeData node=new Node_Data(json);
                String[] str= i.getAsJsonObject().get("pos").getAsString().split(",");
                node.setLocation(new Geo_location(Double.parseDouble(str[0]),Double.parseDouble(str[1]),Double.parseDouble(str[2])));
                _g.addNode(node);
            }for(JsonElement i: elements.getAsJsonArray("Edges")) {
                _g.connect(i.getAsJsonObject().get("src").getAsInt(), i.getAsJsonObject().get("dest").getAsInt(), i.getAsJsonObject().get("w").getAsDouble());
            }
            init(_g);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
