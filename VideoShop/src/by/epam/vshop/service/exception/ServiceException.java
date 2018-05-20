package by.epam.vshop.service.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String massege, Throwable cause) {
		super(massege, cause);
	}

	public ServiceException(String massege) {
		super(massege);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
