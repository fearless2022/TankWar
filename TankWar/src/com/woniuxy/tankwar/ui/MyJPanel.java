package com.woniuxy.tankwar.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.effector.Attack;
import com.woniuxy.tankwar.effector.BornStar;
import com.woniuxy.tankwar.map.Map;
import com.woniuxy.tankwar.util.ReadImgUtil;

/**
 * @author Administrator Jpanel:画板
 */
//主画板
public class MyJPanel extends JPanel implements Runnable {
	private static MyJPanel mp = null;

	public static MyJPanel getInstance() {
		if (mp == null) {
			mp = new MyJPanel();
		}
		return mp;
	}

	private MyJPanel() {
		this.initGame();
	}

	// 初始化游戏
	public void initGame() {
		// 取消当前容器的默认布局
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setSize(864, 864);
		this.setLocation(-32, -32);
		// 初始化地图
		Datacenter.nowMap = Map.map_1;
		// 初始化攻击特效
		Datacenter.attack = new Attack();
		// 初始化我方坦克
//		Datacenter.mt = new MyTank(10, 25, 0, 1, 10, 10);
		// 初始化敌方坦克
//		Datacenter.et = new EnemyTank(9, 25, 0, 1, 10, 10);
//		this.addEnemyTank();
		// 初始敌方化星星
//		Datacenter.bs = new BornStart(2, 1, 3, true);
		this.addEnemyStar();
		// 初始化友方星星
		this.addMyStar();
		// 开启线程
		new Thread(this).start();
	}

	// 添加敌方星星
	public void addEnemyStar() {
		// 添加3个固定位置，3个随机固定位置
		Datacenter.bss.add(new BornStar(1, 1, 3, false));// 左上角
		Datacenter.bss.add(new BornStar(13, 1, 3, false));// 中间
		Datacenter.bss.add(new BornStar(25, 1, 3, false));// 右上角
		while (Datacenter.bss.size() < 6) {
			this.addMoreEnemyStar();
		}
	}

	// 添加随机敌方星星
	public void addMoreEnemyStar() {
		// 0-3随机数
		Random r = new Random();
		int num = r.nextInt(3);
		switch (num) {
		case 0:
			Datacenter.bss.add(new BornStar(1, 1, 3, false));
			break;
		case 1:
			Datacenter.bss.add(new BornStar(13, 1, 3, false));
			break;
		case 2:
			Datacenter.bss.add(new BornStar(25, 1, 3, false));
			break;
		}
	}

	// 添加我方星星
	public void addMyStar() {
		Datacenter.bss.add(new BornStar(10, 25, 3, true));
	}

	// 添加敌方坦克
//	public void addEnemyTank() {
//		Random r = new Random();
//		for(int i = Datacenter.ets.size();i<3;i++) {
//			//随机坐标
//			int x = r.nextInt(25)+1;
//			//随机状态（true-->0：银色，false-->2：红色）
//			boolean flag = r.nextBoolean();
//			if(flag) {
//				Datacenter.ets.add(new EnemyTank(x, 1, 2, 1, 10, 10,0));
//			}else {
//				Datacenter.ets.add(new EnemyTank(x, 1, 2, 1, 10, 10,2));
//			}
//		}
//	}
	@Override
	// Graphics g ：画笔
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawMap(g);
		this.drawBornStar(g);
		this.drawTank(g);
		this.drawBullet(g);
		this.drawBoom(g);
		this.drawBuff(g);
		this.drawAttack(g);
	}

	// 画攻击特效
	public void drawAttack(Graphics g) {
		if (Datacenter.attack != null) {
			Datacenter.attack.drawAttack(g, this);
		}
	}

	// 画Buff
	public void drawBuff(Graphics g) {
		for (int i = 0; i < Datacenter.bfs.size(); i++) {
			Datacenter.bfs.get(i).drawBuff(g, this);
		}
	}

	// 画爆炸
	public void drawBoom(Graphics g) {
		for (int i = 0; i < Datacenter.bos.size(); i++) {
			Datacenter.bos.get(i).drawBoom(g, this);
		}
	}

	// 画子弹
	public void drawBullet(Graphics g) {
		// 画我方子弹
		for (int i = 0; i < Datacenter.mbs.size(); i++) {
			Datacenter.mbs.get(i).drawBullet(g, this);
		}
		// 画敌方子弹
		for (int i = 0; i < Datacenter.ebs.size(); i++) {
			Datacenter.ebs.get(i).drawBullet(g, this);
		}
	}

	// 画星星
	public void drawBornStar(Graphics g) {
//		Datacenter.bs.drawStar(g, this);
		for (int i = 0; i < Datacenter.bss.size(); i++) {
			Datacenter.bss.get(i).drawStar(g, this);
		}
	}

	// 画坦克
	public void drawTank(Graphics g) {
		// 画我方坦克
		if (Datacenter.mt != null) {// 不为空，才能画坦克
			Datacenter.mt.drawTank(g, this);
		}
		// 画敌方坦克
//		Datacenter.et.drawTank(g, this);
		for (int i = 0; i < Datacenter.ets.size(); i++) {
			Datacenter.ets.get(i).drawTank(g, this);
		}
	}

	// 切换水流图片
	public int step = 3;

	// 画地图
	public void drawMap(Graphics g) {
		// 两个点确定一个位置，第一组：确定放在画板上的大小，第二组：截取图片的大小
//		g.drawImage(ReadImgUtil.map, 0, 0, 224, 32, 0, 0, 224, 32, this);
		for (int i = 0; i < Datacenter.nowMap.length; i++) {
			for (int j = 0; j < Datacenter.nowMap[i].length; j++) {
				switch (Datacenter.nowMap[i][j]) {
				case 1:
					// 钢墙
					g.drawImage(ReadImgUtil.map_element, j * 32, i * 32, (j + 1) * 32, (i + 1) * 32, 32, 0, 64, 32,
							this);
					break;
				case 2:
					// 土墙
					g.drawImage(ReadImgUtil.map_element, j * 32, i * 32, (j + 1) * 32, (i + 1) * 32, 0, 0, 32, 32,
							this);
					break;
				case 3:
					// 草地
					g.drawImage(ReadImgUtil.map_element, j * 32, i * 32, (j + 1) * 32, (i + 1) * 32, 64, 0, 96, 32,
							this);
					break;
				case 4:
					// 水
					g.drawImage(ReadImgUtil.map_element, j * 32, i * 32, (j + 1) * 32, (i + 1) * 32, step * 32, 0,
							(step + 1) * 32, 32, this);
					break;
				case 5:
					// boss
					g.drawImage(ReadImgUtil.map_element, j * 32, i * 32, (j + 1) * 32, (i + 1) * 32, 160, 0, 192, 32,
							this);
					break;
				}
			}
		}
	}

	@Override
	public void run() {
		// 刷新画板
		while (!Datacenter.gameover) {
			try {
				this.repaint();
				Thread.sleep(100);
				step++;
				if (step > 4) {
					step = 3;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		MyJFrame.getInstance().remove(this);
		MyJFrame.getInstance().add(GameOverPanel.getInstance());
		MyJFrame.getInstance().repaint();
	}
}
