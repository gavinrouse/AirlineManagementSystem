/* Gavin Rouse
 * Contains the implementation of the System Manager.
 * The system manager has a list of all the airports
 * in the system, and a list of all the airlines
 * in the system. It also runs the menu for the 
 * program.
 */

package travelpackage;

public class SystemManager
{
	private Menu menu = new Menu();
	private AirportList airports = new AirportList();
	private AirlineList airlines = new AirlineList();
	
	public AirportList getAirports()
	{
		return airports;
	}
	
	public void runMenu()
	{
		boolean running = true;
		
		while (running)
		{
			menu.displayMenu();
			menu.performChoice(this, menu.makeChoice());
		}
	}
	
	public void setAirports(AirportList airports)
	{
		this.airports = airports;
	}
	
	public AirlineList getAirlines()
	{
		return airlines;
	}
	
	public void setAirlines(AirlineList airlines)
	{
		this.airlines = airlines;
	}
	
	public void createAirport(String name)
	{
		airports.add(name);
	}
	
	public void createAirline(String name)
	{
		airlines.add(name);
	}
	
	public void createFlight(String airline, String origin, String dest, int year, int month, int day, int hour, int minute, String flightID)
	{
		airlines.addFlight(airline, origin, dest, year, month, day, hour, minute, flightID);
	}
	
	public void createSection(String airline, String flightID, int rows, char layout, SeatClass sClass, int price)
	{
		airlines.addSection(airline, flightID, rows, layout, sClass, price);
	}
	
	public void findAvailableFlights(String origin, String dest)
	{
		airlines.findAvailableFlights(origin, dest);
	}
	
	public void bookSeat(String airline, String flight, SeatClass sClass, int row, char col)
	{
		airlines.bookSeat(airline, flight, sClass, row, col);
	}
	
	public void displaySystemDetails()
	{
		System.out.println("\n***SYSTEM DETAILS***");
		airports.displaySystemDetails();
		airlines.displaySystemDetails();
	}
}
