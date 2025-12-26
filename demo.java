package library_management;
import java.sql.*;

public class demo {
	public static Connection conneted() throws Exception{
		String url="jdbc:mysql://localhost:3306/libraryDB";
		String user="root";
		String pass="root16";
	Connection con=DriverManager.getConnection(url, user, pass);
	
	
return con;
		 
	}

}

