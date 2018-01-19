package vending.business;

/**
 * A parameterized utility class to hold two different object.
 *
 * @author Vivek Singh
 */
public final class Bucket<E1, E2>
{
	/**
	 * Holds the first object.
	 */
	private final E1 first;

	/**
	 * Holds the second object.
	 */
	private final E2 second;

	/**
	 * Construct object.
	 *
	 * @param first The first object.
	 * @param second The second object.
	 */
	public Bucket(final E1 first, final E2 second){
		this.first = first; this.second = second;
	}

	/**
	 * @return first.
	 */
	public E1 getFirst(){
		return first;
	}

	/**
	 * @return second.
	 */
	public E2 getSecond(){
		return second;
	}
}
