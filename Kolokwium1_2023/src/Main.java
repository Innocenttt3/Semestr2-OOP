import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws notOnLandException, DockCityException {
        //points to define land
        Point test1 = new Point(0.0, 0.0);
        Point test2 = new Point(2.0, 0.0);
        Point test3 = new Point(2.0, 2.0);
        Point test4 = new Point(0.0, 2.0);

        Point[] land = new Point[4];
        land[0] = test1;
        land[1] = test2;
        land[2] = test3;
        land[3] = test4;

        Point centerOfCity = new Point(1.0, 1.0);
        Point centerOfResources = new Point(2.0, 2.0);

        //points to define city
        Point city1 = new Point(5.1, 4.1);
        Point city2 = new Point(1.9, 0.1);
        Point city3 = new Point(1.9, 1.9);
        Point city4 = new Point(0.1, 1.9);

        Point[] city = new Point[4];

        city[0] = city1;
        city[1] = city2;
        city[2] = city3;
        city[3] = city4;


        City lublin = new City(centerOfCity, "Lublin", city);
        Land earthLand = new Land(land);

        earthLand.addCity(lublin);
        lublin.determineDockCity(earthLand);
        Resource woodResource = new Resource(centerOfResources, Resource.Type.Wood);
        Resource coalResource = new Resource(centerOfResources, Resource.Type.Coal);
        Resource fishResource = new Resource(centerOfResources, Resource.Type.Fish);
        List<Resource> lublinResources = new ArrayList<>();
        lublinResources.add(woodResource);
        lublinResources.add(coalResource);
        lublinResources.add(fishResource);
        lublin.addResourceRange(lublinResources, 5);
        for (Resource tmpresource: lublinResources) {
            System.out.print(tmpresource.whatResource + " ");
            System.out.println(tmpresource.centerOnMap);

        }
    }
}