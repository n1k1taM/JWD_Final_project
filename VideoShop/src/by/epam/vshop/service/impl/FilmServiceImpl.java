package by.epam.vshop.service.impl;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import by.epam.vshop.bean.Film;
import by.epam.vshop.bean.FilmRating;
import by.epam.vshop.bean.Genre;
import by.epam.vshop.dao.DAOFactory;
import by.epam.vshop.dao.FilmDAO;
import by.epam.vshop.dao.exception.DAOException;
import by.epam.vshop.service.FilmService;
import by.epam.vshop.service.exception.ServiceException;
import by.epam.vshop.service.validation.DataValidater;
import by.epam.vshop.service.validation.FilmValidator;

public class FilmServiceImpl implements FilmService {
	public static final int DEFAULT_FILMS_PER_PAGE = 4;
	public static final int DEFAULT_PAGE_NUMBER = 1;

	@Override
	public Film getFilm(String strFilmId) throws ServiceException {
		if (!(DataValidater.validatePositiveInteger(strFilmId))) {
			throw new ServiceException("Incorrect request`s parameters");
		}
		int filmId = Integer.parseInt(strFilmId);

		DAOFactory daoFactory = DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();
		try {
			Film film = filmDAO.getFilm(filmId);
			if (film != null) {
				film.setGenreList(filmDAO.getGenreByFilmId(film.getId()));
				film.setRating(filmDAO.getFilmRating(filmId));
			}
			return film;
		} catch (DAOException e) {
			throw new ServiceException("Can't show film detales by id", e);
		}
	}

