package github.chorman0773.sentry.test;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.swing.JFrame;

import github.chorman0773.sentry.GameBasic;
import github.chorman0773.sentry.annotation.Game;
import github.chorman0773.sentry.cci.CCIVendor;
import github.chorman0773.sentry.linterface.LauncherInterface;
import github.chorman0773.sentry.linterface.ModInterface;
import github.lightningcreations.lcjei.IEngineInterface;
import github.lightningcreations.lcjei.IGameInfo;

public final class TestLauncher implements LauncherInterface {
	private final GameBasic game;
	private final String[] args;
	private final Game gannot;
	private Window w;
	private Path p;
	private Container c;
	private volatile boolean suspended;
	private volatile boolean initialized;
	private volatile boolean executing;
	private IGameInfo<GameBasic> info = new IGameInfo<>() {

		@Override
		public Class<? extends GameBasic> getGameClass() {
			// TODO Auto-generated method stub
			return game.getClass();
		}

		@Override
		public Class<? extends IEngineInterface<GameBasic>> getEngineInterfaceClass() {
			// TODO Auto-generated method stub
			return TestLauncher.class;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return gannot.gameName();
		}

		@Override
		public String getVersion() {
			// TODO Auto-generated method stub
			return gannot.gameVersion();
		}

		@Override
		public UUID getGameId() {
			// TODO Auto-generated method stub
			return UUID.fromString(gannot.uuid());
		}
		
	};
	
	public TestLauncher(GameBasic g,String[] args) {
		this.game = g;
		this.args = args;
		this.gannot = g.getClass().getAnnotation(Game.class);
		p = FileSystems.getDefault().getPath(".").toAbsolutePath();
		g.instantiate(args, this);
	}
	@Override
	public String[] getGameArguments() {
		// TODO Auto-generated method stub
		return args;
	}

	@Override
	public String getProperty(String name) {
		// TODO Auto-generated method stub
		return System.getProperty(name);
	}

	@Override
	public ModInterface[] activeMods() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CCIVendor getLauncherVendor() {
		// TODO Auto-generated method stub
		return CCIVendor.getFromCaller();
	}

	@Override
	public Window getGameWindow() {
		// TODO Auto-generated method stub
		return w;
	}

	@Override
	public Path getGameDirectory() {
		// TODO Auto-generated method stub
		return p;
	}

	@Override
	public Instrumentation getInstrumentation() {
		throw new UnsupportedOperationException("getInstrumentation");
	}

	@Override
	public void close() {
		destroy();
		System.exit(0);
	}

	@Override
	public Writer getLauncherWriter() {
		// TODO Auto-generated method stub
		return new OutputStreamWriter(System.out);
	}

	@Override
	public SecurityManager getSandbox() {
		// TODO Auto-generated method stub
		return System.getSecurityManager();
	}

	@Override
	public void disableMods() {
		
	}

	@Override
	public Game getGameAnnotation() {
		// TODO Auto-generated method stub
		return gannot;
	}

	@Override
	public ModInterface installMod(URI name) throws UnsupportedOperationException, IOException {
		return null;
	}
	@Override
	public synchronized boolean initialize(Container c) throws IllegalStateException {
		if(this.initialized)
			throw new IllegalStateException("initialize applied to initialized game");
		if(c==null) {
			initialize();
			return false;
		}
		if(!c.isDisplayable())
			throw new IllegalArgumentException();
		this.c = c;
		c.add(game);
		this.initialized = true;
		return true;
	}
	@Override
	public synchronized void run() throws IllegalStateException {
		if(!this.initialized||this.executing)
			throw new IllegalStateException("Cannot execute run on this current Launcher");
		game.init();
		game.start();
		
	}
	@Override
	public Container getCurrentDrawContainer() {
		// TODO Auto-generated method stub
		return c;
	}
	@Override
	public GameBasic getGameObject() {
		// TODO Auto-generated method stub
		return game;
	}
	@Override
	public IGameInfo<GameBasic> getGameInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public synchronized void destroy() {
		if(!this.initialized)
			throw new IllegalStateException("This Engine has not yet been initialized or has been already destroyed");
		
		if(!this.suspended)
			game.stop();
		game.destroy();
		if(w!=null)
			w.dispose();
		w = null;
		c = null;
		this.initialized = false;
	}
	@Override
	public void initialize() {
		try {
			if(this.initialized)
				throw new IllegalStateException("This Engine has already been initialized");
			EventQueue.invokeAndWait(()->{
				JFrame f = new JFrame(gannot.gameName()+" "+gannot.gameVersion());
				f.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosing(WindowEvent e) {
						destroy();
					}

					@Override
					public void windowIconified(WindowEvent e) {
						game.stop();
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						game.start();
					}
					
				});
				f.setSize(game.getSize());
				f.setResizable(false);
				f.add(game);
				f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				f.setVisible(true);
				this.w = f;
			});
			c = game;
			this.initialized = true;
		} catch (InvocationTargetException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public synchronized void resume() throws IllegalStateException {
		if(!this.executing||!this.suspended)
			throw new IllegalStateException("The Game must be suspended to resume");
		this.suspended = false;
		game.start();
	}
	@Override
	public synchronized void suspend() throws IllegalStateException {
		if(!this.executing||this.suspended)
			throw new IllegalStateException("The Game must be executing to be suspended");
		game.stop();	
		this.suspended = true;
	}
	

}
