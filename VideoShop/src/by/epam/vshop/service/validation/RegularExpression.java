package by.epam.vshop.service.validation;

public final class RegularExpression {
	private RegularExpression() {
	}
	
	public static final String NUMBER_REG =  "\\d+";
	public static final String POSITIVE_FLOAT_PATTERN = "^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$";
}
