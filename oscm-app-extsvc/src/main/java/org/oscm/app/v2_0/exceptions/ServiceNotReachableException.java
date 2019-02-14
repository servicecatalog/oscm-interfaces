package org.oscm.app.v2_0.exceptions;

import org.oscm.app.v2_0.data.LocalizedText;

import java.util.List;

/**
 * Exception that is thrown when application is unable to connect to service instance
 */
public class ServiceNotReachableException extends APPlatformException {

    private static final long serialVersionUID = 7578966931340554864L;

    /**
     * Constructs a new exception with the specified detail message. The cause
     * is not initialized.
     *
     * @param message
     *            the detail message
     */
    public ServiceNotReachableException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message
     *            the detail message
     * @param cause
     *            the cause
     */
    public ServiceNotReachableException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified localized text messages.
     * The cause is not initialized.
     *
     * @param messages
     *            the localized text messages
     */
    public ServiceNotReachableException(List<LocalizedText> messages) {
        super(messages);
    }

    /**
     * Constructs a new exception with the specified localized text messages and
     * cause.
     *
     * @param messages
     *            the localized text messages
     * @param cause
     *            the cause
     */
    public ServiceNotReachableException(List<LocalizedText> messages, Throwable cause) {
        super(messages, cause);
    }
}
