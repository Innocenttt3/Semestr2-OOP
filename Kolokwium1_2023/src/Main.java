public class Main {
    public static void main(String[] args) {

        Point test1 = new Point(1.1, 2.2);
        Point test2 = new Point(1.1, 2.2);
        Point test3 = new Point(1.1, 2.2);
        Point test4 = new Point(1.1, 2.2);

        Point[] randomArray = new Point[4];
        randomArray[0] = test1;
        randomArray[1] = test2;
        randomArray[2] = test3;
        randomArray[3] = test4;

        Polygon testPolygon = new Polygon(randomArray);
        testPolygon.isInside(test1);
    }
}