package github.chorman0773.sentry.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@literal @Game} is an Annotation  which allows the Sentry Game Launcher to generate the info
 * for a game. This data is necessary to launch the game.
 * The Bare Minimum requirement is that you provide a uuid, a gameId, a gameName, a gameVersion,
 * and a serialId. It may also be prudent to provide a value for displayVerison, 
 * and allowsMods if the defaults are unsatifactory. dependencyUrls may also be something you should provide
 * if you need to "depend" on certain libraries, but using the libraries feature in the 
 * launch.xml file works very well. Finally advanced users can load external classes using the
 * classesToInit value. However this can throw an exception which can crash the game.
 * @author Connor
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Game {
	/**
	The unique Identifier of this game (the string representation of a UUID)
	Used to guarentee uniqueness
	<br/>As stated in the GameBasic class, this should be a Version 1, Variant 2 UUID, but can legally be any
	UUID, as long as it is a UUID. GUID's enclosed in "{}" are illegal and will cause the following error message to be displayed
	<br/>Cannot launch game: Malformed {@literal @Game} annotation.<br/>Illegal UUID given
	*/
	String uuid();
	/**
	The Domain of the game, which is a java legal identifier (unqualified), that ids this game. 
	Should be in lowerCamelCase
	*/
	String gameId();
	/**
	The human readable name of this game, displayed in the window of the game
	*/
	String gameName();
	/**
	The version of the game. Requires LCLib Version Format
	*/
	String gameVersion();
	/**
	Whether or not the Title of the Game should include the version after the name.<br/>
	defaults to false
	*/
	boolean displayVersion()default false;
	/**
	The serialVersionUID of the game. <br/>
	Used in determining The Serial Identity of the game.<br/>
	This is being phased out but is still useable
	*/
	long serialId() default 0;
	/**
	Whether or not the game allows mods to be added via the launcher<br/>
	Note: This does not prevent Custom Clients from being used, and custom launchers may ignore this property and inject mods anyways. This is only respected in the vanilla, 1st party sentry launcher.<br/>
	defaults to true<br/>
	If mods are loaded they can be forced to be disabled through the Launcher Interface
	*/
	boolean allowsMods() default true;
	
	/**
	 * The provider for the game. By default its the provider that is specified by the launch descriptor (if such a provider exists)
	 */
	public Provider provider() default @Provider();
	
	/**
	 * The behavior of a ClassInitializer if the class initializer for the target class throws an exception.
	 * @author chorm
	 */
	public enum ClassInitFailBehavior{
		/**
		 * Causes the game to crash when a class initializer for classesToInit throws an exception
		 */
		CRASH,
		/**
		 * Ignores any failing initializer for classesToInit
		 */
		IGNORE,
		/**
		 * Same as ignore, but the launcher is requested to display a non-failing diagnostic to the Launcher output frame.
		 */
		WARN;
	}
	/**
	 * A descriptor for classesToInit.
	 */
	public @interface ClassInitializer{
		/**
		 * The class to initialize
		 */
		public Class<?> value();
		/**
		 * The behavior when the initializer throws an exception. By default, it causes the game to crash
		 * @see ClassInitFailBehavior
		 */
		public ClassInitFailBehavior failBehavior() default ClassInitFailBehavior.CRASH;
	}
	/**
	 * List of Classes to Initialize
	 */
	public ClassInitializer[] classesToInit() default {};

}
