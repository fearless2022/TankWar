package com.woniuxy.tankwar.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import com.woniuxy.tankwar.character.EnemyTank;
import com.woniuxy.tankwar.character.MyTank;
import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.util.ReadImgUtil;

//计分板
public class GameScorePanel extends JPanel implements Runnable {
	public GameScorePanel() {
		// 取消当前容器的默认布局
		this.setLayout(null);
		this.setSize(300, 800);
		this.setLocation(832, 0);
		new Thread(this).start();
	}

	// 切换图片
	public int step = 0;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ReadImgUtil.nature, 0, 0, 300, 800, step, 0, step + 300, 1536, this);
		g.setColor(Color.RED);
		g.setFont(new Font("黑体", Font.ITALIC, 22));
		Date nowTime = new Date();
		g.drawString(showTime(nowTime), 30, 100);
		g.drawString("分数：" + EnemyTank.num + " 分", 30, 150);
		g.drawString("剩余敌人：" + EnemyTank.left + " 辆", 30, 200);
		g.drawString("剩余生命：" + MyTank.life + " 次", 30, 250);
		if (Datacenter.endTime != null) {
			g.drawString("游戏时间：" + (Datacenter.endTime.getTime() - Datacenter.startTime.getTime()) / 1000 + " 秒", 30,
					300);
		} else {
			g.drawString("游戏时间：" + (nowTime.getTime() - Datacenter.startTime.getTime()) / 1000 + " 秒", 30, 300);
		}
	}

	// 转换时间
	public String showTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH：mm：ss");
		return sdf.format(date);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
				this.repaint();
				if (step > 2260) {
					step = 0;
				} else {
					step++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
