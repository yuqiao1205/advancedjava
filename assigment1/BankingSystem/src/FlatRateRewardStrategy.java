import java.math.BigDecimal;

// M3 USING STRATEGY
/**
 * Flat-rate reward strategy: using a flat rate.
 */
public class FlatRateRewardStrategy implements AccountRewardStrategy {

    private static final BigDecimal DEFAULT_FLAT_RATE = new BigDecimal("0.01");

    @Override
    public BigDecimal getRewardRate(Account account) {
        return DEFAULT_FLAT_RATE;
    }
}
