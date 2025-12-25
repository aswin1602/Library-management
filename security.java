package library_management;
import java.util.*;
import java.sql.*;

public class security {
	public static boolean authendication() throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter a user name");
		String user=sc.nextLine();
		System.out.println("enter a password");
		String password=sc.nextLine();
		Connection cn=demo.conneted();
		PreparedStatement ps=cn.prepareStatement("select *from librarian where username=? and password=?");
		
		ps.setString(1, user);
		ps.setString(2, password);
		ResultSet rs=ps.executeQuery();
		return rs.next();
		
	}

}
