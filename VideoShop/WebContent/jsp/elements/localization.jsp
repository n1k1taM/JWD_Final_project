<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC>

<html>
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="resource.local" var="loc" />

  	<fmt:message bundle="${loc}" key="local.button.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.button.en" var="en_button" />	

<!-- GENERAL BUTTONS -->
	<fmt:message bundle="${loc}" key="local.button.more" var="button_more"/>
	<fmt:message bundle="${loc}" key="local.button.singin" var="button_sign_in"/>
	<fmt:message bundle="${loc}" key="local.button.signup" var="button_sign_up"/>
	<fmt:message bundle="${loc}" key="local.button.logout" var="button_logout"/>
	<fmt:message bundle="${loc}" key="local.button.backToMain" var="button_back_to_main"/>
	<fmt:message bundle="${loc}" key="local.button.addToCart" var="button_add_to_cart"/>
	<fmt:message bundle="${loc}" key="local.button.addComment" var="button_add_comment"/>
	<fmt:message bundle="${loc}" key="local.button.addRating" var="button_add_rating"/>
	<fmt:message bundle="${loc}" key="local.button.cart" var="button_cart"/>
	<fmt:message bundle="${loc}" key="local.button.goToCart" var="button_go_to_cart"/>
	<fmt:message bundle="${loc}" key="local.button.watchFilm" var="button_watch_film"/>
	<fmt:message bundle="${loc}" key="local.button.addFilm" var="button_add_film"/>
	<fmt:message bundle="${loc}" key="local.button.editFilm" var="button_edit_film"/>
	<fmt:message bundle="${loc}" key="local.button.myFilms" var="button_my_films"/>
	<fmt:message bundle="${loc}" key="local.button.account" var="button_account"/>
	<fmt:message bundle="${loc}" key="local.button.userList" var="button_user_list"/>
	<fmt:message bundle="${loc}" key="local.button.changeDiscount" var="button_change_discount"/>
	<fmt:message bundle="${loc}" key="local.button.changeStatus" var="button_change_status"/>
	<fmt:message bundle="${loc}" key="local.button.makeOrder" var="button_make_order"/>
	<fmt:message bundle="${loc}" key="local.button.payOrder" var="button_pay_order"/>
	
	
	

