import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

public class School implements Comparable<School> {

    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);

    private static final NumberFormat NUMBER_FORMATTER = NumberFormat.getNumberInstance(Locale.US);

    static {
        CURRENCY_FORMATTER.setMaximumFractionDigits(0);
        NUMBER_FORMATTER.setMaximumFractionDigits(0);
    }

    private int recipients;

    /**
     * Constructor.
     *
     * @param id         id of the school.
     * @param name       name of the school.
     * @param state      state of school.
     * @param awards     awards amount.
     * @param recipients number of recipients.
     */
    public School(long id, String name, String state, long awards, int recipients) {
        this.id = id;
        this.awards = awards;
        this.recipients = recipients;
        this.name = name;
        this.state = state;
    }

    public int getRecipients() {
        return recipients;
    }

    private long id;
    private String name;
    private String state;
    private long awards;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("School{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", awards=").append(CURRENCY_FORMATTER.format(awards));
        sb.append(", recipients=").append(NUMBER_FORMATTER.format(recipients));
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipients, id, name, state, awards);
    }

    static class AwardsComparator implements Comparator<School>, Serializable {
        public int compare(School a, School b) {
            return Long.signum(b.awards - a.awards);
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this)
            return true;

        if (!(obj instanceof School)) {
            return false;
        }

        return this.id == ((School) obj).id;

    }

    @Override
    public int compareTo(School school) {
        if (school == this)
            return 0;

        return Long.compare(id, school.getId());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public long getAwards() {
        return awards;
    }

    static class RecipientsComparator implements Comparator<School>, Serializable {
        public int compare(School a, School b) {
            return b.recipients - a.recipients;
        }
    }

    static class AwardsPerRecipientComparator implements Comparator<School>, Serializable {
        public int compare(School a, School b) {
            return (int) Math.signum(((double) b.awards / b.recipients) - ((double) a.awards / a.recipients));
        }
    }

}
