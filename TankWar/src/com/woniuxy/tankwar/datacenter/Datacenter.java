package com.woniuxy.tankwar.datacenter;

import java.util.Date;
import java.util.Vector;

import com.woniuxy.tankwar.character.EnemyTank;
import com.woniuxy.tankwar.character.MyTank;
import com.woniuxy.tankwar.effector.Attack;
import com.woniuxy.tankwar.effector.Boom;
import com.woniuxy.tankwar.effector.BornStar;
import com.woniuxy.tankwar.effector.Buff;
import com.woniuxy.tankwar.effector.Bullet;

public class Datacenter {
	// 游戏是否开始
	public static boolean gamestart = false;
	// 游戏是否结束
	public static boolean gameover = false;
	// 游戏开始时间
	public static Date startTime;
	// 游戏结束时间
	public static Date endTime;
	// 当前地图
	public static int[][] nowMap;
	// 攻击特效
	public static Attack attack;
	// 我方坦克
	public static MyTank mt;
	// 敌方坦克
//	public static EnemyTank et;
	public static Vector<EnemyTank> ets = new Vector<>();
	// 星星
//	public static BornStart bs;
	public static Vector<BornStar> bss = new Vector<>();
	// 我方子弹
	public static Vector<Bullet> mbs = new Vector<>();
	// 敌方子弹
	public static Vector<Bullet> ebs = new Vector<>();
	// 爆炸
	public static Vector<Boom> bos = new Vector<>();
	// BUFF
	public static Vector<Buff> bfs = new Vector<>();

}
