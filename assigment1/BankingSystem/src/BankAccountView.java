import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.Optional;

// M5 MVC Pattern
// M5: allow the user to enter information required to create an object
// Enter basic information to create new account
public class BankAccountView {

    // For new account feature
    // UI fields
    // M5 MVC Pattern
    // M5 use at least one control that is different from a TextField (e.g., a radio button, check box, combo box, etc.)
    // M5 Use ChoiceBox
    private final ChoiceBox<String> choiceNewAccountType = new ChoiceBox();

    private final ChoiceBox<String> choiceOverviewAccount = new ChoiceBox();

    // M5 MVC Pattern
    private Parent root;
    private final TextField textCustomerId = new TextField();
    private final TextField textAccountId = new TextField();
    private final TextField textInitialBalance = new TextField();
    // Controller
    private BankAccountController controller;
    private Label labelCustomerId = new Label("Customer Id");
    private Label labelAccountId = new Label("Account Id");
    private Label labelInitialBalance = new Label("Initial Balance");
    // Label
    private Label labelNewAccountType = new Label("New Account Type");
    private Label labelNewAccountStatus = new Label("");
    private Button buttonResetCreateNewAccount = new Button("Reset");
    // Buttons
    private Button buttonCreateNewAccount = new Button("Create");
    // For account overview feature
    // UI fields
    private Label labelOviewAccount = new Label("Account");

    // Buttons
    private Button buttonOverviewDetail = new Button("Show Detail");
    private Button buttonOverviewWithdraw = new Button("Withdraw");

    private Label labelOverviewStatus = new Label("");

