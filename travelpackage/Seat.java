/* Gavin Rouse
 * Contains the Seat object, which is comprised of
 * a row number, a column letter, a window seat
 * property, an aisle seat property, and a booked
 * property.
 */

package travelpackage;

public class Seat
{
	private int row;
	private char col;
	private boolean window;
	private boolean aisle;
	private boolean booked;
	
	public Seat(int row, char col, boolean window, boolean aisle)
	{
		this.row = row;
		this.col = col;
		this.window = window;
		this.aisle = aisle;
		this.booked = false;
	}
	
	public boolean getStatus()
	{
		return booked;
	}
	
	public boolean isWindow()
	{
		return window;
	}
	
	public boolean isAisle()
	{
		return aisle;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public char getCol()
	{
		return col;
	}
	
	public void bookSeat()
	{
		booked = true;
	}
	
	public String toString()
	{
		String result = "";
		result += row + String.valueOf(col) + " - Booked: ";
		
		if (booked)
			result += "Yes    ";
		
		else
			result += "No     ";
		
		return result;
	}
}
