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
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;


public class DatabaseMainGUI extends JFrame {

	private JPanel contentPane;
	private JTextArea txt_R_spec;
	private static JComboBox cbx_R_errortype;
	private static JComboBox cbx_R_employee;
	private static JComboBox cbx_R_order;
	private static String errorTypeTBL = "tbl_errortype";
	private static String employeeTBL = "tbl_employee";
	private static String orderTBL = "tbl_orderdetails";

	static QueryMethod query = new QueryMethod();
	static InsertMethod insert = new InsertMethod();
	static DeleteMethod delete = new DeleteMethod();
	static UpdateMethod update = new UpdateMethod();

	static private String errorTypeFK = "errortype_ID";
	static private String employeeFK = "employee_ID";
	static private String orderFK = "orderdetails_ID";
	static private String nameTBL = "tbl_repair";
	static private String nameID = "repair_ID";
	static String[] result;
	static String[] nameTxtF = { "errordescription" , "errortype_ID" , "employee_ID" , "orderdetails_ID" };
	static int currentID = 1;
	static int maxID;
	static JPanel frameR;
	String[] newValues = new String[1];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseMainGUI frame = new DatabaseMainGUI();
					frame.setVisible(true);
					result = query.query(nameTBL, 1, nameID);
					maxID = query.getLastID(nameTBL, nameID);
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
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Repair", null, panel_1, null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(56, 154, 1, 15);
		panel_1.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(56, 154, 1, 15);
		panel_1.add(textArea_1);
		
		JLabel label = new JLabel("Repair");
		label.setFont(new Font("Dialog", Font.BOLD, 16));
		label.setBounds(239, 12, 70, 15);
		panel_1.add(label);
		
		JButton button_4 = new JButton("<<");
		button_4.setBounds(12, 274, 54, 25);
		panel_1.add(button_4);
		
		JButton button_5 = new JButton("<");
		button_5.setBounds(66, 274, 54, 25);
		panel_1.add(button_5);
		
		JButton button_6 = new JButton(">");
		button_6.setBounds(118, 274, 54, 25);
		panel_1.add(button_6);
		
		JButton button_7 = new JButton(">>");
		button_7.setBounds(173, 274, 54, 25);
		panel_1.add(button_7);
		
		JButton button_8 = new JButton("New");
		button_8.setBounds(228, 274, 70, 25);
		panel_1.add(button_8);
		
		JButton button_9 = new JButton("Save");
		button_9.setBounds(296, 274, 68, 25);
		panel_1.add(button_9);
		
		JButton button_10 = new JButton("Del");
		button_10.setBounds(366, 274, 70, 25);
		panel_1.add(button_10);
		
		JLabel label_1 = new JLabel("Error Description");
		label_1.setBounds(28, 52, 126, 15);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Error Type:");
		label_2.setBounds(28, 127, 126, 15);
		panel_1.add(label_2);
		
		JComboBox comboBox = new JComboBox((ComboBoxModel) null);
		comboBox.setBounds(185, 122, 251, 20);
		panel_1.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox((ComboBoxModel) null);
		comboBox_1.setBounds(185, 154, 251, 20);
		panel_1.add(comboBox_1);
		
		JLabel label_3 = new JLabel("Employee:");
		label_3.setBounds(28, 154, 126, 15);
		panel_1.add(label_3);
		
		JComboBox comboBox_2 = new JComboBox((ComboBoxModel) null);
		comboBox_2.setBounds(185, 181, 251, 20);
		panel_1.add(comboBox_2);
		
		JLabel label_4 = new JLabel("Order Details:");
		label_4.setBounds(28, 186, 126, 15);
		panel_1.add(label_4);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(171, 39, 265, 68);
		panel_1.add(textArea_2);
		
		JButton button_11 = new JButton("Error Type");
		button_11.setBounds(37, 327, 117, 25);
		panel_1.add(button_11);
		
		JButton button_12 = new JButton("Employee");
		button_12.setBounds(185, 327, 117, 25);
		panel_1.add(button_12);
	}
	public static void insertValues(String[] values) {
		String errorTypeResult[] = query.query(errorTypeTBL, Integer.parseInt(values[2]),
				"errorType_ID");
		String employeeResult[] = query.query(employeeTBL,
				Integer.parseInt(values[3]), "employee_ID");
		String orderResult[] = query.query(orderTBL,
				Integer.parseInt(values[4]), "orderdetails_ID");
		
		frameR.txt_R_spec.setText(values[1]);
		cbx_R_errortype.setSelectedIndex(Integer.parseInt(errorTypeResult[0])-1);
		cbx_R_employee.setSelectedIndex(Integer.parseInt(employeeResult[0])-1);
		cbx_R_order.setSelectedIndex(Integer.parseInt(orderResult[0])-1);
	}
}
