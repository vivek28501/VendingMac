package vending.business;

/**
 * Coins supported by Vending Machine.
 *
 * @author Vivek Singh
 */
public enum Coin
{
	/**
	 * Coin when PENNY.
	 */
	PENNY(1),

	/**
	 * Coin when NICKLE.
	 */
	NICKLE(5),

	/**
	 * Coin when DIME.
	 */
	DIME(10),

	/**
	 * Coin when QUARTER.
	 */
	QUARTER(25);

	/**
	 * Denomination of coin.
	 */
	private final int denomination;

	/**
	 * Construct coin.
	 *
	 * @param denomination The denomination.
	 */
	Coin(final int denomination){
		this.denomination = denomination;
	}

	/**
	 * Returns the denomination.
	 *
	 * @return The denomination.
	 */
	public int getDenomination(){
		return denomination;
	}

}
