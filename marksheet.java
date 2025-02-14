import java.sql.*;
import java.util.Scanner;

public class marksheet {
    public static void main(String[] args) throws SQLException {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/marksheet";  // Use the correct database name here
        String user = "root";  // Replace with your MySQL username
        String password = "Mohit@1234";  // Replace with your MySQL password

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Take student details input
        System.out.print("Enter Roll Number: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Marks for Math: ");
        int mathMarks = scanner.nextInt();

        System.out.print("Enter Marks for Science: ");
        int scienceMarks = scanner.nextInt();

        System.out.print("Enter Marks for English: ");
        int englishMarks = scanner.nextInt();

        System.out.print("Enter Marks for History: ");
        int historyMarks = scanner.nextInt();

        System.out.print("Enter Marks for Geography: ");
        int geographyMarks = scanner.nextInt();

        // Calculate total marks
        int totalMarks = mathMarks + scienceMarks + englishMarks + historyMarks + geographyMarks;

        // SQL query to insert student data and marks into the student1 table
        String studentQuery = "INSERT INTO student1 (roll_no, name, math_marks, science_marks, english_marks, history_marks, geography_marks, total_marks) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Create a database connection
        Connection con = DriverManager.getConnection(url, user, password);

        // Create a prepared statement for inserting data
        PreparedStatement stmt = con.prepareStatement(studentQuery);
        stmt.setInt(1, rollNo);  // Set roll number
        stmt.setString(2, name);  // Set name
        stmt.setInt(3, mathMarks);  // Set Math marks
        stmt.setInt(4, scienceMarks);  // Set Science marks
        stmt.setInt(5, englishMarks);  // Set English marks
        stmt.setInt(6, historyMarks);  // Set History marks
        stmt.setInt(7, geographyMarks);  // Set Geography marks
        stmt.setInt(8, totalMarks);  // Set total marks

        // Execute the insert query
        stmt.executeUpdate();

        // SQL query to retrieve and display the marksheet for the student
        String query = "SELECT * FROM student1 WHERE roll_no = ?";

        // Create another prepared statement to retrieve the data
        PreparedStatement retrieveStmt = con.prepareStatement(query);
        retrieveStmt.setInt(1, rollNo);  // Use roll number to fetch the student's data

        // Execute the select query and retrieve results
        ResultSet rs = retrieveStmt.executeQuery();

        // Display the marksheet
        if (rs.next()) {
            System.out.println("\nMarksheet for Roll No: " + rs.getInt("roll_no"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Math: " + rs.getInt("math_marks"));
            System.out.println("Science: " + rs.getInt("science_marks"));
            System.out.println("English: " + rs.getInt("english_marks"));
            System.out.println("History: " + rs.getInt("history_marks"));
            System.out.println("Geography: " + rs.getInt("geography_marks"));
            System.out.println("Total Marks: " + rs.getInt("total_marks"));
        } else {
            System.out.println("No student found with Roll No: " + rollNo);
        }

        // Close resources
        stmt.close();
        retrieveStmt.close();
        con.close();
        scanner.close();
    }
}
