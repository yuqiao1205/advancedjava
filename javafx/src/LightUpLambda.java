import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LightUpLambda extends Application {
    private Rectangle rectangle;
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Group(), 450, 250);
        rectangle = new Rectangle(200, 100);
        rectangle.setStroke(Color.PINK);
        rectangle.setFill(Color.TRANSPARENT);
        Group root = (Group) scene.getRoot();
        root.getChildren().add(rectangle);
        stage.setScene(scene);
        stage.setTitle("Draw Rectangle");
        stage.show();

        scene.setOnMouseMoved(this::handleMouseMove);
        scene.setOnMouseClicked(this::handleMouseClicked);

//        scene.setOnMouseClicked(mouseEvent -> {
//            if (rectangle.contains(new Point2D(mouseEvent.getX(), mouseEvent.getY()))) {
//                rectangle.setVisible(false);
//            } else {
//                rectangle.setFill(Color.TRANSPARENT);
//            }
//        });
    }

    public void handleMouseMove(MouseEvent mouseEvent) {
        if(rectangle.contains(new Point2D(mouseEvent.getX(), mouseEvent.getY()))){
            rectangle.setFill(Color.SALMON);
        }else{
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setVisible(true);
        }
    }

    public void handleMouseClicked(MouseEvent mouseEvent) {
        if(rectangle.contains(new Point2D(mouseEvent.getX(), mouseEvent.getY()))){
            rectangle.setVisible(false);
        }else{
            rectangle.setFill(Color.TRANSPARENT);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
