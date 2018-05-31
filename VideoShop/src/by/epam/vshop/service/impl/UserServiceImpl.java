package by.epam.vshop.service.impl;

import java.util.List;

import by.epam.vshop.bean.Discount;
import by.epam.vshop.bean.User;
import by.epam.vshop.dao.DAOFactory;
import by.epam.vshop.dao.OrderDAO;
import by.epam.vshop.dao.UserDAO;
import by.epam.vshop.dao.exception.DAOException;
import by.epam.vshop.service.UserService;
import by.epam.vshop.service.exception.ServiceException;
import by.epam.vshop.service.validation.DataValidater;
import by.epam.vshop.service.validation.UserValidator;

public class UserServiceImpl implements UserService {

	@Override
	public User singIn(String login, String password) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();

		User user = null;
		try {
			if (!userDAO.isUserExist(login, password)) {
				throw new ServiceException("Incorrect login or password");
			}
			user = userDAO.getUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException("User signIn mistake");
		}

		if (user == null) {
			throw new ServiceException("User is null");
		}

		return user;
	}

	@Override
	public void signUp(String login, String firstname, String lastname, String email, String password,
			String confirmPassword) throws ServiceException {
		if (!UserValidator.validUser(login, firstname, lastname, email, password, confirmPassword)) {
			throw new ServiceException("Invalid input register data");
		}

		User user = new User(login, password, email, firstname, lastname);

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		OrderDAO orderDAO = factory.getOrderDAO();

		try {
			userDAO.signUp(user);
			user = userDAO.getUser(user.getLogin(), user.getPassword());
			user = userDAO.getUser(login, password);
			orderDAO.addActiveOrder(user.getId());
		} catch (DAOException e) {
			throw new ServiceException("User signup mistake");
		}
	}

	@Override
	public List<User> getUserList(String strPageNumber, String strFilmsPerPage) throws ServiceException {
		if (!DataValidater.validatePositiveIntegers(strPageNumber, strFilmsPerPage)) {
			throw new ServiceException("Incorrect request`s parameters");
		}
		int pageNumber = Integer.parseInt(strPageNumber);
		int userPerPage = Integer.parseInt(strFilmsPerPage);
		if (userPerPage == 0) {
			userPerPage = DEFAULT_USER_PER_PAGE;
		}
		if (pageNumber == 0) {
			userPerPage = DEFAULT_PAGE_NUMBER;
		}

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		List<User> userList = null;

		int numberFirstUserOnPage = (pageNumber - 1) * userPerPage;

		try {
			userList = userDAO.getUserList(numberFirstUserOnPage, userPerPage);
		} catch (DAOException e) {
			throw new ServiceException("Get user list mistake", e);
		}

		if (userList == null) {
			throw new ServiceException("User list is null");
		}

		return userList;
	}

	@Override
	public int getDiscountValue(int userId)throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		int discount = 0;
		try {
			discount = userDAO.getDiscountValue(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		// TODO Auto-generated method stub
		return discount;
	}

	@Override
	public void addDiscountToClient(String strClientId, String strDiscountId) throws ServiceException {
		if (!DataValidater.validatePositiveIntegers(strClientId, strDiscountId)) {
			throw new ServiceException("Incorrect request`s parameters");
		}
		int clientId = Integer.parseInt(strClientId);
		int discountId = Integer.parseInt(strDiscountId);
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try {
			userDAO.updateClientDiscount(clientId, discountId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}

	@Override
	public User getUser(String strUserId) throws ServiceException {
		if (!DataValidater.validatePositiveInteger(strUserId)) {
			throw new ServiceException("Incorrect parameter 'userId'");
		}
		Integer userId = Integer.parseInt(strUserId);
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		User user = null;

		try {
			user = userDAO.getUser(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public Discount getDiscount(int userId) throws ServiceException {
		if (!DataValidater.validatePositiveInteger(userId)) {
			throw new ServiceException("Incorrect parameter 'userId'");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try {
			Discount discount =	userDAO.getDiscount(userId);
			return discount;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Discount> getAllDiscounts() throws ServiceException {
				
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try {
			List<Discount> discount =	userDAO.getAllDiscounts();
			return discount;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void changeUserStatus(String strUserId, String strUserStatus) throws ServiceException {
		if (!DataValidater.validatePositiveInteger(strUserId) ) {
			new ServiceException("Incorrect parameter 'userId'");
		}
		if (!DataValidater.validateBoolean(strUserStatus)) {
			new ServiceException("Incorrect parameter 'userStatus'");
		}
		
		int userId = Integer.parseInt(strUserId);
		boolean userStatus = Boolean.parseBoolean(strUserStatus);
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try {
			userDAO.changeUserStatus(userId, userStatus);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}

}
