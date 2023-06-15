package pl.umcs.powtorzeniezadanie;

public record Dot(
        double x,
        double y,
        double radius,
        String color
) {
    public static String toMessage(double x, double y, double radius, String color) {
        return String.format("%f %f %f %s", x, y, radius, color);
    }

    public static Dot fromMessage(String message) {
        String[] elements = message.split(" ");
        double x = Integer.parseInt(elements[0]);
        double y = Integer.parseInt(elements[1]);
        double radius = Integer.parseInt(elements[2]);
        String color = elements[3];

        return new Dot(x, y, radius, color);
    }
}
