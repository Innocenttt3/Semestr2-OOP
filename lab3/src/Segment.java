public class Segment {

    private Vec2 A;
    private Vec2 B;

    public Vec2 getA() {
        return A;
    }
    public Vec2 getB() {
        return B;
    }
    public Segment(Vec2 a, Vec2 b){
        this.A = a;
        this.B = b;
    }
    public double length(){
        return Math.sqrt(Math.pow(this.A.x - this.B.x, 2) + Math.pow(this.A.y - this.B.y,2));
    }
    public String toSVG(){
        return String.format("<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />", this.A.x, this.B.x, this.A.y, this.B.y);

    }
    public static Segment[] perpendicular(Segment segment, Vec2 point) {
        double a;
        a = (segment.getA().y - segment.getB().y);
        a /= (segment.getA().x - segment.getB().x);
        double b;
        a = -1 / a;
        b = point.y - a * point.x;

        double x0 = point.x;
        double y0 = point.y;
        double r = segment.length();

        double root = Math.sqrt(-y0 * y0 + (2 * a * x0 + 2 * b) * y0 - a * a * x0 * x0 - 2 * a * b * x0 + (a * a + 1) * r * r - b * b);
        double x1 = -(root - a * y0 - x0 + a * b) / (a * a + 1);
        double y1 = -(a * root - a * a * y0 - a * x0 - b) / (a * a + 1);
        double x2 = (root + a * y0 + x0 - a * b) / (a * a + 1);
        double y2 = (a * root + a * a * y0 + a * x0 + b) / (a * a + 1);

        return new Segment[]{new Segment(point, new Vec2(x1, y1)),
                new Segment(point, new Vec2(x2, y2))};
    }

    public static Segment[] perpendicular(Segment segment, Vec2 point, int length) {
        double a;
        a = (segment.getA().y - segment.getB().y);
        a /= (segment.getA().x - segment.getB().x);
        double b;
        a = -1 / a;
        b = point.y - a * point.x;

        double x0 = point.x;
        double y0 = point.y;
        double r = segment.length();

        double root = Math.sqrt(-y0 * y0 + (2 * a * x0 + 2 * b) * y0 - a * a * x0 * x0 - 2 * a * b * x0 + (a * a + 1) * r * r - b * b);
        double x1 = -(root - a * y0 - x0 + a * b) / (a * a + 1);
        double y1 = -(a * root - a * a * y0 - a * x0 - b) / (a * a + 1);
        double x2 = (root + a * y0 + x0 - a * b) / (a * a + 1);
        double y2 = (a * root + a * a * y0 + a * x0 + b) / (a * a + 1);

        return new Segment[]{new Segment(point, new Vec2(x1, y1)),
                new Segment(point, new Vec2(x2, y2))};
    }

}
