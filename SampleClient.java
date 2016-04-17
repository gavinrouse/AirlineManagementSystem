/* Gavin Rouse
 * Contains the main method which has a system manager reference
 * in order to run the program.
 */

import travelpackage.*;

public class SampleClient
{
	public static void main(String[] args)
	{
		SystemManager res = new SystemManager();
		
		res.runMenu();
	}
}
