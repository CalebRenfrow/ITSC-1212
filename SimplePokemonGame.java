import java.util.Scanner;

public class SimplePokemonGame {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter the name of your pokemon: ");
		String userPokemon = input.nextLine();
		int userPokemonHP = 100;
		int userPotions = 10;
		
		int userMenuChoice = 0;
		
		while(userMenuChoice != 3)
		{
		System.out.println("Please Choose from the menu below");
		System.out.println("(1) Search For Pokemonon (2) Use Potion (3) EXIT GAME");
		userMenuChoice = input.nextInt();
		
			switch(userMenuChoice)
			{
			
				case 1://Search For Pokemon
					int search = (int) (Math.random() *(10-1+1)+1);
					if(search < 6)//success
					{
						int pokemonTypeChooser = (int) (Math.random() *(5-1+1)+1);
						String pokemonType = "aks";	
						switch(pokemonTypeChooser)
							{
							
								case 1:
									pokemonType = "Fire";	
									break;
								case 2:
									pokemonType = "Water";	
									break;
								case 3:
									pokemonType = "Grass";	
									break;
								case 4:
									pokemonType = "Electric";	
									break;
								case 5:
									pokemonType = "Ghost";	
									break;
							}
						
						System.out.println("You've encountered a wild " + pokemonType + " Pokemon!");
						int wildPokemonHP = (int) (Math.random() *(100-50+1)+50);
						
						
						
						int userEncounterChoice = 6;
						while(userEncounterChoice != 5)
						{
							
							
							System.out.println();
							System.out.println(userPokemon +" has " + userPokemonHP + " HP.");
							System.out.println("The wild Pokemon has " + wildPokemonHP + " HP.");
					
							System.out.println("What will you do?");
							System.out.println("(1) Fight (2) use a Pokeball (3) Use a potion (4) Flee");
							userEncounterChoice = input.nextInt();
							
						
						switch(userEncounterChoice)
						{
						
							case 1://Fight
								if(userPokemonHP > 0 && wildPokemonHP > 0)
								{
									int damageWild = (int) (Math.random() *(25-10+1)+10);
									wildPokemonHP = wildPokemonHP - damageWild;
									
									int damageUserPokemon = (int) (Math.random() *(25-10+1)+10);
									userPokemonHP = userPokemonHP - damageUserPokemon;								
								}
								
								if(userPokemonHP <= 0 ) 
								{
									System.out.println("Your Pokemon Fainted!");
									userEncounterChoice = 5; userMenuChoice = 3;
								}
								
								if ( wildPokemonHP <= 0)
								{
									System.out.println("You Won! " + userPokemon + " has " + userPokemonHP + "HP left.");
									userEncounterChoice = 5;
								}
								
								else 
								{
									
								}
								break;
							case 2: //Pokeball
								
								double successRate = 100 / wildPokemonHP;								
								int successThrow = (int) (Math.random() *(10-1+1)+1);
								
								if( successThrow < successRate)
								{
									System.out.println("You caught the " + pokemonType + " Pokemon!");
									userEncounterChoice = 5;
								}
								else 
								{
									System.out.println("You failed to catch the Pokemon...");									
								}
								
								break;
							
							case 3://use potion
								if(userPotions == 0)
								{
									System.out.println( "You're out of potions!");
								}
								
								else if (userPokemonHP == 100)
								{
									System.out.println( "You wouldn't waste a potion on a Pokemon with full HP.");
								}
								else if (userPokemonHP >= 80)
								{
									userPokemonHP = 100;
									--userPotions;
									System.out.println( "You used a Potion");
									System.out.println(userPokemon +" has " + userPokemonHP + " HP.");
									System.out.println("You have " + userPotions + " left.");
								}
								else
								{
									--userPotions;
									userPokemonHP = userPokemonHP + 20;
									System.out.println( "You used a Potion");	
									System.out.println(userPokemon +" has " + userPokemonHP + " HP.");
									System.out.println("You have " + userPotions + "Potions  left.");
									
								}	
								break;
							
							case 4: // flee
								System.out.println("You fled.");userEncounterChoice = 5;
								break;
							case 5:	
								break;
							default: System.out.println("[Invalid Input]");
						}
						}
					}
					else
					{
						System.out.println("You've failed to find a Pokemon.");	
					}
					break;
				
				case 2:// Use Potion
					if(userPotions == 0)
					{
						System.out.println( "You're out of potions!");
					}
					
					else if (userPokemonHP == 100)
					{
						System.out.println( "You wouldn't waste a potion on a Pokemon with full HP.");
					}
					else if (userPokemonHP >= 80)
					{
						userPokemonHP = 100;
						--userPotions;
						System.out.println( "You used a Potion");
						System.out.println(userPokemon +" has " + userPokemonHP + " HP.");
						System.out.println("You have " + userPotions + " left.");
					}
					else
					{
						--userPotions;
						userPokemonHP = userPokemonHP + 20;
						System.out.println( "You used a Potion");	
						System.out.println(userPokemon +" has " + userPokemonHP + " HP.");
						System.out.println("You have " + userPotions + " left.");
						
					}
					
					break;
				case 3://Exit
					
					break;
				default:
					System.out.println("[Invalid Input]");
					break;
			
			}
	
		}
	}
}
