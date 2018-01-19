package vending.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vending.exception.NotFullPaidException;
import vending.exception.NotSufficientChangeException;
import vending.exception.SoldOutException;


/**
 * Sample implementation of Vending Machine.
 *
 * @author Vivek Singh
 */
public final class VendingMachineImpl implements IVendingMachine
{
	/**
	 * Cash inventory.
	 */
	private final Inventory<Object> cashInventory = new Inventory<>();

	/**
	 * Item inventory.
	 */
	private final Inventory<Item> itemInventory = new Inventory<>();

	/**
	 * Total sales
	 */
	private long totalSales;

	/**
	 * Current item.
	 */
	private Item currentItem;

	/**
	 * Current balance.
	 */
	private long currentBalance;

	/**
	 * Construct.
	 */
	VendingMachineImpl(){
		initialize();
	}

	private void initialize(){
		//initialize machine with 5 coins of each denomination
		//and 5 cans of each Item
		for(Coin c : Coin.values()){
			cashInventory.put(c, 5);
		}

		for(Item i : Item.values()){
			itemInventory.put(i, 5);
		}

	}

	@Override
	public long selectItemAndGetPrice(final Item item) throws SoldOutException
	{
		if(itemInventory.hasItem(item)){
			currentItem = item;
			return currentItem.getPrice();
		}
		throw new SoldOutException("Sold Out, Please buy another item");
	}

	@Override
	public void insertCoin(final Coin coin) {
		currentBalance = currentBalance + coin.getDenomination();
		cashInventory.add(coin);
	}

	@Override
	public Bucket<Item, List<Coin>> collectItemAndChange() throws NotSufficientChangeException, NotFullPaidException
	{
		final Item item = collectItem();
		totalSales = totalSales + currentItem.getPrice();

		final List<Coin> change = collectChange();

		return new Bucket<>(item, change);
	}


	/**
	 * It is for collect item.
	 *
	 * @return  Item The item.
	 * @throws  NotSufficientChangeException The exception.
	 * @throws  NotFullPaidException The exception.
	 */
	private Item collectItem() throws NotSufficientChangeException,
									  NotFullPaidException
	{
		if(isFullPaid()){
			if(hasSufficientChange()){
				itemInventory.deduct(currentItem);
				return currentItem;
			}
			throw new NotSufficientChangeException("Not Sufficient change in Inventory");

		}
		final long remainingBalance = currentItem.getPrice() - currentBalance;
		throw new NotFullPaidException("Price not full paid, remaining : ",
									   remainingBalance);
	}


	/**
	 * It is for collect Change.
	 *
	 * @return  Item The item.
	 * @throws  NotSufficientChangeException The exception.
	 */
	private List<Coin> collectChange() throws NotSufficientChangeException
	{
		final long changeAmount = currentBalance - currentItem.getPrice();
		final List<Coin> change = getChange(changeAmount);
		updateCashInventory(change);
		currentBalance = 0;
		currentItem = null;
		return change;
	}

	@Override
	public List<Coin> refund() throws NotSufficientChangeException
	{
		final List<Coin> refund = getChange(currentBalance);
		updateCashInventory(refund);
		currentBalance = 0;
		currentItem = null;
		return refund;
	}


	/**
	 * It is whether full paid.
	 *
	 * @return  boolean .
	 */
	private boolean isFullPaid() {
		if(currentBalance >= currentItem.getPrice()){
			return true;
		}

		return false;
	}


	/**
	 * It is for get change.
	 *
	 * @return  boolean .
	 */
	private List<Coin> getChange(final long amount) throws NotSufficientChangeException{
		List<Coin> changes = Collections.emptyList();

		if(amount > 0){
			changes = new ArrayList<>();
			long balance = amount;
			while(balance > 0){
				if(balance >= Coin.QUARTER.getDenomination()
				   && cashInventory.hasItem(Coin.QUARTER)){
					changes.add(Coin.QUARTER);
					balance = balance - Coin.QUARTER.getDenomination();

				}else if(balance >= Coin.DIME.getDenomination()
						 && cashInventory.hasItem(Coin.DIME)) {
					changes.add(Coin.DIME);
					balance = balance - Coin.DIME.getDenomination();

				}else if(balance >= Coin.NICKLE.getDenomination()
						 && cashInventory.hasItem(Coin.NICKLE)) {
					changes.add(Coin.NICKLE);
					balance = balance - Coin.NICKLE.getDenomination();

				}else if(balance >= Coin.PENNY.getDenomination()
						 && cashInventory.hasItem(Coin.PENNY)) {
					changes.add(Coin.PENNY);
					balance = balance - Coin.PENNY.getDenomination();

				}else{
					throw new NotSufficientChangeException("NotSufficientChange, Please try another product");
				}
			}
		}

		return changes;
	}

	@Override
	public void reset(){
		cashInventory.clear();
		itemInventory.clear();
		totalSales = 0;
		currentItem = null;
		currentBalance = 0;
	}


	/**
	 * It is for to check change.
	 *
	 * @return  boolean .
	 */
	private boolean hasSufficientChange(){
		return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
	}


	/**
	 * It is for to check change.
	 *
	 * @return  boolean .
	 */
	private boolean hasSufficientChangeForAmount(long amount){
		boolean hasChange = true;
		try{
			getChange(amount);
		}catch(NotSufficientChangeException nsce){
			return hasChange = false;
		}

		return hasChange;
	}


	/**
	 * It is for to update cash inventory.
	 *
	 * @return  boolean .
	 */
	private void updateCashInventory(List change) {
		for(Object c : change){
			cashInventory.deduct(c);
		}
	}

}

