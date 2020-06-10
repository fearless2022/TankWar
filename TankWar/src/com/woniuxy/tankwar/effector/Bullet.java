package com.woniuxy.tankwar.effector;

import java.awt.Graphics;
import java.util.Date;
import java.util.Random;
import javax.swing.JPanel;
import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.ui.GameOverPanel;
import com.woniuxy.tankwar.util.PlayMusicUtil;
import com.woniuxy.tankwar.util.ReadImgUtil;

//子弹
public class Bullet implements Runnable {

	public int x;
	public int y;
	public int dir;// 方向：0：向上，1：向右，2：向下，3：向左
	public int speed;
	public int atk;
	public boolean isAlive = true;
	public boolean isMyBullet;
	public int step_1 = 1;// 切换我方子弹
	public int step_2 = 0;// 切换敌方子弹

	public Bullet(int x, int y, int dir, int speed, int atk, boolean isMyBullet) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.speed = speed;
		this.atk = atk;
		this.isMyBullet = isMyBullet;
		new Thread(this).start();
	}

	// 画子弹
	public void drawBullet(Graphics g, JPanel j) {
		// 画我方子弹
		if (isMyBullet) {
			if (Datacenter.nowMap[y][x] == 3) {
				g.drawImage(ReadImgUtil.mybullet, x * 32 + 5, y * 32 + 5, (x + 1) * 32 - 5, (y + 1) * 32 - 5,
						step_1 * 180, 0, (step_1 + 1) * 180, 180, j);
				g.drawImage(ReadImgUtil.map_element, (x * 32) + 2, y * 32 + 2, (x + 1) * 32 - 2, (y + 1) * 32 - 2, 64,
						0, 96, 32, j);
			} else {
				g.drawImage(ReadImgUtil.mybullet, x * 32 + 5, y * 32 + 5, (x + 1) * 32 - 5, (y + 1) * 32 - 5,
						step_1 * 180, 0, (step_1 + 1) * 180, 180, j);
			}
		} else {
			// 画敌方子弹
			if (Datacenter.nowMap[y][x] == 3) {
				g.drawImage(ReadImgUtil.enemybullet, x * 32 + 8, y * 32 + 8, (x + 1) * 32 - 8, (y + 1) * 32 - 8, 0,
						step_2 * 42, 45, (step_2 + 1) * 42, j);
				g.drawImage(ReadImgUtil.map_element, (x * 32) + 2, y * 32 + 2, (x + 1) * 32 - 2, (y + 1) * 32 - 2, 64,
						0, 96, 32, j);
			} else {
				g.drawImage(ReadImgUtil.enemybullet, x * 32 + 8, y * 32 + 8, (x + 1) * 32 - 8, (y + 1) * 32 - 8, 0,
						step_2 * 42, 45, (step_2 + 1) * 42, j);
			}
		}
	}

	// 判断地形
	public boolean canMove(int nowX, int nowY) {
		// 方向：0：向上，1：向右，2：向下，3：向左
		// 地形：0：空地，1：铁墙，2：土墙，3：草地，4：水，5：boss
		switch (Datacenter.nowMap[nowY][nowX]) {
		case 1:
			isAlive = false;
			Datacenter.bos.add(new Boom(x, y, 1));
			PlayMusicUtil.playMusic("boom.wav");
			return false;
		case 2:
			isAlive = false;
			Datacenter.nowMap[nowY][nowX] = 0;
			Datacenter.bos.add(new Boom(nowX, nowY, 1));
			PlayMusicUtil.playMusic("boom.wav");
			return false;
		case 5:
			isAlive = false;
			Datacenter.bos.add(new Boom(nowX, nowY, 1));
			PlayMusicUtil.playMusic("boom.wav");
			Datacenter.attack.addTime();
			Datacenter.gameover = true;
			GameOverPanel.getInstance().gameOver();
			PlayMusicUtil.playMusic("end.wav");
			Datacenter.endTime = new Date();
			return false;
		}

		if (isMyBullet) {
			// 敌方坦克位置
			for (int i = 0; i < Datacenter.ets.size(); i++) {
				if (Datacenter.ets.get(i).x == nowX && Datacenter.ets.get(i).y == nowY) {
					isAlive = false;
					Datacenter.ets.get(i).isAlive = false;
					// 产生BUFF
					if (Datacenter.ets.get(i).state == 1) {
						while (!creatBuff()) {
							creatBuff();
							PlayMusicUtil.playMusic("buff.wav");
						}
					}
					return false;
				}
			}
			// 敌方子弹位置
			for (int i = 0; i < Datacenter.ebs.size(); i++) {
				if (Datacenter.ebs.get(i).x == nowX && Datacenter.ebs.get(i).y == nowY) {
					isAlive = false;
					Datacenter.ebs.get(i).isAlive = false;
					Datacenter.bos.add(new Boom(nowX, nowY, 1));
					PlayMusicUtil.playMusic("boom.wav");
					return false;
				}
			}
		} else {
			// 我方坦克位置
			if (Datacenter.mt != null) {
				if (Datacenter.mt.x == nowX && Datacenter.mt.y == nowY) {
					isAlive = false;
					Datacenter.mt.isAlive = false;
					return false;
				}
			}
			// 我方子弹位置
//			for (int i = 0; i < Datacenter.mbs.size(); i++) {
//				if (Datacenter.mbs.get(i).x == nowX && Datacenter.mbs.get(i).y == nowY) {
//					isAlive = false;
//					Datacenter.mbs.get(i).isAlive = false;
//					Datacenter.bos.add(new Boom(x, y, 1));
//					return false;
//				}
//			}
		}
		return true;
	}

	// 产生BUFF
	public boolean creatBuff() {
		Random r = new Random();
		// 随机BUFF
		int type = r.nextInt(6);
		// 随机位置
		int rx = r.nextInt(27);
		int ry = r.nextInt(27);
		switch (Datacenter.nowMap[ry][rx]) {
		case 0:
		case 3:
			Datacenter.bfs.add(new Buff(rx, ry, type, 10));
			return true;
		}
		return false;
	}

	// 移动
	public void move() {
		switch (dir) {
		case 0:
			// 向上移动
			if (canMove(x, y - speed)) {
				y = y - speed;
			}
			break;
		case 1:
			// 向右移动
			if (canMove(x + speed, y)) {
				x = x + speed;
			}
			break;
		case 2:
			// 向下移动
			if (canMove(x, y + speed)) {
				y = y + speed;
			}
			break;
		case 3:
			// 向左移动
			if (canMove(x - speed, y)) {
				x = x - speed;
			}
			break;
		}
	}

	@Override
	public void run() {
		while (isAlive) {
			try {
				this.move();
				Thread.sleep(200);
				step_1++;
				if (step_1 > 2) {
					step_1 = 0;
				}
				step_2++;
				if (step_2 > 3) {
					step_2 = 0;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (isMyBullet) {
			Datacenter.mbs.remove(this);
		} else {
			Datacenter.ebs.remove(this);
		}
	}
}
