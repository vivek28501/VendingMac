package vending.business;

import java.util.List;

import vending.exception.NotFullPaidException;
import vending.exception.NotSufficientChangeException;
import vending.exception.SoldOutException;


/**
 * Declare public API for Vending Machine.
 *
 * @author Vivek Singh
 */
public interface IVendingMachine
{
	/**
	 * It is for selecting item and get price.
	 *
	 * @param item The item.
	 * @throws  SoldOutException The exception.
	 */
	long selectItemAndGetPrice(Item item) throws SoldOutException;

	/**
	 * It is for inserting coin.
	 *
	 * @param coin The coin.
	 */
	void insertCoin(Coin coin);

	/**
	 * It is for refunding.
	 *
	 * @return  List<Coin> The List of coin.
	 * @throws  NotSufficientChangeException The exception.
	 */
	List<Coin> refund() throws NotSufficientChangeException;

	/**
	 * It is for collect item and change.
	 *
	 * @return  Bucket<Item, List<Coin>> The bucket.
	 * @throws  NotSufficientChangeException The exception.
	 * @throws  NotFullPaidException The exception.
	 */
	Bucket<Item, List<Coin>> collectItemAndChange() throws NotSufficientChangeException, NotFullPaidException;

	/**
	 * It is for reset.
	 */
	void reset();
}
