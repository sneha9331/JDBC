
package Oct_19;

import java.sql.*;
import java.util.Scanner;

public class StudentManagement {

	static final String URL = "jdbc:mysql://localhost:3306/jdbc_1340";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

			System.out.println("Student System Connected!");

			while (true) {
				System.out.println("\n1. Insert Hard-coded");
				System.out.println("2. Insert Using Scanner");
				System.out.println("3. Update Marks");
				System.out.println("4. Delete Student");
				System.out.println("5. Exit");
				System.out.print("Enter choice: ");

				int choice = sc.nextInt();

				switch (choice) {

				case 1: {
					String query = "INSERT INTO student VALUES (?, ?, ?, ?)";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setInt(1, 1);
						ps.setString(2, "Sneha");
						ps.setString(3, "Java");
						ps.setDouble(4, 85);

						System.out.println(ps.executeUpdate() + " student inserted successfully");
					}
					break;
				}

				case 2: {
					System.out.print("Enter student ID: ");
					int id = sc.nextInt();
					sc.nextLine();

					System.out.print("Enter name: ");
					String name = sc.nextLine();

					System.out.print("Enter course: ");
					String course = sc.nextLine();

					System.out.print("Enter marks: ");
					double marks = sc.nextDouble();

					String query = "INSERT INTO student VALUES (?, ?, ?, ?)";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setInt(1, id);
						ps.setString(2, name);
						ps.setString(3, course);
						ps.setDouble(4, marks);

						System.out.println(ps.executeUpdate() + " student inserted successfully");
					}
					break;
				}

				case 3: {
					System.out.print("Enter student ID: ");
					int id = sc.nextInt();

					System.out.print("Enter new marks: ");
					double marks = sc.nextDouble();

					String query = "UPDATE student SET marks = ? WHERE id = ?";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setDouble(1, marks);
						ps.setInt(2, id);

						int rows = ps.executeUpdate();
						System.out.println(rows > 0 ? "Marks updated successfully" : "Student not found");
					}
					break;
				}

				case 4: {
					System.out.print("Enter student ID to delete: ");
					int id = sc.nextInt();

					String query = "DELETE FROM student WHERE id = ?";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setInt(1, id);

						int rows = ps.executeUpdate();
						System.out.println(rows > 0 ? "Student deleted successfully" : "Student not found");
					}
					break;
				}

				case 5:
					System.out.println("Exiting Student System");
					return;

				default:
					System.out.println("Invalid choice");
				}
			}

		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		} finally {
			sc.close();
		}
	}
}
