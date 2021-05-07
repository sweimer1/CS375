import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemSetup {
	private String input = "";
	private String output = "";
	public ArrayList<Problem> problems = new ArrayList<Problem>();
	
	public ProblemSetup(String input, String output) {
		this.input = input;
		this.output = output;
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
		ArrayList<Integer> weights = new ArrayList<Integer>();
		ArrayList<Integer> profits = new ArrayList<Integer>();
		Problem prob = new Problem();
		while (sc.hasNextLine()) {//need to print out the number of items, max_prof, runtime, and element indexes of items used
			String linestr = sc.nextLine();
			String[] line = linestr.split(" ");//sometimes line[1] might be a space if there are multiple spaces
			int first = Integer.parseInt(line[0]);//always the first element on a line
			int lineOneLength = line[0].length();//length of first element
			String correction = linestr.substring(lineOneLength, linestr.length()); //take the first element out of the line
			correction = correction.replaceAll("[^0-9]", "");//replace all non number chars with empty string
			int second = Integer.parseInt(correction);//what's left should be the second element
			if (counter == 0) {//if no more lines to be read
				if (profits.size() > 0) {//if there was data to be saved, save it
					prob.setProfits(profits);
					prob.setWeights(weights);
					problems.add(prob);
					prob = new Problem();
				}
				profits = new ArrayList<Integer>();//reset data for next problem
				weights = new ArrayList<Integer>();
				counter = first;
				prob.setCap(second);
			} else {//if there are lines to collect data from
				weights.add(first);//collect it
				profits.add(second);
				counter--;
			}
		}
		prob.setProfits(profits);//add last one
		prob.setWeights(weights);
		problems.add(prob);
		sc.close();
	}
	
	void dynamicProgram(Problem p) throws IOException {
		File outfile = new File(output);
		FileWriter out = new FileWriter(outfile, true);
		int[][] ptable = new int[2][p.getCap() + 1];
		int i = 0;
		int c = 0;
		long start = System.currentTimeMillis();
		for (; c < p.getCap(); c++) {//initialize table
			ptable[0][c] = 0;
		}
		for (; i < p.getProfits().size(); i++) {//compute items in row k+1, set row k to be the same as row k+1, delete row k+1, repeat
			ptable[1][0] = 0;
			for (c = 1; c < p.getCap() + 1; c++) {
				int w = p.getWeights().get(i);
				if ((w <= c) && (ptable[0][c - w] + p.getProfits().get(i) > ptable[0][c])) {
					ptable[1][c] = ptable[0][c - w] + p.getProfits().get(i);
				} else {
					ptable[1][c] = ptable[0][c];
				}
			}
			for (int j = 0; j < c; j++) {
				ptable[0][j] = ptable[1][j];
			}
		}
		long end = System.currentTimeMillis();
		long runtime = end - start;
		out.write(p.getProfits().size() + " " + ptable[1][p.getCap()] + " " + runtime + "\n");
		out.close();
	}
	
	void runInputs() throws IOException {
		File outfile = new File(output);
		FileWriter out = new FileWriter(outfile, true);
		out.write("Dynamic Programming: ");
		out.close();
		for (Problem p : problems) {
			dynamicProgram(p);
		}
	}
}
