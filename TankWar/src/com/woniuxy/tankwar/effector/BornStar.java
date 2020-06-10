package com.woniuxy.tankwar.effector;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import com.woniuxy.tankwar.character.EnemyTank;
import com.woniuxy.tankwar.character.MyTank;
import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.util.ReadImgUtil;

//坦克出生效果
public class BornStar implements Runnable {
	public int x;
	public int y;
	public int time;// 1秒休眠5次，3秒15次
	public boolean isAlive = true;
	public boolean isMyTank = true;
	public int step = 0;
	public int count = 0;

	public BornStar(int x, int y, int time, boolean isMyTank) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.isMyTank = isMyTank;
		new Thread(this).start();
	}

	// 画星星
	public void drawStar(Graphics g, JPanel j) {
		g.drawImage(ReadImgUtil.star, x * 32, y * 32, (x + 1) * 32, (y + 1) * 32, step * 32, 0, (step + 1) * 32, 32, j);
	}

	// 创建坦克
	public void createTank() {
		if (isMyTank) {
			Datacenter.mt = new MyTank(x, y, 0, 1, 1, 1);
		} else {
			Random r = new Random();
			// 随机状态
			int state = r.nextInt(2);
			// 随机种类
			int type = r.nextInt(4);
			Datacenter.ets.add(new EnemyTank(x, y, 2, 1, 1, 1, state, type));
		}
	}

	@Override
	public void run() {
		while (isAlive) {
			try {
				if (count < time * 5) {
					Thread.sleep(200);
					step++;
					count++;
					if (step > 3) {
						step = 0;
					}
				} else {
					isAlive = false;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Datacenter.bss.remove(this);
		this.createTank();
	}
}
