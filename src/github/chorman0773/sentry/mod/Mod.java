package github.chorman0773.sentry.mod;

import github.chorman0773.sentry.annotation.Provider;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Mod {
	public String modname();
	public String version();
	public String modid();
	public String target();

	public Provider provider() default @Provider();
	public String targetVersionRange() default "*";
	public @interface Dependency{
		public String mod();
		public String acceptableVersionRange() default "*";
	}
	public Dependency[] dependencies() default {};
	public Dependency[] softDepend() default {};
	
	public @interface Property{
		public String key();
		public String value();
	}
	public Property[] properties() default {};
}