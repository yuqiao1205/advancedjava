import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LineGUI extends Application {

    private static final int CIRCLE_RADIUS = 5;
    private Pane pane;
    private BorderPane borderPane;
    private Circle startPoint, endPoint;
    private Line line;
    private Button distanceButton, midpointButton, vertHorzButton, angleButton;
    private Text distanceText, midpointText, vertHorxText, angleText;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        borderPane = new BorderPane();

        pane = new Pane();
        pane.setOnMouseClicked(this::handleMouseClicks);
        borderPane.setCenter(pane);

        distanceText = new Text("");
        distanceButton = new Button("Calculate Distance");
        // YOUR CODE HERE- ADD A STATEMENT TO SET THE ACTION OF THE BUTTON
        distanceButton.setOnAction(event -> distanceText.setText(DisplayLineInfo.createDisplayLineInfo(DisplayLineInfo.InfoType.DISTANCE).getInfo(line)));

        TilePane distancePane = new TilePane(distanceButton, distanceText);
        distancePane.setAlignment(Pos.CENTER);

        midpointText = new Text("");
        midpointButton = new Button("Calculate Midpoint");
        // YOUR CODE HERE- ADD A STATEMENT TO SET THE ACTION OF THE BUTTON
        midpointButton.setOnAction(event -> midpointText.setText(DisplayLineInfo.createDisplayLineInfo(DisplayLineInfo.InfoType.MIDPOINT).getInfo(line)));

        TilePane midpointPane = new TilePane(midpointButton, midpointText);
        midpointPane.setAlignment(Pos.CENTER);

        vertHorxText = new Text("");
        vertHorzButton = new Button("Determine Vertical/Horizontal");
        // YOUR CODE HERE- ADD A STATEMENT TO SET THE ACTION OF THE BUTTON
        vertHorzButton.setOnAction(event -> vertHorxText.setText(DisplayLineInfo.createDisplayLineInfo(DisplayLineInfo.InfoType.VERTHORZ).getInfo(line)));

        TilePane vertHorzPane = new TilePane(vertHorzButton, vertHorxText);
        vertHorzPane.setAlignment(Pos.CENTER);

        angleText = new Text("");
        angleButton = new Button("Angle");
        // YOUR CODE HERE- ADD A STATEMENT TO SET THE ACTION OF THE BUTTON
        angleButton.setOnAction(event -> angleText.setText(DisplayLineInfo.createDisplayLineInfo(DisplayLineInfo.InfoType.ANGLE).getInfo(line)));
        TilePane anglePane = new TilePane(angleButton, angleText);
        anglePane.setAlignment(Pos.CENTER);

        VBox controlBox = new VBox(distancePane, midpointPane, vertHorzPane, anglePane);
        controlBox.setAlignment(Pos.CENTER);
        controlBox.setSpacing(10);
        borderPane.setBottom(controlBox);

        Scene scene = new Scene(borderPane, 500, 500, Color.WHITE);
        primaryStage.setTitle("Line Characteristics");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleMouseClicks(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        if (startPoint == null) { // no start point yet
            startPoint = new Circle(x, y, CIRCLE_RADIUS);
            pane.getChildren().add(startPoint);
            addCoordinateString(x, y);
            line = null;
        } else if (endPoint == null) { // start point, but not end point yet
            endPoint = new Circle(x, y, CIRCLE_RADIUS);
            pane.getChildren().add(endPoint);
            addCoordinateString(x, y);

            line = new Line(startPoint.getCenterX(), startPoint.getCenterY(), endPoint.getCenterX(), endPoint.getCenterY());
            pane.getChildren().add(line);

        } else { // startPoint != null && endPoint !=null
            // both start and end are there, so this is starting a new line

            // clear out everything from the last line
            pane.getChildren().clear();
            endPoint = null;
            line = null;
            distanceText.setText("");
            midpointText.setText("");
            vertHorxText.setText("");

            // start the new line
            startPoint = new Circle(x, y, CIRCLE_RADIUS);
            pane.getChildren().add(startPoint);
            addCoordinateString(x, y);
        }

    }

    private void addCoordinateString(double x, double y) {
        String coordinateString = "(" + x + ", " + y + ")";
        Text coordinates = new Text(x - CIRCLE_RADIUS, y - CIRCLE_RADIUS - 2, coordinateString);
        pane.getChildren().add(coordinates);
    }

}
