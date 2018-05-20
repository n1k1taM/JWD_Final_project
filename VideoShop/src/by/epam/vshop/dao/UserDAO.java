package by.epam.vshop.dao;

import java.util.List;

import by.epam.vshop.bean.Discount;
import by.epam.vshop.bean.User;
import by.epam.vshop.dao.exception.DAOException;

public interface UserDAO {
	boolean isUserExist(String login, String password) throws DAOException;

	User getUser(String login, String password) throws DAOException;

	void signUp(User user) throws DAOException;
	
	List<User> getUserList(int begin, int offset) throws DAOException;

	int getDiscountValue(int userId) throws DAOException;

	void updateClientDiscount(int clientId, int discountId) throws DAOException;

	User getUser(Integer userId) throws DAOException;

	Discount getDiscount(int userId) throws DAOException;

	List<Discount> getAllDiscounts() throws DAOException;

	void changeUserStatus(int userId, boolean userStatus) throws DAOException;
}
