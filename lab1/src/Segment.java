public class Segment {

    private Point A;
    private Point B;

    public Point getA() {
        return A;
    }
    public Point getB() {
        return B;
    }
    public Segment(Point a, Point b){
        this.A = a;
        this.B = b;
    }
    public double length(){
        return Math.sqrt(Math.pow(this.A.x - this.B.x, 2) + Math.pow(this.A.y - this.B.y,2));
    }
    public String toSVG(){
        return String.format("<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />", this.A.x, this.B.x, this.A.y, this.B.y);

    }


//  <line x1="0" y1="0" x2="200" y2="200" style="stroke:rgb(255,0,0);stroke-width:2" />
}
