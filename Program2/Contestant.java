
public class Contestant {
	private int totalPoints = 0;
	private int ID;
//	private int index;
	
	public Contestant(int ID, int totalPoints) {
		this.ID = ID;
		this.totalPoints = totalPoints;
	}
	
	public int getTotalPoints() {
		return totalPoints;
	}
	
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	public void addPoints(int points) {
		this.totalPoints += points;
	}
	
	public void subtractPoints(int points) {
		if (totalPoints <= points) {
			this.totalPoints = 0;
		} else {
			this.totalPoints -= points;
		}
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
//	public int getIndex() {
//		return index;
//	}
//	public void setIndex(int index) {
//		this.index = index;
//	}
}