<!-- LABELS -->
	<fmt:message bundle="${loc}" key="local.lable.genre" var="label_genre"/>
	<fmt:message bundle="${loc}" key="local.lable.genres" var="label_genres"/>
	<fmt:message bundle="${loc}" key="local.lable.adminMenu" var="admin_menu"/>
	<fmt:message bundle="${loc}" key="local.lable.year" var="label_year"/>
	<fmt:message bundle="${loc}" key="local.label.login" var="label_login"/>
	<fmt:message bundle="${loc}" key="local.label.password" var="label_password"/>
	<fmt:message bundle="${loc}" key="local.label.confirmPassword" var="label_confirm_password"/>
	<fmt:message bundle="${loc}" key="local.label.film" var="label_film"/>
	<fmt:message bundle="${loc}" key="local.label.price" var="label_price"/>
	<fmt:message bundle="${loc}" key="local.label.cost" var="label_cost"/>
	<fmt:message bundle="${loc}" key="local.label.discount" var="label_discount"/>
	<fmt:message bundle="${loc}" key="local.label.costAfterDiscount" var="label_cost_after_discount"/>
	<fmt:message bundle="${loc}" key="local.label.firstName" var="label_first_name"/>
	<fmt:message bundle="${loc}" key="local.label.lastName" var="label_last_name"/>
	<fmt:message bundle="${loc}" key="local.label.email" var="label_email"/>
	<fmt:message bundle="${loc}" key="local.label.date" var="label_date"/>
	<fmt:message bundle="${loc}" key="local.label.totalCost" var="label_total_cost"/>
	<fmt:message bundle="${loc}" key="local.label.status" var="label_status"/>
	<fmt:message bundle="${loc}" key="local.label.fullName" var="label_fullName"/>
	<fmt:message bundle="${loc}" key="local.label.title" var="label_title"/>
	<fmt:message bundle="${loc}" key="local.label.filmURL" var="label_film_URL"/>
	<fmt:message bundle="${loc}" key="local.label.trailerURL" var="label_trailer_URL"/>
	<fmt:message bundle="${loc}" key="local.label.shortDescription" var="label_short_description"/>
	<fmt:message bundle="${loc}" key="local.label.longDescription" var="label_long_description"/>
	<fmt:message bundle="${loc}" key="local.label.active" var="label_active"/>
	<fmt:message bundle="${loc}" key="local.label.blocked" var="label_blocked"/>
	<fmt:message bundle="${loc}" key="local.label.profile" var="label_profile"/>
	<fmt:message bundle="${loc}" key="local.label.orders" var="label_orders"/>
	<fmt:message bundle="${loc}" key="local.label.enter" var="label_enter"/>
	
	
	
	
	<fmt:message bundle="${loc}" key="local.label.title" var="title_label"/>
	<fmt:message bundle="${loc}" key="local.label.price" var="price_label"/>
	<fmt:message bundle="${loc}" key="local.label.description" var="description_label"/>
	<fmt:message bundle="${loc}" key="local.label.id" var="id_label"/>
	<fmt:message bundle="${loc}" key="local.label.signin" var="signin_label"/>
	<fmt:message bundle="${loc}" key="local.label.signup" var="signup_label"/>
	<fmt:message bundle="${loc}" key="local.label.login" var="login_label"/>
	<fmt:message bundle="${loc}" key="local.label.password" var="password_label"/>
	<fmt:message bundle="${loc}" key="local.label.confirmPassword" var="confirm_password_label"/>
	
	
	<fmt:message bundle="${loc}" key="local.label.name" var="name_label"/>
	<fmt:message bundle="${loc}" key="local.label.surname" var="surname_label"/>
	
	
	<fmt:message bundle="${loc}" key="local.label.phone" var="phone_label"/>
	<fmt:message bundle="${loc}" key="local.label.birthDate" var="birth_date_label"/>
	<fmt:message bundle="${loc}" key="local.label.dateOfPublication" var="date_of_publication_label"/>
	<fmt:message bundle="${loc}" key="local.label.action" var="action_label"/>
	<fmt:message bundle="${loc}" key="local.label.internet" var="internet_label"/>
	<fmt:message bundle="${loc}" key="local.label.digitalTV" var="digital_tv_label"/>
	<fmt:message bundle="${loc}" key="local.label.telephony" var="telephony_label"/>
	<fmt:message bundle="${loc}" key="local.label.tariffs" var="tariffs_label"/>
	<fmt:message bundle="${loc}" key="local.label.speed" var="speed_label"/>
	<fmt:message bundle="${loc}" key="local.label.channels" var="channels_label"/>
	<fmt:message bundle="${loc}" key="local.label.priceOfCityNumbers" var="price_of_city_numbers_label"/>
	<fmt:message bundle="${loc}" key="local.label.priceOfMobileNumbers" var="price_of_mobile_numbers_label"/>
	<fmt:message bundle="${loc}" key="local.label.dimensionPriceMonth" var="dimension_price_per_month"/>
	<fmt:message bundle="${loc}" key="local.label.dimensionPriceMinute" var="dimension_price_per_minute"/>
	<fmt:message bundle="${loc}" key="local.label.position" var="position_label"/>
	<fmt:message bundle="${loc}" key="local.label.blockedTo" var="blocked_to_label"/>
	<fmt:message bundle="${loc}" key="local.label.oldPassword" var="old_password_label"/>
	<fmt:message bundle="${loc}" key="local.label.newPassword" var="new_password_label"/>
	<fmt:message bundle="${loc}" key="local.label.confirmNewPassword" var="confirm_new_password_label"/>
	<fmt:message bundle="${loc}" key="local.label.passport" var="passport_label"/>
	<fmt:message bundle="${loc}" key="local.label.address" var="address_label"/>
	<fmt:message bundle="${loc}" key="local.label.internetServicesProvider" var="internet_services_provider_label"/>
	<fmt:message bundle="${loc}" key="local.label.getServiceRequests" var="get_service_requests_label"/>
	<fmt:message bundle="${loc}" key="local.label.getUserList" var="get_user_list_label"/>
	<fmt:message bundle="${loc}" key="local.label.type" var="type_label"/>
	<fmt:message bundle="${loc}" key="local.label.infoAboutProject" var="info_about_project_label"/>
	<fmt:message bundle="${loc}" key="local.label.themeOfTheProject" var="theme_of_the_proejct_label"/>
	<fmt:message bundle="${loc}" key="local.label.internetProviderService" var="internet_provider_service_label"/>
	<fmt:message bundle="${loc}" key="local.label.group" var="group_label"/>
	<fmt:message bundle="${loc}" key="local.label.socialNetworks" var="scoial_networks_label"/>
	<fmt:message bundle="${loc}" key="local.label.author" var="author_label"/>
	<fmt:message bundle="${loc}" key="local.label.albertZarankovich" var="albert_zarankovich_label"/>
	<fmt:message bundle="${loc}" key="local.label.paymentForService" var="payment_for_service_label"/>
	<fmt:message bundle="${loc}" key="local.label.balance" var="balance_label"/>
	<fmt:message bundle="${loc}" key="local.label.payTo" var="pay_to_label"/>
	<fmt:message bundle="${loc}" key="local.label.paymentFor" var="payment_for_label"/>
	<fmt:message bundle="${loc}" key="local.label.currency" var="currency_label"/>
	<fmt:message bundle="${loc}" key="local.label.amount" var="amount_label"/>
	<fmt:message bundle="${loc}" key="local.label.infoAboutUser" var="info_about_user_label"/>
	<fmt:message bundle="${loc}" key="local.label.found" var="found_label"/>
	<fmt:message bundle="${loc}" key="local.label.personalCabinet" var="personal_cabinet_label"/>
	<fmt:message bundle="${loc}" key="local.label.selectPosition" var="select_position_label"/>
	<fmt:message bundle="${loc}" key="local.label.changePosition" var="change_position_label"/>
	
	<fmt:message bundle="${loc}" key="local.label.setBlock" var="set_block_label"/>
	<fmt:message bundle="${loc}" key="local.label.selectDateForBlock" var="select_date_for_block_label"/>
	
	
	
