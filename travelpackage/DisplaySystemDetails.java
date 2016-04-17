/* Gavin Rouse
 * Contains the implementation of the "Display System Details"
 * menu option. This option prints the current state of the
 * airport system to the console.
 */

package travelpackage;

public class DisplaySystemDetails implements ExecuteBehavior
{
	public void execute(SystemManager res)
	{
		res.displaySystemDetails();
	}
}
