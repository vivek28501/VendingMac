package vending.exception;

/**
 * The Vending Machine throws this exception if the user request for a product which is sold out
 *
 * @author Vivek Singh
 */
public final class SoldOutException extends Exception
{
	private String message;

	public SoldOutException(String string) {
		this.message = string;
	}

	@Override
	public String getMessage(){
		return message;
	}
}
