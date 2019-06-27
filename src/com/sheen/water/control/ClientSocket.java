package com.sheen.water.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.sheen.water.data.po.Customers;
import com.sheen.water.ui.Index;
import com.sheen.water.ui.Login;

public class ClientSocket extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Customers po;//前台传的
	private ObjectInputStream ois;
	private Socket socket = null;
	private ObjectOutputStream oos;
	
	public ClientSocket(Customers po) {
		this.po=po;
		//1、创建客户端Socket，指定服务器地址和端口
		try {
			socket = new Socket("127.0.0.1",28888);
		} catch (UnknownHostException e) {
			System.out.println("创建客户端Socket失败！- UnknownHostException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("创建客户端Socket失败！- IOException ");
			e.printStackTrace();
		}
		//2、获取输出流，向服务器端发送信息
		try {
			oos =  new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("获取输出流出现异常！- IOException ");
			e.printStackTrace();
		}
			
		//字节输出流
		try {
			oos.writeObject(po);
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
		
		//3、获取输入流，并读取服务器端的响应信息
		try {
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			boolean rs=(boolean) ois.readObject();
			if(rs==true) {
				JOptionPane.showMessageDialog(null, "登录成功，欢迎使用桶装水系统！","友情提示",JOptionPane.INFORMATION_MESSAGE);
				//启动主界面
				super.dispose();		
				new Index().setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误","输入错误",JOptionPane.ERROR_MESSAGE);
				super.dispose();		
				new Login().setVisible(true);
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
		
		close();
	}
	
	//4、关闭资源
	private void close() {
		try {
			ois.close();
			oos.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("关闭资源遇到异常！！");
			e.printStackTrace();
		}
	}
}
