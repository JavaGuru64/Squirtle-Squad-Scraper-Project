package edu.mansfield.squirtle_squad.user_interface;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import edu.mansfield.squirtle_squad.database.DatabaseInteractions;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DBGUI {

	private JFrame frmDataviewgui;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	public String searchTerm = null;
	public String searchType = null;
	public String searchOrder = null;

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
		final DatabaseInteractions DI = new DatabaseInteractions();
		final Connection conn = DI.dbConnect();
		
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
		
		final JTextPane textPane = new JTextPane();
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
		
		final JRadioButton lowPriceRB = new JRadioButton("Lowest Price");
		buttonGroup.add(lowPriceRB);
		lowPriceRB.setBounds(0, 0, 109, 23);
		panel.add(lowPriceRB);
		
		final JRadioButton itemIdRB = new JRadioButton("Item ID");
		buttonGroup.add(itemIdRB);
		itemIdRB.setBounds(0, 78, 109, 23);
		panel.add(itemIdRB);
		
		final JRadioButton highPriceRB = new JRadioButton("Highest Price");
		buttonGroup.add(highPriceRB);
		highPriceRB.setBounds(0, 26, 109, 23);
		panel.add(highPriceRB);
		
		final JRadioButton itemNameRB = new JRadioButton("Item Name");
		buttonGroup.add(itemNameRB);
		itemNameRB.setBounds(0, 52, 109, 23);
		panel.add(itemNameRB);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(426, 60, 109, 101);
		frmDataviewgui.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		final JRadioButton bidRB = new JRadioButton("Bid");
		buttonGroup_1.add(bidRB);
		bidRB.setBounds(0, 26, 109, 23);
		panel_1.add(bidRB);
		
		final JRadioButton buyNowRB = new JRadioButton("Buy Now");
		buttonGroup_1.add(buyNowRB);
		buyNowRB.setBounds(0, 52, 109, 23);
		panel_1.add(buyNowRB);
		
		final JRadioButton allItemsRB = new JRadioButton("All Items");
		buttonGroup_1.add(allItemsRB);
		allItemsRB.setBounds(0, 0, 109, 23);
		panel_1.add(allItemsRB);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//If statements to determine searchOrder
				if(lowPriceRB.isSelected()){
					searchOrder = "price ASC";
				}
				else if(highPriceRB.isSelected()){
					searchOrder = "price DESC";

				}
				else if(itemIdRB.isSelected()){
					searchOrder = "id";
					
				}
				else if(itemNameRB.isSelected()){
					searchOrder = "title";
				}
				else{
					searchOrder = "id";
				}
				
				//If statements to determine searchType
				if(bidRB.isSelected()){
					searchType = "true";
				}
				else if(buyNowRB.isSelected()){
					searchType = "false";
				}
				else if(allItemsRB.isSelected()){
					searchType = "";
				}
				
				//Get searchTerm from textField.
				searchTerm = textField.getText();
				if(searchTerm.equals("")){
					searchTerm = "";
				}
				else{
					searchTerm = "\"" + searchTerm + "\"";
				}
				
				
				//Get data from db and display in textPane.
				try {
					//System.out.println("SearchTerm = " + searchTerm + ", searchOrder = " + searchOrder + ", searchType = " + searchType);
					String data = DI.searchSelect(conn, searchTerm, searchOrder, searchType);
					//System.out.println(data);
					textPane.setText(data);
				} catch (SQLException e) {
					System.out.println("You broked it. Right there --->");
					e.printStackTrace();
				}
				
			}
		});
		btnSearch.setBounds(485, 343, 89, 23);
		frmDataviewgui.getContentPane().add(btnSearch);
		
		JLabel lblNewLabel_1 = new JLabel("Item Type:");
		lblNewLabel_1.setBounds(431, 39, 89, 14);
		frmDataviewgui.getContentPane().add(lblNewLabel_1);
	}
}
