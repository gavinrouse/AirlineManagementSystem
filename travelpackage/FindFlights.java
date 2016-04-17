/* Gavin Rouse
 * Contains the implementation of the "Find Flights"
 * menu option. This option allows you to enter an
 * origin, a destination, a date, and a seat class
 * and returns all available flights that meet
 * those criteria.
 */

package travelpackage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Scanner;

public class FindFlights implements ExecuteBehavior
{
	public void execute(SystemManager res)
	{
		Scanner input = new Scanner(System.in);
		String origin = null;
		String dest = null;
		String date = null;
		Calendar cal = new GregorianCalendar();
		Date d = new Date();
		SeatClass sClass = null;
		LinkedList<Flight> found = new LinkedList<Flight>();
				
		System.out.print("Enter origin (e.g. LAX): ");
		origin = input.nextLine().toUpperCase();
		
		System.out.print("Enter destination (e.g. LAX): ");
		dest = input.nextLine().toUpperCase();
		
		while (date == null)
		{
			System.out.print("Enter date (MM/DD/YYYY): ");
			date = input.nextLine();
			
			if (date.length() != 10)
			{
				date = null;
				System.out.println("Invalid date.");
			}
		}
		
		cal.set(Integer.parseInt(date.substring(6, 10)), Integer.parseInt(date.substring(0, 2)) - 1, 
				Integer.parseInt(date.substring(3, 5)), 12, 0, 0);
		d = cal.getTime();
		
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
		
		AirlineList airlines = res.getAirlines();
		LinkedList<Airline> list = airlines.getAirlines();
		
		for (Airline a : list)
		{			
			for (Flight f : a.getFlights())
			{
				if (f.getOrigin().equals(origin))
				{
					if (f.getDest().equals(dest))
					{
						if (d.getMonth() == f.getDate().getMonth() && d.getDate() == f.getDate().getDate() && d.getYear() == f.getDate().getYear())
						{
							for (FlightSection s : f.getFlightSections())
							{
								if (s.getSeatClass().equals(sClass))
								{
									if (s.hasAvailableSeat())
										found.add(f);
								}
							}
						}
					}
				}
			}
		}
		
		if (!found.isEmpty())
		{
			System.out.println("\nFlights found:\n------------");
			
			for (Flight f : found)
			{
				for (FlightSection s : f.getFlightSections())
				{
					if (s.getSeatClass().equals(sClass))
						System.out.println(f.getAirline() + " Flight " + f.getFlightID() + ": $" + s.getPrice());
				}
			}
		}
		
		else
			System.out.print("\nNo flights found");
	}
}
