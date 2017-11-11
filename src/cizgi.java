package avl_grid;

import java.awt.Graphics;

 

public class cizgi extends Main {
	
	static Graphics g = jf.getGraphics();
	
	public static void ciz(AvlNode q){
		
		g.drawLine(q.parent.lbl.getX()+40, q.parent.lbl.getY()+40, q.lbl.getX()+40, q.lbl.getY()+40);
		 
	}
	
	
	
}
