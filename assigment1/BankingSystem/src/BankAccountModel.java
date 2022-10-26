import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// M5 MVC Pattern
public class BankAccountModel {


    private final List<Account> accountList;

    public BankAccountModel(List<Account> accountList) {
        this.accountList = accountList;
    }

    public BankAccountModel() {
        this(false);
    }

    public BankAccountModel(boolean includeSampleData) {
        this(new ArrayList<>());

        if (includeSampleData) {
            createSampleData();
        }
    }

    private void createSampleData() {
        accountList.add(AccountFactory.createDefaultCheckingAccount(1, 1, new BigDecimal("12500")));
        accountList.add(AccountFactory.createDefaultSavingAccount(1, 2, new BigDecimal("25200")));
        accountList.add(AccountFactory.createDefaultCDAccount(2, 3, new BigDecimal("33000")));
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void withdraw(Account account, BigDecimal amount) throws BankAccountException {

        if (amount.compareTo(account.getBalance()) > 0) {
            throw new BankAccountException("Insufficient balance!");
        }

        account.withdrawal(amount);
    }

    public void createNewAccount(String accountType, int customerId, long accountId, BigDecimal initialBalance) throws BankAccountException {

        // Check if the account with accountId exists!
        for (Account account : accountList) {
            if (accountId == account.getAccountId()) {
                throw new BankAccountException("Account with same id exists!");
            }
        }

        // Create account
        Account account = null;

        if ("Checking".equals(accountType)) {
            account = AccountFactory.createDefaultCheckingAccount(customerId, accountId, initialBalance);
        } else if ("Savings".equals(accountType)) {
            account = AccountFactory.createDefaultSavingAccount(customerId, accountId, initialBalance);
        } else if ("CD".equals(accountType)) {
            account = AccountFactory.createDefaultCDAccount(customerId, accountId, initialBalance);
        } else {
            throw new IllegalStateException("Undefined account type " + accountType);
        }

        this.accountList.add(account);

        dumpAccountList();

    }

    private void dumpAccountList() {

        System.out.println("Current account size=" + this.accountList.size());
        for (Account account : accountList) {
            System.out.println("\t" + account);
        }
        System.out.println();

    }
}