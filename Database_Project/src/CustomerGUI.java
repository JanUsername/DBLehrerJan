import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_surname;
	private JTextField txt_loc;
	private JTextField txt_address;
	private JTextField txt_telef;
	private JTextField txt_tax;
	static QueryMethod query = new QueryMethod();
	static InsertMethod insert = new InsertMethod();
	static String[] result;
	static String[] nameTxtF = { "name", "surname", "location", "address",
			"telefonnumber", "taxnumber" };
	static int currentID = 0;
	static int maxID;
	static CustomerGUI frame;
	String[] newValues = new String[6];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CustomerGUI();
					frame.setVisible(true);
					result = query.query("tbl_Customer", 0, "customer_ID");
					System.out.println(currentID);
					maxID = query.maxID("tbl_Customer", "customer_ID");
					insertValues(result);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 571, 391);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add New Customer");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(171, 12, 226, 15);
		panel.add(lblNewLabel);

		JLabel name = new JLabel("Name:");
		name.setBounds(12, 44, 70, 15);
		panel.add(name);

		JLabel surname = new JLabel("Surname:");
		surname.setBounds(12, 72, 70, 15);
		panel.add(surname);

		JLabel location = new JLabel("Location:");
		location.setBounds(12, 99, 96, 15);
		panel.add(location);

		JLabel address = new JLabel("Address:");
		address.setBounds(12, 126, 96, 15);
		panel.add(address);

		JLabel telefonnumber = new JLabel("Telefonnumber:");
		telefonnumber.setBounds(12, 153, 131, 15);
		panel.add(telefonnumber);

		JLabel taxnumber = new JLabel("Taxnumber:");
		taxnumber.setBounds(12, 180, 131, 15);
		panel.add(taxnumber);

		JButton btn_C_first = new JButton("<<");
		btn_C_first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = query.query("tbl_Customer", 0, "customer_ID");
				insertValues(result);
				currentID = 1;
			}
		});
		btn_C_first.setBounds(12, 247, 54, 25);
		panel.add(btn_C_first);

		JButton btn_C_back = new JButton("<");
		btn_C_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentID > 0) {
					currentID--;
					System.out.println(currentID);
					result = query.query("tbl_Customer", currentID,
							"customer_ID");
					insertValues(result);
				}
			}
		});
		btn_C_back.setBounds(66, 247, 54, 25);
		panel.add(btn_C_back);

		JButton btn_C_forward = new JButton(">");
		btn_C_forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID++;
				if (currentID > 0 && currentID < maxID) {
					result = query.query("tbl_Customer", currentID,
							"customer_ID");
					insertValues(result);
				}
			}

		});
		btn_C_forward.setBounds(118, 247, 54, 25);
		panel.add(btn_C_forward);

		JButton btn_C_last = new JButton(">>");
		btn_C_last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxID = query.maxID("tbl_customer", "customer_ID");
				currentID = maxID;
				result = query.query("tbl_Customer", maxID, "customer_ID");
				insertValues(result);
			}
		});
		btn_C_last.setBounds(173, 247, 54, 25);
		panel.add(btn_C_last);

		JButton btn_C_new = new JButton("New");
		btn_C_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = maxID + 1;
				frame.txt_name.setText("");
				frame.txt_name.requestFocusInWindow();
				frame.txt_surname.setText("");
				frame.txt_loc.setText("");
				frame.txt_address.setText("");
				frame.txt_telef.setText("");
				frame.txt_tax.setText("");
			}
		});
		btn_C_new.setBounds(228, 247, 70, 25);
		panel.add(btn_C_new);

		JButton btn_C_del = new JButton("Del");
		btn_C_del.setBounds(366, 247, 70, 25);
		panel.add(btn_C_del);

		txt_name = new JTextField();
		txt_name.setBounds(171, 39, 287, 19);
		panel.add(txt_name);
		txt_name.setColumns(10);

		txt_surname = new JTextField();
		txt_surname.setColumns(10);
		txt_surname.setBounds(171, 70, 287, 19);
		panel.add(txt_surname);

		txt_loc = new JTextField();
		txt_loc.setColumns(10);
		txt_loc.setBounds(171, 97, 287, 19);
		panel.add(txt_loc);

		txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(171, 124, 287, 19);
		panel.add(txt_address);

		txt_telef = new JTextField();
		txt_telef.setColumns(10);
		txt_telef.setBounds(171, 151, 287, 19);
		panel.add(txt_telef);

		txt_tax = new JTextField();
		txt_tax.setColumns(10);
		txt_tax.setBounds(171, 178, 287, 19);
		panel.add(txt_tax);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(297, 247, 70, 25);
		panel.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newValues[0] = "" + txt_name.getText();
				newValues[1] = "" + txt_surname.getText();
				newValues[2] = "" + txt_loc.getText();
				newValues[3] = "" + txt_address.getText();
				newValues[4] = "" + txt_telef.getText();
				newValues[5] = "" + txt_tax.getText();
				insert.insert("tbl_Customer", nameTxtF, newValues);
				maxID = query.maxID("tbl_Customer", "customer_ID");
			}
		});
		btnSave.setBounds(296, 247, 68, 25);
	}

	public static void insertValues(String[] values) {
		frame.txt_name.setText(values[1]);
		frame.txt_surname.setText(values[2]);
		frame.txt_loc.setText(values[3]);
		frame.txt_address.setText(values[4]);
		frame.txt_telef.setText(values[5]);
		frame.txt_tax.setText(values[6]);
	}
}
