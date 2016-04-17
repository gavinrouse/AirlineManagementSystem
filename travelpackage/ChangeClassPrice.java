/* Gavin Rouse
 * Contains the implementation of the "Change Class Price"
 * menu option. This option allows you to change the
 * pricing for a particular seat class on all flights that
 * are part of the given airline, that begin at the given
 * origin, and end at the given destination.
 */

package travelpackage;

import java.util.LinkedList;
import java.util.Scanner;

public class ChangeClassPrice implements ExecuteBehavior
{
	public void execute(SystemManager res)
	{
		Scanner input = new Scanner(System.in);
		String airline = null;
		String origin = null;
		String dest = null;
		SeatClass sClass = null;
		int price = 0;
		
		System.out.print("Enter airline: ");
		airline = input.nextLine().toUpperCase();
		
		System.out.print("Enter origin (e.g. LAX): ");
		origin = input.nextLine().toUpperCase();
		
		System.out.print("Enter destination (e.g. LAX): ");
		dest = input.nextLine().toUpperCase();
		
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
		
		System.out.print("Enter new price: ");
		price = input.nextInt();
		
		AirlineList airlines = res.getAirlines();
		LinkedList<Airline> list = airlines.getAirlines();
		
		for (Airline a : list)
		{	
			if (a.getAirlineName().equals(airline))
			{
				for (Flight f : a.getFlights())
				{
					if (f.getOrigin().equals(origin) && f.getDest().equals(dest))
					{
						for (FlightSection s : f.getFlightSections())
						{
							if (s.getSeatClass().equals(sClass))
								s.setPrice(price);
						}
					}
				}
			}
		}
		
		res.setAirlines(new AirlineList(list));
	}
}
