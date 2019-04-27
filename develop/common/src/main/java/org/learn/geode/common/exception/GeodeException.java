package org.learn.geode.common.exception;

public class GeodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public GeodeException(Exception e) {
        super(e);
    }

    public GeodeException(String errorMsg, Exception e) {
        super(errorMsg, e);
    }

    public GeodeException(String errorMsg) {
        super(errorMsg);
    }

}
