import java.io.*;
import java.util.Scanner;

public class Submission {
	public static void main (String args[]) throws Exception {
		if (args.length != 2) {
			System.out.println("Please execute using: java Submission <input_file.txt> <output_file.txt>");
		} else {
			FileReader file = new FileReader(args[0]);
			Scanner filescanner = new Scanner(file);
			PrintStream out = new PrintStream(new FileOutputStream(args[1]));
			System.setOut(out);
			int size = 0;
			if (filescanner.hasNextLine()) {
				String firstLine = filescanner.nextLine();
				if (Character.isDigit(firstLine.charAt(0))) {
					size = Integer.parseInt(firstLine);
				}
			}
			ExtendedHeap game = new ExtendedHeap(size);
			while (filescanner.hasNextLine()) {
				String[] currentline = filescanner.nextLine().split(" ");
				if (currentline[0].equals("findContestant")) {//if line is findContestant
					//System.out.println("findContestant");
					if (currentline.length != 2) {
						System.out.println("Error: findContestant takes 1 argument.");
					} else {
						System.out.println("findContestant " + currentline[1]);
						int input = Character.getNumericValue(currentline[1].charAt(1));
						game.findContestant(input);
					}
				} else if (currentline[0].equals("insertContestant")) {//if line is insertContestant
					//System.out.println("insertContestant");
					if (currentline.length != 3) {
						System.out.println("Error: insertContestant takes 2 arguments.");
					} else {
						System.out.println("insertContestant " + currentline[1] + " " + currentline[2]);
						String input1 = currentline[1].substring(1, currentline[1].length()-1);
						String input2 = currentline[2].substring(1, currentline[2].length()-1);
						int num1 = Integer.parseInt(input1);
						int num2 = Integer.parseInt(input2);
						game.insertContestant(num1, num2);
					}
				} else if (currentline[0].equals("eliminateWeakest")) {//if line is eliminateWeakest
					System.out.println("eliminateWeakest");
					game.eliminateWeakest();
				} else if (currentline[0].equals("earnPoints")) {//if line is earnPoints
					//System.out.println("earnPoints");
					if (currentline.length != 3) {
						System.out.println("Error: earnPoints takes 2 arguments.");
					} else {
						System.out.println("earnPoints " + currentline[1] + " " + currentline[2]);
						String input1 = currentline[1].substring(1, currentline[1].length()-1);
						String input2 = currentline[2].substring(1, currentline[2].length()-1);
						int num1 = Integer.parseInt(input1);
						int num2 = Integer.parseInt(input2);
						game.earnPoints(num1, num2);
					}
				} else if (currentline[0].equals("losePoints")) {//if line is losePoints
					//System.out.println("losePoints");
					if (currentline.length != 3) {
						System.out.println("Error: losePoints takes 2 arguments.");
					} else {
						System.out.println("losePoints " + currentline[1] + " " + currentline[2]);
						String input1 = currentline[1].substring(1, currentline[1].length()-1);
						String input2 = currentline[2].substring(1, currentline[2].length()-1);
						int num1 = Integer.parseInt(input1);
						int num2 = Integer.parseInt(input2);
						game.losePoints(num1, num2);
					}
				} else if (currentline[0].equals("showContestants")) {//if line is showContestants
					System.out.println("showContestants");
					game.showContestants();
				} else if (currentline[0].equals("showHandles")) {//if line is showHandles
					System.out.println("showHandles");
					game.showHandles();
				} else if (currentline[0].equals("showLocation")) {//if line is showLocation
					System.out.println("showLocation");
					if (currentline.length != 2) {
						System.out.println("Error: showLocation takes 1 argument.");
					} else {
						System.out.println("showLocation " + currentline[1]);
						int input = Character.getNumericValue(currentline[1].charAt(1));
						game.showLocation(input);
					}
				} else if (currentline[0].equals("crownWinner")) {//if line is crownWinner
					System.out.println("crownWinner");
					game.crownWinner();
				} else {
					System.out.println("Error processing command: " + currentline[0]);
				}
			}
			filescanner.close();
			out.close();
		}
	}
}
