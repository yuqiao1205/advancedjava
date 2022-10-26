import java.util.Comparator;

public class AccountBalanceComparator implements Comparator<Account> {

    @Override
    public int compare(Account a1, Account a2) {
        if (a1.balance.compareTo(a2.balance) < 0) {
            return 1;
        } else if (a1.balance.compareTo(a2.balance) > 0) {
            return -1;
        }

        if (a1.accountId < a2.accountId) {
            return -1;
        } else if (a1.accountId > a2.accountId) {
            return 1;
        }

        if (a1.customerId < a2.customerId) {
            return -1;
        } else if (a1.customerId > a2.customerId) {
            return 1;
        }
        return 0;
    }

}
