//BlackJack.java
import java.util.Scanner;
import java.util.Vector;
public class BlackJack
{
	private DeckOfCards deck;
	private Vector<String> dealer;
	private Vector<String> player;
	private boolean first = true;

    //Post: 	The BlackJack object properly initialized.  In particular, the deck is shuffled, the 
    //	dealer's hand is initialized, the player's hand is initialized, 2 cards were dealt to 
    //	the player, 2 cards were dealt to the dealer.
	public BlackJack ()
	{
		deck = new DeckOfCards();
		deck.shuffle();
		
		player = new Vector<String>();
		player.add(deck.deal());
		player.add(deck.deal());

		dealer = new Vector<String>();
		dealer.add(deck.deal());
		dealer.add(deck.deal());
	}

    //Desc: 	Plays 1 game of BlackJack.
    //Input: 	'H' or 'S' from the user to indicate whether the user wants to hit or stay
    //Output: Various messages indicating the progress of the game
	public void play() 
	{
		System.out.println("Player Cards: ");
		displayPlayer();
		System.out.println("Dealer Cards: ");
		displayDealer(first);
		
		int playerTotal = total(player);
		int dealerTotal = total(dealer);
		if (playerTotal == 21)
				System.out.println("Player wins! Congrats!");
		else
			if (playerTotal > 21)
			{
				System.out.println("Player bust. Dealer wins");
				System.out.println(player);
				System.exit(0);
			}
		if (dealerTotal > 21)
		{
			System.out.println("Dealer bust. Player wins");
			System.exit(0);
		}
		else
			if (dealerTotal == 21)
				System.out.println("Dealer wins. Better luck next time!");
		Scanner f = new Scanner(System.in);
		System.out.println("Enter H for hit or S for Stay:");
		String input = f.next();
		input.toUpperCase();
		char choice = input.charAt(0);
		
		if (choice == 'H')
			player.add(deck.deal());

		if (dealerTotal < 17)
			dealer.add(deck.deal());
		
		first = false;
		f.close();
		play();
	}

    //Output: Player's hand displayed on the screen
	private void displayPlayer()
	{
		for (int i = 0; i < player.size(); i++)
			System.out.println(player.get(i));
	}

    //Output: Dealer's hand displayed on the screen with 1 card hidden if first is true; all 
    //	cards of the dealer's hand displayed on the screen if first is false
	private void displayDealer(boolean first)
	{
		for (int i = 0; i < dealer.size(); i++)
		{
			if (!first)
				System.out.println(dealer.get(i));
			else
			{
				System.out.println("First card is hidden.");
				first = false;
			}
		}
	}

	//Desc: ranks the cards in an integer form
	//Pre: a card
	//Return: the rank of the card
	public static int rankings(String card)
	{
		String[] faces = {"Two", "Three", "Four", "Five", "Six",
			"Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
		int ranking = 2;
		outerloop:
		{
			for(int i = 0; i < faces.length; i++)
			{
				for(int j = 3; j <= 6; j++)
				{
					String check = card.substring(0, j);
					if(check.equals(faces[i]))
						break outerloop;
				}
				ranking++;
			}
		}
		if (ranking == 11 || ranking == 12 || ranking == 13)
			ranking = 10;
		if (ranking == 14)
			ranking = 11;
		return ranking;
	}

    //Pre: v contains playing cards
    //Return: The numeric total of all the cards in v
	private int total(Vector<String> v)
	{
		int sum = 0;
		for (int i = 0; i < v.size(); i++)
			sum += rankings(v.get(i));
		return sum;
	}
}