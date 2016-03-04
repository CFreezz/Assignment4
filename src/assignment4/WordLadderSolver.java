/*
    ADD YOUR HEADER HERE
 */

package assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    // delcare class members here.
protected	List<String> history;
protected	Stack<String> path;

    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	
	public WordLadderSolver()
	{
		history = new ArrayList<String>();
		path = new Stack<String>();
	}
    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	path.clear();
    	history.clear();
        path.push(startWord);
        history.add(startWord);
        makePath(startWord, endWord);
        List<String> finalPath = path; 
        //System.out.println(path.size());
        //System.out.println(path);
        if(finalPath.size() <= 1){
        	throw new NoSuchLadderException("No Possible Word Ladder\n");
        }
        return finalPath;
  //      throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
   //     throw new UnsupportedOperationException("Not implemented yet!");
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
    
    private boolean makePath(String start, String end)
    {
    	//boolean flag = false;
    	if(path.peek().equals(end))
    	{
    	//	System.out.println("here");
    	//	path.push(end);
    		return true;
    	}
    	List<String> temp = nextWords(path.peek()); //do I need to add a new here too?
    	List<String> validNext = new ArrayList<String>();
 //   	System.out.println(path);
    	for(String word: temp)
    	{
    		if(!history.contains(word))
    		{
    			history.add(word);
    			validNext.add(word);
    		}
    	}
    	//System.out.println(validNext);
  //     	System.out.println(validNext);
		boolean check = false;
    	for(String word: validNext)
    	{
    		path.push(word);
    		check = makePath(start,end);
    		if(check)
    		{
    			return true;
    		}
    	}
    	if(!check)
    	{
    		path.pop();
    	}
    	return false;
    }
    
    private List<String> nextWords(String word)
    {
    	List<String> wordsFound = new ArrayList<String>();
    	List<String> index0 = Dictionary.oneLetterDifference(word,0);
    	List<String> index1 = Dictionary.oneLetterDifference(word,1);
    	List<String> index2 = Dictionary.oneLetterDifference(word,2);
    	List<String> index3 = Dictionary.oneLetterDifference(word,3);
    	List<String> index4 = Dictionary.oneLetterDifference(word,4);
    	//int totalLength = index0.length + index1.length + index2.length + index3.length + index4.length;
    	//String[] totalWords = addall(index0, index1, index2, index3, index4);
    	for(String value: index0)
    	{
    		wordsFound.add(value);
    	}
    	for(String value: index1)
    	{
    		wordsFound.add(value);
    	}
    	for(String value: index2)
    	{
    		wordsFound.add(value);
    	}
    	for(String value: index3)
    	{
    		wordsFound.add(value);
    	}
    	for(String value: index4)
    	{
    		wordsFound.add(value);
    	}
    	
    	//String[] totalWords = (String[])addAll(index0, index1, index2, index3, index4);
    	
    	return wordsFound;		
    }
}