package avl_grid;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class AvlTree extends Main {
 
protected static AvlNode root; 
static int x;
static int y;

static int lvl;
static AvlTree agac = new AvlTree();
Graphics g = jf.getGraphics();

// TEMEL AVL AÐACI KAYNAK KODU: http://blog.blackbam.at/2012/05/04/avl-tree-implementation-in-java/
 
	/***************************** Core Functions ************************************/

	 /**
	  * Add a new element with key "k" into the tree.
	  * 
	  * @param k
	  *            The key of the new node.
	  */
	 public void insert(int k) {
	  // create new node
	  AvlNode n = new AvlNode(k);
	  // start recursive procedure for inserting the node
	  insertAVL(this.root,n);
	 }
	 
	 /**
	  * Recursive method to insert a node into a tree.
	  * 
	  * @param p The node currently compared, usually you start with the root.
	  * @param q The node to be inserted.
	  */
	 public void insertAVL(AvlNode p, AvlNode q) {
	  // If  node to compare is null, the node is inserted. If the root is null, it is the root of the tree. 
		 if(p==null) {
	   this.root=q;
	  
	   
	   q.posY=1;
	   q.posX=16;
	  
	   x= dizi[q.posY][q.posX].getX();
	   y= dizi[q.posY][q.posX].getY();
	   
	   q.lbl = new JLabel(new ImageIcon("back.png"));
	   q.lbl.setText(Integer.toString(q.key));
	   q.lbl.setBounds(x, y, 40, 40);
	   q.lbl.setVerticalTextPosition(JLabel.CENTER);
	   q.lbl.setHorizontalTextPosition(JLabel.CENTER);
	   q.lbl.setVisible(true);
	   jf.getContentPane().add(q.lbl); 
	    
	   lblDurum.setText(" "+q.key+" eklendi");
	  
	  } else {
	   
	   // If compare node is smaller, continue with the left node
	   if(q.key<p.key) {
	    if(p.left==null) {
	     p.left = q;
	     q.parent = p;
	     
	     q.posY=p.posY+1;
	     lvl = calcuru(q.posY);
	     q.posX=p.posX-lvl;
	     
	     x= dizi[q.posY][q.posX].getX();
		 y= dizi[q.posY][q.posX].getY();
	     
	       q.lbl = new JLabel(new ImageIcon("back.png"));
		   q.lbl.setText(Integer.toString(q.key));
		   q.lbl.setBounds(x, y, 40, 40);
		   q.lbl.setVerticalTextPosition(JLabel.CENTER);
		   q.lbl.setHorizontalTextPosition(JLabel.CENTER);
		   q.lbl.setVisible(true);
		   jf.getContentPane().add(q.lbl);
		   
		   lblDurum.setText(" "+q.key+" eklendi");
	     
		  
	     // Node is inserted now, continue checking the balance
	     recursiveBalance(p);
	     cizgi.ciz(q);
	     
	     
	    } else {
	     insertAVL(p.left,q);
	     
	     
	    }
	    
	   } else if(q.key>p.key) {
	    if(p.right==null) {
	     p.right = q;
	     q.parent = p;
	   
	     q.posY=p.posY+1;
	     lvl = calcuru(q.posY);
	     q.posX=p.posX+lvl;
	     
	     x= dizi[q.posY][q.posX].getX();
		 y= dizi[q.posY][q.posX].getY();
	     
	       q.lbl = new JLabel(new ImageIcon("back.png"));
		   q.lbl.setText(Integer.toString(q.key));
		   q.lbl.setBounds(x, y, 40, 40);
		   q.lbl.setVerticalTextPosition(JLabel.CENTER);
		   q.lbl.setHorizontalTextPosition(JLabel.CENTER);
		   q.lbl.setVisible(true);
		   jf.getContentPane().add(q.lbl);
		 
		   lblDurum.setText(" "+q.key+" eklendi");
	  
		   
	     // Node is inserted now, continue checking the balance
	     recursiveBalance(p);
	     cizgi.ciz(q);
	    } else {
	     insertAVL(p.right,q);
	     
	    }
	   } else {
	    // do nothing: This node already exists
	   }
	  }
	 }
	 
	 /**
	  * Check the balance for each node recursively and call required methods for balancing the tree until the root is reached.
	  * 
	  * @param cur : The node to check the balance for, usually you start with the parent of a leaf.
	  */
	 public void recursiveBalance(AvlNode cur) {
	  
	  // we do not use the balance in this class, but the store it anyway
	  setBalance(cur);
	  int balance = cur.balance;
	  
	  // check the balance
	  if(balance==-2) {
	   
	   if(height(cur.left.left)>=height(cur.left.right)) {
	    cur = rotateRight(cur);
	   } else {
	    cur = doubleRotateLeftRight(cur);
	   }
	  } else if(balance==2) {
	   if(height(cur.right.right)>=height(cur.right.left)) {
	    cur = rotateLeft(cur);
	   } else {
	    cur = doubleRotateRightLeft(cur);
	   }
	  }
	  
	  // we did not reach the root yet
	  if(cur.parent!=null) {
	   recursiveBalance(cur.parent);
	  } else {
	   this.root = cur;
	   
	   relocate(root);
	  	   System.out.println("------------ Dengelendi ----------------");
	  	 
	   
	  }
	 }

	 /**
	  * Removes a node from the tree, if it is existent.
	  */
	 public void remove(int k) {
	  // First we must find the node, after this we can delete it.
	  removeAVL(this.root,k);
	  
	 }
	 
	 /**
	  * Finds a node and calls a method to remove the node.
	  * 
	  * @param p The node to start the search.
	  * @param q The KEY of node to remove.
	  */
	 public void removeAVL(AvlNode p,int q) {
	  if(p==null) {
	   lblDurum.setText(" Bulunamadi");
	   return;
	  } else {
	   if(p.key>q)  {
	    removeAVL(p.left,q);
	    
	   } else if(p.key<q) {
	    removeAVL(p.right,q);
	   } else if(p.key==q) {
	    // we found the node in the tree.. now lets go on!
	    removeFoundNode(p);
	    
	   }
	  }
	 }
	 
	 /**
	  * Removes a node from a AVL-Tree, while balancing will be done if necessary.
	  * 
	  * @param q The node to be removed.
	  */
	 public void removeFoundNode(AvlNode q) {
	  AvlNode r;
	  lblDurum.setText(" "+q.key+" silindi");
	  // at least one child of q, q will be removed directly
	  if(q.left==null && q.right==null) {
	   // the root is deleted
		  if(q.parent==null){
			  q.lbl.setVisible(false);
			  this.root=null;
			  
			  q=null;
			 // relocate(root);
			  return;
		  }
		  r=q;
		  }
	   if(q.left==null || q.right==null) {  
	    if(q.parent==null){
		   this.root=null;
	    
	    q.lbl.setVisible(false);
	    if(q.left==null)
	    	root = q.right;
	    if(q.right==null)
	    	root = q.left;
	   
	    q=null;	
	    relocate(root);
	   return;
	   }
	  r = q;
	  } else {
	   // q has two children --> will be replaced by successor
	   r = successor(q);
	   q.key = r.key;
	  }
	  
	  AvlNode p;
	  if(r.left!=null) {
	   p = r.left;
	  } else {
	   p = r.right;
	  }
	  
	  if(p!=null) {
	   p.parent = r.parent;
	  }
	  
	  if(r.parent==null) {
	   this.root = p;
	  } else {
	   if(r==r.parent.left) {
	    r.parent.left=p;
	   } else {
	    r.parent.right = p;
	   }
	   // balancing must be done until the root is reached.
	   recursiveBalance(r.parent);
	  
	  }
	  r.lbl.setVisible(false);
	
	  relocate(root);
	  
	  
	  
	  r = null;
	 }
	 
	 /**
	  * Left rotation using the given node.
	  * 
	  * 
	  * @param n
	  *            The node for the rotation.
	  * 
	  * @return The root of the rotated tree.
	  */
	 public AvlNode rotateLeft(AvlNode n) {
	  
	  AvlNode v = n.right;
	  v.parent = n.parent;
	  
	  n.right = v.left;
	
	  if(n.right!=null) {
		   n.right.parent=n;
		  }
	  
	  v.left = n;
	  n.parent = v;
	  
	  if(v.parent!=null) {
	   if(v.parent.right==n) {
	    v.parent.right = v;
	   } else if(v.parent.left==n) {
	    v.parent.left = v;
	    
	   }
	  }
	
	  setBalance(n);
	  setBalance(v);
	  
	  lblDurum.setText(" Sola döndü");
	  
	  return v;
	 }
	 
	 /**
	  * Right rotation using the given node.
	  * 
	  * @param n
	  *            The node for the rotation
	  * 
	  * @return The root of the new rotated tree.
	  */
	 public AvlNode rotateRight(AvlNode n) {
	  AvlNode v = n.left;
	  v.parent = n.parent;
	  
	  n.left = v.right;
	
	 
	  if(n.left!=null) {
		   n.left.parent=n;
		  }

	  v.right = n;
	  n.parent = v;
  
	  if(v.parent!=null) {
	   if(v.parent.right==n) {
	    v.parent.right = v;
	   } else if(v.parent.left==n) {
	    v.parent.left = v;
	   }
	  }
	  
	  setBalance(n);
	  setBalance(v);
	 
	  lblDurum.setText(" Saða döndü");
	
	  return v;
	 }
	 /**
	  * 
	  * @param u The node for the rotation.
	  * @return The root after the double rotation.
	  */
	 public AvlNode doubleRotateLeftRight(AvlNode u) {
		 u.left = rotateLeft(u.left);
		 return rotateRight(u);
	 
	 }
	 
	 /**
	  * 
	  * @param u The node for the rotation.
	  * @return The root after the double rotation.
	  */
	 public AvlNode doubleRotateRightLeft(AvlNode u) {
	  u.right = rotateRight(u.right);
	  	  return rotateLeft(u);
	  	  
	 }
	 
	/***************************** Helper Functions ************************************/
	 
	 /**
	  * Returns the successor of a given node in the tree (search recursivly).
	  * 
	  * @param q The predecessor.
	  * @return The successor of node q.
	  */
	 public AvlNode successor(AvlNode q) {
	  if(q.right!=null) {
	   AvlNode r = q.right;
	   while(r.left!=null) {
	    r = r.left;
	   }
	   return r;
	  } else {
	   AvlNode p = q.parent;
	   while(p!=null && q==p.right) {
	    q = p;
	    p = q.parent;
	   }
	   return p;
	  }
	 }
	 
	 /**
	  * Calculating the "height" of a node.
	  * 
	  * @param cur
	  * @return The height of a node (-1, if node is not existent eg. NULL).
	  */
	 private int height(AvlNode cur) {
	  if(cur==null) {
	   return -1;
	  }
	  if(cur.left==null && cur.right==null) {
	   return 0;
	  } else if(cur.left==null) {
	   return 1+height(cur.right);
	  } else if(cur.right==null) {
	   return 1+height(cur.left);
	  } else {
	   return 1+maximum(height(cur.left),height(cur.right));
	  }
	 }
	 
	 /**
	  * Return the maximum of two integers.
	  */
	 private int maximum(int a, int b) {
	  if(a>=b) {
	   return a;
	  } else {
	   return b;
	  }
	 }

	 /** 
	  * Only for debugging purposes. Gives all information about a node.
	  
	  * @param n The node to write information about.
	  */
	 public void debug(AvlNode n) {
	  int l = 0;
	  int r = 0;
	  int p = 0;
	  if(n.left!=null) {
	   l = n.left.key;
	  }
	  if(n.right!=null) {
	   r = n.right.key;
	  }
	  if(n.parent!=null) {
	   p = n.parent.key;
	  }
	  
	  System.out.println("Left: "+l+" Key: "+n+" Right: "+r+" Parent: "+p+" Balance: "+n.balance);
	  
	  if(n.left!=null) {
	   debug(n.left);
	  }
	  if(n.right!=null) {
	   debug(n.right);
	  }
	 }
	 
	 private void setBalance(AvlNode cur) {
	  cur.balance = height(cur.right)-height(cur.left);
	 }
	 
	 /**
	  * Calculates the Inorder traversal of this tree.
	  * 
	  * @return A Array-List of the tree in inorder traversal.
	  */
	 final protected ArrayList<AvlNode> inorder() {
	  ArrayList<AvlNode> ret = new ArrayList<AvlNode>();
	  inorder(root, ret);
	  return ret;
	 }
	 
	 /**
	  * Function to calculate inorder recursivly.
	  * 
	  * @param n
	  *            The current node.
	  * @param io
	  *            The list to save the inorder traversal.
	  */
	 final protected void inorder(AvlNode n, ArrayList<AvlNode> io) {
	  if (n == null) {
	   return;
	  }
	  inorder(n.left, io);
	  io.add(n);
	  inorder(n.right, io);
	  
	 }
		
	 
	 
	 //Her düðümün yerini seviyesine göre hesaplayan fonksiyon
		public static int calcuru(int a){
			switch (a) {
				case 1: a = 16;
					break;
				case 3: a = 8;
					break;
				case 4: a = 4;
					break;
				case 5: a = 2;
					break;
				case 6: a = 1;
					break;
			
			}
			return a;
		}
	
		//Her dönmeden sonra aðaçtaki düðümlerin yerini kökten baþlayarak tekrar kontrol edip yerleþtiren fonksiyon
		public static void relocate(AvlNode k){

			Graphics g = jf.getGraphics();
			
			if(k==null)
				 return;
			 
			 if (k== root){
				 k.posY=1;
				 k.posX=16;
			     x= dizi[k.posY][k.posX].getX();
				 y= dizi[k.posY][k.posX].getY();
				 k.lbl.setBounds(x,y,40,40);
				 k.lbl.setText(Integer.toString(k.key));
				 k.lbl.setVisible(true);
			 	
			 	 }
			 
				 if(k.left != null){
					
					 if(k.left.parent==root)
					 k.left.posY=k.left.parent.posY+2;
					 else 
						 k.left.posY=k.left.parent.posY+1;
					 
				     lvl = calcuru(k.left.posY);
				     k.left.posX=k.left.parent.posX-lvl;
					
				     x= dizi[k.left.posY][k.left.posX].getX();
					 y= dizi[k.left.posY][k.left.posX].getY();
					
					 k.left.lbl.setLocation(x,y);
					 k.left.lbl.setText(Integer.toString(k.left.key));
					 k.left.lbl.setVisible(true);
					 
					 
					 g.drawLine(k.left.lbl.getX()+40, k.left.lbl.getY()+40, k.left.parent.lbl.getX()+40, k.left.parent.lbl.getY()+40);
					 relocate(k.left);
				
				 }
				 if(k.right != null){
					 
					 if(k.right.parent==root)
					 k.right.posY=k.right.parent.posY+2;
					 else
						 k.right.posY=k.right.parent.posY+1;
					 
				     lvl = calcuru(k.right.posY);
				     k.right.posX=k.right.parent.posX+lvl;
				
				     x= dizi[k.right.posY][k.right.posX].getX();
					 y= dizi[k.right.posY][k.right.posX].getY();
					 
					 k.right.lbl.setLocation(x, y);
					 k.right.lbl.setText(Integer.toString(k.right.key));
					 k.right.lbl.setVisible(true);
					 
					 g.drawLine(k.right.lbl.getX()+40, k.right.lbl.getY()+40, k.right.parent.lbl.getX()+40, k.right.parent.lbl.getY()+40);
					 
					 relocate(k.right);
				
				 }  
				 
	}
		
			
			 }	
	 
	  	   
	 