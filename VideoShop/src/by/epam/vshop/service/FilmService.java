package by.epam.vshop.service;

import java.util.List;

import by.epam.vshop.bean.Film;
import by.epam.vshop.bean.Genre;
import by.epam.vshop.service.exception.ServiceException;

public interface FilmService {

	Film getFilm(String strFilmId) throws ServiceException;

	List<Film> getFilmListByGenreId(String strId, String strPageNumber) throws ServiceException;

	List<Genre> getAllGenres() throws ServiceException;

	List<Film> getFilmList(String strPageNumber) throws ServiceException;

	int getMaxPageNumber() throws ServiceException;

	int getMaxPageNumberbyGenreId(String strGenreId) throws ServiceException;

	void addFilm(String title, String cover, String url, String trailerUrl, String year, String price,
			String shortDescription, String longDescription, String[] genres) throws ServiceException;

	void editFilm(String id, String title, String cover, String url, String trailerUrl, String year, String price,
			String shortDescription, String longDescription, String[] genres) throws ServiceException;

	List<Film> getFilmListByOrderId(int orderId) throws ServiceException;

	void addRating(int userId, String strFilmId, String strRatingValue) throws ServiceException;

	List<Film> getClientFilmList(String strClientId, String pageNumber) throws ServiceException;

	int getClientFilmsMaxPage(String strClientId) throws ServiceException;
	
	

	
}
