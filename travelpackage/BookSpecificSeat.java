/* Gavin Rouse
 * Contains the implementation of the "Book Specific Seat"
 * menu option. This option allows you to book a specific
 * seat by entering the flight, the seat class, the row,
 * and the column of the seat you would like to book.
 * If the seat is booked for you, a "Booked successfully"
 * message is returned.
 */

package travelpackage;

import java.util.LinkedList;
import java.util.Scanner;

public class BookSpecificSeat implements ExecuteBehavior
{
	public void execute(SystemManager res)
	{
		Scanner input = new Scanner(System.in);
		String flightID = null;
		SeatClass sClass = null;
		int row = 0;
		String c = null;
		char col = 'z';
		
		System.out.print("Enter flight ID: ");
		flightID = input.nextLine();
		
		while (sClass == null)
		{
			System.out.print("Enter seat class (e.g. economy): ");
			String temp = input.nextLine().toUpperCase();
			if (temp.equals("ECONOMY"))
				sClass = SeatClass.economy;
			else if (temp.equals("BUSINESS"))
				sClass = SeatClass.business;
			else if (temp.equals("FIRST"))
				sClass = SeatClass.first;
			else
				System.out.println("Incorrect class.");
		}
		
		System.out.print("Enter row (e.g. 2): ");
		row = input.nextInt();
		
		while (col == 'z')
		{
			System.out.print("Enter column (e.g. A): ");
			c = input.next().toUpperCase();
			
			if (c.length() > 1)
				System.out.println("Invalid column.");
			
			else
				col = c.charAt(0);
		}
		
		AirlineList airlines = res.getAirlines();
		LinkedList<Airline> list = airlines.getAirlines();
		
		for (Airline a : list)
		{	
			for (Flight f : a.getFlights())
			{
				if (f.getFlightID().equals(flightID))
				{
					for (FlightSection s : f.getFlightSections())
					{
						if (s.getSeatClass().equals(sClass))
						{
							char z = 'A';
							int i = 0;
							for (i = 0; z < col; i++, z++);
							
							if (!s.getSeats()[row - 1][i].getStatus())
							{
								s.bookSeat(row - 1, i);
								System.out.println("\nBooked successfully.");
							}
						}
					}
				}
			}	
		}
		
		res.setAirlines(new AirlineList(list));
	}
}
