import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Builds a Huffman coding tree Reads a text file, 
 * computes Huffman codes for the text 
 * and writes the characters and codes to a text file. 
 * Appends the encoded (compressed) text to the end of the output file.
 * @author Kyle Dybdal
 * @version 2012.12.05
 *
 */
public class HuffmanEncoder {
	
	private static BinTree<CharCount> t;                   // the Huffman coding tree
	private static ArrayList<CharCount> freqTable;         // list of characters and their frequencies
	private static String fName;                           // name of the file to process
	private static HashMap<Character,String> theCodes;     // HashMap associating a Character with its Huffman code

	/**
	 * constructor
	 */
	public HuffmanEncoder()
	{
		
	}
	
	/**
	 * private class used to compare two trees
	 * @author Kyle Dybdal
	 *
	 */
	private static class CompareTrees implements Comparator<BinTree<CharCount>>
	{
		/**
		 * @param left tree to compare
		 * @param right other thing to compare
		 * @return -1 if right is bigger than left, 1 if left is bigger than right, 0 if same
		 */
		public int compare(BinTree<CharCount> left, BinTree<CharCount> right)
		{
			if(left.getData().compareTo(right.getData()) < 0)
			{
				return -1;
			}
			else if(left.getData().compareTo(right.getData()) > 0)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	}
	
	/**
	 * put together all of the processes and encode a file
	 * @param fileName the file to encode
	 */
	public static void huffmanEncode(String fileName)
	{
		fName = fileName;
		ArrayList<CharCount> myArrayList = FreqTableBuilder.getFrequencyTable(fileName);
		getTable();
		buildTree();
		buildCodes();
		try
		{
			writeFile();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * gets the frequency table for this file
	 */
	private static void getTable()
	{
		freqTable = FreqTableBuilder.getFrequencyTable(fName);
	}
	
	/**
	 * build the huffman encoding tree
	 */
	private static void buildTree()
	{
		PriorityQueue<BinTree<CharCount>> theQ = new PriorityQueue<BinTree<CharCount>>(freqTable.size(), new CompareTrees());
		for(CharCount c : freqTable)
		{
			theQ.add(new BinTree<CharCount>(new BinNode<CharCount>(c)));
		}
		
		while(theQ.size() > 1)
		{
			// take the first two trees out of the queue
			BinTree<CharCount> leftTree = theQ.poll();
			BinTree<CharCount> rightTree = theQ.poll();

			int leftFreq = leftTree.getData().getFrequency();
			int rightFreq = rightTree.getData().getFrequency();
			
			int freqSum = leftFreq + rightFreq;
			// combine them, and make its root char null, and its freq the combination of its children
			BinTree<CharCount> bigTree = new BinTree<CharCount>(new CharCount(null, freqSum), leftTree, rightTree);

			theQ.add(bigTree);
		}
		
		t = theQ.poll(); // get the big tree from the queue
	}
	
	/**
	 * builds the codes
	 */
	private static void buildCodes()
	{
		theCodes = new HashMap<Character, String>();
		buildCodesHelper(t, "");
	}
	
	/**
	 * helper method used to recursively build the codes for each character
	 * @param theTree the tree to traverse
	 * @param code that character's code
	 */
	private static void buildCodesHelper(BinTree<CharCount> theTree, String code)
	{
		CharCount theData = theTree.getData();
		Character theChar = theData.getChar();

		if(theChar != null)
		{
			theCodes.put(theChar, code);
		}
		else
		{
			buildCodesHelper(theTree.getLeftSubtree(), code + "0"); 	// left child means 0, right means 1
			buildCodesHelper(theTree.getRightSubtree(), code + "1");	
		}
	}
	
	/**
	 * writes the file
	 * @throws IOException
	 */
	private static void writeFile() throws IOException
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fName));
			BufferedWriter wr = new BufferedWriter(new FileWriter(fName + ".out"));
			wr.write("Source File: " + fName);
			wr.newLine();
			wr.write("Beginning of Coding Table\n");
			wr.newLine();
			for(CharCount c : freqTable)
			{
				wr.write((int)c.getChar() + "," + c.getFrequency());
				wr.newLine();
			}
			wr.write("End of Coding Table");
			wr.newLine();
			wr.write("Beginning of encoded text");
			wr.newLine();
			int c = br.read();
			int code = 0;
			int bitsAdded = 0;
			while(c != -1)	//while there's still characters in the text
			{
				String codeString = theCodes.get((char)c); //get that char's code
				for(int i = 0; i < codeString.length(); i++)
				{
					bitsAdded = bitsAdded + 1;
					if(codeString.charAt(i) == '1')
					{
						code = code << 1;
						code = code + 1;
					}
					else
					{
						code = code << 1;
					}
					if(bitsAdded == 7) // make 7 bit codes out of the codes generated from the tree
					{
						wr.write((char)code);	// write the ascii character associated with those 7 bits
						code = 0;
						bitsAdded = 0;	
					}
				}
				
			c = br.read();
			}
			
			wr.newLine();
			wr.write("End of encoded text");
			br.close();
			wr.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
}
