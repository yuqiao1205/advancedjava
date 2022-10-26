import java.math.BigDecimal;

public class AccountFactory {

    private static final BigDecimal DEFAULT_CHECKING_INTEREST_RATE = new BigDecimal("0.01");

    private static final BigDecimal DEFAULT_CHECKING_OVERDRAFT_FEE = new BigDecimal("35.0");


    // M3 Factory method to create a linked account
    public static LinkedAccount createLinkedAccount(Account primary, Account secondary) {
        return new LinkedAccount(primary, secondary);
    }

    // M3 Factory method to create a default checking account
    public static CheckingAccount createDefaultCheckingAccount(int customerId, long accountId, BigDecimal balance) {

        CheckingAccount.Builder checkingAccountBuilder = new CheckingAccount.Builder();

        checkingAccountBuilder.setCustomerId(customerId).setAccountId(accountId).setBalance(balance);

        // Set default values
        checkingAccountBuilder.setInterestRate(DEFAULT_CHECKING_INTEREST_RATE);
        checkingAccountBuilder.setOverDraftFee(DEFAULT_CHECKING_OVERDRAFT_FEE);

        return checkingAccountBuilder.build();

    }


    // M3 USING BUILDER
    public static SavingsAccount createDefaultSavingAccount(int customerId, long accountId, BigDecimal balance) {

        SavingsAccount.Builder savingAccountBuilder = new SavingsAccount.Builder();
        savingAccountBuilder.setCustomerId(customerId).setAccountId(accountId).setBalance(balance).setInterestRate(new BigDecimal("0.03"));
        savingAccountBuilder.setMonthlyWithdrawalLimits(2);

        return savingAccountBuilder.build();

    }

    // M3 USING BUILDER
    public static CDAccount createDefaultCDAccount(int customerId, long accountId, BigDecimal balance) {

        // M3 USING BUILDER
        CDAccount.Builder cdAccountBuilder = new CDAccount.Builder();
        cdAccountBuilder.setCustomerId(customerId).setAccountId(accountId).setBalance(balance).setInterestRate(new BigDecimal("0.05"));
        cdAccountBuilder.setDefaultMaturityPeriodMonths(12);

        return cdAccountBuilder.build();

    }

}
