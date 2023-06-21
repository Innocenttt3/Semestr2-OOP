public class Polygon {
    private Point[] arrayOfPoints;
    public Polygon(Point[] arrayOfPoints) {
        this.arrayOfPoints = arrayOfPoints;
    }
    public boolean isInside(Point point)  {
        double d = 0;
        double b = 0;
        double x = 0;
        double a = 0;
        int counter = 0;
        int amountOfPoints = 0;
        for (Point tmpPoint: arrayOfPoints) {
            amountOfPoints++;
        }
       for (int i = 0; i < amountOfPoints - 1; i++) {
           double pay = arrayOfPoints[i].y;
           double pby = arrayOfPoints[i + 1].y;
           double pax = arrayOfPoints[i].y;
           double pbx = arrayOfPoints[i + 1].y;

           if(pay > pby) {
               double tmpValueYa = pay;
               pay = pby;
               pby = tmpValueYa;
           }
           if (pay < point.y  && point.y < pby){
               d = pbx - pax;
               if (d == 0) {
                   x = pax;
               } else {
                   a = (pby - pay) / d;
                   b = (pay - a * pax);
                   x = (point.y - b) / a;
                   if (x < point.x) {
                       counter++;
                   }
               }
           }
       }
        return counter % 2 != 0;
    }
}
