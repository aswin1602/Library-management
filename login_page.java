package library_management;
import java.util.*;
import java.sql.*;

public class login_page {
	public static void main(String[] args) throws Exception{
		 if (!security.authendication()) {
	            System.out.println("Access Denied");
	            System.exit(0);
	        }
		 System.out.println("Login Successful!");

	        Scanner sc = new Scanner(System.in);
	        while (true) {
	            System.out.println("\n1. Add Book");
	            System.out.println("2. View Books");
	            System.out.println("3. Issue Book");
	            System.out.println("4. Return Book");
	            System.out.println("5. Exit");

	            System.out.print("Choice: ");
	            int ch = sc.nextInt();

	            switch (ch) {
	                case 1: CRUD.addBook(); break;
	                case 2: CRUD.viewBooks(); break;
	                case 3: CRUD.issueBook(); break;
	                case 4: CRUD.returnBook(); break;
	                case 5: System.exit(0);
	                default: System.out.println("Invalid Choice");
		
	}

}}}
