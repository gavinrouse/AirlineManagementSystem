/* Gavin Rouse
 * This class is the storage for the list
 * of airports.
 */

package travelpackage;

import java.util.LinkedList;

public class AirportList extends ValidityCheck
{
	private LinkedList<Airport> airports = new LinkedList<Airport>();
	
	AirportList()
	{
		LinkedList<Airport> a = new LinkedList<Airport>();
		this.airports = a;
	}
	
	public LinkedList<Airport> getAirports()
	{
		return airports;
	}
	
	public void add(String name)
	{
		if (checkValidity(name))
			airports.add(new Airport(name));
	}
	
	public boolean validNameLength(String name)
	{
		if (name.length() == 3)
			return true;
		
		else
		{
			System.out.println(name + " is an invalid name. Airport names must be three characters.");
			return false;
		}
	}
	
	public boolean doesntExist(String name)
	{
		for (Airport airport : airports)
		{				
			if (name.equals(airport.getAirportName()))
			{
				System.out.println("An airport named " + name + " already exists.");
				return false;
			}
		}
		
		return true;
	}
	
	public void displaySystemDetails()
	{
		System.out.print("Airports: ");
		
		for (Airport airport : airports)
		{
			System.out.print(airport.toString());
			
			if (!airport.equals(airports.getLast()))
				System.out.print(", ");
		}
	}
}
