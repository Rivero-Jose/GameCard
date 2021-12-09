package GUI;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Control.CardController;

import src.Card;
import src.Direction;
import src.GameHandler;
import src.GameMode;
import src.HELP;

public class CardWindow {

	public int BOARD_SIZE ;
	public static final int PANEL_WIDTH = 150;
	public static final int PANEL_HEIGHT = 120;
	public static final int PANEL_OFFSET = 5;
	
	public static int BOARD_X_OFFSET;
	public static int BOARD_Y_OFFSET;
	public static int BOARD_WIDTH;
	public static int BOARD_HEIGHT;

	private JFrame _frame;
	private CardPanel[][] _cp;
	
	public CardWindow(int y){
		BOARD_SIZE = y;
		BOARD_X_OFFSET = 10 + BOARD_SIZE * 5;
		BOARD_Y_OFFSET = 30 + BOARD_SIZE * 5;
		BOARD_WIDTH = PANEL_WIDTH * BOARD_SIZE + BOARD_X_OFFSET;
		BOARD_HEIGHT = PANEL_HEIGHT * BOARD_SIZE + BOARD_Y_OFFSET;
		setupFrame();
		_cp = new CardPanel[BOARD_SIZE][BOARD_SIZE];
		initializePanel();
		addActionListeners();
		revalidate();

	}
	
	/*
	 * ACTIVITY 5
	 * 
	 * The getOpponentPlay method will determine what card the opponent will play. First it checks to see
	 * if the game has ended. It will then generate a random position to attempt to place a card. A loop
	 * will then be entered that will continuously check for an available spot to place a card. Once
	 * an available spot has been determined; the method will initialize a card in that space and
	 * check to see if the opponent has claimed any cards.
	 */
	public void getOpponentPlay(Card c){
		if(checkGameOver()){
			return;
		}
		int row = (int)(Math.random()*BOARD_SIZE);
		int col = (int)(Math.random()*BOARD_SIZE);
		while(!_cp[row][col].getOwner().equals(" ")){
			row =(int)(Math.random()*BOARD_SIZE);
			col = (int)(Math.random()*BOARD_SIZE);
		}
		_cp[row][col].initialize(c);
		boardCheck(_cp[row][col]);
		checkGameOver();
	}
	
	/*
	 * The boardCheck method takes a CardPanel as a parameter and then checks all of the surrounding
	 * CardPanels to see if any of those cards are claimed by the play.
	 */
	
	public void boardCheck(CardPanel p){
		int r = p.getRow();
		int c = p.getCol();
		if(r != 0 && !_cp[r-1][c].getOwner().equals(" ") && !_cp[r-1][c].getOwner().equals(p.getOwner())){
			if(p.getCard().claim(_cp[r-1][c].getCard(), Direction.UP)){
				_cp[r-1][c].changeOwner();
			}
		}
		if(r < _cp.length - 1 && !_cp[r+1][c].getOwner().equals(" ") && !_cp[r+1][c].getOwner().equals(p.getOwner())){
			if(p.getCard().claim(_cp[r+1][c].getCard(), Direction.DOWN)){
				_cp[r+1][c].changeOwner();
			}
		}
		if(c != 0 && !_cp[r][c-1].getOwner().equals(" ") && !_cp[r][c-1].getOwner().equals(p.getOwner())){
			if(p.getCard().claim(_cp[r][c-1].getCard(), Direction.LEFT)){
				_cp[r][c-1].changeOwner();
			}
		}
		if(c != _cp[r].length - 1 && !_cp[r][c+1].getOwner().equals(" ") && !_cp[r][c+1].getOwner().equals(p.getOwner())){
			if(p.getCard().claim(_cp[r][c+1].getCard(), Direction.RIGHT)){
				_cp[r][c+1].changeOwner();
			}
		}
	}


