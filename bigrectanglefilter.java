import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


interface Filter<x> {
    boolean accept(x obj);
}


class BigRectangleFilter implements Filter<Rectangle> {
    @Override
    public boolean accept(Rectangle rectangle) {

        return rectangle.getWidth() + rectangle.getHeight() + rectangle.getWidth() + rectangle.getHeight()> 10;
    }
}


public class BigRectLister {
    public static void main(String[] args) {

        List<Rectangle> rectangles = createRectangles();


        BigRectangleFilter bigRectangleFilter = new BigRectangleFilter();


        List<Rectangle> bigRectangles = collectAll(rectangles, bigRectangleFilter);


        System.out.println("Rectangles with parameters above 10: ");
        for (Rectangle rectangle : bigRectangles) {
            System.out.println(rectangle);
        }
    }

    private static List<Rectangle> createRectangles() {
        List<Rectangle> rectangles = new ArrayList<>();


        rectangles.add(new Rectangle(3, 3)); // 12
        rectangles.add(new Rectangle(1, 6)); // 14
        rectangles.add(new Rectangle(7, 2)); // 18
        rectangles.add(new Rectangle(10, 1)); // 22
        rectangles.add(new Rectangle(10, 10)); // 40
        rectangles.add(new Rectangle(2, 2)); // 8
        rectangles.add(new Rectangle(3, 2)); // 10
        rectangles.add(new Rectangle(1, 1)); // 4
        rectangles.add(new Rectangle(1,2 )); // 14
        rectangles.add(new Rectangle(2, 3)); // 10

        return rectangles;
    }

    private static <x> List<x> collectAll(List<x> items, Filter<x> filter) {
        List<x> result = new ArrayList<>();
        for (x item : items) {
            if (filter.accept(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
