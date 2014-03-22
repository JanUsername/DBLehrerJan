import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;


public class DataBGUI extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataBGUI frame = new DataBGUI();
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
	public DataBGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 416);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblHalloJanSchler = new JLabel("Order");
		lblHalloJanSchler.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHalloJanSchler.setBounds(12, 12, 151, 15);
		panel.add(lblHalloJanSchler);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 39, 70, 15);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
	}
}
