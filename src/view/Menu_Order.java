package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.MyList;
import module.Module_Order;
import physical.Order;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Menu_Order extends JFrame {

	public JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {			
			public void run() {
				try {
					Menu_Order frame = new Menu_Order();
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
	Module_Order o;
	public Menu_Order() {
		o = new Module_Order();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 108, 429, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		showDataOrder();
	}
	
	public void showDataOrder() {
		MyList<Order> l = o.readFile();
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("PCode");
		dtm.addColumn("CCode");
		dtm.addColumn("Quantity");
		for (Order order : l) {
			dtm.addRow(new Object[] { order.pcode, order.ccode, order.quantity });
		}
		table.setModel(dtm);

	}

}
