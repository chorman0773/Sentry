package github.chorman0773.sentry.mod;

import github.chorman0773.sentry.GameBasic;
import github.chorman0773.sentry.annotation.Game;
import github.chorman0773.sentry.linterface.LauncherInterface;

public abstract class SentryMod {
	private final GameBasic game;
	private final LauncherInterface lint;
	private final Game gannotation;
	private final Mod mannotation;
	protected SentryMod(GameBasic origin,LauncherInterface linterface,Game gameAnnotation,Mod modAnnotation) {
		this.game = origin;
		this.lint = linterface;
		this.gannotation = gameAnnotation;
		this.mannotation = modAnnotation;
	}
	
	protected final GameBasic getGame() {
		return game;
	}
	protected final LauncherInterface getLauncherInterface() {
		return lint;
	}
	protected final Game getGameAnnotation(){
		return gannotation;
	}
	protected final Mod getModAnnotation() {
		return mannotation;
	}
	
	public void preinit() {}
	public void init() {}
	public abstract void start();
	public abstract void stop();
	public void destroy() {}
	public void disable() {}
}
