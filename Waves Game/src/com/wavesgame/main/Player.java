package com.wavesgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject 
{

	Handler handler;
	HUD hud;
	
	public Player(float x, float y, ID id, Handler handler, HUD hud) 
	{
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 61);
		
		if(hud.getLevel() >= 10 && hud.getLevel() <= 15)
			y = Game.clamp(y, 100, Game.WIDTH - 38);
		
		collision();
	}
	
	public void collision()
	{
		for(int count = 0;(count < handler.object.size());count++)
		{
			GameObject tempObject = handler.object.get(count);
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.BossEnemy1 || tempObject.getId() == ID.HardEnemy)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					HUD.health -= 2;
				}
			}
		}
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}