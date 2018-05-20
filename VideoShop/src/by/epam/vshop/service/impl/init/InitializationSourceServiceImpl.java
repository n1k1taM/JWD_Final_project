package by.epam.vshop.service.impl.init;

import by.epam.vshop.dao.DAOFactory;
import by.epam.vshop.dao.InitializationSourceDAO;
import by.epam.vshop.dao.exception.DAOException;
import by.epam.vshop.service.InitializationSourceService;
import by.epam.vshop.service.exception.ServiceException;

public class InitializationSourceServiceImpl implements InitializationSourceService{

	@Override
	public void initSource() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		InitializationSourceDAO initializationDAO = daoFactory.getInitializationSourceDAO();
		
		try {
			initializationDAO.initSource();
		} catch (DAOException e) {
			throw new ServiceException("Error initialization source",e);
		}
	
	}

	@Override
	public void destroySource() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		InitializationSourceDAO initializationDAO = daoFactory.getInitializationSourceDAO();
		
		try {
			initializationDAO.destroySource();
		} catch (DAOException e) {
			throw new ServiceException("Error close connections",e);
		}
		
	}

}
