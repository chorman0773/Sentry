package github.chorman0773.sentry.linterface;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.lang.instrument.Instrumentation;
import java.net.URI;
import java.nio.file.Path;
import java.util.UUID;

import github.chorman0773.sentry.GameBasic;
import github.chorman0773.sentry.annotation.Game;
import github.chorman0773.sentry.cci.CCIVendor;
import github.lightningcreations.lcjei.IEngineInterface;
import github.lightningcreations.lcjei.IGameInfo;
import github.lightningcreations.lcjei.resources.ResourceSet;

/**
 * Interface for most Standard Sentry Launcher Interfaces
 * @author connor
 *
 */
public interface LauncherInterface extends IEngineInterface<GameBasic> {
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
	@Deprecated
	public CCIVendor getLauncherVendor();
	/**
	 * Obtains the Window that the game is running in.<br/>
	 * By default, the Engine is initialized to draw to this window.<br/>
	 * @deprecated Due to the introduction of LCJEI, it is possible for a Sentry game to be embedded into a Container that is not a Window. 
	 * In these cases, the method returns null. Use {@link #getCurrentDrawContainer()} instead. 
	 */
	@Deprecated
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
	 * Called to exit the game. <br/>
	 * 
	 *  A call to close() implies a call to destroy(). The launcher cannot be reinitialized after a call to close().<br/>
	 *  This method may only be called if initiailized using a frame provided by the implementation. 
	 *   Calling this method in violation of this Contract or the contract provided by destory() causes an {@link java.lang.IllegalStateException} to be thrown.<br/>
	 *  @see destroy() to exit the game on a temporary basis. 
	 *  @throws IllegalStateException if the contract defined here or by {@link #destroy()} is violated.
	 */
	void close() throws IllegalStateException;
	
	/**
	 * Returns information about the current game.<br/>
	 * Unlike {@link IEngineInterface#getGameInfo()}, this method is Mandatory.<br/>
	 * Method calls for the returned IGameInfo are required to match the values associated with the Game Annotation.<br/>
	 */
	@Override
	IGameInfo<GameBasic> getGameInfo();
	
	/**
	 * Destroys the Game Instance, calling stop and destroy in sequence if not already called.<br/>
	 * It is possible to create a new window for the game by calling {@link LauncherInterface#initialize()}.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	public void destroy();
	
	/**
	 * Constructs a new Window in an implementation defined manner, and binds the game to it.<br/>
	 * After this call, {@link IEngineInterface#getCurrentDrawContainer()} will return the same as {@link LauncherInterface#getGameWindow()}.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	public void initialize();
	
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
	
	/**
	 * Gets a ResourceSet for use by the game from the launcher.
	 * This supersedes the use of {@link github.chorman0773.sentry.resources}
	 */
	public ResourceSet<Path> getResourceSet();
}
