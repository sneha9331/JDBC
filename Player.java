package Oct_21;

public class Player {

	private int id;
	private String name;
	private String category;
	private int runs;
	private int wickets;
	private int catches;

	public Player() {
	}

	public Player(int id, String name, String category, int runs, int wickets, int catches) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.runs = runs;
		this.wickets = wickets;
		this.catches = catches;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getCatches() {
		return catches;
	}

	public void setCatches(int catches) {
		this.catches = catches;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", category=" + category + ", runs=" + runs + ", wickets="
				+ wickets + ", catches=" + catches + "]";
	}
}
