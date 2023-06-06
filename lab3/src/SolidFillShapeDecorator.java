import java.util.Locale;

public class SolidFillShapeDecorator extends ShapeDecorator{
    private String color;

    public SolidFillShapeDecorator(Shape decoratorShape, String color) {
        super(decoratorShape);
        this.color = color;
    }
    @Override
    public String toSvg(String parameters) {
        String output = String.format(Locale.ENGLISH, "fill=\\\"%s\\\" %s \"", color, parameters);
                return decoratedShape.toSvg(output);
    }
}
