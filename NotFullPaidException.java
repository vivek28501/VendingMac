package vending.exception;

/**
 * An Exception thrown by Vending Machine when a user tries to collect an item, without paying the full amount.
 *
 * @author Vivek Singh
 */
public final class NotFullPaidException extends Exception
{
	private String message;
	private long remaining;

	public NotFullPaidException(String message, long remaining) {
		this.message = message;
		this.remaining = remaining;
	}

	public long getRemaining(){
		return remaining;
	}

	@Override
	public String getMessage(){
		return message + remaining;
	}
}
