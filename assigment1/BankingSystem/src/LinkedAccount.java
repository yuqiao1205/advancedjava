import java.math.BigDecimal;

/**
 * Composite account for two linked accounts: primary and secondary.
 */
public class LinkedAccount {

    private final Account primaryAccount;

    private final Account secondaryAccount;

    public Account getPrimaryAccount() {
        return primaryAccount;
    }

    public Account getSecondaryAccount() {
        return secondaryAccount;
    }

    public LinkedAccount(Account primaryAccount, Account secondaryAccount) {
        this.primaryAccount = primaryAccount;
        this.secondaryAccount = secondaryAccount;
    }

    public BigDecimal getBalance() {
        return primaryAccount.getBalance().add(secondaryAccount.getBalance());
    }

}
