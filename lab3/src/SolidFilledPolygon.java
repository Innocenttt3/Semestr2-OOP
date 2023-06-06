import java.util.Locale;

public class SolidFilledPolygon extends Polygon{
    private String color;

    public SolidFilledPolygon(String color, Vec2[] point) {
        super(point);
        this.color = color;
    }
    @Override
    public String toSvg(String parameters) {
            String output = String.format(Locale.ENGLISH, "fill=\"%s\" %s", parameters, color);
            return output;
    }
}
