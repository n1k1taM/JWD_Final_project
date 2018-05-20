package by.epam.vshop.dao;

import java.util.List;

import by.epam.vshop.bean.Order;
import by.epam.vshop.dao.exception.DAOException;

public interface OrderDAO {

	int getActiveOrderId(int userId) throws DAOException;
	boolean isOrderHasFilm(int orderId, int filmId) throws DAOException;
	boolean isClientHasFilmInOrders(int userId, int filmId)throws DAOException;
	void addFilmToOrder(int orderId, int filmId) throws DAOException;
	Order getActiveOrder(int userId) throws DAOException;
	void deleteFilmFromActiveOrder(int filmId, int orderId)throws DAOException;
	float getActiveOrderCost(int userId) throws DAOException;
	void payOrder(float orderCostAfterDiscount, int userId)throws DAOException;
	List<Order> getPayedOrdersByUserId(int id) throws DAOException;
	boolean isFilmInActiveOrder(int userId, int filmId) throws DAOException;
	boolean isFilmInPayedOrder(int userId, int filmId) throws DAOException;
	void addActiveOrder(int id)throws DAOException;
	
	
	
	

}
