import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class City extends Polygon {
    public final Point center;
    private String name;

    public boolean isDockCity() {
        return dockCity;
    }

    public Set<Resource> resourceSet;

    private boolean dockCity;

    public void determineDockCity(Land land) {
        Point[] tmpArray = super.arrayOfPoints;
        for (Point tmp2 : tmpArray) {
            if (!land.isInside(tmp2)) {
                this.dockCity = true;
                System.out.println("jest miastem portowym");
                return;
            }
        }
        this.dockCity = false;
        System.out.println("nie jest miastem portowym");
    }

    public String getName() {
        return name;
    }

    public City(Point center, String name, Point[] twoPoints) {
        super(twoPoints);
        this.center = center;
        this.name = name;
        resourceSet = new HashSet<>();
    }

    public void addResourceRange(List<Resource> resources, double range) throws DockCityException {
        for (Resource tmpResource : resources) {
            if (range >= Point.calculateDistance(tmpResource.centerOnMap, center)) {
                if(!this.isDockCity() && tmpResource.whatResource.equals(Resource.Type.Fish)){
                    throw new DockCityException(this.getName() + " nie jest miastem portowym - brak dostepu do ryb");
                }
                resourceSet.add(tmpResource);
            }
        }
    }
}
