package course01;

public class Rectangle implements Shape{
    private final double w, h;
    public Rectangle(double w, double h) { this.w = w; this.h = h; }
    public double area() { return w * h; }
    public double perimeter() { return 2 * (w + h); }
    public String name() { return "Rectangle(" + w + "x" + h + ")"; }
}
