package by.epam.vshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.vshop.bean.Discount;
import by.epam.vshop.bean.User;
import by.epam.vshop.bean.UserRole;
import by.epam.vshop.dao.ColumnLabel;
import by.epam.vshop.dao.UserDAO;
import by.epam.vshop.dao.connection.ConnectionPool;
import by.epam.vshop.dao.exception.ConnectionPoolException;
import by.epam.vshop.dao.exception.DAOException;
import by.epam.vshop.dao.manager.SQLRequestManager;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean isUserExist(String login, String password) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean userExistence = false;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_USER_BY_LOGIN_AND_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, String.valueOf(password.hashCode()));
			resultSet = preparedStatement.executeQuery();

			userExistence = resultSet.next();

		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		return userExistence;
	}

	@Override
	public User getUser(String login, String password) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_USER_BY_LOGIN_AND_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, String.valueOf(password.hashCode()));
			resultSet = preparedStatement.executeQuery();

			user = new User();
			if (resultSet.next()) {
				user.setDiscount(resultSet.getInt(ColumnLabel.USER_DISCOUNT_ID));
				user.setEmail(resultSet.getString(ColumnLabel.USER_EMAIL));
				user.setFirstName(resultSet.getString(ColumnLabel.USER_FIRST_NAME));
				user.setId(resultSet.getInt(ColumnLabel.USER_ID));
				user.setLogin(resultSet.getString(ColumnLabel.USER_LOGIN));
				user.setPassword(resultSet.getString(ColumnLabel.USER_PASSWORD));
				user.setRole(UserRole.valueOf(resultSet.getString(ColumnLabel.USER_ROLE).toUpperCase()));
				user.setStatus(resultSet.getBoolean(ColumnLabel.USER_STATUS));
				user.setLastName(resultSet.getString(ColumnLabel.USER_SURNAME));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		return user;
	}

	@Override
	public void signUp(User user) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.INSERT_USER);

			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setInt(5, user.getPassword().hashCode());

			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query insert_sign_up_user", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}
	}

	@Override
	public List<User> getUserList(int begin, int offset) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> userList = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_USER_LIST);
			preparedStatement.setInt(1, begin);
			preparedStatement.setInt(2, offset);
			resultSet = preparedStatement.executeQuery();

			userList = new ArrayList<User>();
			User user = null;

			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(ColumnLabel.USER_ID));
				user.setLogin(resultSet.getString(ColumnLabel.USER_LOGIN));
				user.setFirstName(resultSet.getString(ColumnLabel.USER_FIRST_NAME));
				user.setLastName(resultSet.getString(ColumnLabel.USER_SURNAME));
				user.setDiscount(resultSet.getInt(ColumnLabel.USER_DISCOUNT_ID));
				user.setEmail(resultSet.getString(ColumnLabel.USER_EMAIL));
				user.setRole(UserRole.valueOf(resultSet.getString(ColumnLabel.USER_ROLE).toUpperCase()));
				user.setStatus(resultSet.getBoolean(ColumnLabel.USER_STATUS));

				userList.add(user);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'select_user'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		return userList;
	}

	@Override
	public int getDiscountValue(int userId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int discount = 0;
		
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_DISCOUNT_PERSENT_BY_USER_ID);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				discount = resultSet.getInt(ColumnLabel.DISCOUNT_PERSENT);
			}
			
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can`t close connection");
		} catch (SQLException e) {
			throw new DAOException("SQL mistak in query 'get_discount_by_user_id'");
		}finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		
		
		
		return discount;
	}

	@Override
	public void updateClientDiscount(int clientId, int discountId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.UPDATE_CLIENT_DISCOUNT);

			preparedStatement.setInt(1, discountId);
			preparedStatement.setInt(2, clientId);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'update_client_discount'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}
		
	}

	@Override
	public User getUser(Integer userId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_USER_BY_USER_ID);
			preparedStatement.setInt(1, userId);
			
			resultSet = preparedStatement.executeQuery();

			user = new User();
			if (resultSet.next()) {
				user.setDiscount(resultSet.getInt(ColumnLabel.USER_DISCOUNT_ID));
				user.setEmail(resultSet.getString(ColumnLabel.USER_EMAIL));
				user.setFirstName(resultSet.getString(ColumnLabel.USER_FIRST_NAME));
				user.setId(resultSet.getInt(ColumnLabel.USER_ID));
				user.setLogin(resultSet.getString(ColumnLabel.USER_LOGIN));
				user.setPassword(resultSet.getString(ColumnLabel.USER_PASSWORD));
				user.setRole(UserRole.valueOf(resultSet.getString(ColumnLabel.USER_ROLE).toUpperCase()));
				user.setStatus(resultSet.getBoolean(ColumnLabel.USER_STATUS));
				user.setLastName(resultSet.getString(ColumnLabel.USER_SURNAME));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		return user;
	}

	@Override
	public Discount getDiscount(int userId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_DISCOUNT_BY_USER_ID);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			Discount discount = new Discount();
			if (resultSet.next()) {
				discount.setPersent(resultSet.getInt(ColumnLabel.DISCOUNT_PERSENT));
				discount.setId(resultSet.getInt(ColumnLabel.DISCOUNT_ID));;
			}
			return discount;
			
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can`t close connection");
		} catch (SQLException e) {
			throw new DAOException("SQL mistak in query 'get_discount_by_user_id'");
		}finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public List<Discount> getAllDiscounts() throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_ALL_DISCOUNTS);
			resultSet = preparedStatement.executeQuery();
			
			List<Discount> discountList = new ArrayList<>(); 
			while (resultSet.next()) {
				Discount discount = new Discount();
				discount.setPersent(resultSet.getInt(ColumnLabel.DISCOUNT_PERSENT));
				discount.setId(resultSet.getInt(ColumnLabel.DISCOUNT_ID));
				discountList.add(discount);
			}
			return discountList;
			
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can`t close connection");
		} catch (SQLException e) {
			throw new DAOException("SQL mistak in query 'get_discount_by_user_id'");
		}finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public void changeUserStatus(int userId, boolean userStatus) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.UPDATE_USER_STATUS);

			preparedStatement.setBoolean(1, userStatus);
			preparedStatement.setInt(2, userId);
			preparedStatement.executeUpdate();
			
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'update_client_discount'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}
	}

}
