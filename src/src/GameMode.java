package src;

import java.util.Random;

import javax.swing.JOptionPane;

public class GameMode {
	Random gene = new Random();
	public static boolean b = false;
	public static boolean bb = false;
	public static boolean bbb = false;
	public static int yy;
	
	public static String player1;
	public static String player2;

	
	
	public int choose(){
		int x = 0;
		
		String [] buttons1 = {"3X3","4X4","5X5"};
		int y = JOptionPane.showOptionDialog(null,"Choose your board size","Options", 0, JOptionPane.INFORMATION_MESSAGE, null, buttons1, buttons1);
		if (y == 0){
			x = 3;
			yy = x;
			String [] buttons3 = {"Human","Computer"};
			int zz = JOptionPane.showOptionDialog(null,"VS. Human or NPC?","Options", 0, JOptionPane.INFORMATION_MESSAGE, null, buttons3, buttons3);
			if(zz == 0){
				b = true;
				getNames();
				first2();
			}if(zz == 1){
			String [] buttons2 = {"EASY","HARD"};
			int z = JOptionPane.showOptionDialog(null,"Choose your diffculty","Options", 0, JOptionPane.INFORMATION_MESSAGE, null, buttons2, buttons2);
			if(z == 0){
				first();
			}if(z == 1){
				bbb = true;
				first();
			}
			}
			
		}if (y == 1){
			x = 4;
			yy = x;
			String [] buttons3 = {"Human","Computer"};
			int zz = JOptionPane.showOptionDialog(null,"VS. Human or NPC?","Options", 0, JOptionPane.INFORMATION_MESSAGE, null, buttons3, buttons3);
			if(zz == 0){
				b = true;
				getNames();
				first2();
			}if(zz == 1){
			String [] buttons2 = {"EASY","HARD"};
			int z = JOptionPane.showOptionDialog(null,"Choose your diffculty","Options", 0, JOptionPane.INFORMATION_MESSAGE, null, buttons2, buttons2);
			if(z == 0){
				first();
			}if(z == 1){
				bbb = true;
				first();
			}
			}
		}if (y == 2){
			x = 5;
			yy = x;
			String [] buttons3 = {"Human","Computer"};
			int zz = JOptionPane.showOptionDialog(null,"VS. Human or NPC?","Options", 0, JOptionPane.INFORMATION_MESSAGE, null, buttons3, buttons3);
			if(zz == 0){
				b = true;
				getNames();
				first2();
			}if(zz == 1){
			String [] buttons2 = {"EASY","HARD"};
			int z = JOptionPane.showOptionDialog(null,"Choose your diffculty","Options", 0, JOptionPane.INFORMATION_MESSAGE, null, buttons2, buttons2);
			if(z == 0){
				first();
			}if(z == 1){
				bbb = true;
				first();
			}
			}
		}
		return x;
	}
	public static boolean setPlayer;
	public static boolean setPlayer2;
	public void first(){
		int z = gene.nextInt(2);
		if(z == 0){
			setPlayer = true;
		}else{
			setPlayer = false;
		}
	}
	public void first2(){
		int z = gene.nextInt(2);
		if(z == 0){
			setPlayer2 = true;
			bb = true;
//			player();
		}else{
			setPlayer2 = false;
			bb = false;
//			player();
		}
	}
	
	
	public void getNames(){
		player1 = JOptionPane.showInputDialog(null, "Please enter Player 1 name.", "Player 1", JOptionPane.PLAIN_MESSAGE);
		player2= JOptionPane.showInputDialog(null, "Please enter Player 2 name.", "Player 2", JOptionPane.PLAIN_MESSAGE);

		
		
	}

}
