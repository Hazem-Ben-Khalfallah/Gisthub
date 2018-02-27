package com.blacknebula.gisthub.github;

/**
 * @author hazem
 */
public class InvalidNonceException extends RuntimeException {

    public InvalidNonceException(String message) {
        super(message);
    }

}
