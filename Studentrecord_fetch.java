
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Studentrecord_fetch {
    public static void main(String[] args) throws SQLException {
        // DATABASE CONNECTION PARAMETERS
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "root";
        String password = "Mohit@1234";

        // CREATE A SCANNER OBJECT FOR USER INPUT
        Scanner sc = new Scanner(System.in);

        // INFORMATION BY USER INPUT
        System.out.println("ENTER THE ROLL NO OF THE STUDENT TO UPDATE RECORD:");
        int rollnoToUpdate = sc.nextInt();
        sc.nextLine();  // Consume the newline left by nextInt()

        // Fetch existing data to show current record before update
        String fetchQuery = "SELECT * FROM studentrecord WHERE ROLL_NO = ?";
        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmtFetch = con.prepareStatement(fetchQuery);
        pstmtFetch.setInt(1, rollnoToUpdate);

        ResultSet rs = pstmtFetch.executeQuery();

        if (rs.next()) {
            System.out.println("Existing Record:");
            System.out.println("STUDENT NAME: " + rs.getString("STUDENT_NAME"));
            System.out.println("FATHER NAME: " + rs.getString("FATHERS_NAME"));
            System.out.println("MOTHER NAME: " + rs.getString("MOTHERS_NAME"));
            System.out.println("AGE: " + rs.getInt("STUDENT_AGE"));
            System.out.println("ROLL NO: " + rs.getInt("ROLL_NO"));

            // Ask for new data to update
            System.out.println("ENTER NEW STUDENT NAME:");
            String STUDENT = sc.nextLine();
            System.out.println("ENTER NEW FATHER NAME:");
            String FATHER = sc.nextLine();
            System.out.println("ENTER NEW MOTHER NAME:");
            String MOTHER = sc.nextLine();
            System.out.println("ENTER NEW AGE:");
            int age = sc.nextInt();
            sc.nextLine();  // Consume the newline after nextInt()

            // SQL QUERY TO UPDATE USER RECORD
            String updateQuery = "UPDATE studentrecord SET STUDENT_NAME = ?, FATHERS_NAME = ?, MOTHERS_NAME = ?, STUDENT_AGE = ? WHERE ROLL_NO = ?";
            PreparedStatement pstmtUpdate = con.prepareStatement(updateQuery);

            // Set the updated values for the prepared statement
            pstmtUpdate.setString(1, STUDENT);
            pstmtUpdate.setString(2, FATHER);
            pstmtUpdate.setString(3, MOTHER);
            pstmtUpdate.setInt(4, age);
            pstmtUpdate.setInt(5, rollnoToUpdate);

            // EXECUTE THE UPDATE QUERY
            int rowsUpdated = pstmtUpdate.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("RECORD UPDATED SUCCESSFULLY.");
            } else {
                System.out.println("No record found with roll number: " + rollnoToUpdate);
            }

            pstmtUpdate.close();
        } else {
            System.out.println("No record found for roll number: " + rollnoToUpdate);
        }

        // CLOSE THE RESOURCES
        rs.close();
        pstmtFetch.close();
        con.close();

        // CLOSE THE SCANNER
        sc.close();
    }
}
