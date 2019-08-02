import java.awt.EventQueue;

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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPasswordField;

public class LogInFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4936501633883316203L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private int xx, xy;
	private final JPasswordField textFieldPwd = new JPasswordField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrame frame = new LogInFrame();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

////////////////////////////////////
/// Login Frame constructor     ///
/// Input : None 				 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
///////////////////////////////////
	public LogInFrame() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 393);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 285, 393);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbLabelLens = new JLabel("New label");
		lbLabelLens.setHorizontalAlignment(SwingConstants.CENTER);
		lbLabelLens.setIcon(new ImageIcon(LogInFrame.class.getResource("/images/camera-lens.jpg")));
		lbLabelLens.setBounds(0, 0, 285, 298);
		panel.add(lbLabelLens);

		JLabel lblLavaFlo = new JLabel("LAVA FLO");
		lblLavaFlo.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLavaFlo.setForeground(new Color(255, 0, 51));
		lblLavaFlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLavaFlo.setBounds(85, 309, 113, 36);
		panel.add(lblLavaFlo);

		JButton btnNewButton = new JButton("LogIn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginActionPerformed(e);
				// System.exit(0);
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(204, 51, 0));
		btnNewButton.setOpaque(true);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(363, 265, 252, 29);
		contentPane.add(btnNewButton);

		textFieldName = new JTextField();
		textFieldName.setBounds(346, 118, 285, 35);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(356, 87, 275, 2);
		contentPane.add(separator);

		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(346, 100, 85, 16);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(346, 165, 85, 16);
		contentPane.add(lblPassword);

		textFieldPwd.setColumns(10);
		textFieldPwd.setBounds(346, 181, 285, 35);
		contentPane.add(textFieldPwd);

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
		lblX.setBounds(684, 6, 25, 16);
		contentPane.add(lblX);
	}

////////////////////////////////////
/// Login page				     ///
/// Input : Event 				 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
///////////////////////////////////
	private void LoginActionPerformed(ActionEvent e) {

		Connection conn = MyDatabase.getJdbc();
		PreparedStatement pstm;
		ResultSet rest;
//
		try {
			pstm = conn.prepareStatement("SELECT `user`, `pwd`, id FROM `login` WHERE `user` = ? AND `pwd` = ?");
			pstm.setString(1, textFieldName.getText());
			pstm.setString(2, String.valueOf(textFieldPwd.getPassword()));
			rest = pstm.executeQuery();

			if (rest.next()) {

				ContactFrame.currentUserId = rest.getInt("id");
				ContactFrame conframe = new ContactFrame();
				conframe.setVisible(true);
				conframe.setLocationRelativeTo(null);

				this.dispose();

			} else {
				JOptionPane.showMessageDialog(null, "Login Error");
			}

		} catch (SQLException ex) {
			Logger.getLogger(LogInFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
