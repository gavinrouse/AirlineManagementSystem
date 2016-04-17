/* Gavin Rouse
 * Contains the Flight Section object, which is composed of
 * seat class, a 2-D array of seats, a price for the seats,
 * a specific layout, a number of rows, and a layout behavior.
 */

package travelpackage;

public class FlightSection
{
	private SeatClass sClass;
	private Seat[][] seats;
	private int price;
	private char layout;
	private int rows;
	private LayoutBehavior behavior = null;
	
	FlightSection(int rows, char layout, SeatClass sClass, int price)
	{
		if (rows > 0 && rows <= 100)
		{
			this.layout = layout;
			this.rows = rows;
			layout(rows, layout);
			this.sClass = sClass;
			setPrice(price);
		}
		
		else
			System.out.println("Invalid number of rows. Must be between 1 and 100.");
	}
	
	public SeatClass getSeatClass()
	{
		return sClass;
	}
	
	public Seat[][] getSeats()
	{
		return seats;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public char getLayout()
	{
		return layout;
	}
	
	public int getRows()
	{
		return rows;
	}
	
	public void setSeats(Seat[][] seats)
	{
		this.seats = seats;
	}
	
	public void setPrice(int price)
	{
		if (price >= 0)
			this.price = price;
		else
			System.out.println("Invalid price.");
	}
	
	public void layout(int rows, char layout)
	{		
		if (layout == 'S')
			behavior = new SLayout();
		
		else if (layout == 'M')
			behavior = new MLayout();
		
		else if (layout == 'W')
			behavior = new WLayout();
		
		else
			System.out.println("Invalid layout.");
		
		setSeats(behavior.create(rows));
	}
	
	public boolean hasAvailableSeat()
	{
		for (int i = 0; i < seats.length; i++)
		{
			for (int j = 0; j < seats[i].length; j++)
			{
				if (!seats[i][j].getStatus())
					return true;
			}
		}
		
		return false;
	}
	
	public void bookSeat(int row, int col)
	{
		seats[row][col].bookSeat();
	}
	
	public String toString()
	{
		String result = "";
		
		result += this.getSeatClass() + " $" + this.getPrice() + "\n";
		
		for (int i = 0; i < this.getSeats().length; i++)
		{
			result += "\t\t\t  ";
			
			for (int j = 0; j < this.getSeats()[i].length; j++)
			{
				result += this.getSeats()[i][j].toString();
			}
			
			result += "\n";
		}
		
		return result;
	}
}
