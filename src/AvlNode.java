package avl_grid;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class AvlNode extends AvlTree {
	public AvlNode left;
	 public AvlNode right;
	 public AvlNode parent;
	 public int key;
	 public int balance;
	 
	 
	 public int posX,posY;
	 public JLabel lbl;

	 public AvlNode(int k) {
	  left = right = parent = null;
	  balance = 0;
	  key = k;
	  
	  x=posX;
	  y=posY;
	  lbl = new JLabel(new ImageIcon("back.png"));
	  lbl.setBounds(x, y, 40, 40);
	
	 }
	 public String toString() {
	  return "" + key;
	 }
	 public AvlNode getNode(AvlNode k){
		 return k;
	 }
	 }