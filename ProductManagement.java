
package Oct_19;

import java.sql.*;
import java.util.Scanner;

public class ProductManagement {

	static final String URL = "jdbc:mysql://localhost:3306/jdbc_1340";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

			System.out.println("Product System Connected!");

			while (true) {
				System.out.println("\n1. Insert Hard-coded");
				System.out.println("2. Insert Using Scanner");
				System.out.println("3. Update Price");
				System.out.println("4. Delete Product");
				System.out.println("5. Exit");
				System.out.print("Enter choice: ");

				int choice = sc.nextInt();

				switch (choice) {

				case 1: {
					String query = "INSERT INTO product VALUES (?, ?, ?, ?)";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setInt(1, 101);
						ps.setString(2, "Keyboard");
						ps.setDouble(3, 800);
						ps.setInt(4, 10);

						System.out.println(ps.executeUpdate() + " product inserted successfully");
					}
					break;
				}

				case 2: {
					System.out.print("Enter product ID: ");
					int id = sc.nextInt();
					sc.nextLine();

					System.out.print("Enter product name: ");
					String name = sc.nextLine();

					System.out.print("Enter price: ");
					double price = sc.nextDouble();

					System.out.print("Enter quantity: ");
					int quantity = sc.nextInt();

					String query = "INSERT INTO product VALUES (?, ?, ?, ?)";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setInt(1, id);
						ps.setString(2, name);
						ps.setDouble(3, price);
						ps.setInt(4, quantity);

						System.out.println(ps.executeUpdate() + " product inserted successfully");
					}
					break;
				}

				case 3: {
					System.out.print("Enter product ID: ");
					int id = sc.nextInt();

					System.out.print("Enter new price: ");
					double price = sc.nextDouble();

					String query = "UPDATE product SET price = ? WHERE id = ?";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setDouble(1, price);
						ps.setInt(2, id);

						int rows = ps.executeUpdate();
						System.out.println(rows > 0 ? "Price updated successfully" : "Product not found");
					}
					break;
				}

				case 4: {
					System.out.print("Enter product ID to delete: ");
					int id = sc.nextInt();

					String query = "DELETE FROM product WHERE id = ?";

					try (PreparedStatement ps = con.prepareStatement(query)) {

						ps.setInt(1, id);

						int rows = ps.executeUpdate();
						System.out.println(rows > 0 ? "Product deleted successfully" : "Product not found");
					}
					break;
				}

				case 5:
					System.out.println("Exiting Product System");
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
