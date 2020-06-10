package com.woniuxy.tankwar.character;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.effector.Boom;
import com.woniuxy.tankwar.effector.Bullet;
import com.woniuxy.tankwar.ui.MyJFrame;
import com.woniuxy.tankwar.ui.MyJPanel;
import com.woniuxy.tankwar.ui.GamePassPanel;
import com.woniuxy.tankwar.util.PlayMusicUtil;
import com.woniuxy.tankwar.util.ReadImgUtil;

//敌方坦克
public class EnemyTank extends Tank implements Runnable {
	public static int num = 0;
	public static int pass = 20;
	public static int left = pass;
	Random r = new Random();

	public EnemyTank(int x, int y, int dir, int speed, int atk, int hp, int state, int type) {
		super(x, y, dir, speed, atk, hp, state, type);
		new Thread(this).start();
	}

	@Override
	public void drawTank(Graphics g, JPanel j) {
		if (Datacenter.nowMap[y][x] == 3) {
			g.drawImage(ReadImgUtil.enemy, x * 32, y * 32, (x + 1) * 32, (y + 1) * 32,
					(step + state * 2 + type * 4) * 32, dir * 32, (step + state * 2 + type * 4 + 1) * 32,
					(dir + 1) * 32, j);
			g.drawImage(ReadImgUtil.map_element, (x * 32) + 2, y * 32 + 2, (x + 1) * 32 - 2, (y + 1) * 32 - 2, 64, 0,
					96, 32, j);
		} else {
			g.drawImage(ReadImgUtil.enemy, x * 32, y * 32, (x + 1) * 32, (y + 1) * 32,
					(step + state * 2 + type * 4) * 32, dir * 32, (step + state * 2 + type * 4 + 1) * 32,
					(dir + 1) * 32, j);
		}
	}

	public void move() {
		switch (dir) {
		case 0:
			// 向上移动
			if (canMove(x, y - speed)) {
				y = y - speed;
			} else {
				dir = r.nextInt(4);
			}
			break;
		case 1:
			// 向右移动
			if (canMove(x + speed, y)) {
				x = x + speed;
			} else {
				dir = r.nextInt(4);
			}
			break;
		case 2:
			// 向下移动
			if (canMove(x, y + speed)) {
				y = y + speed;
			} else {
				dir = r.nextInt(4);
			}
			break;
		case 3:
			// 向左移动
			if (canMove(x - speed, y)) {
				x = x - speed;
			} else {
				dir = r.nextInt(4);
			}
			break;
		}
	}

	public void shot() {
		// 随机发射1-2颗子弹
		for (int i = 0; i < r.nextInt(2) + 1; i++) {
			Datacenter.ebs.add(new Bullet(x, y, dir, 1, 1, false));
		}
	}

	@Override
	public void run() {
		// 坦克履带转动
		while (isAlive) {
			try {
				this.move();
				if (r.nextInt(100) > 90) {
					this.shot();
					PlayMusicUtil.playMusic("shot.wav");
				}
				Thread.sleep(200);
				step++;
				if (step > 1) {
					step = 0;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Datacenter.ets.remove(this);
		Datacenter.bos.add(new Boom(x, y, 1));
		PlayMusicUtil.playMusic("boom.wav");
		if (EnemyTank.num < EnemyTank.pass) {
			EnemyTank.num++;
		} else {
			EnemyTank.num = EnemyTank.pass;
		}
		if (EnemyTank.left > 0) {
			EnemyTank.left--;
		} else {
			EnemyTank.left = 0;
		}
		if (EnemyTank.left == 0) {
			MyJFrame.getInstance().remove(MyJPanel.getInstance());
			MyJFrame.getInstance().add(GamePassPanel.getInstance());
			MyJFrame.getInstance().repaint();
		}
		Datacenter.attack.addTime();
		MyJPanel.getInstance().addMoreEnemyStar();
	}
}
