/* Gavin Rouse
 * Contains the implementation of the "Book Preferred Seat" 
 * menu option. This option allows you to book a seat on a
 * particular flight with seat class preference and a
 * window or aisle seat preference. If a seat matching
 * your preference is found, it is booked, otherwise the
 * first available seat in the seat class you prefer is
 * booked.
 */

package travelpackage;

import java.util.LinkedList;
import java.util.Scanner;

public class BookPreferredSeat implements ExecuteBehavior
{
	public void execute(SystemManager res)
	{
		Scanner input = new Scanner(System.in);
		String flightID = null;
		SeatClass sClass = null;
		String preference = null;
		
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
		
		while (preference == null)
		{
			System.out.print("Enter seating preference (e.g. window): ");
			preference = input.nextLine().toUpperCase();
			
			if (!preference.equals("WINDOW") && !preference.equals("AISLE"))
			{
				preference = null;
				System.out.println("Invalid preference.");
			}
		}
		
		AirlineList airlines = res.getAirlines();
		LinkedList<Airline> list = airlines.getAirlines();
		boolean window = false;
		boolean aisle = false;
		boolean booked = false;
		
		if (preference.equals("WINDOW"))
		{
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
								int i, j;
								
								for (i = 0; i < s.getSeats().length; i++)
								{
									for (j = 0; j < s.getSeats()[i].length; j++)
									{
										if (!s.getSeats()[i][j].getStatus() && s.getSeats()[i][j].isWindow())
										{
											s.getSeats()[i][j].bookSeat();
											window = true;
											booked = true;
											System.out.println("Booked window seat " + s.getSeats()[i][j].getRow() +
													s.getSeats()[i][j].getCol());
											
											if (booked)
											{
												i = s.getSeats().length - 1;
												j = 100;
											}
										}
									}
								}
								
								if (!window)
									System.out.println("No window seats available.");
							}
						}
					}
				}	
			}
		}
		
		if (preference.equals("AISLE"))
		{
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
								int i, j;
								
								for (i = 0; i < s.getSeats().length; i++)
								{
									for (j = 0; j < s.getSeats()[i].length; j++)
									{
										if (!s.getSeats()[i][j].getStatus() && s.getSeats()[i][j].isAisle())
										{
											s.getSeats()[i][j].bookSeat();
											aisle = true;
											booked = true;
											System.out.println("Booked aisle seat " + s.getSeats()[i][j].getRow() +
													s.getSeats()[i][j].getCol());
											
											if (booked)
											{
												i = s.getSeats().length - 1;
												j = 100;
											}
										}
									}
								}
								
								if (!aisle)
									System.out.println("No aisle seats available.");
							}
						}
					}
				}	
			}
		}
		
		if (!booked)
		{
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
								int i, j;
								
								for (i = 0; i < s.getSeats().length; i++)
								{
									for (j = 0; j < s.getSeats()[i].length; j++)
									{
										if (!s.getSeats()[i][j].getStatus())
										{
											s.getSeats()[i][j].bookSeat();
											booked = true;
											System.out.println("Booked seat " + s.getSeats()[i][j].getRow() +
													s.getSeats()[i][j].getCol());
											
											if (booked)
											{
												i = s.getSeats().length - 1;
												j = 100;
											}
										}
									}
								}
								
								if (!booked)
									System.out.println("No seats available.");
							}
						}
					}
				}	
			}
		}
		
		res.setAirlines(new AirlineList(list));
	}
}
