package com.sheen.water.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class BackStage extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackStage frame = new BackStage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BackStage() {
		setTitle("桶装水后台页面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 539);
		
		initMenu();
		new UpdateTableThread().start();
		
	}
	
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("添加桶装水类型");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new CreateProducts();
			}
		});
		mntmNewMenuItem_1.setFont(new Font("宋体", Font.PLAIN, 17));
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_1.setForeground(Color.BLUE);
		menuBar.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("订单处理");
		mntmNewMenuItem.setFont(new Font("宋体", Font.PLAIN, 17));
		mntmNewMenuItem.setForeground(Color.BLUE);
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("删除历史订单");
		mntmNewMenuItem_2.setFont(new Font("宋体", Font.PLAIN, 17));
		mntmNewMenuItem_2.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_2.setForeground(Color.BLUE);
		menuBar.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("退出系统");
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		mntmNewMenuItem_3.setFont(new Font("宋体", Font.PLAIN, 17));
		mntmNewMenuItem_3.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_3.setForeground(Color.BLUE);
		menuBar.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable();
		contentPane.add(table, BorderLayout.NORTH);
	}
	
	// 刷新
	private void flushMatchedTable() {
		// 创建tableModel
		model = new DefaultTableModel();
		// 使用tableModel创建JTable
		JTable table = new JTable(model);
		// 通过JTable对象创建JScrollPane，显示数据
		scrollPane = new JScrollPane(table);
		// 添加XX选项卡
		contentPane.add("XX", scrollPane);
	}
		
		
	// 线程类，每隔两分钟刷新一次显示数据表格中的数据
	private class UpdateTableThread extends Thread {
		// 重写run()方法
		public void run() {
			while (true) {
				// 移除所有的选项卡
				contentPane.removeAll();
				// 刷新表格信息
				flushMatchedTable();
				try {
					// 线程挂起两分钟
					Thread.sleep(2*60*1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
