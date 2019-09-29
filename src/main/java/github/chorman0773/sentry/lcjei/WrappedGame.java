package github.chorman0773.sentry.lcjei;

import java.util.concurrent.atomic.AtomicBoolean;

import github.chorman0773.sentry.GameBasic;
import github.lightningcreations.lcjei.IEngineInterface;
import github.lightningcreations.lcjei.service.EngineLookup;

public class WrappedGame<GuestGameType> extends GameBasic {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1058291013839118820L;
	private final IEngineInterface<GuestGameType> guestEngine;
	private final GuestGameType guestGame;
	private final AtomicBoolean isSuspended = new AtomicBoolean();
	
	public WrappedGame(GuestGameType game,Class<GuestGameType> type) {
		assert type.isAssignableFrom(game.getClass());
		guestEngine = EngineLookup.getByEngineClass(type).map(s->s.newEngine(game)).orElseThrow(()->new RuntimeException("Cannot Find "));
		guestGame = game;
	}
	
	@Override
	public final void stop() {
		guestEngine.suspend();
		isSuspended.set(true);
	}

	@Override
	public final void start() {
		if(isSuspended.getAndSet(false))
			guestEngine.resume();
	}

	@Override
	public final void run() {
		guestEngine.run();
		synchronized(this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	protected final void destroyOverride() {
		guestEngine.destroy();
	}
	
	protected final void doInit() {
		guestEngine.initialize(this);
	}

}
