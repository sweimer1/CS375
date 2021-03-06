package program1;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Program1 {
	public static void main(String[] args) {
		try {
			File output = new File("output.txt");
			BruteForce b = new BruteForce();
			PrintStream s = new PrintStream(output);
			System.setOut(s);
			b.printMax("price_list.txt", "market_price.txt");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
