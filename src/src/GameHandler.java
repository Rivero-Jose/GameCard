
package src;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import GUI.CardPanel;
import GUI.CardWindow;

/*
 * This class will handle anything related to the game. This includes getting the user's
 * card placement and the game's AI. It also creates the baord and generates the decks
 * the players will use.
 */
public final class GameHandler{

	// Decks to be used by the player and computer.
	public static Deck _d = new Deck();
	public static ArrayList<Card> PC;
	public static ArrayList<Card> NPC;
	public static ArrayList<Card> PC2;
	
	// Getters for the player and computer IDs
	public static String PCID(){if(PC.isEmpty()){return "";}else{return PC.get(0).getPlayerID();}}
	public static String NPCID(){if(NPC.isEmpty()){return "";}else{return NPC.get(0).getPlayerID();}}
	public static String PC2ID(){if(PC2.isEmpty()){return "";}else{return PC2.get(0).getPlayerID();}}
	
	/*
	 * ACTIVITY 1
	 * Constructor for the GameHandler Class. Creates the board and generates decks for PC and NPC.
	 * Also starts the game.
	 */
	boolean help = true;
	public GameHandler(){
		if(help){
		JOptionPane.showMessageDialog(null, "Welcome to the Game Card Lab \n" +
				"The rules are simple \n" +
				"1. CLick in the set of 9 boxes to place a card \n" +
				"2. If its Red, it your opponents, if green its yours\n" +
				"3. You just need to have a higher value to take ur opponents card(WHICH CAN ONLY BE DONE WHEN U PLACE A CARD \n" +
				"4. Same for your opponent\n" +
				"5. CLICK THE BOARD TO FIND OUT WHO GOES FIRST \n" +
				"6. BLUE is Player 1, MAGENTA is Player 2");
				
		help = false;
		}
		GameMode GM = new GameMode();
		CardWindow w = new CardWindow(GM.choose());
		if(GameMode.b){
			PC = _d.generateDeck("X");
			PC2 = _d.generateDeck("O");
			if(GameMode.bb){
				JOptionPane.showMessageDialog(null, GameMode.player2+"'s turn");
			}else{
				JOptionPane.showMessageDialog(null, GameMode.player1+"'s turn");
			}
		
		}else{
			PC = _d.generateDeck("X");
			NPC = _d.generateDeck("O");
		}
		
	}
	
	/*
	 * ACTIVITY 4
	 * The getPlayerCard method removes a card from the player's deck to be used by the player.
	 */
	public static Card getPlayerCard(){
		return PC.remove((int)(Math.random() * PC.size()));
	}
	public static Card getPlayerCard2(){
		return PC2.remove((int)(Math.random() * PC2.size()));
	}
	/*
	 * ACTIVITY 5
	 * The getOpponentCard method removes a card from the opponent's deck to be used by the opponent.
	 */
	
	public static Card getOpponentCard(CardPanel P, CardWindow W){
		if(GameMode.bbb){
			for(int i = 0; i < NPC.size();i++){
				if(P.checkOwned()){
					if(NPC.get(i).getUp() > P.getCard().getDown() ){
						W.boardCheck(P);
						return NPC.remove(i);
					}else if(NPC.get(i).getDown() > P.getCard().getUp()){
						W.boardCheck(P);
						return NPC.remove(i);
					}else if(NPC.get(i).getRight() > P.getCard().getLeft() ){
						W.boardCheck(P);
						return NPC.remove(i);
					}else if(NPC.get(i).getLeft() > P.getCard().getRight() ){
						W.boardCheck(P);
						return NPC.remove(i);
					}
				}
				
			}
			return NPC.remove((int)(Math.random() * NPC.size()));
		}else{
			return NPC.remove((int)(Math.random() * NPC.size()));
		}
		
	}
//	public static Object[] selectOpponentCard(){
//	if(GameMode.bbb){
//		for(int t = 0; v.getBoard().length; t++){
//			for(int t2; t2 < v.getBoard()[t].length; t2++){
//				for(int i = 0; i < NPC.size(); i++){
//					if(t > 0 && v.getBoard()[t][t2] == null){
//						if(NPC.get(i).claim(v.getBoard()[t][t2].getCard(), Direction.DOWN)){
//							return new Object[]{NPC.get(i), t, t2};
//						}
//					}if(t < 0 CardWindow.BOARD_SIZE - 1 && v.getBoard()[t][t2] == null){
//						if(NPC.get(i).claim(v.getBoard()[t][t2].getCard(), Direction.UP)){
//							return new Object[]{NPC.get(i), t, t2};
//						}
//					}if(t2 > 0 && v.getBoard()[t][t2] == null){
//						if(NPC.get(i).claim(v.getBoard()[t][t2].getCard(), Direction.LEFT)){
//							return new Object[]{NPC.get(i), t, t2};
//						}
//					}if(t2 < 0 CardWindow.BOARD_SIZE - 1 && v.getBoard()[t][t2] == null){
//						if(NPC.get(i).claim(v.getBoard()[t][t2].getCard(), Direction.RIGHT)){
//							return new Object[]{NPC.get(i), t, t2};
//						}
//					}
//				}
//			}
//		}
//	}
//		return new Object[]{getOpponentCard()};
//}
	
	public static Card selectCard(){
		String[] cardDisplay = new String[PC.size()];
		for(int i = 0; i < PC.size(); i++){
			cardDisplay[i] = PC.get(i).getUp() + "    "+ PC.get(i).getRight() + "    "+
									PC.get(i).getDown() + "    "+ PC.get(i).getLeft() + "    "; 
		}
		JComboBox cb = new JComboBox(cardDisplay);
		if(JOptionPane.showOptionDialog(null, cb, "Select card: UP - RIGHT - DOWN - LEFT",
				JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, new String[]{"OK"},"OK") !=-1){
			Card temp = GameHandler.PC.get(cb.getSelectedIndex());
			GameHandler.PC.remove(cb.getSelectedIndex());
			return temp;
		}else{
			return null;
		}
	}
	public static Card selectCard2(){
		String[] cardDisplay = new String[PC2.size()];
		for(int i = 0; i < PC2.size(); i++){
			cardDisplay[i] = PC2.get(i).getUp() + "    "+ PC2.get(i).getRight() + "    "+
									PC2.get(i).getDown() + "    "+ PC2.get(i).getLeft() + "    "; 
		}
		JComboBox cb = new JComboBox(cardDisplay);
		if(JOptionPane.showOptionDialog(null, cb, "Select card: UP - RIGHT - DOWN - LEFT",
				JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, new String[]{"OK"},"OK") !=-1){
			Card temp = GameHandler.PC2.get(cb.getSelectedIndex());
			GameHandler.PC2.remove(cb.getSelectedIndex());
			return temp;
		}else{
			return null;
		}
	}
	/*
	 * This method will re-generate the cards in the player's and the computer's decks.
	 */
	public static void regenerateDecks(){
		if(GameMode.b){
			PC = _d.generateDeck("X");
			PC2 = _d.generateDeck("O");
		}else{
			PC = _d.generateDeck("X");
			NPC = _d.generateDeck("O");
		}
		
	}
	
	

}
