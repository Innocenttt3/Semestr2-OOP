import java.util.Locale;

public class Polygon {

    private Point[] arr;

    public Polygon(int pointsAmount){
        arr = new Point[pointsAmount];
    }

    public void setPoint(int index, Point point){
        arr[index] = point;
    }
    public void setPointsArr(Point pointsArr[]){
        this.arr = pointsArr;
    }
    public String toSVG(){
        String pointsInfo = "";
        for (Point point: arr){
            pointsInfo += point.x + "," + point.y + " ";
        }
        return String.format(Locale.ENGLISH,"<polygon points= %s", pointsInfo);
    }


}
