/* Gavin Rouse
 * Contains the Airline object, which is 
 * comprised of an airline name and a list of flights
 * for that particular airline.
 */

package travelpackage;

import java.util.LinkedList;

public class Airline
{
	private String airlineName;
	private LinkedList<Flight> flights;
	
	Airline(String name)
	{
		this.airlineName = name;
		flights = new LinkedList<Flight>();
	}
	
	public String getAirlineName()
	{
		return airlineName;
	}
	
	public LinkedList<Flight> getFlights()
	{
		return flights;
	}
	
	public void addFlight(Flight flight)
	{
		flights.add(flight);
	}
	
	public String toString()
	{
		return airlineName;
	}
}
