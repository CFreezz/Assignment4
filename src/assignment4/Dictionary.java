package assignment4;

//Chris Friesen cmf2536
//Malvika Gupta mg42972
//Thursday 2pm 
//3/6/2016
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
	// uses a hashtable to store the dictionary
	protected static Hashtable<String, Integer> dictionaryHash;
	protected String filename;

	// constructor takes dictionary from the filename
	// it then sorts into a hashtbale, with load factor of .5 to optimize speed
	// over data storage
	public Dictionary(String args) throws FileNotFoundException {
		filename = args;
		dictionaryHash = new Hashtable<String, Integer>(10, (float) .50);
		Scanner input;
		// use the input class to parse each line, then valid word to see if
		// that word should be added
		// to the hashtable
		try {
			input = new Scanner(new File(filename));
			String word;
			String line;
			int index = 0;
			while (input.hasNextLine()) {
				line = input.nextLine();
				word = validWord(line);
				if (word != null) {
					dictionaryHash.put(word, index);
					index++;
				}
			}
		} catch (FileNotFoundException e) { // file couldn't be found
			System.out.println("Cannot find dictionary " + filename);
			System.exit(0);
		}
	}

	// a word is valid if the first 5 characters are letters
	protected String validWord(String line) {
		for (int i = 0; i < 5; i++) {
			if (!Character.isLetter(line.charAt(i))) {
				return null;
			}
		}
		return line.substring(0, 5);
	}

	//
	// public method inDictionary, returns boolean of whether a string is in the
	// dictionary
	//
	//
	public boolean inDictionary(String word) {
		Integer check = dictionaryHash.get(word);
		if (check == null) {
			return false;
		}
		return true;
	}

	//
	// public method oneLetterDifferece, return a list of strings that differ in
	// one letter
	// at the given index, returns null if incorrect index
	//
	static char alphabet[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public List<String> oneLetterDifference(String word, int index) {
		if (index < 0 || index > 4) {
			System.err.println("Index must be between 0 and 4");
			return null;
		}
		char charWord[] = word.toCharArray();
		String temp;
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < 26; i++) {
			charWord[index] = alphabet[i];
			temp = new String(charWord);
			if (inDictionary(temp)) {
				if (!temp.equals(word)) {
					result.add(temp);
				}
			}
		}
		return result;
	}

}