<!-- LABESLS FOR GENERAL ACTIONS -->
	<fmt:message bundle="${loc}" key="local.label.Swift" var="swift_label"/>
	<fmt:message bundle="${loc}" key="local.label.language" var="language_label"/>
	<fmt:message bundle="${loc}" key="local.label.account" var="account_label"/>
	<fmt:message bundle="${loc}" key="local.label.home" var="home_label"/>
	<fmt:message bundle="${loc}" key="local.label.about" var="about_us_label"/>
	<fmt:message bundle="${loc}" key="local.label.news" var="news_label"/>
	<fmt:message bundle="${loc}" key="local.label.error" var="error_label"/>
	<fmt:message bundle="${loc}" key="local.label.management" var="management_label"/>
	<fmt:message bundle="${loc}" key="local.label.security" var="security_label"/>
	<fmt:message bundle="${loc}" key="local.label.authorization" var="authorization_label"/>
	<fmt:message bundle="${loc}" key="local.label.errorWrongRequest" var="error_wrong_request_label"/>
	
<!-- EDIT -->
	<fmt:message bundle="${loc}" key="local.label.edit.news" var="news_edit_label"/>
	
	<fmt:message bundle="${loc}" key="local.label.edit.tariff" var="tariff_edit_label"/>

<!-- ADD -->
	<fmt:message bundle="${loc}" key="local.label.add.news" var="news_add_label"/>
	
<!-- GET -->
	<fmt:message bundle="${loc}" key="local.label.get.allNews" var="news_get_all_label"/>

<!-- SECURITY -->
	<fmt:message bundle="${loc}" key="local.security.message" var="security_message"/>

<!-- ERROR -->
	<fmt:message bundle="${loc}" key="local.error.403.message" var="error_403_message"/>
	<fmt:message bundle="${loc}" key="local.error.404.message" var="error_404_message"/>
	<fmt:message bundle="${loc}" key="local.error.414.message" var="error_414_message"/>
	<fmt:message bundle="${loc}" key="local.error.504.message" var="error_504_message"/>
	
