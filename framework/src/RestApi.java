package etu1829.framework.annotation;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RestApi {
    String value() default "aonnaa";
}
