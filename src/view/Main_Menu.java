package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main_Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Menu frame = new Main_Menu();
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
	public static Menu_Customer c;
	public static Menu_Order o;
	public static Menu_Product p;
	public static Main_Menu m;
	public static void changePage(JFrame parent, JPanel children){
		parent.setContentPane(children);
		parent.validate();
		parent.repaint();
//		parent.pack();
	}
	
	public Main_Menu() {
		m = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnProduct = new JButton("Product");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(p==null){
					p = new Menu_Product();		
					changePage(m, p.contentPane);
				}else{
					changePage(m, p.contentPane);
				}
			}
		});
		btnProduct.setBounds(46, 46, 89, 23);
		contentPane.add(btnProduct);
		
		JButton btnCustomer = new JButton("Customer");
		
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c==null){
					c = new Menu_Customer();		
					changePage(m, c.contentPane);
				}else{
					changePage(m, c.contentPane);
				}
			}
		});
		btnCustomer.setBounds(169, 46, 101, 23);
		contentPane.add(btnCustomer);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(o==null){
					o = new Menu_Order();		
					changePage(m, o.contentPane);
				}else{
					changePage(m, o.contentPane);
				}
			}
		});
		btnOrder.setBounds(303, 46, 89, 23);
		contentPane.add(btnOrder);
	}
}
