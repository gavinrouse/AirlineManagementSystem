/* Gavin Rouse
 * Contains the implementation for the menu. The menu
 * has a display behavior, a make choice behavior,
 * and a perform choice behavior.
 */

package travelpackage;

import java.util.Scanner;

public class Menu
{
	private ExecuteBehavior c = null;
	
	public void displayMenu()
	{
		System.out.println("\n\nAirport System Menu\n-------------------\n");
		System.out.println("1) Create airport system using input file");
		System.out.println("2) Change the price of seats in a flight section"); 
		System.out.println("3) Find available flights");
		System.out.println("4) Change the seat class pricing for an airline");
		System.out.println("5) Book a specific seat");
		System.out.println("6) Book a preferred seat");
		System.out.println("7) Display airport system details");
		System.out.println("8) Store airport system details");
		System.out.println("0) Quit");
		System.out.print("\nYour choice: ");
	}
	
	public int makeChoice()
	{
		int choice = -1;
		Scanner input = new Scanner(System.in);
		
		while (choice == -1)
		{
			int temp = input.nextInt();
			if (temp >= 0 && temp <= 8)
				choice = temp;
			else
				System.out.print("Incorrect choice. Choose again: ");
		}
		
		return choice;
	}
	
	public void performChoice(SystemManager res, int choice)
	{		
		if (choice == 1)
			c = new CreateAirportSystem();
		else if (choice == 2)
			c = new ChangeSectionPrice();
		else if (choice == 3)
			c = new FindFlights();
		else if (choice == 4)
			c = new ChangeClassPrice();
		else if (choice == 5)
			c = new BookSpecificSeat();
		else if (choice == 6)
			c = new BookPreferredSeat();
		else if (choice == 7)
			c = new DisplaySystemDetails();
		else if (choice == 8)
			c = new StoreSystemDetails();
		else if (choice == 0)
			System.exit(0);
		
		c.execute(res);
	}
}
