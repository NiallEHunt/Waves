package com.wavesgame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3275450156793090762L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	public static int difficulty = 0;
	// 0 = Normal
	// 1 = Hard
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	private Random r;
	private Spawner spawner;
	private Menu menu;
	
	public enum STATE
	{
		Game,
		Select,
		Menu,
		Help,
		Paused,
		Confirm,
		Win,
		End
	}
	
	public static STATE gameState = STATE.Menu;
	
	public Game()
	{
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		AudioPlayer.init();
		
		AudioPlayer.getMusic("Music").loop();
		
		new Window(WIDTH, HEIGHT, "Waves", this);
		
		spawner = new Spawner(handler, hud);
		r = new Random();
		
		if(gameState == STATE.Game)
		{
			handler.addObject(new Player(((WIDTH / 2) - 32),((HEIGHT / 2) - 32),ID.Player, handler, hud));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		}
	}

	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
	/*
	public void run()
	{
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0.0;
		long timer = System.currentTimeMillis();
		//int frames = 0;
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			if(running)
				render();
			//frames++;
			
			if((System.currentTimeMillis() - timer) > 1000)
			{
				timer += 1000;
				//System.out.println("FPS: " + frames);
				//frames = 0;
			}
			try
			{
				Thread.sleep((long) ((lastTime - System.nanoTime() + ns) / 1000000));
			}
			catch(Exception exception)
			{
			
			}
		}
	}
*/
	
	public void run()
	{
	   long lastLoopTime = System.nanoTime();
	   final int TARGET_FPS = 60;
	   final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;  
	  // int fps = 0;
	   long lastFpsTime = 0;

	   // keep looping round until the game ends
	   while (running)
	   {
	      // work out how long its been since the last update, this
	      // will be used to calculate how far the entities should
	      // move this loop
	      long now = System.nanoTime();
	      long updateLength = now - lastLoopTime;
	      lastLoopTime = now;
	     // double delta = updateLength / ((double)OPTIMAL_TIME);

	      // update the frame counter
	      lastFpsTime += updateLength;
	      //fps++;
	      
	      // update our FPS counter if a second has passed since
	      // we last recorded
	      if (lastFpsTime >= 1000000000)
	      {
	         //System.out.println("(FPS: "+fps+")");
	         lastFpsTime = 0;
	        // fps = 0;
	      }
	      
	      // update the game logic
	      tick();
	      
	      // draw everything
	      render();
	      
	      // we want each frame to take 10 milliseconds, to do this
	      // we've recorded when we started the frame. We add 10 milliseconds
	      // to this and then factor in the current time to give 
	      // us our final value to wait for
	      // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
	      try
	      {
	    	  Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
	      }
	      catch(Exception exception)
	      {
	    	  
	      }
	   }
	}
	
	private void tick()
	{
		if(gameState == STATE.Game && gameState != STATE.Paused && gameState != STATE.Confirm && gameState != STATE.Win)
		{
			handler.tick();
			HUD.tick();
			spawner.tick();
			
			if(HUD.health <= 0)
			{
				AudioPlayer.getSound("Game over").play();
				gameState = STATE.End;
				handler.clearEnemies();
				HUD.health = 100;
			}
		}
		else if(gameState == STATE.End || gameState == STATE.Win)
		{
			menu.tick();
			handler.tick();
			spawner.tick();
		}
		else if(Game.gameState == STATE.Paused)
		{
			menu.tick();
		}
		else
		{
			menu.tick();
			handler.tick();
		}
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy(); 
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(gameState == STATE.Game)
		{
			HUD.render(g);
		}
		else
		{
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max)
	{
		if(var >= max)
			return max;
		else if(var <= min)
			return min;
		else
			return var;
	}
	
	public static void main(String[]args)
	{
		new Game();
	}

}
