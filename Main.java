import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/College";
        String user = "root";
        String password = "Mohit@1234";

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Get student details from user
        System.out.print("Enter Roll Number: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Attendance Status (Present/Absent): ");
        String attendance = scanner.nextLine();

        // SQL query to insert student record
        String query = "INSERT INTO Students (roll_no, name, attendance , marks) VALUES (?, ?, ?, ?)";

        // Establishing connection to the database
        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = con.prepareStatement(query);

        // Set the values for the PreparedStatement
        pstmt.setInt(1, rollNo);
        pstmt.setString(2, name);
        pstmt.setString(3, attendance);

        // Execute the insert query
        pstmt.executeUpdate();

        // Close the resources
        pstmt.close();
        con.close();

        // Close the scanner
        scanner.close();

        System.out.println("Student data inserted successfully!");
    }
}
