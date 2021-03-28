import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JDesktopPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class UpdateStudentInformation extends JFrame {

	private JPanel contentPane;
	private JTextField txtClose;
	private JTextField txtSearch;
	private JTextField ID_Search;
	private JLabel lblEnterId;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField First_Name_Result;
	private JTextField Last_Name_Result;
	private JTextField Email_Result;
	private JLabel label_3;
	private JTextField Phone_Number_Result;
	private JTextField Username_Result;
	private JPasswordField passwordField_Result;
	byte[] person_image = null;
	private JDesktopPane desktopPane;
	private JLabel lbl_img;
	private JTextField txtUpdate;
	private JTextField BackButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudentInformation frame = new UpdateStudentInformation();
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
	public UpdateStudentInformation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(10, 28, 52));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtClose = new JTextField();
		txtClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		txtClose.setText("CLOSE");
		txtClose.setOpaque(false);
		txtClose.setHorizontalAlignment(SwingConstants.CENTER);
		txtClose.setForeground(Color.WHITE);
		txtClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtClose.setColumns(10);
		txtClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtClose.setBounds(508, 448, 128, 25);
		contentPane.add(txtClose);
		
		txtSearch = new JTextField();
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					Class.forName("org.sqlite.JDBC");

                    Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");

                    String sql ="select * from USERS where ID=?";
                    String FindID = ID_Search.getText();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1,FindID);
                    ResultSet set= pst.executeQuery();
                    
                    
                    
                    String add1 =set.getString("FIRST_NAME");
                    First_Name_Result.setText(add1);
                    
                    String add2 =set.getString("SECOND_NAME");
                    Last_Name_Result.setText(add2);
                    
                    String add3 =set.getString("EMAIL");
                    Email_Result.setText(add3);
                    
                    String add4 =set.getString("NUMBER");
                    Phone_Number_Result.setText(add4);
                    
                    String add5 =set.getString("USERNAME");
                    Username_Result.setText(add5);
                    
                    String add6 =set.getString("PASSWORD");
                    passwordField_Result.setText(add6);
                    
                    byte[] img = set.getBytes("IMAGE");
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH));
                    lbl_img.setIcon(imageIcon);
                    
                    
                    
                    
                    connection.close();
                    pst.close();
				 }catch(Exception e){
					 
			        }
					
					
			    }//GEN-LAST:event_jMenuItem1ActionPerformed

				
			});		txtSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSearch.setText("Search");
		txtSearch.setOpaque(false);
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setForeground(Color.WHITE);
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSearch.setColumns(10);
		txtSearch.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtSearch.setBounds(575, 17, 215, 25);
		contentPane.add(txtSearch);
		
		ID_Search = new JTextField();
		ID_Search.setColumns(10);
		ID_Search.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ID_Search.setBounds(305, 19, 237, 25);
		contentPane.add(ID_Search);
		
		lblEnterId = new JLabel("Enter ID");
		lblEnterId.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEnterId.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterId.setForeground(Color.WHITE);
		lblEnterId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEnterId.setBounds(10, 11, 243, 33);
		contentPane.add(lblEnterId);
		
		label_1 = new JLabel("First Name");
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setBounds(10, 55, 243, 33);
		contentPane.add(label_1);
		
		label_2 = new JLabel("Last Name");
		label_2.setHorizontalTextPosition(SwingConstants.CENTER);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_2.setBounds(10, 99, 243, 33);
		contentPane.add(label_2);
		
		First_Name_Result = new JTextField();
		First_Name_Result.setColumns(10);
		First_Name_Result.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		First_Name_Result.setBounds(305, 63, 237, 25);
		contentPane.add(First_Name_Result);
		
		Last_Name_Result = new JTextField();
		Last_Name_Result.setColumns(10);
		Last_Name_Result.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Last_Name_Result.setBounds(305, 107, 237, 25);
		contentPane.add(Last_Name_Result);
		
		Email_Result = new JTextField();
		Email_Result.setColumns(10);
		Email_Result.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Email_Result.setBounds(305, 151, 237, 25);
		contentPane.add(Email_Result);
		
		label_3 = new JLabel("Email");
		label_3.setHorizontalTextPosition(SwingConstants.CENTER);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_3.setBounds(10, 143, 243, 33);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Phone Number");
		label_4.setHorizontalTextPosition(SwingConstants.CENTER);
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_4.setBounds(10, 187, 243, 33);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Username");
		label_5.setHorizontalTextPosition(SwingConstants.CENTER);
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_5.setBounds(10, 231, 243, 33);
		contentPane.add(label_5);
		
		Phone_Number_Result = new JTextField();
		Phone_Number_Result.setColumns(10);
		Phone_Number_Result.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Phone_Number_Result.setBounds(305, 195, 237, 25);
		contentPane.add(Phone_Number_Result);
		
		Username_Result = new JTextField();
		Username_Result.setColumns(10);
		Username_Result.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Username_Result.setBounds(305, 239, 237, 25);
		contentPane.add(Username_Result);
		
		JLabel label_6 = new JLabel("Password");
		label_6.setHorizontalTextPosition(SwingConstants.CENTER);
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_6.setBounds(10, 275, 243, 33);
		contentPane.add(label_6);
		
		passwordField_Result = new JPasswordField();
		passwordField_Result.setEchoChar('*');
		passwordField_Result.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField_Result.setBounds(305, 279, 237, 25);
		contentPane.add(passwordField_Result);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(575, 60, 215, 226);
		contentPane.add(desktopPane);
		
		lbl_img = new JLabel();
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lbl_img, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addComponent(lbl_img, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(123, Short.MAX_VALUE))
		);
		desktopPane.setLayout(gl_desktopPane);
		
		txtUpdate = new JTextField();
		txtUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					
					 Class.forName("org.sqlite.JDBC");
					 Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
					 
					 
					 	String value1 = First_Name_Result.getText();
		             	String value2 = Last_Name_Result.getText();
		                String value3 = Email_Result.getText();
		                String value4 = Phone_Number_Result.getText();
		                String value5 = Username_Result.getText();
		                String value6 = passwordField_Result.getText();
		                String value7 = ID_Search.getText();
		                
		                
		                
		                String sql= "update USERS set id='"+value7+"',first_name='"+value1+"', SECOND_NAME='"+value2+"', "
		                        + "EMAIL='"+value3+"',password='"+value6+"',NUMBER='"+value4+"',USERNAME='"+value5+"'"
		                        + "where id='"+value7+"' ";
		                PreparedStatement pst=connection.prepareStatement(sql);
		                pst.executeUpdate();
		                
		                JOptionPane.showMessageDialog(null, "Database has been updated successfully");
		                pst.close();
		                connection.close();
				}catch(Exception e){System.out.println();
			}
		
			}
			
		});
		txtUpdate.setText("UPDATE");
		txtUpdate.setOpaque(false);
		txtUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		txtUpdate.setForeground(Color.WHITE);
		txtUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUpdate.setColumns(10);
		txtUpdate.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtUpdate.setBounds(216, 448, 128, 25);
		contentPane.add(txtUpdate);
		
		BackButton = new JTextField();
		BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
	           	 dispose();
	           	 
				 BetterLogin BL = new BetterLogin();
				 BL.setVisible(true);
					
				}
		});
		BackButton.setText("Back");
		BackButton.setOpaque(false);
		BackButton.setHorizontalAlignment(SwingConstants.CENTER);
		BackButton.setForeground(Color.WHITE);
		BackButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BackButton.setColumns(10);
		BackButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		BackButton.setBounds(24, 448, 80, 25);
		contentPane.add(BackButton);
		setUndecorated(true);
	}
}
