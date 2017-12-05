package com.wavesgame.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void init()
	{
		try {
			soundMap.put("Menu sound", new Sound("res/mouse_click.wav"));
			soundMap.put("Game over", new Sound("res/game_over.wav"));
			musicMap.put("Music", new Music("res/background_music.wav"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Sound getSound(String key)
	{
		return soundMap.get(key);
	}
	
	public static Music getMusic(String key)
	{
		return musicMap.get(key);
	}

}
