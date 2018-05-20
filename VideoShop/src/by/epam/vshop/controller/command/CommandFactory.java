package by.epam.vshop.controller.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import by.epam.vshop.controller.command.admin.AddDiscountToClient;
import by.epam.vshop.controller.command.admin.ChangeUserStatus;
import by.epam.vshop.controller.command.admin.GetUserList;
import by.epam.vshop.controller.command.admin.ShowUserAccountForAdmin;
import by.epam.vshop.controller.command.film.AddFilm;
import by.epam.vshop.controller.command.film.EditFilm;
import by.epam.vshop.controller.command.film.GetGenreList;
import by.epam.vshop.controller.command.film.LoadFilmOnEditForm;
import by.epam.vshop.controller.command.film.ShowFilm;
import by.epam.vshop.controller.command.film.ShowFilmsByGenre;
import by.epam.vshop.controller.command.init.DestroySource;
import by.epam.vshop.controller.command.init.InitializationSource;
import by.epam.vshop.controller.command.user.AddComment;
import by.epam.vshop.controller.command.user.AddFilmToActiveOrder;
import by.epam.vshop.controller.command.user.AddRating;
import by.epam.vshop.controller.command.user.DeleteFilmFromActiveOrder;
import by.epam.vshop.controller.command.user.GetUserPaymentHistory;
import by.epam.vshop.controller.command.user.Logout;
import by.epam.vshop.controller.command.user.PayOrder;
import by.epam.vshop.controller.command.user.ShowActiveOrder;
import by.epam.vshop.controller.command.user.ShowClientFilmList;
import by.epam.vshop.controller.command.user.SignIn;
import by.epam.vshop.controller.command.user.SignUp;
import by.epam.vshop.controller.command.user.WatchFilm;
import by.epam.vshop.controller.command.user.ShowMainPage;
import by.epam.vshop.controller.command.user.ShowPaymentPage;
import by.epam.vshop.controller.command.user.ShowUserAccount;

public class CommandFactory {
	private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);
	private final static CommandFactory instance = new CommandFactory();
	Map<CommandName, Command> repository = new HashMap<>();

	public CommandFactory() {
		super();
		repository.put(CommandName.SHOW_FILMS_BY_GENRE, new ShowFilmsByGenre());
		repository.put(CommandName.SHOW_FILM, new ShowFilm());
		repository.put(CommandName.INITIALIZATION_SOURCE, new InitializationSource());
		repository.put(CommandName.SING_IN, new SignIn());
		repository.put(CommandName.LOGOUT, new Logout());
		repository.put(CommandName.SHOW_MAIN_PAGE, new ShowMainPage());
		repository.put(CommandName.SIGN_UP, new SignUp());
		repository.put(CommandName.DESTROY_SOURCE, new DestroySource());
		repository.put(CommandName.ADD_FILM, new AddFilm());
		repository.put(CommandName.GET_GENRE_LIST, new GetGenreList());
		repository.put(CommandName.ADD_FILM_TO_ACTIVE_ORDER, new AddFilmToActiveOrder());
		repository.put(CommandName.LOAD_FILM_ON_EDIT_PAGE, new LoadFilmOnEditForm());
		repository.put(CommandName.EDIT_FILM, new EditFilm());
		repository.put(CommandName.SHOW_ACTIVE_ORDER, new ShowActiveOrder());
		repository.put(CommandName.DELETE_FILM_FROM_ACTIVE_ORDER, new DeleteFilmFromActiveOrder());
		repository.put(CommandName.ADD_COMMENT, new AddComment());
		repository.put(CommandName.ADD_RATING, new AddRating());
		repository.put(CommandName.SHOW_CLIENT_FILM_LIST, new ShowClientFilmList());
		repository.put(CommandName.SHOW_USER_ACCOUNT, new ShowUserAccount());
		repository.put(CommandName.SHOW_PAYMENT_PAGE, new ShowPaymentPage());
		repository.put(CommandName.PAY_ORDER, new PayOrder());
		repository.put(CommandName.SHOW_USER_ACCOUNT_FOR_ADMIN, new ShowUserAccountForAdmin());
		repository.put(CommandName.ADD_DISCOUNT_TO_CLIENT, new AddDiscountToClient());
		repository.put(CommandName.USER_LIST, new GetUserList());
		repository.put(CommandName.CHANGE_USER_STATUS, new ChangeUserStatus());
		repository.put(CommandName.GET_USER_PAYMENT_HISTORY, new GetUserPaymentHistory());
		repository.put(CommandName.WATCH_FILM, new WatchFilm());
	}

	public Command getCommand(String commandName) {
		Command command;
		try {
			command = repository.get(CommandName.valueOf(commandName.toUpperCase()));
		} catch (IllegalArgumentException e) {
			LOGGER.error(e);
			command = repository.get(CommandName.WRONG_REQUEST);
		}
		return command;

	}

	public static CommandFactory getInstance() {
		return instance;
	}
}
