package program1;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Program1 {
	public static void main(String[] args) {
		if ((args.length == 4) && (args[0].equals("-m")) && (args[2].equals("-p"))) {
			try {
				File output = new File("output.txt");
				File check1 = new File(args[1]);
				File check2 = new File(args[3]);
				Scanner check1S = new Scanner(check1);
				Scanner check2S = new Scanner(check2);
				BruteForce b = new BruteForce();
				PrintStream s = new PrintStream(output);
				System.setOut(s);
				b.printMax(args[3], args[1]);
//			} catch (IOException e) {
//				System.out.println("Could not write to output.txt");
			} catch (FileNotFoundException e) {
				System.out.println("Specified file(s) not found");
			}
		} else if ((args.length == 4) && (args[0].equals("-p")) && (args[2].equals("-m"))) {
			try {
				File output = new File("output.txt");
				File check1 = new File(args[1]);
				File check2 = new File(args[3]);
				Scanner check1S = new Scanner(check1);
				Scanner check2S = new Scanner(check2);
				BruteForce b = new BruteForce();
				PrintStream s = new PrintStream(output);
				System.setOut(s);
				b.printMax(args[1], args[3]);
//			} catch (IOException e) {
//				System.out.println("Could not write to output.txt");
			} catch (FileNotFoundException e) {
				System.out.println("Specified file(s) not found");
			}
		} else {
			System.out.println("Invoke as follows:\n$ java program1/Program1 -m <market-price-file> -p <price-list-file>\n\nOR\n\n$ java program1/Program1 -p <price-list-file> -m <market-price-file>");
		}
	}
}
