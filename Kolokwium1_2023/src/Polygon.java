public class Polygon {
    protected Point[] arrayOfPoints;

    public Point[] getArrayOfPoints() {
        return arrayOfPoints;
    }

    public Polygon(Point[] arrayOfPoints) {
        this.arrayOfPoints = arrayOfPoints;
    }

    public boolean isInside(Point point) {
        int counter = 0;
        double x = 0;
        double d = 0;
        double a = 0;
        double b = 0;
        for (int i = 0; i < arrayOfPoints.length - 1; i++) {

            Point pa = arrayOfPoints[i];
            Point pb = arrayOfPoints[i + 1];
            if (pa.y > pb.y) {
                Point tmpPoint = pa;
                pa = pb;
                pb = tmpPoint;
            }
            if (pa.y < point.y && point.y < pb.y) {
                d = pb.x - pa.x;
                if (d == 0) {
                    x = pa.x;
                } else {
                    a = (pb.y - pa.y) / d;
                    b = pa.y - a * pa.x;
                    x = (point.y - b) / a;
                }
                if (x < point.x) {
                    counter++;
                }
            }

        }
        Point pa = arrayOfPoints[arrayOfPoints.length - 1];
        Point pb = arrayOfPoints[0];
        if (pa.y > pb.y) {
            Point tmpPoint = pa;
            pa = pb;
            pb = tmpPoint;
        }
        if (pa.y < point.y && point.y < pb.y) {
            d = pb.x - pa.x;
            if (d == 0) {
                x = pa.x;
            } else {
                a = (pb.y - pa.y) / d;
                b = pa.y - a * pa.x;
                x = (point.y - b) / a;
            }
            if (x < point.x) {
                counter++;
            }
        }
        return counter % 2 != 0;
    }
}