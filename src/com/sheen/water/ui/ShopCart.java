package com.sheen.water.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sheen.water.data.Model.OrderItemTableModel;
import com.sheen.water.data.po.OrderItems;
import com.sheen.water.service.OrderItemsService;

import javax.swing.JTable;

public class ShopCart extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopCart frame = new ShopCart();
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
	public ShopCart() {
		setTitle("你的购物车");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		table = new JTable();
		table.setCellSelectionEnabled(true); 
		panel.add(table);
		
		
		//获取Bean，这个无法直接依赖注入
		@SuppressWarnings("resource")
		ApplicationContext act =  new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderItemsService service = act.getBean(OrderItemsService.class);
	    List<OrderItems> list=service.findAll();
	    OrderItemTableModel tableModel = new OrderItemTableModel();
	    tableModel.setData(list);
	    table = new JTable(tableModel);
		// 通过JTable对象创建JScrollPane，显示数据
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane,BorderLayout.CENTER);
		setContentPane(contentPane);
		
		new UpdateTableThread().start();
	}
	
	// 刷新
	private void flushMatchedTable() {
			// 创建tableModel
			@SuppressWarnings("resource")
			ApplicationContext act =  new ClassPathXmlApplicationContext("applicationContext.xml");
			OrderItemsService service = act.getBean(OrderItemsService.class);
		    List<OrderItems> list=service.findAll();
		    OrderItemTableModel tableModel = new OrderItemTableModel();
		    tableModel.setData(list);
		    table = new JTable(tableModel);
			// 通过JTable对象创建JScrollPane，显示数据
			JScrollPane scrollPane = new JScrollPane(table);
			contentPane.add(scrollPane,BorderLayout.CENTER);
			setContentPane(contentPane);
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
