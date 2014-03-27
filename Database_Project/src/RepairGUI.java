import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import java.awt.Font;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RepairGUI extends JFrame {

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
	static RepairGUI frame;
	String[] newValues = new String[1];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RepairGUI();
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
	public RepairGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 535, 364);
		contentPane.add(panel);
		panel.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(56, 154, 1, 15);
		panel.add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(56, 154, 1, 15);
		panel.add(textArea_1);

		JLabel label_4 = new JLabel("Repair");
		label_4.setFont(new Font("Dialog", Font.BOLD, 16));
		label_4.setBounds(239, 12, 70, 15);
		panel.add(label_4);

		JButton btn_R_first = new JButton("<<");
		btn_R_first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = query.query(nameTBL, 1, nameID);
				insertValues(result);
				currentID = 1;
			}
		});
		btn_R_first.setBounds(12, 274, 54, 25);
		panel.add(btn_R_first);

		JButton btn_R_back = new JButton("<");
		btn_R_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentID > 1) {
					currentID--;
					result = query.query(nameTBL, currentID, nameID);
					while (result[1] == null) {
						currentID--;
						result = query.query(nameTBL, currentID, nameID);
					}
					insertValues(result);
				}
			}
		});
		btn_R_back.setBounds(66, 274, 54, 25);
		panel.add(btn_R_back);

		JButton btn_R_forward = new JButton(">");
		btn_R_forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID++;
				if (currentID > 1 && currentID <= maxID) {
					result = query.query(nameTBL, currentID, nameID);
					while (result[1] == null && currentID <= maxID) {
						currentID++;
						result = query.query(nameTBL, currentID, nameID);
					}
					insertValues(result);
				}
			}
		});
		btn_R_forward.setBounds(118, 274, 54, 25);
		panel.add(btn_R_forward);

		JButton btn_R_last = new JButton(">>");
		btn_R_last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (currentID<maxID) {
					currentID++;
				}
				result = query.query(nameTBL, currentID, nameID);
				insertValues(result);
			}
		});
		btn_R_last.setBounds(173, 274, 54, 25);
		panel.add(btn_R_last);

		JButton btn_R_new = new JButton("New");
		btn_R_new.setBounds(228, 274, 70, 25);
		panel.add(btn_R_new);

		JButton btn_R_save = new JButton("Save");
		btn_R_save.setBounds(296, 274, 68, 25);
		panel.add(btn_R_save);

		JButton btn_R_del = new JButton("Del");
		btn_R_del.setBounds(366, 274, 70, 25);
		panel.add(btn_R_del);

		JLabel lblNewLabel = new JLabel("Error Description");
		lblNewLabel.setBounds(28, 52, 126, 15);
		panel.add(lblNewLabel);

		JLabel lblErrorType = new JLabel("Error Type:");
		lblErrorType.setBounds(28, 127, 126, 15);
		panel.add(lblErrorType);

		int lengthErrortype = query.maxID(errorTypeTBL, "errortype_ID");
		String[] cbx_R_valuesErr = new String[lengthErrortype];
		
		cbx_R_valuesErr = query.queryColumn(errorTypeTBL, "errorname", lengthErrortype);
		DefaultComboBoxModel<String> modelErr = new DefaultComboBoxModel(cbx_R_valuesErr);
		
		cbx_R_errortype = new JComboBox(modelErr);
		cbx_R_errortype.setBounds(185, 122, 251, 20);
		panel.add(cbx_R_errortype);

		int lengthEmployee = query.maxID(employeeTBL, "employee_ID");
		String[] cbx_R_valuesEmp = new String[lengthErrortype];
		
		cbx_R_valuesEmp = query.queryColumn(employeeTBL, "name", lengthEmployee);
		DefaultComboBoxModel<String> modelEmp = new DefaultComboBoxModel(cbx_R_valuesEmp);
		
		cbx_R_employee = new JComboBox(modelEmp);
		cbx_R_employee.setBounds(185, 154, 251, 20);
		panel.add(cbx_R_employee);

		JLabel lblEmployee = new JLabel("Employee:");
		lblEmployee.setBounds(28, 154, 126, 15);
		panel.add(lblEmployee);

		int lengthOrder = query.maxID(orderTBL, "orderdetails_ID");
		String[] cbx_R_valuesOrd = new String[lengthErrortype];
		
		cbx_R_valuesOrd = query.queryColumn(orderTBL, "orderdetails_ID", lengthOrder);
		DefaultComboBoxModel<String> modelOrd = new DefaultComboBoxModel(cbx_R_valuesOrd);
		
		cbx_R_order = new JComboBox(modelOrd);
		cbx_R_order.setBounds(185, 181, 251, 20);
		panel.add(cbx_R_order);

		JLabel lblOrderDetails = new JLabel("Order Details:");
		lblOrderDetails.setBounds(28, 186, 126, 15);
		panel.add(lblOrderDetails);
		
		txt_R_spec = new JTextArea();
		txt_R_spec.setBounds(171, 39, 265, 68);
		panel.add(txt_R_spec);
		
		JButton btnErrorType = new JButton("Error Type");
		btnErrorType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErrorTypeGUI eGui = new ErrorTypeGUI();
				eGui.openErrorTypeGUI();
				frame.revalidate();
				frame.repaint();
			}
		});
		btnErrorType.setBounds(37, 327, 117, 25);
		panel.add(btnErrorType);
		
		JButton btnEmployee = new JButton("Employee");
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeGUI emGui = new EmployeeGUI();
				emGui.openEmployeeGUI();
			}
		});
		btnEmployee.setBounds(185, 327, 117, 25);
		panel.add(btnEmployee);
	}
	public static void insertValues(String[] values) {
		String errorTypeResult[] = query.query(errorTypeTBL, Integer.parseInt(values[2]),
				"errorType_ID");
		String employeeResult[] = query.query(employeeTBL,
				Integer.parseInt(values[3]), "employee_ID");
		String orderResult[] = query.query(orderTBL,
				Integer.parseInt(values[4]), "orderdetails_ID");
		
		frame.txt_R_spec.setText(values[1]);
		cbx_R_errortype.setSelectedIndex(Integer.parseInt(errorTypeResult[0])-1);
		cbx_R_employee.setSelectedIndex(Integer.parseInt(employeeResult[0])-1);
		cbx_R_order.setSelectedIndex(Integer.parseInt(orderResult[0])-1);
	}
}
