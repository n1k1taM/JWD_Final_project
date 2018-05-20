package by.epam.vshop.custom_tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTag extends SimpleTagSupport {
	private int currentPageNumber;
	private int maxPageNumber;
	private String paginationLink;

	private static final int MIN_PAGE = 1;

	public PaginationTag() {
		super();
	}
	
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public void setMaxPageNumber(int maxPageNumber) {
		this.maxPageNumber = maxPageNumber;
	}

	public void setPaginationLink(String paginationLink) {
		this.paginationLink = paginationLink;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();

		if ((maxPageNumber == 1)||(maxPageNumber == 0)) {
			return;
		}
		out.print("<nav>");
		out.print("<ul class=\"pagination\">");
		
		out.print(buildPreviousButton());

		out.print(buildPageButton(MIN_PAGE));

		if (currentPageNumber > MIN_PAGE + 2) {
			out.print(buildThreePointButton());
		}
		for (int pageNumber = currentPageNumber - 1; pageNumber <= currentPageNumber + 1; pageNumber++) {
			if (!(pageNumber <= MIN_PAGE) && !(pageNumber >= maxPageNumber)) {
				out.print(buildPageButton(pageNumber));
			}
		}
		if (currentPageNumber < maxPageNumber - 2) {
			out.print(buildThreePointButton());
		}

		out.print(buildPageButton(maxPageNumber));

		out.print(buildNextButton());
		
		out.println("</ul>");
		out.println("</nav>");
		
	}

	private String buildPageButton(int pageNumber) {
		StringBuilder builder = new StringBuilder();

		builder.append("<li class=\"page-item");

		if (pageNumber == currentPageNumber) {
			builder.append(" active");
		};
		builder.append("\">").append("<a class=\"page-link\" href=\"").append(paginationLink)
				.append("&pageNumber=" + pageNumber).append("\">").append(pageNumber).append("</a></li>");
		
		return builder.toString();
	}

	private String buildPreviousButton() {
		StringBuilder builder = new StringBuilder();
		if (currentPageNumber == MIN_PAGE) {
			builder.append("<li class=\"page-item disabled\">")
					.append("<a class=\"page-link\" href=\"#\" tabindex=\"-1\">Previous</a>").append("</li>");
		} else {
			builder.append("<li class=\"page-item\">").append("<a class=\"page-link\" href=\"").append(paginationLink)
					.append("&pageNumber=" + (currentPageNumber - 1)).append("\">Previous</a>").append("</li>");
		}
		return builder.toString();
	}



	private String buildThreePointButton() {
		StringBuilder builder = new StringBuilder();
		builder.append("<li class=\"page-item disabled\">")
		.append("<a class=\"page-link\" href=\"#\" tabindex=\"-1\">...</a>").append("</li>");
		return builder.toString();
	}
	private String buildNextButton(){
		StringBuilder builder = new StringBuilder();
		
		if (currentPageNumber == maxPageNumber) {
			builder.append("<li class=\"page-item disabled\">")
					.append("<a class=\"page-link\" href=\"#\" tabindex=\"-1\">Next</a>").append("</li>");
		} else {
			builder.append("<li class=\"page-item\">").append("<a class=\"page-link\" href=\"").append(paginationLink)
					.append("&pageNumber=" + (currentPageNumber + 1)).append("\">Next</a>").append("</li>");
		}
		return builder.toString();
		
	}
	

}
