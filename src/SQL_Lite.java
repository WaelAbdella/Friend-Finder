import java.sql.*;

 

public class SQL_Lite {

 

              public static void main(String[] args)

              {

                          

                           Connection c = null;

                           Statement stmt = null;

                          

                           try

                           {

                                         Class.forName("org.sqlite.JDBC");

                                         c = DriverManager.getConnection("jdbc:sqlite:testingDatabase.db");

                                        

                                         System.out.println("Database opened successfully");

                                        

                                         stmt = c.createStatement();

                          

                                         String sqlQuery = "CREATE TABLE IF NOT EXISTS USERS" +

                                                                                                  "(ID INT           PRIMARY KEY NOT NULL,"+

 																								  "USERNAME TEXT 						                          NOT NULL,"+
 
                                                                                                  "PASSWORD TEXT                        				        NOT NULL,"+
                                                                                                  
                                                                                                  "AGE INT                                                    NOT NULL,"+
                                                                                                  
                                                                                                  "FIRST_NAME TEXT                              			NOT NULL,"+

                                                                                                  "SECOND_NAME TEXT                                       NOT NULL,"+

                                                                                                  "EMAIL TEXT                                           NOT NULL,"+

                                                                                                  "FIRST_HOBBY TEXT                                  NOT NULL,"+

                                                                                                  "SECOND_HOBBY       TEXT                         NOT NULL,"+

                                                                                                  "THIRD_HOBBY TEXT                                         )";

                                        

 

             

                                         stmt.executeUpdate(sqlQuery);

                                        

                                         System.out.println("Table created successfully");

                                        

                                         sqlQuery = "INSERT INTO USERS(ID, USERNAME, PASSWORD, AGE, FIRST_NAME, SECOND_NAME, EMAIL, FIRST_HOBBY, SECOND_HOBBY, THIRD_HOBBY) VALUES (2,'WAEL','PASSWORD', 29,'Jack', 'Reacher', 'jr12@uowmail.edu.au', 'Cricket', 'Smashing', 'Smoking')";

                                         stmt.executeUpdate(sqlQuery);

                                         System.out.println(sqlQuery);

                                        

             

                                        

                                         stmt.close();

                                         c.close();

                           }

                           catch(Exception e)

                           {

                                         System.out.println(e.getMessage());

                                         System.exit(0);

                           }

 

              }

 

}












/*import java.sql.*;

public class SQL_Lite {

	public static void main(String[] args) 
	{
		 
		Connection c = null;
		Statement stmt = null;
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			System.out.println("Database opened successfully");
			
			stmt = c.createStatement();
			
			String sqlQuery = "CREATE TABLE IF NOT EXISTS USERS" +
							  "(ID INT 	PRIMARY KEY 		NOT NULL,"+
							  "AGE INT					NOT NULL,"+
							  "NAME TEXT					NOT NULL)";
			
			System.out.println(sqlQuery);
			
			stmt.executeUpdate(sqlQuery);
			
			System.out.println("Table created successfully");
			
			sqlQuery = "INSERT INTO USERS(ID, AGE, NAME) VALUES (2,29,'Jack')";
			stmt.executeUpdate(sqlQuery);
			System.out.println(sqlQuery);
			
			sqlQuery = "INSERT INTO USERS(ID, AGE, NAME) VALUES (3,19,'Rex')";
			stmt.executeUpdate(sqlQuery);
			System.out.println(sqlQuery);
			
			stmt.close();
			c.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}

	}

}
*/
