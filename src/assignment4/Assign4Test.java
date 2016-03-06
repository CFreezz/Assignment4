package assignment4;

//Chris Friesen cmf2536
//Malvika Gupta mg42972
//Thursday 2pm 
//3/6/2016
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

public class Assign4Test {
	// test dictionary validity
	@Test
	public void testDictionary() throws FileNotFoundException {
		Dictionary dictionary = new Dictionary("A4words.dat");
		boolean result = dictionary.inDictionary("fears");
		assertEquals(true, result);
		result = dictionary.inDictionary("dodge");
		assertEquals(true, result);
		result = dictionary.inDictionary("");
		assertEquals(false, result);
		result = dictionary.inDictionary("blame");
		assertEquals(true, result);
		result = dictionary.inDictionary("Blame");
		assertEquals(false, result);
		result = dictionary.inDictionary("123678");
		assertEquals(false, result);
		result = dictionary.inDictionary("!@#$@");
		assertEquals(false, result);

	}

	// test ladderword functionality
	@Test
	public void testgetLadderWords() {
		Input in = null;
		try {
			in = new Input("getLadderWordstest.txt", "A4words.dat");
		} catch (FileNotFoundException e) {
			System.exit(0);
		}
		String[] output = { "test", "test" };
		while (output != null) {
			try {
				output = in.getLadderWords();
				if (output != null) {
					System.out.println(output[0] + " " + output[1]);
				}
			} catch (NoSuchLadderException e) {
				System.out.print("Exception\n");
			}
		}
		assertEquals(null, output);

	}

	// overall blackbox test of program using various words
	@Test
	public void testComputeLadder() throws FileNotFoundException {
		// Junit test = new Junit();
		Dictionary dictionary = new Dictionary("A4words.dat");

		Assignment4Interface wordLadderSolver = new WordLadderSolver(dictionary);
		List<String> result = null;
		boolean correct = false;
		String[] first = { "dears", "stone", "mumbo", "hello", "hello", "heads", "atlas" };
		String[] second = { "fears", "money", "ghost", "buddy", "world", "tails", "zebra" };
		boolean[] results = { true, true, false, true, false, true, false };
		int size = 7;

		for (int i = 0; i < size; i++) {
			try {
				result = wordLadderSolver.computeLadder(first[i], second[i]);
				correct = wordLadderSolver.validateResult(first[i], second[i], result);
			} catch (NoSuchLadderException e) {
				System.out.println(e.getMessage());
				correct = false;
			}
			assertEquals(results[i], correct);
		}

	}

}
