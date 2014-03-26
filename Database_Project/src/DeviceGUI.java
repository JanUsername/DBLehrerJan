import java.awt.BorderLayout;
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

public class DeviceGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_D_name;
	private JTextField txt_D_price;
	private JTextField txt_D_devicetype;
	private JTextField txt_D_producer;
	private JTextArea txt_D_desc;

	static QueryMethod query = new QueryMethod();
	static InsertMethod insert = new InsertMethod();
	static DeleteMethod delete = new DeleteMethod();
	static UpdateMethod update = new UpdateMethod();

	static private String nameTBL = "tbl_device";
	static private String nameID = "device_ID";
	static String[] result;
	static String[] nameTxtF = { "devicename", "devicedesc", "devicetype_ID",
			"producer_ID", "unitprice" };
	static int currentID = 1;
	static int maxID;
	static DeviceGUI frame;
	String[] newValues = new String[6];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new DeviceGUI();
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
	public DeviceGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 542, 373);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblAddNewDevice = new JLabel("Add new Device");
		lblAddNewDevice.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAddNewDevice.setBounds(188, 12, 169, 15);
		panel.add(lblAddNewDevice);

		JLabel lblDeviceName = new JLabel("Device Name:");
		lblDeviceName.setBounds(12, 55, 125, 15);
		panel.add(lblDeviceName);

		txt_D_name = new JTextField();
		txt_D_name.setBounds(167, 53, 265, 19);
		panel.add(txt_D_name);
		txt_D_name.setColumns(10);

		txt_D_price = new JTextField();
		txt_D_price.setColumns(10);
		txt_D_price.setBounds(167, 82, 265, 19);
		panel.add(txt_D_price);

		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBounds(12, 84, 125, 15);
		panel.add(lblUnitPrice);

		txt_D_devicetype = new JTextField();
		txt_D_devicetype.setColumns(10);
		txt_D_devicetype.setBounds(167, 227, 265, 19);
		panel.add(txt_D_devicetype);

		JLabel lblDeviceType = new JLabel("Device Type:");
		lblDeviceType.setBounds(12, 229, 125, 15);
		panel.add(lblDeviceType);

		txt_D_producer = new JTextField();
		txt_D_producer.setColumns(10);
		txt_D_producer.setBounds(167, 258, 265, 19);
		panel.add(txt_D_producer);

		JLabel lblProducer = new JLabel("Producer:");
		lblProducer.setBounds(12, 260, 125, 15);
		panel.add(lblProducer);

		JButton btn_D_first = new JButton("<<");
		btn_D_first.setBounds(8, 315, 54, 25);
		panel.add(btn_D_first);

		JButton btn_D_back = new JButton("<");
		btn_D_back.setBounds(62, 315, 54, 25);
		panel.add(btn_D_back);

		JButton btn_D_forward = new JButton(">");
		btn_D_forward.setBounds(114, 315, 54, 25);
		panel.add(btn_D_forward);

		JButton btn_D_last = new JButton(">>");
		btn_D_last.setBounds(169, 315, 54, 25);
		panel.add(btn_D_last);

		JButton btn_D_new = new JButton("New");
		btn_D_new.setBounds(224, 315, 70, 25);
		panel.add(btn_D_new);

		JButton btn_D_save = new JButton("Save");
		btn_D_save.setBounds(292, 315, 68, 25);
		panel.add(btn_D_save);

		JButton btn_D_del = new JButton("Del");
		btn_D_del.setBounds(362, 315, 70, 25);
		panel.add(btn_D_del);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(12, 115, 125, 15);
		panel.add(lblDescription);

		txt_D_desc = new JTextArea();
		txt_D_desc.setBounds(167, 113, 265, 101);
		panel.add(txt_D_desc);
	}

	public static void insertValues(String[] values) {
		frame.txt_D_name.setText(values[1]);
		frame.txt_D_desc.setText(values[2]);
		frame.txt_D_devicetype.setText(values[3]);
		frame.txt_D_producer.setText(values[4]);
		frame.txt_D_price.setText(values[5]+"€");
	}
}
