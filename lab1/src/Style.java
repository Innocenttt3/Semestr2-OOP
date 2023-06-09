public class Style {

    public final String fillColor;
    public final String strokeColor;
    public final double strokeWidth;

    public Style(String fillColor, String strokeColor, double strokeWidth) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    public String toSvg() {
        return String.format("Style: fillColor = %s, stokeColor = %s, strokeWidth = %2f\"", this.fillColor, this.strokeColor, this.strokeWidth);
    }

}
