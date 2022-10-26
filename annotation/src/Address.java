import java.lang.annotation.*;
import java.lang.reflect.*;

public class Address {

    @ProperLength(minLength = 1, maxLength = 255)
    private String street;

    @ProperLength(minLength = 0, maxLength = 255)
    private String street2;

    @ProperLength(minLength = 1, maxLength = 40)
    private String city;

    @ProperLength(minLength = 2, maxLength = 2)
    private String state;

    @ProperLength(minLength = 5, maxLength = 5)
    private String zip;

    public Address(String street, String street2, String city, String state, String zip) throws IllegalArgumentException {
        this.street = street;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zip = zip;

        validateLengths();
    }

    private void validateLengths() throws IllegalArgumentException {
        // YOUR CODE HERE
        Field[] fields = Address.class.getDeclaredFields();
        for (Field field : fields) {

            try {

                Annotation annotation = field.getAnnotation(ProperLength.class);
                if (annotation instanceof ProperLength) {
                    ProperLength properLength = (ProperLength) annotation;

                    int minLength = properLength.minLength();
                    int maxLength = properLength.maxLength();

                    String fieldValue = (String) field.get(this);

                    if (fieldValue == null && minLength > 0) {
                        throw new IllegalArgumentException(String.format("Invalid address value [%s]; cannot be null.", field.getName()));
                    } else {
                        int fieldLength = fieldValue.length();

                        if (minLength == maxLength && minLength != fieldLength) {
                            throw new IllegalArgumentException(String.format("Invalid address value [%s=\"%s\"]; %s must be exactly %d characters.", field.getName(), fieldValue, field.getName(), minLength));
                        } else if (fieldLength < minLength) {
                            throw new IllegalArgumentException(String.format("Invalid address value [%s=\"%s\"]; %s must be %d or more characters.", field.getName(), fieldValue, field.getName(), minLength));
                        } else if (fieldLength > maxLength) {
                            throw new IllegalArgumentException(String.format("Invalid address value [%s=\"%s\"]; %s can have a maximum of %d characters.", field.getName(), fieldValue, field.getName(), maxLength));
                        }
                    }

                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String toString() {
        String s = street + (street2.length() > 0 ? "\t" + street2 : "") +
                "\t" + city + ", " + state + " " + zip;
        return s;

    }


}
