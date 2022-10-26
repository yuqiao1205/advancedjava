import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public abstract class Account implements Comparable<Account> {

    // M2 HOMEWORK STATIC
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    // M2 HOMEWORK STATIC
    private static final int CURRENCY_PLACES = 2;

    // M2 HOMEWORK STATIC METHOD
    public static BigDecimal roundToCurrency(BigDecimal value) {
        return value.setScale(CURRENCY_PLACES, ROUNDING_MODE);
    }

    // M3 USING BUILDER
    public static class Builder {

        protected long customerId;
        protected long accountId;
        protected BigDecimal interestRate = BigDecimal.ZERO;
        protected BigDecimal balance = BigDecimal.ZERO;
        protected AccountRewardStrategy accountRewardStrategy = new FlatRateRewardStrategy();

        public void setAccountRewardStrategy(AccountRewardStrategy accountRewardStrategy) {
            this.accountRewardStrategy = accountRewardStrategy;
        }

        public Builder setCustomerId(long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setInterestRate(BigDecimal interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        public Builder setBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder setAccountId(long accountId) {
            this.accountId = accountId;
            return this;
        }

    }

    protected long customerId;
    protected long accountId;
    protected BigDecimal interestRate;
    protected BigDecimal balance;
    protected AccountRewardStrategy accountRewardStrategy;

    // M3 USING BUILDER
    public Account(Builder builder) {
        this.customerId = builder.customerId;
        this.accountId = builder.accountId;
        this.balance = roundToCurrency(builder.balance);
        this.interestRate = builder.interestRate;
        this.accountRewardStrategy = builder.accountRewardStrategy;
    }

    public Account(long customerId, long accountId, BigDecimal balance, BigDecimal interestRate) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.balance = roundToCurrency(balance);
        this.interestRate = interestRate;
    }

    public abstract String getAccountTypeId();

    public void deposit(BigDecimal depositAmount) {

        if (depositAmount.doubleValue() > 0) {
            balance = balance.add(roundToCurrency(depositAmount));
            // balance += depositAmount;
            System.out.println("Account: #" + accountId + " new balance is $" + balance + " after $" + depositAmount + " deposit!");
        } else {
            System.out.println("Account: #" + accountId + " deposit amount must be positive!  Provided=$" + depositAmount + ".");
        }

    }

    public void withdrawal(BigDecimal withdrawalAmount) {
        if (withdrawalAmount.doubleValue() > 0) {
            balance = balance.subtract(roundToCurrency(withdrawalAmount));
            System.out.println("Account: #" + accountId + " new balance is $" + balance + " after $" + withdrawalAmount + " withdraw!");
        } else {
            System.out.println("Account: #" + accountId + " withdraw amount must be positive!  Provided=$" + withdrawalAmount + ".");
        }
    }

    public void transfer(Account recipientAccount, BigDecimal transferAmount) {
        if (balance.compareTo(roundToCurrency(transferAmount)) >= 0) {
            System.out.println("Transferring: $" + transferAmount + " from account #" + accountId + " to account #" + recipientAccount.getAccountId());
            withdrawal(transferAmount);
            recipientAccount.deposit(transferAmount);
        } else {
            System.out.println("Current account: #" + accountId + " doesn't have enough amount!  Need amount=$" + transferAmount + ".");
        }
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Return the potential reward amount related to the balance.
     *
     * @return potential reward amount.
     */
    // M3 USING STRATEGY
    public BigDecimal getPotentialBalanceReward() {
        return this.balance.multiply(accountRewardStrategy.getRewardRate(this));
    }


    // Factory method

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("customerId=").append(customerId);
        sb.append(", accountId=").append(accountId);
        sb.append(", balance=").append(balance);
        sb.append(", interestRate=").append(interestRate);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return customerId == account.customerId &&
                accountId == account.accountId &&
                Objects.equals(interestRate, account.interestRate) &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, accountId, balance, interestRate);
    }

    @Override
    public int compareTo(Account that) {
        if (this.balance.compareTo(that.balance) < 0) {
            return -1;
        } else if (this.balance.compareTo(that.balance) > 0) {
            return 1;
        }

        if (this.accountId < that.accountId) {
            return -1;
        } else if (this.accountId > that.accountId) {
            return 1;
        }

        if (this.customerId < that.customerId) {
            return -1;
        } else if (this.customerId > that.customerId) {
            return 1;
        }
        return 0;
    }

}
