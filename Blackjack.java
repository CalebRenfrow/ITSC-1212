/*
 * Author: Caleb Renfrow
 * Version: 10/29/2017
 * Description: Blackjack
 * 
 * Pseudocode:
 * - Display Instructions using displayInstructions method
 * - create a deck of 52 cards by combining an array containing faceValues and another of the card's suit.
 * - deal 2 cards using the drawCard method 
 * - ask user if they wish to be dealt another card, using drawCard method if yes
 * - If either the user or the dealer has been given an ace card the program will ask the user whether the aces should be an 11 or 1
 * 			(when the dealer draws it assumes they are 11(for the sake of explanation), but the user can change this value back to 1 given this set of rules)
 * - the displayWinner method runs, displaying the winner, if the player wins then a win counter ticks up
 * - ask the player if the wish to play again (if no then exit)
 * - After the player decides to quit or there are no more cards left the game exits and displays the total wins for the user.
 * 
 * ------------Methods-----------------Methods------------------Methods---------------------Methods-----------------------
 * 
 * -displayInstructions()
 * 		- display the instructions
 * 
 * -drawCard(String card)
 * 		- the created deck that was made at the beginning of the program is referenced here.
 * 		- a card has been drawn whenever the counter increases(moves from each index)
 * 		- if the card has a face then it is assigned to an integer value 10, unless it is an ace then it is assigned 80 (this is an arbitrary number, 80 is later changed to 11 or 1)
 * 		- if the card is a number card then the value of that card
 * 		- the playerTotal then increases by the appropriate int value decided in this method.
 * 		- the playerTotal is then returned
 * 
 * -determineWinner(int playerTotal, int compTotal)
 * 		- if playerTotal and compTotal are == then its a tie, if they both exceed 21 then it is also a tie, a string for a tie is returned
 * 		- if playerTotal is < compTotal and compTotal is <=21 then the appropriate string for the player losing is returned
 * 		- if compTotal is < playerTotal and playerTotal is <= 21 then the appropriate string for the player winning is returned
 */

import java.util.*;

public class Blackjack
{

