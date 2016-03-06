//Chris Friesen cmf2536
//Malvika Gupta mg42972
// Thursday 2pm 
//3/6/2016

package assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface {
	// delcare class members here.
	// history of previous valid words
	// utilize stack template for the path of the solution
	protected List<String> history;
	protected Stack<String> path;
	protected Dictionary dictionary;

	public WordLadderSolver(Dictionary dict) {
		history = new ArrayList<String>();
		path = new Stack<String>();
		dictionary = dict;
	}

	// do not change signature of the method implemented from the interface
	@Override
	public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException {
		path.clear(); // initialize stack, and path
		history.clear();
		path.push(startWord);
		history.add(startWord);
		// call makepath recursive function for solution, stored in path
		makePath(startWord, endWord);
		List<String> finalPath = path;

		if (finalPath.size() <= 1) { // if the path is 1/0, then no ladder was
										// found, throw no ladder exception
			throw new NoSuchLadderException("No Possible Word Ladder");
		}
		return finalPath;

	}

	@Override
	public boolean validateResult(String startWord, String endWord, List<String> wordLadder) {
		// check the structure of the word ladder, to see if it is actually
		// valid
		if (wordLadder.size() == 1) {
			return false;
		} else if (!wordLadder.get(wordLadder.size() - 1).equals(endWord)) {
			return false;
		} else if ((!wordLadder.get(0).equals(startWord))) {
			return false;
		} else {
			return true;
		}
	}

	// recursive makePath, stores solution on stack path, only needs start and
	// end, dictionary is a member of the class
	private boolean makePath(String start, String end) {

		if (path.peek().equals(end)) { // base case the word is found, send the
										// true value up the recursive stack

			return true;
		}
		List<String> temp = nextWords(path.peek());
		// get the list of possible next words
		List<String> validNext = new ArrayList<String>();
		// add the words to the history, to ensure the path does not double up
		// on itself
		for (String word : temp) {
			if (!history.contains(word)) {
				history.add(word);
				validNext.add(word);
			}
		}

		boolean check = false;
		// call makePath on all the possible nextwords, propagate up true if
		// 'check' signals the end word is found
		for (String word : validNext) {
			path.push(word);
			check = makePath(start, end);
			if (check) {
				return true;
			}
		}
		// if the end word could not be found with these next words, return up
		// the chain and retry with the next possible word
		if (!check) {
			path.pop();
		}
		return false;
	}

	// nextword algorithm for finding next words, utililizes dictionary method
	// oneLetterDifference
	private List<String> nextWords(String word) {
		List<String> wordsFound = new ArrayList<String>();
		// get the lists of words that differ in each index 0-4
		List<String> index0 = dictionary.oneLetterDifference(word, 0);
		List<String> index1 = dictionary.oneLetterDifference(word, 1);
		List<String> index2 = dictionary.oneLetterDifference(word, 2);
		List<String> index3 = dictionary.oneLetterDifference(word, 3);
		List<String> index4 = dictionary.oneLetterDifference(word, 4);
		// add them all to the possible next words list
		for (String value : index0) {
			wordsFound.add(value);
		}
		for (String value : index1) {
			wordsFound.add(value);
		}
		for (String value : index2) {
			wordsFound.add(value);
		}
		for (String value : index3) {
			wordsFound.add(value);
		}
		for (String value : index4) {
			wordsFound.add(value);
		}

		return wordsFound;
	}
}