package com.wavesgame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.wavesgame.main.Game.STATE;

public class Menu extends MouseAdapter
{
	//private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int tickCount = 0;
	private int highScore = 0;
	
	public Menu(Game game, Handler handler, HUD hud)
	{
		//this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu)
		{
			// play button
			if(mouseOver(mx, my, 220, 120, 200, 64))
			{
				Game.gameState = STATE.Select;
				
				AudioPlayer.getSound("Menu sound").play();
			}
			
			// help button
			if(mouseOver(mx, my, 220, 220, 200, 64))
			{
				Game.gameState = STATE.Help;
				
				AudioPlayer.getSound("Menu sound").play();
			}
			
			// quit button
			if(mouseOver(mx, my, 220, 320, 200, 64))
			{
				AudioPlayer.getSound("Menu sound").play();
				
				System.exit(1);
			}
		}
		else if(Game.gameState == STATE.Select)
		{
			// Normal button
			if(mouseOver(mx, my, 220, 120, 200, 64))
			{
				Game.gameState = STATE.Game;
				handler.clearEnemies();
				handler.addObject(new Player(((Game.WIDTH / 2) - 32),((Game.HEIGHT / 2) - 32),ID.Player, handler, hud));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
				Game.difficulty = 0;
				
				AudioPlayer.getSound("Menu sound").play();
			}
			
			// Hard button
			if(mouseOver(mx, my, 220, 220, 200, 64))
			{
				Game.gameState = STATE.Game;
				handler.clearEnemies();
				handler.addObject(new Player(((Game.WIDTH / 2) - 32),((Game.HEIGHT / 2) - 32),ID.Player, handler, hud));
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
				
				Game.difficulty = 1;
				
				AudioPlayer.getSound("Menu sound").play();
			}
			
			// Back button
			if(mouseOver(mx, my, 220, 320, 200, 64))
			{
				Game.gameState = STATE.Menu;
				AudioPlayer.getSound("Menu sound").play();
			}
		}
		else if(Game.gameState == STATE.Help)
		{
			// back button
			if(mouseOver(mx, my, 220, 320, 200, 64))
			{
				Game.gameState = STATE.Menu;
				
				AudioPlayer.getSound("Menu sound").play();
			}
		}
		else if(Game.gameState == STATE.End)
		{
			// try again button
			if(mouseOver(mx, my, 220, 320, 200, 64))
			{
				Game.gameState = STATE.Menu;
				hud.setScore(0);
				hud.setLevel(1);
				
				AudioPlayer.getSound("Menu sound").play();
			}
		}
		else if(Game.gameState == STATE.Paused)
		{
			//Resume button
			if(mouseOver(mx, my, 220, 220, 200, 64))
			{
				Game.gameState = STATE.Game;
				
				AudioPlayer.getSound("Menu sound").play();
			}
			
			//Quit button
			if(mouseOver(mx, my, 220, 320, 200, 64))
			{
				Game.gameState = STATE.Confirm;
				AudioPlayer.getSound("Menu sound").play();
			}
		}
		else if(Game.gameState == STATE.Confirm)
		{
				//Yes button
				if(mouseOver(mx, my, 220, 220, 200, 64))
				{
					handler.clearEnemies();
					
					Game.gameState = STATE.End;
					
					HUD.health = 100;
					
					AudioPlayer.getSound("Menu sound").play();
				}
				
				//No button
				if(mouseOver(mx, my, 220, 320, 200, 64))
				{
					Game.gameState = STATE.Paused;
					AudioPlayer.getSound("Menu sound").play();
				}
		}
		else if(Game.gameState == STATE.Win)
		{
			// try again button
						if(mouseOver(mx, my, 220, 320, 200, 64))
						{
							Game.gameState = STATE.Menu;
							hud.setScore(0);
							hud.setLevel(1);
							
							AudioPlayer.getSound("Menu sound").play();
						}
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if((mx > x && mx < (x + width)) && (my > y && my < (y + height)))
			return true;
		else return false;
	}
	
	public void tick()
	{
		if(Game.gameState != STATE.Game && Game.gameState != STATE.Confirm && Game.gameState != STATE.Paused)
		{
			tickCount++;
			if(tickCount >= 10)
			{
				tickCount = 0;
				handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH) , 0, ID.MenuParticle, handler));
			}
		}
	}
	
	public void render(Graphics g)
	{
		Font font1 = new Font("Title", 1, 50);
		Font font2 = new Font("Buttons", 1, 30);
		Font font3 = new Font("Text", 1, 20);
		Font font4 = new Font("Small text", 1, 10);
		
		if(Game.gameState == STATE.Menu)
		{
			g.setColor(Color.BLACK);
			g.fillRect(220, 120, 200, 64);
			g.fillRect(220, 220, 200, 64);
			g.fillRect(220, 320, 200, 64);
	
			g.setColor(Color.WHITE);
			
			g.setFont(font1);
			g.drawString("Waves", 226, 70);
			
			g.setFont(font2);
			
			g.drawRect(220, 120, 200, 64);
			g.drawString("Play", 283, 163);
			
			g.drawRect(220, 220, 200, 64);
			g.drawString("Help", 283, 263);
	
			g.drawRect(220, 320, 200, 64);
			g.drawString("Quit", 283, 363);
		}
		else if(Game.gameState == STATE.Select)
		{
			g.setColor(Color.BLACK);
			g.fillRect(220, 120, 200, 64);
			g.fillRect(220, 220, 200, 64);
			g.fillRect(220, 320, 200, 64);
	
			g.setColor(Color.WHITE);
			
			g.setFont(font1);
			g.drawString("Select Difficulty", 100, 70);
			
			g.setFont(font2);
			
			g.drawRect(220, 120, 200, 64);
			g.drawString("Normal", 262, 163);
	
			g.drawRect(220, 320, 200, 64);
			g.drawString("Back", 283, 363);
			
			g.setFont(font3);

			g.drawRect(220, 220, 200, 64);
			g.drawString("I like to die", 236, 260);
			
			g.setFont(font4);
			g.drawString("... a lot", 368, 260);
		}
		else if(Game.gameState == STATE.Help)
		{
			g.setColor(Color.BLACK);
			g.fillRect(220, 320, 200, 64);
			
			g.setColor(Color.WHITE);
			
			g.setFont(font1);
			g.drawString("Help", 250, 70);
			
			g.setFont(font3);
			
			g.drawString("Use the WASD keys to move your player around.", 45, 170);
			g.drawString("Avoid the enemies to survive!", 135, 200);
			
			g.setFont(font2);
			
			g.drawRect(220, 320, 200, 64);
			g.drawString("Back", 283, 363);
		}
		else if(Game.gameState == STATE.End)
		{
			g.setColor(Color.BLACK);
			g.fillRect(220, 320, 200, 64);
			
			g.setColor(Color.WHITE);
			
			g.setFont(font1);
			g.drawString("GAME OVER", 160, 70);
			
			g.setFont(font3);
			
			g.drawString("You died with a score of: " + hud.getScore(), 150, 190);
			if(hud.getScore() >= highScore)
				highScore = hud.getScore();
			g.drawString("High Score: " + highScore, 230, 220);
			
			g.setFont(font2);
			
			g.drawRect(220, 320, 200, 64);
			g.drawString("Try Again", 240, 363);
		}
		else if(Game.gameState == STATE.Paused)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	
			g.setColor(Color.WHITE);
			
			g.setFont(font1);
			g.drawString("Paused", 218, 70);
			
			g.setFont(font3);
			g.drawString("Score: " + hud.getScore(), 270, 150);
			g.drawString("Level: " + hud.getLevel(), 270, 180);
			
			g.setFont(font2);
			
			g.drawRect(220, 220, 200, 64);
			g.drawString("Resume", 253, 263);
	
			g.drawRect(220, 320, 200, 64);
			g.drawString("Quit", 283, 363);
		}
		else if(Game.gameState == STATE.Confirm)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	
			g.setColor(Color.WHITE);
			
			g.setFont(font1);
			g.drawString("Paused", 218, 70);
			
			g.setFont(font3);
			
			g.drawString("Are you sure you want to quit?", 154, 180);
			
			g.setFont(font2);
			
			g.drawRect(220, 220, 200, 64);
			g.drawString("Yes", 287, 263);
	
			g.drawRect(220, 320, 200, 64);
			g.drawString("No", 297, 363);
		}
		else if(Game.gameState == STATE.Win)
		{
			g.setColor(Color.BLACK);
			g.fillRect(220, 320, 200, 64);
			
			g.setColor(Color.WHITE);
			
			g.setFont(font1);
			g.drawString("YOU WIN", 195, 70);
			
			g.setFont(font3);
			
			g.drawString("Congratulaions you win!!", 180, 190);
			if(hud.getScore() >= highScore)
				highScore = hud.getScore();
			g.drawString("High Score: " + highScore, 230, 220);
			
			g.setFont(font2);
			
			g.drawRect(220, 320, 200, 64);
			g.drawString("Try Again", 240, 363);
		}
	}
}
