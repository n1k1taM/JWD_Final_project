package by.epam.vshop.dao;

import by.epam.vshop.dao.exception.DAOException;

public interface InitializationSourceDAO {
	/** Method for initialization source when server is started.
	 * @throws DAOException
	 */
	void initSource() throws DAOException;
	/** Method for initialization source when server is shutting down.
	 * @throws DAOException
	 */
	void destroySource() throws DAOException;
}
