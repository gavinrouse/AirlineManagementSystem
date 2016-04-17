/* Gavin Rouse
 * Contains the implementation of the "Store System Details" 
 * menu option. This option allows you to store the current
 * state of the airport system in a file. The file is
 * written in AMS file format.
 */

package travelpackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class StoreSystemDetails implements ExecuteBehavior
{
	public void execute(SystemManager res)
	{
		Scanner input = new Scanner(System.in);
		FileWriter file = null;
		
		try
		{
			System.out.print("Enter file path to write to: ");
			file = new FileWriter(input.nextLine());
		
		
			BufferedWriter bufWrite = new BufferedWriter(file);
			
			bufWrite.write("[");
			
			for (Airport a : res.getAirports().getAirports())
			{
				if (!a.equals(res.getAirports().getAirports().getLast()))
					bufWrite.write(a.getAirportName() + ", ");
				
				else
				{
					bufWrite.write(a.getAirportName() + "]");
					bufWrite.write("{");
				}
			}
			
			AirlineList airlines = res.getAirlines();
			LinkedList<Airline> list = airlines.getAirlines();
			
			for (Airline air : list)
			{
				if (air.getAirlineName().equals(list.getFirst().getAirlineName()))
					bufWrite.write(air.getAirlineName() + "[");
				
				else
					bufWrite.write(air.getAirlineName() + "[");
				
				for (Flight f : air.getFlights())
				{
					bufWrite.write(f.getFlightID() + "|" + (f.getDate().getYear() + 1900) + ", " + (f.getDate().getMonth() + 1) + 
							", " + f.getDate().getDate() + ", " + f.getDate().getHours() + ", " + f.getDate().getMinutes() +
							"|" + f.getOrigin() + "|" + f.getDest() + "[");
					
					for (FlightSection s : f.getFlightSections())
					{
						if (s.getSeatClass().equals(SeatClass.economy))
							bufWrite.write("E");
						else if (s.getSeatClass().equals(SeatClass.business))
							bufWrite.write("B");
						else if (s.getSeatClass().equals(SeatClass.first))
							bufWrite.write("F");
						
						if (!s.equals(f.getFlightSections().getLast()))
							bufWrite.write(":" + s.getPrice() + ":" + s.getLayout() + ":" + s.getRows() + ",");
						
						else
						{
							if (!f.equals(air.getFlights().getLast()))
								bufWrite.write(":" + s.getPrice() + ":" + s.getLayout() + ":" + s.getRows() + "], ");
							
							else if (!air.equals(list.getLast()))
								bufWrite.write(":" + s.getPrice() + ":" + s.getLayout() + ":" + s.getRows() + "]], ");
							
							else
								bufWrite.write(":" + s.getPrice() + ":" + s.getLayout() + ":" + s.getRows() + "]]}");
						}
					}
				}
			}
			
			bufWrite.close();
		}
		
		catch (IOException e)
		{
			System.out.println("Error writing to file.");
		}
	}
}
