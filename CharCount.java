/**
 * A class to hold a Character and an integer. 
 * This class is used to hold character/count pairs for building a Huffman coding tree
 * @author Kyle Dybdal
 * @version 2012.11.01
 *
 */
public class CharCount implements Comparable<CharCount>{

	private int frequency;
	private Character theChar;
	/**
	 * constructor 
	 * @param ch the character to store
	 */
	public CharCount(Character ch)
	{
		theChar = ch;
		frequency = 0;
	}
	
	/**
	 * constructor
	 * @param ch the character to store
	 * @param f the frequency of that character
	 */
	public CharCount(Character ch, int f)
	{
		theChar = ch;
		frequency = f;
	}
	
	/**
	 * compares frequencies
	 * @return < 0 if this frequency is lower than a's frequency , 
	 * >0 if this freq is greater than a's freq, 0 if frequencies are equal
	 * 
	 */
	public int compareTo(CharCount a)
	{
		if(frequency < a.getFrequency())
		{
			return -1;
		}
		else if(frequency > a.getFrequency())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	/**
	 * gets the character
	 * @return the character
	 */
	public Character getChar()
	{
		return theChar;
	}
	
	/**
	 * gets the frequency
	 * @return the frequency
	 */
	public int getFrequency()
	{
		return frequency;
	}
	
	/**
	 * sets the frequency
	 * @param f the frequency
	 */
	public void setCount(int f)
	{
		frequency = f;
	}
}
