public class Point {
    public final double x;
    public final double y;

    public Point(double firstNumber, double secondNumber) {
        this.x = firstNumber;
        this.y = secondNumber;
    }

    public static double calculateDistance(Point point1, Point point2) {
        double distance = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
        return distance;
    }


}
