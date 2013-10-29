import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 * Decodes text that has been run through a huffman encoder
 * @author Kyle Dybdal
 * @version 2012.12.05
 *
 */
public class HuffmanDecoder {
	private static String fName;                    	// Name of the file to process
	private static BufferedWriter bw;               	// The file to write. This is the decoded text. 
	private static BufferedReader br;               	// The file to read. This is the encoded text. 
	private static PriorityQueue<BinTree<CharCount>> q;	// Used to build the Huffman coding tree
	private static ArrayList<CharCount> freqTable;		//the frequency table
	private static int fileSize;                   	 	// size of the uncoded input file
	private static BinTree<CharCount> t;                   	// the Huffman coding tree
	private static int totalCharCount;					// total number of characters in the file;

	/**
	 * constructor
	 */
	public HuffmanDecoder()
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
	 * builds a tree based on the frequency table included in the .out file
	 */
	private static void buildTree()
	{
		//generate a new freqTable from the input file
		freqTable = new ArrayList<CharCount>();
		totalCharCount = 0;
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fName));
			String s = br.readLine();
			s = s + br.readLine();
			String origFile = fName.substring(0, (fName.length() - 4));
			
			if(s.equals("Source File: " + origFile + "Beginning of Coding Table"))
			{
				br.readLine();	
				String codeString = br.readLine();
				while(codeString.indexOf(",") > 0) //read until there are no more pairs
				{
					//split the line into the char and its count
					String[] codePair = codeString.split(",");
					int theChar = Integer.parseInt(codePair[0]);
					int theCount = Integer.parseInt(codePair[1]);
		
					//add them to the table
					freqTable.add(new CharCount((char)theChar, theCount));
					totalCharCount += theCount;
					codeString = br.readLine();
				}
			}
			
			// same build tree method as before
			q = new PriorityQueue<BinTree<CharCount>>(freqTable.size(), new CompareTrees());
			for(CharCount c : freqTable)
			{
				q.add(new BinTree<CharCount>(new BinNode<CharCount>(c)));
			}

			while(q.size() > 1)
			{
				BinTree<CharCount> leftTree = q.poll();
				BinTree<CharCount> rightTree = q.poll();

				int leftFreq = leftTree.getData().getFrequency();
				int rightFreq = rightTree.getData().getFrequency();

				int freqSum = leftFreq + rightFreq;
				BinTree<CharCount> bigTree = new BinTree<CharCount>(new CharCount(null, freqSum), leftTree, rightTree);

				q.add(bigTree);
			}

			t = q.poll(); // get the big tree from the queue
		}
		catch(IOException e)
		{
			e.getMessage();
		}
	}

	/**
	 * decodes the compressed text
	 */
	private static void decodeFile()
	{
		try
		{
			br = new BufferedReader(new FileReader(fName));
			bw = new BufferedWriter(new FileWriter(fName + ".decode"));
			String x = br.readLine();
			while(!x.equals("Beginning of encoded text"))	// get to the encoded text
			{
				x = br.readLine();
			}
			int bitCount = 0;
			BinNode<CharCount> theNode = t.root;
			int c = br.read();
			int tempCharCount = 0;
			while(tempCharCount < totalCharCount)	//until we have added all the characters
			{
				while(theNode.getData().getChar() == null) 	// while we're not in a leaf
				{
					int direction = c & 64;					// do we go left or right?
					if(direction == 64)						// go right
					{
						theNode = theNode.getRight();
						bitCount++;
					}
					else									// go left
					{
						theNode = theNode.getLeft();
						bitCount++;
					}
					if(bitCount == 7)	// once the 7 bits are finished
					{
						bitCount = 0;	// reset the count
						c = br.read();
					}
					else
					{
						c = c << 1;	// get the next direction
					}
				}
				theNode.getData();
				bw.write(theNode.getData().getChar());	// we hit a leaf! write it
				tempCharCount++;	// add one to the number of characters added
				theNode = t.root;		//go back to the top of the tree
			}
			br.close();
			bw.close();

		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * puts all the processes together
	 * @param theFile the file to process
	 */
	public static void huffmanDecode(String theFile)
	{
		fName = theFile;
		buildTree();
		decodeFile();
	}
	

}
