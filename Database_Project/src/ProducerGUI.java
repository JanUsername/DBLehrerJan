import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ProducerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_P_name;
	private JTextField txt_P_address;
	private JTextField txt_P_loc;
	
	static QueryMethod query = new QueryMethod();
	static InsertMethod insert = new InsertMethod();
	static DeleteMethod delete = new DeleteMethod();
	static UpdateMethod update = new UpdateMethod();

	static private String nameTBL = "tbl_producer";
	static private String nameID = "producer_ID";
	static String[] result;
	static String[] nameTxtF = { "name", "address", "location" };
	static int currentID = 1;
	static int maxID;
	static ProducerGUI frameProdGui;
	String[] newValues = new String[3];
	/**
	 * Launch the application.
	 */
	public void openProducerGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameProdGui = new ProducerGUI();
					frameProdGui.setVisible(true);
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
	public ProducerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 570, 203);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAddNewProducer = new JLabel("Add new Producer");
		lblAddNewProducer.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAddNewProducer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewProducer.setBounds(194, 12, 175, 15);
		panel.add(lblAddNewProducer);
		
		JLabel name = new JLabel("Name:");
		name.setBounds(22, 46, 70, 15);
		panel.add(name);
		
		txt_P_name = new JTextField();
		txt_P_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.update(nameTBL, "name", txt_P_name.getText(), nameID, currentID);
			}
		});
		txt_P_name.setBounds(150, 44, 255, 19);
		panel.add(txt_P_name);
		txt_P_name.setColumns(10);
		
		txt_P_address = new JTextField();
		txt_P_address.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.update(nameTBL, "address", txt_P_address.getText(), nameID, currentID);
			}
		});
		txt_P_address.setColumns(10);
		txt_P_address.setBounds(150, 75, 255, 19);
		panel.add(txt_P_address);
		
		JLabel address = new JLabel("Address:");
		address.setBounds(22, 77, 70, 15);
		panel.add(address);
		
		txt_P_loc = new JTextField();
		txt_P_loc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.update(nameTBL, "location", txt_P_loc.getText(), nameID, currentID);
			}
		});
		txt_P_loc.setColumns(10);
		txt_P_loc.setBounds(150, 106, 255, 19);
		panel.add(txt_P_loc);
		
		JLabel location = new JLabel("Location");
		location.setBounds(22, 108, 70, 15);
		panel.add(location);
		
		JButton btn_P_first = new JButton("<<");
		btn_P_first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = query.query(nameTBL, 1, nameID);
				insertValues(result);
				currentID = 1;
			}
		});
		btn_P_first.setBounds(27, 150, 54, 25);
		panel.add(btn_P_first);
		
		JButton btn_P_back = new JButton("<");
		btn_P_back.addActionListener(new ActionListener() {
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
		btn_P_back.setBounds(81, 150, 54, 25);
		panel.add(btn_P_back);
		
		JButton btn_P_forward = new JButton(">");
		btn_P_forward.addActionListener(new ActionListener() {
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
		btn_P_forward.setBounds(133, 150, 54, 25);
		panel.add(btn_P_forward);
		
		JButton btn_P_last = new JButton(">>");
		btn_P_last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxID = query.maxID(nameTBL, nameID);
				currentID = maxID;
				result = query.query(nameTBL, maxID, nameID);
				insertValues(result);
			}
		});
		btn_P_last.setBounds(188, 150, 54, 25);
		panel.add(btn_P_last);
		
		JButton btn_P_new = new JButton("New");
		btn_P_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = maxID + 1;
				frameProdGui.txt_P_name.setText("");
				frameProdGui.txt_P_name.requestFocusInWindow();
				frameProdGui.txt_P_loc.setText("");
				frameProdGui.txt_P_address.setText("");
			}
		});
		btn_P_new.setBounds(243, 150, 70, 25);
		panel.add(btn_P_new);
		
		JButton btn_P_save = new JButton("Save");
		btn_P_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newValues[0] = "" + txt_P_name.getText();
				newValues[1] = "" + txt_P_address.getText();
				newValues[2] = "" + txt_P_loc.getText();
				insert.insert(nameTBL, nameTxtF, newValues);
				maxID = query.maxID(nameTBL, nameID);
			}
		});
		btn_P_save.setBounds(311, 150, 68, 25);
		panel.add(btn_P_save);
		
		JButton btn_P_del = new JButton("Del");
		btn_P_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete.delete(nameTBL, nameID, currentID);
			}
		});
		btn_P_del.setBounds(381, 150, 70, 25);
		panel.add(btn_P_del);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public static void insertValues(String[] values) {
		frameProdGui.txt_P_name.setText(values[1]);
		System.out.println(values[1]);
		frameProdGui.txt_P_address.setText(values[2]);
		frameProdGui.txt_P_loc.setText(values[3]);
	}
}
