package github.chorman0773.sentry.linterface;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.lang.instrument.Instrumentation;
import java.net.URI;
import java.nio.file.Path;
import java.util.UUID;

import github.chorman0773.sentry.annotation.Game;
import github.chorman0773.sentry.cci.CCIVendor;

/**
 * Interface for most Standard Sentry Launcher Interfaces
 * @author connor
 *
 */
public interface LauncherInterface {
	/**
	 * Gets the list of Arguments passed to the game
	 */
	public String[] getGameArguments();
	/**
	 * Gets the property with the given name in the launch file or null if its not defined
	 */
	public String getProperty(String name);
	/**
	 * Lists the active (running mods) that are in this Game (optional operation)
	 * @throws UnsupportedOperationException if the Launcher does not support Mod Interfaces
	 */
	public ModInterface[] activeMods()throws UnsupportedOperationException;
	/**
	 * Obtains the vendor of the running launcher
	 */
	public CCIVendor getLauncherVendor();
	/**
	 * Obtains the Window that the game is running in.
	 */
	public Window getGameWindow();
	
	/**
	 * Gets the base directory that the game is running from
	 */
	public Path getGameDirectory();
	
	/**
	 * Obtains an instrumentation instance to manipulate the class environment (optional operation)
	 * @throws UnsupportedOperationException if the Launcher does not support Instrumentation
	 * @throws SecurityException if the Sandbox does not permit instrumenting the class environment
	 */
	public Instrumentation getInstrumentation();
	
	/**
	 * Called when the game exits
	 */
	void close();
	
	/**
	 * Obtains a writer to print to the launcher terminal
	 */
	public Writer getLauncherWriter();
	
	/**
	 * Gets the Sandbox environment. 
	 * Effectively returns System.getSecurityManager() unless the System Security Manager is replaced.
	 */
	public SecurityManager getSandbox();
	
	/**
	 * Disables all mods running in the game environment
	 */
	public void disableMods();
	
	/**
	 * Obtains the Game Annotation for the Game's class.
	 */
	public Game getGameAnnotation();
	
	/**
	 * Installs the given mod to the system and performs 
	 * 	Phase 1-3 of Game Launch on the mod as normal. 
	 * This is an optional operation, except during Game Launch Phase 2.
	 * The URI must name a valid Mod Descriptor file. Otherwise an unspecified subclass of SentryException is thrown
	 * The method fails unconditionally and returns null if a call to disableMods was made previously.
	 * @throws UnsupportedOperationException if the call does not originate during Game Launch and the operation is not provided at any other time 
	 * @throws IOException if the operation fails
	 * @throws SentryException if the uri names something other then a well formed Mod Descriptor file
	 */
	public ModInterface installMod(URI name) throws UnsupportedOperationException, IOException;
}
