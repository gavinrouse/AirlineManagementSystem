/* Gavin Rouse
 * Contains the implementation of the "Create Airport System"
 * menu option. This option allows you to specify a file
 * which contains the necessary information to initialize
 * the airport system. The file must be in AMS file format.
 */

package travelpackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CreateAirportSystem implements ExecuteBehavior
{
	public void execute(SystemManager res)
	{
		Scanner input = new Scanner(System.in);
		FileReader file = null;
		
		while (file == null)
		{
			try
			{
				System.out.print("Enter file path: ");
				file = new FileReader(input.nextLine());
			}
			
			catch (FileNotFoundException e)
			{
				System.out.println("Cannot locate file.");
			}
		}
		
		BufferedReader bufRead = new BufferedReader(file);
		String temp = "";
		String lines = "";
		
		try
		{
			while ((temp = bufRead.readLine()) != null)
			{
				lines += temp + "\n";
			}
		} 
		
		catch (IOException e)
		{
			System.out.println("File read failed.");
		}
		
		temp = lines.substring(1, lines.indexOf(']'));
		String[] airports = temp.split(", ");
		lines = lines.substring(lines.indexOf(']') + 1);
		
		for (int i = 0; i < airports.length; i++)
		{
			res.createAirport(airports[i]);
		}
		
		String airline = "";
		String flightID = "";
		String year = "";
		String month = "";
		String day = "";
		String hour = "";
		String minute = "";
		String origin = "";
		String dest = "";
		String sClass = "";
		SeatClass sc = null;
		String price = "";
		String layout = "";
		String rows = "";
		
		while (lines != null)
		{
			if (lines.charAt(0) == '{' || lines.charAt(0) == ' ')
			{
				airline = lines.substring(1, lines.indexOf('['));
				res.createAirline(airline);
				lines = lines.substring(lines.indexOf('[') + 1);
			}
			
			flightID = lines.substring(0, lines.indexOf('|'));
			lines = lines.substring(lines.indexOf('|') + 1);
			
			year = lines.substring(0, lines.indexOf(','));
			lines = lines.substring(lines.indexOf(',') + 2);
			
			month = lines.substring(0, lines.indexOf(','));
			lines = lines.substring(lines.indexOf(',') + 2);
			
			day = lines.substring(0, lines.indexOf(','));
			lines = lines.substring(lines.indexOf(',') + 2);
			
			hour = lines.substring(0, lines.indexOf(','));
			lines = lines.substring(lines.indexOf(',') + 2);
			
			minute = lines.substring(0, lines.indexOf('|'));
			lines = lines.substring(lines.indexOf('|') + 1);
			
			origin = lines.substring(0, lines.indexOf('|'));
			lines = lines.substring(lines.indexOf('|') + 1);
			
			dest = lines.substring(0, lines.indexOf('['));
			lines = lines.substring(lines.indexOf('[') + 1);
			
			res.createFlight(airline, origin, dest, Integer.parseInt(year), Integer.parseInt(month), 
					Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute), flightID);
			
			temp = lines.substring(0, lines.indexOf(']'));
			lines = lines.substring(lines.indexOf(']') + 3);
			
			while (temp != null)
			{
				sClass = temp.substring(0, temp.indexOf(':'));
				temp = temp.substring(temp.indexOf(':') + 1);
				
				price = temp.substring(0, temp.indexOf(':'));
				temp = temp.substring(temp.indexOf(':') + 1);
				
				layout = temp.substring(0, temp.indexOf(':'));
				temp = temp.substring(temp.indexOf(':') + 1);
				
				if (temp.length() > 2)
				{
					rows = temp.substring(0, temp.indexOf(','));
					temp = temp.substring(temp.indexOf(',') + 1);
				}
				
				else
				{
					rows = temp.substring(0, 1);
					temp = null;
				}
				
				if (sClass.equals("E"))
					sc = SeatClass.economy;
				else if (sClass.equals("B"))
					sc = SeatClass.business;
				else if (sClass.equals("F"))
					sc = SeatClass.first;
				
				res.createSection(airline, flightID, Integer.parseInt(rows), layout.charAt(0), sc, Integer.parseInt(price));
			}
			
			if (lines.length() <= 1)
				lines = null;
		}
		
		try
		{
			bufRead.close();
		} 
		
		catch (IOException e)
		{
			System.out.println("Error closing file.");
		}
	}
}
