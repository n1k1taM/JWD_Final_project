package by.epam.vshop.dao.manager;

public final class SQLRequestManager {

	private SQLRequestManager() {
		super();
	}

	public static final String GET_FILM_BY_ID = "SELECT * FROM `film` WHERE `f_id`= ?";
	public static final String GET_GENRE_LIST_BY_FILM_ID = "SELECT genre.g_id, genre.g_name FROM genre LEFT JOIN film_m2m_genre ON genre.g_id = film_m2m_genre.g_id WHERE f_id = ?";
	public static final String GET_FILM_LIST_BY_GENRE_ID = "SELECT * FROM film LEFT JOIN film_m2m_genre ON film.f_id = film_m2m_genre.f_id WHERE film_m2m_genre.g_id = ? ORDER BY film.f_title LIMIT ?, ?";
	public static final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE u_login = ? AND u_password = ?";
	public static final String GET_ALL_GENRES = "SELECT g_id, g_name FROM genre";
	public static final String GET_FILM_RATING_BY_FILM_ID = "SELECT avg(fr_value)as fr_value FROM film_rating JOIN film ON film_rating.f_id = film.f_id WHERE film.f_id = ?";
/*	public static final String GET_FILM_LIST = "SELECT * FROM film LEFT JOIN film_m2m_genre ON film.f_id = film_m2m_genre.f_id ORDER BY film.f_title LIMIT ?, ?";
*/	public static final String GET_FILM_LIST = "SELECT * FROM film ORDER BY film.f_title LIMIT ?, ?";

	public static final String GET_FILM_COUNTER = "SELECT count(f_id) FROM film WHERE f_status=1";
	public static final String GET_FILM_COUNTER_BY_GENRE_ID = "SELECT count(film.f_id)FROM film JOIN film_m2m_genre on film.f_id = film_m2m_genre.f_id where f_status = 1 AND film_m2m_genre.g_id = ?";
	public static final String GET_ACTIVE_COMMENTS_LIST_BY_FILM_ID = "SELECT c_id, f_id, `comment`.u_id, c_status, u_login, c_date, c_message FROM comment JOIN user ON user.u_id = `comment`.u_id WHERE f_id = ? ORDER BY c_date DESC LIMIT ?, ?";

	public static final String INSERT_USER = "INSERT INTO user (u_login, u_first_name, u_surname, u_email, u_password) VALUES (?,?,?,?,?)";
	public static final String GET_USER_LIST = "SELECT u_id, u_login, u_first_name, u_surname, u_email, u_password, u_role, u_status, d_id FROM user WHERE u_role = 'client' ORDER BY u_login DESC LIMIT ?, ?";
	public static final String INSERT_FILM = "INSERT INTO film (f_title, f_cover, f_url, f_trailer_url, f_release_year, f_price, f_short_description, f_long_description) VALUES (?,?,?,?,?,?,?,?)";
	public static final String GET_GENRE_BY_ID = "SELECT * FROM genre WHERE g_id = ?";
	public static final String INSER_GENRE_TO_FILM = "INSERT INTO film_m2m_genre (f_id, g_id) VALUES (?,?)";

	public static final String GET_FILM_ID_BY_TITLE = "SELECT * FROM film WHERE f_title = ?";

	
	public static final String GET_ACTIVE_ORDER_ID_BY_USER_ID = "SELECT o_id FROM `order` WHERE order.u_id = ? AND o_status = 'active'";
	public static final String GET_PAYED_ORDER_LIST_BY_USER_ID = "SELECT o_id, o_cost, o_date, o_status FROM `order` WHERE order.u_id = ? AND o_status = 'payed'";
	public static final String IS_ORDER_HAS_FILM = "SELECT * FROM order_has_film WHERE o_id = ? and f_id = ?";

	public static final String INSER_FILM_TO_ORDER = "INSERT INTO order_has_film (o_id, f_id) VALUES (?,?)";
	public static final String DELETE_FILM_FROM_ORDER = "DELETE FROM order_has_film where o_id = ? AND f_id = ?";

