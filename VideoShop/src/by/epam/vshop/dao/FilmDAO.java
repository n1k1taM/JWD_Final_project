package by.epam.vshop.dao;

import java.util.List;

import by.epam.vshop.bean.Film;
import by.epam.vshop.bean.FilmRating;
import by.epam.vshop.bean.Genre;
import by.epam.vshop.dao.exception.DAOException;

public interface FilmDAO {
	Film getFilm(int id) throws DAOException;

	List<Genre> getGenreByFilmId(int id) throws DAOException;

	List<Film> getFilmListByGenreId(int genreId, int begin, int offset) throws DAOException;

	List<Genre> getAllGenres() throws DAOException;

	float getFilmRating(int filmId) throws DAOException;

	List<Film> getFilmList(int begin, int offset) throws DAOException;

	int getFilmCounter() throws DAOException;

	int getFilmCounter(int genreId) throws DAOException;

	Genre getGenreById(int id) throws DAOException;

	void addFilm(Film film) throws DAOException;

	void addGenreToFilm(int filmId, int genreId) throws DAOException;
	
	int getFilmIdByTitle(String title) throws DAOException;

	void editFilm(Film film) throws DAOException;

	void removeGenresByFilmId(int id) throws DAOException;
	
	List<Film> getFilmListByOrderId(int orderId) throws DAOException;

	boolean isRatingExist(FilmRating filmRating) throws DAOException;

	void updateRating(FilmRating filmRating) throws DAOException;

	void addRating(FilmRating filmRating) throws DAOException;

	List<Film> getClientFilmList(int clientId, int begin, int offset) throws DAOException;

	int getClientFilmCounter(int clientId) throws DAOException;

	

	List<Film> getFilmsByOrderId(int orderId) throws DAOException;
	
}
