package vending.business;

/**
 * Items or products supported by Vending Machine.
 *
 * @author Vivek Singh
 */
public enum Item
{
	/**
	 * Holds the Coke and its price.
	 */
	COKE("Coke", 25),

	/**
	 * Holds the Pepsi and its price.
	 */
	PEPSI("Pepsi", 35),

	/**
	 * Holds the Soda and its price.
	 */
	SODA("Soda", 45);

	/**
	 * Holds the name.
	 */
	private String name;

	/**
	 * Holds the price.
	 */
	private int price;

	/**
	 * Construct.
	 *
	 * @param name The name.
	 * @param price The price.
	 */
	Item(final String name, final int price){
		this.name = name;
		this.price = price;
	}

	/**
	 * Returns the name.
	 *
	 * @return The name.
	 */
	public String getName(){
		return name;
	}

	/**
	 * Returns the price.
	 *
	 * @return The price.
	 */
	public long getPrice(){
		return price;
	}
}
