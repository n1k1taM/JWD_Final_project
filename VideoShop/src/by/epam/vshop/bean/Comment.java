package by.epam.vshop.bean;

import java.io.Serializable;
import java.sql.Date;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int filmId;
	private int userId;
	private String userLogin;
	private String massage;
	private boolean status;
	private Date date;

	public Comment() {
		super();
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + filmId;
		result = prime * result + id;
		result = prime * result + ((massage == null) ? 0 : massage.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + userId;
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (filmId != other.filmId)
			return false;
		if (id != other.id)
			return false;
		if (massage == null) {
			if (other.massage != null)
				return false;
		} else if (!massage.equals(other.massage))
			return false;
		if (status != other.status)
			return false;
		if (userId != other.userId)
			return false;
		if (userLogin == null) {
			if (other.userLogin != null)
				return false;
		} else if (!userLogin.equals(other.userLogin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", filmId=" + filmId + ", userId=" + userId + ", userLogin=" + userLogin
				+ ", massage=" + massage + ", status=" + status + ", date=" + date + "]";
	}

}
