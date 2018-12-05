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
	<br/>If this identifier is not a java legal identifier under the ROOT locale the following error message will be displayed
	<br/>Cannot launch game: Malformed {@literal @Game} annotation<br/> value of gameId is not an identifier.
	<br/>Unlike actual java identifiers this can technically include reserved words.
	*/
	String gameId();
	/**
	The human readable name of this game, displayed in the window of the game
	*/
	String gameName();
	/**
	The version of the game. Should be in {@literal<MAJOR>.<MINOR>.<BUILD>} but can be any versioning format.
	*/
	String gameVersion();
	/**
	Whether or not the Title of the Game should include the version after the name.<br/>
	defaults to false
	*/
	boolean displayVersion()default false;
	/**
	The serialVersionUID of the game. <br/>
	Used in determining The Serial Identity of the game.
	*/
	long serialId();
	/**
	Whether or not the game allows mods to be added via the launcher<br/>
	Note: This does not prevent Custom Clients from being used, and custom launchers may ignore this property and inject mods anyways. This is only respected in the vanilla, 1st party sentry launcher.<br/>
	defaults to true<br/>
	If mods are loaded they can be forced to be disabled through the Launcher Interface
	*/
	boolean allowsMods() default true;
	/**
	A List of urls pointing to jars to classload. This does not guarentee that the jars are opened at the same scope as the Game. To do so, place the dependancies in the ".launch" file.<br/>
	defaults to an empty array
	<br/> if Any of the urls are malformed or do not exist then the below error message will be displayed
	<br/>Unable to launch: Malformed {@literal @Game} Annotation<br/>One or more dependancyUrls is malformed or does not exist
	*/
	String[] dependancyUrls() default {};
	/**
	A List of fully qualified class names to initialize (by calling Class.forName(<name>,true,loader))
	This may cause an exception, which will crash the game. So it is more reliable to provide a launch.xml file.<br/>
	defaults to an empty array.
	<br/>Annother reliable way to load classes for within a games code is to add the {@literal @ForceLoading} annotation
	to the class. This only works for the game itself however.
	*/
	String[] classesToInit() default {};

}
