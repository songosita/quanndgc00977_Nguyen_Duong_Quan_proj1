package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.MyList;
import model.MyNode;
import module.Module_Customer;
import physical.Customer;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu_Customer extends JFrame {

	public JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_Customer frame = new Menu_Customer();
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
	Module_Customer m;
	private JTextField txtID;
	private JLabel ID;
	private JTextField txtName;
	private JTextField txtPhone;
	private JButton btnSearch;
	private JButton btnDelete;

	public Menu_Customer() {
		m = new Module_Customer();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 143, 434, 118);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		txtID = new JTextField();
		txtID.setBounds(72, 21, 101, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		ID = new JLabel("ID :");
		ID.setBounds(10, 24, 52, 14);
		contentPane.add(ID);

		JLabel lblNewLabel = new JLabel("Name :\r\n");
		lblNewLabel.setBounds(10, 55, 52, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Phone :");
		lblNewLabel_1.setBounds(10, 86, 52, 14);
		contentPane.add(lblNewLabel_1);

		txtName = new JTextField();
		txtName.setBounds(72, 52, 101, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtPhone = new JTextField();
		txtPhone.setBounds(72, 83, 101, 20);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtID.getText().equals("") || txtName.getText().equals("") || txtPhone.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Must type all");
				} else {					
					Customer c = new Customer();					
					c.ccode = txtID.getText();
					c.cus_name = txtName.getText();
					c.phone = txtPhone.getText();
					if(m.readFile().contain(c.toString())){
						JOptionPane.showMessageDialog(null, "Ccode Existed!");
					}else{
						MyList<Customer> l = m.readFile();
						l.add(c);
						m.addNew(l);
						showDataCustomer();
					}
				}
			}
		});
		btnAdd.setBounds(317, 20, 89, 23);
		contentPane.add(btnAdd);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = txtID.getText();
				MyNode<Customer> c = m.searchbyCcode(id);
				if(c !=null){
					DefaultTableModel dtm = new DefaultTableModel();
					dtm.addColumn("ID");
					dtm.addColumn("Name");
					dtm.addColumn("Phone");
					dtm.addRow(new Object[] {c.data.ccode, c.data.cus_name, c.data.phone});
					table.setModel(dtm);
				}else if(txtID.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Type something!");
				}
				else{
					JOptionPane.showMessageDialog(null, "Ccode doesn't exist!");
				}				
			}
		});
		btnSearch.setBounds(317, 51, 89, 23);
		contentPane.add(btnSearch);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = txtID.getText();
				if(m.deletebyCcode(id)){
					JOptionPane.showMessageDialog(null, "Succesfully!");
					showDataCustomer();
				}else if(txtID.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Type somthing!");
					showDataCustomer();
				}
				else{
					JOptionPane.showMessageDialog(null, "Ccode doesn't exist!");
					showDataCustomer();
				}
			}
		});
		btnDelete.setBounds(317, 82, 89, 23);
		contentPane.add(btnDelete);
		showDataCustomer();
	}

	public void showDataCustomer() {
		MyList<Customer> l = m.readFile();
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Phone");
		for (Customer customer : l) {
			dtm.addRow(new Object[] { customer.ccode, customer.cus_name, customer.phone });
		}
		table.setModel(dtm);
		
	}
}
