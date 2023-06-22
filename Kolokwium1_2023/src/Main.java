public class Main {
    public static void main(String[] args) {

        Point test1 = new Point(0.0, 0.0);
        Point test2 = new Point(2.0, 0.0);
        Point test3 = new Point(0.0, 2.0);
        Point test4 = new Point(2.0, 2.0);
        Point pointIN = new Point(1.0, 1.0);

        Point[] randomArray = new Point[4];
        randomArray[0] = test1;
        randomArray[1] = test2;
        randomArray[2] = test3;
        randomArray[3] = test4;
        Polygon polygon = new Polygon(randomArray);
        System.out.println(polygon.isInside(pointIN));
    }
}