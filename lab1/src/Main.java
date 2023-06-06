public class Main {
    public static void main(String[] args) {

        Point[] arr1;
        arr1 = new Point[2];
        Point a = new Point(1, 1);
        Point b = new Point(2, 2);
        arr1[1] = a;
        arr1[0] = b;
        Segment segment = new Segment(a, b);
        Polygon polygon = new Polygon(2);
        polygon.setPointsArr(arr1);

        System.out.println(polygon.toSVG());

    }

}