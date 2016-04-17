/* Gavin Rouse
 * Contains the Flight object, which is composed of
 * an airline that the flight belongs to, an origin,
 * a destination, a date, a flight ID, and a list of
 * seat classes that are available on the flight.
 */

package travelpackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Flight
{
	private String airline;
	private String origin;
	private String dest;
	private Date date = new Date();
	private String flightID;
	private LinkedList<FlightSection> sections;
	
	Flight(String airline, String origin, String dest, int year, int month, int day, int hour, int minute, String id)
	{
		if (validFlight(origin, dest, year, month, day, hour, minute, id))
		{
			this.airline = airline;
			this.origin = origin;
			this.dest = dest;
			Calendar c = new GregorianCalendar();
			c.set(year, month - 1, day, hour, minute, 0);
			this.date = c.getTime();
			this.flightID = id;
			this.sections = new LinkedList<FlightSection>();
		}
	}
	
	public String getAirline()
	{
		return airline;
	}
	
	public String getOrigin()
	{
		return origin;
	}
	
	public String getDest()
	{
		return dest;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public String getFlightID()
	{
		return flightID;
	}
	
	public LinkedList<FlightSection> getFlightSections()
	{
		return sections;
	}
	
	public void addSection(Flight flight, int rows, char layout, SeatClass sClass, int price)
	{
		boolean exists = false;
		
		for (FlightSection section : flight.getFlightSections())
		{
			if (section.getSeatClass().equals(sClass))
			{
				System.out.println(sClass + " class already exists for flight " + flight.getFlightID() + ".");
				exists = true;
			}
		}
		
		if (!exists)
		{
			FlightSection section = new FlightSection(rows, layout, sClass, price);
			sections.add(section);
		}
	}
	
	public boolean validFlight(String origin, String dest, int year, int month, int day, int hour, int minute, String id)
	{
		if (origin.length() == 3 && dest.length() == 3 && !origin.equals(dest))
		{
			if (year >= 0)
			{
				if (month >= 1 && month <= 12)
				{
					if (day >= 1 && day <= 31)
					{
						if (hour >= 0 && hour <= 24)
						{
							if (minute >= 0 && minute <= 59)
								return true;
							
							System.out.println(minute + " is an invalid minute.");
							return false;
						}
						
						System.out.println(hour + " is an invalid hour.");
						return false;
					}
					
					System.out.println(day + " is an invalid day.");
					return false;
				}
				
				System.out.println(month + " is an invalid month.");
				return false;
			}
			
			System.out.println(year + " is an invalid year.");
			return false;
		}
		
		System.out.println(origin + ", " + dest + " is an invalid origin and destination pair.");
		return false;
	}
	
	public String toString()
	{
		String result = "";
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		result += "Date-" + dateFormat.format(date) + "\tOrigin-" + this.getOrigin() + "\tDest-" + this.getDest() + "\n\t\tSections: ";
		
		for (FlightSection section : this.getFlightSections())
		{
			result += section.toString();
			result += "\n\t\t\t  ";
		}
		
		return result;
	}
}
