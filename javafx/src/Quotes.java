import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class Quotes extends Application {

    // Quotes to be displayed
    private String[] quotesArray = {"All our dreams can come true, if we have the courage to pursue them.",
            "It does not matter how slowly you go as long as you do not stop.",
            "The future belongs to those who believe in the beauty of their dreams.",
            "Success is not final, failure is not fatal: it is the courage to continue that counts.",
            "Believe you can and you’re halfway there.",
            "Don’t wish it were easier. Wish you were better."};

    // All available font names
    private String[] fonts = (String[]) Font.getFontNames().toArray();

    // UI Components
    private Text textQuote;
    private Button buttonChangeQuotes;

    // Random generator
    private Random random = new Random();

    @Override
    public void start(Stage primaryStage) {

        // Initialize UI Components
        textQuote = new Text();
        buttonChangeQuotes = new Button("Change Quotes");

        buttonChangeQuotes.setFont(new Font(null, 24));

        // Add event listeners
        buttonChangeQuotes.setOnAction(e -> changeQuotes());

        // Define Layouts
        VBox root = new VBox(textQuote, buttonChangeQuotes);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(32));
        root.setSpacing(32);

        // Setup scene and stage
        primaryStage.setTitle("My Favorite Quotes");

        Scene scene = new Scene(root, 1200, 300);
        primaryStage.setScene(scene);

        // Display initial quotes
        changeQuotes();

        primaryStage.show();

    }

    /**
     * Chagne the quote with both random quote text and style.
     **/
    public void changeQuotes() {

        // Choose a random quote and fonts and color
        String quote = quotesArray[random.nextInt(quotesArray.length)];
        String font = fonts[random.nextInt(fonts.length)];
        Color color = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1);

        // Update the display
        textQuote.setText(quote);
        textQuote.setFont(new Font(font, 24));
        textQuote.setFill(color);

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