<!-- MESSAGESSSSSS -->
	<fmt:message bundle="${loc}" key="local.label.logo.welcomeSite" var="welcome_site_label"/>
	<fmt:message bundle="${loc}" key="local.label.quote" var="quote_label"/>
	<fmt:message bundle="${loc}" key="local.label.quote.authro" var="quote_authror_label"/>
	<fmt:message bundle="${loc}" key="local.label.head.one" var="head_one_label"/>
	<fmt:message bundle="${loc}" key="local.label.head.two" var="head_two_label"/>
	<fmt:message bundle="${loc}" key="local.label.head.three" var="head_three_label"/>
	<fmt:message bundle="${loc}" key="local.label.head.four" var="head_four_label"/>
	<fmt:message bundle="${loc}" key="local.label.head.five" var="head_five_label"/>
	<fmt:message bundle="${loc}" key="local.label.head.fix" var="head_fix_label"/>
	<fmt:message bundle="${loc}" key="local.label.head.seven" var="head_seven_label"/>
	<fmt:message bundle="${loc}" key="local.label.head.eight" var="head_eight_label"/>
	<fmt:message bundle="${loc}" key="local.label.head.nine" var="head_nine_label"/>
	<fmt:message bundle="${loc}" key="local.label.quote.aboutEpam" var="about_epam_label"/>
	<fmt:message bundle="${loc}" key="local.label.rules" var="rules_label"/>
	<fmt:message bundle="${loc}" key="local.label.rules.heading" var="rules_heading_label"/>
	
	
	<fmt:message bundle="${loc}" key="local.label.rules.one" var="rules_one_label"/>
	<fmt:message bundle="${loc}" key="local.label.rules.two" var="rules_two_label"/>
	<fmt:message bundle="${loc}" key="local.label.rules.three" var="rules_three_label"/>
	<fmt:message bundle="${loc}" key="local.label.rules.notation" var="rules_notation_label"/>
	<fmt:message bundle="${loc}" key="local.label.notation" var="notation_label"/>
	
	<fmt:message bundle="${loc}" key="local.label.account.createAgreement" var="create_agreement_label"/>
	<fmt:message bundle="${loc}" key="local.label.account.createRequestOnService" var="create_request_on_service_label"/>
	<fmt:message bundle="${loc}" key="local.label.account.getYourRequestOnService" var="get_your_request_on_service_label"/>
	<fmt:message bundle="${loc}" key="local.label.account.getYourBalance" var="get_your_balance_label"/>
	<fmt:message bundle="${loc}" key="local.label.account.getAgreementList" var="get_agreement_list_label"/>
	<fmt:message bundle="${loc}" key="local.label.account.getRequestList" var="get_request_list_label"/>
	<fmt:message bundle="${loc}" key="local.label.account.getNewsList" var="get_news_list_label"/>
	<fmt:message bundle="${loc}" key="local.label.account.addNewTypeOfTariff" var="add_new_type_of_tariff"/>
	
	<fmt:message bundle="${loc}" key="local.label.account.addTariff" var="add_tariff_label"/>
	<fmt:message bundle="${loc}" key="local.label.account.agreement" var="agreement_label"/>
	<fmt:message bundle="${loc}" key="local.label.agreement.rules.one" var="agreement_rules_one_label"/>
	<fmt:message bundle="${loc}" key="local.label.agreement.rules.notation" var="agreement_rules_notation_label"/>
	<fmt:message bundle="${loc}" key="local.label.account.agreementRules" var="agreement_rules_label"/>
	<fmt:message bundle="${loc}" key="local.label.account.requestOnService" var="request_on_service_label"/>
	<fmt:message bundle="${loc}" key="local.label.peroid" var="peroid_label"/>
	<fmt:message bundle="${loc}" key="local.label.ServiceAction" var="service_action_label"/>
	<fmt:message bundle="${loc}" key="local.label.requestId" var="request_id_label"/>
	<fmt:message bundle="${loc}" key="local.label.tariffID" var="tariff_id_label"/>
	<fmt:message bundle="${loc}" key="local.label.agreementId" var="agreement_id_label"/>
	<fmt:message bundle="${loc}" key="local.label.userId" var="user_id_label"/>
	<fmt:message bundle="${loc}" key="local.label.date" var="date_label"/>
	<fmt:message bundle="${loc}" key="local.label.close" var="close_label"/>
	<fmt:message bundle="${loc}" key="local.label.selectTypeOfTariff" var="select_type_of_tariff_label"/>
	<fmt:message bundle="${loc}" key="local.label.addTariff" var="add_tariff_label"/>
	<fmt:message bundle="${loc}" key="local.label.getTariffList" var="get_tariff_list_label"/>
	<fmt:message bundle="${loc}" key="local.label.userInfo" var="user_info_label"/>
	
	
	<fmt:message bundle="${loc}" key="local.label.enable" var="enable_label"/>
	<fmt:message bundle="${loc}" key="local.label.disable" var="disable_label"/>
	<fmt:message bundle="${loc}" key="local.label.accepted" var="accepted_label"/>
	<fmt:message bundle="${loc}" key="local.label.yes" var="yes_label"/>
	<fmt:message bundle="${loc}" key="local.label.no" var="no_label"/>
	<fmt:message bundle="${loc}" key="local.label.result" var="result_label"/>
	
	<fmt:message bundle="${loc}" key="local.label.tariff.loadType" var="tariff_load_type_label"/>

<!-- INPUT TITLE -->
	<fmt:message bundle="${loc}" key="local.input.title.login" var="input_title_login"/>
	<fmt:message bundle="${loc}" key="local.input.title.name" var="input_title_name"/>
	<fmt:message bundle="${loc}" key="local.input.title.password" var="input_title_password"/>
	<fmt:message bundle="${loc}" key="local.input.title.email" var="input_title_email"/>
	<fmt:message bundle="${loc}" key="local.input.title.phone" var="input_title_phone"/>
	<fmt:message bundle="${loc}" key="local.input.title.passport" var="input_title_passport"/>
</html>