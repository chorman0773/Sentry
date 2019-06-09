package github.chorman0773.sentry.test;

import java.awt.Color;
import java.awt.Graphics;

import github.chorman0773.sentry.GameBasic;
import github.chorman0773.sentry.generic.GenericGame;

public class TestGame extends GenericGame {
	public TestGame(int frameRate, int tickRate) {
		super(frameRate, tickRate);
		this.setSize(700, 550);
	}



	public static void main(String[] args) {
		TestGame game = new TestGame(60,40);
		TestLauncher launch = new TestLauncher(game,args);
	}



	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 700, 550);
	}



	@Override
	public void tick() {
		this.getWriter().println("Tick at: "+System.currentTimeMillis()+"ms.");
	}



	@Override
	public void stop() {
		this.getWriter().println("Stopping Game: pausing");
		this.pauseGame();
	}



	@Override
	public void start() {
		this.getWriter().println("Starting Game: unpausing");
		this.unpauseGame();
	}



	@Override
	protected void doInit() throws Exception {
		this.getWriter().println("Test Game: init");
	}



	@Override
	protected void destroyOverride() {
		this.getWriter().println("Test Game: destroy");
	}

}
