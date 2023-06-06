import java.util.Locale;

public class Elipse extends Shape{
    public Elipse(Point elipseCenter, double ray, Style style) {
        this.elipseCenter = elipseCenter;
        this.ray = ray;
        this.style = style;
    }

    @Override
    public String toSVG() {
        return String.format(Locale.ENGLISH, "<ellipse cx= %2f cy= %2f rx= %2f ry= %2f style=%s", elipseCenter.x, elipseCenter.y, ray, ray, style.toSvg());
    }
    private Point elipseCenter;
    private double ray;
    private Style style;
}
