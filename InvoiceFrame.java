
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;

//---------------------------------------------------------------------------
//
//LAVAFLO Client Management App  
//
//
//Author: Feuler Tovar
//Date: 12/01/18
//Issues: None known
//
//Description:
//This application will hold client information for a photography and other media businesses
//Data will be persisted using MySQL database schemas
//
//
//
//Assumptions:
//MySQL AND Java Swing dependencies are properly installed
//
//

public class InvoiceFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableInvoices;
	private JTextField idtext;
	private JTextField firsttext;
	private JTextField lasttext;
	private JTextField packagetext;
	private JTextField pricetext;
	private JTextField numbertext;
	private String fname;
	private String lname;
	
	private int xx,xy;
	

////////////////////////////////////
/// Invoice Page Entry point     ///
/// Input : None 				 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
///////////////////////////////////
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvoiceFrame frame = new InvoiceFrame(null,null);
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

////////////////////////////////////
/// Invoice Frame constructor    ///
/// Input : firstname, lastname  ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
////////////////////////////////////
	// position variable initialized
	int pos = 0;
	public static int currentUserId;

	public InvoiceFrame(String firstname, String lastname ) {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();

				int y = e.getYOnScreen();
				setLocation(x - xx, y - xy);
				

			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();

				xy = e.getY();
			}
		});
		
		//initialize globals
		fname = firstname;
		lname = lastname;

		initialize();
		fillInvoicesTable();
		
		tableInvoices.setShowGrid(true);
		tableInvoices.setGridColor(Color.ORANGE);
		tableInvoices.setSelectionBackground(Color.darkGray);

		JTableHeader th = tableInvoices.getTableHeader();
		th.setForeground(Color.BLUE);

		th.setFont(new Font("Tahoma", Font.PLAIN, 14));

	}
		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 566);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Invoices panel
		tableInvoices = new JTable();
		tableInvoices.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableInvoices.setBounds(16, 301, 790, 189);
		contentPane.add(tableInvoices);

		// tableInvoices initializer
		tableInvoices.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {

		}));
		tableInvoices.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				TableInvoiceMouseClicked(e);
			}
		});
		

		//Add text fields to Invoice panel
		numbertext = new JTextField();
		numbertext.setBounds(127, 42, 235, 31);
		contentPane.add(numbertext);
		numbertext.setColumns(10);

		firsttext = new JTextField();
		firsttext.setColumns(10);
		firsttext.setBounds(127, 85, 235, 31);
		firsttext.setText(fname);
		contentPane.add(firsttext);

		lasttext = new JTextField();
		lasttext.setColumns(10);
		lasttext.setBounds(127, 128, 235, 31);
		lasttext.setText(lname);
		contentPane.add(lasttext);

		packagetext = new JTextField();
		packagetext.setColumns(10);
		packagetext.setBounds(127, 168, 235, 31);
		contentPane.add(packagetext);

		pricetext = new JTextField();
		pricetext.setColumns(10);
		pricetext.setBounds(127, 211, 235, 31);
		contentPane.add(pricetext);

		//Add handler for first record button
		JButton btnFirst = new JButton("<<");
		btnFirst.setToolTipText("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ButtonFirstActionPerformed(e);
			}
		});
		btnFirst.setBounds(95, 262, 54, 14);
		contentPane.add(btnFirst);
		
		//Add handler for previous record button
		JButton btnPrev = new JButton("<");
		btnPrev.setToolTipText("Previous");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ButtonPreviousActionPerformed(e);
			}
		});
		btnPrev.setBounds(184, 262, 54, 14);
		contentPane.add(btnPrev);
		
		//Add handler for next record button
		JButton btnNext = new JButton(">");
		btnNext.setToolTipText("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ButtonNextActionPerformed(e);
			}
		});
		btnNext.setBounds(272, 262, 54, 14);
		contentPane.add(btnNext);

		//	//Add handler for last record button
		JButton btnLast = new JButton(">>");
		btnLast.setToolTipText("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ButtonLastActionPerformed(e);
			}
		});
		btnLast.setBounds(358, 262, 54, 14);
		contentPane.add(btnLast);

		//Add handler for add record button
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setOpaque(true);
		btnAdd.setBorderPainted(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddInvoiceActionPerformed(e);

			}
		});
		btnAdd.setBackground(new Color(153, 204, 153));
		btnAdd.setBounds(473, 254, 95, 22);
		contentPane.add(btnAdd);

		////Add handler for update record button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UpdateInvoiceActionPerformed(e);
			}
		});
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(153, 204, 255));
		btnUpdate.setBounds(584, 254, 95, 22);
		btnUpdate.setOpaque(true);
		btnUpdate.setBorderPainted(false);
		contentPane.add(btnUpdate);

		//Add handler for delete record button
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DeleteInvoiceActionPerformed(e);
			}
		});
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(255, 102, 51));
		btnDelete.setBounds(691, 254, 95, 22);
		btnDelete.setOpaque(true);
		btnDelete.setBorderPainted(false);
		contentPane.add(btnDelete);

		//Add labels for text fields and boxes
		JLabel lblId = new JLabel("Invoice#");
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setBounds(16, 49, 61, 16);
		contentPane.add(lblId);

		JLabel lblFirstname = new JLabel("First Name");
		lblFirstname.setForeground(new Color(255, 255, 255));
		lblFirstname.setBounds(16, 92, 80, 16);
		contentPane.add(lblFirstname);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(new Color(255, 255, 255));
		lblLastName.setBounds(16, 135, 80, 16);
		contentPane.add(lblLastName);

		JLabel lblPhone = new JLabel("Package");
		lblPhone.setForeground(new Color(255, 255, 255));
		lblPhone.setBounds(16, 175, 61, 16);
		contentPane.add(lblPhone);

		JLabel lbCost = new JLabel("Price");
		lbCost.setForeground(new Color(255, 255, 255));
		lbCost.setBounds(16, 218, 61, 16);
		contentPane.add(lbCost);

		JLabel lblPhoto = new JLabel("");
		lblPhoto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoto.setIcon(new ImageIcon(new ImageIcon(InvoiceFrame.class.getResource("/images/Lights.jpg")).getImage().getScaledInstance(378, 239, Image.SCALE_SMOOTH)));
		lblPhoto.setForeground(new Color(255, 255, 255));
		lblPhoto.setBounds(422, 31, 378, 211);
		contentPane.add(lblPhoto);
		
		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblX.setForeground(new Color(255, 255, 255));
		lblX.setHorizontalAlignment(SwingConstants.RIGHT);
		lblX.setBounds(781, 9, 25, 16);
		contentPane.add(lblX);
		
		JLabel lbgoback = new JLabel("");
		lbgoback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 GoBackActionPerformed(e);
			
			}
		});
		lbgoback.setBounds(750, 6, 25, 25);
		lbgoback.setIcon(new ImageIcon(new ImageIcon(InvoiceFrame.class.getResource("/images/icons/Favim.com-5208820.png"))
				.getImage().getScaledInstance(20, 12, Image.SCALE_SMOOTH)));
		contentPane.add(lbgoback);
	}
	
