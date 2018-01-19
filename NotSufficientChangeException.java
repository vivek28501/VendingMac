package vending.exception;

/**
 * Vending Machine throws this exception to indicate that it doesn't have sufficient change to complete this request.
 *
 * @author Vivek Singh
 */
public final class NotSufficientChangeException extends Exception
{
	private String message;

	public NotSufficientChangeException(String string) {
		this.message = string;
	}

	@Override
	public String getMessage(){
		return message;
	}
}
