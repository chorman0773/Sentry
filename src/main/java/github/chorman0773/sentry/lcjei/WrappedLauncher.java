package github.chorman0773.sentry.lcjei;

import java.awt.Container;
import java.awt.Window;
import java.io.IOException;
import java.io.Writer;
import java.lang.instrument.Instrumentation;
import java.net.URI;
import java.nio.file.Path;

import github.chorman0773.sentry.GameBasic;
import github.chorman0773.sentry.annotation.Game;
import github.chorman0773.sentry.cci.CCIVendor;
import github.chorman0773.sentry.linterface.LauncherInterface;
import github.chorman0773.sentry.linterface.ModInterface;
import github.lightningcreations.lcjei.IEngineInterface;
import github.lightningcreations.lcjei.IGameInfo;

public final class WrappedLauncher<HostGameType> implements LauncherInterface {
	private IEngineInterface<HostGameType> host;
	
	public WrappedLauncher() {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public Container getCurrentDrawContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameBasic getGameObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean initialize(Container arg0) throws IllegalStateException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void run() throws IllegalStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getGameArguments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProperty(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModInterface[] activeMods() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public CCIVendor getLauncherVendor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Window getGameWindow() {
		// TODO Auto-generated method stub
		return null;
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
	public void close() throws IllegalStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public IGameInfo<GameBasic> getGameInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public Writer getLauncherWriter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecurityManager getSandbox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disableMods() {
		// TODO Auto-generated method stub

	}

	@Override
	public Game getGameAnnotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModInterface installMod(URI name) throws UnsupportedOperationException, IOException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void resume() throws IllegalStateException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void suspend() throws IllegalStateException {
		// TODO Auto-generated method stub
		
	}

}
