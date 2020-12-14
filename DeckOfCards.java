//Example 8-13
//A DeckOfCards object represents a deck of ordinary playing cards. The top card is dealt each
//time the method deal is called. A dealt card will not be reused until the DeckOfCards is shuffled.
import java.util.Random;
public class DeckOfCards
{
    private String[] rank;
    private String[] suit;
    private int top;
    //Post: This DeckOfCards initialized to a new deck; the cards are ordered from AceSpace to
    // KingDiamond as in a new deck; top initialized to 0.
    //
    public DeckOfCards()
    {
        rank=new String[52];
        suit=new String[52];
        for (int i=0; i<4; i++)
        {

            rank[0+13*i]="Ace";
            rank[1+13*i]="Two";
            rank[2+13*i]="Three";
            rank[3+13*i]="Four";
            rank[4+13*i]="Five";
            rank[5+13*i]="Six";
            rank[6+13*i]="Seven";
            rank[7+13*i]="Eight";
            rank[8+13*i]="Nine";
            rank[9+13*i]="Ten";
            rank[10+13*i]="Jack";
            rank[11+13*i]="Queen";
            rank[12+13*i]="King";
        }
        for (int i=0; i<13; i++)
            suit[i]="Spade";
        for (int i=13; i<26; i++)
            suit[i]="Heart";
        for (int i=26; i<39; i++)
            suit[i]="Club";
        for (int i=39; i<52; i++)
            suit[i]="Diamond";
        top=0;
    }
    //Post: This DeckOfCards thoroughly shuffled; top set to 0.
    public void shuffle()
    {
        Random rand=new Random();
        for (int i=0; i<1000; ++i)
        {
            int x=rand.nextInt(52);
            int y=rand.nextInt(52);
            String temp=rank[x];
            rank[x]=rank[y];
            rank[y]=temp;
            temp=suit[x];
            suit[x]=suit[y];
            suit[y]=temp;
        }
        top=0;
    }
    //Post: top incremented by 1.
    //Return: The top card of this DeckOfCards as a String such as &quot;AceHeart&quot;, &quot;TwoSpade&quot;,
    // &quot;TenDiamond&quot;, &quot;KingClub&quot;. If top &gt;=52, &quot;NoMoreCard&quot; is returned.
    public String deal()
    {
        String card="";
        if (top>=52) card = "NoMoreCard";
        else card = rank[top]+suit[top];
        top++;
        return card;
    }
}