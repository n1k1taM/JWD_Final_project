package by.epam.vshop.dao.connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.epam.vshop.dao.exception.ConnectionPoolException;
import by.epam.vshop.dao.exception.DAOException;
import by.epam.vshop.dao.manager.DBParameter;
import by.epam.vshop.dao.manager.DBResourceManager;

public final class ConnectionPool implements Closeable {
	private static final Logger logger = Logger.getLogger(ConnectionPool.class);

	private static volatile ConnectionPool instance;
	private BlockingQueue<Connection> freeConnection;
	private BlockingQueue<Connection> busyConnection;

	private int poolsize;
	private String driver;
	private String user;
	private String password;
	private String url;

	public static ConnectionPool getInstance() throws ConnectionPoolException {
		ConnectionPool localInstance = instance;
		if (localInstance == null) {
			synchronized (ConnectionPool.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new ConnectionPool();
				}
			}
		}
		return localInstance;
	}

	private ConnectionPool() throws ConnectionPoolException {
		DBResourceManager resourceManager = DBResourceManager.getInstance();
		this.driver = resourceManager.getValue(DBParameter.DB_DRIVER);
		this.user = resourceManager.getValue(DBParameter.DB_USER);
		this.password = resourceManager.getValue(DBParameter.DB_PASSWORD);
		this.url = resourceManager.getValue(DBParameter.DB_URL);

		try {
			this.poolsize = Integer.parseInt(resourceManager.getValue(DBParameter.DB_POOLSIZE));
		} catch (NumberFormatException e) {
			this.poolsize = 10;
		}
		freeConnection = new ArrayBlockingQueue<Connection>(poolsize);
		busyConnection = new ArrayBlockingQueue<Connection>(poolsize);

		try {
			Class.forName(driver);
			for (int i = 0; i < poolsize; i++) {
				freeConnection.add(DriverManager.getConnection(url, user, password));
			}
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Can't find database driver class", e);
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in ConnectionPool", e);
		}
	}

	public Connection take() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = freeConnection.take();
			busyConnection.put(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Error connecting to the data source", e);
		}
		return connection;
	}

	public void free(Connection connection) throws InterruptedException, DAOException {
		if (connection == null) {
			throw new DAOException("Connection is null");
		}
		if (!busyConnection.contains(connection)) {
			throw new DAOException("Try to close unknown connection");
		}

		Connection tempConnection = connection;
		connection = null;
		busyConnection.remove(tempConnection);
		freeConnection.put(tempConnection);
	}

	@Override
	public void close() throws IOException {
		List<Connection> listConnection = new ArrayList<Connection>();
		listConnection.addAll(this.busyConnection);
		listConnection.addAll(this.freeConnection);

		for (Connection connection : listConnection) {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, "Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Statement isn't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, "Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Statement isn't closed", e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "PrepareStatement ins't closed", e);
			}
		}

	}

	public void closeConnection(Connection con, PreparedStatement preSt1, PreparedStatement preSt2) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, "Connection isn't return to the pool", e);
			}
		}

		if (preSt1 != null) {
			try {
				preSt1.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "PrepareStatement1 isn't closed", e);
			}
		}

		if (preSt2 != null) {
			try {
				preSt2.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "PrepareStatement ins't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, PreparedStatement preSt, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, "Connection isn't return to the pool", e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "PrepareStatement ins't closed", e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "ResultSet ins't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, "Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Statement isn't closed", e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "ResultSet ins't closed", e);
			}
		}
	}

}
