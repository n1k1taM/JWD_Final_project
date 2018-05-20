package by.epam.vshop.service.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.vshop.bean.User;
import by.epam.vshop.dao.connection.ConnectionPool;
import by.epam.vshop.dao.exception.ConnectionPoolException;
import by.epam.vshop.service.ServiceFactory;
import by.epam.vshop.service.UserService;
import by.epam.vshop.service.exception.ServiceException;

public class UserServiceImplTest {

	@BeforeClass
	public static void initializationConnectionPool() throws ConnectionPoolException {
		@SuppressWarnings("unused")
		ConnectionPool pool = ConnectionPool.getInstance();
	}

	@AfterClass
	public static void destroyConnectionPool() throws ConnectionPoolException, IOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.close();
	}

	@Test
	public void getUserList_shoudReturnUserList() throws ServiceException {
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		List<User> userList = userService.getUserList("1", "5");

		assertNotNull(userList);
		assert (userList.size() > 0);
	}

	@Test
	public void getUserList_shoudReturnOneUser() throws ServiceException {
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		List<User> userList = userService.getUserList("1", "1");

		assertNotNull(userList);
		assertEquals(userList.size(), 1);
	}

}
