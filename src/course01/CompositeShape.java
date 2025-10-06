package course01;

import java.util.List;
import java.util.Collection;

public class CompositeShape implements Shape {

    private final List<Shape> parts;

    public CompositeShape(List<Shape> parts) {
        // Copy for immutability
        this.parts = List.copyOf(parts);
    }

    // Compute total area of all parts
    @Override
    public double area() {
        return parts.stream()
                .mapToDouble(Shape::area)
                .sum();
    }

    // Compute total perimeter
    @Override
    public double perimeter() {
        return parts.stream()
                .mapToDouble(Shape::perimeter)
                .sum();
    }

    @Override
    public String name() {
        return "CompositeShape(" + parts.size() + " parts)";
    }

    // --- Static utility methods ---
    public static double totalArea(Collection< Shape> shapes) {
        return shapes.stream()
                .mapToDouble(Shape::area)
                .sum();
    }

    public static double totalPerimeter(Collection< Shape> shapes) {
        return shapes.stream()
                .mapToDouble(Shape::perimeter)
                .sum();
    }

    // --- Example of usage ---
    public static void main(String[] args) {
        Shape circle = new Circle(2.0);
        Shape rect = new Rectangle(3.0, 4.0);
        Shape tri = new RightTriangle(3.0, 4.0);

        // Combine shapes into one composite
        CompositeShape composite = new CompositeShape(List.of(circle, rect, tri));

        System.out.println(composite.name());
        System.out.println("Area = " + composite.area());
        System.out.println("Perimeter = " + composite.perimeter());

        // Use static utilities directly
        double allAreas = CompositeShape.totalArea(List.of(circle, rect, tri));
        System.out.println("Sum of areas (via static util): " + allAreas);
    }
}