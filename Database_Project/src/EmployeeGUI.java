import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EmployeeGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_E_name;
	private JTextField txt_E_surname;
	private JTextField txt_E_spec;

	static QueryMethod query = new QueryMethod();
	static InsertMethod insert = new InsertMethod();
	static DeleteMethod delete = new DeleteMethod();
	static UpdateMethod update = new UpdateMethod();

	static private String nameTBL = "tbl_employee";
	static private String nameID = "employee_ID";
	static String[] result;
	static String[] nameTxtF = { "name", "surname", "Specialization" };
	static int currentID = 1;
	static int maxID;
	static EmployeeGUI frameEGui;
	String[] newValues = new String[3];
	
	/**
	 * Launch the application.
	 */
	public void openEmployeeGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameEGui = new EmployeeGUI();
					frameEGui.setVisible(true);
					result = query.query(nameTBL, 1, nameID);
					maxID = query.maxID(nameTBL, nameID);
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
	public EmployeeGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 448, 271);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEmployee = new JLabel("Employee");
		lblEmployee.setBounds(186, 12, 70, 15);
		panel.add(lblEmployee);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 52, 70, 15);
		panel.add(lblName);
		
		txt_E_name = new JTextField();
		txt_E_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.update(nameTBL, "name", txt_E_name.getText(), nameID, currentID);
			}
		});
		txt_E_name.setColumns(10);
		txt_E_name.setBounds(159, 50, 168, 19);
		panel.add(txt_E_name);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(12, 88, 70, 15);
		panel.add(lblSurname);
		
		txt_E_surname = new JTextField();
		txt_E_surname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.update(nameTBL, "surname", txt_E_surname.getText(), nameID, currentID);
			}
		});
		txt_E_surname.setColumns(10);
		txt_E_surname.setBounds(159, 86, 168, 19);
		panel.add(txt_E_surname);
		
		JLabel lblSpecializion = new JLabel("Specialization:");
		lblSpecializion.setBounds(12, 123, 108, 15);
		panel.add(lblSpecializion);
		
		txt_E_spec = new JTextField();
		txt_E_spec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.update(nameTBL, "specialization", txt_E_spec.getText(), nameID, currentID);
			}
		});
		txt_E_spec.setColumns(10);
		txt_E_spec.setBounds(159, 121, 168, 19);
		panel.add(txt_E_spec);
		
		JButton btn_E_first = new JButton("<<");
		btn_E_first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = query.query(nameTBL, 1, nameID);
				insertValues(result);
				currentID = 1;
			}
		});
		btn_E_first.setBounds(12, 185, 54, 25);
		panel.add(btn_E_first);
		
		JButton btn_E_back = new JButton("<");
		btn_E_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentID > 1) {
					currentID--;
					result = query.query(nameTBL, currentID, nameID);
					while (result[1]==null){
						currentID--;
						result = query.query(nameTBL, currentID, nameID);
					}
					insertValues(result);
				}
			}
		});
		btn_E_back.setBounds(66, 185, 54, 25);
		panel.add(btn_E_back);
		
		JButton btn_E_forward = new JButton(">");
		btn_E_forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID++;
				if (currentID > 0 && currentID <= maxID) {
					result = query.query(nameTBL, currentID, nameID);
					while (result[1] == null) {
						currentID++;
						result = query.query(nameTBL, currentID, nameID);
					}
					insertValues(result);
				}
			}
		});
		btn_E_forward.setBounds(118, 185, 54, 25);
		panel.add(btn_E_forward);
		
		JButton btn_E_last = new JButton(">>");
		btn_E_last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxID = query.maxID(nameTBL, nameID);
				currentID = maxID;
				result = query.query(nameTBL, maxID, nameID);
				insertValues(result);
			}
		});
		btn_E_last.setBounds(173, 185, 54, 25);
		panel.add(btn_E_last);
		
		JButton btn_E_new = new JButton("New");
		btn_E_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = maxID + 1;
				frameEGui.txt_E_name.setText("");
				frameEGui.txt_E_name.requestFocusInWindow();
				frameEGui.txt_E_surname.setText("");
				frameEGui.txt_E_spec.setText("");
			}
		});
		btn_E_new.setBounds(228, 185, 70, 25);
		panel.add(btn_E_new);
		
		JButton btn_E_save = new JButton("Save");
		btn_E_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newValues[0] = "" + txt_E_name.getText();
				newValues[1] = "" + txt_E_surname.getText();
				newValues[2] = "" + txt_E_spec.getText();
				insert.insert(nameTBL, nameTxtF, newValues);
				maxID = query.maxID(nameTBL, nameID);
			}
		});
		btn_E_save.setBounds(296, 185, 68, 25);
		panel.add(btn_E_save);
		
		JButton btn_E_del = new JButton("Del");
		btn_E_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete.delete(nameTBL, nameID, currentID);
			}
		});
		btn_E_del.setBounds(366, 185, 70, 25);
		panel.add(btn_E_del);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	public static void insertValues(String[] values) {
		frameEGui.txt_E_name.setText(values[1]);
		frameEGui.txt_E_surname.setText(values[2]);
		frameEGui.txt_E_spec.setText(values[3]);
	}
}
