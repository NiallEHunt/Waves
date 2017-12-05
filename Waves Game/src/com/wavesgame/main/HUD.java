package com.wavesgame.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD 
{
	public static int health = 100;
	
	private static int greenValue = 255;
	
	private static int score = 0;
	private static int level = 1;
	
	
	public static void tick()
	{
		health = (int) Game.clamp(health, 0, 100);
		
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		
		greenValue = health * 2;
		
		score++;
	}
	
	public static void render(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 32);
		
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, health * 2, 32);
		
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
	}
	
	public void setScore(int score)
	{
		HUD.score = score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		HUD.level = level;
		
	}
}
