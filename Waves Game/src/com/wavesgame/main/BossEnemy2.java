package com.wavesgame.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy2 extends GameObject
{
	private Handler handler;
	
	private int timer = 1000;
	
	public static boolean bossIsFinished = false;
	
	Random r = new Random();

	public BossEnemy2(float x, float y, ID id, Handler handler) 
	{
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 0;
	}

	public void tick() 
	{	
		if(timer == 1000)
		{
			int xBullet = 8;
			int yBullet = 0;
			for(int i = 0;(i < 9);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 0, 3));
				xBullet += 85;
			}
		}
		if(timer == 850)
		{
			int xBullet = 0;
			int yBullet = 30;
			for(int i = 0;(i < 6);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 3, 0));
				yBullet += 85;
			}
		}
		if(timer == 650)
		{
			int xBullet = 50;
			int yBullet = 0;
			for(int i = 0;(i < 9);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 0, 5));
				xBullet += 85;
			}
		}
		if(timer == 550)
		{
			int xBullet = 0;
			int yBullet = 1;
			for(int i = 0;(i < 6);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 5, 0));
				yBullet += 85;
			}
		}
		if(timer == 400)
		{
			int xBullet = 8;
			int yBullet = 0;
			for(int i = 0;(i < 9);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 0, 7));
				xBullet += 85;
			}	
		}
		if(timer == 250)
		{
			int xBullet = 0;
			int yBullet = 1;
			for(int i = 0;(i < 6);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 7, 0));
				yBullet += 85;
			}
		}
		if(timer == 200)
		{
			int xBullet = 50;
			int yBullet = 0;
			for(int i = 0;(i < 9);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 0, 7));
				xBullet += 85;
			}	
		}
		if (timer == 150)
		{
			int xBullet = 0;
			int yBullet = 1;
			for(int i = 0;(i < 6);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 7, 0));
				yBullet += 85;
			}
		}
		if(timer == 100)
		{
			int xBullet = 0;
			int yBullet = 1;
			for(int i = 0;(i < 6);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 7, 0));
				yBullet += 85;
			}
		}
		if(timer == 50)
		{
			int xBullet = 30;
			int yBullet = 0;
			for(int i = 0;(i < 9);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 0, 7));
				xBullet += 85;
			}
		}
		if(timer == 25)
		{
			int xBullet = 0;
			int yBullet = 1;
			for(int i = 0;(i < 6);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 8, 0));
				yBullet += 85;
			}
			
			xBullet = 8;
			yBullet = 0;
			for(int i = 0;(i < 9);i++)
			{
				handler.addObject(new BossEnemyBullet(xBullet, yBullet, ID.BasicEnemy, handler, 0, 8));
				xBullet += 85;
			}
			
		}
		if(timer >= 0)
			timer--;
			
	}

	public void render(Graphics g) 
	{

	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 0, 0);
	}

}
