/**
 * Comparator for Integers.
 */

import java.util.Comparator;

public class IntComparator implements Comparator<Integer>
{
	/**
	 * Compares the given numbers.
	 * 
	 * @param  a  the first number to compare
	 * @param  b  the second number to compare
	 *
	 * @return negative value when a < b
	 *         positive value when a > b
	 *             0          when a == b
	 */
	public int compare( Integer a, Integer b )
	{
		if ( a < b ) {
			return -1;
		}
		else if ( a > b ) {
			return +1;
		}
		else {
			return 0;
		}
	}
}