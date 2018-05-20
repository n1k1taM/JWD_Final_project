package by.epam.vshop.dao.impl;

import java.io.IOException;

import by.epam.vshop.dao.InitializationSourceDAO;
import by.epam.vshop.dao.connection.ConnectionPool;
import by.epam.vshop.dao.exception.ConnectionPoolException;
import by.epam.vshop.dao.exception.DAOException;

public class InitializationSourceDAOImpl implements InitializationSourceDAO {

	@Override
	public void initSource() throws DAOException {
		

		try {
			ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		}

	}

	@Override
	public void destroySource() throws DAOException {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			connectionPool.close();
		} catch (IOException | ConnectionPoolException e) {
			throw new DAOException("Failure to close all connections", e);
		}
	}

}
