package github.chorman0773.sentry.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import github.chorman0773.sentry.GameBasic;
import github.chorman0773.sentry.annotation.Game;
import github.chorman0773.sentry.generic.GenericGame;

@Game(uuid="30f59cc6-be13-11e9-9cb5-2a2ae2dbcce4", gameId = "sentryTest", gameName = "Sentry Test Game", gameVersion = "1.0")
public class TestGame extends GenericGame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3007064851643223382L;



	public TestGame(int frameRate, int tickRate) {
		super(frameRate, tickRate);
		this.setSize(700, 550);
	}



	public static void main(String[] args) {
		TestGame game = new TestGame(60,40);
		TestLauncher launch = new TestLauncher(game,args);
		launch.initialize();
		launch.run();
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
		this.addClickListener(MouseEvent.BUTTON1, (int x,int y)->System.out.printf("Click@[%d,%d]%n",x,y));
	}



	@Override
	protected void destroyOverride() {
		this.getWriter().println("Test Game: destroy");
	}

}
