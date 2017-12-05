package com.wavesgame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.wavesgame.main.Game.STATE;

public class KeyInput extends KeyAdapter 
{
	
	private Handler handler;
	
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
		
		for(int i = 0;(i < keyDown.length);i++)
			keyDown[i] = false;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();

		for(int i =0;(i < handler.object.size());i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player)
			{
				// key events for player
				
				if(key == KeyEvent.VK_W)
				{
					tempObject.setVelY(-5);
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_S)
				{
					tempObject.setVelY(5);
					keyDown[1] = true;
				}
				if(key == KeyEvent.VK_A)
				{
					tempObject.setVelX(-5);
					keyDown[2] = true;
				}
				if(key == KeyEvent.VK_D)
				{
					tempObject.setVelX(5);
					keyDown[3] = true;
				}
				
				if(Game.gameState == STATE.Game || Game.gameState == STATE.Paused || Game.gameState == STATE.Confirm)
				{
					if(key == KeyEvent.VK_ESCAPE)
					{
						if(Game.gameState == STATE.Game)
							Game.gameState = STATE.Paused;
						else
							Game.gameState = STATE.Game;
					}
				}
				
			}
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i =0;(i < handler.object.size());i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player)
			{
				// key events for player 
				
				if(key == KeyEvent.VK_W)
					keyDown[0] = false;
				if(key == KeyEvent.VK_S)
					keyDown[1] = false;
				if(key == KeyEvent.VK_A)
					keyDown[2] = false;
				if(key == KeyEvent.VK_D)
					keyDown[3] = false;
				
				// Vertical Movement
				if(!keyDown[0] && ! keyDown[1])
					tempObject.setVelY(0);
				// Horizontal Movement
				if(!keyDown[2] && ! keyDown[3])
					tempObject.setVelX(0);
				
			}
		}
	}
}
