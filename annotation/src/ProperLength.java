import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ProperLength {
    int minLength() default 1;
    int maxLength() default 255;
}
