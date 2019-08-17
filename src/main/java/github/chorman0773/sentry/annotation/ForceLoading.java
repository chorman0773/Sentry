package github.chorman0773.sentry.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import github.lightningcreations.lclib.annotation.ASMMarker;

/**
 * ForceLoading is an annotation that tells the Sentry Launcher that the class must be
 * initialized in the memory, and should always exist.
 * This class may result in extra code being generated (ASM Code Generator)
 * @author Connor
 * @deprecated this annotation is a hint and is not guaranteed to be respected. Use {@link Game#classesToInit()} instead.
 */
@Documented
@Target(TYPE)
@ASMMarker
public @interface ForceLoading {

}
