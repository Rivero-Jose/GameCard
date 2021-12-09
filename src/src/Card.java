package src;

/*
 * This class will handle the behaviors of a Card. This includes the values they hold, the
 * player the card belongs to and comparing the card to another card in the act of a claim.
 * A claim occurs when a player puts down a card next to an unclaimed card that is already on the
 * board. The player putting down the card gets to try to "claim" the unclaimed card. The player
 * claims the unclaimed card if the value of that claiming card in the direction it is facing the 
 * unclaimed card is higher than the unclaimed card's value in the opposing direction.
 */
public class Card {

	// Value Variables
	private int _right, _left, _up, _down;
	private String _playerID;

	// Getters
	public int getRight() {	return _right; }
	public int getLeft() { return _left; }
	public int getUp() { return _up; }
	public int getDown() { return _down; }
	public String getPlayerID() { return _playerID; }

	// Setters
	public void setPlayerID(String ID) { _playerID = ID; }

	// Constructor
	public Card(int r, int l, int u, int d, String ID) {
		_right = r;
		_left = l;
		_up = u;
		_down = d;
		_playerID = ID;

	}

	// Alternate Constructor that duplicates a card. Used for generating decks.
	public Card(Card c, String ID) {
		_right = c.getRight();
		_left = c.getLeft();
		_up = c.getUp();
		_down = c.getDown();
		_playerID = ID;
	}

	// Comparison method. It requires a card to compare to and the direction
	// that card is facing this card. This card is considered the claiming card
	// and Card c is considered the unclaimed card.
	public boolean claim(Card c, Direction d) {
		
		// First check the direction.
		switch (d) {

		case UP:
			// Compare opposing directions and determine if a claim was made.
			if (_up > c.getDown())
				return true;
			else
				return false;

		case RIGHT:
			// Compare opposing directions and determine if a claim was made.
			if (_right > c.getLeft())
				return true;
			else
				return false;

		case DOWN:
			// Compare opposing directions and determine if a claim was made.
			if (_down > c.getUp())
				return true;
			else
				return false;

		case LEFT:
			// Compare opposing directions and determine if a claim was made.
			if (_left > c.getRight())
				return true;
			else
				return false;

		default:
			return false;
		}
	}

}
