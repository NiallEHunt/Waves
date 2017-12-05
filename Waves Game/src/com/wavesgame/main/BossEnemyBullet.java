package com.wavesgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemyBullet extends GameObject
{
	private Handler handler;
	Random r = new Random();

	public BossEnemyBullet(float x, float y, ID id, Handler handler, float velX, float velY) 
	{
		super(x, y, id);
		
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		
		if(x <= 0 || x >= Game.WIDTH)
			handler.removeObject(this);
		if(y <= 0 || y >= Game.HEIGHT)
			handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.LIGHT_GRAY, 16, 16, 0.05f, handler));
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
