package api;

public class Edge_data implements EdgeData{
    private int Src;
    private int Dest;
    private double Weight;
    private String Info;
    private int tag;

    public Edge_data(int src,int dest,double weight){
        this.Src=src;
        this.Dest=dest;
        this.Weight=weight;
    }
    public Edge_data(Edge_data e){
        this.Src=e.Src;
        this.Dest=e.Dest;
        this.Weight=e.Weight;
        this.Info=e.Info;
        this.tag=e.tag;
    }

    @Override
    public int getSrc() {
        return this.Src;
    }

    @Override
    public int getDest() {
        return this.Dest;
    }

    @Override
    public double getWeight() {
        return this.Weight;
    }

    @Override
    public String getInfo() {
        return this.Info;
    }

    @Override
    public void setInfo(String s) {
    this.Info=s;
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
        return "Edge_Data{" +
                "_src=" + Src +
                ", _dest=" + Dest +
                ", _tag=" + tag +
                ", _weight=" + Weight +
                ", _info='" + Info + '\'' +
                '}';
    }
}
