import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDesktopPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Cursor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JScrollBar;

import java.util.ArrayList;


public class FriendFinder extends JFrame {


	 JEditorPane contentPane;
	private JTextField txtHomepage;
	private JTextField Show_Email;
	private JLabel lblCourse;
	private JLabel lblEmail;
	private JLabel lblFirstName;
	private JTextField Show_First_Name;
	private JComboBox Hobbies_Search;
	private JComboBox Hobbies2_Search;
	private JLabel lbl_img;
	private JDesktopPane desktopPane;
	private ImageIcon format =null;
    String filename = null;
    byte[] person_image = null;
    private JTextField Show_Number;
    private JComboBox Course_Search;
    static int count=0;
    static int start=0;
    //NextButtonClass [] array = new NextButtonClass[5];
    ArrayList<NextButtonClass> array = new ArrayList<NextButtonClass>();
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriendFinder frame = new FriendFinder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public class NextButtonClass {

	     String Email;
	     String Name;
	     int PhoneNumber;
	     byte[] Getimg;

	    public NextButtonClass() {  // constructor 

	    }

	    public void setEmail(String Email) {
	        this.Email = Email;
	    }

	    public String getEmail() {
	        return Email;
	    }

	    public void setName(String Name) {
	        this.Name = Name;
	    }

	    public String getName() {
	        return Name;
	    }
	    public void setPhoneNumber(int PhoneNumber) {
	        this.PhoneNumber = PhoneNumber;
	    }

	    public int getPhoneNumber() {
	        return PhoneNumber;
	    }
	    
	    public void setGetimg(byte[] Getimg) {
	        this.Getimg = Getimg;
	    }

	    public byte[] getGetimg() {
	        return Getimg;
	    }
	    // and many more getter and setter methods ...
	}
	
	
	/**
	 * Create the frame.
	 */
	public FriendFinder() {
		
		contentPane = new JEditorPane();
		contentPane.setEditable(false);

		//Create object that implements HyperlinkListener
		//interface..

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(380, 50, 600, 620);
		contentPane = new JEditorPane();
		contentPane.setBackground(new Color(10, 28, 52));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image img = new ImageIcon (this.getClass().getResource("/Student.jpg")).getImage();
		
		JLabel lblHobbies = new JLabel("Hobbies");
		lblHobbies.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHobbies.setHorizontalAlignment(SwingConstants.LEFT);
		lblHobbies.setForeground(Color.WHITE);
		lblHobbies.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHobbies.setBounds(20, 27, 112, 33);
		contentPane.add(lblHobbies);
		
		JLabel label_1 = new JLabel("Hobbies");
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setBounds(20, 127, 112, 33);
		contentPane.add(label_1);

		Hobbies_Search = new JComboBox();
		Hobbies_Search.addItem("Football");
		Hobbies_Search.addItem("Basketball");
		Hobbies_Search.addItem("Jogging");
		Hobbies_Search.addItem("Tennis");
		Hobbies_Search.setSelectedItem(null);
		Hobbies_Search.setBounds(20, 84, 112, 20);
		contentPane.add(Hobbies_Search);
		
		
		Hobbies2_Search = new JComboBox();
		Hobbies2_Search.addItem("Lisening to Music");
		Hobbies2_Search.addItem("Writing");
		Hobbies2_Search.addItem("Reading");
		Hobbies2_Search.addItem("Partying");
		Hobbies2_Search.setSelectedItem(null);
		Hobbies2_Search.setBounds(20, 192, 120, 23);
		contentPane.add(Hobbies2_Search);
		

		Course_Search = new JComboBox();
		Course_Search.addItem("Computer Science");
		Course_Search.addItem("International Business");
		Course_Search.addItem("Engineering");
		Course_Search.setSelectedItem(null);
		Course_Search.setBounds(20, 306, 120, 23);
		contentPane.add(Course_Search);
		

		
		JTextField txtSearch = new JTextField();
		txtSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					count=0;
					start=0;
					Class.forName("org.sqlite.JDBC");

                    Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");

                    String sql ="select * from USERS where FIRST_HOBBY=? and SECOND_HOBBY=? and COURSE=?";
                    String FirstHobby=(String) Hobbies_Search.getSelectedItem();
                    String SecondHobby=(String)Hobbies2_Search.getSelectedItem();
                    String Course=(String)Course_Search.getSelectedItem();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1,FirstHobby);
                    pst.setString(2,SecondHobby);
                    pst.setString(3,Course);
                    ResultSet set= pst.executeQuery();
                    array.clear();
                    
