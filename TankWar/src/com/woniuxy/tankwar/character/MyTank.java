package com.woniuxy.tankwar.character;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JPanel;
import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.effector.Boom;
import com.woniuxy.tankwar.effector.Bullet;
import com.woniuxy.tankwar.ui.GameOverPanel;
import com.woniuxy.tankwar.ui.MyJPanel;
import com.woniuxy.tankwar.util.PlayMusicUtil;
import com.woniuxy.tankwar.util.ReadImgUtil;

//我方坦克
public class MyTank extends Tank implements Runnable {

	public static int life = 2;

	public MyTank(int x, int y, int dir, int speed, int atk, int hp) {
		super(x, y, dir, speed, atk, hp);
		new Thread(this).start();
	}

	// 画坦克角色
	public void drawTank(Graphics g, JPanel j) {
		if (Datacenter.nowMap[y][x] == 3) {
			g.drawImage(ReadImgUtil.player1, x * 32, y * 32, (x + 1) * 32, (y + 1) * 32, (step + state * 2) * 32,
					dir * 32, (step + state * 2 + 1) * 32, (dir + 1) * 32, j);
			g.drawImage(ReadImgUtil.map_element, (x * 32) + 2, y * 32 + 2, (x + 1) * 32 - 2, (y + 1) * 32 - 2, 64, 0,
					96, 32, j);
		} else {
			g.drawImage(ReadImgUtil.player1, x * 32, y * 32, (x + 1) * 32, (y + 1) * 32, (step + state * 2) * 32,
					dir * 32, (step + state * 2 + 1) * 32, (dir + 1) * 32, j);
		}
	}

	// 坦克移动
	public void move(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			// 向上移动
			if (dir == 0) {
				if (canMove(x, y - speed)) {
					y = y - speed;
				}
			} else {
				dir = 0;
			}
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			// 向右移动
			if (dir == 1) {
				if (canMove(x + speed, y)) {
					x = x + speed;
				}
			} else {
				dir = 1;
			}
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			// 向下移动
			if (dir == 2) {
				if (canMove(x, y + speed)) {
					y = y + speed;
				}
			} else {
				dir = 2;
			}
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			// 向左移动
			if (dir == 3) {
				if (canMove(x - speed, y)) {
					x = x - speed;
				}
			} else {
				dir = 3;
			}
			break;
		case KeyEvent.VK_J:
		case KeyEvent.VK_SPACE:
			this.shot();
			PlayMusicUtil.playMusic("shot.wav");
			break;
		}
	}

	// 发射子弹
	public void shot() {
		Datacenter.mbs.add(new Bullet(x, y, dir, 1, 1, true));
	}

	@Override
	public void run() {
		// 坦克履带转动
		while (isAlive) {
			try {
				Thread.sleep(200);
				step++;
				if (step > 1) {
					step = 0;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Datacenter.mt = null;
		Datacenter.bos.add(new Boom(x, y, 1));
		PlayMusicUtil.playMusic("boom.wav");
		Datacenter.attack.addTime();
		if (life > 0) {
			MyJPanel.getInstance().addMyStar();
			life--;
		} else {
			Datacenter.gameover = true;
			GameOverPanel.getInstance().gameOver();
			PlayMusicUtil.playMusic("end.wav");
			Datacenter.endTime = new Date();
		}
	}
}
