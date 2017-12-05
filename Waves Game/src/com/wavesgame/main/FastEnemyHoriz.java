package com.wavesgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemyHoriz extends GameObject
{
	private Handler handler;

	public FastEnemyHoriz(float x, float y, ID id, Handler handler) 
	{
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 10;
		velY = 3;
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		
		if(x <= 0 || x >= Game.WIDTH - 16)
			velX *= -1;
		if(y <= 0 || y >= Game.HEIGHT - 40)
			velY *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.BLUE, 16, 16, 0.03f, handler));
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.BLUE);
		g.fillRect((int)x,(int) y, 16, 16);
	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
