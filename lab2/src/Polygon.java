import java.util.Locale;

public class Polygon extends Shape {

    private Point[] arr;
    Style style;

    public Polygon(int pointAmount){
        arr = new Point[pointAmount];
        style = new Style("transparent", "black", 1);
    }
    public Polygon(int pointsAmount, Style style){
        arr = new Point[pointsAmount];
        this.style = style;
    }

    public void setPoint(int index, Point point){
        arr[index] = point;
    }
    public void setPointsArr(Point pointsArr[]){
        this.arr = pointsArr;
    }
    public String toSVG(){
        String pointsInfo = "";
        String styleInfo = style.toSvg();
        for (Point point: arr){
            pointsInfo += point.x + "," + point.y + " ";
        }
        return String.format(Locale.ENGLISH,"<polygon points= %s\n style:%s >\n", pointsInfo, styleInfo);
    }

    public static Polygon Square(Segment segment, Style style){
        Point point = new Point((segment.getA().x+segment.getB().x)/2, (segment.getA().y+segment.getB().y)/2);
        Segment segmentprostopadly = Segment.perpendicular(segment,point)[0];
        Point[] arr = {segment.getA(),segmentprostopadly.getA(),
                segment.getB(),segmentprostopadly.getB()};
        Polygon square = new Polygon(4);
        square.setPointsArr(arr);
        return square;
    }

}
