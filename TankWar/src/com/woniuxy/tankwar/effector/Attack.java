package com.woniuxy.tankwar.effector;

import java.awt.Graphics;
import javax.swing.JPanel;
import com.woniuxy.tankwar.util.ReadImgUtil;

//攻击特效
public class Attack {
	int time = 0;

	public void drawAttack(Graphics g, JPanel j) {
		if (time > 0) {
			g.drawImage(ReadImgUtil.attack, 0, 0, 832, 832, 0, 0, 568, 384, j);
			time--;
		}
	}

	// 改变时间
	public void addTime() {
		time = time + 2;
	}
}
