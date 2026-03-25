package zw.co.firstmutual.premiumreceipting.exceptions;

/**
 * @author Jackson Tungamira <tungamirajackson@gmail.com>
 */

public class InvalidInputException extends RuntimeException{
    private static final long serialVersionUID = -9079454849611061074L;

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(final String message) {
        super(message);
    }

}

