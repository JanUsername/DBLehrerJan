import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;


public class ErrorTypeGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_ET_name;
	private JTextArea txt_ET_desc;

	static QueryMethod query = new QueryMethod();
	static InsertMethod insert = new InsertMethod();
	static DeleteMethod delete = new DeleteMethod();
	static UpdateMethod update = new UpdateMethod();

	static private String nameTBL = "tbl_ErrorType";
	static private String nameID = "errortype_ID";
	static String[] result;
	static String[] nameTxtF = { "errorname", "description" };
	static int currentID = 1;
	static int maxID;
	static ErrorTypeGUI frame;
	String[] newValues = new String[3];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ErrorTypeGUI();
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
	public ErrorTypeGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 537, 271);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAdd = new JLabel("Add new Error Type");
		lblAdd.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAdd.setBounds(188, 12, 185, 15);
		panel.add(lblAdd);
		
		txt_ET_desc = new JTextArea();
		txt_ET_desc.setBounds(157, 76, 216, 96);
		panel.add(txt_ET_desc);
		
		JLabel lblErrorname = new JLabel("Errorname:");
		lblErrorname.setBounds(25, 49, 93, 15);
		panel.add(lblErrorname);
		
		txt_ET_name = new JTextField();
		txt_ET_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.update(nameTBL, "name", txt_ET_name.getText(), nameID, currentID);
			}
		});
		txt_ET_name.setBounds(159, 47, 214, 19);
		panel.add(txt_ET_name);
		txt_ET_name.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(25, 78, 93, 15);
		panel.add(lblDescription);
		
		JButton btn_ET_first = new JButton("<<");
		btn_ET_first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = query.query(nameTBL, 1, nameID);
				insertValues(result);
				currentID = 1;
			}
		});
		btn_ET_first.setBounds(25, 201, 54, 25);
		panel.add(btn_ET_first);
		
		JButton btn_ET_back = new JButton("<");
		btn_ET_back.addActionListener(new ActionListener() {
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
		btn_ET_back.setBounds(79, 201, 54, 25);
		panel.add(btn_ET_back);
		
		JButton btn_ET_forward = new JButton(">");
		btn_ET_forward.addActionListener(new ActionListener() {
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
		btn_ET_forward.setBounds(131, 201, 54, 25);
		panel.add(btn_ET_forward);
		
		JButton btn_ET_last = new JButton(">>");
		btn_ET_last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxID = query.maxID(nameTBL, nameID);
				currentID = maxID;
				result = query.query(nameTBL, maxID, nameID);
				insertValues(result);
			}
		});
		btn_ET_last.setBounds(186, 201, 54, 25);
		panel.add(btn_ET_last);
		
		JButton btn_ET_new = new JButton("New");
		btn_ET_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = maxID + 1;
				frame.txt_ET_name.setText("");
				frame.txt_ET_name.requestFocusInWindow();
				frame.txt_ET_desc.setText("");
			}
		});
		btn_ET_new.setBounds(241, 201, 70, 25);
		panel.add(btn_ET_new);
		
		JButton btn_ET_save = new JButton("Save");
		btn_ET_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newValues[0] = "" + txt_ET_name.getText();
				newValues[1] = "" + txt_ET_desc.getText();
				insert.insert(nameTBL, nameTxtF, newValues);
				maxID = query.maxID(nameTBL, nameID);
			}
		});
		btn_ET_save.setBounds(309, 201, 68, 25);
		panel.add(btn_ET_save);
		
		JButton btn_ET_del = new JButton("Del");
		btn_ET_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete.delete(nameTBL, nameID, currentID);
			}
		});
		btn_ET_del.setBounds(379, 201, 70, 25);
		panel.add(btn_ET_del);
		
		
	}
	public static void insertValues(String[] values) {
		frame.txt_ET_name.setText(values[1]);
		//System.out.println(values[2]);
		frame.txt_ET_desc.setText(values[2]);
	}
}
