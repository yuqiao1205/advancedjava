import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BankMain {

    public static void main(String[] args) {

        // M3 USING FACTORY
        CheckingAccount checkingAccount = AccountFactory.createDefaultCheckingAccount(2, 2, new BigDecimal("200"));

        // M3 USING BUILDER
        // Direct SavingAccountBuilder how to build SavingAccount
        SavingsAccount.Builder savingAccountBuilder = new SavingsAccount.Builder();
        savingAccountBuilder.setCustomerId(2).setAccountId(3).setBalance(new BigDecimal("5000")).setInterestRate(new BigDecimal("0.03"));
        savingAccountBuilder.setMonthlyWithdrawalLimits(2);

        // M3 USING BUILDER
        // Build a saving account
        SavingsAccount savingsAccount = savingAccountBuilder.build();

        // Build another saving account with different reward strategy
        savingAccountBuilder.setAccountId(31).setAccountRewardStrategy(new BalanceRewardStrategy());
        SavingsAccount savingsAccount2 = savingAccountBuilder.build();

        // M3 USING BUILDER
        CDAccount.Builder cdBuilder = new CDAccount.Builder();
        cdBuilder.setCustomerId(2).setAccountId(4).setBalance(new BigDecimal("1000")).setInterestRate(new BigDecimal("0.05"));
        cdBuilder.setDefaultMaturityPeriodMonths(12);
        CDAccount cdAccount = cdBuilder.build();

        // Using array list to hold the accounts
        List<Account> accounts = new ArrayList<>();

        accounts.add(checkingAccount);
        accounts.add(savingsAccount);
        accounts.add(cdAccount);

        for (Account a : accounts) {
            System.out.println(a.toString());
        }

        System.out.println();

        savingsAccount.deposit(new BigDecimal(150));

        System.out.println();

        System.out.println("Checking account #" + checkingAccount.getAccountId() + " balance is: $" + checkingAccount.getBalance());
        checkingAccount.withdrawal(new BigDecimal(75));
        checkingAccount.transfer(savingsAccount, new BigDecimal(25));
        checkingAccount.openCheck("2018-2-1", "Jane", new BigDecimal(10000), "bought used car");
        checkingAccount.openCheck("2018-2-2", "Tommy", new BigDecimal(10), "bought food");
        savingsAccount.withdrawal(new BigDecimal(5.00));
        savingsAccount.withdrawal(new BigDecimal(1.00));
        savingsAccount.withdrawal(new BigDecimal(2.00));
        cdAccount.saveToCD(2000, 12);

        System.out.println("Checking account balance is: $" + checkingAccount.getBalance());
        System.out.println();

        for (Account a : accounts) {
            if (a instanceof CDAccount) {
                System.out.println("CDAccount: " + a.getAccountId() + " Default maturity period months: " + ((CDAccount) a).getDefaultMaturityPeriodMonths());
            } else if (a instanceof SavingsAccount) {
                System.out.println("SavingsAccount: " + a.getAccountId() + " Is Monthly Withdrawal Limit Reached: " + ((SavingsAccount) a).isMontlyWithdrawalLimitReached());
            }
        }

        System.out.println();


        // Sort accounts using comparable
        Collections.sort(accounts);
        for (Account a : accounts) {
            System.out.println(a.toString());
        }

        System.out.println();

        // M3 USING STRATEGY
        // Sort strategy: the accounts by balance
        Collections.sort(accounts, new AccountBalanceComparator());
        System.out.println("xSort strategy: by balance");
        for (Account a : accounts) {
            System.out.println("\t" + a.toString());
        }


        // Sort strategy: the accounts by interest rate
        Collections.sort(accounts, new AccountInterestRateComparator());
        System.out.println("Sort strategy: by interest rate");
        for (Account a : accounts) {
            System.out.println("\t" + a.toString());
        }


        // M2 HOMEWORK ENUM USE
        for (Account a : accounts) {
            if (a instanceof CheckingAccount) {
                CheckingAccount c = (CheckingAccount) a;
                CheckingAccountType checkingAccountType = c.getCheckingAccountType();
                System.out.println("Checking account #" + c.getAccountId() + " accountType=" + checkingAccountType + " defaultMonthlyFee=" + checkingAccountType.getDefaultMonthlyFee());
            }
        }

        // M3 USING STRATEGY
        // Use strategy to determine the reward rate
        System.out.println();
        System.out.println("Default strategy: Calculate potential reward of transaction for account #" + savingsAccount.getAccountId() + " balance=" + savingsAccount.getBalance() + ", potential reward=" + savingsAccount.getPotentialBalanceReward());
        System.out.println("Balance reward strategy: Calculate potential reward of transaction for account #" + savingsAccount2.getAccountId() + " balance=" + savingsAccount2.getBalance() + ", potential reward=" + savingsAccount2.getPotentialBalanceReward());

        // M3 USING FACTORY
        LinkedAccount linkedSavingAccount = AccountFactory.createLinkedAccount(savingsAccount, savingsAccount2);
        System.out.println("Linked account(#" + linkedSavingAccount.getPrimaryAccount().getAccountId() + ", #" + linkedSavingAccount.getSecondaryAccount().getAccountId() + "), balance=" + linkedSavingAccount.getBalance());

    }

}
