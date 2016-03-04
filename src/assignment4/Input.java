package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
	protected Scanner input;
	protected Dictionary wordlist;// = new Dictionary();

	protected Input(String file, String dictionary) throws FileNotFoundException {
		System.out.println("Initializing Input File\n");
		wordlist = new Dictionary(dictionary);
			try { // check if file exists, if yes, continue below
				input = new Scanner(new File(file));

			} catch (FileNotFoundException e) { // file does not exist, handle
												// it
				System.out.println("Cannot find file " + file);
				System.exit(0);
			}

	}

	public boolean correctLine(String Line) {
		if (Line == null) {
			System.out.print("Line is empty: ");
			return false;
		}
		String[] Split = Line.split("\\s+");
		if (Split.length != 2) {
			System.out.print("Line does not contain two words: ");
			return false;
		}
		if (Split[0].length() != 5){
			System.out.print(Split[0] + " Is not the correct length: ");
			return false;
		}
		if (Split[1].length() != 5){
			System.out.print(Split[1] + " Is not the correct length: ");
			return false;
		}
		if (!Dictionary.inDictionary(Split[0])) {
			System.out.print(Split[0] + " is not in the dictionary: ");
			return false;
		}
		if (!Dictionary.inDictionary(Split[1])) {
			System.out.print(Split[1] + " is not in the dictionary: ");
			return false;
		}
		if (Split[0].equals(Split[1])) {
			System.out.print("These words are the same: ");
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
