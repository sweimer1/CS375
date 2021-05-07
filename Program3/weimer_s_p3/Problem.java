import java.util.ArrayList;

public class Problem {
	private ArrayList<Integer> weights = new ArrayList<Integer>();
	private ArrayList<Integer> profits = new ArrayList<Integer>();
	private ArrayList<Double> ratios = new ArrayList<Double>();
	private ArrayList<Integer> indexes = new ArrayList<Integer>();
	private ArrayList<Integer> bestSet = new ArrayList<Integer>();
	private ArrayList<String> include = new ArrayList<String>();
	private ArrayList<Double> x = new ArrayList<Double>();
	private int capacity;
	private int currentWeight;
	private int maximumProfit;
	private int bound;
	private boolean wasCalled = false;
	
	public ArrayList<Integer> getIndexes() {
		return indexes;
	}

	public void setIndexes(ArrayList<Integer> indexes) {
		this.indexes = indexes;
	}
	
	public ArrayList<String> getInclude() {
		return include;
	}
	
	public ArrayList<Integer> getBestSet() {
		return bestSet;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getMaximumProfit() {
		return maximumProfit;
	}

	public void setMaximumProfit(int mp) {
		this.maximumProfit = mp;
	}

	public ArrayList<Integer> getWeights() {
		return weights;
	}
	
	public void setWeights(ArrayList<Integer> weights) {
		this.weights = weights;
	}
	
	public ArrayList<Integer> getProfits() {
		return profits;
	}
	
	public void setProfits(ArrayList<Integer> profits) {
		this.profits = profits;
	}
	
	public ArrayList<Double> getRatios() {
		return ratios;
	}
	
	public void setRatios(ArrayList<Double> ratios) {
		this.ratios = ratios;
	}
	
	public void kwf2(int i, int weight, int profit, int w, int p, int C, int n) {
		bound = profit;
		for (int j = i; j < n; j++) {
			x.set(i, 0.0);
		}
		while ((currentWeight < C) && (i < n)) {
			if (currentWeight + w <= C) {
				x.set(i, 1.0);
				currentWeight += w;
				bound += p;
			} else {
				x.set(i, (double) (C - currentWeight) / (double) w);
				weight = C;
				bound += p*x.get(i);
			}
			i++;
		}
//		System.out.println("bound updated");
	}
	
	public void knapsack(int i, int profit, int weight) {
		if (i < profits.size()) {
			if (!wasCalled) {
				for (int j = 0; j < profits.size(); j++) {
					x.add(0.0);
					include.add("");
				}
				wasCalled = true;
			}
			if (weight <= capacity && profit > maximumProfit) {
				maximumProfit = profit;
				bestSet.add(i);
//				System.out.println("added");
			}
			if (currentWeight >= capacity) {
				return;
			}
			kwf2(i + 1, weight, profit, weights.get(i + 1), profits.get(i + 1), capacity, profits.size());
			if (bound <= maximumProfit) {
				return;
			}
			include.set(i + 1, "yes");
			knapsack(i + 1, profit + profits.get(i + 1), weight + weights.get(i + 1));
			include.set(i + 1, "no");
			knapsack(i + 1, profit, weight);
		}
	}
}
