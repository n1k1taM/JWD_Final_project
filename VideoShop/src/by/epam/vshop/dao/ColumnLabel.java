package by.epam.vshop.dao;

public final class ColumnLabel {

	private ColumnLabel() {
		super();
	}

	/*
	 * Column labels for Film table
	 */
	public static final String FILM_ID = "f_id";
	public static final String FILM_TITLE = "f_title";
	public static final String FILM_SHORT_DESCRIPTION = "f_short_description";
	public static final String FILM_LONG_DESCRIPTION = "f_long_description";
	public static final String FILM_RELEASE_YEAR = "f_release_year";
	public static final String FILM_TRAILER_URL = "f_trailer_url";
	public static final String FILM_URL = "f_url";
	public static final String FILM_PRICE = "f_price";
	public static final String FLIM_STATUS = "f_status";
	public static final String FILM_COVER = "f_cover";

	/*
	 * Column labels in Data Base for User table
	 */

	public static final String USER_ID = "u_id";
	public static final String USER_LOGIN = "u_login";
	public static final String USER_PASSWORD = "u_password";
	public static final String USER_EMAIL = "u_email";
	public static final String USER_ROLE = "u_role";
	public static final String USER_STATUS = "u_status";
	public static final String USER_FIRST_NAME = "u_first_name";
	public static final String USER_SURNAME = "u_surname";
	public static final String USER_DISCOUNT_ID = "d_id";

	/*
	 * Column labels in Data Base for table Genre
	 */

	public static final String GENRE_ID = "g_id";
	public static final String GENRE_NAME = "g_name";

	/*
	 * Column labels in Data Base for table film_rating
	 */

	public static final String FILM_RATING_ID = "fr_id";
	public static final String FILM_RATING_VALUE = "fr_value";

	/*
	 * Column labels in Data Base for table comment
	 */

	public static final String COMMENT_ID = "c_id";
	public static final String COMMENT_MESSAGE = "c_message";
	public static final String COMMENT_STATUS = "c_status";
	public static final String COMMENT_DATE = "c_date";
	
	/*
	 * Column labels in Data Base for table 'order'
	 */
	public static final String ORDER_ID = "o_id";
	public static final String ORDER_DATE = "o_date";
	public static final String ORDER_COST = "o_cost";
	public static final String ORDER_STATUS = "o_status";
	
	/*
	 * Column labels in Data Base for table 'discount'
	 */
	public static final String DISCOUNT_PERSENT = "d_persent";
	public static final String DISCOUNT_ID = "d_id";
	
	
	
}
