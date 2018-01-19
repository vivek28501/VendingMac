package vending.business;

/**
 * Factory class to create instance of Vending Machine,
 * this can be extended to create instance of different types of vending machines.
 *
 * @author Vivek Singh
 */
public final class VendingMachineFactory
{
	/**
	 * It is factory for Vending Machine.
	 *
	 * @return  VendingMachine The vending machine.
	 */
	public static IVendingMachine createVendingMachine() {
		return new VendingMachineImpl();
	}
}
