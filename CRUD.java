package library_management;
import java.util.*;
import java.sql.*;
import java.sql.Date;


public class CRUD {
	public static void addBook() throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.print("title :");
		String title=sc.nextLine();
		System.out.print("Author :");
		String author=sc.nextLine();
		
		Connection cn=demo.conneted();
		PreparedStatement ps=cn.prepareStatement("INSERT INTO books(title, author) VALUES(?,?)");
		ps.setString(1, title);
		ps.setString(2, author);
		
		int rs=ps.executeUpdate();
		System.out.println("Book added Succesfully");
		cn.close();
    }

    public static void viewBooks() throws Exception {
        System.out.println("View Books Logic");
        Connection con = demo.conneted();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM books");

        while (rs.next()) {
            System.out.println(
                rs.getInt("book_id") + " | " +
                rs.getString("title") + " | " +
                rs.getString("author") 
              
            );
        }}
        

    public static void issueBook() throws Exception{
    	Connection con = demo.conneted(); 
    	Scanner sc = new Scanner(System.in); 
    	System.out.print("Book id: "); 
    	int bookid = sc.nextInt(); 
    	PreparedStatement check = con.prepareStatement(
    		    "SELECT * FROM books WHERE book_id=?"
    		);
    		check.setInt(1, bookid);
    		ResultSet rs = check.executeQuery();

    		if (!rs.next()) {
    		    System.out.println("Book id not found");
    		    return;
    		}

    	System.out.print("Student id: "); 
    	int studentid = sc.nextInt();
    	
    	PreparedStatement ps = con.prepareStatement( "INSERT INTO issued_books(book_id,student_id,issue_date) VALUES(?,?,CURDATE())" ); 
    	ps.setInt(1, bookid);
    	ps.setInt(2, studentid);
    	ps.executeUpdate();
     
    	con.close();
    }

    public static void returnBook() throws Exception{
    	   Scanner sc = new Scanner(System.in);
           System.out.print("book id: ");
           int issueId = sc.nextInt();
           System.out.print("Student id: ");
           int studentId = sc.nextInt();
           Connection con = demo.conneted();

           PreparedStatement check = con.prepareStatement(
               "SELECT issue_date FROM issued_books WHERE book_id=? AND student_id=? AND return_date IS NULL"
           );
           check.setInt(1, issueId);
           check.setInt(2, studentId);

           ResultSet rs = check.executeQuery();

           if (!rs.next()) {
               System.out.println("Book not issued to this student or already returned");
               return;
           }


           Date issueDate = rs.getDate("issue_date");

           long diff = System.currentTimeMillis() - issueDate.getTime();
           long days = diff / (1000 * 60 * 60 * 24);

           int fine = 0;
           if (days > 7) {
               fine = (int)(days - 7) * 10;
           }

           PreparedStatement ps = con.prepareStatement(
               "UPDATE issued_books SET return_date=CURDATE(), fine=? WHERE book_id=?"
           );
           ps.setInt(1, fine);
           ps.setInt(2, issueId);
           ps.executeUpdate();

           System.out.println("Book returned successfully");
           System.out.println("Fine amount: ₹" + fine);

           con.close();
            }

}