                    while (set.next()) 
        	        {
        	           System.out.println(start);
                       String  Email = set.getString("EMAIL");
        	           String  Name = set.getString("FIRST_NAME");
        	           int  PhoneNumber  = set.getInt("NUMBER");
        	           byte[] Getimg = set.getBytes("IMAGE");
        	           System.out.println( "ID = " + Email );
        	           System.out.println( "NAME = " + Name );
        	           System.out.println( "Phone number = " + PhoneNumber );
        	           array.add(new NextButtonClass());
        	           //array[start] = new NextButtonClass();
        	           array.get(start).setEmail(Email);
        	           array.get(start).setName(Name);
        	           array.get(start).setPhoneNumber(PhoneNumber);
        	           array.get(start).setGetimg(Getimg);
        	           start++;   
        	        }
                    
                    String add1 = array.get(count).getEmail();
                    Show_Email.setText(add1);
                
                    String add2 = array.get(count).getName();
                    Show_First_Name.setText(add2);
                    
                    String add3 =String.valueOf(array.get(count).getPhoneNumber());
                    Show_Number.setText(add3);
                    
                    byte[] img = array.get(count).getGetimg();
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH));
                    lbl_img.setIcon(imageIcon);
                       
        	           
        	        count++;
                   /*
                
*/					
                    
                   
			 }catch(Exception e){
		            
		        }
				
				
		    }//GEN-LAST:event_jMenuItem1ActionPerformed

			
		});
		
	    JTextField txtNext = new JTextField();
	    txtNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (count == start)
                {
             	   count=0;
                }
				
				 String add1 = array.get(count).getEmail();
                 Show_Email.setText(add1);
             
                 String add2 = array.get(count).getName();
                 Show_First_Name.setText(add2);
                 
                 String add3 =String.valueOf(array.get(count).getPhoneNumber());
                 Show_Number.setText(add3);
                 
                 byte[] img = array.get(count).getGetimg();
                 ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH));
                 lbl_img.setIcon(imageIcon);
                count++;
                
				
			}
		});
		txtNext.setText("Next");
		txtNext.setOpaque(false);
		txtNext.setHorizontalAlignment(SwingConstants.CENTER);
		txtNext.setForeground(Color.WHITE);
		txtNext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNext.setColumns(10);
		txtNext.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtNext.setBounds(453, 396, 100, 25);
		contentPane.add(txtNext);

		


		
		txtSearch.setText("Search");
		txtSearch.setOpaque(false);
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setForeground(Color.WHITE);
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSearch.setColumns(10);
		txtSearch.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtSearch.setBounds(20, 396, 120, 25);
		contentPane.add(txtSearch);
		
		txtHomepage = new JTextField();
		txtHomepage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				 BetterLogin BL = new BetterLogin();
				 BL.setVisible(true);
			}
		});
		txtHomepage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtHomepage.setText("Go To HomePage");
		txtHomepage.setOpaque(false);
		txtHomepage.setHorizontalAlignment(SwingConstants.CENTER);
		txtHomepage.setForeground(Color.WHITE);
		txtHomepage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHomepage.setColumns(10);
		txtHomepage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtHomepage.setBounds(179, 396, 198, 25);
		contentPane.add(txtHomepage);
		
		JLabel lblContactInformation = new JLabel("Contact Information");
		lblContactInformation.setHorizontalTextPosition(SwingConstants.CENTER);
		lblContactInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblContactInformation.setForeground(Color.WHITE);
		lblContactInformation.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblContactInformation.setBounds(156, 27, 231, 33);
		contentPane.add(lblContactInformation);
		
		Show_Email = new JTextField();
		Show_Email.setColumns(10);
		Show_Email.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Show_Email.setBounds(170, 137, 181, 20);
		contentPane.add(Show_Email);
		
		lblCourse = new JLabel("Course");
		lblCourse.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCourse.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourse.setForeground(Color.WHITE);
		lblCourse.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCourse.setBounds(20, 240, 112, 33);
		contentPane.add(lblCourse);
		
		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail.setBounds(142, 74, 231, 33);
		contentPane.add(lblEmail);
		
		lblFirstName = new JLabel("First Name");
		lblFirstName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFirstName.setBounds(368, 251, 206, 33);
		contentPane.add(lblFirstName);
		
		Show_First_Name = new JTextField();
		Show_First_Name.setColumns(10);
		Show_First_Name.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Show_First_Name.setBounds(376, 306, 198, 20);
		contentPane.add(Show_First_Name);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(368, 11, 206, 226);
		contentPane.add(desktopPane);
		
		lbl_img = new JLabel();
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lbl_img, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lbl_img, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
		);
		desktopPane.setLayout(gl_desktopPane);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoneNumber.setForeground(Color.WHITE);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPhoneNumber.setBounds(156, 196, 206, 33);
		contentPane.add(lblPhoneNumber);
		
		Show_Number = new JTextField();
		Show_Number.setColumns(10);
		Show_Number.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Show_Number.setBounds(170, 250, 181, 20);
		contentPane.add(Show_Number);
		

	}
	
	

}
