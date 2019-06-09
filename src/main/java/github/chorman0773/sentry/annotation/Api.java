package github.chorman0773.sentry.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

@Documented
@Retention(RUNTIME)
public @interface Api {
	public String name();
	public String version();
	public String humanName() default "";
	public Provider provider() default @Provider();
}
