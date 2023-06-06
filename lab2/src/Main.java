import java.nio.channels.ShutdownChannelGroupException;

public class Main {
    public static void main(String[] args) {
        Style style = new Style("red", "blue", 100);
        Point a = new Point(1, 1);
        Elipse elipse = new Elipse(a, 10, style);
        System.out.println(elipse.toSVG());
//        Point b = new Point(2, 2);
//        Point c = new Point(3, 3);
//        Point[] pointsArr;
//        pointsArr = new Point[2];
//        pointsArr[0] = a;
//        pointsArr[1] = b;
//
//        Point[] pointsArr2;
//        pointsArr2 = new Point[3];
//        pointsArr2[0] = a;
//        pointsArr2[1] = b;
//        pointsArr2[2] = c;
//        Style style1 = new Style("white", "black", 100);
//        Polygon polygon = new Polygon(2);
//        polygon.setPointsArr(pointsArr);
//        Polygon polygon2 = new Polygon( 3, style);
//        polygon2.setPointsArr(pointsArr2);
//        SvgScene scene = new SvgScene();
//        scene.addPolygon(polygon);
//        scene.addPolygon(polygon2);
//        scene.save("/Users/kamilgolawski/Nauka/Programowanie");

    }

}