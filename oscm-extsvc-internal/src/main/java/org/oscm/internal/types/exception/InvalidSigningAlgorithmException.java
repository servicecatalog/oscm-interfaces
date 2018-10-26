/*******************************************************************************
 *
 *  Copyright FUJITSU LIMITED 2018
 *
 *******************************************************************************/
package org.oscm.internal.types.exception;

/**
 * Created by PLGrubskiM on 2018-07-18.
 */
public class InvalidSigningAlgorithmException extends SaaSApplicationException {

    public InvalidSigningAlgorithmException() {
    }

    public InvalidSigningAlgorithmException(String message) {
        super(message);
    }
}
