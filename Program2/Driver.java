import java.io.*;
import java.util.Scanner;

public class Driver {
	public static void main (String args[]) throws Exception {
		if (args.length != 2) {
			System.out.println("Please execute using: java Driver <input_file.txt> <output_file.txt>");
		} else {
			FileReader file = new FileReader(args[0]);
			Scanner filescanner = new Scanner(file);
			while (filescanner.hasNextLine()) {
				String[] currentline = filescanner.nextLine().split(" ");
				if (Character.isDigit(currentline[0].charAt(0))) {//if line is a number
					ExtendedHeap game = new ExtendedHeap(Integer.parseInt(currentline[0]));//it must be the max size
					System.out.println(game.getHeapSizeLim());
				} else if (currentline[0].equals("findContestant")) {//if line is findContestant
					System.out.println("findContestant");
				} else if (currentline[0].equals("insertContestant")) {//if line is insertContestant
					System.out.println("insertContestant");
				} else if (currentline[0].equals("eliminateWeakest")) {//if line is eliminateWeakest
					System.out.println("eliminateWeakest");
				} else if (currentline[0].equals("earnPoints")) {//if line is earnPoints
					System.out.println("earnPoints");
				} else if (currentline[0].equals("losePoints")) {//if line is losePoints
					System.out.println("losePoints");
				} else if (currentline[0].equals("showContestants")) {//if line is showContestants
					System.out.println("showContestants");
				} else if (currentline[0].equals("showHandles")) {//if line is showHandles
					System.out.println("showHandles");
				} else if (currentline[0].equals("showLocation")) {//if line is showLocation
					System.out.println("showLocation");
				} else if (currentline[0].equals("crownWinner")) {//if line is crownWinner
					System.out.println("crownWinner");
				} else {
					System.out.println("Error processing command: " + currentline[0]);
				}
			}
			filescanner.close();
		}
	}
}
