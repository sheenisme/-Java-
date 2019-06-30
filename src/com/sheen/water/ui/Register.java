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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

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
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel add = new JLabel("家庭住址：");
		add.setBounds(90, 180, 75, 30);
		contentPane.add(add);
		
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
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Customers c=new Customers();
				c.setName(name.getText().toString());
				c.setPassword(new String (password1.getPassword()));
				c.setAddress(addredss.getText().toString());
				c.setPhone(phone.getText().toString());
				if(name.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(name.getText().toString().length() < 3) {
					JOptionPane.showMessageDialog(null, "用户名长度不能小于3!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(!(Pattern.compile("^[a-z0-9_-]{3,15}$").matcher(name.getText().toString()).matches()))
				{
					JOptionPane.showMessageDialog(null, "正则表达式验证失败，请输入正确格式的名字!","输入错误",JOptionPane.ERROR_MESSAGE);
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
				if(!(Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$").matcher(password1.getText().toString()).matches()))
				{
					JOptionPane.showMessageDialog(null, "正则表达式验证失败，请正确格式的密码!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(addredss.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "您的地址为空！请输入正确的地址!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(!(Pattern.compile("^[\\u4e00-\\u9fa5]{0,}$").matcher(addredss.getText().toString().trim()).matches()))
				{
					JOptionPane.showMessageDialog(null, "正则表达式验证失败，请正确格式的中文地址!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(phone.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "您的手机号码为空！请输入正确的联系方式!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(!(Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$").matcher(phone.getText().toString()).matches()))
				{
					JOptionPane.showMessageDialog(null, "正则表达式验证失败，请正确格式的手机号码!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}		
				
				Socket socket = null;
				//1、创建客户端Socket，指定服务器地址和端口
				try {
					socket = new Socket("127.0.0.1",28889);
				} catch (UnknownHostException e) {
					System.out.println("创建客户端Socket失败！- UnknownHostException");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("创建客户端Socket失败！- IOException ");
					e.printStackTrace();
				}
				ObjectOutputStream oos = null;
				//2、获取输出流，向服务器端发送信息
				try {
					oos =  new ObjectOutputStream(socket.getOutputStream());
				} catch (IOException e) {
					System.out.println("获取输出流出现异常！- IOException ");
					e.printStackTrace();
				}
					
				//字节输出流
				try {
					oos.writeObject(c);
					oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				//关闭输出流
				try {
					socket.shutdownOutput();
				} catch (IOException e) {
					System.out.println("socket.shutdownOutput()-出现异常！- IOException ");
					e.printStackTrace();
				}
				
				ObjectInputStream ois = null;
				//3、获取输入流，并读取服务器端的响应信息
				try {
					ois = new ObjectInputStream(socket.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					int rs=(int) ois.readObject();
					if(rs > 0) {
						JOptionPane.showMessageDialog(null, "注册成功，请您使用桶装水系统！","友情提示",JOptionPane.INFORMATION_MESSAGE);
						//转入登录页面
						dispose();
						new Login().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "注册错误","输入错误",JOptionPane.ERROR_MESSAGE);
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
				} catch (IOException e) {
					System.out.println("关闭ClientSocket的输入流失败！！");
					e.printStackTrace();
				}
								
			}
		});
		button.setBounds(135, 288, 129, 32);
		contentPane.add(button);
	}

}
