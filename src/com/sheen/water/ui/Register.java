package com.sheen.water.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sheen.water.data.po.Customers;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register extends JFrame {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JPasswordField password1;
	private JPasswordField password2;
	private JTextField addredss;
	private JTextField phone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setTitle("桶装水系统注册页面");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 411);
		contentPane = new JPanel();
		contentPane.setToolTipText("注册");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLable = new JLabel("姓   名：");
		nameLable.setBounds(90, 44, 75, 30);
		contentPane.add(nameLable);
		
		name = new JTextField();
		name.setBounds(176, 41, 121, 30);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel passwordLable = new JLabel("密   码：");
		passwordLable.setBounds(90, 87, 75, 30);
		contentPane.add(passwordLable);
		
		JLabel label = new JLabel("确认密码：");
		label.setBounds(90, 135, 75, 30);
		contentPane.add(label);
		
		password1 = new JPasswordField();
		password1.setBounds(176, 84, 121, 30);
		contentPane.add(password1);
		
		password2 = new JPasswordField();
		password2.setBounds(176, 135, 121, 30);
		contentPane.add(password2);
		
		JLabel address = new JLabel("家庭住址：");
		address.setBounds(90, 180, 75, 30);
		contentPane.add(address);
		
		JLabel label_2 = new JLabel("联系电话：");
		label_2.setBounds(90, 223, 75, 30);
		contentPane.add(label_2);
		
		addredss = new JTextField();
		addredss.setColumns(10);
		addredss.setBounds(176, 184, 121, 30);
		contentPane.add(addredss);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(176, 229, 121, 30);
		contentPane.add(phone);
		
		JButton button = new JButton("注册");
		button.addMouseListener(new MouseAdapter() {
			/**
			 * 双击注册按钮对应的事件
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Customers po=new Customers();
				po.setName(name.getText().toString());
				po.setPassword(new String (password1.getPassword()));
				po.setAddress(address.getText().toString());
				if(name.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(name.getText().toString().length() < 3) {
					JOptionPane.showMessageDialog(null, "用户名长度不能小于3!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(password1.getPassword().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(password2.getPassword().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "确认密码不能为空!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(password1.getPassword().toString().equals(password2.getPassword().toString())) {
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致！!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(password1.getPassword().toString().length() < 6) {
					JOptionPane.showMessageDialog(null, "密码长度小于6!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(address.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "您的地址为空！请输入正确的地址!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(phone.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "您的手机号码为空！请输入正确的联系方式!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				//执行注册的操作，写入数据库
				JOptionPane.showMessageDialog(null, "注册成功，请您使用桶装水系统！","友情提示",JOptionPane.INFORMATION_MESSAGE);
				//转入登录页面
				dispose();
				new Login().setVisible(true);				
			}
		});
		button.setBounds(135, 288, 129, 32);
		contentPane.add(button);
	}

}
