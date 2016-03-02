package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
	protected Scanner input;
	protected Dictionary wordlist = new Dictionary();

	protected Input(String[] args) throws FileNotFoundException {
		System.out.println("Initializing Input File\n");
		if (args.length == 1) {

			try { // check if file exists, if yes, continue below
				input = new Scanner(new File(args[0]));

			} catch (FileNotFoundException e) { // file does not exist, handle
												// it
				System.out.println("Cannot find file " + args[0]);
				System.exit(0);
			}
		} else {
			System.out.println("Incorrect number of arguments");
		}
	}

	public boolean correctLine(String Line) {
		if (Line == null) {
			System.out.print("Line is empty: ");
			return false;
		}
		String[] Split = Line.split("\\s+");
		if (Split.length != 2) {
			System.out.print("Line contains too many or too few words: ");
			return false;
		}
		if (Split[0].length() != 5 || Split[1].length() != 5) {
			System.out.print("One or both of the words are not the correct length: ");
			return false;
		}
		if (!wordlist.inDictionary(Split[0]) || !wordlist.inDictionary(Split[1])) {
			System.out.print("One or both of these words are not in the dictionary: ");
			return false;
		}
		return true;
	}

	public String[] getLadderWords() throws NoSuchLadderException {
		if (input.hasNextLine()) {
			String line = input.nextLine();
			if (correctLine(line)) {
				String[] Split = line.split("\\s+");
				return Split;

			} else {
				throw new NoSuchLadderException("Ignoring Line");
			}

		} else {
			return null;
		}

	}

}
