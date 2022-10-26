import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ArtShow extends Application
{

    @Override
    public void start(Stage stage)
    {

        Group root = new Group();
        Scene scene = new Scene(root, 800, 800); // add scene
        scene.setFill(Color.LIGHTGRAY); // set color

        Rectangle rectangle = new Rectangle(100, 100, 100, 100); // add rectangle
        rectangle.setFill(Color.RED); // red color to rectangle
        root.getChildren().add(rectangle); // add rectangle

        Circle circle = new Circle();
        circle.setCenterX(300.0f); // make a circle
        circle.setCenterY(135.0f);
        circle.setRadius(100.0f);
        circle.setFill(Color.YELLOW); // yellow color
        root.getChildren().add(circle); // add circle

        stage.setScene(scene); // set scene
        stage.show(); // show image

    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}