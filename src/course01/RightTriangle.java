package course01;

public class RightTriangle implements Shape {
    private final double a, b; // catetos
    public RightTriangle(double a, double b) { this.a = a; this.b = b; }
    public double area() { return (a * b) / 2.0; }
    public double perimeter() { return a + b + Math.hypot(a, b); }
    public String name() { return "RightTriangle(a=" + a + ", b=" + b + ")"; }
}
