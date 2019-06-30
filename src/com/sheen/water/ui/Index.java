package com.sheen.water.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sheen.water.data.Model.ProductsTableModel;
import com.sheen.water.data.po.OrderItems;
import com.sheen.water.data.po.Products;
import com.sheen.water.service.ProductsService;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class Index extends JFrame {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JScrollPane scrollPane;
	private JTable table;
	JPanel panel2,panel;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
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
	public Index() {
		setTitle("客户服务页面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 455);
		initMenu();
	
	}
	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("     我要下单   ");
		mnNewMenu.setFont(new Font("宋体", Font.PLAIN, 17));
		mnNewMenu.setHorizontalAlignment(SwingConstants.RIGHT);
		mnNewMenu.setForeground(Color.BLUE);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("我要下单      ");
		mntmNewMenuItem_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		mnNewMenu.add(mntmNewMenuItem_1);
				
		JMenu mnNewMenu_3 = new JMenu("     购物车        ");
		mnNewMenu_3.setForeground(Color.BLUE);
		mnNewMenu_3.setFont(new Font("宋体", Font.PLAIN, 17));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("退出系统");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem.setFont(new Font("宋体", Font.PLAIN, 17));
		mntmNewMenuItem.setForeground(Color.RED);
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel2 = new JPanel();
		contentPane.add(panel2, BorderLayout.CENTER);
		
		//获取Bean，这个无法直接依赖注入
		@SuppressWarnings("resource")
		ApplicationContext act =  new ClassPathXmlApplicationContext("applicationContext.xml");
	    ProductsService service = act.getBean(ProductsService.class);
	    List <Products> list=service.findAll();
	    ProductsTableModel tableModel = new ProductsTableModel();
	    tableModel.setData(list);
	    table = new JTable(tableModel);
	    //table.set
	    //支持多选
	  	table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// 通过JTable对象创建JScrollPane，显示数据
		scrollPane = new JScrollPane(table);
		panel2.add(scrollPane,BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setBounds(158, 383, 501, 157);
		contentPane.add(panel, BorderLayout.SOUTH);
	
		JLabel label = new JLabel("  数量：");
		label.setBounds(150, 8, 65, 37);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(219, 10, 98, 36);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("下单！");
		button.setBounds(57, 76, 113, 51);
		panel.add(button);
		button.addMouseListener(new MouseAdapter() {
			//鼠标单击的事件
			@Override
			public void mouseClicked(MouseEvent e) {
				OrderItems orderItem=new OrderItems();
				int selectRow []=table.getSelectedRows();
				if(selectRow.length == 0) {
					JOptionPane.showMessageDialog(null, "请选择需要下单的水的规格!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				else {
					orderItem.setKind(Double.parseDouble(table.getValueAt(selectRow[0],0).toString()));
					//orderItem.setPrice(Double.parseDouble(table.getValueAt(selectRow[0],1).toString()));
					orderItem.setQuantity(Integer.parseInt(textField.getText()));
					orderItem.update();
					
					Socket socket = null;
					//1、创建客户端Socket，指定服务器地址和端口
					try {
						socket = new Socket("127.0.0.1",28887);
					} catch (UnknownHostException e1) {
						System.out.println("创建客户端Socket失败！- UnknownHostException");
						e1.printStackTrace();
					} catch (IOException e1) {
						System.out.println("创建客户端Socket失败！- IOException ");
						e1.printStackTrace();
					}
					
					ObjectOutputStream oos = null;
					//2、获取输出流，向服务器端发送信息
					try {
						oos =  new ObjectOutputStream(socket.getOutputStream());
					} catch (IOException e1) {
						System.out.println("获取输出流出现异常！- IOException ");
						e1.printStackTrace();
					}
						
					//字节输出流
					try {
						oos.writeObject(orderItem);
						oos.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					//关闭输出流
					try {
						socket.shutdownOutput();
					} catch (IOException e1) {
						System.out.println("socket.shutdownOutput()-出现异常！- IOException ");
						e1.printStackTrace();
					}
					
					ObjectInputStream ois = null;
					//3、获取输入流，并读取服务器端的响应信息
					try {
						ois = new ObjectInputStream(socket.getInputStream());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						boolean rs= (boolean) ois.readObject();
						if(rs == true) {
							JOptionPane.showMessageDialog(null, "下单成功！","友情提示",JOptionPane.INFORMATION_MESSAGE);
							this.dispose();
							new Index().setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "下单错误","输入错误",JOptionPane.ERROR_MESSAGE);
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//关闭输入流
					try {
						socket.shutdownInput();
					} catch (IOException e1) {
						System.out.println("关闭下单的Socket的输入流失败！！");
						e1.printStackTrace();
					}								
				}		
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton button_1 = new JButton("结算！");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		button_1.setBounds(289, 75, 113, 51);
		panel.add(button_1);
		
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			//鼠标单击的事件
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		mnNewMenu_3.addMouseListener(new MouseAdapter() {
			//鼠标单击的事件
			@Override
			public void mouseClicked(MouseEvent e) {
				new ShopCart().setVisible(true);
			}
		});
		tableModel.fireTableDataChanged();
		//new UpdateTableThread().start();
	}
	
		// 刷新
		private void flushMatchedTable() {
			//获取Bean，这个无法直接依赖注入
			@SuppressWarnings("resource")
			ApplicationContext act =  new ClassPathXmlApplicationContext("applicationContext.xml");
		    ProductsService service = act.getBean(ProductsService.class);
		    List <Products> list=service.findAll();
		    ProductsTableModel tableModel = new ProductsTableModel();
		    tableModel.setData(list);
		    table = new JTable(tableModel);
			// 通过JTable对象创建JScrollPane，显示数据
			//scrollPane = new JScrollPane(table);
			contentPane.add(panel2, BorderLayout.CENTER);
			contentPane.add(panel, BorderLayout.SOUTH);
		}
			
			
		// 线程类，每隔两分钟刷新一次显示数据表格中的数据
		@SuppressWarnings("unused")
		private class UpdateTableThread extends Thread {
			// 重写run()方法
			public void run() {
				while (true) {
					// 移除所有的选项卡
					//contentPane.removeAll();
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
