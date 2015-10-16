package edu.mansfield.squirtle_squad.user_interface;
import java.awt.EventQueue;

import javax.swing.JFrame;
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
	@SuppressWarnings("serial")
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(375, 38, 200, 20);
		frmDataviewgui.getContentPane().add(scrollPane);
		
		JList<String> list = new JList<String>();
		scrollPane.setViewportView(list);
		list.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {"Lowest Price", "Highest Price", "Item Name", "Item ID", "Bid/Auction", "Site of Origin"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(485, 80, 89, 23);
		frmDataviewgui.getContentPane().add(btnSearch);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDataviewgui.setVisible(false);
			}
		});
		btnCancel.setBounds(485, 428, 89, 23);
		frmDataviewgui.getContentPane().add(btnCancel);
	}
}
