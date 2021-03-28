import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollBar;


public class BetterLogin extends JFrame {

	private JPanel contentPane;
	private JTextField username_field;
	private JTextField txtLogin;
	private JTextField txtClose;
	private JPasswordField password_field;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblGetMoney;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}	catch (Exception e){
			
		}
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BetterLogin frame = new BetterLogin();
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
	public BetterLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(10, 28, 52));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username_field = new JTextField();
		username_field.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		username_field.setBounds(300, 102, 259, 33);
		contentPane.add(username_field);
		username_field.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				try{
					 Class.forName("org.sqlite.JDBC");

                     Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
                     
                     String username = username_field.getText();
                     String password = password_field.getText();
                     String query    = "select * from USERS  where username=? and password=?";
                     PreparedStatement statement = connection.prepareStatement(query);
                     statement.setString(1,username);
                     statement.setString(2,password);
                     
                     ResultSet set=statement.executeQuery();
                     
                     if (set.next())
                     {
                    	 JOptionPane.showMessageDialog(null, "login successful");
                    	 if(username.equals("admin"))
                         {
                    		 dispose();
                    		 UpdateStudentInformation USI = new UpdateStudentInformation();
                    		 USI.setVisible(true);
             				connection.close();
                         }
                    	 else{
                    	 dispose();
         				 FriendFinder FF = new FriendFinder();
         				 FF.setVisible(true);
         				connection.close();
                    	 }
                     }
                     else
                     {
                    	 JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    	 connection.close();
                     }
                     
				} catch (ClassNotFoundException | SQLException e)
				{
					e.printStackTrace();
	
				}
				
			}
		
		});
		txtLogin.setForeground(new Color(255, 255, 255));
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setText("LOGIN");
		txtLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtLogin.setOpaque(false);
		txtLogin.setBounds(301, 218, 120, 25);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		txtClose = new JTextField();
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		txtClose.setForeground(new Color(255, 255, 255));
		txtClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setText("CLOSE");
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.setOpaque(false);
		txtClose.setColumns(10);
		txtClose.setBounds(431, 218, 128, 25);
		contentPane.add(txtClose);
		
		password_field = new JPasswordField();
		password_field.setEchoChar('*');
		password_field.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		password_field.setBounds(300, 146, 259, 33);
		contentPane.add(password_field);
		
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(346, 32, 173, 47);
		contentPane.add(lblLogin);
		
		lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBounds(216, 106, 74, 24);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(216, 150, 74, 24);
		contentPane.add(lblPassword);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon (this.getClass().getResource("/Capture3.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(10, 52, 192, 201);
		contentPane.add(label);
		
		JTextField txtSignUp = new JTextField();
		txtSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
           	 dispose();
           	 
			 SignUpWindow SUW = new SignUpWindow();
			 SUW.setVisible(true);
				
			}
		});
		txtSignUp.setText("Sign up");
		txtSignUp.setOpaque(false);
		txtSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		txtSignUp.setForeground(Color.WHITE);
		txtSignUp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSignUp.setColumns(10);
		txtSignUp.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtSignUp.setBounds(367, 254, 120, 25);
		contentPane.add(txtSignUp);
		
		lblGetMoney = new JLabel("GET MONEY");
		lblGetMoney.setBounds(61, 423, 387, 108);
		contentPane.add(lblGetMoney);
		setUndecorated(true);
		
		
	}
}