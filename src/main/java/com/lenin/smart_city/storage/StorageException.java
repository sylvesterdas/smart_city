package com.lenin.smart_city.storage;

public class StorageException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7256913124524190525L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
