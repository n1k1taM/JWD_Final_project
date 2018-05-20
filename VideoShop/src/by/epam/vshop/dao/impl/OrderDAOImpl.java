package by.epam.vshop.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.vshop.bean.Order;
import by.epam.vshop.bean.OrderStatus;
import by.epam.vshop.dao.ColumnLabel;
import by.epam.vshop.dao.OrderDAO;
import by.epam.vshop.dao.connection.ConnectionPool;
import by.epam.vshop.dao.exception.ConnectionPoolException;
import by.epam.vshop.dao.exception.DAOException;
import by.epam.vshop.dao.manager.SQLRequestManager;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public int getActiveOrderId(int userId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int activOrderId = 0;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_ACTIVE_ORDER_ID_BY_USER_ID);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			activOrderId = resultSet.getInt(1);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'get_active_order_id_by_user_id'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		return activOrderId;
	}

	@Override
	public boolean isOrderHasFilm(int orderId, int filmId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isFilmExistInOrder = false;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.IS_ORDER_HAS_FILM);
			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, filmId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isFilmExistInOrder = true;
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		return isFilmExistInOrder;
	}

	@Override
	public void addFilmToOrder(int orderId, int filmId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.INSER_FILM_TO_ORDER);
			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, filmId);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'insert_film_to_order'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}

	}

	@Override
	public Order getActiveOrder(int userId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Order order = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_ACTIVE_ORDER_BY_USER_ID);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				order.setDate(resultSet.getDate(ColumnLabel.ORDER_DATE));
				order.setId(resultSet.getInt(ColumnLabel.ORDER_ID));
				order.setStatus(OrderStatus.valueOf(resultSet.getString(ColumnLabel.ORDER_STATUS).toUpperCase()));
				order.setUserId(resultSet.getInt(ColumnLabel.USER_ID));	
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base");
		} catch (SQLException e) {
			throw new DAOException("SQL exceptinon in query 'get_active_order'"); 
		}finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return order;
	}

	@Override
	public void deleteFilmFromActiveOrder(int filmId, int orderId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.DELETE_FILM_FROM_ORDER);
			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, filmId);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'delete_film_from_order'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}
	}

	@Override
	public boolean isClientHasFilmInOrders(int clientId, int filmId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.IS_CLIENT_HAS_FILM_IN_ORDER);
			preparedStatement.setInt(1, clientId);
			preparedStatement.setInt(2, filmId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}else{
				return false;
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake in query 'get_client_film_list_by_client_id'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public float getActiveOrderCost(int userId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		float orderCost = 0;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_ACTIVE_ORDER_COST_BY_USER_ID);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				orderCost = resultSet.getFloat(1);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake in query 'get_client_film_list_by_client_id'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return orderCost;
	}

	@Override
	public void payOrder(float orderCostAfterDiscount, int userId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;

		try {
			pool = ConnectionPool.getInstance();
						
			connection = pool.take();
			connection.setAutoCommit(false);
			preparedStatement1 = connection.prepareStatement(SQLRequestManager.CHANGE_ACTIVE_ORDER_TO_PAID);
			preparedStatement1.setFloat(1, orderCostAfterDiscount);
			preparedStatement1.setDate(2, new Date ((new java.util.Date()).getTime()));
			preparedStatement1.setInt(3, userId);			
			preparedStatement1.executeUpdate();
			
			preparedStatement2 = connection.prepareStatement(SQLRequestManager.ADD_ACTIVE_ORDER);
			preparedStatement2.setInt(1, userId);			
			preparedStatement2.executeUpdate();
			
			connection.commit();
			
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				new DAOException("Can`t rollback",e1);
			}
			throw new DAOException("Error executing the query 'change_active_order_to_paid' or 'change_active_order_to_paid'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement1, preparedStatement2);
		}
		
	}

	@Override
	public List<Order> getPayedOrdersByUserId(int userId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Order> orderList = new ArrayList<Order>();

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_PAYED_ORDER_LIST_BY_USER_ID);
			preparedStatement.setInt(1, userId);
			Order order = null;
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				order = new Order();
				order.setId(resultSet.getInt(ColumnLabel.ORDER_ID));
				order.setDate(resultSet.getDate(ColumnLabel.ORDER_DATE));
				order.setStatus(OrderStatus.valueOf(resultSet.getString(ColumnLabel.ORDER_STATUS).toUpperCase()));
				order.setCost(resultSet.getFloat(ColumnLabel.ORDER_COST));
				orderList.add(order);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		return orderList;
	}

	@Override
	public boolean isFilmInActiveOrder(int userId, int filmId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.IS_FILM_IN_ACTIVE_ORDER);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, filmId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}else{
				return false;
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake in query 'get_client_film_list_by_client_id'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public boolean isFilmInPayedOrder(int userId, int filmId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.IS_FILM_IN_PAYED_ORDER);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, filmId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}else{
				return false;
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake in query 'get_client_film_list_by_client_id'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public void addActiveOrder(int userId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement2 = connection.prepareStatement(SQLRequestManager.ADD_ACTIVE_ORDER);
			preparedStatement2.setInt(1, userId);			
			preparedStatement2.executeUpdate();
			
			
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {

			throw new DAOException("Error executing the query 'change_active_order_to_paid' or 'change_active_order_to_paid'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement1);
		}
	}

}