////////////////////////////////////
//  Delete a invoice		     ///
/// Input : Event 				 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
///////////////////////////////////
	private void DeleteInvoiceActionPerformed(ActionEvent evt) {

		// the user must select a Invoice to edit
		if (!numbertext.getText().equals("")) {

			int numid = Integer.valueOf(numbertext.getText());
			MyQuery mq = new MyQuery();
			mq.deleteInvoice(numid);
			refreshJtableInvoices();
			clearFields();

		} else {
			JOptionPane.showMessageDialog(null, "Select A Invoice From Table");
		}

	}// GEN-LAST:event_jButtonDeleteInvoiceActionPerformed
	
/////////////////////////////////////
/// Mouse handler for table click ///
/// Input : Event 				  ///
/// Output: None 				  ///
/// Returns nothing 			  ///
///////////////////////////////////
	private void TableInvoiceMouseClicked(MouseEvent evt) {

		// display selected JTable row data
		// get selected row index
		int rowIndex = tableInvoices.getSelectedRow();

		if (rowIndex >= 0) {
			//numbertext.setText(tableInvoices.getValueAt(rowIndex, 0).toString());
			firsttext.setText(tableInvoices.getValueAt(rowIndex, 1).toString());
			lasttext.setText(tableInvoices.getValueAt(rowIndex, 2).toString());
			packagetext.setText(tableInvoices.getValueAt(rowIndex, 3).toString());
			pricetext.setText(tableInvoices.getValueAt(rowIndex, 4).toString());
			numbertext.setText(tableInvoices.getValueAt(rowIndex, 5).toString());
		}

	}//

