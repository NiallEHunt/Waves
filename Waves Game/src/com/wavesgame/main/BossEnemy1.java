package com.wavesgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy1 extends GameObject
{
	private Handler handler;
	
	public static boolean bossIsFinished = false;
	
	Random r = new Random();
	
	private int timer = 70;
	private int timer2 = 50;

	public BossEnemy1(float x, float y, ID id, Handler handler) 
	{
		super(x, y, id);
		
		this.handler = handler;
		
		bossIsFinished = false;
		
		velX = 0;
		velY = 2;
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		if(timer <= 0)
			velY = 0;
		else
			timer--;
		
		if(timer <= 0)
			timer2--;
		if(timer2 <= 0)
		{
			if(velX == 0)
				velX = 2;
			if(velX > 0)
				velX += 0.005f;
			else if(velX < 0)
				velX -= 0.005f;
			Game.clamp(velX, -10, 10);
			if(!bossIsFinished)
			{
				int spawn = r.nextInt(10);
				if(spawn == 0)
				{
					float velXForBullet = r.nextInt(5);
					if(r.nextInt(2) == 0)
						velXForBullet *= -1;
					handler.addObject(new BossEnemyBullet((int)x + 48, (int)y + 48, ID.BasicEnemy, handler, velXForBullet, 5));
				}
			}
			if(bossIsFinished)
			{
				velX = 0;
				velY = -0.5f;
				
				if(y <= -150)
				{
					handler.removeObject(this);
				}
			}
		}
		if(x <= 0 || x >= Game.WIDTH - 100)
		{
			velX *= -1;
			if(x <= 0)
				handler.addObject(new BossEnemyBullet((int)x + 10, (int)y + 48, ID.BasicEnemy, handler, 0, 6));
			else if(x >= Game.WIDTH - 100)
				handler.addObject(new BossEnemyBullet((int)x + 66, (int)y + 48, ID.BasicEnemy, handler, 0, 6));

		}
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 96, 96);
	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 96, 96);
	}

}
