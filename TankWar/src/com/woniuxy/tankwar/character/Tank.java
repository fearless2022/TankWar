package com.woniuxy.tankwar.character;

import java.awt.Graphics;
import javax.swing.JPanel;
import com.woniuxy.tankwar.datacenter.Datacenter;

//抽象Tank父类
public abstract class Tank {
	public int x;
	public int y;
	public int dir;// 方向：0：向上，1：向右，2：向下，3：向左
	public int speed;
	public int atk;
	public int hp;
	public int state = 0;
	public int type = 0;
	public boolean isAlive = true;
	public boolean isMyTank;
	// 控制履带转动
	public int step = 0;

	// 我方坦克构造方法
	public Tank(int x, int y, int dir, int speed, int atk, int hp) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.speed = speed;
		this.atk = atk;
		this.hp = hp;
	}

	// 敌方坦克构造方法
	public Tank(int x, int y, int dir, int speed, int atk, int hp, int state, int type) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.speed = speed;
		this.atk = atk;
		this.hp = hp;
		this.state = state;
		this.type = type;
	}

	// 画坦克角色
	public abstract void drawTank(Graphics g, JPanel j);

	// 判断地形
	public boolean canMove(int nowX, int nowY) {
		// 方向：0：向上，1：向右，2：向下，3：向左
		// 地形：0：空地，1：铁墙，2：土墙，3：草地，4：水，5：boss
		switch (Datacenter.nowMap[nowY][nowX]) {
		case 0:
		case 3:
			return true;
		}
		return false;
	}
}
