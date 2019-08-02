
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.ListSelectionModel;


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

public class ContactFrame extends JFrame {


	private static final long serialVersionUID = 945764852938354115L;
	private JTable tableContacts;
	private JTextField idtext;
	private JTextField firsttext;
	private JTextField lasttext;
	private JTextField phonetext;
	private JTextField mailtext;
	private JTextField addresstext;
	private JTextField textFieldFb;
	private JTextField textFieldIg;
	private JTextField textFieldTw;
	private int xx, xy;

////////////////////////////////////
/// Contact Frame constructor     ///
/// Input : None 				 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
///////////////////////////////////
	// position variable initialized
	int pos = 0;
	public static int currentUserId;

	public ContactFrame() {

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
		
		initialize();
		fillContactsTable();

		tableContacts.setShowGrid(true);
		tableContacts.setGridColor(Color.ORANGE);
		tableContacts.setSelectionBackground(Color.darkGray);

		JTableHeader th = tableContacts.getTableHeader();
		th.setForeground(Color.BLUE);

		th.setFont(new Font("Tahoma", Font.PLAIN, 14));

	}

////////////////////////////////////
/// Contact Page Entry point     ///
/// Input : None 				 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
///////////////////////////////////
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactFrame frame = new ContactFrame();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//set background
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 820, 536);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("CONTACTS");
		getContentPane().setLayout(null);

		// Contact panel
		JPanel panelContacts = new JPanel();
		panelContacts.setBounds(0, 0, 820, 540);
		panelContacts.setBackground(new Color(119, 136, 153));
		getContentPane().add(panelContacts);
		panelContacts.setLayout(null);
		panelContacts.setVisible(true);
		
		//Construct Table
		tableContacts = new JTable();
		tableContacts.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		tableContacts.setFillsViewportHeight(true);
		tableContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableContacts.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableContacts.setBounds(16, 286, 790, 165);
		panelContacts.add(tableContacts);

		//tableContacts initializer
		tableContacts.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {

		}));
		tableContacts.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				TableContactMouseClicked(e);
			}
		});
		

		//Add text fields to Contacts panel
		idtext = new JTextField();
		idtext.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		idtext.setBounds(135, 21, 235, 31);
		panelContacts.add(idtext);
		idtext.setColumns(10);

		firsttext = new JTextField();
		firsttext.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		firsttext.setColumns(10);
		firsttext.setBounds(135, 64, 235, 31);
		panelContacts.add(firsttext);

		lasttext = new JTextField();
		lasttext.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lasttext.setColumns(10);
		lasttext.setBounds(135, 107, 235, 31);
		panelContacts.add(lasttext);

		phonetext = new JTextField();
		phonetext.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		phonetext.setColumns(10);
		phonetext.setBounds(135, 147, 235, 31);
		panelContacts.add(phonetext);

		mailtext = new JTextField();
		mailtext.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		mailtext.setColumns(10);
		mailtext.setBounds(135, 190, 235, 31);
		panelContacts.add(mailtext);

		//Add Buttons to panel
		
		//Add handler for first record button
		JButton btnFirst = new JButton("<<");
		btnFirst.setToolTipText("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ButtonFirstActionPerformed(e);
			}
		});
		btnFirst.setBounds(95, 250, 54, 14);
		panelContacts.add(btnFirst);

		//Add handler for previous record button
		JButton btnPrev = new JButton("<");
		btnPrev.setToolTipText("Previous");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ButtonPreviousActionPerformed(e);
			}
		});
		btnPrev.setBounds(184, 250, 54, 14);
		panelContacts.add(btnPrev);
		
		//Add handler for next record button
		JButton btnNext = new JButton(">");
		btnNext.setToolTipText("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ButtonNextActionPerformed(e);
			}
		});
		btnNext.setBounds(272, 250, 54, 14);
		panelContacts.add(btnNext);

		//Add handler for last record button
		JButton btnLast = new JButton(">>");
		btnLast.setToolTipText("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ButtonLastActionPerformed(e);
			}
		});
		btnLast.setBounds(358, 250, 54, 14);
		panelContacts.add(btnLast);

		addresstext = new JTextField();
		addresstext.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		addresstext.setHorizontalAlignment(SwingConstants.LEFT);
		addresstext.setBounds(492, 21, 264, 61);
		panelContacts.add(addresstext);
		addresstext.setColumns(10);

		//	//Add handler for add record button
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setOpaque(true);
		btnAdd.setBorderPainted(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AddContactActionPerformed(e);
			}
		});
		btnAdd.setBackground(new Color(153, 204, 153));
		btnAdd.setBounds(390, 474, 95, 22);
		panelContacts.add(btnAdd);
		
		//Add handler for update record button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UpdateContactActionPerformed(e);
			}
		});
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(153, 204, 255));
		btnUpdate.setBounds(497, 474, 95, 22);
		btnUpdate.setOpaque(true);
		btnUpdate.setBorderPainted(false);
		panelContacts.add(btnUpdate);

		//Add handler for delete record button
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DeleteContactActionPerformed(e);
			}
		});
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(255, 102, 51));
		btnDelete.setBounds(604, 474, 95, 22);
		btnDelete.setOpaque(true);
		btnDelete.setBorderPainted(false);
		panelContacts.add(btnDelete);

		//Add labels for text fields and boxes
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setBounds(24, 28, 61, 16);
		panelContacts.add(lblId);

		JLabel lblFirstname = new JLabel("First Name");
		lblFirstname.setForeground(new Color(255, 255, 255));
		lblFirstname.setBounds(24, 71, 80, 16);
		panelContacts.add(lblFirstname);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(new Color(255, 255, 255));
		lblLastName.setBounds(24, 114, 80, 16);
		panelContacts.add(lblLastName);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setForeground(new Color(255, 255, 255));
		lblPhone.setBounds(24, 154, 61, 16);
		panelContacts.add(lblPhone);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(24, 197, 61, 16);
		panelContacts.add(lblEmail);

		JLabel lblAdress = new JLabel("Address");
		lblAdress.setForeground(new Color(255, 255, 255));
		lblAdress.setBounds(408, 28, 61, 16);
		panelContacts.add(lblAdress);

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
		lblX.setBounds(780, 6, 25, 16);
		panelContacts.add(lblX);

		textFieldFb = new JTextField();
		textFieldFb.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		textFieldFb.setBounds(492, 123, 270, 21);
		panelContacts.add(textFieldFb);
		textFieldFb.setColumns(10);
		
		//Add handlers for social media links
		JLabel lblfacebook = new JLabel("");
		lblfacebook.setToolTipText("View Facebook");
		lblfacebook.setHorizontalAlignment(SwingConstants.CENTER);
		lblfacebook.setBounds(468, 123, 22, 22);
		lblfacebook.setIcon(new ImageIcon(new ImageIcon(ContactFrame.class.getResource("/images/icons/facebook.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		lblfacebook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BrowseWeb br = new BrowseWeb();
				try {
					br.Browser(textFieldFb.getText(), e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelContacts.add(lblfacebook);

		textFieldIg = new JTextField();
		textFieldIg.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		textFieldIg.setColumns(10);
		textFieldIg.setBounds(492, 148, 270, 21);
		panelContacts.add(textFieldIg);

		JLabel lblgram = new JLabel("");
		lblgram.setToolTipText("View Instagram");
		lblgram.setHorizontalAlignment(SwingConstants.CENTER);
		lblgram.setBounds(468, 149, 22, 22);
		lblgram.setIcon(new ImageIcon(new ImageIcon(ContactFrame.class.getResource("/images/icons/instagram.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		lblgram.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BrowseWeb br = new BrowseWeb();
				try {
					br.Browser(textFieldIg.getText(), e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelContacts.add(lblgram);

		textFieldTw = new JTextField();
		textFieldTw.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		textFieldTw.setColumns(10);
		textFieldTw.setBounds(492, 173, 270, 21);
		panelContacts.add(textFieldTw);

		JLabel lbltwitter = new JLabel("");
		lbltwitter.setToolTipText("View Twitter");

		lbltwitter.setHorizontalAlignment(SwingConstants.CENTER);
		lbltwitter.setBounds(468, 174, 22, 22);
		lbltwitter.setIcon(new ImageIcon(new ImageIcon(ContactFrame.class.getResource("/images/icons/twitter_icon.png"))
				.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		lbltwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BrowseWeb br = new BrowseWeb();
				try {
					br.Browser(textFieldTw.getText(), e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				;
			}
		});
		panelContacts.add(lbltwitter);

		
		
		JButton jbuttonInvoice = new JButton("Invoice");
		jbuttonInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InvoiceActionPerformed(e);

			}
		});
		jbuttonInvoice.setOpaque(true);
		jbuttonInvoice.setForeground(new Color(255, 255, 255));
		jbuttonInvoice.setBorderPainted(false);
		jbuttonInvoice.setBackground(new Color(210, 180, 140));
		jbuttonInvoice.setBounds(711, 474, 95, 22);
		panelContacts.add(jbuttonInvoice);

	}
	
////////////////////////////////////
// 	Delete a contact		     ///
/// Input : Event 				 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
///////////////////////////////////

	private void DeleteContactActionPerformed(ActionEvent evt) {

		// the user must select a contact to edit
		if (!idtext.getText().equals("")) {

			int id = Integer.valueOf(idtext.getText());
			MyQuery mq = new MyQuery();
			mq.deleteContact(id);
			refreshJtableContacts();
			clearFields();

		} else {
			JOptionPane.showMessageDialog(null, "Select A Contact From Table");
		}

	}// GEN-LAST:event_jButtonDeleteContactActionPerformed

/////////////////////////////////////
/// Mouse handler for table click ///
/// Input : Event 				  ///
/// Output: None 				  ///
/// Returns nothing 			  ///
///////////////////////////////////
	private void TableContactMouseClicked(MouseEvent evt) {

		// display selected JTable row data
		// get selected row index
		int rowIndex = tableContacts.getSelectedRow();

		if (rowIndex >= 0) {
			idtext.setText(tableContacts.getValueAt(rowIndex, 0).toString());
			firsttext.setText(tableContacts.getValueAt(rowIndex, 1).toString());
			lasttext.setText(tableContacts.getValueAt(rowIndex, 2).toString());
			phonetext.setText(tableContacts.getValueAt(rowIndex, 3).toString());
			mailtext.setText(tableContacts.getValueAt(rowIndex, 4).toString());
			addresstext.setText(tableContacts.getValueAt(rowIndex, 5).toString());
			textFieldFb.setText(tableContacts.getValueAt(rowIndex, 6).toString());
			textFieldIg.setText(tableContacts.getValueAt(rowIndex, 7).toString());
			textFieldTw.setText(tableContacts.getValueAt(rowIndex, 8).toString());
		}

	}//
	
/////////////////////////////////////
///	Add a contact 				  ///
/// Input : Event 				  ///
/// Output: None 				  ///
/// Returns nothing 			  ///
///////////////////////////////////
	private void AddContactActionPerformed(ActionEvent e) {//

		String firstname = firsttext.getText();
		String lastname = lasttext.getText();
		String phone = phonetext.getText();
		String address = addresstext.getText();
		String email = mailtext.getText();
		String fburl = textFieldFb.getText();
		String igurl = textFieldIg.getText();
		String twurl = textFieldTw.getText();

		// check if first name, last name & phone fields are empty

		if ((!firstname.equalsIgnoreCase("")) | (!lastname.equalsIgnoreCase("")) | (!phone.equalsIgnoreCase(""))) {
			try {
				Contact client = new Contact(null, firstname, lastname, phone, email, address, fburl, igurl, twurl,
						currentUserId);
				MyQuery query = new MyQuery();
				query.insertContact(client);
				refreshJtableContacts();
				clearFields();
			} catch (Exception ex) {
				Logger.getLogger(ContactFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You Must Fill in your name and number");
		}

	}//

/////////////////////////////////////
///	update a contact 			  ///
/// Input : Event 				  ///
/// Output: None 				  ///
/// Returns nothing 			  ///
///////////////////////////////////
	private void UpdateContactActionPerformed(ActionEvent evt) {//

		// the user must select a contact to delete
		if (!idtext.getText().equals("")) {
			int id = Integer.valueOf(idtext.getText());
			String firstname = firsttext.getText();
			String lastname = lasttext.getText();
			String phone = phonetext.getText();
			String address = addresstext.getText();
			String email = mailtext.getText();
			String fburl = textFieldFb.getText();
			String igurl = textFieldIg.getText();
			String twurl = textFieldTw.getText();

			Contact con = new Contact(id, firstname, lastname, phone, email, address, fburl, igurl, twurl,
					currentUserId);

			MyQuery mq = new MyQuery();
			mq.updateContact(con);
			refreshJtableContacts();
			clearFields();
		} else {
			JOptionPane.showMessageDialog(null, "Select A Contact From Table");
		}
	}//

////////////////////////////////////////////////////////////
///	Handlers for Last, First, Previous and Next buttons	 ///
/// Input : Event 										 ///
/// Output: None 				 						 ///
/// Returns nothing 			 						 ///
////////////////////////////////////////////////////////////
	private void ButtonLastActionPerformed(ActionEvent evt) {//
		pos = tableContacts.getRowCount() - 1;
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

		if (pos <= tableContacts.getRowCount() - 2) {
			pos++;
			showData(pos);
		}

	}// GEN-LAST:event_jButtonNextActionPerformed

	//refresh the table
	public void refreshJtableContacts() {
		tableContacts.setModel(new DefaultTableModel());
		fillContactsTable();
	}

	//clear all text fields
	public void clearFields() {
		idtext.setText("");
		firsttext.setText("");
		lasttext.setText("");
		mailtext.setText("");
		phonetext.setText("");
		addresstext.setText("");
		textFieldFb.setText("");
		textFieldIg.setText("");
		textFieldTw.setText("");
		//
	}

/////////////////////////////////////////////////////////////
///	Populate text fields with table data				  ///
/// Input : index 										  ///
/// Output: None 										  ///
/// Returns nothing 									  ///
/////////////////////////////////////////////////////////////
	private void showData(int index) {
		idtext.setText(tableContacts.getValueAt(index, 0).toString());
		firsttext.setText(tableContacts.getValueAt(index, 1).toString());
		lasttext.setText(tableContacts.getValueAt(index, 2).toString());
		phonetext.setText(tableContacts.getValueAt(index, 3).toString());
		mailtext.setText(tableContacts.getValueAt(index, 4).toString());
		addresstext.setText(tableContacts.getValueAt(index, 5).toString());
		textFieldFb.setText(tableContacts.getValueAt(index, 6).toString());
		textFieldIg.setText(tableContacts.getValueAt(index, 7).toString());
		textFieldTw.setText(tableContacts.getValueAt(index, 8).toString());

		// select the table row
		tableContacts.setRowSelectionInterval(index, index);
	}
	
	
/////////////////////////////////////////////////////////////
///	Fills table with data persisted in the database		  ///
/// Input : index 										  ///
/// Output: None 										  ///
/// Returns nothing 									  ///
/////////////////////////////////////////////////////////////
	public void fillContactsTable() {

		MyQuery query = new MyQuery();
		ArrayList<Contact> list = query.createList(currentUserId);
		//
		String[] colNames = { "Id", "First Name", "Last Name", "Phone", "Email", "Address", "Facebook", "Instagram",
				"Twitter" };
		Object[][] rows = new Object[list.size()][9];

		for (int i = 0; i < list.size(); i++) {
			rows[i][0] = list.get(i).getCid();
			rows[i][1] = list.get(i).getFname();
			rows[i][2] = list.get(i).getLname();
			rows[i][3] = list.get(i).getPhone();
			rows[i][4] = list.get(i).getEmail();
			rows[i][5] = list.get(i).getAddress();
			rows[i][6] = list.get(i).getFburl();
			rows[i][7] = list.get(i).getIgurl();
			rows[i][8] = list.get(i).getTwurl();

		}

		MyTableModel model = new MyTableModel(rows, colNames);
		tableContacts.setModel(model);
		tableContacts.setRowHeight(40);
	}
	
	
/////////////////////////////////////////////////////////////////
///	Launched Invoice Frame, passing in first and last name	  ///
/// Input : index 										      ///
/// Output: None 										      ///
/// Returns nothing 									      ///
////////////////////////////////////////////////////////////////
	private void InvoiceActionPerformed(ActionEvent e) {

		try {
			if (!idtext.getText().equals("")) {
				String firstname = firsttext.getText();
				String lastname = lasttext.getText();

				InvoiceFrame inframe = new InvoiceFrame(firstname, lastname);
				InvoiceFrame.currentUserId = currentUserId;
				inframe.setVisible(true);
				inframe.setLocationRelativeTo(null);

				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Select A Contact From Table");
			}

		} catch (Exception ex) {
			Logger.getLogger(ContactFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
