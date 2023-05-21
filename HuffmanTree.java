// CSCI 211
// Brady Moore
// Student ID 010674564
// Program 3
// In keeping with the UM Honor Code, I have neither given nor received assistance from anyone other
// than the instructor.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class HuffmanTree implements HuffmanADT
{
    protected BinaryTreeNode<Name> root; 
    protected int modCount;
	private ArrayList<Name> popularNames;

    /**
     * Creates a Huffman tree using the character set and frequency distribution
     * given in the file with name passed as a parameter.
     * 
     */
    public HuffmanTree(String filename) throws FileNotFoundException {
        root = null;
    	popularNames = new ArrayList<Name>();

		// Read frequency popularity data from file to create list of Name objects
		// You need to complete this method
		processFile(filename);


		// Construct the Huffman tree using the list of Letters
		// This method is given
		build();
    }

	/**
	 * method: processFile
	 * Reads in popular US names and adds to popularNames list
	 * @param filename
	 */
	public void processFile(String filename) throws FileNotFoundException {
		//TODO:   Read Names from Data File and adds to popularNames list.

		Scanner scan1 = new Scanner(new File(filename));
		while(scan1.hasNextLine()) {
			String[] names = scan1.nextLine().split(",");
			String name = names[0].trim();
			Double rank = Double.valueOf(names[1]);
			String gender = names[2].trim();
			popularNames.add(new Name(name, rank, gender));


		}
	}

	/**
	 * method: build
	 * Constructs Huffman Tree
 	 */
    private void build() {
    		// Make leaf nodes in the tree/forest for each character
    		ArrayList<BinaryTreeNode<Name>> nodeList = new ArrayList<BinaryTreeNode<Name>>();
    		BinaryTreeNode<Name> temp = null;
    		for (int i=0; i<popularNames.size(); i++) {
    			temp = new BinaryTreeNode<Name>(new Name(popularNames.get(i)));
    			nodeList.add(temp);
    		}
    		/* Use standard idea of Huffman encoding to build tree from leaf nodes.
    		 * Repeatedly, find the two subtrees with minimum weight (least popular) and join them.
    		 * Internal nodes don't use the "name" or "gender" fields, so just make them a constant
    		 * (- for name and "none" for gender).  The rank, or frequency, used for the internal node is the
    		 * sum of the Ranks of the two children.  
    		 * Stop when one node left in forest--it is a tree!
    		 */
    		BinaryTreeNode<Name> node1, node2;
    		while (nodeList.size() > 1) {

    			//Get the 2 most popular names
      			node1 = getLeastPopular(nodeList);
    			node2 = getLeastPopular(nodeList);

    			Name internalElement = new Name("-", (node1.getElement().getRank() +
	                    node2.getElement().getRank()), "none");
    			BinaryTreeNode<Name> internal = new 
    					BinaryTreeNode<Name>(internalElement); 
    			internal.setRight(node1);
    			internal.setLeft(node2);
    			
    			nodeList.add(internal);
    		}
    		// The one remaining node is the root
    		root = nodeList.get(0);
    }

	/**
	 * method: getLeastPopular
	 * Private method that steps through list to get the lowest ranked name (i.e., least occurrences).
	 * Used to build Huffman Tree.
	 *
	 * @param nodes
	 * @return
	 */
    private BinaryTreeNode<Name> getLeastPopular(ArrayList<BinaryTreeNode<Name>> nodes) {
	     int min=0;   // index of min
    	
	     // Find the node in the forest with the least frequency
	     for (int i=1; i<nodes.size(); i++) {
	    	      if (nodes.get(i).getElement().getRank() < 
	    	    		  nodes.get(min).getElement().getRank()) {
	    	    	  		min = i;
	    	      }
	     }
	     // Save, remove, then return the smallest node
	     BinaryTreeNode<Name> leastPopular = nodes.get(min);
	     nodes.remove(min);
	     return leastPopular;
    }

	/**
	 * method: getRootElement
	 * Returns the root's element
	 *
 	 * @return
	 * @throws EmptyCollectionException
	 */
    public Name getRootElement() throws EmptyCollectionException {
        if (root == null)
        		throw new EmptyCollectionException("Huffman Tree");
        else return root.getElement();
    }

	/**
	 * method: getRootNode
	 * Returns the root's reference
	 *
	 * @return
	 * @throws EmptyCollectionException
	 */
	protected BinaryTreeNode<Name> getRootNode() throws EmptyCollectionException  {
        if (root == null)
        		throw new EmptyCollectionException("Huffman Tree");
        else return root;
    }

	/**
	 * method: getRight
	 * Returns "this" node's right child
	 *
	 * @return
	 */
	public BinaryTreeNode<Name> getRight() {
        return root.right;
    }

	/**
	 * method: getLeft
	 * Returns "this" node's left child
	 *
	 * @return
	 */
	public BinaryTreeNode<Name> getLeft() {
        return root.left;
    }

	/**
	 * method: isEmpty
	 * Returns true if the tree is empty
	 *
	 * @return
	 */
	public boolean isEmpty()  {
        return (root == null);
    }


	/**
	 * method: compressCodes
	 * This method returns an ArrayList of the codes in the Huffman Tree.
	 * The Compress class has a name and its corresponding encoding.  In the
	 * tree, right edges are designated as / and left edges as *.
	 *
	 * DO NOT CHANGE THIS METHOD, but you need to write the traverse method.
	 *
	 * @return
	 */
    public ArrayList<Compress> compressCodes() {
    		ArrayList<Compress> huffman = new ArrayList<>();
    		if (root == null) return null;
    		traverse(huffman, root.right, "/");
    		traverse(huffman, root.left, "*");
    		return huffman;
    }

    /**
	 * method: traverse
	 * Recursive method to traverse the Huffman tree.
	 * For each leaf node, add a Code record to the ArrayList.
     */
	String code;

    private void traverse(ArrayList<Compress> huffman, BinaryTreeNode<Name> node, String prefix) {
    	// TODO:  Fill in this method

    	if(isEmpty()) throw new EmptyCollectionException("Tree is Empty");
		code += prefix;
		if (node.getRight() != null && node.getLeft() != null){
			if(prefix == "/") {
				traverse(huffman, node.right, prefix);
			}
			else{
				traverse(huffman, node.left, prefix);
			}
		}
		else if(node.getRight() != null && node.getLeft() == null){
			code+= "/";
			traverse(huffman, node.right, "/");
		}
		else if (node.getRight() == null && node.getLeft() != null){
			code+= "*";
			traverse(huffman, node.left, "*");
		}
		else {
			huffman.add(new Compress(node.getElement(), code));
		}
		code = "";
    }

    /**
	 * method: encode
	 * The encode method should use the compressCodes method above to produce a look up
     * table.  Then, step through the parameter string and simply "look up" the code 
     * for each name, appending the code to the end of the encoded string.  A
     * sequential search through the ArrayList for each name is fine since 
     * even though there are 2000 names in the list.
	 *
	 * @return
     */
	public String encode(String str) throws InvalidHuffmanCodeException {
		// TODO: fill in this method
		String colon = "";
		String encoded = "";
		boolean good = false;

		String [] del = str.split(":");
		ArrayList<Compress> code = compressCodes();
		for(int i = 0; i <del.length; i++){
			for(Compress codes : code){
				if(codes.getName().equals(del[i])){
					encoded += codes.getCode() + colon;

					good = true;
				}
			}

		}
		if(good != true){
			throw new InvalidHuffmanCodeException();
		}



		return encoded;
	}

    /**
	 * method: decode
     * The decode method accepts a string of /'s and *'s and uses the Huffman
     * tree to determine the original string.  Because it is a prefix code, you
     * can start at the root of the Huffman tree and traverse right for '/' and left
     * for '*' until a leaf is reached.  The name associated with that code
     * (stored in the node) can be appended to the decoded string. Then,
     * reset back to the root and continue stepping through the huffString 
     * parameter until the end of the string is reached.
     *
	 * @return
     */
	public String decode(String huffString) throws InvalidHuffmanCodeException{
		// TODO: Fill in this method
		String decoded = "";
		int pos = 0;

		BinaryTreeNode<Name> current = root;

		while(pos <huffString.length()){
			if(huffString.charAt(pos) == '/'){

				current = current.getRight();
			}
			else if(huffString.charAt(pos) == '*'){

				current = current.getLeft();
			}
			else{
				throw new InvalidHuffmanCodeException();
			}

		}
		decoded = current.getElement().getName();
	
		return decoded;
	}

	/**
	 * method: toString
	 * Outputs name
	 * @return
	 */
	@Override
	public String toString() {
		String output = "";
		for(Name oneName: popularNames)
			output += oneName + ":";
		return output;
	}
}

