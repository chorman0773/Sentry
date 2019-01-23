package github.chorman0773.sentry.test;

import java.awt.Window;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.instrument.Instrumentation;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import github.chorman0773.sentry.GameBasic;
import github.chorman0773.sentry.annotation.Game;
import github.chorman0773.sentry.cci.CCIVendor;
import github.chorman0773.sentry.linterface.LauncherInterface;
import github.chorman0773.sentry.linterface.ModInterface;

public final class TestLauncher implements LauncherInterface {
	private final GameBasic game;
	private final String[] args;
	private final Game gannot;
	private Window w;
	private Path p;
	public TestLauncher(GameBasic g,String[] args) {
		this.game = g;
		this.args = args;
		this.gannot = g.getClass().getAnnotation(Game.class);
		
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
		return null;
	}

	@Override
	public Window getGameWindow() {
		// TODO Auto-generated method stub
		return w;
	}

	@Override
	public Path getGameDirectory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instrumentation getInstrumentation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		w.setVisible(false);
		w.dispose();
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

}