	public static void main(String[] args) 
	{
		// Creates a randomly shuffled deck that will go on to ignore previously drawn cards
		//(draws from the top [0] and the counter adds one moving to each subsequent card)
		String faceValue[] ={"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
		String suit[] = {"Hearts","Diamonds","Clubs","Spades"};
		String[] deck = new String [52];
				
		
		for(int i = 0; i < deck.length; i++ )
			{
				deck[i] = faceValue[i%13] + " of "+ suit[i%4];
			}
		
		//randomizes deck
		for(int i = 0; i < deck.length; i++ )
			{
				int index = (int)(Math.random()*(deck.length));
				
				String tempDeck = deck[i];
				deck[i] = deck[index];
				deck[index] = tempDeck;
			}
		
		
		// The Game Begins
		Scanner input = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		int counter = 0;
		int winCounter = 0;
		
		String playAgain = "";
		
		displayInstructions();
	
		//If You want to see the deck of cards		
				for (int u = 0;u<deck.length;u++)
					{
						System.out.println(deck[u]);
					}
				System.out.println();
				
		
		// if play again equals no or there are not enough cards to continue the game (I think about 48 sounds right) the game ends
		while( !playAgain.equals("no") && counter <= 48)
			{	
				String drawAnother = "";
				int playerTotal = 0; //players total value of hand (starts at zero)
				int compTotal = 0;//dealers total value of hand (starts at zero)
				
				
				System.out.println("Player Draws:");
				System.out.println("Card 1: " + deck[counter]);// player gets the first card (if this is a completely new game then the first card would be deck[0]
				playerTotal += drawCard(deck[counter++]);// the value of the card is added to the playerTotal value and then is removed from the deck 
				System.out.println("Card 2: " + deck[counter]);
				playerTotal += drawCard(deck[counter++]);
				
				System.out.println("\nDealer Draws:");
				System.out.println("Card 1: " + deck[counter]);
				compTotal += drawCard(deck[counter++]);
				System.out.println("Card 2: " + deck[counter] + "\n");
				compTotal += drawCard(deck[counter++]);
				
				// will ask if the player would like to draw more cards (yes or no)
				while(!drawAnother.equals("no"))
				{
					System.out.println("Would you like to draw another card  (yes or no)?");
					drawAnother = input.nextLine().toLowerCase();
					
					
					while((!(drawAnother.equalsIgnoreCase("yes")|| drawAnother.equalsIgnoreCase("no"))))
					 { 
						 System.out.println("[Invalid Input]");
						 System.out.println("Would you like to draw another card?");
						 drawAnother = input.nextLine().toLowerCase();
					 }
					
					switch(drawAnother)
					{
						case "yes":
							System.out.println("You drew the " + deck[counter]); //card is revealed
							playerTotal += drawCard(deck[counter++]);// card value is added to playerTotal and is removed from deck
							break;
							
						case "no":
							drawAnother = "no";
							break;
					}
				}
				
				//dealer draws until their hand value is equal to or greater than 17
				while(compTotal < 17)
					{
						compTotal += drawCard(deck[counter++]);//value of the card is added to compTotal and is removed from deck
					}
					
				// if either player has a value greater than 80 that means they have an ace, otherwise this part will be skipped
				if(playerTotal >=80 || compTotal >= 80)
				{
					System.out.println("Should Aces be 1 or 11?");
					int aceValue = input.nextInt();
					
					//will repeat until all aces are either 1 or 11
					while(playerTotal >=80 || compTotal >= 80)
					{					
					
						//calculates the appropriate value for each player 
						switch(aceValue) 
						{
							case 1: 
									if(playerTotal >= 80)
										{
											playerTotal -= 80;playerTotal+=1;
										}
									
									if(compTotal >= 80)
										{
											compTotal -= 80;compTotal+=1;
										}
									
								break;
							
							case 11:
									if(playerTotal >= 80)
										{
											playerTotal -= 80;playerTotal+=11;
										}
									
									if(compTotal >= 80)
										{
											compTotal -= 80;compTotal+=11;
										}
								break;
								
						     default:
							    	 if(playerTotal >= 80)
										{
											playerTotal -= 80;playerTotal+=1;
										}
									
									if(compTotal >= 80)
										{
											compTotal -= 80;compTotal+=1;
										}
								break;
						}
					}
				}
				
				//displays final values
				System.out.println("Your total is: " + playerTotal);
				System.out.println("The dealer's total is: " + compTotal);
				
				//displays who won
				String winner = determineWinner(playerTotal,compTotal);
				System.out.println(winner);
				
				//if player won the win counter increases
				if(winner.contains(" player won"))
					winCounter += 1;			
				
				//prompts user to input either yes or no
				System.out.println("would you like to play again (yes or no)?");		
				playAgain = scan.nextLine().toLowerCase();
				 
				
				
			}
		
		
		
		//displays player's wins after the user decides to quit (or runs out of cards)
		System.out.println( "Total wins: " + winCounter);
		
		if(playAgain.equalsIgnoreCase("no"))
		{
			//ends game
		}
		else
			{
			// If there are no more cards left for the game then the game ends
			// I could add a shuffle function but I did not know if it would remove my extra credit 
			 System.out.println("Oh No! We're out of Cards! You're going to need to restart the game!"
			 		+ "\n(I was going to put in a reshuffle function but did not want to mess up extra credit)");
			 
			}
	input.close();	
	scan.close();
		
	}

	/*//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	Methods          Methods         Methods            Methods             Methods            Methods            Methods
             Methods          Methods         Methods            Methods              Methods            Methods	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

	
	//displays the instructions of Blackjack
	static public void displayInstructions()
	{
		System.out.println("First, the dealer and player each gets two cards."
				+ "\nThe goal of the game is to get as close, or equal, to 21 as possible without going over."
				+ "\nWhoever is closest to 21 is the winner. If anyone goes over 21, they automatically lose."
				+ "\nAfter the first two cards are drawn, you may decide whether or not to draw again."
				+ "\nAces may either be 1 or 11. Good luck!\n");
	}
		
	// determines the value of the top card (that once determined will get "removed" from the deck (really it is just ignored as the counter in main() moves on
	static public int drawCard( String card)
	{
		
		int cardNumber = 0;
		String value = card.substring(0,card.indexOf(" "));//takes the first word from the card to determine if its a face or value
		
		//face cards are assigned appropriate values
		if(value.equals("Ace"))
		{
			cardNumber = 80;// I chose 80 so that it the value can be referenced later when determining what the real value of Aces should be
		}
		else if(value.equals("Jack"))
		{
			cardNumber = 10;
		}
		else if(value.equals("Queen"))
		{
			cardNumber = 10;
		}
		else if(value.equals("King"))
		{
			cardNumber = 10;
		}
		else 
		{
			cardNumber = Integer.parseInt(value); // if the card is numbered then it is converted to an integer
		}
		
		return cardNumber;//returns the value of the card on the top of the deck (dealing/drawing the card) 
	}
	
	
	// method determines the winner, takes in the values of playerTotal and compTotal to determine who will win
	static public String determineWinner(int playerTotal, int compTotal)
	{
		String winner = " ";
		if(playerTotal == 21 || compTotal == 21)
			{
				if (playerTotal == 21 && compTotal != 21)
					{
						winner = "The player won!";				
					}
				else if(playerTotal == 21 && compTotal == 21)
					{
						winner = "It's a Tie";
					}
				else
					{
						 winner =  "The dealer won this turn. :(";
					}
			}
		
		else if(playerTotal > 21 || compTotal > 21)
			{				
				if(playerTotal > 21 && compTotal> 21)
					{
						winner = "It's a Tie";
					}
				else if(playerTotal > 21)
					{
						 winner =  "The dealer won this turn. :(";
					}
				else if(compTotal > 21)
					{
						 winner =  "The player won!";
					}
			}
		
		else if(compTotal < playerTotal || compTotal > playerTotal)
			{
				if(compTotal < playerTotal)
					{
						 winner =  "The player won!";
					}
				if(compTotal > playerTotal)
					{
						 winner =  "The dealer won this turn. :(";
					}
			}
		
		else if (compTotal == playerTotal)
			{
				 winner = "It's a Tie";
			}
		
		return winner; // returns the appropriate string back to main()
		
	}

}
