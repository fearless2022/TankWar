package com.woniuxy.tankwar.ui;

import java.util.Date;
import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.util.PlayMusicUtil;

/**
 * @author Administrator Jframe:顶层容器（最外层画框） Jpanel:画板
 */
//开始界面
public class Begin {
	public void init() {
//		// 新建主窗口，并加标题名
//		JFrame j = new JFrame("TankWar");
//		// 获取工具类
//		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
//		// 获取屏幕的尺寸
//		Dimension screenSize = defaultToolkit.getScreenSize();
//		// 设置窗口大小
//		j.setSize(816, 832);
//		// 设置窗口居中
//		j.setLocation((int) (screenSize.getWidth() - 816) / 2, (int) (screenSize.getHeight() - 832) / 2);
//		// 取消当前容器的默认布局
//		j.setLayout(null);
//		// 设置内层画板,获取父类所有画板对象，添加一个组件
//		j.getContentPane().add(new MyJpanel());
//		//j.setContentPane(new MyJpanel());
//		// 显示窗口
//		j.setVisible(true);
//		// 设置关闭选项（关闭窗口，结束程序）
//		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MyJFrame.getInstance();
	}

	public static void main(String[] args) {
		new Begin().init();
	}

}
