import java.io.*;
import java.util.Scanner;

public class Driver {
	public static void main (String args[]) throws Exception {
		FileReader file = new FileReader(args[1]);
		Scanner filescanner = new Scanner(file);
		while (filescanner.hasNextLine()) {
			String[] currentline = filescanner.nextLine().split(" ");
			System.out.println(currentline[0]);
		}
		filescanner.close();
	}
}
