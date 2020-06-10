package com.woniuxy.tankwar.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//读取图片
public class ReadImgUtil {

	public static Image map_element;
	public static Image player1;
	public static Image enemy;
	public static Image nature;
	public static Image star;
	public static Image mybullet;
	public static Image enemybullet;
	public static Image boom;
	public static Image buff;
	public static Image attack;
	public static Image gameover;
	public static Image gamestart;
	public static Image gamepass;

	static {
		try {
			map_element = ImageIO.read(new File("img/icon.bmp"));
			player1 = ImageIO.read(new File("img/player1.bmp"));
			enemy = ImageIO.read(new File("img/enemy.bmp"));
			nature = ImageIO.read(new File("img/nature.png"));
			star = ImageIO.read(new File("img/xinxin.bmp"));
			mybullet = ImageIO.read(new File("img/mybullet.png"));
			enemybullet = ImageIO.read(new File("img/enemybullet.png"));
			boom = ImageIO.read(new File("img/boom.png"));
			buff = ImageIO.read(new File("img/buff.bmp"));
			attack = ImageIO.read(new File("img/attack.png"));
			gameover = ImageIO.read(new File("img/gameover.png"));
			gamestart = ImageIO.read(new File("img/gamestart.png"));
			gamepass = ImageIO.read(new File("img/gamepass.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
