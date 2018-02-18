



import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.simple.parser.ParseException;

public class UniqueMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
		
	public void map(LongWritable key, Text record, Context context) throws IOException, InterruptedException
	{
		String tweet = record.toString();

			String[] listOfWords = splitTweet(tweet);
			ArrayList<String> testWords = filterWords(listOfWords);
			for(int i=0; i<testWords.size(); i++)
			{
				if(checkDuplicate(testWords.get(i),testWords,i))
				{
					context.write(new Text(testWords.get(i)), new IntWritable(1));
				}
			}
	}
	
	private String[] splitTweet(String tweet)
	{
		return tweet.split(" ");
	}
	
	private ArrayList<String> filterWords(String[] wordList)
	{
		ArrayList<String> realWords = new ArrayList<String>();
		for(int i=0; i<wordList.length; i++)
		{
			if(isWord(wordList[i]))
			{
				realWords.add(wordList[i]);
			}
		}
		return realWords;
	}
	
	private boolean checkDuplicate(String word, ArrayList<String> wordList, int index)
	{
		boolean isSingle = true;
		for(int i=0; i<wordList.size(); i++)
		{
			if(i > index)
			{
				if(word.equals(wordList.get(i)))
				{
	//				removeDuplicates(word, wordList);
					return false;
//					MapReduceProgram.getInstance().getIntermediatePairs().add(duplicateArr);
					
				}
			}
			
		}
		
		if(isSingle)
		{
			return isSingle;
//			MapReduceProgram.getInstance().getIntermediatePairs().add(uniqueArr);
		}
		return isSingle;
		
		
	}
	
	private void removeDuplicates(String word, ArrayList<String> wordList)
	{
		for(int i=0; i<wordList.size(); i++)
		{
			wordList.remove(word);
		}
	}
	
	public boolean isWord(String word)
	{
		boolean wordStatus = true;
		if(word.contains("#") || word.contains(":") || word.contains("/") || word.contains("@") || word.contains("?") || word.contains("*"))
		{
			wordStatus = false;
		}
		
		return wordStatus;
	}
	
	
	
}

