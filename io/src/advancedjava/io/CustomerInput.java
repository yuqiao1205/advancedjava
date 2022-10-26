package advancedjava.io;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerInput extends Application {

    private final static Font RESULT_FONT = Font.font("Helvetica", 24);
    private final static Font INPUT_FONT = Font.font("Helvetica", 20);
    private Stage primaryStage;
    private Text statusText, resultText;
    private Button uploadButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        VBox primaryBox = new VBox();
        primaryBox.setAlignment(Pos.CENTER);
        primaryBox.setSpacing(20);
        primaryBox.setStyle("-fx-background-color: white");

        VBox uploadBox = new VBox();
        uploadBox.setAlignment(Pos.CENTER);
        uploadBox.setSpacing(20);
        Text uploadLabel = new Text("Upload a comma-separated file with customer data.");
        uploadLabel.setFont(INPUT_FONT);
        uploadButton = new Button("Upload data");
        uploadButton.setOnAction(this::processDataUpload);

        uploadBox.getChildren().add(uploadLabel);
        uploadBox.getChildren().add(uploadButton);
        primaryBox.getChildren().add(uploadBox);

        VBox resultsBox = new VBox();
        resultsBox.setAlignment(Pos.CENTER);
        resultsBox.setSpacing(20);
        statusText = new Text("");
        statusText.setVisible(false);
        statusText.setFont(RESULT_FONT);
        statusText.setFill(Color.RED);
        resultText = new Text("");
        resultText.setVisible(false);
        resultText.setFont(RESULT_FONT);
        resultsBox.getChildren().add(statusText);
        resultsBox.getChildren().add(resultText);
        primaryBox.getChildren().add(resultsBox);

        Scene scene = new Scene(primaryBox, 475, 200, Color.TRANSPARENT);
        primaryStage.setTitle("Customer Data Upload");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void processDataUpload(ActionEvent event) {
        statusText.setVisible(false);
        resultText.setVisible(false);
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);
        parseFile(file);

    }

    private void parseFile(File file) {

        // M6 IO

        // Open and parse CSV
        try (Scanner scanner = new Scanner(file, Charset.defaultCharset().name())) {

            // List of customers
            List<Customer> customerList = new ArrayList<>();

            // Parse the content of CSV
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                // Split the line by comma
                String[] rawFields = line.split(",");
                if (rawFields.length < 2) {
                    throw new IllegalStateException("Not enough fields are provided! Line=" + line);
                }

                // Parse fields of customer object
                String customerId = rawFields[0];
                if (customerId.contains("@")) {
                    throw new InvalidCharacterException("ID contains invalid character.");
                }

                Integer numOfOrders = 0;
                try {
                    numOfOrders = Integer.parseInt(rawFields[1]);
                } catch (NumberFormatException nfe) {
                    throw new NumberFormatException("non-integer quantity.");
                }

                // Create and add the customer object
                customerList.add(new Customer(customerId, numOfOrders));
            }

            // Show result
            // Show the status text
            statusText.setText("Upload Successful: " + customerList.size() + " customers.");
            statusText.setVisible(true);

            // Show the result text
            // Show the totalOrders of all customers
            resultText.setText("Total Number of Orders: " + customerList.stream().mapToInt(n -> n.getNumberOfOrders()).sum());
            resultText.setVisible(true);

            // Disable the upload button
            uploadButton.setDisable(true);

        } catch (NumberFormatException e) {

            // Situation 1: Number format exception in field
            statusText.setText("Upload failed: " + e.getMessage());
            statusText.setVisible(true);

        } catch (InvalidCharacterException e) {

            // Situation 2: Invalid character in field
            statusText.setText("Upload failed: " + e.getMessage());
            statusText.setVisible(true);

        } catch (IOException e) {

            // Situation 3: IO Exception
            statusText.setText("Upload failed: I/O error.");
            statusText.setVisible(true);

        } catch (IllegalStateException e) {

            // Situation 4: Other cases
            statusText.setText("Upload failed: " + e.getMessage());
            statusText.setVisible(true);

        }

    }

}

// M6: IO
// Define own exception for invalid character
class InvalidCharacterException extends Exception {
    public InvalidCharacterException(String message) {
        super(message);
    }
}