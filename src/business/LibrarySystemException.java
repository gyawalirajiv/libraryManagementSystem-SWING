package business;

import java.io.Serializable;

public class LibrarySystemException extends Exception implements Serializable {
	
	private static final long serialVersionUID = 4546546516544L;
	public LibrarySystemException() {
		super();
	}
	public LibrarySystemException(String msg) {
		super(msg);
	}
	public LibrarySystemException(Throwable t) {
		super(t);
	}
}
