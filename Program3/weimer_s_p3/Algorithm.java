import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Algorithm {
	private int mode;
	private String input = "";
	private String output = "";
	private ArrayList<Problem> problems = new ArrayList<Problem>();

	public Algorithm(String input, String output, String mode) {
		this.input = input;
		this.output = output;
		this.mode = Integer.parseInt(mode);
	}

	public void greedyAlgoOne(Problem p) throws IOException {
		File outfile = new File(output);
		FileWriter out = new FileWriter(outfile, true);
		int capacity = p.getCapacity();
		ArrayList<Integer> profits = p.getProfits();
		ArrayList<Integer> weights = p.getWeights();
		ArrayList<Double> ratios = p.getRatios();
		ArrayList<Integer> indexArray = p.getIndexes();
		long start = System.currentTimeMillis();
		int size = profits.size();
        for (int i = 1; i < size; i++) {//sort elements
            double key = ratios.get(i);
            int key2 = profits.get(i);
            int key3 = weights.get(i);
            int key4 = indexArray.get(i);
            int j = i - 1;
            while (j >= 0 && ratios.get(j) < key) {
//                arr[j + 1] = arr[j];
                ratios.set(j + 1, ratios.get(j));
                profits.set(j + 1, profits.get(j));
                weights.set(j + 1, weights.get(j));
                indexArray.set(j + 1, indexArray.get(j));
                j = j - 1;
            }
//            arr[j + 1] = key;
            ratios.set(j + 1, key);
            profits.set(j + 1, key2);
            weights.set(j + 1, key3);
            indexArray.set(j + 1, key4);
        }
//        System.out.println(weights.toString());
//        System.out.println(profits.toString());

        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int maxProf = 0;
        int currentWeight = 0;
        for (int i = 0; currentWeight < capacity && i < profits.size(); i++) {
        	if (currentWeight + weights.get(i) <= capacity) {
        		maxProf += profits.get(i);
        		indexes.add(indexArray.get(i));
        	}
        	//System.out.println(currentWeight);
        	currentWeight += weights.get(i);
		}
        long end = System.currentTimeMillis();
        long time = end - start;

        String indexString = indexes.toString().replace(",", "").replace("[", "").replace("]", "");
        out.write(profits.size() + " " + maxProf + " " + time + " " + indexString + "\n");
//        System.out.println(profits.size() + " " + maxProf + " " + time + " " + indexString);
        out.close();
	}

	public void gatherData() throws IOException {
		File infile = new File(input);
		Scanner sc = new Scanner(infile);
		//read first line to get knapsack size and number of elements
		//read amount of lines == number of elements
		//perform problem on these elements
		//read next line for knapsack size and number of elements of next problem (if there is any)
		//repeat
		int counter = 0;
		int index = 1;
		ArrayList<Double> ratios = new ArrayList<Double>();
		ArrayList<Integer> weights = new ArrayList<Integer>();
		ArrayList<Integer> profits = new ArrayList<Integer>();
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		Problem prob = new Problem();
		while (sc.hasNextLine()) {//need to print out the number of items, max_prof, runtime, and element indexes of items used
			String linestr = sc.nextLine();
			String[] line = linestr.split(" ");
			int first = Integer.parseInt(line[0]);
			int lineOneLength = line[0].length();
			String correction = linestr.substring(lineOneLength, linestr.length());
			correction = correction.replaceAll("[^0-9]", "");
			int second = Integer.parseInt(correction);
			//System.out.println(first + " " + second);
			if (counter == 0) {//if no more lines to be read
				if (profits.size() > 0) {//if there was data to be saved, save it
					prob.setProfits(profits);
					prob.setWeights(weights);
					prob.setRatios(ratios);
					prob.setIndexes(indexes);
					problems.add(prob);
					prob = new Problem();
				}
				profits = new ArrayList<Integer>();//reset data for next problem
				weights = new ArrayList<Integer>();
				ratios = new ArrayList<Double>();
				indexes = new ArrayList<Integer>();
				counter = first;
				index = 1;
				prob.setCapacity(second);
			} else {//if there are lines to collect data from
				weights.add(first);//collect it
				profits.add(second);
				ratios.add((double) second / (double) first);
				indexes.add(index);
				index++;
				counter--;
			}
		}
		prob.setProfits(profits);//add last one
		prob.setWeights(weights);
		prob.setRatios(ratios);
		prob.setIndexes(indexes);
		problems.add(prob);
		sc.close();
	}

	public void greedyAlgoTwo(Problem p) throws IOException {
		File outfile = new File(output);
		FileWriter out = new FileWriter(outfile, true);

		int capacity = p.getCapacity();//set up problem
		ArrayList<Integer> profits = p.getProfits();
		ArrayList<Integer> weights = p.getWeights();
		ArrayList<Double> ratios = p.getRatios();
		ArrayList<Integer> indexArray = p.getIndexes();
		long start = System.currentTimeMillis();

		int size = profits.size();//perform greedy algo 1
        for (int i = 1; i < size; i++) {//sort elements
            double key = ratios.get(i);
            int key2 = profits.get(i);
            int key3 = weights.get(i);
            int key4 = indexArray.get(i);
            int j = i - 1;
            while (j >= 0 && ratios.get(j) < key) {
//                arr[j + 1] = arr[j];
                ratios.set(j + 1, ratios.get(j));
                profits.set(j + 1, profits.get(j));
                weights.set(j + 1, weights.get(j));
                indexArray.set(j + 1, indexArray.get(j));
                j = j - 1;
            }
//            arr[j + 1] = key;
            ratios.set(j + 1, key);
            profits.set(j + 1, key2);
            weights.set(j + 1, key3);
            indexArray.set(j + 1, key4);
        }
//        System.out.println(weights.toString());
//        System.out.println(profits.toString());

        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int maxProf = 0;
        int currentWeight = 0;
        for (int i = 0; currentWeight < capacity && i < profits.size(); i++) {
        	if (currentWeight + weights.get(i) <= capacity) {
        		maxProf += profits.get(i);
        		indexes.add(indexArray.get(i));
        	}
        	//System.out.println(currentWeight);
        	currentWeight += weights.get(i);
		}

        int maxIndex = 0;
        int maxOfAll = 0;//get max profit of all element
        for (int i = 0; i < size; i++) {
        	if (profits.get(i) > maxOfAll) {
        		maxOfAll = profits.get(i);
        		maxIndex = indexArray.get(i);
        	}
        }

        if (maxOfAll > maxProf) {
        	maxProf = maxOfAll;
        	indexes.clear();
        	indexes.add(maxIndex);
        }

        long end = System.currentTimeMillis();
        long time = end - start;

        String indexString = indexes.toString().replace(",", "").replace("[", "").replace("]", "");
        out.write(profits.size() + " " + maxProf + " " + time + " " + indexString + "\n");
//        System.out.println(profits.size() + " " + maxProf + " " + time + " " + indexString);
        out.close();
	}

//	if ( weight <= C && profit > maxprofit)
//  	save better solution
//		maxprofit = profit // save new profit
//		numbest = i;
//		bestset = include; // save current solution
//	if promising(i)
//		include[i + 1] = yes;
//		knapsack(i + 1, profit + p[i + 1], weight + w[i + 1]);
//		include[i + 1] = no;
//		knapsack(i + 1, profit, weight);



	public void backTrackAlgo(Problem p) throws IOException {
		File outfile = new File(output);
		FileWriter out = new FileWriter(outfile, true);
		ArrayList<Integer> profits = p.getProfits();
		ArrayList<Integer> weights = p.getWeights();
		ArrayList<Double> ratios = p.getRatios();
		ArrayList<Integer> indexArray = p.getIndexes();
		long start = System.currentTimeMillis();
		int capacity = p.getCapacity();

		ArrayList<Integer> indexes = new ArrayList<Integer>();//indexes for answer
		int size = profits.size();//perform greedy algo 1
        for (int i = 1; i < size; i++) {//sort elements
            double key = ratios.get(i);
            int key2 = profits.get(i);
            int key3 = weights.get(i);
            int key4 = indexArray.get(i);
            int j = i - 1;
            while (j >= 0 && ratios.get(j) < key) {
//                arr[j + 1] = arr[j];
                ratios.set(j + 1, ratios.get(j));
                profits.set(j + 1, profits.get(j));
                weights.set(j + 1, weights.get(j));
                indexArray.set(j + 1, indexArray.get(j));
                j = j - 1;
            }
//            arr[j + 1] = key;
            ratios.set(j + 1, key);
            profits.set(j + 1, key2);
            weights.set(j + 1, key3);
            indexArray.set(j + 1, key4);
        }
//        System.out.println(weights.toString());
//        System.out.println(profits.toString());

        int maxProf = 0;
        int currentWeight = 0;
        for (int i = 0; currentWeight < capacity && i < profits.size(); i++) {
        	if (currentWeight + weights.get(i) <= capacity) {
        		maxProf += profits.get(i);
        		indexes.add(indexArray.get(i));
        	}
        	//System.out.println(currentWeight);
        	currentWeight += weights.get(i);
		}

        int maxOfAll = 0;//get max profit of all element
        for (int i = 0; i < size; i++) {
        	if (profits.get(i) > maxOfAll) {
        		maxOfAll = profits.get(i);
        	}
        }

        if (maxOfAll > maxProf) {
        	maxProf = maxOfAll;
        }
        p.setMaximumProfit(maxProf);

        p.knapsack(0, 0, 0);

		long end = System.currentTimeMillis();
        long time = end - start;

        for (int i = 0; i < p.getInclude().size(); i++) {
        	if (p.getInclude().get(i).equals("yes")) {
        		indexes.set(i, p.getBestSet().get(i));
//        		System.out.println(indexArray.get(i));
//        		System.out.println("check");
        	}
        }

		String indexString = indexes.toString().replace(",", "").replace("[", "").replace("]", "");
        out.write(profits.size() + " " + p.getMaximumProfit() + " " + time + " " + indexString + "\n");
//        System.out.println(profits.size() + " " + maxProf + " " + time + " " + indexString);
        out.close();
	}

	public void performAlgorithm() throws IOException {
		gatherData();
		if (mode == 0) {
			for (Problem p : problems) {
				greedyAlgoOne(p);
			}
		} else if (mode == 1) {
			for (Problem p : problems) {
				greedyAlgoTwo(p);
			}
		} else {
			for (Problem p : problems) {
				backTrackAlgo(p);
			}
		}
	}
}