/////////////////////////////////////
///	Add an invoice				  ///
/// Input : Event 				  ///
/// Output: None 				  ///
/// Returns nothing 			  ///
///////////////////////////////////
	private void AddInvoiceActionPerformed(ActionEvent e) {//

		
		String firstname = firsttext.getText();
		String lastname = lasttext.getText();
		String pkg = packagetext.getText();
		String price = pricetext.getText();
		String number = numbertext.getText();

		// check if first name, last name & pkg fields are empty

		if ((!firstname.equalsIgnoreCase("")) | (!lastname.equalsIgnoreCase("")) | (!pkg.equalsIgnoreCase(""))) {
			try {
				Invoice client = new Invoice(null, firstname, lastname, pkg, price, number, currentUserId);
				MyQuery query = new MyQuery();
				query.insertInvoice(client);
				refreshJtableInvoices();
				clearFields();
			} catch (Exception ex) {
				Logger.getLogger(InvoiceFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You Must Fill in your name and number");
		}

	}//

/////////////////////////////////////
///	update an invoice 			  ///
/// Input : Event 				  ///
/// Output: None 				  ///
/// Returns nothing 			  ///
///////////////////////////////////	
	private void UpdateInvoiceActionPerformed(ActionEvent evt) {//

		// the user must select a Invoice to update
		if (!numbertext.getText().equals("")) {
			//int id = Integer.valueOf(idtext.getText());
			String firstname = firsttext.getText();
			String lastname = lasttext.getText();
			String pkg = packagetext.getText();
			String price = pricetext.getText();
			String number = numbertext.getText();


			Invoice con = new Invoice(null, firstname, lastname, pkg, price, number, currentUserId);

			MyQuery mq = new MyQuery();
			mq.updateInvoice(con);
			refreshJtableInvoices();
			clearFields();
		} else {
			JOptionPane.showMessageDialog(null, "Select A Invoice From Table");
		}
	}//
	
////////////////////////////////////////////////////////////
///	Handlers for Last, First, Previous and Next buttons	 ///
/// Input : Event 										 ///
/// Output: None 				 						 ///
/// Returns nothing 			 						 ///
////////////////////////////////////////////////////////////

	private void ButtonLastActionPerformed(ActionEvent evt) {//
		pos = tableInvoices.getRowCount() - 1;
		showData(pos);
	}//

	private void ButtonFirstActionPerformed(ActionEvent evt) {
		pos = 0;
		showData(pos);
	}//

	private void ButtonPreviousActionPerformed(ActionEvent evt) {//
		if (pos >= 1) {
			pos--;
			showData(pos);
		}

	}//

	private void ButtonNextActionPerformed(ActionEvent evt) {

		if (pos <= tableInvoices.getRowCount() - 2) {
			pos++;
			showData(pos);
		}

	}// GEN-LAST:event_jButtonNextActionPerformed

	//refresh the table
	public void refreshJtableInvoices() {
		tableInvoices.setModel(new DefaultTableModel());
		fillInvoicesTable();
	}

	//clear all fields
	public void clearFields() {
		firsttext.setText("");
		lasttext.setText("");
		packagetext.setText("");
		pricetext.setText("");
		numbertext.setText("");
		//
	}

	
/////////////////////////////////////////////////////////////
///	Populate text fields with table data				  ///
/// Input : index 										  ///
/// Output: None 										  ///
/// Returns nothing 									  ///
/////////////////////////////////////////////////////////////
	private void showData(int index) {
		firsttext.setText(tableInvoices.getValueAt(index, 1).toString());
		lasttext.setText(tableInvoices.getValueAt(index, 2).toString());
		packagetext.setText(tableInvoices.getValueAt(index, 3).toString());
		pricetext.setText(tableInvoices.getValueAt(index, 4).toString());
		numbertext.setText(tableInvoices.getValueAt(index, 5).toString());
		// select the table row
		tableInvoices.setRowSelectionInterval(index, index);
	}

/////////////////////////////////////////////////////////////
///	Fills table with data persisted in the database		  ///
/// Input : index 										  ///
/// Output: None 										  ///
/// Returns nothing 									  ///
/////////////////////////////////////////////////////////////	
	public void fillInvoicesTable() {

		MyQuery query = new MyQuery();
		ArrayList<Invoice> list = query.createInvoiceList(currentUserId);
		//
		String[] colNames = { "Id", "First Name", "Last Name", "Package", "Price", "Number"};
		Object[][] rows = new Object[list.size()][6];

		for (int i = 0; i < list.size(); i++) {
			rows[i][0] = list.get(i).getCid();
			rows[i][1] = list.get(i).getFname();
			rows[i][2] = list.get(i).getLname();
			rows[i][3] = list.get(i).getPkg();
			rows[i][4] = list.get(i).getPrice();
			rows[i][5] = list.get(i).getNumber();
		}

		MyTableModel model = new MyTableModel(rows, colNames);
		tableInvoices.setModel(model);
		tableInvoices.setRowHeight(40);
	}

	
/////////////////////////////////////////////////////////////////
///	Launched Contact Frame, passing in first and last name	  ///
/// Input : event 										      ///
/// Output: None 										      ///
/// Returns nothing 									      ///
////////////////////////////////////////////////////////////////	
	private void GoBackActionPerformed(MouseEvent e) {

		try {

				ContactFrame inframe = new ContactFrame();
				// conframe.setUndecorated(true);
				inframe.setVisible(true);
				inframe.setLocationRelativeTo(null);

				this.dispose();


		} catch (Exception ex) {
			Logger.getLogger(InvoiceFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
