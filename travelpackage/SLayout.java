/* Gavin Rouse
 * Contains the implementation of the S Layout. This layout
 * has 3 columns, with an aisle between columns 1 and 2.
 */

package travelpackage;

public class SLayout implements LayoutBehavior
{
	public Seat[][] create(int rows)
	{
		Seat[][] seats = new Seat[rows][3];
		
		for (int i = 0; i < rows; i++)
		{
			char c = 'A';
			
			for (int j = 0; j < 3; j++)
			{
				if (j == 0)
					seats[i][j] = new Seat(i + 1, c, true, true);
				
				else if (j == 1)
					seats[i][j] = new Seat(i + 1, c, false, true);
				
				else if (j == 2)
					seats[i][j] = new Seat(i + 1, c, true, false);
				
				c++;
			}
		}
		
		return seats;
	}
}
