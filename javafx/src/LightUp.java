import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LightUp extends Application {
    private Rectangle rectangle;


    @Override
    public void start(Stage stage) throws Exception {

        // Initialize components
        rectangle = new Rectangle(200, 100);
        rectangle.setStroke(Color.PINK);
        rectangle.setFill(Color.TRANSPARENT);



        // Initialize scene
        Scene scene = new Scene(new Group(), 450, 250);

        // Initialize event handlers
        scene.setOnMouseClicked(mouseEvent -> {
            if (rectangle.contains(new Point2D(mouseEvent.getX(), mouseEvent.getY()))) {
                rectangle.setVisible(false);
            } else {
                rectangle.setFill(Color.TRANSPARENT);
            }
        });

        scene.setOnMouseMoved(mouseEvent -> {
            if (rectangle.contains(new Point2D(mouseEvent.getX(), mouseEvent.getY()))) {
                rectangle.setFill(Color.SALMON);
            } else {
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setVisible(true);
            }
        });


        // Initialize layout
        Group root = (Group) scene.getRoot();
        root.getChildren().add(rectangle);

        // Show the main window
        stage.setScene(scene);
        stage.setTitle("Draw Rectangle");
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
