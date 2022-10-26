import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public class LineUtils {

    public static double getAngle(Line line) {

        double dx = line.getEndX() - line.getStartX();
        double dy = line.getEndY() - line.getStartY();

        double inRads = Math.atan2(dy, dx);

        // We need to map to coord system when 0 degree is at 3 O'clock, 270 at 12 O'clock
        if (inRads < 0) {
            inRads = Math.abs(inRads);
        } else {
            inRads = 2 * Math.PI - inRads;
        }

        return Math.toDegrees(inRads);
    }

    public static double getDistance(Line line) {
        return Math.sqrt(Math.pow(line.getEndX() - line.getStartX(), 2) + Math.pow(line.getEndY() - line.getStartY(), 2));
    }

    public static Point2D getMidPoint(Line line) {

        double mx = (line.getEndX() + line.getStartX()) / 2;
        double my = (line.getEndY() + line.getStartY()) / 2;

        return new Point2D(mx, my);

    }

    public static boolean isVertical(Line line) {
        return Double.compare(line.getEndX(), line.getStartX()) == 0;
    }

    public static boolean isHorizontal(Line line) {
        return Double.compare(line.getEndY(), line.getStartY()) == 0;
    }

}