package com.sheen.water.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sheen.water.data.po.Products;
import com.sheen.water.service.ProductsService;

public class CreateProducts extends JFrame {
	private static final long serialVersionUID = 1L;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProducts frame = new CreateProducts();
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
	public CreateProducts() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("桶装水新增页面");
		getContentPane().setBackground(SystemColor.controlHighlight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 383, 320);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("容量：");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("华文楷体", Font.BOLD, 18));
		label.setBackground(Color.GRAY);
		label.setBounds(72, 53, 76, 42);
		getContentPane().add(label);
		
		JTextField kind = new JTextField();
		kind.setBackground(Color.WHITE);
		kind.setBounds(143, 54, 128, 32);
		kind.setColumns(10);
		getContentPane().add(kind);
		
		
		JLabel label_1 = new JLabel("价格：");
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("华文楷体", Font.BOLD, 18));
		label_1.setBackground(Color.GRAY);
		label_1.setBounds(71, 103, 76, 42);
		getContentPane().add(label_1);
		
		JTextField price = new JTextField();
		price.setBackground(Color.WHITE);
		price.setBounds(144, 107, 128, 32);
		getContentPane().add(price);
		
		JButton button = new JButton("新增桶装水！");
		/**
		 * 双击新增按钮
		 */
		button.addMouseListener(new MouseAdapter() {
			//@Autowired
			private ProductsService service;
			//@Autowired
			private Products po;
			
			@Override
			public void mouseClicked(MouseEvent e) {		
				if(kind.getText().trim() == null || kind.getText().trim() == "") {
					JOptionPane.showMessageDialog(null, "容量不能为空!","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if(price.getText().trim() == null || kind.getText().trim() == "") {
					JOptionPane.showMessageDialog(null, "价格不能为空","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				po=new Products();
				po.setKind(Double.valueOf(kind.getText().trim()));
				po.setPrice(Double.valueOf(price.getText().trim()));
				
				//获取Bean，这个无法直接依赖注入
				@SuppressWarnings("resource")
				ApplicationContext act =  new ClassPathXmlApplicationContext("applicationContext.xml");
			    service = act.getBean(ProductsService.class);
			    
				boolean flag=service.create(po);
				if( flag == true) {
					JOptionPane.showMessageDialog(null, "新增成功，请继续使用桶装水系统！","友情提示",JOptionPane.INFORMATION_MESSAGE);
					//启动主界面
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "新增出现异常，请重试！","输入错误",JOptionPane.ERROR_MESSAGE);
					return ;
				}
			}
		});
		button.setFont(new Font("仿宋", Font.BOLD, 18));
		button.setForeground(new Color(0, 0, 255));
		button.setBackground(new Color(240, 240, 240));
		button.setBounds(100, 166, 167, 37);
		getContentPane().add(button);
	}
}
