package com.sheen.water.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.sheen.water.control.ClientSocket;
import com.sheen.water.data.po.Customers;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;


public class Login extends JFrame {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private JTextField name;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("桶装水系统登录页面");
		getContentPane().setBackground(SystemColor.controlHighlight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 427);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("用户名：");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("华文楷体", Font.BOLD, 18));
		label.setBackground(Color.GRAY);
		label.setBounds(126, 96, 76, 42);
		getContentPane().add(label);
		
		name = new JTextField();
		name.setBackground(Color.WHITE);
		name.setBounds(201, 102, 128, 32);
		getContentPane().add(name);
		name.setColumns(10);
		
		JLabel label_1 = new JLabel("密  码：");
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("华文楷体", Font.BOLD, 18));
		label_1.setBackground(Color.GRAY);
		label_1.setBounds(126, 145, 76, 42);
		getContentPane().add(label_1);
		
		password = new JPasswordField();
		password.setBackground(Color.WHITE);
		password.setBounds(201, 151, 128, 32);
		getContentPane().add(password);
		
		JButton button = new JButton("登录");
		/**
		 * 双击登录按钮
		 */
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Customers po=new Customers();
				po.setName(name.getText());
				po.setPassword(new String (password.getPassword()));
				@SuppressWarnings("unused")
				ClientSocket client = new ClientSocket(po);
				if(po.getName().equals("")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(po.getName().length() < 3) {
					JOptionPane.showMessageDialog(null, "用户名长度不能小于3!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(po.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				dispose();
				/*
				if( ) {
					JOptionPane.showMessageDialog(null, "登录成功，欢迎使用桶装水系统！","友情提示",JOptionPane.INFORMATION_MESSAGE);
					//启动主界面
					dispose();					
				}else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}	*/
			}
		}
		);
		button.setFont(new Font("仿宋", Font.BOLD, 18));
		button.setForeground(new Color(0, 0, 255));
		button.setBackground(new Color(240, 240, 240));
		button.setBounds(137, 216, 94, 32);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("注册");
		/**
		 * 双击注册按钮
		 */
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Register().setVisible(true);
			}
		});
		button_1.setForeground(Color.BLUE);
		button_1.setFont(new Font("仿宋", Font.BOLD, 18));
		button_1.setBackground(SystemColor.menu);
		button_1.setBounds(262, 216, 94, 32);
		getContentPane().add(button_1);
	}
}
