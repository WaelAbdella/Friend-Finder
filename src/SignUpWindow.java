import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JPasswordField;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Cursor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;


public class SignUpWindow extends JFrame {

	private JPanel contentPane;
	private JLabel lblAge;
	private  JLabel label_2;
	private JLabel lblLastName;
	private JLabel lblEmail;
	private JLabel lblFirstHobby;
	private JLabel lblFirstHobby_1;
	private JLabel lblThirdHobby;
	private JLabel lblSecondHobby;
	private JLabel lblUsername;
	private JLabel lblConfirmPassword;
	private JLabel lblPassword;
	private JTextField ID_Field;
	private JTextField Age_Field;
	private JTextField First_Name_Field;
	private JTextField Last_Name_Field;
	private JTextField Email_Field;
	private JTextField Number_Field;
	private JTextField Username_Field;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JComboBox Hobby1_Field;
	 private ImageIcon format =null;
	    String filename = null;
	    byte[] person_image = null;
	    private JLabel lbl_img;
	    private JDesktopPane desktopPane;
	    private JTextField Sign_up_Field;
	    private JComboBox Hobby2_Field;
	    private JComboBox Course_Field;
	    private JTextField txtClose;
	   
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpWindow frame = new SignUpWindow();
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
	public SignUpWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(380, 50, 870, 620);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(10, 28, 52));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		Sign_up_Field = new JTextField();
		Sign_up_Field.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Sign_up_Field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					 Class.forName("org.sqlite.JDBC");
					 
				

                    Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
                    String username_check = Username_Field.getText();
                    String Checkquery    = "select * from USERS  where username=?";
                    PreparedStatement Checkstatement = connection.prepareStatement(Checkquery);

            		String FirstHobby=(String) Hobby1_Field.getSelectedItem();
            		String SecondHobby=(String) Hobby2_Field.getSelectedItem();
            		String Course=(String) Course_Field.getSelectedItem();
            		
            		Checkstatement.setString(1,FirstHobby);
                    Checkstatement.setString(1,username_check);
                    ResultSet set=Checkstatement.executeQuery();
                    
                    if (set.next())
                    {
                   	 JOptionPane.showMessageDialog(null, "Username Exists please try again");
                    }
                    else
                    if(!passwordField.getText().equals(passwordField_1.getText()) )
                    {
                    	JOptionPane.showMessageDialog(null, "Password Mismatch Please try again");
                    	
                    }
                    else
                    {	
                    String query = "insert into USERS values(?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement statement = connection.prepareStatement(query);
                
                   
                    statement.setString(1, ID_Field.getText());
                    statement.setString(2, Age_Field.getText());
                    statement.setString(3, First_Name_Field.getText());
                    statement.setString(4, Last_Name_Field.getText());
                    statement.setString(5, Email_Field.getText());
                    statement.setString(6, FirstHobby);
                    statement.setString(7, SecondHobby);
                    statement.setString(8, Number_Field.getText());
                    statement.setString(9, Course);
                    statement.setString(10, Username_Field.getText());
                    statement.setString(11, passwordField.getText());
                    statement.setBytes(12,person_image);
                    
                    statement.execute();
                    JOptionPane.showMessageDialog(null, "Sign Up Successfull, Please Login using your username and password");
                    dispose();
    				BetterLogin BL = new BetterLogin();
    				BL.setVisible(true);

                    
                    }

				} catch (ClassNotFoundException | SQLException e)
				{
					e.printStackTrace();
				}
			}
		
		});		
		
		
		Hobby1_Field = new JComboBox();
		Hobby1_Field.addItem("Football");
		Hobby1_Field.addItem("Basketball");
		Hobby1_Field.addItem("Jogging");
		Hobby1_Field.addItem("Tennis");
		Hobby1_Field.setSelectedItem(null);
		Hobby1_Field.setBounds(305, 285, 237, 25);
		contentPane.add(Hobby1_Field);

		Hobby2_Field = new JComboBox();
		Hobby2_Field.addItem("Lisening to Music");
		Hobby2_Field.addItem("Writing");
		Hobby2_Field.addItem("Reading");
		Hobby2_Field.addItem("Partying");
		Hobby2_Field.setSelectedItem(null);
		Hobby2_Field.setBounds(305, 329, 237, 25);
		contentPane.add(Hobby2_Field);
		
		Course_Field = new JComboBox();
		Course_Field.addItem("Computer Science");
		Course_Field.addItem("International Business");
		Course_Field.addItem("Engineering");
		Course_Field.setSelectedItem(null);
		Course_Field.setBounds(305, 241, 237, 25);
		contentPane.add(Course_Field);
		
		
		
		
		
		Sign_up_Field.setText("Sign up");
		Sign_up_Field.setOpaque(false);
		Sign_up_Field.setHorizontalAlignment(SwingConstants.CENTER);
		Sign_up_Field.setForeground(Color.WHITE);
		Sign_up_Field.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Sign_up_Field.setColumns(10);
		Sign_up_Field.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Sign_up_Field.setBounds(188, 546, 120, 25);
		contentPane.add(Sign_up_Field);
		
		JLabel lblID = new JLabel("ID");
		lblID.setHorizontalTextPosition(SwingConstants.CENTER);
		lblID.setHorizontalAlignment(SwingConstants.LEFT);
		lblID.setForeground(Color.WHITE);
		lblID.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblID.setBounds(10, 11, 243, 33);
		contentPane.add(lblID);
		
		
		
		lblAge = new JLabel("AGE");
		lblAge.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAge.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge.setForeground(Color.WHITE);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAge.setBounds(10, 55, 243, 33);
		contentPane.add(lblAge);
		
		
		
		
		label_2 = new JLabel("First Name");
		label_2.setHorizontalTextPosition(SwingConstants.CENTER);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_2.setBounds(10, 99, 243, 33);
		contentPane.add(label_2);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLastName.setBounds(10, 143, 243, 33);
		contentPane.add(lblLastName);
		
		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail.setBounds(10, 187, 243, 33);
		contentPane.add(lblEmail);
		
		lblFirstHobby = new JLabel("Course");
		lblFirstHobby.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFirstHobby.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstHobby.setForeground(Color.WHITE);
		lblFirstHobby.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFirstHobby.setBounds(10, 231, 243, 33);
		contentPane.add(lblFirstHobby);
		
		lblFirstHobby_1 = new JLabel("First Hobby");
		lblFirstHobby_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFirstHobby_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstHobby_1.setForeground(Color.WHITE);
		lblFirstHobby_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFirstHobby_1.setBounds(10, 275, 243, 33);
		contentPane.add(lblFirstHobby_1);
		
		lblThirdHobby = new JLabel("Phone Number");
		lblThirdHobby.setHorizontalTextPosition(SwingConstants.CENTER);
		lblThirdHobby.setHorizontalAlignment(SwingConstants.LEFT);
		lblThirdHobby.setForeground(Color.WHITE);
		lblThirdHobby.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThirdHobby.setBounds(10, 363, 243, 33);
		contentPane.add(lblThirdHobby);
		
		lblSecondHobby = new JLabel("Second Hobby");
		lblSecondHobby.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSecondHobby.setHorizontalAlignment(SwingConstants.LEFT);
		lblSecondHobby.setForeground(Color.WHITE);
		lblSecondHobby.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSecondHobby.setBounds(10, 319, 243, 33);
		contentPane.add(lblSecondHobby);
		
		lblUsername = new JLabel("Username");
		lblUsername.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsername.setBounds(10, 407, 243, 33);
		contentPane.add(lblUsername);
		
		lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblConfirmPassword.setBounds(10, 495, 243, 33);
		contentPane.add(lblConfirmPassword);
		
		lblPassword = new JLabel("Password");
		lblPassword.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(10, 451, 243, 33);
		contentPane.add(lblPassword);
		
		ID_Field = new JTextField();
		ID_Field.setColumns(10);
		ID_Field.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ID_Field.setBounds(305, 19, 237, 25);
		contentPane.add(ID_Field);
		
		Age_Field = new JTextField();
		Age_Field.setColumns(10);
		Age_Field.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Age_Field.setBounds(305, 63, 237, 25);
		contentPane.add(Age_Field);
		
		First_Name_Field = new JTextField();
		First_Name_Field.setColumns(10);
		First_Name_Field.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		First_Name_Field.setBounds(305, 107, 237, 25);
		contentPane.add(First_Name_Field);
		
		Last_Name_Field = new JTextField();
		Last_Name_Field.setColumns(10);
		Last_Name_Field.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Last_Name_Field.setBounds(305, 151, 237, 25);
		contentPane.add(Last_Name_Field);
		
		Email_Field = new JTextField();
		Email_Field.setColumns(10);
		Email_Field.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Email_Field.setBounds(305, 195, 237, 25);
		contentPane.add(Email_Field);
		
		Number_Field = new JTextField();
		Number_Field.setColumns(10);
		Number_Field.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Number_Field.setBounds(305, 371, 237, 25);
		contentPane.add(Number_Field);
		
		Username_Field = new JTextField();
		Username_Field.setColumns(10);
		Username_Field.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Username_Field.setBounds(305, 415, 237, 25);
		contentPane.add(Username_Field);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setBounds(305, 455, 237, 25);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setEchoChar('*');
		passwordField_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField_1.setBounds(305, 503, 237, 25);
		contentPane.add(passwordField_1);
		
		JButton btnNewButton = new JButton("Attach Image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
		        chooser.showOpenDialog(null);
		        File f = chooser.getSelectedFile();
		        
		        filename =f.getAbsolutePath();
		        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_DEFAULT));
		        lbl_img.setIcon(imageIcon);
		      try {

		            File image = new File(filename);
		            FileInputStream fis = new FileInputStream (image);
		            ByteArrayOutputStream bos= new ByteArrayOutputStream();
		            byte[] buf = new byte[1024];

		            for(int readNum; (readNum=fis.read(buf))!=-1; ){

		                bos.write(buf,0,readNum);
		            }
		            person_image=bos.toByteArray();
		        }

		        catch(Exception e){
		            JOptionPane.showMessageDialog(null,e);

		        }
		       
		    }//GEN-LAST:event_jMenuItem1ActionPerformed

			
		});
		btnNewButton.setBounds(351, 546, 153, 23);
		contentPane.add(btnNewButton);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(552, 9, 292, 226);
		contentPane.add(desktopPane);
		
		lbl_img = new JLabel();
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lbl_img, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lbl_img, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
		);
		desktopPane.setLayout(gl_desktopPane);
		
		txtClose = new JTextField();
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				BetterLogin BL = new BetterLogin();
				BL.setVisible(true);
			}
		});
		txtClose.setText("Close");
		txtClose.setOpaque(false);
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setForeground(Color.WHITE);
		txtClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtClose.setColumns(10);
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.setBounds(21, 550, 120, 25);
		contentPane.add(txtClose);
		
		
		

	}
}