    public void showAccountDetailDialog(long accountId, String accountType, long customerId, BigDecimal balance, String otherDetail) {
        Stage dialog = new Stage();

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(null);

        VBox dialogVbox = new VBox(4);

        dialogVbox.setPadding(new Insets(5, 5, 5, 5));

        dialogVbox.getChildren().add(new Text("Account #" + accountId + "- (" + accountType + ")"));
        dialogVbox.getChildren().add(new Text("\tcustomerId: " + customerId));
        dialogVbox.getChildren().add(new Text("\tbalance: $" + balance));

        if (otherDetail != null && !otherDetail.isEmpty()) {
            dialogVbox.getChildren().add(new Text("\t" + otherDetail));
        }

        Scene dialogScene = new Scene(dialogVbox);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    public void init() {
        // Initialize UI Components
        initUIComponents();

        setEventHandlers();

        // Initialize layout
        // TODO: Improve layout using https://www.callicoder.com/javafx-registration-form-gui-tutorial/
        // TODO: https://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
        // TODO: Add titlePane: https://docs.oracle.com/javafx/2/ui_controls/accordion-titledpane.htm

        Parent overviewPane = createOverviewPane();
        Parent newAccountPane = createNewAccountPane();

        // Set root level pane
        root = new VBox();
        ((VBox) root).getChildren().addAll(overviewPane, newAccountPane);

    }

    private Parent createOverviewPane() {

        TitledPane titledPane = createDefaultTitledPane("Account Overview");

        VBox contentPane = new VBox();

        // Define content pane
        GridPane contentGridPane = new GridPane();

        contentGridPane.setVgap(4);
        contentGridPane.setHgap(4);
        contentGridPane.setPadding(new Insets(5, 5, 5, 5));
        contentGridPane.add(labelOviewAccount, 0, 0);
        contentGridPane.add(choiceOverviewAccount, 1, 0);

        // Define the button panel
        HBox hboxButtonPanel = new HBox(buttonOverviewDetail, buttonOverviewWithdraw);
        hboxButtonPanel.setSpacing(6);
        hboxButtonPanel.setPadding(new Insets(5, 5, 5, 5));
        hboxButtonPanel.setAlignment(Pos.CENTER_RIGHT);

        // Define the status panel
        HBox hboxStatusPanel = new HBox(labelOverviewStatus);
        hboxStatusPanel.setPadding(new Insets(0, 0, 0, 0));
        hboxStatusPanel.setSpacing(0);
        hboxStatusPanel.setBorder(new Border(new BorderStroke(Color.LIGHTSLATEGRAY, BorderStrokeStyle.DASHED, null, new BorderWidths(1))));

        // Add components to content pane
        contentPane.getChildren().add(contentGridPane);
        contentPane.getChildren().add(hboxButtonPanel);
        contentPane.getChildren().add(hboxStatusPanel);

        // Add content pane
        titledPane.setContent(contentPane);

        return titledPane;

    }

    public Label getLabelNewAccountStatus() {
        return labelNewAccountStatus;
    }

    public Label getLabelOverviewStatus() {
        return labelOverviewStatus;
    }

    private Parent createNewAccountPane() {

        TitledPane titledPane = createDefaultTitledPane("Create New Account");

        VBox contentPane = new VBox();

        // Define content pane
        GridPane contentGridPane = new GridPane();

        contentGridPane.setVgap(4);
        contentGridPane.setHgap(4);
        contentGridPane.setPadding(new Insets(5, 5, 5, 5));
        contentGridPane.add(labelNewAccountType, 0, 0);
        contentGridPane.add(choiceNewAccountType, 1, 0);
        contentGridPane.add(labelCustomerId, 0, 1);
        contentGridPane.add(textCustomerId, 1, 1);
        contentGridPane.add(labelAccountId, 0, 2);
        contentGridPane.add(textAccountId, 1, 2);
        contentGridPane.add(labelInitialBalance, 0, 3);
        contentGridPane.add(textInitialBalance, 1, 3);

        // Define the button panel
        HBox hboxButtonPanel = new HBox(buttonCreateNewAccount, buttonResetCreateNewAccount);
        hboxButtonPanel.setSpacing(6);
        hboxButtonPanel.setPadding(new Insets(5, 5, 5, 5));
        hboxButtonPanel.setAlignment(Pos.CENTER_RIGHT);

        // Define the status panel
        HBox hboxStatusPanel = new HBox(labelNewAccountStatus);
        hboxStatusPanel.setPadding(new Insets(0, 0, 0, 0));
        hboxStatusPanel.setSpacing(0);
        hboxStatusPanel.setBorder(new Border(new BorderStroke(Color.LIGHTSLATEGRAY, BorderStrokeStyle.DASHED, null, new BorderWidths(1))));

        // Add components to content pane
        contentPane.getChildren().add(contentGridPane);
        contentPane.getChildren().add(hboxButtonPanel);
        contentPane.getChildren().add(hboxStatusPanel);

        // Add content pane
        titledPane.setContent(contentPane);

        return titledPane;

    }


    private TitledPane createDefaultTitledPane(String title) {
        TitledPane titledPane = new TitledPane();

        titledPane.setAnimated(false);
        titledPane.setCollapsible(true);

        titledPane.setText(title);

        return titledPane;
    }

    /**
     * The place to initialize UI components
     */
    private void initUIComponents() {

        controller.setNewAccountTypeChoice();

        controller.refreshOverviewList();

        choiceOverviewAccount.setMinWidth(185);
    }

    public void setController(BankAccountController controller) {
        this.controller = controller;
    }

    public ChoiceBox getChoiceNewAccountType() {
        return choiceNewAccountType;
    }

    public TextField getTextCustomerId() {
        return textCustomerId;
    }

    public ChoiceBox<String> getChoiceOverviewAccount() {
        return choiceOverviewAccount;
    }

    public TextField getTextAccountId() {
        return textAccountId;
    }

    public TextField getTextInitialBalance() {
        return textInitialBalance;
    }


    public void setEventHandlers() {

        // M5 MVC Pattern
        // Call controller to create object using current values in view
        buttonCreateNewAccount.setOnAction(e -> createNewAccountHandler());

        // M5 MVC Pattern
        // Reset UI using controller
        buttonResetCreateNewAccount.setOnAction(e -> resetCreateNewAccountHandler());

        // M5 MVC Pattern
        // M5 have a button to display information about the object
        // M5 Show detail of the selected account
        buttonOverviewDetail.setOnAction(e -> showAccountDetailHandler());

        // M5 have a button to display information about the object
        // M5 have a button to display the results of "processing" an object (or a collection of objects)
        // M5 The withdraw result(successful or failure) will be displayed in the status bar
        buttonOverviewWithdraw.setOnAction(e -> withdrawAccountHandler());

    }

    private void createNewAccountHandler() {
        // Validate input for create new account
        if (validateInputForCreateNewAccount()) {
            try {
                controller.createNewAccount();
                showNewAccountStatus(Alert.AlertType.INFORMATION, "New account created!");
            } catch (BankAccountException bae) {
                showNewAccountStatus(Alert.AlertType.ERROR, bae.getMessage());
            }

        }
    }

    private boolean validateInputForCreateNewAccount() {

        // TODO: Validate the values as well
        if (choiceNewAccountType.getValue().isEmpty()) {
            showNewAccountStatus(Alert.AlertType.ERROR, "Please choose account type!");
            return false;
        }
        if (textCustomerId.getText().isEmpty()) {
            showNewAccountStatus(Alert.AlertType.ERROR, "Please enter customer id!");
            return false;
        }

        if (textAccountId.getText().isEmpty()) {
            showNewAccountStatus(Alert.AlertType.ERROR, "Please enter account id!");
            return false;
        }

        if (textInitialBalance.getText().isEmpty()) {
            showNewAccountStatus(Alert.AlertType.ERROR, "Please enter initial balance!");
            return false;
        }

        return true;
    }

    private void resetCreateNewAccountHandler() {
        this.choiceNewAccountType.getSelectionModel().selectFirst();
        this.textCustomerId.setText("");
        this.textAccountId.setText("");
        this.textInitialBalance.setText("");
    }

    private void showAccountDetailHandler() {
        try {
            controller.showAccountDetail();
        } catch (BankAccountException bae) {
            showOviewStatus(Alert.AlertType.ERROR, bae.getMessage());
        }
    }

    private void withdrawAccountHandler() {

        if (choiceOverviewAccount.getSelectionModel().isEmpty()) {
            showOviewStatus(Alert.AlertType.ERROR, "Account is required!");
            return;
        }

        TextInputDialog dialog = new TextInputDialog("");

        dialog.setTitle("Withdraw");
        dialog.setHeaderText(choiceOverviewAccount.getSelectionModel().getSelectedItem());
        dialog.setContentText("Withdraw amount:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            try {
                controller.withdraw(result.get());
                showOviewStatus(Alert.AlertType.INFORMATION, "Transaction completed!");

            } catch (BankAccountException bae) {
                showOviewStatus(Alert.AlertType.ERROR, bae.getMessage());
            }
        } else {
            showOviewStatus(Alert.AlertType.INFORMATION, "No input, withdraw cancelled!");
        }
    }

    private void showNewAccountStatus(Alert.AlertType alertType, String message) {
        showStatus(alertType, this.labelNewAccountStatus, message);
    }

    private void showOviewStatus(Alert.AlertType alertType, String message) {
        showStatus(alertType, this.labelOverviewStatus, message);
    }

    private void showStatus(Alert.AlertType alertType, Label labelStatus, String message) {
        labelStatus.setText(message);

        if (Alert.AlertType.ERROR == alertType) {
            labelStatus.setTextFill(Color.RED);
        } else if (alertType == Alert.AlertType.INFORMATION) {
            labelStatus.setTextFill(Color.GREEN);
        } else if (alertType == Alert.AlertType.WARNING) {
            labelStatus.setTextFill(Color.ORANGE);
        } else {
            labelStatus.setTextFill(Color.BLACK);
        }
    }

    public Parent getRoot() {
        return root;
    }


}