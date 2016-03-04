package assignment4;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class Assign4Driver {
	public static void main(String[] args) throws FileNotFoundException {

		// Dictionary x = new Dictionary(); don't need dictionary in driver, but
		// here's how to define it
		
		// Input object will sort thru the lines of the file, needs to be passed dictionary file
		Input in = new Input(args[1], args[0]);
		// Initialize output to not null
		String[] output = { "Begin", "Ladder" };
		// Create a word ladder solver object
		Assignment4Interface wordLadderSolver = new WordLadderSolver();
		// when output is null, there is no next line
		while (output != null) {
			try {
				output = in.getLadderWords();
				if (output != null) {
					// print the two words
					System.out.println(output[0] + " " + output[1]);
					// solve the ladder
					List<String> result = wordLadderSolver.computeLadder(output[0], output[1]);
					boolean correct = wordLadderSolver.validateResult(output[0], output[1], result);


					if (correct) {
						System.out.println(result);// print it
					}
				

				}
				// exception should catch all errors
			} catch (NoSuchLadderException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} finally{
				System.out.println("**********");
			}
		}

	}
}
