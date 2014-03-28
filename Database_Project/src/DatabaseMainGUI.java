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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JTextField;


public class DatabaseMainGUI extends JFrame {

	private JPanel contentPaneOrder;
	static private JPanel contentPanelRepair;
	static private JPanel contentPanelDevice;
	private static JComboBox cbx_O_device;
	private static JComboBox cbx_O_customer;
	private JTextField txt_O_dateOrder;
	private JTextField txt_O_dateDeliver;
	private JTextField txt_O_quantity;
	
	static QueryMethod query = new QueryMethod();
	static InsertMethod insert = new InsertMethod();
	static DeleteMethod delete = new DeleteMethod();
	static UpdateMethod update = new UpdateMethod();

	static private String customerFK = "customer_ID";
	static private String CustomerTBL= "tbl_customer";
	static private String deviceFK = "employee_ID";
	static private String orderDFK = "orderdetails_ID";
	static private String orderDTBL = "tbl_orderdetails";
	static private String nameTBL1 = "tbl_orderDetails";
	static private String nameTBL2 = "tbl_order";
	static private String nameID1 = "orderdetails_ID";
	static private String nameID2 = "order_ID";
	static String[] result;
	static String[] nameTxtF = { "dateoforder" , "dateofdelivery" , "customer_ID" , "orderdetails_ID" };
	static int currentID = 1;
	static int maxID;
	static DatabaseMainGUI frame;
	String[] newValues = new String[1];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new DatabaseMainGUI();
					frame.setVisible(true);
					result = query.query(nameTBL2, 1, nameID2);
					maxID = query.getLastID(nameTBL2, nameID2);
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
		lbl_T1_Title.setBounds(232, 12, 151, 15);
		panel.add(lbl_T1_Title);
		
		JLabel dateoforder = new JLabel("Date of Order");
		dateoforder.setBounds(12, 39, 121, 15);
		panel.add(dateoforder);
		
		JLabel dateofdelivery = new JLabel("Date of Delivery");
		dateofdelivery.setBounds(12, 66, 121, 15);
		panel.add(dateofdelivery);
		
		JLabel quantity = new JLabel("Quantity");
		quantity.setBounds(12, 93, 70, 15);
		panel.add(quantity);
		
		JLabel device_ID = new JLabel("Device");
		device_ID.setBounds(12, 120, 70, 15);
		panel.add(device_ID);
		
		JLabel customer_ID = new JLabel("Customer");
		customer_ID.setBounds(12, 147, 70, 15);
		panel.add(customer_ID);
		
		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerGUI custGui = new CustomerGUI();
				custGui.startCostumerGUI();
			}
		});
		btnAddCustomer.setBounds(406, 323, 142, 25);
		panel.add(btnAddCustomer);
		
		JButton btn_O_first = new JButton("<<");
		btn_O_first.setBounds(12, 224, 54, 25);
		panel.add(btn_O_first);
		
		JButton btn_O_back = new JButton("<");
		btn_O_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_O_back.setBounds(66, 224, 54, 25);
		panel.add(btn_O_back);
		
		JButton btn_O_forward = new JButton(">");
		btn_O_forward.setBounds(118, 224, 54, 25);
		panel.add(btn_O_forward);
		
		JButton btn_O_last = new JButton(">>");
		btn_O_last.setBounds(173, 224, 54, 25);
		panel.add(btn_O_last);
		
		JButton btn_O_new = new JButton("New");
		btn_O_new.setBounds(232, 224, 70, 25);
		panel.add(btn_O_new);
		
		JButton btn_O_del = new JButton("Del");
		btn_O_del.setBounds(305, 224, 70, 25);
		panel.add(btn_O_del);
		
		JButton btnRepair = new JButton("Repair");
		btnRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RepairGUI repGui = new RepairGUI();
				repGui.startRepairGUI();
			}
		});
		btnRepair.setBounds(252, 323, 142, 25);
		panel.add(btnRepair);
		
		JButton btnDevice = new JButton("device");
		btnDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeviceGUI devGui = new DeviceGUI();
				devGui.startDeviceGUI();
			}
		});
		btnDevice.setBounds(98, 323, 142, 25);
		panel.add(btnDevice);
		
		txt_O_dateOrder = new JTextField();
		txt_O_dateOrder.setBounds(172, 37, 203, 19);
		panel.add(txt_O_dateOrder);
		txt_O_dateOrder.setColumns(10);
		
		txt_O_dateDeliver = new JTextField();
		txt_O_dateDeliver.setColumns(10);
		txt_O_dateDeliver.setBounds(172, 64, 203, 19);
		panel.add(txt_O_dateDeliver);
		
		txt_O_quantity = new JTextField();
		txt_O_quantity.setBounds(173, 91, 202, 19);
		panel.add(txt_O_quantity);
		txt_O_quantity.setColumns(10);
		
		
		int lengthOrder = query.maxID(orderDTBL, "orderdetails_ID");
		String[] cbx_R_valuesOrd = new String[lengthOrder];
		
		cbx_R_valuesOrd = query.queryColumn(orderDTBL, "orderdetails_ID", lengthOrder);
		DefaultComboBoxModel<String> modelOrd = new DefaultComboBoxModel(cbx_R_valuesOrd);
				
		cbx_O_device = new JComboBox(modelOrd);
		cbx_O_device.setBounds(173, 115, 202, 24);
		panel.add(cbx_O_device);
		
		int lengthCustomer = query.maxID(CustomerTBL, "customer_ID");
		String[] cbx_R_valuesCus = new String[lengthCustomer];
		
		cbx_R_valuesCus = query.queryColumn(CustomerTBL, "surname", lengthCustomer);
		DefaultComboBoxModel<String> modelCus = new DefaultComboBoxModel(cbx_R_valuesCus);
				
		cbx_O_customer = new JComboBox(modelCus);
		cbx_O_customer.setBounds(173, 142, 203, 24);
		panel.add(cbx_O_customer);
	}
	public static void insertValues(String[] values) {
		String customerResult[] = query.query(CustomerTBL, Integer.parseInt(values[3]),
				"customer_ID");
//		String deviceResult[] = query.query(deviceFK,Integer.parseInt(values[3]), "employee_ID");
		String orderDResult[] = query.query(orderDTBL,
				Integer.parseInt(values[4]), "orderdetails_ID");
		
		frame.txt_O_dateOrder.setText(values[1]);
		frame.txt_O_dateDeliver.setText(values[2]);
		cbx_O_customer.setSelectedIndex(Integer.parseInt(customerResult[0])-1);
		cbx_O_device.setSelectedIndex(Integer.parseInt(orderDResult[0])-1);
//		cbx_R_order.setSelectedIndex(Integer.parseInt(orderResult[0])-1);
	}
}


//static private String customerFK = "errortype_ID";
//static private String deviceFK = "employee_ID";
//static private String orderDFK = "orderdetails_ID";