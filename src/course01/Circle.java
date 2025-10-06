package course01;

public class Circle implements Shape{
    private final double r;
    public Circle(double r) { this.r = r; }
    public double area() { return Math.PI * r * r; }
    public double perimeter() { return 2 * Math.PI * r; }
    public String name() { return "Circle(r=" + r + ")"; }
}
