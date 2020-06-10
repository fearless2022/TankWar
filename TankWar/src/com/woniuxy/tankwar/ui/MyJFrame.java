package com.woniuxy.tankwar.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.JFrame;
import com.woniuxy.tankwar.datacenter.Datacenter;
import com.woniuxy.tankwar.util.PlayMusicUtil;

/**
 * @author Administrator Jframe:顶层容器（最外层画框）
 */
public class MyJFrame extends JFrame {
	private static MyJFrame mf = null;

	public static MyJFrame getInstance() {
		if (mf == null) {
			mf = new MyJFrame();
		}
		return mf;
	}

	private MyJFrame() {
		// 新建主窗口，并加标题名
		super("TankWar");
		// 获取工具类
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		// 获取屏幕的尺寸
		Dimension screenSize = defaultToolkit.getScreenSize();
		// 设置窗口大小
		this.setSize(1148, 840);
		// 设置窗口居中
		this.setLocation((int) (screenSize.getWidth() - 1148) / 2, (int) (screenSize.getHeight() - 848) / 2);
		// 取消当前容器的默认布局
		this.setLayout(null);
		// 设置内层画板,获取父类所有画板对象，添加组件
//		this.setContentPane(new MyJpanel());
		this.getContentPane().add(GameStartPanel.getInstance());
		// 添加键盘监听
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (Datacenter.mt != null) {
					Datacenter.mt.move(e);
				}
				while (!Datacenter.gamestart) {
					GameStartPanel.getInstance().start(e);
				}
			}
		});
		// 显示窗口
		this.setVisible(true);
		// 设置关闭选项（关闭窗口，结束程序）
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
