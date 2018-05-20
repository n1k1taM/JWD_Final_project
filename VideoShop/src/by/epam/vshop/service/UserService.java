package by.epam.vshop.service;

import java.util.List;

import by.epam.vshop.bean.Discount;
import by.epam.vshop.bean.User;
import by.epam.vshop.service.exception.ServiceException;

public interface UserService {
	public static final int DEFAULT_USER_PER_PAGE = 5;
	public static final int DEFAULT_PAGE_NUMBER = 5;
	
	User singIn(String login, String password) throws ServiceException;
	
	void signUp(String login, String firstname, String lastname, String email, String password, String confirmPassword) throws ServiceException;
	
	List<User> getUserList(String strPageNumber, String strFilmsPerPage) throws  ServiceException;

	int getDiscountValue(int userId) throws ServiceException;
	
	Discount getDiscount(int userId) throws ServiceException;

	void addDiscountToClient(String strClientId, String strDiscountId) throws ServiceException;

	User getUser(String strUserId) throws ServiceException;

	List<Discount> getAllDiscounts() throws ServiceException;

	void changeUserStatus(String strUserId, String strUserStatus) throws ServiceException;
}
