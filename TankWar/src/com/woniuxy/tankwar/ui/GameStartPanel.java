package com.woniuxy.tankwar.ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.JPanel;
import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.util.PlayMusicUtil;
import com.woniuxy.tankwar.util.ReadImgUtil;

//游戏开始画面
public class GameStartPanel extends JPanel {
	private static GameStartPanel gsp = null;

	public static GameStartPanel getInstance() {
		if (gsp == null) {
			gsp = new GameStartPanel();
		}
		return gsp;
	}

	private GameStartPanel() {
		// 取消当前容器的默认布局
		this.setLayout(null);
		this.setSize(1132, 800);
		this.setLocation(0, 0);
	}

	// 判断是否开始游戏
	public void start(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_1:
			this.gameStart();
			Datacenter.gamestart = true;
			break;
		}
	}

	// 开始游戏，添加画板
	public void gameStart() {
		MyJFrame.getInstance().remove(this);
		MyJFrame.getInstance().repaint();
		Datacenter.startTime = new Date();
		PlayMusicUtil.playMusic("start.wav");
		MyJFrame.getInstance().getContentPane().add(MyJPanel.getInstance());
		MyJFrame.getInstance().getContentPane().add(new GameScorePanel());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ReadImgUtil.gamestart, 0, 0, 1132, 800, 0, 0, 1080, 840, this);
	}
}
