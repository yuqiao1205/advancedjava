public class BankAccountException extends Exception {
    public BankAccountException(String message) {
        super(message);
    }

    public BankAccountException(String message, Throwable throwable) {
        super(message, throwable);
    }
}