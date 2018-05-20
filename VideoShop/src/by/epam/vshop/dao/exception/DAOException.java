package by.epam.vshop.dao.exception;

public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}

	/**
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * @param exception
	 */
	public DAOException(Exception e) {
		super(e);
	}

	/**
	 * @param message
	 * @param exception
	 */
	public DAOException(String message, Exception e) {
		super(message, e);
	}
}
