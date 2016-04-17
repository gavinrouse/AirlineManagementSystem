/* Gavin Rouse
 * Contains the impementation of the W Layout. This layout
 * has 10 columns, with an aisles between columns 3 and 4
 * and columns 7 and 8.
 */

package travelpackage;

public class WLayout implements LayoutBehavior
{
	public Seat[][] create(int rows)
	{
		Seat[][] seats = new Seat[rows][10];
		
		for (int i = 0; i < rows; i++)
		{
			char c = 'A';
			
			for (int j = 0; j < 10; j++)
			{
				if (j == 0 || j == 9)
					seats[i][j] = new Seat(i + 1, c, true, false);
				
				else if (j == 2 || j == 3 || j == 6 || j == 7)
					seats[i][j] = new Seat(i + 1, c, false, true);
				
				else
					seats[i][j] = new Seat(i + 1, c, false, false);
				
				c++;
			}
		}
		
		return seats;
	}
}
