package github.chorman0773.sentry.generic;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.Instant;

import github.chorman0773.sentry.GameBasic;

@SuppressWarnings("serial")
public abstract class GenericGame extends GameBasic{
  private int frameRate;
  private int tickRate;
  private Thread graphicsInterruptThread;
  private Thread tickThread;
  private Thread renderThread;
  private boolean running;
  public static class GenericGameProfiler{
	  private Instant startTime;
	  private long totalTicks;
	  private Instant lastTickTime;
	  private double instantTps;
	  private double averageTps;
	  private int target;
	  GenericGameProfiler(int target){
		  startTime = Instant.now();
	  }
	  public double getAverageTps() {
		  return averageTps;
	  }
	  public double getInstantTps() {
		  return instantTps;
	  }
	  void tick() {
		  Instant now = Instant.now();
		  var tickLen = Duration.between(lastTickTime, now);
		  lastTickTime = now;
		  double dur = tickLen.toMillis();
		  instantTps = Math.min(1/dur, target);
		  totalTicks++;
		  var runtime = Duration.between(startTime, now);
		  dur = runtime.toMillis();
		  averageTps = Math.min(totalTicks/dur,target);
	  }
	  void setTarget(int target) {
		  this.target = target;
	  }
  }
  private GenericGameProfiler tickTimer;
  private GenericGameProfiler renderTimer;
  public GenericGame(int frameRate,int tickRate){
    this.frameRate = frameRate;
    this.tickRate = tickRate;
  }
  public void setFrameRate(int frameRate){
    this.frameRate = frameRate;
    renderTimer.setTarget(frameRate);
  }
  public void setTickRate(int tickRate){
    this.tickRate = tickRate;
    tickTimer.setTarget(tickRate);
  }
  public int getFrameRate(){
    return frameRate;
  }
  public int getTickRate(){
    return tickRate;
  }
  public abstract void tick();
  
  private void renderLoop(){
    try{
      while(!Thread.interrupted()){
        Thread.sleep(1000/frameRate);
        renderThread.interrupt();
      }
    }catch(InterruptedException e){}
  }
  
  protected final void initGame()throws Exception{
    tickThread = new Thread(new Runnable(){
      public void run(){
    	tickTimer = new GenericGameProfiler(tickRate);
        while(running){
          while(!Thread.interrupted());
          GenericGame.this.tick();
          tickTimer.tick();
        }
      }
    });
    renderThread = new Thread(new Runnable(){
      public void run(){
    	renderTimer = new GenericGameProfiler(frameRate);
        while(running){ 
          try {
        	  while(!Thread.interrupted());
				EventQueue.invokeAndWait(GenericGame.this::repaint);
				renderTimer.tick();
			} catch (InvocationTargetException | InterruptedException e) {}
        }
      }
    });
    graphicsInterruptThread = new Thread(this::renderLoop);
    doInit();
  }
  
  protected void doInit()throws Exception {}
  
  public final void run(){
    running = true;
    tickThread.start();
    renderThread.start();
    graphicsInterruptThread.start();
    try{
      while(!Thread.interrupted()){

        Thread.sleep(1000/tickRate);
        tickThread.interrupt();
      }
    }catch(InterruptedException e){}
    graphicsInterruptThread.interrupt();
  }
  public final GenericGameProfiler getTickProfiler() {
	  return tickTimer;
  }
  public final GenericGameProfiler getRenderProfiler() {
	  return renderTimer;
  }
};
