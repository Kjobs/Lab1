package test.action;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * @author Kobs
 *
 */
@Retention(RUNTIME)
@Target(PACKAGE)
public @interface PkgAnnotation {

}
