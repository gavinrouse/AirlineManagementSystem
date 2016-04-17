/* Gavin Rouse
 * Contains the implementation of the M Layout. This layout
 * has 4 columns with an aisle between columns 2 and 3.
 */

package travelpackage;

public class MLayout implements LayoutBehavior
{
	public Seat[][] create(int rows)
	{
		Seat[][] seats = new Seat[rows][4];
		
		for (int i = 0; i < rows; i++)
		{
			char c = 'A';
			
			for (int j = 0; j < 4; j++)
			{
				if (j == 0 || j == 3)
					seats[i][j] = new Seat(i + 1, c, true, false);
				
				else if (j == 1 || j == 2)
					seats[i][j] = new Seat(i + 1, c, false, true);
				
				c++;
			}
		}
		
		return seats;
	}
}
