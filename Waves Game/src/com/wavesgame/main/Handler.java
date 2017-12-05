package com.wavesgame.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.wavesgame.main.Game.STATE;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick()
	{
		for(int i =0;(i < object.size());i++)
		{
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0;(i < object.size());i++)
		{
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}		
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}

	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
	public void clearEnemies()
	{
		for(int i = 0;(i < object.size());i++)
		{
			GameObject tempObject = object.get(i);
			if(Game.gameState == STATE.End || Game.gameState == STATE.Paused || Game.gameState == STATE.Confirm || Game.gameState == STATE.Win)
			{
				removeObject(tempObject);
				i--;
			}
			else if(tempObject.getId() != ID.Player)
			{
				removeObject(tempObject);
				i--;
			}
		}
	}
	
	public void clearBoss()
	{
		BossEnemy1.bossIsFinished = true;
		BossEnemyHard1.bossIsFinished = true;
	}
}
