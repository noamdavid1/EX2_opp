package api;
import java.util.*;

public class Graph implements DirectedWeightedGraph{
    private HashMap<Integer, NodeData> NodeM;
    private HashMap<Integer,HashMap<Integer,EdgeData>>outEdgeM;
    private HashMap<Integer,HashMap<Integer,EdgeData>>inEdgeM;
    private int e=0,mc=0;

    public Graph(){
        this.NodeM=new HashMap<>();
        this.outEdgeM= new HashMap<>();
        this.inEdgeM= new HashMap<>();
    }

    public Graph(Graph g){
        this.NodeM = g.NodeM;
        this.outEdgeM = g.outEdgeM;
        this.inEdgeM = g.inEdgeM;
       for(Map.Entry<Integer,NodeData> entry: g.NodeM.entrySet())
        {NodeM.put(entry.getKey(), entry.getValue());}
        for(Map.Entry<Integer,HashMap<Integer,EdgeData>> first_obj : g.outEdgeM.entrySet())
        {
            for(Map.Entry<Integer,EdgeData> second_obj : first_obj.getValue().entrySet())
            {
                outEdgeM.get(first_obj.getKey()).put(second_obj.getKey(), second_obj.getValue());
            }
        }
        for(Map.Entry<Integer,HashMap<Integer,EdgeData>> first_obj : g.inEdgeM.entrySet()) {
            for (Map.Entry<Integer, EdgeData> second_obj : first_obj.getValue().entrySet()) {
                inEdgeM.get(first_obj.getKey()).put(second_obj.getKey(), second_obj.getValue());
            }
        }this.e=g.e;
    }

    @Override
    public NodeData getNode(int key) {
        return NodeM.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return outEdgeM.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
    if(!NodeM.containsKey(n.getKey())){
    Node_Data temp= new Node_Data((Node_Data)n);
    this.NodeM.put(n.getKey(),temp);
    this.outEdgeM.put(n.getKey(),new HashMap<Integer,EdgeData>());
    this.inEdgeM.put(n.getKey(),new HashMap<Integer,EdgeData>());
    mc++;
    }
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (!(NodeM.containsKey(src) && NodeM.containsKey(dest) && src != dest)) return;
        if (w < 0) return;

        if (!outEdgeM.get(src).containsKey(dest))
            e++;
        Edge_data edge = new Edge_data(src, dest, w);
        outEdgeM.get(src).put(dest, edge);
        inEdgeM.get(dest).put(src, edge);
        mc++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return this.NodeM.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        Collection<EdgeData> temp = new ArrayList<EdgeData>() ;
        Iterator it = this.outEdgeM.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            HashMap<Integer, EdgeData> hashTemp = (HashMap<Integer, EdgeData>) pair.getValue();
            Iterator<EdgeData> it1 = hashTemp.values().iterator();
            while (it1.hasNext()) {
                EdgeData edge = it1.next();
                temp.add(edge);
            }
        }return temp.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return this.outEdgeM.get(node_id).values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        if(!this.NodeM.containsKey(key)) {
            return null;
        }
            else{
            int sizeEdgeOut = this.outEdgeM.get(key).size();
            int sizeEdgeIn = this.inEdgeM.get(key).size();
            this.e -= sizeEdgeOut;
            this.e -= sizeEdgeIn;
            this.mc += sizeEdgeOut + sizeEdgeIn;

            this.outEdgeM.get(key).clear();
            this.inEdgeM.get(key).clear();
            this.mc++;
            return this.NodeM.remove(key);
        }
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (getNode(src) != null && getNode(dest) != null && src != dest) {
            inEdgeM.get(dest).remove(src);
            e--;
            return outEdgeM.get(src).remove(dest);

        }
        return null;
    }

    @Override
    public int nodeSize() {
        return NodeM.size();
    }

    @Override
    public int edgeSize() {
        return e;
    }

    @Override
    public int getMC() {
        return mc;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + NodeM +
                ", edgesOut=" + outEdgeM +
                ", edgesIn=" + inEdgeM +
                ", NodeNum=" + this.nodeSize() +
                ", EdgeNum=" + e +
                ", Mc=" + mc +
                '}';
    }
}
