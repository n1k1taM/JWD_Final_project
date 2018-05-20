package by.epam.vshop.service;

import java.util.List;

import by.epam.vshop.bean.Order;
import by.epam.vshop.bean.OrderStatus;
import by.epam.vshop.service.exception.ServiceException;

public interface OrderService {
	void addFilmToActiveOrder(String strFilmId, int userId)throws ServiceException;

	Order getActiveOrder(int userId) throws ServiceException;
	int getActiveOrderId(int userId) throws ServiceException;
	

	float getCostBeforeDiscount(Order order) throws ServiceException;

	float getOrderCostAfterDiscount(float orderCostBeforeDiscount, int discount)throws ServiceException;

	void deleteFilmFromActiveOrder(String filmId, int orderId) throws ServiceException;

	void payOrder(int userId, String strPaymentValue) throws ServiceException;

	List<Order> getPayedOrdersByUserId(String id) throws ServiceException;
	
	OrderStatus getOrderStatus(int userId, String strFilmId) throws ServiceException;
}
