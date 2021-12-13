package api;

public class Geo_location implements GeoLocation{
    private double x;
    private double y;
    private double z;

    public Geo_location(double x, double y){
        this.x=x;
        this.y=y;
        this.z=0;
    }
    public Geo_location(double x, double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Geo_location(Geo_location lo){
        this.x= lo.x();
        this.y=lo.y();
        this.z= lo.z();
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        return Math.sqrt(Math.pow(x - g.x(), 2) + Math.pow(y - g.y(), 2) + Math.pow(z - g.z(), 2));
    }

    public double distance2d(GeoLocation g) {
        double dis_y=(Math.pow((this.y-g.y()),2));
        double dis_x=Math.pow((this.x-g.x()),2);
        return Math.sqrt(dis_y+dis_x);
    }

    public String toString() {
        return x + "," + y + "," + z ;
    }
}
