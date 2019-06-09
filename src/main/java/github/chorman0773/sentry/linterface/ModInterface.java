package github.chorman0773.sentry.linterface;

import java.util.UUID;

import github.chorman0773.sentry.cci.CCIVendor;
import github.lightningcreations.lclib.Version;

/**
 * Interface class provided by the launcher for interacting with Sentry Mods
 * which are currently running with the mod.
 * This class may be useful for games which can optimize certain mods, or need to know about what mods are running.
 * @author connor
 *
 */
public interface ModInterface {
	/**
	 * Gets the object which is assigned to the Mod (optional operation)
	 * @return an instance of the SentryMod class (from Sentry Modification framework)
	 * @throws UnsupportedOperationException if the Active Launcher does not support obtaining Mod instances or the Mod does not have an assigned instance
	 */
	Object getModObject()throws UnsupportedOperationException;
	/**
	 * Obtains the class which the mod is running.
	 * This is more stable then getModObject().getClass() as getModClass() is required to be supported, while getModObject() is not
	 */
	Class<?> getModClass();
	/**
	 * Obtains a Human Readable name of the mod	 (optional operation)
	 * @throws UnsupportedOperationException if the Launcher or Mod does not support Human Readable Names
	 */
	String getHumanModName()throws UnsupportedOperationException;
	
	/**
	 * Obtains the UUID of the mod
	 */
	UUID getModUUID();
	
	/**
	 * Obtains the Version of the Mod
	 */
	Version getVersion();
	
	/**
	 * Obtains a string representing the Range of Valid Versions of the Current Game
	 * @throws UnsupportedOperationException if the Launcher or Mod does not support Version Ranges
	 */
	String versionRange();
	
	/**
	 * Gets the game which the mod is targeted for
	 */
	UUID targetGame();
	
	/**
	 * Registers a loading hook for the mod during a given phase of Loading. (optional operation)
	 * The phase may be One of the 2 Standard Loading Phases, starting from phase 2, or an Implementation Defined Non-standard Loading Phase
	 * If an unsupported phase is given, it is Unspecified whether the request is silently ignored or an exception is thrown
	 * If phase 0 or phase 1 is passed in, the request is silently ignored.
	 * @throws IllegalArgumentException if phase is negative or not defined by the launcher, and the launcher chooses to throw an exception
	 * @throws NullPointerException if hook is null
	 * @throws UnsupportedOperationException if the Launcher or Mod does not support registering hooks
	 */
	void registerLoadingHook(int phase,Runnable hook)throws IllegalArgumentException, NullPointerException, UnsupportedOperationException;
	
	/**
	 * Gets the Vendor of the Mod or null if the mod does not provide a vendor.
	 */
	CCIVendor getVendor();
	/**
	 * Disables this mod.
	 * This operation is required to be supported if Mod Interfaces are supported
	 */
	void disable();
}
