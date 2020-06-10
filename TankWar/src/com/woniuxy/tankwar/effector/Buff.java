package com.woniuxy.tankwar.effector;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

import com.woniuxy.tankwar.character.MyTank;
import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.util.ReadImgUtil;

//BUFF效果
public class Buff implements Runnable {
	public int x;
	public int y;
	public int time;// 1秒休眠5次，10秒50次
	public int type;// BUFF类型
	public boolean isAlive = true;
	public int count = 0;
	Timer t = new Timer();

	public Buff(int x, int y, int type, int time) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.time = time;
		new Thread(this).start();
	}

	// 画BUFF
	public void drawBuff(Graphics g, JPanel j) {
		g.drawImage(ReadImgUtil.buff, x * 32, y * 32, (x + 1) * 32, (y + 1) * 32, type * 32, 0, (type + 1) * 32, 32, j);
	}

	// 吃BUFF
	public void eatBuff() {
		if (Datacenter.mt != null) {
			if (Datacenter.mt.x == x && Datacenter.mt.y == y) {
				isAlive = false;
				this.createEffector(type);
			}
		}
	}

	// 产生BUFF效果
	public void createEffector(int type) {
		switch (type) {
		case 0:
			// 加生命
			MyTank.life++;
			break;
		case 1:
			// 定时
			for (int i = 0; i < Datacenter.ets.size(); i++) {
				Datacenter.ets.get(i).speed = 0;
			}
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					for (int i = 0; i < Datacenter.ets.size(); i++) {
						Datacenter.ets.get(i).speed = 1;
					}
				}
			}, 5000);
			break;
		case 2:
			// 改变boss周围地形
			Datacenter.nowMap[25][12] = 1;
			Datacenter.nowMap[24][12] = 1;
			Datacenter.nowMap[24][13] = 1;
			Datacenter.nowMap[24][14] = 1;
			Datacenter.nowMap[25][14] = 1;
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					Datacenter.nowMap[25][12] = 2;
					Datacenter.nowMap[24][12] = 2;
					Datacenter.nowMap[24][13] = 2;
					Datacenter.nowMap[24][14] = 2;
					Datacenter.nowMap[25][14] = 2;
				}
			}, 5000);
			break;
		case 3:
			// 炸弹
			for (int i = 0; i < Datacenter.ets.size(); i++) {
				Datacenter.ets.get(i).isAlive = false;
			}
			break;
		case 4:
			// 升级
			if (Datacenter.mt.state < 3) {
				Datacenter.mt.state++;
			} else {
				Datacenter.mt.state = 3;
			}
			break;
		case 5:
			// 钢盔
			break;
		}
	}

	@Override
	public void run() {
		while (isAlive) {
			try {
				this.eatBuff();
				if (count < time * 5) {
					Thread.sleep(200);
					count++;
				} else {
					isAlive = false;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Datacenter.bfs.remove(this);
	}
}
