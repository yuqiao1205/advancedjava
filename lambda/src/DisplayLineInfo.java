import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

@FunctionalInterface
public interface DisplayLineInfo {

    public static DisplayLineInfo createDisplayLineInfo(InfoType type) {

        switch (type) {
            case DISTANCE:
                return line -> String.format("%.1f", LineUtils.getDistance(line));
            case MIDPOINT:
                return line -> {
                    Point2D mp = LineUtils.getMidPoint(line);
                    return String.format("(%.1f,%.1f)", mp.getX(), mp.getY());
                };
            case VERTHORZ:
                return line -> String.format("Vertical? %b Horizontal? %b", LineUtils.isVertical(line), LineUtils.isHorizontal(line));
            case ANGLE:
                return line -> String.format("%.1f", LineUtils.getAngle(line));
        }
        return null;

    }

    String getInfo(Line line);

    public static enum InfoType {
        DISTANCE, MIDPOINT, VERTHORZ, ANGLE;
    }

}
