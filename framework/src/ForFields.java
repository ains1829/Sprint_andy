package etu1829.framework.annotation;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ForFields {
    String valeur() default "valeur par défaut";
}
