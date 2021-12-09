package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import src.Display;
import src.GameHandler;
import src.GameMode;

import GUI.CardPanel;
import GUI.CardWindow;
/*
 * This class is designed to control the player interactions with the board. It
 * implements the ActionListener so that when a player clicks a button the
 * actionPerformed method will be called. 
 */
public class CardController implements ActionListener{
	
	// Instance variables the class will use. They are used to reference other variables.
	private CardWindow _w;
	private CardPanel _p;
	private CardPanel _p2;
	
	
	/*
	 * The constructor grabs a CardPanel and CardWindow from another class and
	 * then references them for later use.
	 */
	public CardController(CardPanel p, CardWindow w, CardPanel p2){
		_p = p;
		_w = w;
		_p2 = p2;
	}

	/*
	 * ACTIVITY 2
	 * 
	 * The actionPerformed method will initialize the CardPanel with a card. Every
	 * button in the CardPanel will use the same ActionListener. It will then check
	 * the CardWindow to see if any cards were claimed when that card was placed. Then
	 * the opponent will get to play. The actionPerformed method will do nothing if the
	 * a card is attempted to be placed in an already owned card.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(GameMode.b){
			if(!_p.checkOwned()){
				if(GameMode.setPlayer2){
					_p2.initialize(GameHandler.selectCard2());
					_w.boardCheck(_p2);
					_w.checkGameOver();
					GameMode.setPlayer2 = false;
					JOptionPane.showMessageDialog(null, GameMode.player1+"'s turn");
				}else{
					_p.initialize(GameHandler.selectCard());
					_w.boardCheck(_p);
					_w.checkGameOver();
					GameMode.setPlayer2 = true;
					JOptionPane.showMessageDialog(null,  GameMode.player1+"'s turn");
				}
			}
		}else{
			if(!_p.checkOwned()){
				if(GameMode.setPlayer){
					
//					_p.initialize(GameHandler.getPlayerCard());
//					_w.boardCheck(_p);
					
					_w.getOpponentPlay(GameHandler.getOpponentCard(_p, _w));
					_w.boardCheck(_p);
					GameMode.setPlayer = false;
				}else{
					_p.initialize(GameHandler.selectCard());
					_w.boardCheck(_p);
					_w.getOpponentPlay(GameHandler.getOpponentCard(_p, _w));
				}
			}
		}
	}
	
	
}
