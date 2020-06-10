package com.woniuxy.tankwar.effector;

import java.awt.Graphics;
import javax.swing.JPanel;
import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.util.ReadImgUtil;

//爆炸
public class Boom implements Runnable {
	public int x;
	public int y;
	public int time;// 1秒休眠5次，3秒15次
	public boolean isAlive = true;
	public int step = 0;
	public int count = 0;

	public Boom(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
		new Thread(this).start();
	}

	// 画爆炸
	public void drawBoom(Graphics g, JPanel j) {
		g.drawImage(ReadImgUtil.boom, x * 32, y * 32, (x + 1) * 32, (y + 1) * 32, step * 64, 0, (step + 1) * 64, 64, j);
	}

	@Override
	public void run() {
		while (isAlive) {
			try {
				if (count < time * 5) {
					Thread.sleep(200);
					step++;
					count++;
					if (step > 6) {
						step = 0;
					}
				} else {
					isAlive = false;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Datacenter.bos.remove(this);
	}

}
