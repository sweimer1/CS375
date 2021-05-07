import java.io.IOException;

public class Submission {
	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out.println("Proper execution: \njava Submission <input_filename> <output_filename> <3>");
			System.exit(0);
		}
		if (!args[2].equals("3")) {
			System.out.println("Proper execution: \njava Submission <input_filename> <output_filename> <3>");
			System.exit(0);
		}
		ProblemSetup ps = new ProblemSetup(args[0], args[1]);
		ps.gatherData();
		ps.runInputs();
	}
}
