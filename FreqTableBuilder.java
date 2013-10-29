import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * Builds a letter frequency table for a text file. 
 * @author Kyle Dybdal
 * @version 2012.11.01
 *
 */


public class FreqTableBuilder {
	
	private static int[] theCounts;
	private static ArrayList<CharCount> theTable;
	/**
	 * get a table of frequencies from the input file
	 * 
	 * @param fileName the name of the file to analyze
	 * @return an ArrayList of CharCount objects.
	 * Each entry records a (unique) Character from the input text file 
	 * and the frequency of that Character in the file.
	 * The list is sorted in ascending order (by frequency).
	 */
	public static ArrayList<CharCount> getFrequencyTable(String fileName)
	{
		// count characters
		buildTable(fileName);
		// build ArrayList
		ArrayList<CharCount> theTable = new ArrayList<CharCount>();
		for(int i = 0; i < theCounts.length; i++)
		{
			if(theCounts[i] != 0)
			{
				theTable.add(new CharCount((char)i, theCounts[i]));
			}
		}
		// sort
		Collections.sort(theTable);
		// return
		return theTable;
	}
	
	private static void buildTable(String fileName)
	{
		theCounts = new int[65536];
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			int c = br.read();
			while(c != -1)
			{
				theCounts[c]++;
				c = br.read();
			}
			br.close();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "can't read file " + fileName + "\n" + e.getMessage());
		}
	}
}
