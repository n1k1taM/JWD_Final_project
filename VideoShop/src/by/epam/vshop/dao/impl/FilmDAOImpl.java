package by.epam.vshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
	
import by.epam.vshop.bean.Film;
import by.epam.vshop.bean.FilmRating;
import by.epam.vshop.bean.Genre;
import by.epam.vshop.dao.ColumnLabel;
import by.epam.vshop.dao.FilmDAO;
import by.epam.vshop.dao.connection.ConnectionPool;
import by.epam.vshop.dao.exception.ConnectionPoolException;
import by.epam.vshop.dao.exception.DAOException;
import by.epam.vshop.dao.manager.SQLRequestManager;

public class FilmDAOImpl implements FilmDAO {

	@Override
	public Film getFilm(int id) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Film film = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_FILM_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();

			film = new Film();
			film.setId(resultSet.getInt(ColumnLabel.FILM_ID));
			film.setCover(resultSet.getString(ColumnLabel.FILM_COVER));
			film.setShortDescription(resultSet.getString(ColumnLabel.FILM_SHORT_DESCRIPTION));
			film.setLongDescription(resultSet.getString(ColumnLabel.FILM_LONG_DESCRIPTION));
			film.setPrice(resultSet.getInt(ColumnLabel.FILM_PRICE));
			film.setReleaseYear(Year.of(resultSet.getInt(ColumnLabel.FILM_RELEASE_YEAR)));
			film.setStatus(resultSet.getBoolean(ColumnLabel.FLIM_STATUS));
			film.setTitle(resultSet.getString(ColumnLabel.FILM_TITLE));
			film.setTrailerURL(resultSet.getString(ColumnLabel.FILM_TRAILER_URL));
			film.setURL(resultSet.getString(ColumnLabel.FILM_URL));
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return film;
	}

	@Override
	public List<Genre> getGenreByFilmId(int id) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Genre> genreList = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_GENRE_LIST_BY_FILM_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			genreList = new ArrayList<Genre>();

			while (resultSet.next()) {
				Genre genre = new Genre();
				genre.setId(resultSet.getInt(ColumnLabel.GENRE_ID));
				genre.setName(resultSet.getString(ColumnLabel.GENRE_NAME));
				genreList.add(genre);
			}
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return genreList;
	}

	@Override
	public List<Film> getFilmListByGenreId(int genreId, int begin, int offset) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Film> filmList = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_FILM_LIST_BY_GENRE_ID);
			preparedStatement.setInt(1, genreId);
			preparedStatement.setInt(2, begin);
			preparedStatement.setInt(3, offset);
			resultSet = preparedStatement.executeQuery();
			filmList = new ArrayList<>();
			while (resultSet.next()) {
				Film film = new Film();
				film.setId(resultSet.getInt(ColumnLabel.FILM_ID));
				film.setCover(resultSet.getString(ColumnLabel.FILM_COVER));
				film.setShortDescription(resultSet.getString(ColumnLabel.FILM_SHORT_DESCRIPTION));
				film.setLongDescription(resultSet.getString(ColumnLabel.FILM_LONG_DESCRIPTION));
				film.setPrice(resultSet.getInt(ColumnLabel.FILM_PRICE));
				film.setReleaseYear(Year.of(resultSet.getInt(ColumnLabel.FILM_RELEASE_YEAR)));
				film.setStatus(resultSet.getBoolean(ColumnLabel.FLIM_STATUS));
				film.setTitle(resultSet.getString(ColumnLabel.FILM_TITLE));
				film.setTrailerURL(resultSet.getString(ColumnLabel.FILM_TRAILER_URL));
				film.setURL(resultSet.getString(ColumnLabel.FILM_URL));
				filmList.add(film);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return filmList;
	}

	@Override
	public List<Genre> getAllGenres() throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Genre> genreList = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLRequestManager.GET_ALL_GENRES);
			genreList = new ArrayList<>();
			while (resultSet.next()) {
				Genre genre = new Genre();
				genre.setId(resultSet.getInt(ColumnLabel.GENRE_ID));
				genre.setName(resultSet.getString(ColumnLabel.GENRE_NAME));
				genreList.add(genre);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, statement, resultSet);
		}
		return genreList;
	}

	@Override
	public float getFilmRating(int filmId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		float avgRating = 0;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_FILM_RATING_BY_FILM_ID);
			preparedStatement.setInt(1, filmId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				avgRating = resultSet.getFloat(ColumnLabel.FILM_RATING_VALUE);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return avgRating;
	}

	@Override
	public List<Film> getFilmList(int begin, int offset) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Film> filmList = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_FILM_LIST);
			preparedStatement.setInt(1, begin);
			preparedStatement.setInt(2, offset);
			resultSet = preparedStatement.executeQuery();
			filmList = new ArrayList<>();
			while (resultSet.next()) {
				Film film = new Film();
				film.setId(resultSet.getInt(ColumnLabel.FILM_ID));
				film.setCover(resultSet.getString(ColumnLabel.FILM_COVER));
				film.setShortDescription(resultSet.getString(ColumnLabel.FILM_SHORT_DESCRIPTION));
				film.setLongDescription(resultSet.getString(ColumnLabel.FILM_LONG_DESCRIPTION));
				film.setPrice(resultSet.getInt(ColumnLabel.FILM_PRICE));
				film.setReleaseYear(Year.of(resultSet.getInt(ColumnLabel.FILM_RELEASE_YEAR)));
				film.setStatus(resultSet.getBoolean(ColumnLabel.FLIM_STATUS));
				film.setTitle(resultSet.getString(ColumnLabel.FILM_TITLE));
				film.setTrailerURL(resultSet.getString(ColumnLabel.FILM_TRAILER_URL));
				film.setURL(resultSet.getString(ColumnLabel.FILM_URL));
				filmList.add(film);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return filmList;

	}

	@Override
	public int getFilmCounter() throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int filmCounter = 0;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLRequestManager.GET_FILM_COUNTER);

			resultSet.next();
			filmCounter = resultSet.getInt(1);

		} catch (ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, statement, resultSet);
		}
		return filmCounter;
	}

	@Override
	public int getFilmCounter(int genreId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int filmCounter = 0;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_FILM_COUNTER_BY_GENRE_ID);
			preparedStatement.setInt(1, genreId);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			filmCounter = resultSet.getInt(1);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return filmCounter;
	}

	@Override
	public Genre getGenreById(int id) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Genre genre = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_GENRE_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();

			genre = new Genre();
			genre.setId(resultSet.getInt(ColumnLabel.GENRE_ID));
			genre.setName(resultSet.getString(ColumnLabel.GENRE_NAME));

		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		return genre;
	}

	@Override
	public void addFilm(Film film) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.INSERT_FILM);

			preparedStatement.setString(1, film.getTitle());
			preparedStatement.setString(2, film.getCover());
			preparedStatement.setString(3, film.getURL());
			preparedStatement.setString(4, film.getTrailerURL());
			preparedStatement.setString(5, film.getReleaseYear().toString());
			preparedStatement.setFloat(6, film.getPrice());
			preparedStatement.setString(7, film.getShortDescription());
			preparedStatement.setString(8, film.getLongDescription());
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'insert_film'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}

	}

	@Override
	public void addGenreToFilm(int filmId, int genreId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.INSER_GENRE_TO_FILM);
			preparedStatement.setInt(1, filmId);
			preparedStatement.setInt(2, genreId);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'insert_genre_to_film'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}

	}

	@Override
	public int getFilmIdByTitle(String title) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_FILM_ID_BY_TITLE);
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			id = resultSet.getInt(ColumnLabel.FILM_ID);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		return id;
	}

	@Override
	public void editFilm(Film film) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.UPDATE_FILM);

			preparedStatement.setString(1, film.getTitle());
			preparedStatement.setString(2, film.getCover());
			preparedStatement.setString(3, film.getURL());
			preparedStatement.setString(4, film.getTrailerURL());
			preparedStatement.setString(5, film.getReleaseYear().toString());
			preparedStatement.setFloat(6, film.getPrice());
			preparedStatement.setString(7, film.getShortDescription());
			preparedStatement.setString(8, film.getLongDescription());
			preparedStatement.setInt(9, film.getId());
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'update_film'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}
	}

	@Override
	public void removeGenresByFilmId(int id) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.DELETE_GENRES_BY_FILMID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}
	}

	@Override
	public List<Film> getFilmListByOrderId(int orderId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Film> filmList = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_FILM_LIST_BY_ORDER_ID);
			preparedStatement.setInt(1, orderId);
			resultSet = preparedStatement.executeQuery();
			filmList = new ArrayList<>();
			while (resultSet.next()) {
				Film film = new Film();
				film.setId(resultSet.getInt(ColumnLabel.FILM_ID));
				film.setCover(resultSet.getString(ColumnLabel.FILM_COVER));
				film.setShortDescription(resultSet.getString(ColumnLabel.FILM_SHORT_DESCRIPTION));
				film.setLongDescription(resultSet.getString(ColumnLabel.FILM_LONG_DESCRIPTION));
				film.setPrice(resultSet.getInt(ColumnLabel.FILM_PRICE));
				film.setReleaseYear(Year.of(resultSet.getInt(ColumnLabel.FILM_RELEASE_YEAR)));
				film.setStatus(resultSet.getBoolean(ColumnLabel.FLIM_STATUS));
				film.setTitle(resultSet.getString(ColumnLabel.FILM_TITLE));
				film.setTrailerURL(resultSet.getString(ColumnLabel.FILM_TRAILER_URL));
				film.setURL(resultSet.getString(ColumnLabel.FILM_URL));
				filmList.add(film);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return filmList;
	}

	@Override
	public boolean isRatingExist(FilmRating filmRating) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.FIND_FILM_RATING_BY_FILM_ID_AND_USER_ID);

			preparedStatement.setInt(1, filmRating.getFilmId());
			preparedStatement.setInt(2, filmRating.getUserId());

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'find_film_rating_by_film_id_and_user_id'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

	}

	@Override
	public void updateRating(FilmRating filmRating) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.UPDATE_FILM_RATING);

			preparedStatement.setFloat(1, filmRating.getValue());
			preparedStatement.setInt(2, filmRating.getUserId());
			preparedStatement.setInt(3, filmRating.getFilmId());
			
			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'update_film_rating'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}

	}

	@Override
	public void addRating(FilmRating filmRating) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.INSERT_INTO_FILM_RATING);

			preparedStatement.setInt(1, filmRating.getFilmId());
			preparedStatement.setInt(2, filmRating.getUserId());
			preparedStatement.setFloat(3, filmRating.getValue());
			
			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'update_film_rating'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}
		
	}

	@Override
	public List<Film> getClientFilmList(int clientId, int begin, int offset) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Film> filmList = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_CLIENT_FILM_LIST_BY_CLIENT_ID);
			preparedStatement.setInt(1, clientId);
			preparedStatement.setInt(2, begin);
			preparedStatement.setInt(3, offset);
			resultSet = preparedStatement.executeQuery();
			filmList = new ArrayList<>();
			while (resultSet.next()) {
				Film film = new Film();
				film.setId(resultSet.getInt(ColumnLabel.FILM_ID));
				film.setCover(resultSet.getString(ColumnLabel.FILM_COVER));
				film.setShortDescription(resultSet.getString(ColumnLabel.FILM_SHORT_DESCRIPTION));
				film.setLongDescription(resultSet.getString(ColumnLabel.FILM_LONG_DESCRIPTION));
				film.setPrice(resultSet.getInt(ColumnLabel.FILM_PRICE));
				film.setReleaseYear(Year.of(resultSet.getInt(ColumnLabel.FILM_RELEASE_YEAR)));
				film.setStatus(resultSet.getBoolean(ColumnLabel.FLIM_STATUS));
				film.setTitle(resultSet.getString(ColumnLabel.FILM_TITLE));
				film.setTrailerURL(resultSet.getString(ColumnLabel.FILM_TRAILER_URL));
				film.setURL(resultSet.getString(ColumnLabel.FILM_URL));
				filmList.add(film);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake in query 'get_client_film_list_by_client_id'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return filmList;
	}

	@Override
	public int getClientFilmCounter(int clientId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int filmCounter = 0;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_CLIENT_FILM_COUNTER_BY_CLIENT_ID);
			preparedStatement.setInt(1, clientId);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			filmCounter = resultSet.getInt(1);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return filmCounter;
	}

	@Override
	public List<Film> getFilmsByOrderId(int orderId) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Film> filmList = null;
		
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_FILM_LIST_BY_GENRE_ID);
			preparedStatement.setInt(1, orderId);
			resultSet = preparedStatement.executeQuery();
			filmList = new ArrayList<Film>();
			
			while (resultSet.next()) {
				Film film = new Film();
				film.setId(resultSet.getInt(ColumnLabel.FILM_ID));
				film.setCover(resultSet.getString(ColumnLabel.FILM_COVER));
				film.setShortDescription(resultSet.getString(ColumnLabel.FILM_SHORT_DESCRIPTION));
				film.setLongDescription(resultSet.getString(ColumnLabel.FILM_LONG_DESCRIPTION));
				film.setPrice(resultSet.getInt(ColumnLabel.FILM_PRICE));
				film.setReleaseYear(Year.of(resultSet.getInt(ColumnLabel.FILM_RELEASE_YEAR)));
				film.setStatus(resultSet.getBoolean(ColumnLabel.FLIM_STATUS));
				film.setTitle(resultSet.getString(ColumnLabel.FILM_TITLE));
				film.setTrailerURL(resultSet.getString(ColumnLabel.FILM_TRAILER_URL));
				film.setURL(resultSet.getString(ColumnLabel.FILM_URL));
				filmList.add(film);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		return filmList;
	}

	@Override
	public boolean isGenreExest(int id) throws DAOException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			pool = ConnectionPool.getInstance();
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLRequestManager.GET_GENRE_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}else{
				return false;
			}


		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't get connection to data base", e);
		} catch (SQLException e) {
			throw new DAOException("SQL mistake", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		
	}
}

