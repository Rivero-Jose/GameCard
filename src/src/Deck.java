package src;

import java.util.ArrayList;

/*
 * The deck class will generate decks from a master list of cards. It can generate these
 * decks by copying cards from the master list and placing the copy in the generated deck.
 */
public final class Deck {
	
	private ArrayList<Card> ClassicDeck;
	
	// Constructor. Every card in the game is constructed here.
	public Deck(){
		// right, left, up, down, ID
		ClassicDeck = new ArrayList<Card>();
		ClassicDeck.add(new Card(5,4,1,1,""));
		ClassicDeck.add(new Card(3,1,5,1,""));
		ClassicDeck.add(new Card(5,3,1,3,""));
		ClassicDeck.add(new Card(2,1,6,1,""));
		ClassicDeck.add(new Card(5,3,2,1,""));
		ClassicDeck.add(new Card(4,1,2,4,""));
 		ClassicDeck.add(new Card(1,5,1,4,""));
		ClassicDeck.add(new Card(1,5,3,2,""));
		ClassicDeck.add(new Card(1,1,2,6,""));
		ClassicDeck.add(new Card(3,2,4,4,""));
		ClassicDeck.add(new Card(6,1,2,2,""));
		ClassicDeck.add(new Card(4,3,2,5,""));
		ClassicDeck.add(new Card(5,4,1,6,""));
		ClassicDeck.add(new Card(6,1,3,1,""));
		ClassicDeck.add(new Card(5,3,2,4,""));
		ClassicDeck.add(new Card(6,1,4,7,""));
		ClassicDeck.add(new Card(1,4,3,4,""));
		ClassicDeck.add(new Card(4,5,6,2,""));
		ClassicDeck.add(new Card(2,4,3,6,""));
		ClassicDeck.add(new Card(3,1,4,5,""));
		ClassicDeck.add(new Card(7,1,5,2,""));
		ClassicDeck.add(new Card(1,2,5,3,""));
		ClassicDeck.add(new Card(1,4,2,6,""));
		ClassicDeck.add(new Card(5,1,3,6,""));
		ClassicDeck.add(new Card(1,2,4,1,""));
		ClassicDeck.add(new Card(5,3,2,1,""));
		ClassicDeck.add(new Card(1,6,1,3,""));
		ClassicDeck.add(new Card(2,1,4,1,""));
		ClassicDeck.add(new Card(5,2,6,3,""));
		ClassicDeck.add(new Card(1,4,2,1,""));
		ClassicDeck.add(new Card(6,4,3,1,""));
		
//		for(int i = 0; i < 100000;i++){
//			
//			ClassicDeck.add(new Card((int)((Math.random())*9 +1),(int)((Math.random())*9 +1),(int)((Math.random())*9 +1),(int)((Math.random())*9 +1),""));
//		}
	}
	
	// Method to generate a deck of cards. The deck is entered as an ArrayList for convenience.
	// This allows cards to be dynamically removed from a player's hand.
	public ArrayList<Card> generateDeck(String ID){
		ArrayList<Card> c = new ArrayList<Card>();
		for(int i = 0; i < 30; i++){
			c.add(new Card(ClassicDeck.get((int)(Math.random() * ClassicDeck.size())), ID));
		}
		return c;
	}

}
