public class City extends Polygon {
    public final Point center;
    private String name;

    public String getName() {
        return name;
    }

    public City(Point center, String name, Point[] twoPoints) {
        super(twoPoints);
        this.center = center;
        this.name = name;
    }
}
