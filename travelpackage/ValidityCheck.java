/* Gavin Rouse
 * Checks the validity of an airport or airline name
 * when it is created.
 */

package travelpackage;

public abstract class ValidityCheck
{
	public final boolean checkValidity(String name)
	{
		if (validNameLength(name))
		{
			if (doesntExist(name))
				return true;
		}
		
		return false;
	}
	
	public abstract boolean validNameLength(String name);
	
	public abstract boolean doesntExist(String name);
}
