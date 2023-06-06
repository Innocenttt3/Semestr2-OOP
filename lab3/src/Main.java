import java.nio.channels.ShutdownChannelGroupException;

public class Main {
    public static void main(String[] args) {

        Vec2 a = new Vec2(1, 1);
        Vec2 b = new Vec2(2, 2);
        Vec2 c = new Vec2(3, 3);
        Vec2 d = new Vec2(4, 4);

        Vec2[] arrOfPoints = new Vec2[4];
        arrOfPoints[0] = a;
        arrOfPoints[1] = a;
        arrOfPoints[2] = a;
        arrOfPoints[3] = a;

        Shape poly = new Polygon(arrOfPoints);
        Shape elip = new Ellipse(a, 10, 10);
        SolidFillShapeDecorator example = new SolidFillShapeDecorator(poly, "black");
        SolidFillShapeDecorator example2 = new SolidFillShapeDecorator(elip, "black");
        StrokeShapeDecorator example3 = new StrokeShapeDecorator(poly, "black", 10);
        SolidFillShapeDecorator polyDecorator = new SolidFillShapeDecorator(poly, "#000000");
        SolidFillShapeDecorator ellipseDecorator = new SolidFillShapeDecorator(elip, "#FFFFFF");
    }
}
