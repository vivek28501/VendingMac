package vending.test;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import vending.business.Bucket;
import vending.business.Coin;
import vending.business.IVendingMachine;
import vending.business.Item;
import vending.business.VendingMachineFactory;
import vending.exception.NotFullPaidException;
import vending.exception.NotSufficientChangeException;
import vending.exception.SoldOutException;


/**
 * VendingMachineConsole Description.
 *
 * @author Vivek Singh
 */
public final class VendingMachineConsole
{
	public static void main(String[] args)
	{

		IVendingMachine vm = VendingMachineFactory.createVendingMachine();

		Scanner console = new Scanner(System.in);
		System.out.println("  ^^/>   Vending Machine   ^^/>");

		while (true)
		{

			if (Item.SODA.equals(Collections.emptyList()) || Item.PEPSI.equals(Collections.emptyList()) || Item.COKE.equals(Collections.emptyList()))
			{

				System.out.println("Temporarily out of order");

				break;

			}

			System.out.println("Select your product.");

			System.out.println("1 - SODA       ");

			System.out.println("2 - PEPSI       ");

			System.out.println("3 - COKE");

			System.out.print("Your Choice    :");


			int YourChoice = console.nextInt();

			while (YourChoice < 1 || YourChoice > 3)
			{

				System.out.println("Please pick a valid product");

				System.out.print("Your Choice    :");

				YourChoice = console.nextInt();

			}

			if (YourChoice == 1)
			{

				System.out.println("You chose SODA");
			}
			else if (YourChoice == 2)
			{

				System.out.println("You chose PEPSI");

			}

			else if (YourChoice == 3)
			{

				System.out.println("You chose COKE");


			}

			System.out.print("Please insert coin : ");

			int coins = console.nextInt();


			while (coins < (Coin.DIME.getDenomination()))
			{

				System.out.println("Please insert the remaining ");

				coins += console.nextInt();

			}

			try
			{
				if (coins == vm.selectItemAndGetPrice(Item.valueOf(Integer.toString(YourChoice))));
			}
			catch (SoldOutException e)
			{
				e.printStackTrace();
			}

			try
			{
				vm.insertCoin(Coin.valueOf(Integer.toString(coins)));
			}
			catch(Exception exp){

			}

			try
			{
				Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();
			}
			catch (NotSufficientChangeException e)
			{
				e.printStackTrace();
			}
			catch (NotFullPaidException e)
			{
				e.printStackTrace();
			}
			{

				System.out.println("Thank you, please take your drink.");

			}



			System.out.println("");

			System.out.println("-------Welcome to the Vending Machine-------");


		}

	}


}

