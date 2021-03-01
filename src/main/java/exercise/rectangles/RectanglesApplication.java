package exercise.rectangles;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Scanner;

@SpringBootApplication
public class RectanglesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RectanglesApplication.class)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .run(args)
                .close();
    }

    static Point parsePoint(String line) {
        var chunks = line.trim().split(",");
        if (chunks.length == 2) {
            return new Point(Integer.parseInt(chunks[0].trim()), Integer.parseInt(chunks[1].trim()));
        }
        throw new IllegalArgumentException("Not a point: " + line);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Top left coords A: ");
        Point a1 = parsePoint(scanner.nextLine());

        System.out.print("Bottom right coords A: ");
        Point a2 = parsePoint(scanner.nextLine());

        System.out.print("Top left coords B: ");
        Point b1 = parsePoint(scanner.nextLine());

        System.out.print("Bottom right coords B: ");
        Point b2 = parsePoint(scanner.nextLine());

        var a = new Rectangle(a1, a2);
        var b = new Rectangle(b1, b2);

        System.out.println(a);
        System.out.println(b);

        System.out.printf("A intersects B: %s\n", a.intersects(b));
        System.out.printf("A contains B: %s\n", a.contains(b));
        System.out.printf("A adjacent to B: %s\n", a.adjacent(b));
    }
}
