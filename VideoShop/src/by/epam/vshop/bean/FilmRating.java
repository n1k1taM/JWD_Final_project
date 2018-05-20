package by.epam.vshop.bean;

import java.io.Serializable;

public class FilmRating implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int userId;
	private int filmId;
	private float value;

	public FilmRating() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + filmId;
		result = prime * result + id;
		result = prime * result + userId;
		result = prime * result + Float.floatToIntBits(value);
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
		FilmRating other = (FilmRating) obj;
		if (filmId != other.filmId)
			return false;
		if (id != other.id)
			return false;
		if (userId != other.userId)
			return false;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FilmRating [id=" + id + ", userId=" + userId + ", filmId=" + filmId + ", value=" + value + "]";
	}

	

}
