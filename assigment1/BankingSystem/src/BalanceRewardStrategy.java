import java.math.BigDecimal;

// M3 USING STRATEGY

/**
 * Balance-based reward strategy using rate range to determine the actual rate.
 */
public class BalanceRewardStrategy implements AccountRewardStrategy {

    private static final BigDecimal LEVEL_1_RATE = new BigDecimal("0.01");
    private static final BigDecimal LEVEL_1_UPPER_RANGE = new BigDecimal("1000");

    private static final BigDecimal LEVEL_2_RATE = new BigDecimal("0.02");
    private static final BigDecimal LEVEL_2_UPPER_RANGE = new BigDecimal("10000");

    private static final BigDecimal LEVEL_3_RATE = new BigDecimal("0.03");
    private static final BigDecimal LEVEL_3_UPPER_RANGE = new BigDecimal("100000");

    private static final BigDecimal LEVEL_TOP_RATE = new BigDecimal("0.05");


    @Override
    public BigDecimal getRewardRate(Account account) {
        BigDecimal balance = account.getBalance();

        if (balance.compareTo(LEVEL_1_UPPER_RANGE) < 0) {
            return LEVEL_1_RATE;
        } else if (balance.compareTo(LEVEL_2_UPPER_RANGE) < 0) {
            return LEVEL_2_RATE;
        } else if (balance.compareTo(LEVEL_3_UPPER_RANGE) < 0) {
            return LEVEL_3_RATE;
        } else {
            return LEVEL_TOP_RATE;
        }

    }

}
