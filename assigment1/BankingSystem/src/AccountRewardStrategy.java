import java.math.BigDecimal;

// M3 USING STRATEGY

/**
 * Strategy to determine the account's reward ratio rate.
 */
public interface  AccountRewardStrategy {
    /**
     * Get account reward ratio by reward strategy.
     *
     * @param account account object.
     * @return calculated reward ratio.
     */
    BigDecimal getRewardRate(Account account);
}
