/*
    ADD YOUR HEADER HERE
 */

package assignment4;

import java.util.List;
import java.util.Stack;


// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    // delcare class members here.
	List history;
	Stack path;

    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	
	public WordLadderSolver()
	{
		history = new List;
		path = new Stack;
	}
    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
        path.push(startWord);
        history.add(startWord);
        makePath(startWord, endWord);
        List finalPath = path; 
        return finalPath;
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
        if(wordLadder.size()==1)
        {
        	return false;
        }
        else if(!wordLadder.get(wordLadder.size()-1).equals(endWord))
        {
        	return false;
        }
        else if((!wordLadder.get(0).equals(startWord)))
        {
        	return false;
        }
        else
        {
        	return true;
        }
    }

    // add additional methods here
    
    private void makePath(String start, String end)
    {
    	if(path.peek() ==end)
    	{
    		return
    	}
    	List temp = nextWords(path.peek()); //do I need to add a new here too?
    	List validNext = new ArrayList;
    	for(String word: temp)
    	{
    		if(!history.contains(word))
    		{
    			history.add(word);
    			validNext.add(word)
    		}
    	}
    	for(String word: validNext)
    	{
    		path.push(word);
    		makePath(start,end);
    	}
    	path.pop()
    	return
    }
    
    private List nextWords(String word)
    {
    	List wordsFound = new List;
    	String[] index0 = Dictionary.oneLetterDifference(word,0);
    	String[] index1 = Dictionary.oneLetterDifference(word,1);
    	String[] index2 = Dictionary.oneLetterDifference(word,2);
    	String[] index3 = Dictionary.oneLetterDifference(word,3);
    	String[] index4 = Dictionary.oneLetterDifference(word,4);
    	int totalLength = index0.length + index1.length() + index2.length() + index3+length() + index4.length();
    	String[] totalWords = index0 + index1 +index2 + index3 +index4;
    	for(String item: totalWords)
    	{
    		wordsFound.add(item);
    	}
    	return wordsFound;		
    }
}