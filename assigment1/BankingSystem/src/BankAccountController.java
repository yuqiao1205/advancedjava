
import javafx.collections.FXCollections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// M5 MVC Pattern
public class BankAccountController {

    private final BankAccountView view;
    private final BankAccountModel model;

    public BankAccountController(BankAccountModel model, BankAccountView view) {
        this.model = model;
        this.view = view;
    }

    public BankAccountView getView() {
        return view;
    }

    public BankAccountModel getModel() {
        return model;
    }

    public void refreshOverviewList() {
        view.getChoiceOverviewAccount().setItems(FXCollections.observableList(getOverviewList()));
        view.getChoiceOverviewAccount().getSelectionModel().selectFirst();
    }

    public void setNewAccountTypeChoice() {
        view.getChoiceNewAccountType().setItems(FXCollections.observableArrayList(
                "Checking", "Savings", "CD"));
        view.getChoiceNewAccountType().getSelectionModel().selectFirst();
    }

    private List<String> getOverviewList() {
        List<String> result = new ArrayList<>();

        for (Account account : model.getAccountList()) {
            result.add("Account #" + account.getAccountId() + " - " + account.getAccountTypeId());
        }

        return result;
    }


    // M5 MVC Pattern
    // M5 create the objects (using the classes of the model) using the data entered by the user (via the view)
    // M5 Create new account
    public void createNewAccount() throws BankAccountException {
        // M5 MVC Pattern
        // Get values from view
        try {
            String accountType = (String) view.getChoiceNewAccountType().getValue();
            int customerId = Integer.parseInt(view.getTextCustomerId().getText());
            long accountId = Long.parseLong(view.getTextAccountId().getText());
            BigDecimal initialBalance = new BigDecimal(view.getTextInitialBalance().getText());

            if (initialBalance.compareTo(BigDecimal.ZERO) > 0) {
                // Handle exception when accountId is already exists
                model.createNewAccount(accountType, customerId, accountId, initialBalance);

                // M5 MVC Pattern
                // M5 display/process the objects when necessary and return the output to the view
                // M5 Refresh account list after creating new account
                this.refreshOverviewList();

            } else {
                throw new BankAccountException("Initial balance is required!");
            }

        } catch (NumberFormatException nfe) {
            throw new BankAccountException("Invalid number format!", nfe);
        }
    }

    public void withdraw(String inputAmount) throws BankAccountException {
        // M5 MVC Pattern
        // Get values from view
        try {
            int accountIndex = view.getChoiceOverviewAccount().getSelectionModel().getSelectedIndex();

            if (accountIndex < 0 || accountIndex > model.getAccountList().size() - 1) {
                throw new BankAccountException("No account selected!");
            }

            Account account = model.getAccountList().get(accountIndex);
            BigDecimal amount = new BigDecimal(inputAmount);

            model.withdraw(account, amount);
        } catch (NumberFormatException nfe) {
            throw new BankAccountException("Invalid number format!", nfe);
        }
    }

    // M5 MVC Pattern
    // M5 define the methods that will respond to the button clicks of the view
    // M5 show the account detail
    public void showAccountDetail() throws BankAccountException {
        // M5 MVC Pattern
        // Get values from view

        int accountIndex = view.getChoiceOverviewAccount().getSelectionModel().getSelectedIndex();

        if (accountIndex < 0 || accountIndex > model.getAccountList().size() - 1) {
            throw new BankAccountException("No account selected!");
        }

        Account account = model.getAccountList().get(accountIndex);

        view.showAccountDetailDialog(account.getAccountId(), account.getAccountTypeId(), account.getCustomerId(), account.getBalance(), "");

    }


}