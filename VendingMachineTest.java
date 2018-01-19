package vending.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import vending.business.Bucket;
import vending.business.Coin;
import vending.business.IVendingMachine;
import vending.business.Item;
import vending.business.VendingMachineFactory;
import vending.business.VendingMachineImpl;
import vending.exception.NotFullPaidException;
import vending.exception.NotSufficientChangeException;
import vending.exception.SoldOutException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * VendingMachineTest Description.
 *
 * @author Vivek Singh
 */
public final class VendingMachineTest
{
	private static IVendingMachine vm;


	@BeforeClass
	public static void setUp()
	{
		vm = VendingMachineFactory.createVendingMachine();
	}


	@AfterClass
	public static void tearDown()
	{
		vm = null;
	}


	@Test
	public void testBuyItemWithExactPrice() throws SoldOutException, NotSufficientChangeException, NotFullPaidException
	{

		//select item, price in cents
		long price = vm.selectItemAndGetPrice(Item.COKE);

		//price should be Coke's price
		assertEquals(Item.COKE.getPrice(), price);

		//25 cents paid
		vm.insertCoin(Coin.QUARTER);

		Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();

		Item item = bucket.getFirst();
		List<Coin> change = bucket.getSecond();

		// should be Coke
		assertEquals(Item.COKE, item);

		// there should not be any change
		assertTrue(change.isEmpty());
	}


	@Test
	public void testBuyItemWithMorePrice() throws SoldOutException, NotSufficientChangeException, NotFullPaidException
	{
		long price = vm.selectItemAndGetPrice(Item.SODA);

		assertEquals(Item.SODA.getPrice(), price);

		vm.insertCoin(Coin.QUARTER);
		vm.insertCoin(Coin.QUARTER);

		Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();

		Item item = bucket.getFirst();
		List<Coin> change = bucket.getSecond();

		//should be Coke
		assertEquals(Item.SODA, item);

		//there should not be any change
		assertTrue(!change.isEmpty());

		//comparing change
		assertEquals(50 - Item.SODA.getPrice(), getTotal(change));
	}


	@Test
	public void testRefund() throws SoldOutException, NotSufficientChangeException
	{
		long price = vm.selectItemAndGetPrice(Item.PEPSI);

		assertEquals(Item.PEPSI.getPrice(), price);

		vm.insertCoin(Coin.DIME);
		vm.insertCoin(Coin.NICKLE);
		vm.insertCoin(Coin.PENNY);
		vm.insertCoin(Coin.QUARTER);

		assertEquals(41, getTotal(vm.refund()));
	}


	@Test(expected = NotSufficientChangeException.class)
	public void testNotSufficientChangeException() throws SoldOutException, NotSufficientChangeException, NotFullPaidException
	{
		for (int i = 0; i < 5; i++)
		{
			vm.selectItemAndGetPrice(Item.SODA);
			vm.insertCoin(Coin.QUARTER);
			vm.insertCoin(Coin.QUARTER);
			vm.collectItemAndChange();
			vm.selectItemAndGetPrice(Item.PEPSI);
			vm.insertCoin(Coin.QUARTER);
			vm.insertCoin(Coin.QUARTER);
			vm.collectItemAndChange();
		}
	}


	@Test(expected = SoldOutException.class)
	public void testReset() throws SoldOutException
	{
		IVendingMachine vmachine = VendingMachineFactory.createVendingMachine();
		vmachine.reset();
		vmachine.selectItemAndGetPrice(Item.COKE);
	}


	private long getTotal(List<Coin> change)
	{
		long total = 0;
		for (Coin c : change)
		{
			total = total + c.getDenomination();
		}
		return total;
	}
}

