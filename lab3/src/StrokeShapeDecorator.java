import java.util.Locale;

public class StrokeShapeDecorator extends ShapeDecorator{
    private String color;
    private double width;
    public StrokeShapeDecorator(Shape shape, String color, double width) {
        super(shape);
        this.color = color;
        this.width = width;
    }
    public String toSvg(String parameters) {
        String output = String.format(Locale.ENGLISH, "\"stroke=\\\"%s\\\" stroke-width=\\\"%f\\\"", color, width);
        return decoratedShape.toSvg(output);
    }
}
