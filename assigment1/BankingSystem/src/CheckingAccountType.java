import java.math.BigDecimal;

// M2 HOMEWORK ENUM
public enum CheckingAccountType {

    PERSONAL(new BigDecimal("15")), PREMIUM_PERSONAL(), STUDENT, BUSINESS(new BigDecimal("30"));

    private final BigDecimal defaultMonthlyFee;

    CheckingAccountType(BigDecimal defaultMonthlyFee) {
        this.defaultMonthlyFee = defaultMonthlyFee;
    }

    CheckingAccountType() {
        this(BigDecimal.ZERO);
    }

    public BigDecimal getDefaultMonthlyFee(){
        return defaultMonthlyFee;
    }

}