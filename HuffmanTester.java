
import javax.swing.JOptionPane;

/**
 *Tests the Huffman Encoder and Huffman Decoder for CS261F12Lab10
 * 
 * @author Chuck Hommel
 * @version 2012.10.30
 */
public class HuffmanTester
{
    private String fName;  // name of the file to compress

    // mainline
    public static void main(String[] args)
    {
        HuffmanTester t = new HuffmanTester();
    }

    /**
     * Constructor for objects of class HuffmanTester
     */
    public HuffmanTester()
    {
        String[] options = {" Encode " , " Decode ", " Quit " };
        int quitChoice = 2;
        int choice = JOptionPane.showOptionDialog(null, "Select one", "Huffman Encoder/Decoder", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, null, options, null);
   //     System.out.println(choice);
        while(choice != JOptionPane.CLOSED_OPTION && choice != quitChoice)
        {
            fName = "";
            fName = JOptionPane.showInputDialog(null, " Enter the name of the file to process: ");
            if (fName != null)   // null means user hit cancel button
            {
                while (fName.equals(""))
                {
                    fName = JOptionPane.showInputDialog(null, " Enter the name of the file to process: ");
                }
            }
            if (choice == 0)
            {
         //       HuffmanEncoder.huffmanEncode(fName);
                HuffmanEncoderSolution.huffmanEncode(fName);
            }
            else
            {
                if (choice == 1)
                {
           //         HuffmanDecoder.huffmanDecode(fName);
                    HuffmanDecoderSolution.huffmanDecode(fName);

                }
            }
            choice = JOptionPane.showOptionDialog(null, "Select one", "Huffman Encoder/Decoder", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, null, options, null);
        }

    }
}