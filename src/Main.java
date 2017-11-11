package avl_grid;


import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

import javax.swing.JPanel;

public class Main {
	private static int sutun = 36;
	private static int satir = 18;
	private final JButton btnEkle = new JButton("Ekle");
	private final JButton btnSil = new JButton("Sil");
	private final JTextField txtSayi = new JTextField();
	private final JLabel lblSayi = new JLabel("Sayý: ");
	public JLabel lblDurum = new JLabel();
	public static JFrame jf = new JFrame();
	public static JPanel jp = new JPanel();
	static JLabel dizi[][] = new JLabel[satir][sutun];
	int sayi;
	
	public Main(){
		
		jf.setTitle("AVL AGACI");
		jf.setSize(1440,720);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		jf.setVisible(true);
		
		jp.setBounds(0, 0, 180, 90);
		jp.setBackground(Color.GRAY);
		jp.setLayout(null);
		jp.setVisible(true);
		jf.add(jp);
		
		lblSayi.setBounds(10, 11, 50, 20);
		jp.add(lblSayi);
		
		txtSayi.setBounds(45,11,50,20);
		txtSayi.setColumns(5);
		jp.add(txtSayi);
		
		btnEkle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				sayi = Integer.parseInt(txtSayi.getText());
				
				AvlTree.agac.insert(sayi);
					
				txtSayi.setText("");
			}
		});
		btnEkle.setBounds(100,11,70, 20);
		jp.add(btnEkle);

		btnSil.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				sayi = Integer.parseInt(txtSayi.getText());
				
				AvlTree.agac.remove(sayi);
				
				txtSayi.setText("");
			}
		});
		btnSil.setBounds(100,41,70, 20);
		jp.add(btnSil);
		
		lblDurum.setBounds(0,61,180,20);
		jp.add(lblDurum);
		
		lblDurum.setHorizontalTextPosition(JLabel.CENTER);
		lblDurum.setVerticalTextPosition(JLabel.CENTER);
		
		int L=0;
		int H=0;
		//yapay grid
		for(int i=0; i < satir ; i++ ){
			for(int j=0 ; j< sutun  ; j++){
				dizi[i][j]=new JLabel();
				dizi[i][j].setBounds(L,H,40,40);
				//dizi[i][j].setText("x");
				
				jf.add(dizi[i][j]);
				dizi[i][j].setVisible(true);
				L=L+40;
			}
			H=H+40;
			L=0;
			jf.repaint();
		}
	}

	
	public static void main(String args[]){
		Main app = new Main();
		
	}









	
}