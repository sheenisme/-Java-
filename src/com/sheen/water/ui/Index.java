package com.sheen.water.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private JTable table;
	private JTextField textField;
	private JScrollPane scrollPane;
	private DefaultTableModel model;

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
		setBounds(100, 100, 858, 655);
		
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
		
		JMenu mnNewMenu_1 = new JMenu("     查看桶装水信息      ");
		mnNewMenu_1.setForeground(Color.BLUE);
		mnNewMenu_1.setFont(new Font("宋体", Font.PLAIN, 17));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("查看信息      ");
		mntmNewMenuItem_2.setFont(new Font("宋体", Font.PLAIN, 17));
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			//鼠标单击的事件
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu = new JMenu("     我要下单   ");
		mnNewMenu.setFont(new Font("宋体", Font.PLAIN, 17));
		mnNewMenu.setHorizontalAlignment(SwingConstants.RIGHT);
		mnNewMenu.setForeground(Color.BLUE);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("我要下单      ");
		mntmNewMenuItem_1.setFont(new Font("宋体", Font.PLAIN, 17));
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			//鼠标单击的事件
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_2 = new JMenu("   我的信息    ");
		mnNewMenu_2.setFont(new Font("宋体", Font.PLAIN, 17));
		mnNewMenu_2.setForeground(Color.BLUE);
		mnNewMenu_2.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("显示我的信息    ");
		mntmNewMenuItem_3.setFont(new Font("宋体", Font.PLAIN, 17));
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_3 = new JMenu("     购物车        ");
		mnNewMenu_3.setForeground(Color.BLUE);
		mnNewMenu_3.setFont(new Font("宋体", Font.PLAIN, 17));
		mnNewMenu_3.setEnabled(false);
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
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// 新建一个默认数据模型
		model = new DefaultTableModel(); 
		table = new JTable(model);
		table.setBounds(17, 31, 805, 304);
		//支持多选
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// 用列表创建可滚动的Panel，把这个Panel添加到窗口中　　
		scrollPane = new JScrollPane(table); 
		contentPane.add(scrollPane);
		
		//Vector row = new Vector(); // 数据行向量，使用它的add()添加元素，比如整数、String、Object等，有几行就new几个行向量　　
		//Vector data = new Vector(); // 数据行向量集，因为列表不止一行，往里面添加数据行向量，添加方法
		//Vector names = new Vector();// 列名向量，使用它的add()方法添加列名　
		//model.setDataVector(data, names); // 设置模型中的元素，它会自动显示在列表中
		
		JPanel panel = new JPanel();
		panel.setBounds(158, 383, 501, 157);
		contentPane.add(panel);
		panel.setLayout(null);
		
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
		
		JButton button_1 = new JButton("结算！");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setBounds(289, 75, 113, 51);
		panel.add(button_1);
	}
}
