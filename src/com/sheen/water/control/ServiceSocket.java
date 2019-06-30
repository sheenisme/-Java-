package com.sheen.water.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sheen.water.data.po.Customers;
import com.sheen.water.data.po.OrderItems;
import com.sheen.water.service.CustomersService;
import com.sheen.water.service.OrderItemsService;

public class ServiceSocket {
	
	public ServiceSocket() {
		new LoginThread().start();
		new RegisterThread().start();
		new OrderThread().start();
	}
	
	private class LoginThread extends Thread{
		private ObjectInputStream ois;
		private Socket socket;
		private ServerSocket serverSocket;
		private ObjectOutputStream oos;
		private CustomersService customersServices;
		
		public LoginThread() {
			//1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
			try {
				serverSocket = new ServerSocket(28888);//1024-65535的某个端口
				System.out.println("服务器已启动！");
			} catch (IOException e) {
				System.out.println("ServiceSocket 创建Socket 出现异常！！！");
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			while(this.isAlive()) {
				/**
				 * 基于TCP协议的Socket通信，实现用户登录，服务端
				*/
				//2、调用accept()方法开始监听，等待客户端的连接
				try {
					socket = serverSocket.accept();
				} catch (IOException e) {
					System.out.println("serverSocket.accept()出现异常！！！");
					e.printStackTrace();
				}
				
				Customers vo=null;
				
				//3、获取输入流，并读取客户端信息
				try {
					ois = new ObjectInputStream(socket.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					vo=(Customers) ois.readObject();
				} catch (ClassNotFoundException e1) {
					System.out.println("ServiceSocket的获取输入流出现-ClassNotFoundException异常 ！");
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//关闭输入流
				try {
					socket.shutdownInput();
				} catch (IOException e) {
					System.out.println("关闭ServiceSocket的输入流失败！！");
					e.printStackTrace();
				}
				
				//4、获取输出流，响应客户端的请求
				try {
					oos = new ObjectOutputStream(socket.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
								
				@SuppressWarnings("resource")
				ApplicationContext act =  new ClassPathXmlApplicationContext("applicationContext.xml");
				customersServices = act.getBean(CustomersService.class);
				boolean rs=customersServices.selectOne(vo);
				try {
					oos.writeObject(rs);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}					
				
				//关闭输出流
				try {
					socket.shutdownOutput();
				} catch (IOException e) {
					System.out.println("关闭ServiceSocket的输出流失败-出现异常！- IOException ");
					e.printStackTrace();
				} 
				
				//close();
				
			}
		}	
		@SuppressWarnings("unused")
		private void close(){
			try {
				//5、关闭资源
				oos.close();
				ois.close();
				socket.close();
				serverSocket.close();	
				System.out.println("服务器资源已全部关闭！");
			}catch(IOException e) {
				System.out.println("关闭服务器Socket资源出现异常！！！");
				e.printStackTrace();
			}
		}
	}
	
	private class RegisterThread extends Thread{
		private ObjectInputStream ois;
		private Socket socket;
		private ServerSocket serverSocket;
		private ObjectOutputStream oos;
		private CustomersService customersServices;
		
		public RegisterThread() {
			//1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
			try {
				serverSocket = new ServerSocket(28889);//1024-65535的某个端口
				System.out.println("服务器2已启动！");
			} catch (IOException e) {
				System.out.println("ServiceSocket 创建 RegisterSocket 出现异常！！！");
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			while(this.isAlive()) {
				/**
				 * 基于TCP协议的Socket通信，实现用户登录，服务端
				*/
				//2、调用accept()方法开始监听，等待客户端的连接
				try {
					socket = serverSocket.accept();
				} catch (IOException e) {
					System.out.println("Register  serverSocket.accept()出现异常！！！");
					e.printStackTrace();
				}
				
				Customers vo=null;
				
				//3、获取输入流，并读取客户端信息
				try {
					ois = new ObjectInputStream(socket.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					vo=(Customers) ois.readObject();
				} catch (ClassNotFoundException e1) {
					System.out.println("注册  ServiceSocket的获取输入流出现-ClassNotFoundException异常 ！");
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//关闭输入流
				try {
					socket.shutdownInput();
				} catch (IOException e) {
					System.out.println("关闭ServiceSocket的输入流失败！！");
					e.printStackTrace();
				}
				
				//4、获取输出流，响应客户端的请求
				try {
					oos = new ObjectOutputStream(socket.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
			
				@SuppressWarnings("resource")
				ApplicationContext act =  new ClassPathXmlApplicationContext("applicationContext.xml");
				customersServices = act.getBean(CustomersService.class);
				int rs=customersServices.insertCustomer(vo);
				try {
					oos.writeObject(rs);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}					
				
				//关闭输出流
				try {
					socket.shutdownOutput();
				} catch (IOException e) {
					System.out.println("关闭ServiceSocket的输出流失败-出现异常！- IOException ");
					e.printStackTrace();
				} 
				
				//close();	
			}
		}	
		
		@SuppressWarnings("unused")
		private void close(){
			try {
				//5、关闭资源
				oos.close();
				ois.close();
				socket.close();
				serverSocket.close();	
				System.out.println("服务器资源已全部关闭！");
			}catch(IOException e) {
				System.out.println("关闭服务器Socket资源出现异常！！！");
				e.printStackTrace();
			}
		}
	}
	
	
	private class OrderThread extends Thread{
		private ObjectInputStream ois;
		private Socket socket;
		private ServerSocket serverSocket;
		private ObjectOutputStream oos;
		private OrderItemsService services;
		
		public OrderThread() {
			//1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
			try {
				serverSocket = new ServerSocket(28887);//1024-65535的某个端口
				System.out.println("服务器3已启动！");
			} catch (IOException e) {
				System.out.println("ServiceSocket 创建 下单Socket 出现异常！！！");
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			while(this.isAlive()) {
				//2、调用accept()方法开始监听，等待客户端的连接
				try {
					socket = serverSocket.accept();
				} catch (IOException e) {
					System.out.println("Register  serverSocket.accept()出现异常！！！");
					e.printStackTrace();
				}
				
				OrderItems vo=null;
				
				//3、获取输入流，并读取客户端信息
				try {
					ois = new ObjectInputStream(socket.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					vo=(OrderItems) ois.readObject();
				} catch (ClassNotFoundException e1) {
					System.out.println("注册  ServiceSocket的获取输入流出现-ClassNotFoundException异常 ！");
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//关闭输入流
				try {
					socket.shutdownInput();
				} catch (IOException e) {
					System.out.println("关闭ServiceSocket的输入流失败！！");
					e.printStackTrace();
				}
				
				//4、获取输出流，响应客户端的请求
				try {
					oos = new ObjectOutputStream(socket.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
								
				@SuppressWarnings("resource")
				ApplicationContext act =  new ClassPathXmlApplicationContext("applicationContext.xml");
				services = act.getBean(OrderItemsService.class);
				boolean rs=services.create(vo);
				try {
					oos.writeObject(rs);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}					
				
				//关闭输出流
				try {
					socket.shutdownOutput();
				} catch (IOException e) {
					System.out.println("关闭ServiceSocket的输出流失败-出现异常！- IOException ");
					e.printStackTrace();
				} 
				
				//close();	
			}
		}	
		
		@SuppressWarnings("unused")
		private void close(){
			try {
				//5、关闭资源
				oos.close();
				ois.close();
				socket.close();
				serverSocket.close();	
				System.out.println("服务器资源已全部关闭！");
			}catch(IOException e) {
				System.out.println("关闭服务器Socket资源出现异常！！！");
				e.printStackTrace();
			}
		}
	}
	
	
	// 主程序
	public static void main(String[] args) {
		new ServiceSocket();
	}
}
