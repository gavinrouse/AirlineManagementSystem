/* Gavin Rouse
 * Contains the Airport object, which 
 * consists of an airport name.
 */

package travelpackage;

public class Airport
{
	private String airportName;
	
	Airport(String name)
	{
		this.airportName = name;
	}
	
	public String getAirportName()
	{
		return airportName;
	}
	
	public String toString()
	{
		return airportName;
	}
}
