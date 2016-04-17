/* Gavin Rouse
 * Contains the implementation of the "Change Section Price"
 * menu option. This option allows you to change the price
 * of all the seats in a given seat class, on a given flight.
 */

package travelpackage;

import java.util.LinkedList;
import java.util.Scanner;

public class ChangeSectionPrice implements ExecuteBehavior
{
	public void execute(SystemManager res)
	{
		Scanner input = new Scanner(System.in);
		String flightID = null;
		SeatClass sClass = null;
		int price = 0;
		
		System.out.print("Enter flightID: ");
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
		
		System.out.print("Enter new price: ");
		price = input.nextInt();
		
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
							s.setPrice(price);
						}
					}
				}
			}
		}
		
		res.setAirlines(new AirlineList(list));
	}
}
