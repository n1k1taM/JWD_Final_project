package by.epam.vshop.service;

import by.epam.vshop.service.exception.ServiceException;

public interface InitializationSourceService {
	/**Method for initialization source when server is started.
	 * @throws ServiceException
	 */
	void initSource() throws ServiceException;
	/**Method for initialization source when server is shutting down.
	 * @throws ServiceException
	 */
	void destroySource() throws ServiceException;
}