	/*
	 * The checkGameOver method will check to see if the game has ended. If the game has ended it will
	 * determine who the winner is and anounce it. The method will also reset the board for another
	 * game to be played.
	 */

	
	public boolean checkGameOver(){
		int count = 0;
		for(CardPanel[] c : _cp){
			for(CardPanel cp : c){
				if(!cp.getOwner().equals(" ")){
					count++;
				}
			}
		}
		if(count == BOARD_SIZE*BOARD_SIZE){
			String s = checkWinner();
			JOptionPane.showMessageDialog(null, s + " wins!");
			refresh();
			return true;
		}
		return false;
	}
	
	/*
	 * The refresh method will recreate the entire board and reinitialize all GUI elements. It also revalidates
	 * the frame so that these changes can be seen.
	 */
	private void refresh(){
		GameHandler.regenerateDecks();
		remove();
		_cp = new CardPanel[BOARD_SIZE][BOARD_SIZE];
		initializePanel();
		addActionListeners();
		revalidate();
	}
	
	/*
	 * The remove method is used to refresh the game after a winner has been determined. It will remove
	 * all GUI elements from the JFrame so that they can be recreated and added again.
	 */
	private void remove(){
		for(int r = 0; r < _cp.length; r++){
			for(int c = 0; c < _cp[r].length; c++){
				_frame.remove(_cp[r][c]);
			}
		}
	}
	
	/*
	 * The checkWinner method checks who won the game. It is called only if the game has ended and only
	 * when there are no available spots left for card placement.
	 */
	public String checkWinner(){
		if(GameMode.b){
			GameMode GM = new GameMode();
			int count = 0;
			for(CardPanel[] c : _cp){	
				for(CardPanel cp : c){
					if(cp.getOwner().equals("X")){
						count++;
					}
				}
			}
			if(count > (BOARD_SIZE * BOARD_SIZE)/2){
				GM.first2();
				return GameMode.player1+"'s turn";
			}
			else{
				GM.first2();
				return GameMode.player2+"'s turn";
			}
		}else{
			GameMode GM = new GameMode();
			int count = 0;
			for(CardPanel[] c : _cp){	
				for(CardPanel cp : c){
					if(cp.getOwner().equals("X")){
						count++;
					}
				}
			}
			if(count > (BOARD_SIZE * BOARD_SIZE)/2){
				GM.first();
				return "Player";
			}
			else{
				GM.first();
				return "Computer";
			}
		}
		
	}
	
	/*
	 * The revalidate method toggles visibility for the frame and validates it's components. This allows
	 * the user to see the updated frame after the game has been reset.
	 */

	private void revalidate(){
		_frame.setVisible(false);
		_frame.validate();
		_frame.setVisible(true);
	}
	
	/*
	 * The setupFrame method sets up the JFrame with the necessary parameters.
	 */
	private void setupFrame(){
		_frame = new JFrame("Card Game Lab");
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setLayout(null);
		_frame.setSize(BOARD_WIDTH,BOARD_HEIGHT);
		_frame.setResizable(false);
	}
	
	/*
	 * The initializePanel method creates all the panels and adds them to the JFrame.
	 */
	private void initializePanel(){
		int x = 0;
		int y = 0;
		for(int r = 0; r < _cp.length; r++){
			for(int c = 0; c < _cp[r].length; c++){
				_cp[r][c] = new CardPanel(r,c);
				_cp[r][c].setLocation(x, y);
				_cp[r][c].setSize(PANEL_WIDTH, PANEL_HEIGHT);
				x += PANEL_WIDTH + PANEL_OFFSET;
				_frame.add(_cp[r][c]);
			}
			x = 0;
			y += PANEL_HEIGHT + PANEL_OFFSET;
		}
	}
	
	/*
	 * The addActionListeners method adds the CardController listeners to all of the buttons inside
	 * each of the CardPanels. It does this by using the addListeners method inside the CardPanel class.
	 */
	private void addActionListeners(){
		
		for(int r = 0; r < _cp.length; r++){
			for(int c = 0; c < _cp[r].length; c++){
				_cp[r][c].addListeners(new CardController(_cp[r][c], this,_cp[r][c]));
			}
		}
	}
}
