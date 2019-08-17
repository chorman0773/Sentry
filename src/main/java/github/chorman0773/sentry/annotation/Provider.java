package github.chorman0773.sentry.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Class for wrapping a provider as an annotation.
 * @author chorm
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Provider {
	public String id() default "";
	public String humanName() default "";
	public String publicKey() default "";
}
