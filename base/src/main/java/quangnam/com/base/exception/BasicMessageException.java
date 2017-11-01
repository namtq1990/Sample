package quangnam.com.base.exception;

/**
 * Created by quangnam on 10/18/17.
 * Project Sample
 */

public class BasicMessageException extends BaseException {

    private int messageID = -1;

    public BasicMessageException(String message) {
        super(message);
    }

    public BasicMessageException(int errorCode, String message) {
        super(errorCode, message);
    }

    public BasicMessageException(int errorCode, String message, Throwable throwable) {
        super(errorCode, message, throwable);
    }

    /**
     * @return resource ID of message for display
     */
    public int getMessageID() {
        return messageID;
    }

    /**
     * Set resource ID for show message
     * @param messageID resource ID for message
     */
    public BasicMessageException setMessageID(int messageID) {
        this.messageID = messageID;
        return this;
    }
}
