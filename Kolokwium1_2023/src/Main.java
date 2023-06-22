public class Main {
    public static void main(String[] args) throws notOnLandException {

        Point test1 = new Point(0.0, 0.0);
        Point test2 = new Point(2.0, 0.0);
        Point test3 = new Point(2.0, 2.0);
        Point test4 = new Point(0.0, 2.0);

        Point center = new Point(6.0, 1.0);


        Point[] earth = new Point[4];
        earth[0] = test1;
        earth[1] = test2;
        earth[2] = test3;
        earth[3] = test4;
        City testCity = new City(center, "Lublin", earth);
        Land earthLand = new Land(earth);

        earthLand.addCity(testCity);




    }
}