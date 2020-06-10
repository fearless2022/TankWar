package com.woniuxy.tankwar.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.util.ReadImgUtil;

//游戏结束画面
public class GameOverPanel extends JPanel {
	private static GameOverPanel gop = null;

	public static GameOverPanel getInstance() {
		if (gop == null) {
			gop = new GameOverPanel();
		}
		return gop;
	}

	private GameOverPanel() {
		// 取消当前容器的默认布局
		this.setLayout(null);
		this.setSize(832, 800);
		this.setLocation(0, 0);
	}

	// 游戏结束，清空所有状态
	public void gameOver() {
		Datacenter.mt.isAlive = false;
		for (int i = 0; i < Datacenter.ets.size(); i++) {
			Datacenter.ets.get(i).isAlive = false;
		}
		for (int i = 0; i < Datacenter.mbs.size(); i++) {
			Datacenter.mbs.get(i).isAlive = false;
		}
		for (int i = 0; i < Datacenter.ebs.size(); i++) {
			Datacenter.ebs.get(i).isAlive = false;
		}
		for (int i = 0; i < Datacenter.bos.size(); i++) {
			Datacenter.bos.get(i).isAlive = false;
		}
		for (int i = 0; i < Datacenter.bfs.size(); i++) {
			Datacenter.bfs.get(i).isAlive = false;
		}
		for (int i = 0; i < Datacenter.bss.size(); i++) {
			Datacenter.bss.get(i).isAlive = false;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ReadImgUtil.gameover, 0, 0, 832, 800, 0, 0, 960, 640, this);
	}
}