	@Override
	public List<Film> getFilmListByGenreId(String strId, String strPageNumber, String strMaxPageNumber) throws ServiceException {

		if (!DataValidater.validatePositiveIntegers(strId, strPageNumber, strMaxPageNumber)) {
			throw new ServiceException("Incorrect request`s parameters");
		}
		int id = Integer.parseInt(strId);
		int pageNumber = Integer.parseInt(strPageNumber);
		int maxPageNumber = Integer.parseInt(strMaxPageNumber);
		if (pageNumber > maxPageNumber) {
			throw new ServiceException("Incorrect page number: pageNumber = "+ pageNumber + "; maxPageNumber = " + maxPageNumber);
		}
		int filmsPerPage = DEFAULT_FILMS_PER_PAGE;
		if (pageNumber == 0) {
			filmsPerPage = DEFAULT_PAGE_NUMBER;
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();
		List<Film> filmsList = new ArrayList<>();

		int numberFirstFilmOnPage = (pageNumber - 1) * filmsPerPage;
		try {
			if(!filmDAO.isGenreExest(id)){
				throw new ServiceException("Genre isn`t exest: genre id = " + id);
			}
			filmsList = filmDAO.getFilmListByGenreId(id, numberFirstFilmOnPage, filmsPerPage);
			for (Film film : filmsList) {
				film.setGenreList(filmDAO.getGenreByFilmId(film.getId()));
				film.setRating(filmDAO.getFilmRating(film.getId()));
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return filmsList;
	}

	@Override
	public List<Genre> getAllGenres() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();
		List<Genre> genreList = null;
		try {
			genreList = filmDAO.getAllGenres();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return genreList;
	}

	@Override
	public List<Film> getFilmList(String strPageNumber, String strMaxPageNumber) throws ServiceException {
		if (!DataValidater.validatePositiveIntegers(strPageNumber, strMaxPageNumber)) {
			throw new ServiceException("Incorrect request`s parameters");
		}
		int pageNumber = Integer.parseInt(strPageNumber);
		int maxPageNumber = Integer.parseInt(strMaxPageNumber);
		int filmsPerPage = DEFAULT_FILMS_PER_PAGE;
		if (pageNumber == 0) {
			filmsPerPage = DEFAULT_PAGE_NUMBER;
		}
		if (pageNumber > maxPageNumber) {
			throw new ServiceException("Incorrect page number: pageNumber = "+ pageNumber + "; maxPageNumber = " + maxPageNumber);
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();
		List<Film> filmsList = new ArrayList<>();

		int numberFirstFilmOnPage = (pageNumber - 1) * filmsPerPage;
		try {
			filmsList = filmDAO.getFilmList(numberFirstFilmOnPage, filmsPerPage);
			for (Film film : filmsList) {
				film.setGenreList(filmDAO.getGenreByFilmId(film.getId()));
				film.setRating(filmDAO.getFilmRating(film.getId()));
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return filmsList;
	}

	@Override
	public int getMaxPageNumber() throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();

		try {
			int filmCounter = filmDAO.getFilmCounter();
			int maxPageNumber = filmCounter / DEFAULT_FILMS_PER_PAGE;
			if (filmCounter % DEFAULT_FILMS_PER_PAGE != 0) {
				maxPageNumber++;
			}
			return maxPageNumber;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int getMaxPageNumberbyGenreId(String strGenreId) throws ServiceException {

		if (!DataValidater.validatePositiveInteger(strGenreId)) {
			throw new ServiceException("Incorrect request`s parameters");
		}
		int genreId = Integer.parseInt(strGenreId);

		DAOFactory daoFactory = DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();

		try {
			int filmCounter = filmDAO.getFilmCounter(genreId);
			int maxPageNumber = filmCounter / DEFAULT_FILMS_PER_PAGE;
			if (filmCounter % DEFAULT_FILMS_PER_PAGE != 0) {
				maxPageNumber++;
			}
			return maxPageNumber;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addFilm(String title, String cover, String url, String trailerUrl, String year, String price,
			String shortDescription, String longDescription, String[] genres) throws ServiceException {
		if (!FilmValidator.validFilm(title, cover, url, trailerUrl, year, price, shortDescription, longDescription,
				genres)) {
			throw new ServiceException("Invalid film data");
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();

		Film film = new Film();
		film.setTitle(title);
		film.setCover(cover);
		film.setURL(url);
		film.setTrailerURL(trailerUrl);
		film.setReleaseYear(Year.parse(year));
		film.setPrice(Integer.parseInt(price));
		film.setShortDescription(shortDescription);
		film.setLongDescription(longDescription);

		try {
			filmDAO.addFilm(film);
			int filmId = filmDAO.getFilmIdByTitle(film.getTitle());
			for (String genreId : genres) {
				filmDAO.addGenreToFilm(filmId, Integer.parseInt(genreId));
			}
		} catch (DAOException e) {
			throw new ServiceException("Error of executing action", e);
		}

	}

	@Override
	public void editFilm(String id, String title, String cover, String url, String trailerUrl, String year,
			String price, String shortDescription, String longDescription, String[] genres) throws ServiceException {

		if (!FilmValidator.validFilm(title, cover, url, trailerUrl, year, price, shortDescription, longDescription,
				genres, id)) {
			throw new ServiceException("Invalid film data");
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();

		Film film = new Film();
		film.setId(Integer.parseInt(id));
		film.setTitle(title);
		film.setCover(cover);
		film.setURL(url);
		film.setTrailerURL(trailerUrl);
		film.setReleaseYear(Year.parse(year));
		film.setPrice(Float.parseFloat(price));
		film.setShortDescription(shortDescription);
		film.setLongDescription(longDescription);

		try {
			filmDAO.editFilm(film);
			filmDAO.removeGenresByFilmId(Integer.parseInt(id));
			for (String genreId : genres) {
				filmDAO.addGenreToFilm(Integer.parseInt(id), Integer.parseInt(genreId));
			}
		} catch (DAOException e) {
			throw new ServiceException("Error of executing action", e);
		}
	}

	@Override
	public List<Film> getFilmListByOrderId(int orderId) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();
		List<Film> filmList = null;
		
		try {
			filmList = filmDAO.getFilmListByOrderId(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		
		return filmList;
	}

	@Override
	public void addRating(int userId, String strFilmId, String strRatingValue) throws ServiceException {
		if (!DataValidater.validatePositiveInteger(strFilmId)|| !DataValidater.validatePositiveFloat(strRatingValue)) {
			throw new ServiceException("Incorrect request parameters");
		}
		
		int filmId = Integer.parseInt(strFilmId);
		float ratingValue = Float.parseFloat(strRatingValue);
		
		DAOFactory daoFactory= DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();
		
		FilmRating filmRating = new FilmRating();
		filmRating.setFilmId(filmId);
		filmRating.setUserId(userId);
		filmRating.setValue(ratingValue);
		
		try {
			if (filmDAO.isRatingExist(filmRating)) {
				filmDAO.updateRating(filmRating);
			}else {
				filmDAO.addRating(filmRating);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		
		
	}

	@Override
	public List<Film> getClientFilmList(String strClientId, String strPageNumber) throws ServiceException {
		if (!DataValidater.validatePositiveIntegers(strClientId, strPageNumber)) {
			throw new ServiceException("Incorrect request parameters");
		}
		
		int clientId = Integer.parseInt(strClientId);
		int pageNumber  = Integer.parseInt(strPageNumber);
		
		int filmsPerPage = DEFAULT_FILMS_PER_PAGE;
		if (pageNumber == 0) {
			filmsPerPage = DEFAULT_PAGE_NUMBER;
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();
		List<Film> filmsList = new ArrayList<>();

		int numberFirstFilmOnPage = (pageNumber - 1) * filmsPerPage;
		try {
			filmsList = filmDAO.getClientFilmList(clientId, numberFirstFilmOnPage, filmsPerPage);
			for (Film film : filmsList) {
				film.setGenreList(filmDAO.getGenreByFilmId(film.getId()));
				film.setRating(filmDAO.getFilmRating(film.getId()));
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return filmsList;
	}

	@Override
	public int getClientFilmsMaxPage(String strClientId) throws ServiceException {
		if (!DataValidater.validatePositiveInteger(strClientId)) {
			throw new ServiceException("Incorrect request`s parameters");
		}
		int clientId = Integer.parseInt(strClientId);

		DAOFactory daoFactory = DAOFactory.getInstance();
		FilmDAO filmDAO = daoFactory.getFilmDAO();

		try {
			int filmCounter = filmDAO.getClientFilmCounter(clientId);
			int maxPageNumber = filmCounter / DEFAULT_FILMS_PER_PAGE;
			if (filmCounter % DEFAULT_FILMS_PER_PAGE != 0) {
				maxPageNumber++;
			}
			return maxPageNumber;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
