package Oct_21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IPLDatabase {

	private static final String URL = "jdbc:mysql://localhost:3306/jdbc_1340";

	private static final String USER = "root";

	private static final String PASSWORD = "root";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public static void savePlayers(Map<String, List<Player>> teamMap) throws SQLException {

		String query = "INSERT INTO players " + "(id, name, category, runs, wickets, catches, team_name) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?) " + "ON DUPLICATE KEY UPDATE " + "name = VALUES(name), "
				+ "category = VALUES(category), " + "runs = VALUES(runs), " + "wickets = VALUES(wickets), "
				+ "catches = VALUES(catches), " + "team_name = VALUES(team_name)";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

			for (Map.Entry<String, List<Player>> entry : teamMap.entrySet()) {

				String team = entry.getKey();

				for (Player p : entry.getValue()) {

					ps.setInt(1, p.getId());
					ps.setString(2, p.getName());
					ps.setString(3, p.getCategory());
					ps.setInt(4, p.getRuns());
					ps.setInt(5, p.getWickets());
					ps.setInt(6, p.getCatches());
					ps.setString(7, team);

					ps.addBatch();
				}
			}

			int[] result = ps.executeBatch();

			System.out.println(result.length + " player records saved to database.");
		}
	}

	public static Map<String, List<Player>> fetchPlayers() throws SQLException {

		Map<String, List<Player>> teamMap = new HashMap<>();

		String query = "SELECT * FROM players";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				String category = rs.getString("category");
				int runs = rs.getInt("runs");
				int wickets = rs.getInt("wickets");
				int catches = rs.getInt("catches");
				String team = rs.getString("team_name");

				Player p = new Player(id, name, category, runs, wickets, catches);

				teamMap.computeIfAbsent(team, key -> new ArrayList<>()).add(p);
			}
		}

		return teamMap;
	}
}
