import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class loginform {
    public static void main(String[] args) throws SQLException {
//        DATABASE CONNECTION PARAMETERS
        String url = "jdbc:mysql://localhost:3306/login_form";
        String user = "root";
        String password = "Mohit@1234";
//        CREATE A SCANNER OBJECT FOR USER INPUT
        Scanner sc = new Scanner(System.in);
//        INFORMATION BY USER INPUT
        System.out.println("ENTER THE USER NAME :");
        String name = sc.nextLine();
        System.out.println("ENTER THE PASSWORD :");
        String password1 = sc.nextLine();
        System.out.println("ENTER THE EMAIL :");
        String email = sc.nextLine();
//        SQL QUERY TO INSERT USER RECORD
        String query = "INSERT INTO user_login(username, password, email) VALUES(?,?,?)";
//        ESTABLISHING CONNECTION TO THE DATABASE
        Connection con = DriverManager.getConnection(url,user,password);
        PreparedStatement pstmt = con.prepareStatement(query);
//        SET THE VALUES FOR THE PREPARED STATEMENT
        pstmt.setString(1,name);
        pstmt.setString(2,password1);
        pstmt.setString(3,email);
//        EXECUTE THE INSERT QUERY
        pstmt.executeUpdate();
//        CLOSE THE RESOURCE
        pstmt.close();
        con.close();
//        CLOSE THE SCANNER
//        scanner.close();
        System.out.println("DATA INSERT SUCCESSFULLY ");
    }
}
