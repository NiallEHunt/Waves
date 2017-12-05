package com.wavesgame.main;

import java.util.Random;

import com.wavesgame.main.Game.STATE;

public class Spawner 
{
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawner(Handler handler, HUD hud)
	{
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick()
	{
		if(Game.gameState == STATE.End || Game.gameState == STATE.Win)
			scoreKeep = 0;
		else
		{
			scoreKeep++;
			if(scoreKeep >= 250)
			{
				scoreKeep = 0;
				hud.setLevel(hud.getLevel() + 1);
				
				if(Game.difficulty == 0)
				{
					switch(hud.getLevel())
					{
					case 2:
					case 3:
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						break;
					case 4:
					case 5:
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						break;
					case 6:
					case 7:
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						break;
					case 10:
						handler.clearEnemies();
						handler.addObject(new BossEnemy1(((Game.WIDTH / 2) - 48), -120, ID.BossEnemy1, handler));
						break;
					case 15:
						handler.clearBoss();
						HUD.health += 50;
						break;
					case 16:
						handler.addObject(new SmartFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new SmartFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						break;
					case 18:
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new FastEnemyHoriz(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new FastEnemyHoriz(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						break;
					case 22:
						handler.clearEnemies();
						handler.addObject(new BossEnemy2(0, 0, ID.BossEnemy1, handler));
						break;
					case 27:
						handler.clearEnemies();
						for(int i = 0;(i < 3);i++)
							handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						for(int i = 0;(i < 3);i++)
							handler.addObject(new FastEnemyHoriz(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						break;
					case 30:
						Game.gameState = STATE.Win;
						handler.clearEnemies();
					default:
						break;
					}
				}
				else if(Game.difficulty == 1)
				{
					switch(hud.getLevel())
					{
					case 2:
					case 3:
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						break;
					case 4:
					case 5:
					case 6:
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						break;
					case 7:
					case 8:
					case 9:
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						break;
					case 10:
						handler.clearEnemies();
						handler.addObject(new BossEnemyHard1(((Game.WIDTH / 2) - 48), -120, ID.BossEnemy1, handler));
						break;
					case 15:
						handler.clearBoss();
						HUD.health += 50;
						break;
					case 16:
						for(int i = 0;(i < 4);i++)
							handler.addObject(new SmartFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						break;
					case 18:
						for(int i = 0;(i < 4);i++)
							handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						for(int i = 0;(i < 4);i++)
							handler.addObject(new FastEnemyHoriz(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						break;
					case 22:
						handler.clearEnemies();
						handler.addObject(new BossEnemy2(0, 0, ID.BossEnemy1, handler));
						handler.addObject(new SmartFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						break;
					case 24:
						handler.addObject(new SmartFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						break;
					case 27:
						handler.clearEnemies();
						for(int i = 0;(i < 3);i++)
							handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						for(int i = 0;(i < 3);i++)
							handler.addObject(new FastEnemyHoriz(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						break;
					case 28:
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					case 30:
						Game.gameState = STATE.Win;
						handler.clearEnemies();
					default:
						break;
					}
				}
			}
		}
	}
}
