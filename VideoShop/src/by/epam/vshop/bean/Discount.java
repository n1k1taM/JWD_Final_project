package by.epam.vshop.bean;

import java.io.Serializable;

public class Discount implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int persеnt;

	public Discount() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersеnt() {
		return persеnt;
	}

	public void setPersеnt(int persеnt) {
		this.persеnt = persеnt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + persеnt;
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
		Discount other = (Discount) obj;
		if (id != other.id)
			return false;
		if (persеnt != other.persеnt)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Discount [id=" + id + ", persеnt=" + persеnt + "]";
	}

}
