package github.chorman0773.sentry;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import github.chorman0773.sentry.authentication.AuthenticationService;
import github.chorman0773.sentry.event.ClickListener;
import github.chorman0773.sentry.event.InputListener;
import github.chorman0773.sentry.linterface.LauncherInterface;
import github.chorman0773.sentry.login.Session;
import github.chorman0773.sentry.resources.ResourcePack;
import github.chorman0773.sentry.text.I18N;
import github.chorman0773.sentry.text.TextComponent;
/**
 * Basic Class Which All Games are built with. 
 * @author Connor
 * @see github.chorman0773.sentry.annotation.Game {@link @Game}
 */
public abstract class GameBasic extends JPanel implements Runnable, Serializable, Thread.UncaughtExceptionHandler{	
	
	
	public static final Gson gson = new GsonBuilder()
	.registerTypeAdapter(TextComponent.class, new TextComponent.TextComponentDeserializer())
	.create();
	public static final JsonParser json = new JsonParser();
	
	public static I18N lang;
	public static ResourcePack currResourcePack;
		
	private static final long serialVersionUID = 7983132883186970678L;
	private static GameBasic instance;
	public static GameBasic getInstance() {
		return instance;
	}
	/**
	 * Gets the instance of GameBasic for the given class
	 * @param cl the class 
	 * @return
	 */
	public static <T extends GameBasic> T getInstance(Class<T> cl) {
		return Preconditions.assertType(instance, cl);
	}

	private Thread thread;
	private String[] args;
	private LauncherInterface launcher;
	private Random random;

	public GameBasic(){
		instance = this;
	}

	/**
	 * Called by the launcher to initialize the game. 
	 * @param args the arguments the game are launched with
	 * @param launcher an implementation defined interface to the launcher
	 */
	public final void instantiate(String[] args,LauncherInterface launcher){
		this.args = args;
		this.launcher = launcher;
		this.random = new Random(Long.getLong("sentry.random.seed", System.currentTimeMillis())&0xffffffffffffL);
	}

	/**
	 * called when an uncaught exception is reported in the code. Calls the optional uncaughtExceptionImpl(Throwable) prints the StackTrace, then exits
	 * if the error is in the runtime control thread or stops the thread otherwise
	 * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread, java.lang.Throwable)
	 */
	@Override
	public final void uncaughtException(Thread t, Throwable e) {
		uncaughtExceptionImpl(e);
		t.getThreadGroup().uncaughtException(t, e);
	}
	
	@Deprecated(forRemoval=true)
	protected final boolean isLaunched(){
		return isWindowed();
	}

	/**
	 * Implementation for uncaughtException. Optional to override.
	 * If you override it, it adds functionality to your games when they fail due to an uncaught exception.
	 * This should be used to clean-up any resources that otherwise you would only clean-up in stop or
	 * destroy as neither are called.
	 * @param e the exception reported.
	 */
	protected void uncaughtExceptionImpl(Throwable e){}

	

