package com.woniuxy.tankwar.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.woniuxy.tankwar.util.ReadImgUtil;

//通关画面
public class GamePassPanel extends JPanel {
	private static GamePassPanel pp = null;

	public static GamePassPanel getInstance() {
		if (pp == null) {
			pp = new GamePassPanel();
		}
		return pp;
	}

	private GamePassPanel() {
		// 取消当前容器的默认布局
		this.setLayout(null);
		this.setSize(832, 800);
		this.setLocation(0, 0);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ReadImgUtil.gamepass, 0, 0, 832, 800, 0, 0, 640, 960, this);
	}
}
