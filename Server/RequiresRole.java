import java.lang.annotation.*;

/*
* Java Annotation to specify and describe the role of the Online Marketplace users,
* which will be either Customer or Administrator.
*/

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface RequiresRole 
{
    String value();
}