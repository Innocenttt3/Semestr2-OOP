import java.util.ArrayList;
import java.util.List;

public class Land extends Polygon {

    private List<City> listOfCities;
    public Land(Point[] arrayOfPoints) {
        super(arrayOfPoints);
        this.listOfCities = new ArrayList<>();
    }
    public void addCity(City city) throws notOnLandException {
        Polygon tmpPolygon = new Polygon(super.arrayOfPoints);
        if (!tmpPolygon.isInside(city.center)) {
            throw new RuntimeException(city.getName() + " nie moze byc na wodzie");
        }
        listOfCities.add(city);
    }
}
