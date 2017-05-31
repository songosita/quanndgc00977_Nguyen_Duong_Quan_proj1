package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.MyList;
import model.MyNode;
import module.Module_Product;
import physical.Product;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Menu_Product extends JFrame {

	public JPanel contentPane;
	private JTable table;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtQuantity;
	private JTextField txtSale;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_Product frame = new Menu_Product();
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
	Module_Product m;
	private JScrollPane scrollPane;

	public Menu_Product() {
		m = new Module_Product();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 153, 434, 108);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Name : ");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Quantity : ");
		lblNewLabel_2.setBounds(10, 61, 67, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Sale : ");
		lblNewLabel_3.setBounds(10, 86, 46, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Price : ");
		lblNewLabel_4.setBounds(10, 111, 46, 14);
		contentPane.add(lblNewLabel_4);

		txtID = new JTextField();
		txtID.setBounds(87, 8, 124, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(87, 33, 124, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtQuantity = new JTextField();
		txtQuantity.setBounds(87, 58, 124, 20);
		contentPane.add(txtQuantity);
		txtQuantity.setColumns(10);

		txtSale = new JTextField();
		txtSale.setBounds(87, 83, 124, 20);
		contentPane.add(txtSale);
		txtSale.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setBounds(87, 108, 124, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(302, 7, 89, 23);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtID.getText().equals("")|| txtName.getText().equals("")|| txtQuantity.getText().equals("")
						|| txtSale.getText().equals("") || txtPrice.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Must type all!");
				}else if(Integer.valueOf(txtSale.getText())>Integer.valueOf(txtQuantity.getText())){					
					JOptionPane.showMessageDialog(null, "Sale must be lower than Quantity");
				}else{
					Product p = new Product();
					p.pcode = txtID.getText();
					p.pro_name = txtName.getText();
					p.quantity = Integer.valueOf(txtQuantity.getText());
					p.sale = Integer.valueOf(txtSale.getText());
					p.price = Double.valueOf(txtPrice.getText());
					p.pro_image_url = null;					
					if(m.readFile().contain(p.toString())){
						JOptionPane.showMessageDialog(null, "Pcode Existed!");
					}else{
					MyList<Product> l = m.readFile();
					l.add(p);
					m.addNew(l);
					showDataProduct();
					}
				}
			}
		});
		contentPane.add(btnAdd);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = txtID.getText();
				MyNode<Product> p = m.searchbyPcode(id);
				if(p !=null){
					DefaultTableModel dtm = new DefaultTableModel();
					dtm.addColumn("ID");
					dtm.addColumn("Name");
					dtm.addColumn("Quantity");
					dtm.addColumn("Sale");
					dtm.addColumn("Price");
					dtm.addRow(new Object[] {p.data.pcode, p.data.pro_name, p.data.quantity, p.data.sale, p.data.price});
					table.setModel(dtm);
				}else if(txtID.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Type something!");
				}
				else{
					JOptionPane.showMessageDialog(null, "Pcode doesn't exist!");
				}			
			}
		});
		btnSearch.setBounds(302, 57, 89, 23);
		contentPane.add(btnSearch);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = txtID.getText();
				if(m.deletebyPcode(id)){
					JOptionPane.showMessageDialog(null, "Succesfully!");
					showDataProduct();
				}else if(txtID.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Type somthing!");
					showDataProduct();
				}else{
					JOptionPane.showMessageDialog(null, "Ccode doesn't exist!");
					showDataProduct();
				}
			}
		});
		btnDelete.setBounds(302, 32, 89, 23);
		contentPane.add(btnDelete);
		showDataProduct();
	}

	public void showDataProduct() {
		MyList<Product> p = m.readFile();
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Quantity");
		dtm.addColumn("Sale");
		dtm.addColumn("Price");
		for (Product product : p) {
			dtm.addRow(new Object[] { product.pcode, product.pro_name, product.quantity, product.sale, product.price });
		}
		table.setModel(dtm);

	}
}
