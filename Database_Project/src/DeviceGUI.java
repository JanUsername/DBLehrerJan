import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class DeviceGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_D_name;
	private JTextField txt_D_price;
	private JTextArea txt_D_desc;
	private static JComboBox cbx_device;
	private static JComboBox cbx_producer;
	private static String devT = "tbl_deviceType";
	private static String producer = "tbl_producer";

	static QueryMethod query = new QueryMethod();
	static InsertMethod insert = new InsertMethod();
	static DeleteMethod delete = new DeleteMethod();
	static UpdateMethod update = new UpdateMethod();

	static private String devicetFK = "devicetype_ID";
	static private String producerFK = "producer_ID";
	static private String nameTBL = "tbl_device";
	static private String nameID = "device_ID";
	static String[] result;
	static String[] nameTxtF = { "devicename", "devicedesc", "unitprice" };
	static int currentID = 1;
	static int maxID;
	static DeviceGUI frame;
	String[] newValues = new String[3];

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
		txt_D_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.update(nameTBL, "devicename", txt_D_name.getText(),
						nameID, currentID);
			}
		});
		txt_D_name.setBounds(167, 53, 265, 19);
		panel.add(txt_D_name);
		txt_D_name.setColumns(10);

		txt_D_price = new JTextField();
		txt_D_price.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.update(nameTBL, "unitprice", txt_D_price.getText(),
						nameID, currentID);
			}
		});
		txt_D_price.setColumns(10);
		txt_D_price.setBounds(167, 82, 265, 19);
		panel.add(txt_D_price);

		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBounds(12, 84, 125, 15);
		panel.add(lblUnitPrice);

		JLabel lblDeviceType = new JLabel("Device Type:");
		lblDeviceType.setBounds(12, 229, 125, 15);
		panel.add(lblDeviceType);

		JLabel lblProducer = new JLabel("Producer:");
		lblProducer.setBounds(12, 260, 125, 15);
		panel.add(lblProducer);

		JButton btn_D_first = new JButton("<<");
		btn_D_first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = query.query(nameTBL, 1, nameID);
				insertValues(result);
				currentID = 1;
			}
		});
		btn_D_first.setBounds(8, 299, 54, 25);
		panel.add(btn_D_first);

		JButton btn_D_back = new JButton("<");
		btn_D_back.addActionListener(new ActionListener() {
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
		btn_D_back.setBounds(62, 299, 54, 25);
		panel.add(btn_D_back);

		JButton btn_D_forward = new JButton(">");
		btn_D_forward.addActionListener(new ActionListener() {
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
		btn_D_forward.setBounds(114, 299, 54, 25);
		panel.add(btn_D_forward);

		JButton btn_D_last = new JButton(">>");
		btn_D_last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxID = query.maxID(nameTBL, nameID);
				currentID = maxID;
				result = query.query(nameTBL, maxID, nameID);
				insertValues(result);
			}
		});
		btn_D_last.setBounds(169, 299, 54, 25);
		panel.add(btn_D_last);

		JButton btn_D_new = new JButton("New");
		btn_D_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = maxID + 1;
				frame.txt_D_name.setText("");
				frame.txt_D_name.requestFocusInWindow();
				frame.txt_D_desc.setText("");
				frame.txt_D_price.setText("");
			}
		});
		btn_D_new.setBounds(224, 299, 70, 25);
		panel.add(btn_D_new);

		JButton btn_D_save = new JButton("Save");
		btn_D_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newValues[0] = "" + txt_D_name.getText();
				newValues[1] = "" + txt_D_desc.getText();
				newValues[2] = "" + txt_D_price.getText();
				insert.insert(nameTBL, nameTxtF, newValues);
				update.updateFK(nameTBL, devicetFK, cbx_device.getSelectedIndex(), "device_ID", currentID);
				update.updateFK(nameTBL, producerFK, cbx_producer.getSelectedIndex(), "producer_ID", currentID);
				maxID = query.maxID(nameTBL, nameID);
			}
		});
		btn_D_save.setBounds(292, 299, 68, 25);
		panel.add(btn_D_save);

		JButton btn_D_del = new JButton("Del");
		btn_D_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete.delete(nameTBL, nameID, currentID);
			}
		});
		btn_D_del.setBounds(362, 299, 70, 25);
		panel.add(btn_D_del);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(12, 115, 125, 15);
		panel.add(lblDescription);

		txt_D_desc = new JTextArea();
		txt_D_desc.setBounds(167, 113, 265, 101);
		panel.add(txt_D_desc);
		
		int lengthDev = query.maxID(devT, "Devicetype_ID");
		
		String[] cbx_d_values = new String[lengthDev];
		cbx_d_values = query.queryColumn(devT, "name", lengthDev);
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel(cbx_d_values);
		
		cbx_device = new JComboBox(model);
		cbx_device.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				update.updateFK(nameTBL, devicetFK, cbx_device.getSelectedIndex(), "device_ID", currentID);
			}
		});
		cbx_device.setBounds(167, 224, 265, 24);
		panel.add(cbx_device);
		
		int lengthProd = query.maxID(producer, "producer_ID");
		String[] cbx_p_values = new String[lengthProd];
		
		cbx_p_values = query.queryColumn(producer, "name", lengthProd);
		DefaultComboBoxModel<String> modelPro = new DefaultComboBoxModel(cbx_p_values);
		
		cbx_producer = new JComboBox(modelPro);
		cbx_producer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.updateFK(nameTBL, producerFK, cbx_producer.getSelectedIndex(), "producer_ID", currentID);
			}
		});
		cbx_producer.setBounds(167, 255, 265, 24);
		panel.add(cbx_producer);
		
		JButton btnAddDeviceType = new JButton("Device Type");
		btnAddDeviceType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeviceTypeGUI dtGui = new DeviceTypeGUI();
				dtGui.openDeviceTypeGUI();
			}
		});
		btnAddDeviceType.setBounds(18, 336, 169, 25);
		panel.add(btnAddDeviceType);
		
		JButton btnNewProducer = new JButton("Producer");
		btnNewProducer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProducerGUI pGui = new ProducerGUI();
				pGui.openProducerGUI();
				frame.revalidate();
				frame.repaint();
			}
		});
		btnNewProducer.setBounds(199, 336, 169, 25);
		panel.add(btnNewProducer);
		
	}

	public static void insertValues(String[] values) {
		String deviceResult[] = query.query(devT, Integer.parseInt(values[3]),
				"deviceType_ID");
		String producerResult[] = query.query(producer,
				Integer.parseInt(values[4]), "producer_ID");
		frame.txt_D_name.setText(values[1]);
		frame.txt_D_desc.setText(values[2]);
		cbx_device.setSelectedIndex(Integer.parseInt(deviceResult[0]));
		cbx_producer.setSelectedIndex(Integer.parseInt(producerResult[0]));
		frame.txt_D_price.setText(values[5] + "€");
	}
}
