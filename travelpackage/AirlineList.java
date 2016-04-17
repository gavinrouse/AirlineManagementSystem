/* Gavin Rouse
 * This class is the storage for the list of airlines.
 */

package travelpackage;

import java.util.LinkedList;

public class AirlineList extends ValidityCheck
{
	private LinkedList<Airline> airlines = new LinkedList<Airline>();
	
	AirlineList()
	{
		LinkedList<Airline> a = new LinkedList<Airline>();
		this.airlines = a;
	}
	
	AirlineList(LinkedList<Airline> airlines)
	{
		this.airlines = airlines;
	}
	
	public LinkedList<Airline> getAirlines()
	{
		return airlines;
	}
	
	public void setAirlines(LinkedList<Airline> airlines)
	{
		this.airlines = airlines;
	}
	
	public void add(String name)
	{
		if (checkValidity(name))
			airlines.add(new Airline(name));
	}
	
	public boolean validNameLength(String name)
	{
		if (name.length() <= 6)
			return true;
		
		else
		{
			System.out.println(name + " is an invalid name. Airline names must be six or fewer characters.");
			return false;
		}
	}
	
	public boolean doesntExist(String name)
	{
		for (Airline airline : airlines)
		{
			if (name.equals(airline.getAirlineName()))
			{
				System.out.println("An airline named " + name + " already exists.");
				return false;
			}
		}
		
		return true;
	}
	
	public void addFlight(String airline, String origin, String dest, int year, int month, int day, int hour, int minute, String flightID)
	{
		boolean airlineExists = false;
		
		for (Airline air : airlines)
		{
			if (airline.equals(air.getAirlineName()))
			{
				airlineExists = true;
				Flight flight = new Flight(airline, origin, dest, year, month, day, hour, minute, flightID);
				
				if (flight.getFlightID() != null)
					air.addFlight(flight);
			}
		}
		
		if (!airlineExists)
			System.out.println(airline + " is not an existing airline.");
	}
	
	public void addSection(String airline, String flightID, int rows, char layout, SeatClass sClass, int price)
	{
		boolean airlineExists = false;
		for (Airline a : airlines)
		{
			if (a.getAirlineName().equals(airline))
			{
				airlineExists = true;
				
				for (Flight f : a.getFlights())
				{
					if (f.getFlightID().equals(flightID))
					{
						f.addSection(f, rows, layout, sClass, price);
					}
				}
			}	
		}
		
		if (!airlineExists)
			System.out.println(airline + " is not an existing airline.");
	}
	
	public void bookSeat(String airline, String flight, SeatClass sClass, int row, char col)
	{
		boolean badAirline = true;
		boolean badFlight = true;
		boolean bookedSeat = true;
		
		for (Airline air : airlines)
		{
			if (air.getAirlineName().equals(airline))
			{
				badAirline = false;
				
				for (Flight f : air.getFlights())
				{
					if (f.getFlightID().equals(flight))
					{
						badFlight = false;
						
						for (FlightSection section : f.getFlightSections())
						{
							if (section.getSeatClass().equals(sClass))
							{
								char c = 'A';
								int i = 0;
								for (i = 0; c < col; i++, c++);
								
								if (!section.getSeats()[row - 1][i].getStatus())
								{
									bookedSeat = false;
									section.bookSeat(row - 1, i);
								}
							}
						}
					}
				}
			}
		}
		
		if (badAirline)
			System.out.println(airline + " is not an existing airline.");
		
		else if (badFlight)
			System.out.println("Flight " + flight + " is not an existing flight.");
		
		else if (bookedSeat)
			System.out.println("Seat " + row + col + " is already booked.");
	}

	public void findAvailableFlights(String origin, String dest)
	{
		boolean available = false;
		System.out.println("\n\nAvailable Flights:");
		
		for (Airline airline : airlines)
		{
			for (Flight flight : airline.getFlights())
			{
				available = false;
				
				if (flight.getOrigin().equals(origin))
				{
					if (flight.getDest().equals(dest))
					{
						for (FlightSection section : flight.getFlightSections())
						{
							if (section.hasAvailableSeat())
								available = true;
						}
					}
				}
				
				if (available)
				{
					System.out.println(airline.getAirlineName() + " Flight " + flight.getFlightID() + 
							": Date-" + flight.getDate().getMonth() + "/" + flight.getDate().getDate() + "/" + flight.getDate().getYear() +
							"\tOrigin-" + flight.getOrigin() + "\tDest-" + flight.getDest());
				}
			}
		}
			
	}

	public void displaySystemDetails()
	{
		System.out.print("\nAirlines: ");
		
		for (Airline airline : airlines)
		{
			System.out.print(airline.toString());
			
			if (!airline.getAirlineName().equals(airlines.getLast().getAirlineName()))
				System.out.print(", ");
		}
		
		System.out.print("\nFlights: ");
		
		for (Airline airline : airlines)
		{
			for (Flight flight : airline.getFlights())
			{
				System.out.print(flight.getFlightID());
				
				if (!flight.equals(airline.getFlights().getLast()))
					System.out.print(", ");
			}
		}
		
		for (Airline airline : airlines)
		{
			for (Flight flight : airline.getFlights())
			{
				System.out.print("\n" + airline.getAirlineName() + " Flight " + flight.getFlightID() + ": " + flight.toString());
			}
		}
	}
}
