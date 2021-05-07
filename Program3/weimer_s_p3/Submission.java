import java.io.IOException;

public class Submission {
	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out.println("Proper execution: \njava Submission <input_filename> <output_filename> <0/1/2>");
			System.exit(0);
		}
		Algorithm a = new Algorithm(args[0], args[1], args[2]);
		a.performAlgorithm();
	}
}
