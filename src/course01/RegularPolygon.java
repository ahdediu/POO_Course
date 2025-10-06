package course01;

public class RegularPolygon implements Shape{
    private final int n;
    private final double side;
    public RegularPolygon(int n, double side) { this.n = n; this.side = side; }
    public double area() { return (n * Math.pow(side, 2)) / (4 * Math.tan(Math.PI / n)); }
    public double perimeter() { return n * side; }
    public String name() { return "RegularPolygon(n=" + n + ", side=" + side + ")"; }
}
