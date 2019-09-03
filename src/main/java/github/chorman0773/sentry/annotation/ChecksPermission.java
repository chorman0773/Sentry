package github.chorman0773.sentry.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

/**
 * Indicates the the following method can check a specified permission
 *  with the System Security Manager. This may result in a {@link SecurityManager}.<br/>
 *  
 * Note that this is not enforced. It is the responsibility of the annotated method
 *  to actually check the permission. This is more of a hint in place of a statement of security checks.
 *  
 *  
 * @author chorm
 * @see SecurityManager
 * @see SecurityException
 */
@Documented
@Target({ METHOD, CONSTRUCTOR })
public @interface ChecksPermission {
	/**
	 * The permission checked. This should be in format KindPermission:string but nothing is enforced.
	 * If KindPermission is not provided, it can be assumed SentryPermission.
	 * Additional details may appear in the method documentation.
	 */
	public String value();
}
