package GUI;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.Card;

/*
 * The CardPanel class is a JPanel filled with JButtons. There are nine buttons in total.
 * Four of the buttons will represent the values of the card and the center button will
 * represent the owner of the card.
 */
public class CardPanel extends JPanel {
	

	/* 
	 * These instance variables determine the number of jbuttons that make a card,
	 * as well as the width and height of each button. They will not be allowed to
	 * change.
	 */
	public static final int CARD_SIZE = 3;
	public static final int BUTTON_WIDTH = 50;
	public static final int BUTTON_HEIGHT = 40;
	private Card _c;
	private int _row;
	private int _col;
	
	// This instance variable holds the JButtons
	private JButton[][] _cv;
	
	/*
	 * Getters for the Card, owner of the card and the row and col the CardPanel 
	 * is stored in the CardWindow's CardPanel array.
	 */
	public Card getCard(){ return _c; }
	public String getOwner(){ return _cv[1][1].getText(); }
	public int getRow(){ return _row; }
	public int getCol(){ return _col; }
	
	/*
	 * The CardPanel constructor will setup the CardPanel for use. It will initalize the
	 * JButtons we will use to hold the card values and add them to the JPanel.
	 */
	public CardPanel(int row, int col){
		_row = row;
		_col = col;
		setLayout(null);
		setBackground(Color.RED); // Keep just in case to see if you accidentally make the JPanel too big.
		_cv = new JButton[CARD_SIZE][CARD_SIZE];
		initializeButtons();
	}
	
	
	/*
	 *  This method will initialize all the buttons in the JPanel.
	 */
	private void initializeButtons(){
		int offsetx = 0;
		int offsety = 0;
		
		for(int r = 0; r < _cv.length; r++){
			for(int c = 0; c < _cv[0].length; c++){
				_cv[r][c] = new JButton(" ");
				_cv[r][c].setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
				_cv[r][c].setLocation(offsetx, offsety);
				offsetx += BUTTON_WIDTH;
				add(_cv[r][c]);
			}
			offsetx = 0;
			offsety += BUTTON_HEIGHT;
		}
	}
	
	/*
	 * This method will add the action listeners to the buttons. Since the CardController
	 * requires the CardWindow; we will call this method from the CardWindow class.
	 */
	public void addListeners(ActionListener a){
		for(int r = 0; r < _cv.length; r++){
			for(int c = 0; c < _cv[0].length; c++){
				_cv[r][c].addActionListener(a);
			}
		}
	}
	
	/*
	 * When a card is placed on the board this method will be called. It will change the
	 * text of the JButtons to contain the card values as well as who owns the card. Green 
	 * text belongs to the player while red text belongs to the computer.
	 */
	public void initialize(Card c){
		_c = c;
		if(c.getPlayerID().equals("X")){
			_cv[0][1].setForeground(Color.BLUE);
			_cv[2][1].setForeground(Color.BLUE);
			_cv[1][0].setForeground(Color.BLUE);
			_cv[1][2].setForeground(Color.BLUE);
			_cv[1][1].setForeground(Color.BLUE);
		}
		else{
			_cv[0][1].setForeground(Color.MAGENTA);
			_cv[2][1].setForeground(Color.MAGENTA);
			_cv[1][0].setForeground(Color.MAGENTA);
			_cv[1][2].setForeground(Color.MAGENTA);
			_cv[1][1].setForeground(Color.MAGENTA);
		}
		
		_cv[0][1].setText(String.valueOf(c.getUp()));
		_cv[2][1].setText(String.valueOf(c.getDown()));
		_cv[1][0].setText(String.valueOf(c.getLeft()));
		_cv[1][2].setText(String.valueOf(c.getRight()));
		_cv[1][1].setText(c.getPlayerID());
		
	}
	
	/*
	 * This helper method will allow the card panel to change the owner and denote this by
	 * changing the color of the JButton's text as well.
	 */
	public void changeOwner(){
		if(getOwner().equals(" ")){
			
		}
		else if(getOwner().equals("X")){
			_cv[1][1].setText("O");
			_cv[0][1].setForeground(Color.MAGENTA);
			_cv[2][1].setForeground(Color.MAGENTA);
			_cv[1][0].setForeground(Color.MAGENTA);
			_cv[1][2].setForeground(Color.MAGENTA);
			_cv[1][1].setForeground(Color.MAGENTA);
		}
		else{
			_cv[1][1].setText("X");
			_cv[0][1].setForeground(Color.BLUE);
			_cv[2][1].setForeground(Color.BLUE);
			_cv[1][0].setForeground(Color.BLUE);
			_cv[1][2].setForeground(Color.BLUE);
			_cv[1][1].setForeground(Color.BLUE);
		}
	}

	/*
	 * A method used to check if this cardPanel is owned or free.
	 */
	public boolean checkOwned(){
		if(getOwner().equals(" ")){
				return false;	
		}
		else{
			return true;
		}
	}
	
}
