package Oct_21;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainIPL {

	public static void main(String[] args) {

		List<Player> csk = new ArrayList<>();
		List<Player> mi = new ArrayList<>();
		List<Player> rcb = new ArrayList<>();

		// CSK players
		csk.add(new Player(1, "Ruturaj Gaikwad", "Batsman", 2500, 0, 20));
		csk.add(new Player(2, "MS Dhoni", "Wicketkeeper", 5200, 0, 140));
		csk.add(new Player(3, "Shivam Dube", "All-Rounder", 1800, 10, 15));
		csk.add(new Player(4, "Ravindra Jadeja", "All-Rounder", 2800, 120, 60));
		csk.add(new Player(5, "Deepak Chahar", "Bowler", 200, 70, 10));
		csk.add(new Player(6, "Devon Conway", "Batsman", 1600, 0, 12));
		csk.add(new Player(7, "Moeen Ali", "All-Rounder", 1100, 30, 18));
		csk.add(new Player(8, "Matheesha Pathirana", "Bowler", 20, 35, 3));
		csk.add(new Player(9, "Ajinkya Rahane", "Batsman", 4500, 0, 25));
		csk.add(new Player(10, "Tushar Deshpande", "Bowler", 50, 25, 5));

		// MI players
		mi.add(new Player(11, "Rohit Sharma", "Batsman", 6500, 0, 90));
		mi.add(new Player(12, "Ishan Kishan", "Wicketkeeper", 2500, 0, 45));
		mi.add(new Player(13, "Suryakumar Yadav", "Batsman", 3200, 0, 35));
		mi.add(new Player(14, "Hardik Pandya", "All-Rounder", 1800, 50, 40));
		mi.add(new Player(15, "Jasprit Bumrah", "Bowler", 100, 150, 15));
		mi.add(new Player(16, "Tilak Varma", "Batsman", 1200, 0, 10));
		mi.add(new Player(17, "Tim David", "Batsman", 700, 0, 12));
		mi.add(new Player(18, "Piyush Chawla", "Bowler", 600, 180, 30));
		mi.add(new Player(19, "Romario Shepherd", "All-Rounder", 250, 15, 8));
		mi.add(new Player(20, "Akash Madhwal", "Bowler", 10, 20, 2));

		// RCB players
		rcb.add(new Player(21, "Virat Kohli", "Batsman", 8000, 0, 100));
		rcb.add(new Player(22, "Rajat Patidar", "Batsman", 1000, 0, 12));
		rcb.add(new Player(23, "Dinesh Karthik", "Wicketkeeper", 4800, 0, 140));
		rcb.add(new Player(24, "Glenn Maxwell", "All-Rounder", 2700, 35, 50));
		rcb.add(new Player(25, "Mohammed Siraj", "Bowler", 100, 80, 15));
		rcb.add(new Player(26, "Faf du Plessis", "Batsman", 4500, 0, 45));
		rcb.add(new Player(27, "Cameron Green", "All-Rounder", 700, 10, 12));
		rcb.add(new Player(28, "Karn Sharma", "Bowler", 300, 70, 20));
		rcb.add(new Player(29, "Anuj Rawat", "Wicketkeeper", 500, 0, 15));
		rcb.add(new Player(30, "Yash Dayal", "Bowler", 10, 25, 3));

		Map<String, List<Player>> allIplTeamMap = new HashMap<>();

		allIplTeamMap.put("CSK", csk);
		allIplTeamMap.put("MI", mi);
		allIplTeamMap.put("RCB", rcb);

		try {
			// Step 1: Save the initial player data to MySQL
			IPLDatabase.savePlayers(allIplTeamMap);

			// Step 2: Fetch player data back from MySQL
			allIplTeamMap = IPLDatabase.fetchPlayers();

			System.out.println("\nPlayers fetched from database!");

		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
			return;
		}

		Set<String> teamNameKey = allIplTeamMap.keySet();
		Collection<List<Player>> allTeams = allIplTeamMap.values();

		List<Player> allPlayers = new ArrayList<>();

		for (List<Player> team : allTeams) {
			allPlayers.addAll(team);
		}

		// 1. Display all teams and players
		System.out.println("\n1. All Teams and Players");
		for (String team : teamNameKey) {
			System.out.println("\nTeam: " + team);
			for (Player p : allIplTeamMap.get(team)) {
				System.out.println(p);
			}
		}

		// 2. Display all batsmen
		System.out.println("\n2. All Batsmen");
		for (Player p : allPlayers) {
			if (p.getCategory().equalsIgnoreCase("Batsman")) {
				System.out.println(p);
			}
		}

		// 3. Display all bowlers
		System.out.println("\n3. All Bowlers");
		for (Player p : allPlayers) {
			if (p.getCategory().equalsIgnoreCase("Bowler")) {
				System.out.println(p);
			}
		}

		// 4. Display all all-rounders
		System.out.println("\n4. All All-Rounders");
		for (Player p : allPlayers) {
			if (p.getCategory().equalsIgnoreCase("All-Rounder")) {
				System.out.println(p);
			}
		}

		// 5. Display all wicketkeepers
		System.out.println("\n5. All Wicketkeepers");
		for (Player p : allPlayers) {
			if (p.getCategory().equalsIgnoreCase("Wicketkeeper")) {
				System.out.println(p);
			}
		}

		// 6. MI batsmen
		System.out.println("\n6. MI Batsmen");
		displayCategory(allIplTeamMap.get("MI"), "Batsman");

		// 7. MI bowlers
		System.out.println("\n7. MI Bowlers");
		displayCategory(allIplTeamMap.get("MI"), "Bowler");

		// 8. CSK batsmen
		System.out.println("\n8. CSK Batsmen");
		displayCategory(allIplTeamMap.get("CSK"), "Batsman");

		// 9. CSK bowlers
		System.out.println("\n9. CSK Bowlers");
		displayCategory(allIplTeamMap.get("CSK"), "Bowler");

		// 10. Players with more than 2000 runs
		System.out.println("\n10. Players with more than 2000 runs");
		for (Player p : allPlayers) {
			if (p.getRuns() > 2000) {
				System.out.println(p);
			}
		}

		// 11. Player with highest runs
		System.out.println("\n11. Highest Runs");
		Player highestRuns = allPlayers.get(0);
		for (Player p : allPlayers) {
			if (p.getRuns() > highestRuns.getRuns()) {
				highestRuns = p;
			}
		}
		System.out.println(highestRuns);

		// 12. Player with highest wickets
		System.out.println("\n12. Highest Wickets");
		Player highestWickets = allPlayers.get(0);
		for (Player p : allPlayers) {
			if (p.getWickets() > highestWickets.getWickets()) {
				highestWickets = p;
			}
		}
		System.out.println(highestWickets);

		// 13. Player with highest catches
		System.out.println("\n13. Highest Catches");
		Player highestCatches = allPlayers.get(0);
		for (Player p : allPlayers) {
			if (p.getCatches() > highestCatches.getCatches()) {
				highestCatches = p;
			}
		}
		System.out.println(highestCatches);

		// 14. Player with lowest runs
		System.out.println("\n14. Lowest Runs");
		Player lowestRuns = allPlayers.get(0);
		for (Player p : allPlayers) {
			if (p.getRuns() < lowestRuns.getRuns()) {
				lowestRuns = p;
			}
		}
		System.out.println(lowestRuns);

		// 15. Player with lowest wickets
		System.out.println("\n15. Lowest Wickets");
		Player lowestWickets = allPlayers.get(0);
		for (Player p : allPlayers) {
			if (p.getWickets() < lowestWickets.getWickets()) {
				lowestWickets = p;
			}
		}
		System.out.println(lowestWickets);

		// 16. Second-highest distinct runs
		System.out.println("\n16. Second-Highest Runs");
		int highest = Integer.MIN_VALUE;
		int secondHighest = Integer.MIN_VALUE;

		for (Player p : allPlayers) {
			int runs = p.getRuns();

			if (runs > highest) {
				secondHighest = highest;
				highest = runs;
			} else if (runs < highest && runs > secondHighest) {
				secondHighest = runs;
			}
		}

		if (secondHighest == Integer.MIN_VALUE) {
			System.out.println("Second-highest runs not available");
		} else {
			for (Player p : allPlayers) {
				if (p.getRuns() == secondHighest) {
					System.out.println(p);
				}
			}
		}

		// 17. Total runs of each team
		System.out.println("\n17. Total Runs of Each Team");
		for (String team : teamNameKey) {
			int totalRuns = 0;

			for (Player p : allIplTeamMap.get(team)) {
				totalRuns += p.getRuns();
			}

			System.out.println(team + " total runs = " + totalRuns);
		}

		// 18. Total wickets of each team
		System.out.println("\n18. Total Wickets of Each Team");
		for (String team : teamNameKey) {
			int totalWickets = 0;

			for (Player p : allIplTeamMap.get(team)) {
				totalWickets += p.getWickets();
			}

			System.out.println(team + " total wickets = " + totalWickets);
		}

		// 19. Team with highest total runs
		System.out.println("\n19. Team with Highest Total Runs");
		String bestTeam = "";
		int bestTeamRuns = -1;

		for (String team : teamNameKey) {
			int totalRuns = 0;

			for (Player p : allIplTeamMap.get(team)) {
				totalRuns += p.getRuns();
			}

			if (totalRuns > bestTeamRuns) {
				bestTeamRuns = totalRuns;
				bestTeam = team;
			}
		}

		System.out.println(bestTeam + " = " + bestTeamRuns + " runs");

		// 20. Count players in each team
		System.out.println("\n20. Player Count in Each Team");
		for (String team : teamNameKey) {
			System.out.println(team + " players = " + allIplTeamMap.get(team).size());
		}

		// 21. Count all batsmen
		System.out.println("\n21. Total Batsmen");
		int batsmanCount = 0;
		for (Player p : allPlayers) {
			if (p.getCategory().equalsIgnoreCase("Batsman")) {
				batsmanCount++;
			}
		}
		System.out.println(batsmanCount);

		// 22. Count all bowlers
		System.out.println("\n22. Total Bowlers");
		int bowlerCount = 0;
		for (Player p : allPlayers) {
			if (p.getCategory().equalsIgnoreCase("Bowler")) {
				bowlerCount++;
			}
		}
		System.out.println(bowlerCount);

		// 23. Search player by name
		System.out.println("\n23. Search Virat Kohli");
		boolean found = false;

		for (Player p : allPlayers) {
			if (p.getName().equalsIgnoreCase("Virat Kohli")) {
				System.out.println(p);
				found = true;
			}
		}

		if (!found) {
			System.out.println("Player not found");
		}

		// 24. Highest runs in MI
		System.out.println("\n24. Highest Runs in MI");
		List<Player> miPlayers = allIplTeamMap.get("MI");

		Player miHighestRuns = miPlayers.get(0);

		for (Player p : miPlayers) {
			if (p.getRuns() > miHighestRuns.getRuns()) {
				miHighestRuns = p;
			}
		}

		System.out.println(miHighestRuns);

		// 25. Category-wise player counts
		System.out.println("\n25. Category-wise Counts");
		Map<String, Integer> categoryCount = new HashMap<>();

		for (Player p : allPlayers) {
			String category = p.getCategory();

			categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
		}

		for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}

	// Reusable method to display a category
	public static void displayCategory(List<Player> players, String category) {

		for (Player p : players) {
			if (p.getCategory().equalsIgnoreCase(category)) {
				System.out.println(p);
			}
		}
	}
}
