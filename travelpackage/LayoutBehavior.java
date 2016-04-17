/* Gavin Rouse
 * Contains the create behavior, which is implemented
 * by all of the layout options in order to properly
 * initialize the layout of the seats in a section.
 */

package travelpackage;

public interface LayoutBehavior
{
	public Seat[][] create(int rows);
}