	public static final String UPDATE_FILM = "UPDATE film SET f_title = ?, f_cover = ?, f_url = ?, f_trailer_url = ?, f_release_year = ?, f_price = ?, f_short_description = ?, f_long_description = ? WHERE f_id = ?";
	public static final String DELETE_GENRES_BY_FILMID = "DELETE FROM film_m2m_genre where f_id = ?";
	public static final String GET_ACTIVE_ORDER_BY_USER_ID = "SELECT o_date, o_id, o_status, u_id FROM `order` where u_id = ? AND o_status ='active'";
	public static final String GET_FILM_LIST_BY_ORDER_ID = "SELECT film.f_id, f_title, f_short_description, f_long_description, f_release_year, f_trailer_url, f_url, f_price, f_status, f_cover FROM film 	right join order_has_film on film.f_id = order_has_film.f_id WHERE o_id = ?";
	
	public static final String GET_ALL_DISCOUNTS = "SELECT d_id, d_persent FROM discount";
	public static final String GET_DISCOUNT_PERSENT_BY_USER_ID = "SELECT d_persent FROM discount JOIN user ON user.d_id = discount.d_id WHERE user.u_id = ?;";
	public static final String GET_DISCOUNT_BY_USER_ID = "SELECT discount.d_id, d_persent FROM discount JOIN user ON user.d_id = discount.d_id WHERE user.u_id = ?;";
	public static final String ADD_COMMENT_TO_FILM = "INSERT INTO `comment`(f_id, u_id, c_date, c_message)VALUES(?, ?, ?, ?)";

	public static final String FIND_FILM_RATING_BY_FILM_ID_AND_USER_ID = "SELECT * FROM film_rating WHERE f_id = ? AND u_id = ?;";
	public static final String UPDATE_FILM_RATING = "UPDATE film_rating SET fr_value = ? WHERE u_id = ? AND f_id = ?";
	public static final String INSERT_INTO_FILM_RATING = "INSERT INTO film_rating(f_id, u_id, fr_value) VALUES(?, ?, ?)";
	public static final String GET_CLIENT_FILM_LIST_BY_CLIENT_ID = "SELECT * FROM film RIGHT JOIN order_has_film ON film.f_id = order_has_film.f_id LEFT JOIN `order` ON `order`.o_id = order_has_film.o_id WHERE `order`.u_id = ? AND `order`.o_status = 'payed' ORDER BY film.f_title LIMIT ?,?";
	public static final String UPDATE_CLIENT_DISCOUNT = "UPDATE user SET d_id = ? WHERE u_id = ? AND u_role='client'";
	public static final String GET_CLIENT_FILM_COUNTER_BY_CLIENT_ID = "SELECT count(film.f_id) FROM film RIGHT JOIN order_has_film ON film.f_id = order_has_film.f_id LEFT JOIN `order` ON `order`.o_id = order_has_film.o_id WHERE `order`.u_id = ? AND `order`.o_status = 'payed'";
	public static final String IS_CLIENT_HAS_FILM_IN_ORDER = "SELECT film.f_id FROM film RIGHT JOIN order_has_film ON film.f_id = order_has_film.f_id LEFT JOIN `order` ON `order`.o_id = order_has_film.o_id WHERE `order`.u_id = ? AND film.f_id =?";
	public static final String GET_USER_BY_USER_ID = "SELECT * FROM user WHERE u_id = ?";
	public static final String GET_ACTIVE_ORDER_COST_BY_USER_ID = "SELECT sum(film.f_price) FROM film RIGHT JOIN order_has_film ON film.f_id = order_has_film.f_id LEFT JOIN `order` ON `order`.o_id = order_has_film.o_id WHERE `order`.u_id = ? AND `order`.o_status = 'active'";
	
	public static final String CHANGE_ACTIVE_ORDER_TO_PAID = "UPDATE `order` SET o_status='payed', o_cost=?, o_date=? WHERE u_id = ? AND o_status='active'";
	public static final String ADD_ACTIVE_ORDER = "INSERT INTO `order`(u_id, o_status) VALUES (?, 'active')";
	public static final String UPDATE_USER_STATUS = "UPDATE user SET u_status = ? WHERE u_id = ?";
	public static final String IS_FILM_IN_ACTIVE_ORDER = "SELECT f_id FROM `order` JOIN order_has_film ON `order`.o_id = order_has_film.o_id AND `order`.o_status = 'active' AND `order`.u_id =? AND order_has_film.f_id = ?";
	public static final String IS_FILM_IN_PAYED_ORDER = "SELECT f_id FROM `order` JOIN order_has_film ON `order`.o_id = order_has_film.o_id AND `order`.o_status = 'payed' AND `order`.u_id =? AND order_has_film.f_id = ?";
	
	

}
