package com.woniuxy.tankwar.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import sun.audio.AudioPlayer;

//播放音乐
public class PlayMusicUtil {
	public static void playMusic(String string) {
		File file = new File("music/" + string);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			AudioPlayer.player.start(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
