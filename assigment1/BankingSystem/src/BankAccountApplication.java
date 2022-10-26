import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// M5 MVC Pattern
public class BankAccountApplication extends Application {

    // Model
    private BankAccountModel model;

    private BankAccountView view;

    private BankAccountController controller;


    @Override
    public void start(Stage primaryStage) {

        // Setup scene and stage
        primaryStage.setTitle("Bank Account System");

        Scene scene = new Scene(view.getRoot());
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    /**
     * Initialize the model/view for initial loading.
     */
    public void init() {

        // M5 MVC Pattern
        // Initialize Model/View/Controller
        model = new BankAccountModel(true);
        view = new BankAccountView();
        controller = new BankAccountController(model, view);
        view.setController(controller);

        // Initialize view
        view.init();

    }

    /**
     * Main entry point.
     *
     * @param args command-line args.
     */
    public static void main(String[] args) {
        launch(args);
    }

}