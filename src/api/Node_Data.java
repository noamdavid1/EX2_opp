package api;

public class Node_Data implements NodeData{

    private int key;
    private GeoLocation location;
    private double Weight;
    private String info;
    private int tag;

    public Node_Data(int key){
        this.key=key;
        this.location=null;
        this.Weight=0;
        this.tag=0;
        this.info="";
    }

    public Node_Data(Node_Data n){
        this.key= n.key;
        this.location=n.location;
        this.tag=n.tag;
        this.info=n.info;
        this.Weight=n.Weight;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
    this.location=p;
    }

    @Override
    public double getWeight() {
        return this.Weight;
    }

    @Override
    public void setWeight(double w) {
    this.Weight=w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
    this.info=s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
    this.tag=t;
    }

    @Override
    public String toString() {
        return "Node_Data{" +
                "tag=" + tag +
                ",key=" + key +
                ", weight=" + Weight +
                ", _info='" +info + '\'' +
                ", location=" + location +
                '}';
    }
}
