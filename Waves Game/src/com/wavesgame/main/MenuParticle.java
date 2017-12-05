package com.wavesgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject
{
	private Handler handler;
	private Random r = new Random();
	
	private Color color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), 40);

	public MenuParticle(float x, float y, ID id, Handler handler) 
	{
		super(x, y, id);
		
		this.handler = handler;
		

		velX = 0;
		velY = r.nextInt(4) + 1;

	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		
		if(x >= Game.WIDTH)
			handler.removeObject(this);
		if(y >= Game.HEIGHT)
			handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, color, 10, 10, 0.03f, handler));
	}

	public void render(Graphics g) 
	{
		g.setColor(color);
		g.fillRect((int)x,(int) y, 10, 10);
	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 10, 10);
	}

}
