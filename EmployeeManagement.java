
package Oct_19;

import java.sql.*;
import java.util.Scanner;

public class EmployeeManagement {

	static final String URL = "jdbc:mysql://localhost:3306/jdbc_1340";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

			System.out.println("Employee System Connected!");

			while (true) {
				System.out.println("\n1. Insert Hard-coded");
				System.out.println("2. Insert Using Scanner");
				System.out.println("3. Update Salary");
				System.out.println("4. Delete Employee");
				System.out.println("5. Exit");
				System.out.print("Enter choice: ");

				int choice = sc.nextInt();

				switch (choice) {

				case 1: {
					String query = "INSERT INTO employee VALUES (?, ?, ?, ?)";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setInt(1, 3);
						ps.setString(2, "Omkar");
						ps.setString(3, "Dev");
						ps.setDouble(4, 88000);

						System.out.println(ps.executeUpdate() + " employee inserted successfully");
					}
					break;
				}

				case 2: {
					System.out.print("Enter ID: ");
					int id = sc.nextInt();
					sc.nextLine();

					System.out.print("Enter name: ");
					String name = sc.nextLine();

					System.out.print("Enter department: ");
					String dept = sc.nextLine();

					System.out.print("Enter salary: ");
					double salary = sc.nextDouble();

					String query = "INSERT INTO employee VALUES (?, ?, ?, ?)";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setInt(1, id);
						ps.setString(2, name);
						ps.setString(3, dept);
						ps.setDouble(4, salary);

						System.out.println(ps.executeUpdate() + " employee inserted successfully");
					}
					break;
				}

				case 3: {
					System.out.print("Enter employee ID: ");
					int id = sc.nextInt();

					System.out.print("Enter new salary: ");
					double salary = sc.nextDouble();

					String query = "UPDATE employee SET salary = ? WHERE id = ?";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setDouble(1, salary);
						ps.setInt(2, id);

						int rows = ps.executeUpdate();
						System.out.println(rows > 0 ? "Salary updated successfully" : "Employee not found");
					}
					break;
				}

				case 4: {
					System.out.print("Enter employee ID to delete: ");
					int id = sc.nextInt();

					String query = "DELETE FROM employee WHERE id = ?";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setInt(1, id);

						int rows = ps.executeUpdate();
						System.out.println(rows > 0 ? "Employee deleted successfully" : "Employee not found");
					}
					break;
				}

				case 5:
					System.out.println("Exiting Employee System");
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