	/**
	 * Initializes the game, builds the thread necissary for the game to run. GameBasic implements Runnable and requires that you implement its run meathod.
	 * If you require any additional setup (e.g.Loading a specific class) override the optional initGame meathod as protected.
	 * initGame does nothing unless overriden but is completely optional to override.
	 */
	public final void init(){
		thread = new Thread(this,"Sentry-GameThread");
		thread.setUncaughtExceptionHandler(this);
		try {
			initGame();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		thread.start();
	}
	/**
	 * Method that is called by init. Can be overrided (optional) to provide additional setup before the game launches (both in the browser and in the window)
	 * @throws Exception to indicate that something went wrong
	 */
	protected void initGame() throws Exception {}
	/**
	 * Override Required method. When Extending GameBasic, you are required to add functionality for suspending the game, when the
	 * browser navigates away from the page as well as unloading the game (not the assets) when the Window closes.
	 */
	public abstract void stop();
	/**
	 * Override Required method. When Extending GameBasic, you are required to add functionality for starting (and Resuming) the game
	 * when the browser navigates to this page and starting the runtime thread that controls the game. Note that the runtime
	 * thread is started in the init method. <br/><br/>
	 * {@inheritDoc}
	 * @see Applet.start() {@literal for more info about Starting Applets}
	 */
	public abstract void start();
	/**
	 * Override Required Method. This is the main method for controlling the thread that runs the game. This Method should only return when Interuptted,
	 *  however you must be able to control the thread by suspending (using wait) and resuming asynchronously.
	 * The Thread which runs the game and the main thread that controls the game are seperate. 
	 * All actual game code must appear here. 
	 * Any out of place Game code may never run or might run erroneously. <br/><br/>
	 *
	 * {@inheritDoc}
	 */
	public abstract void run();
	
	/**
	 * The Destroy Method is used to completely exit the game. When Called, the game will immedialtely stop the runtime thread,
	 * make it null (for Garbage Collection)
	 * Do not call Destroy at all. It causes the game to terminate after calling additional optional code for handling complete termination.
	 * Destroy should only be called indirectly via exit(int).
	 * {@inheritDoc}
	 * @see exit(int)
	 */
	public final void destroy() {
		destroyOverride();
		
		thread.interrupt();
		try{
			thread.join();
		}catch(InterruptedException t){}
		thread = null;
	}
	/**
	 * Exits the game. Should only be called in a Windowed Context. Shows the Exit Code before calling stop and destroy.
	 * Do Not ever call System.exit(int), as it will exit the launcher as well.
	 * @param exitCode
	 */
	public void exit(int exitCode){
		launcher.close();
	}
	protected void destroyOverride() {}
	/**
	 * Use to determine if the applet is in a window, or if it is in a browser/applet view.
	 * the return value is the same as this.getAppletContext() instanceof JFrame.
	 * This entire method is replaced with a "return true" by the vanila sentry launcher.
	 * @return True if the game is displayd on the desktop in a window, or false otherwise
	 * @deprecated Due to Applet Based Sentry Games being removed, this method is meaningless (will always return true)
	 */
	@Deprecated(forRemoval=true)
	protected final boolean isWindowed(){
		return true;
	}
	protected final void setAuthService(AuthenticationService service) {
		this.authService = service;
	}
	

	public AuthenticationService getAuthService(){
		return this.authService;
	}
	private AuthenticationService authService;

	public Map<Integer,ClickListener> clicks;
	public Map<Integer,InputListener> inputs;
	private boolean hasAddedClickListener;
	private boolean hasAddedInputListener;

	public void addClickListener(int input,ClickListener c){
		if(!hasAddedClickListener){
			clicks = new HashMap<>();
			hasAddedClickListener = true;
			this.addMouseListener(new MouseAdapter(){

				/* (non-Javadoc)
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				@Override
				public void mouseClicked(MouseEvent e) {
					if(clicks.containsKey(e.getButton()))
						clicks.get(e.getButton()).onClick(e.getX(), e.getY());
				}

			});
		}
		this.clicks.put(input, c);

	}
	public void addInputListener(int input, InputListener i){
		if(!hasAddedInputListener){
			this.inputs = new HashMap<>();
			hasAddedInputListener = true;
			this.addKeyListener(new KeyAdapter(){

				/* (non-Javadoc)
				 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
				 */
				@Override
				public void keyTyped(KeyEvent e) {
					if(inputs.containsKey((int)e.getKeyChar()))
						inputs.get((int)e.getKeyChar()).inputSent();
				}



			});

		}
		this.inputs.put(input, i);
	}

	public Path getDirectory() {
		return launcher.getGameDirectory();
	}
	
	private PrintWriter writer;
	public PrintWriter getWriter() {
		if(writer==null)
			return writer = new PrintWriter(launcher.getLauncherWriter(),true);
		else
			return writer;
	}
	
	public LauncherInterface getLauncherInterface() {
		return launcher;
	}
	protected final Object clone()throws CloneNotSupportedException{
		throw new CloneNotSupportedException("Cannot clone a game");
	}
	public Random getRandom() {
		return random;
	}
	public Session getSession() {
		// TODO Auto-generated method stub
		return null;
	}
}
