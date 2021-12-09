package src;
import javax.swing.JOptionPane;

public class HELP {
	
	public HELP(){
		boolean help = true;
		if(help){
		JOptionPane.showMessageDialog(null, "Welcome to the Game Card Lab \n" +
				"The rules are simple \n" +
				"1. CLick in the set of 9 boxes to place a card \n" +
				"2. If its Red, it your opponents, if green its yours" +
				"3. You just need to have a higher value to take ur opponents card(WHICH CAN ONLY BE DONE WHEN U PLACE A CARD \n" +
				"4. Same for your opponent");
		help = false;
		}
	}
	


}
