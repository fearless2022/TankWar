package com.woniuxy.tankwar.map;

//静态地图数据
public class Map {
	/*
	 * 地形：0：空地，1：铁墙，2：土墙，3：草地，4：水，5：boss
	 */
	public static int[][] map_1 = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 3, 3, 3, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 2, 0, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 1 },
			{ 1, 0, 0, 0, 2, 0, 0, 0, 3, 3, 3, 0, 2, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 4, 4, 4, 0, 2, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 2, 0, 2, 0, 0, 2, 2, 2, 2, 2, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 2, 0, 2, 0, 0, 0, 0, 2, 0, 2, 0, 0, 3, 0, 0, 0, 0, 0, 2, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 2, 2, 2, 1, 1, 0, 0, 2, 0, 2, 0, 0, 3, 0, 0, 0, 3, 0, 2, 0, 1 },
			{ 1, 0, 0, 0, 2, 2, 2, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 2, 2, 0, 3, 0, 2, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 2, 2, 2, 0, 0, 3, 0, 2, 1, 1 },
			{ 1, 4, 4, 4, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 3, 3, 2, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 1 },
			{ 1, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 1 },
			{ 1, 0, 3, 3, 3, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 4, 4, 2, 2, 1 },
			{ 1, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1 },
			{ 1, 0, 3, 0, 0, 2, 4, 4, 4, 0, 2, 0, 2, 3, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1 },
			{ 1, 1, 3, 0, 0, 2, 0, 0, 4, 0, 2, 0, 2, 0, 3, 2, 0, 0, 0, 0, 0, 1, 0, 0, 2, 2, 1 },
			{ 1, 0, 0, 0, 0, 2, 2, 0, 4, 0, 2, 2, 2, 0, 0, 0, 4, 4, 3, 0, 0, 1, 0, 0, 2, 0, 1 },
			{ 1, 0, 0, 2, 2, 2, 0, 0, 2, 0, 2, 0, 1, 1, 0, 2, 0, 0, 3, 0, 0, 1, 0, 0, 2, 0, 1 },
			{ 1, 0, 2, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 3, 2, 2, 2, 2, 2, 2, 0, 1 },
			{ 1, 0, 2, 0, 0, 2, 3, 3, 2, 0, 2, 3, 2, 2, 2, 2, 0, 3, 3, 0, 2, 0, 0, 2, 0, 0, 1 },
			{ 1, 2, 2, 2, 2, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 2, 0, 0, 2, 0, 3, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 2, 0, 0, 1 },
			{ 1, 0, 3, 0, 0, 2, 0, 0, 2, 0, 0, 0, 2, 2, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 3, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 5, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, };
}