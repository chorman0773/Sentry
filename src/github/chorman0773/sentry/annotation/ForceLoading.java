package github.chorman0773.sentry.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import github.lightningcreations.lclib.annotation.ASMMarker;

/**
 * ForceLoading is an annotation that tells the Sentry Launcher that the class must be
 * initialized in the memory, and should always exist.
 * This is more of a Recommendation then a Requirement.
 * The Official Sentry Launcher respects this annotation but implementations may not
 * To ensure that the class is loaded either add it to the preload classes block of
 * the Game Information file or in the Game Annotation (Prefer info in Game Information to Annotation)
 * This class may result in extra code being generated (ASM Code Generator)
 * @author Connor
 *
 */
@Documented
@Target(TYPE)
@ASMMarker
public @interface ForceLoading {

}
