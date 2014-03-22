import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DatabaseMainGUI extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseMainGUI frame = new DatabaseMainGUI();
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
	public DatabaseMainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 416);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Order", null, panel, null);
		panel.setLayout(null);
		
		JLabel lbl_T1_Title = new JLabel("Order");
		lbl_T1_Title.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl_T1_Title.setBounds(12, 12, 151, 15);
		panel.add(lbl_T1_Title);
		
		JLabel Order_ID = new JLabel("Order ID");
		Order_ID.setBounds(12, 39, 70, 15);
		panel.add(Order_ID);
		
		JLabel dateoforder = new JLabel("Date of Order");
		dateoforder.setBounds(12, 66, 121, 15);
		panel.add(dateoforder);
		
		JLabel dateofdelivery = new JLabel("Date of Delivery");
		dateofdelivery.setBounds(12, 93, 121, 15);
		panel.add(dateofdelivery);
		
		JLabel quantity = new JLabel("Quantity");
		quantity.setBounds(12, 120, 70, 15);
		panel.add(quantity);
		
		JLabel device_ID = new JLabel("Device");
		device_ID.setBounds(12, 147, 70, 15);
		panel.add(device_ID);
		
		JLabel customer_ID = new JLabel("Customer");
		customer_ID.setBounds(12, 174, 70, 15);
		panel.add(customer_ID);
		
		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.setBounds(406, 323, 142, 25);
		panel.add(btnAddCustomer);
		
		JButton button = new JButton("<<");
		button.setBounds(12, 212, 54, 25);
		panel.add(button);
		
		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(66, 212, 54, 25);
		panel.add(button_1);
		
		JButton button_2 = new JButton(">");
		button_2.setBounds(118, 212, 54, 25);
		panel.add(button_2);
		
		JButton button_3 = new JButton(">>");
		button_3.setBounds(173, 212, 54, 25);
		panel.add(button_3);
		
		JButton btnNew = new JButton("New");
		btnNew.setBounds(232, 212, 70, 25);
		panel.add(btnNew);
		
		JButton btnDel = new JButton("Del");
		btnDel.setBounds(305, 212, 70, 25);
		panel.add(btnDel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
	}
}
