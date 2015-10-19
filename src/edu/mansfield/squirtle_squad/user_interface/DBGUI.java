package edu.mansfield.squirtle_squad.user_interface;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DBGUI {

	private JFrame frmDataviewgui;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBGUI window = new DBGUI();
					window.frmDataviewgui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DBGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDataviewgui = new JFrame();
		frmDataviewgui.setTitle("DataViewGUI");
		frmDataviewgui.setBounds(100, 100, 600, 500);
		frmDataviewgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDataviewgui.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 11, 299, 440);
		frmDataviewgui.getContentPane().add(scrollPane_1);
		
		JTextPane textPane = new JTextPane();
		scrollPane_1.setViewportView(textPane);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(319, 11, 46, 14);
		frmDataviewgui.getContentPane().add(lblSearch);
		
		JLabel lblNewLabel = new JLabel("Sort By:");
		lblNewLabel.setBounds(319, 39, 46, 14);
		frmDataviewgui.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(375, 11, 199, 20);
		frmDataviewgui.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSearch.setBounds(485, 343, 89, 23);
		frmDataviewgui.getContentPane().add(btnSearch);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDataviewgui.setVisible(false);
			}
		});
		btnCancel.setBounds(485, 428, 89, 23);
		frmDataviewgui.getContentPane().add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setBounds(315, 60, 109, 101);
		frmDataviewgui.getContentPane().add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Lowest Price");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(0, 0, 109, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Item ID");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(0, 78, 109, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Highest Price");
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(0, 26, 109, 23);
		panel.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Item Name");
		buttonGroup.add(rdbtnNewRadioButton_4);
		rdbtnNewRadioButton_4.setBounds(0, 52, 109, 23);
		panel.add(rdbtnNewRadioButton_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(426, 60, 109, 101);
		frmDataviewgui.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Bid");
		buttonGroup_1.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(0, 26, 109, 23);
		panel_1.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("Buy Now");
		buttonGroup_1.add(rdbtnNewRadioButton_5);
		rdbtnNewRadioButton_5.setBounds(0, 52, 109, 23);
		panel_1.add(rdbtnNewRadioButton_5);
		
		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("All Items");
		buttonGroup_1.add(rdbtnNewRadioButton_6);
		rdbtnNewRadioButton_6.setBounds(0, 0, 109, 23);
		panel_1.add(rdbtnNewRadioButton_6);
		
		JLabel lblNewLabel_1 = new JLabel("Item Type:");
		lblNewLabel_1.setBounds(431, 39, 89, 14);
		frmDataviewgui.getContentPane().add(lblNewLabel_1);
	}
}
