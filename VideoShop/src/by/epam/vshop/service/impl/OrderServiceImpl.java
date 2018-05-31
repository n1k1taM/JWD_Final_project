package by.epam.vshop.service.impl;

import java.util.List;

import by.epam.vshop.bean.Film;
import by.epam.vshop.bean.Order;
import by.epam.vshop.bean.OrderStatus;
import by.epam.vshop.dao.DAOFactory;
import by.epam.vshop.dao.OrderDAO;
import by.epam.vshop.dao.UserDAO;
import by.epam.vshop.dao.exception.DAOException;
import by.epam.vshop.service.OrderService;
import by.epam.vshop.service.exception.ServiceException;
import by.epam.vshop.service.validation.DataValidater;

public class OrderServiceImpl implements OrderService{

	@Override
	public void addFilmToActiveOrder(String strFilmId, int userId) throws ServiceException {
		DataValidater.validatePositiveInteger(strFilmId);
		int filmId = Integer.parseInt(strFilmId);
				
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO =  daoFactory.getOrderDAO();
				
		try {
			int activeOrderId = orderDAO.getActiveOrderId(userId);
			if (orderDAO.isClientHasFilmInOrders(userId, filmId)) {
				throw new ServiceException("Duplicate film in order: orderId = " + activeOrderId + ", filmId = " + filmId);
			}
			orderDAO.addFilmToOrder(activeOrderId, filmId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public Order getActiveOrder(int userId) throws ServiceException{
		if (!DataValidater.validatePositiveInteger(String.valueOf(userId))) {
			throw new ServiceException("Incorrect parameter 'userId'");
		}
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		
		
		Order order = null;
		try {
			order = orderDAO.getActiveOrder(userId);
		} catch (DAOException e) {
			new ServiceException(e);
		}
		
		return order;
	}

	@Override
	public float getCostBeforeDiscount(Order order) throws ServiceException {
		float cost = 0;
		List<Film> filmList = order.getFilms();
		for (Film film : filmList) {
			cost += film.getPrice();
		}
		if(cost == 0){
			throw new ServiceException("Order is empty");
		}
		return cost;
	}

	@Override
	public float getOrderCostAfterDiscount(float orderCostBeforeDiscount, int discount) throws ServiceException {
		if (discount == 0) {
			return orderCostBeforeDiscount;
		}
		return (orderCostBeforeDiscount * (100-discount)) / 100;
	}

	@Override
	public int getActiveOrderId(int userId) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		int orderId = 0;
		try {
			orderId = orderDAO.getActiveOrderId(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return orderId;
	}

	@Override
	public void deleteFilmFromActiveOrder(String strFilmId, int orderId) throws ServiceException {
		if (!DataValidater.validatePositiveInteger(strFilmId)) {
			throw new ServiceException("Incorrect request parameter");
		}
		int filmId = Integer.parseInt(strFilmId);
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		
		try {
			if (!orderDAO.isOrderHasFilm(orderId, filmId)) {
				throw new ServiceException("Active order doesn`t have film");
			}
			orderDAO.deleteFilmFromActiveOrder(filmId, orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Order> getPayedOrdersByUserId(String strUserId) throws ServiceException {
		if (!DataValidater.validatePositiveInteger(strUserId)) {
			throw new ServiceException("Incorrect parameter 'userId'");
		}
		int userId = Integer.parseInt(strUserId);

		DAOFactory factory = DAOFactory.getInstance();
		OrderDAO orderDAO = factory.getOrderDAO();
		List<Order> orderList = null;

		try {
			orderList = orderDAO.getPayedOrdersByUserId(userId);
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return orderList;
	}

	@Override
	public void payOrder(int userId, String strPaymentValue) throws ServiceException {
		if (!DataValidater.validatePositiveFloat(strPaymentValue)) {
			throw new ServiceException("Incorrect value of parameter 'paymentValue'");
		}
		float paymentValue = Float.parseFloat(strPaymentValue);
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try {
			float orderCostBeforeDiscount = orderDAO.getActiveOrderCost(userId);
			int discount = userDAO.getDiscountValue(userId);
			float orderCostAfterDiscount = orderCostBeforeDiscount*(100-discount)/100;
			if ((orderCostAfterDiscount != paymentValue) ||(orderCostAfterDiscount == 0)) {
				throw new ServiceException("Incorrect value of parameter 'paymentValue'");
			}
			
			orderDAO.payOrder(orderCostAfterDiscount, userId);			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public OrderStatus getOrderStatus(int userId, String strFilmId) throws ServiceException {
		if (!DataValidater.validatePositiveInteger(userId)) {
			throw new ServiceException("Incorrext parameter 'userId'");
		}
		if (!DataValidater.validatePositiveInteger(strFilmId)) {
			throw new ServiceException("Incorrext parameter 'filmId'");
		}
		
		int filmId = Integer.parseInt(strFilmId);
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
				
		try {
			if (orderDAO.isFilmInActiveOrder(userId,filmId)) {
				return OrderStatus.ACTIVE;
			}
			if (orderDAO.isFilmInPayedOrder(userId,filmId)) {
				return OrderStatus.PAYED;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return null;
	}

	@Override
	public boolean isFilmInPayedOrder(int userId, String strFilmId) throws ServiceException {
		if (!DataValidater.validatePositiveInteger(userId)) {
			throw new ServiceException("Incorrext parameter 'userId'");
		}
		if (!DataValidater.validatePositiveInteger(strFilmId)) {
			throw new ServiceException("Incorrext parameter 'filmId'");
		}
		
		int filmId = Integer.parseInt(strFilmId);
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		
		try {
			return orderDAO.isFilmInPayedOrder(userId, filmId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
}
