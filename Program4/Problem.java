import java.util.ArrayList;

public class Problem {
	private ArrayList<Integer> profits = new ArrayList<Integer>();
	private ArrayList<Integer> weights = new ArrayList<Integer>();
	private int cap;
	
	public ArrayList<Integer> getProfits() {
		return profits;
	}
	
	public void setProfits(ArrayList<Integer> profits) {
		this.profits = profits;
	}
	
	public ArrayList<Integer> getWeights() {
		return weights;
	}
	
	public void setWeights(ArrayList<Integer> weights) {
		this.weights = weights;
	}
	
	public int getCap() {
		return cap;
	}
	
	public void setCap(int cap) {
		this.cap = cap;;
	}
}
