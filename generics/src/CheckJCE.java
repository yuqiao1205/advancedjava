import javax.crypto.Cipher;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;

public class CheckJCE {
    private static final String EOL = System.getProperty("line.separator");

    public static void main(final String[] args) throws Exception {
        final Provider[] providers = Security.getProviders();
        final Boolean verbose = Arrays.asList(args).contains("-v");
        for (final Provider p : providers) {
            System.out.format("%s %s%s", p.getName(), p.getVersion(), EOL);
            for (final Object o : p.keySet()) {
                System.out.format("\t%s : %s%s", o, p.getProperty((String) o), EOL);
            }
        }

        System.out.println("AES Key Length=" + Cipher.getMaxAllowedKeyLength("AES"));
    }
}
