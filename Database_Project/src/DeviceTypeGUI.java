import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DeviceTypeGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_DT_name;
	private JTextField txt_DT_desc;

	static QueryMethod query = new QueryMethod();
	static InsertMethod insert = new InsertMethod();
	static DeleteMethod delete = new DeleteMethod();
	static UpdateMethod update = new UpdateMethod();

	static private String nameTBL = "tbl_devicetype";
	static private String nameID = "devicetype_ID";
	static String[] result;
	static String[] nameTxtF = { "name", "description" };
	static int currentID = 1;
	static int maxID;
	static DeviceTypeGUI frame;
	String[] newValues = new String[3];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new DeviceTypeGUI();
					frame.setVisible(true);
					frame = new DeviceTypeGUI();
					frame.setVisible(true);
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
	public DeviceTypeGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 545, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAddNewDevice = new JLabel("Add new Device Typ");
		lblAddNewDevice.setBounds(149, 12, 140, 15);
		panel.add(lblAddNewDevice);
		
		JLabel name = new JLabel("Name:");
		name.setBounds(12, 42, 70, 15);
		panel.add(name);
		
		txt_DT_name = new JTextField();
		txt_DT_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.update(nameTBL, "name", txt_DT_name.getText(), nameID, currentID);
			}
		});
		txt_DT_name.setBounds(141, 39, 168, 18);
		panel.add(txt_DT_name);
		txt_DT_name.setColumns(10);
		
		JLabel description = new JLabel("Description:");
		description.setBounds(12, 72, 111, 15);
		panel.add(description);
		
		txt_DT_desc = new JTextField();
		txt_DT_desc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.update(nameTBL, "description", txt_DT_desc.getText(), nameID, currentID);
			}
		});
		txt_DT_desc.setColumns(10);
		txt_DT_desc.setBounds(141, 69, 168, 41);
		panel.add(txt_DT_desc);
		
		JButton btn_DT_first = new JButton("<<");
		btn_DT_first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = query.query(nameTBL, 1, nameID);
				insertValues(result);
				currentID = 1;
			}
		});
		btn_DT_first.setBounds(30, 132, 54, 25);
		panel.add(btn_DT_first);
		
		JButton btn_DT_back = new JButton("<");
		btn_DT_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentID > 0) {
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
		btn_DT_back.setBounds(84, 132, 54, 25);
		panel.add(btn_DT_back);
		
		JButton btn_DT_forward = new JButton(">");
		btn_DT_forward.addActionListener(new ActionListener() {
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
		btn_DT_forward.setBounds(136, 132, 54, 25);
		panel.add(btn_DT_forward);
		
		JButton btn_DT_last = new JButton(">>");
		btn_DT_last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxID = query.maxID(nameTBL, nameID);
				currentID = maxID;
				result = query.query(nameTBL, maxID, nameID);
				insertValues(result);
			}
		});
		btn_DT_last.setBounds(191, 132, 54, 25);
		panel.add(btn_DT_last);
		
		JButton btn_DT_new = new JButton("New");
		btn_DT_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = maxID + 1;
				frame.txt_DT_name.setText("");
				frame.txt_DT_name.requestFocusInWindow();
				frame.txt_DT_desc.setText("");
			}
		});
		btn_DT_new.setBounds(246, 132, 70, 25);
		panel.add(btn_DT_new);
		
		JButton btn_DT_save = new JButton("Save");
		btn_DT_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newValues[0] = "" + txt_DT_name.getText();
				newValues[1] = "" + txt_DT_desc.getText();
				insert.insert(nameTBL, nameTxtF, newValues);
				maxID = query.maxID(nameTBL, nameID);
			}
		});
		btn_DT_save.setBounds(314, 132, 68, 25);
		panel.add(btn_DT_save);
		
		JButton btn_DT_del = new JButton("Del");
		btn_DT_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete.delete(nameTBL, nameID, currentID);
			}
		});
		btn_DT_del.setBounds(384, 132, 70, 25);
		panel.add(btn_DT_del);
	}
	public static void insertValues(String[] values) {
		frame.txt_DT_name.setText(values[1]);
		frame.txt_DT_desc.setText(values[2]);
	}
}
