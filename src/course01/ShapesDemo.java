package course01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShapesDemo {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>(List.of(
                new Circle(5),
                new Rectangle(4, 8),
                new RightTriangle(3, 4),
                new RegularPolygon(6, 3),   // hexágono regular
                new Circle(2.5),
                new Rectangle(10, 2)
        ));

        // 1) imprimir cada forma
        shapes.forEach(s ->
                System.out.printf("%-28s  area=%8.3f  perim=%8.3f%n", s.name(), s.area(), s.perimeter())
        );

        // 2) totais
        double totalArea = shapes.stream().mapToDouble(Shape::area).sum();
        double totalPer = shapes.stream().mapToDouble(Shape::perimeter).sum();
        System.out.printf("%nTOTAL area=%.3f  perimeter=%.3f%n%n", totalArea, totalPer);

        // 3) filtrar por área > 50
        List<Shape> big = shapes.stream()
                .filter(s -> s.area() > 50.0)
                .collect(Collectors.toList());
        System.out.println("Shapes with area > 50.0:");
        big.forEach(s ->
                        System.out.printf("%-28s  area=%8.3f  perim=%8.3f%n", s.name(), s.area(), s.perimeter()));


                // 4) ordenar por área crescente
        shapes.sort(Comparator.comparingDouble(Shape::area));
        System.out.println("\nOrdenadas por area:");
        shapes.forEach(s ->
                System.out.printf("%-28s  area=%8.3f%n", s.name(), s.area())
        );
    }
}
